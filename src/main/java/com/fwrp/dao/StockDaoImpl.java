/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dao;

import com.fwrp.model.Stock;
import com.fwrp.database.DBConnection;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

/**
 *
 * @author mrinm
 */
public class StockDaoImpl implements StockDao {
    
    public boolean check(Stock stock) {
        return true;
    }
    
    @Override
    public boolean insert(Stock stock) {
        Connection conn = DBConnection.getConnection();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        String query = String.format("INSERT into stock (item_id,retailer_id," +
                                     " exp_date, price, quantity,surplus) " +
                                     "VALUES (\"%d\",\"%d\",\"%s\",\"%f\",\"%s\")",
                                     stock.getItem().getId(),
                                     stock.getRetailer().getId(),
                                     fmt.format(stock.getExpiryDate()),
                                     stock.getDiscountedPrice(),
                                     stock.IsSurplus());
        try {
            PreparedStatement ps = conn.prepareStatement(query, Statement.SUCCESS_NO_INFO);		
	    ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DB Create Failed for " + query +
                               " Message: " + e.getMessage());
            return false;
	}
	return true;
    }

    @Override
    public boolean delete(Integer itemId, Integer retailerId) {
        Connection conn = DBConnection.getConnection();
        String query = String.format("DELETE from stock where item_id=%d and " +
                                     " retailer_id = %d",itemId,retailerId);
        try {
            PreparedStatement ps = conn.prepareStatement(query, Statement.SUCCESS_NO_INFO);		
	    ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DB delete Failed for " + query +
                               " Message: " + e.getMessage());
            return false;
	}
	return true;
    }

    @Override
    public boolean update(Stock stock) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean markSurplus(Stock stock, boolean surplus) {
        Connection conn = DBConnection.getConnection();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        String query = String.format("UPDATE from stock SET surplus=\"%s\" " +
                                     "where item_id=%d and retailer_id = %d " +
                                     "and exp_date = \"%s\"",
                                     surplus, stock.getItem().getId(),
                                     stock.getRetailer().getId(),
                                     fmt.format(stock.getExpiryDate()));
                
                                     
        try {
            PreparedStatement ps = conn.prepareStatement(query, Statement.SUCCESS_NO_INFO);		
	    ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DB update Failed for " + query +
                               " Message: " + e.getMessage());
            return false;
	}
	return true;        
    }
    
}
