/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fwrp.dao;
import java.util.ArrayList;
import com.fwrp.model.Consumption;

/**
 *
 * @author Madhumita, Piyalee, Pooja, Shilpi
 */
public interface ConsumerDao {
    ArrayList<Consumption> getAll();
    boolean insert(Consumption entry);
}
