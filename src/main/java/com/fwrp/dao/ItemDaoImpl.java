/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dao;
import com.fwrp.model.Item;
import com.fwrp.model.factory.ItemFactory;
import com.fwrp.database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author madhumita,piyalee,pooja,shilpi
 */
public class ItemDaoImpl implements ItemDao {

    private static ItemDaoImpl itemDao = null;
    
    public static ItemDaoImpl getInstance() {
        if (itemDao == null) {
            itemDao =  new ItemDaoImpl();
        }
        return itemDao;
    }
    
    @Override
    public boolean insert(Item item) {
        Integer item_id;
        Connection conn = DBConnection.getConnection();
        String query = String.format("INSERT into items (Item_Type,Name) " +
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
        String query = String.format("SELECT * from items where Item_ID=%d", id);
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
    public Item check(String itemType, String itemName) {
        Connection conn = DBConnection.getConnection();
        Item item = null;
        String query = String.format("SELECT * from items where Item_Type=\"%s\" and Name=\"%s\"",
                                     itemType,itemName);
        try {
            PreparedStatement ps = conn.prepareStatement(query);		
	    ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                item = ItemFactory.createWithId(itemType, itemName, rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("DB delete Failed for " + query +
                               " Message: " + e.getMessage());
	}
	return item;
    }

    @Override
    public boolean delete(Integer id) {
        if (this.check(id)) {
            Connection conn = DBConnection.getConnection();
            String query = String.format("DELETE from items where Item_ID=%d", id);
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

    @Override
    public Item get(Integer itemId) {
        Item item = null;
        Connection conn = DBConnection.getConnection();
        String query = String.format("SELECT * from items where Item_ID=%d", itemId);
        try {
            PreparedStatement ps = conn.prepareStatement(query);		
	    ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                item = ItemFactory.createWithId(rs.getString("Item_Type"),
                                                   rs.getString("Name"),
                                                   itemId);
            }
        } catch (SQLException e) {
            System.out.println("DB delete Failed for " + query +
                               " Message: " + e.getMessage());
	}
	return item;
    }
    
    @Override
    public ArrayList<Item> getAll() {
        ArrayList items = new ArrayList<Item>();
        Connection conn = DBConnection.getConnection();
        String query = String.format("SELECT * from items");
        try {
            PreparedStatement ps = conn.prepareStatement(query);		
	    ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                items.add(ItemFactory.createWithId(rs.getString("Item_Type"),
                                                   rs.getString("Name"),
                                                   rs.getInt("Item_ID")));
            }
            return items;
        } catch (SQLException e) {
            System.out.println("DB delete Failed for " + query +
                               " Message: " + e.getMessage());
	}
	return items;
    }
}
