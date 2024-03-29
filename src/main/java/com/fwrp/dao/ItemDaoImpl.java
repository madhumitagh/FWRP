/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dao;
import com.fwrp.model.Item;
import com.fwrp.database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author mrinm
 */
public class ItemDaoImpl implements ItemDao {

    @Override
    public boolean insert(Item item) {
        Integer item_id;
        Connection conn = DBConnection.getConnection();
        String query = String.format("INSERT into item (item_type,name) " +
                                     "VALUES (\"%s\",\"%s\")",
                                     item.getItemtype(), item.getName());
        try {
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	    ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
	    if (rs.next()) {
                item_id = rs.getInt(1);
                item.setId(item_id);
	    }
        } catch (SQLException e) {
            System.out.println("DB Create Failed for Item " + query +
                               " Message: " + e.getMessage());
            return false;
	}
	return true;
    }
    
    @Override
    public boolean check(Integer id) {
        Connection conn = DBConnection.getConnection();
        String query = String.format("SELECT * from item where item_id=%d", id);
        try {
            PreparedStatement ps = conn.prepareStatement(query);		
	    ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("DB delete Failed for " + query +
                               " Message: " + e.getMessage());
	}
	return false;
    }

    @Override
    public boolean delete(Integer id) {
        if (this.check(id)) {
            Connection conn = DBConnection.getConnection();
            String query = String.format("DELETE from item where item_id=%d", id);
            try {
                PreparedStatement ps = conn.prepareStatement(query,
                                                    Statement.SUCCESS_NO_INFO);		
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println("DB delete Failed for " + query +
                                   " Message: " + e.getMessage());
                return false;
            }
        }
        return true;
    }
}
