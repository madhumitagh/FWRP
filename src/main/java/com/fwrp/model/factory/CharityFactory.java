/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.model.factory;

import com.fwrp.model.Entity;
import com.fwrp.model.Charity;

/**
 *
 * @author mrinm
 */
public class CharityFactory extends ConsumerFactory {

    @Override
    public Entity getConsumer(String username, String password, String name) {
        return new Charity(username, password, name);
    }
}
