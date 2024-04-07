/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dao;

import com.fwrp.model.Consumption;
import java.util.ArrayList;
import com.fwrp.database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import com.fwrp.model.*;

/**
 *
 * @author mrinm
 */
public class ConsumerDaoImpl implements ConsumerDao {

    private static ConsumerDaoImpl consumerDao = null;
    
    private ConsumerDaoImpl() {}
    
    public static ConsumerDaoImpl getInstance() {
        if (consumerDao == null) {
            consumerDao =  new ConsumerDaoImpl();
        }
        return consumerDao;
    }
    
    @Override
    public ArrayList<Consumption> getAll() {
        ArrayList consumptions = new ArrayList<Consumption>();
        Connection conn = DBConnection.getConnection();
        String query = String.format("SELECT * from consumption");
        String type;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Consumption consumption = null;
        EntityType entityType;
        try {
            PreparedStatement ps = conn.prepareStatement(query);		
	    ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                type = rs.getString("ConsumerType");
                if (type.equals("CHA")) {
                    entityType = EntityType.CHARITY;
                } else if (type.equals("IND")) {
                    entityType = EntityType.INDIVIDUAL;
                } else {
                    System.out.println("Invalid Enityt Type " + type);
                    break;
                }

                consumption = new Consumption(rs.getInt("Item_ID"),
                                               rs.getInt("Retailer_ID"),
                                               rs.getInt("Consumer_ID"),
                                               entityType,
                                               rs.getInt("Quantity"),
                                               fmt.parse(rs.getString("Date_Purchased")),
                                               fmt.parse(rs.getString("Expiration_Date")),
                                               rs.getDouble("Price_Purchased"));
                consumptions.add(consumption);
            }
        } catch (SQLException e) {
            System.out.println("DB Insert Failed for " + query +
                               " Message: " + e.getMessage());
	} catch (ParseException e) {
            System.out.println("Parse Excption for " + query +
                               " Message: " + e.getMessage());
        }
        return consumptions;
    }

    @Override
    public boolean insert(Consumption entry) {
        Connection conn = DBConnection.getConnection();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        String query = String.format("INSERT into consumption (Item_ID,Retailer_ID," +
                                     "Expiration_Date,ConsumerType,Consumer_ID," + 
                                     "Quantity,Date_Purchased,Price_Purchased " +
                                     "VALUES (%d,%d,\"%s\",\"%s\",%d,%d,\"%s\",%f)",
                                     entry.getItemId(), entry.getRetailerId(),
                                     fmt.format(entry.getExpirationDate()),
                                     entry.getConsumerTypeStr(),
                                     entry.getConsumerId(), entry.getQuantity(),
                                     fmt.format(entry.getPurchaseDate()),
                                     entry.getPrice());
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
}
