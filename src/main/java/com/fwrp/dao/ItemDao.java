/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fwrp.dao;

import com.fwrp.model.Item;
import java.util.ArrayList;

/**
 *
 * @author madhumita, piyalee,pooja,shilpi
 */
public interface ItemDao {
    public boolean insert(Item item);
    public boolean delete(Integer id);
    public boolean check(Integer id);
    public ArrayList<Item> getAll();
}
