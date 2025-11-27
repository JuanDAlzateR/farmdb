package com.solvd.farm.dao.impl;

import com.solvd.farm.dao.impl.mysql.CountableDAO;
import com.solvd.farm.dao.interfaces.ICountableDAO;
import com.solvd.farm.model.Countable;
import com.solvd.farm.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.util.List;

/**
 * Unit tests for the class CountableDAO.java (Mysql and Mybatis)
 * Tests
 */
@Listeners(TestNGListener.class)
public class CountableDAOTest {
    public static final Logger LOGGER = LogManager.getLogger(CountableDAOTest.class);
    private static ICountableDAO countableDAO;
    private Countable testCountable;


    // Configuration method (setup) to inject the corresponding implementation
    // TestNG injects the parameter in CountableDAOSuite.xml
    @BeforeTest(alwaysRun = true) //Forces execution ignoring group and method filters
    public void updateCountableDAO(ITestContext context) {
        if (countableDAO == null) {
            String daoType = context.getCurrentXmlTest().getParameter("daoType");
            LOGGER.info("starting setup... chosen daoType: " + daoType);
            if (daoType == null) {
                LOGGER.info("unsupported DAO type: " + daoType + " using Mysql by default.");
                this.countableDAO = new CountableDAO();
            } else if (daoType.equalsIgnoreCase("Mysql")) {
                this.countableDAO = new CountableDAO();
                LOGGER.info("using MySql...");
            } else if (daoType.equalsIgnoreCase("Mybatis")) {
                this.countableDAO = new com.solvd.farm.dao.impl.mybatis.CountableDAO();
                LOGGER.info("using Mybatis...");
            }
        }

    }


    @Test(groups = {"delete after"}, testName = "Verify insertion and ID", description = "verifies insertion and generated ID injection", dataProvider = "Countable Provider")
    public void testSaveCountableSuccessAndIdInjection(Countable countable) {
        // The initial ID must be 0
        countable.setCountableId(0);
        int initialId = countable.getCountableId();

        // ACT
        countableDAO.save(countable);
        this.testCountable = countable;
        // ASSERT

        // 1. No exception (implicit)

        // 2. verify ID injection.
        Assert.assertNotEquals(initialId, countable.getCountableId(),
                "Countable ID wasn't updated after injection");

        // 3. Verifiy object added to DB
        Countable retrievedCountable = countableDAO.getCountableById(countable.getCountableId());

        Assert.assertNotNull(retrievedCountable,
                "Object couldn't be retrieved from DB.");

        // 4. Verifiy data
        Assert.assertEquals(retrievedCountable.getName(), countable.getName(), "retrieved object name doesn't match.");
        Assert.assertEquals(retrievedCountable.getCountableId(), countable.getCountableId(), "ID's doesn't match.");

    }


    @Test(groups = {"save before"}, testName = "Verify delete", description = "verifies delete of a Countable", dataProvider = "Countable Provider")
    public void testDeleteCountable(Countable countable) {

        // ACT (Acción): Ejecutar el método a probar
        countableDAO.delete(countable.getCountableId());

        // ASSERT

        // 1. No exception (implicit)

        // 2. Verifiy object is not in DB
        Countable retrievedCountable = countableDAO.getCountableById(countable.getCountableId());

        Assert.assertNull(retrievedCountable,
                "Object hasn't been deleted from DB.");
        this.testCountable = null;

    }

    @Test(groups = {"save before", "delete after"}, testName = "check find countable by id", description = "verifies finding countables", dataProvider = "Countable Provider")
    public void testGetCountableById(Countable countable) {

        Countable countable1 = countableDAO.getCountableById(countable.getCountableId());

        Assert.assertEquals(countable1.getName(), countable.getName(), "Objects doesn't have the same name");
        Assert.assertEquals(countable1.getCountableId(), countable.getCountableId(), "Objects doesn't have the same id");

    }

    @Test(groups = {"save before", "delete after"}, testName = "Verify list creation", description = "verifies retriaval of Countable list", dataProvider = "Countable Provider")
    public void testGetCountableList(Countable countable) {

        List<Countable> list = countableDAO.countableList();

        Assert.assertFalse(list.isEmpty(), "The retrieved list is empty");

        Assert.assertEquals(list.getLast().getName(), countable.getName(), "Object is not at the end of the list");

    }

    @Test(groups = {"save before", "delete after"}, testName = "Verify update countable", description = "verifies update in DB", dataProvider = "Countable Provider")
    public void testUpdateCountable(Countable countable) {

        Countable updateCountable = countableDAO.getCountableById(testCountable.getCountableId());
        updateCountable.setName(updateCountable.getName() + " updated");

        countableDAO.update(updateCountable);

        Countable retriveCountable = countableDAO.getCountableById(testCountable.getCountableId());

        Assert.assertEquals(retriveCountable.getName(), testCountable.getName() + " updated", "Object didn't update");

    }

    @DataProvider(name = "Countable Provider")
    public Object[][] data() {
        Product countable1 = new Product();
        countable1.setCountable(0, "Apple Test", 1, 1);
        Product countable2 = new Product();
        countable2.setCountable(0, "Lemon Test", 2, 1);
        return new Object[][]{
                //name, quantity, farmId
                {countable1},
                {countable2},
        };
    }

    @BeforeMethod(groups = {"save before"})
    public void addCountableToDB(Object[] data) { //the Before method receives only one of <ITestContext, XmlTest, Method, Object[], ITestResult>, not specific classes

        // 1. Verificar si se recibió el array de datos (siempre bueno)
        if (data == null || data.length == 0) {
            throw new RuntimeException("DataProvider didn't provide data.");
        }

        // 2. Hacer el cast del primer elemento del array al tipo Countable
        Countable countable = (Countable) data[0];

        countableDAO.save(countable);

        this.testCountable = countable;
//        LOGGER.info("Saving Countable with ID: "+countable.getCountableId());
    }

    @AfterMethod(groups = {"delete after"})
    public void cleanupTestData() {
        if (testCountable != null && testCountable.getCountableId() > 0) {
            countableDAO.delete(testCountable.getCountableId());
//            LOGGER.info("Cleaning Countable with ID: " + testCountable.getCountableId());
        }
    }


}