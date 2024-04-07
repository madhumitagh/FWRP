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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author madhumita
 */
public class StockDaoImpl implements StockDao {
    private static StockDaoImpl stockDao = null;
    
    private StockDaoImpl() {};
    public static StockDaoImpl getInstance() {
        if (stockDao == null) {
            stockDao =  new StockDaoImpl();
        }
        return stockDao;
    }
    
    public boolean check(Stock stock) {
        return true;
    }
    
    @Override
    public boolean insert(Stock stock) {
        Connection conn = DBConnection.getConnection();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        String query = String.format("INSERT into stock (Item_id,Retailer_id," +
                                     " Expiration_Date, Discounted_Price, Quantity,Surplus) " +
                                     "VALUES (\"%d\",\"%d\",\"%s\",\"%f\",\"%s\")",
                                     stock.getItemId(),
                                     stock.getRetailerId(),
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
        String query = String.format("DELETE from stock where Item_Id=%d and " +
                                     " Retailer_Id = %d",itemId,retailerId);
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
        String query = String.format("UPDATE from stock SET Surplus=\"%s\" " +
                                     "where Item_Id=%d and Retailer_Id = %d " +
                                     "and Expiration_Date = \"%s\"",
                                     surplus, stock.getItemId(),
                                     stock.getRetailerId(),
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
    
    @Override
    public ArrayList<Stock> getAll(Integer retailerId) {
        ArrayList stocks = new ArrayList<Stock>();
        Connection conn = DBConnection.getConnection();
        String query = String.format("SELECT * from stock where retailer_id = %d ",
                                     retailerId);
                                     
        try {
            PreparedStatement ps = conn.prepareStatement(query);		
	    ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                
                try {
                    stocks.add(new Stock(Integer.parseInt(rs.getString("Item_Id")),
                            Integer.parseInt(rs.getString("Retailer_Id")),
                            fmt.parse(rs.getString("Expiration_Date")),
                            Double.parseDouble(rs.getString("Discounted_Price")),
                            Integer.valueOf(rs.getString("Quantity")),
                            Boolean.parseBoolean(rs.getString("Surplus"))));
                } catch (ParseException ex) {
                    Logger.getLogger(StockDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return stocks;
        } catch (SQLException e) {
            System.out.println("DB delete Failed for " + query +
                               " Message: " + e.getMessage());
	}
	return stocks;
    }
    
    @Override
    public ArrayList<Stock> getAll() {
        ArrayList stocks = new ArrayList<Stock>();
        Connection conn = DBConnection.getConnection();
        String query = String.format("SELECT * from stock");
                                     
        try {
            PreparedStatement ps = conn.prepareStatement(query);		
	    ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                
                try {
                    stocks.add(new Stock(Integer.parseInt(rs.getString("Item_Id")),
                            Integer.parseInt(rs.getString("Retailer_Id")),
                            fmt.parse(rs.getString("Expiration_Date")),
                            Double.parseDouble(rs.getString("Discounted_Price")),
                            Integer.valueOf(rs.getString("Quantity")),
                            Boolean.parseBoolean(rs.getString("Surplus"))));
                } catch (ParseException ex) {
                    Logger.getLogger(StockDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return stocks;
        } catch (SQLException e) {
            System.out.println("DB delete Failed for " + query +
                               " Message: " + e.getMessage());
	}
	return stocks;
    }
}
