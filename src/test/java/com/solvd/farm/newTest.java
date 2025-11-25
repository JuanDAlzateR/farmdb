package com.solvd.farm;

import org.testng.annotations.*;

import java.util.Set;

/**
 * Unit tests for the a class.
 * Tests
 */
public class newTest{

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite: Setting up resources for the entire test suite.");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Before Test: Initializing resources for this specific test block.");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("Before Class: Setting up resources for this test class.");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Before Method: Executing before each test method.");
    }

    @Test
    public void testMethod1() {
        System.out.println("Running Test Method 1.");
    }

    @Test
    public void testMethod2() {
        System.out.println("Running Test Method 2.");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("After Method: Executing after each test method.");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("After Class: Cleaning up resources for this test class.");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("After Test: Tearing down resources for this specific test block.");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("After Suite: Cleaning up resources for the entire test suite.");
    }
}

