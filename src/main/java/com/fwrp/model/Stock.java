/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fwrp.model;
import com.fwrp.dao.ItemDaoImpl;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Madhumita, Piyalee, Pooja, Shilpi
 */
public class Stock {
    private Integer itemId;
    private Integer retailerId;
    private Date expiryDate;
    private double discountedPrice;
    private Integer quantity;
    private boolean surplus;
    
    private Stock() {}
    
    public Stock(int itemId, int retailerId, Date expiryDate, double discountedPrice, Integer quantity, boolean surplus) {
        this.itemId = itemId;
        this.retailerId = retailerId;
        this.expiryDate = expiryDate;
        this.discountedPrice = discountedPrice;
        this.quantity = quantity;
        this.surplus = surplus;
    }

    public Stock(int itemId, int retailerId, Date expiryDate) {
        this.itemId = itemId;
        this.retailerId = retailerId;
        this.expiryDate = expiryDate;
    }

    public boolean IsSurplus() {
        return surplus;
    }

    public void setSurplus(boolean surplus) {
        this.surplus = surplus;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Integer getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(int retailerId) {
        this.retailerId = retailerId;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }
    
    public String getExpiryDateStr() {
        SimpleDateFormat fmt = new SimpleDateFormat("MMM dd, yyyy");
        return fmt.format(expiryDate);
    }

    public String getExpiryDateWidgetFmt() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        return fmt.format(expiryDate);
    }


    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public String getItemNameStr() {
        Item item = ItemDaoImpl.getInstance().get(this.itemId);
        if (item != null) {
            return item.toString();
        }
        return null;        
    }
}
