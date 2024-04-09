/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dao;

import com.fwrp.database.DBConnection;
import com.fwrp.model.Entity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.fwrp.model.factory.IndividualFactory;

/**
 *
 * @author madhumita, piyalee, pooja, shilpi
 */
public class IndividualDaoImpl implements EntityDao {

    private static IndividualDaoImpl individualDao = null;
    
    public static IndividualDaoImpl getInstance() {
        if (individualDao == null) {
            individualDao =  new IndividualDaoImpl();
        }
        return individualDao;
    }
    
    @Override
    public Entity authenticate(Entity entity) {
        Connection conn = DBConnection.getConnection();
        
        /* check username/password */
        String query = String.format("SELECT * from individual where " +
                                     "Username=\"%s\" and BINARY Password=\"%s\"",
                                     entity.getUsername(), entity.getPassword());
        try {
            PreparedStatement ps = conn.prepareStatement(query);		
	    ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                entity.setName(rs.getString("Name"));
                entity.setId(rs.getInt("Individual_ID"));
                entity.setSubscribe(rs.getBoolean("Subscribe"));
                return entity;
            }
        } catch (SQLException e) {
            System.out.println("DB delete Failed for " + query +
                               " Message: " + e.getMessage());
	}
	return null;
    }

    @Override
    public boolean enList(Entity entity) {
        Integer id;
        Connection conn = DBConnection.getConnection();
        String query = String.format("INSERT into individual (Username,Password," +
                                     "Name,Subscribe) " +
                                     "VALUES (\"%s\",\"%s\",\"%s\",%s)",
                                     entity.getUsername(), entity.getPassword(),
                                     entity.getName(),entity.isSubscribe());
        try {
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	    ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
	    if (rs.next()) {
                id = rs.getInt(1);
                entity.setId(id);
	    }
        } catch (SQLException e) {
            System.out.println("DB Create Failed for Charity " + query +
                               " Message: " + e.getMessage());
            return false;
	}
	return true;

    }

    @Override
    public boolean deList(Entity entity) {
        if (this.check(entity.getUsername())) {
            Connection conn = DBConnection.getConnection();
            String query = String.format("DELETE from individual where id=%d",
                                         entity.getId());
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
    public boolean subscriber(Entity entity) {
        Connection conn = DBConnection.getConnection();
        String query = String.format("UPDATE from individual SET subscriber=%s " +
                                     "where id=%d",
                                     entity.isSubscribe(), entity.getId());                

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
    public boolean check(String username) {
        Connection conn = DBConnection.getConnection();
        /* check username */
        String query = String.format("SELECT * from individual where " +
                                     "username=\"%s\"", username);
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
    public ArrayList<Entity> getAllSubscribed() {
        Connection conn = DBConnection.getConnection();
         ArrayList<Entity> list = new ArrayList();
        /* check username */
        String query = "SELECT * from charity where Subscribe=true";
        try {
            PreparedStatement ps = conn.prepareStatement(query);		
	    ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(IndividualFactory.getInstance().getConsumer(rs.getString("Username"),
                                                                     rs.getString("Password"),
                                                                     rs.getString("Name")));
            }
        } catch (SQLException e) {
            System.out.println("DB fetch Failed for " + query +
                               " Message: " + e.getMessage());
	}
	return list;
    }
}
