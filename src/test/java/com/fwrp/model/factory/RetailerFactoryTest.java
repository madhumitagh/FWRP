/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.fwrp.model.factory;

import com.fwrp.model.Retailer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Madhumita,Piyalee,Pooja,Shilpi
 */
public class RetailerFactoryTest {
    
    public RetailerFactoryTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }

    /**
     * Test of create method, of class RetailerFactory.
     */
    @org.junit.jupiter.api.Test
    public void testCreate() {
        System.out.println("Testing create method");
        String username = "user123";
        String password = "pass123";
        String name = "RetailerName";
        Retailer result = RetailerFactory.create(username, password, name);
        
        assertNotNull(result, "The created Retailer object should not be null.");
        assertEquals(username, result.getUsername(), "Username should match the provided input.");
        assertEquals(password, result.getPassword(), "Password should match the provided input.");
        assertEquals(name, result.getName(), "Name should match the provided input.");
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
