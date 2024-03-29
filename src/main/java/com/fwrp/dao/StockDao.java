/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fwrp.dao;
import com.fwrp.model.Stock;
/**
 *
 * @author mrinm
 */
public interface StockDao {
    public boolean insert(Stock stock);
    public boolean delete(Integer itemId, Integer retailerId);
    public boolean update(Stock stock);
    public boolean markSurplus(Stock stock, boolean surplus);
}