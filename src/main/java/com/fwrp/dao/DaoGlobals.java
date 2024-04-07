/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.dao;
import com.fwrp.database.DBConnection;

/**
 *
 * @author madumita,piyalee, pooja, shilpi
 */
public class DaoGlobals {
    public static void setServDir(String srvDir) {
        DBConnection.setServerPath(srvDir);
    }    
}
