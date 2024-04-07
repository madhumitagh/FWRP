/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.model.factory;

import com.fwrp.model.Individual;
import com.fwrp.model.Entity;

/**
 *
 * @author mrinm
 */
public class IndividualFactory extends ConsumerFactory {

    public static IndividualFactory individualFactory = null;
    
    public static IndividualFactory getInstance() {
        if (individualFactory == null) {
            individualFactory = new IndividualFactory();
        }
        return individualFactory;
    }
    
    @Override
    public Entity getConsumer(String username, String password, String name) {
        return new Individual(username, password, name);
    }
}
