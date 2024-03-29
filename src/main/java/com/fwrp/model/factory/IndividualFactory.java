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

    @Override
    public Entity getConsumer(String username, String password, String name) {
        return new Individual(username, password, name);
    }
}
