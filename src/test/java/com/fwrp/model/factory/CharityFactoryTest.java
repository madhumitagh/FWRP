/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.fwrp.model.factory;

import com.fwrp.model.Entity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author pooja
 */
public class CharityFactoryTest {
    
    public CharityFactoryTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class CharityFactory.
     */
    @Test
   public void testGetInstance() {
        System.out.println("getInstance");
        CharityFactory firstInstance = CharityFactory.getInstance();
        CharityFactory secondInstance = CharityFactory.getInstance();
        assertSame(firstInstance, secondInstance);
    }
}

   