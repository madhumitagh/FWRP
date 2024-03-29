/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.model;

/**
 *
 * @author mrinm
 */
public class Retailer extends Entity {

    public Retailer(String username, String password, String name) {
        super(username, password, name, EntityType.RETAILER);
    }
    
    @Override
    protected boolean authenticate() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void updateItem() {
    }
    
}
