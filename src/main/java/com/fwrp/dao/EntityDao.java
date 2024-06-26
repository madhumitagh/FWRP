/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fwrp.dao;
import com.fwrp.model.Entity;
import java.util.ArrayList;
/**
 *
 * @author madhumita, piyalee.pooja,shilpi
 */
public interface EntityDao {
    public Entity authenticate(Entity entity);
    public boolean check(String username);
    public boolean enList(Entity entity);
    public boolean deList(Entity entity);
    public boolean subscriber(Entity entity);
    public ArrayList<Entity> getAllSubscribed();
}
