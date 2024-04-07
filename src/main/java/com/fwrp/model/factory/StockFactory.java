/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.model.factory;
import com.fwrp.model.Stock;
import java.util.Date;

/**
 *
 * @author madhu
 */
public class StockFactory {
    public static Stock create(int itemId, int retailerId, Date expiryDate,
                               double price, Integer quantity,
                               boolean surplus) {
        return new Stock(itemId, retailerId, expiryDate, price, quantity, surplus);
    }
    
    public static Stock create(int itemId, int retailerId, Date expiryDate) {
        return new Stock(itemId, retailerId, expiryDate);
    }

}
