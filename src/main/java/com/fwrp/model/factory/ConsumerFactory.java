/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.model.factory;
import com.fwrp.model.Entity;

/**
 *
 * @author Madhumita, Piyalee, Pooja, Shilpi
 */
public abstract class ConsumerFactory {
    public abstract Entity getConsumer(String username,
                                       String password,
                                       String name);
}
