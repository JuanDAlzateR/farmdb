package com.solvd.farm.dao.impl.mybatis;

import com.solvd.farm.model.Countable;
import com.solvd.farm.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Unit tests for the class mysql/CountableDAO.java
 * Tests
 */
public class CountableDAOTest {
    public static final Logger LOGGER = LogManager.getLogger(CountableDAOTest.class);
    private final CountableDAO countableDAO = new CountableDAO();
    private Countable testCountable;

    @Test(groups = {"delete after"}, testName = "Verify insertion and ID", description = "verifies insertion and generated ID injection", dataProvider = "Countable Provider")
    public void testSaveCountableSuccessAndIdInjection(Countable countable) {
        // The initial ID must be 0
        countable.setCountableId(0);
        int initialId = countable.getCountableId();

        // ACT (Acción): Ejecutar el método a probar
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

    @Test(groups = {"save before", "delete after"}, testName = "Verify list creation", description = "verifies retriaval of Countable list", dataProvider = "Countable Provider")
    public void testGetCountableList(Countable countable) {

        List<Countable> list = countableDAO.countableList();

        Assert.assertFalse(list.isEmpty(), "The retrieved list is empty");

        Assert.assertEquals(list.getLast().getName(), countable.getName(), "Object is not at the end of the list");

    }

    @Test(groups = {"save before", "delete after"}, testName = "Verify list creation", description = "verifies retriaval of Countable list", dataProvider = "Countable Provider")
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
        LOGGER.info("save before group");
    }

    @AfterMethod(groups = {"delete after"})
    public void cleanupTestData() {
        if (testCountable != null && testCountable.getCountableId() > 0) {
            countableDAO.delete(testCountable.getCountableId());
            LOGGER.info("Cleaning Countable with ID: " + testCountable.getCountableId());
        }
    }


}