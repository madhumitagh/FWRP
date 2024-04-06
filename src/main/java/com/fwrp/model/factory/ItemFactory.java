/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.model.factory;

import com.fwrp.model.Item;

/**
 *
 * @author mrinm
 */
public class ItemFactory {
    
    public static Item create(String itemtype, String name) {
        return new Item(itemtype, name);
    }
    
    public static Item createWithId(String itemtype, String name, Integer id) {
        return new Item(itemtype, name, id);
    }

}
