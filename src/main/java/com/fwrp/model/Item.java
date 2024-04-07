/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.model;

/**
 *
 * @author Madhumita, Piyalee, Pooja, Shilpi
 */
public class Item {

    public Item(String Itemtype, String Name) {
        this.Itemtype = Itemtype;
        this.Name = Name;
    }
    
    public Item(String Itemtype, String Name, Integer Id) {
        this.Itemtype = Itemtype;
        this.Name = Name;
        this.Id = Id;
    }
    
    private String Itemtype;
    private String Name;
    private Integer Id;

    public String getItemtype() {
        return Itemtype;
    }

    public void setItemtype(String Itemtype) {
        this.Itemtype = Itemtype;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }
    
}
