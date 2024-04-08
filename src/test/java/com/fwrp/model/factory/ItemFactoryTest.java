/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.fwrp.model.factory;

import com.fwrp.model.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author madhu
 */
public class ItemFactoryTest {
    
    public ItemFactoryTest() {
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
     * Test of create method, of class ItemFactory.
     */
    @Test
    public void testCreate() {
        System.out.println("Testing create Method");
        String itemType = "exampleType";
        String name = "exampleName";
        Item result = ItemFactory.create(itemType, name);
        assertNotNull(result, "Item should not be done after creation.");
        //assertEquals(itemType, result.getItemType(),"Item type should match the input.");
        assertEquals(name, result.getName(),"Item name should match the input.");
    }

    /**
     * Test of createWithId method, of class ItemFactory.
     */
    @Test
    public void testCreateWithId() {
        System.out.println("Testing createWithId method");
        String itemtype = "exampleTypeWithId";
        String name = "exampleNameWithId";
        Integer id = 1;
        Item result = ItemFactory.createWithId(itemtype, name, id);
        assertNotNull(result, "Item should not be null when created with an Id.");
         assertEquals(id, result.getId(),"Item Id should match the input.");
          //assertEquals(itemType, result.getItemType(),"Item type should match the input.");
           assertEquals(name, result.getName(),"Item name should match the input.");         
      
    }
    
}
