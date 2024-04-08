<%-- 
    Document   : retailerupdate
    Created on : Apr 7, 2024, 11:44:41 AM
    Author     : madhu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.fwrp.model.Stock,com.fwrp.model.Item" %>
<!DOCTYPE html>
<html>
   <head>
        <meta charset="UTF-8">
        <meta name="description" content="Retailer Update Item">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Charitable Organization Login</title>
        <link rel="stylesheet" href="../style.css">
    </head>
    <body>
        <header>
            <h1 id="title">Food Waste Reduction Platform</h1>
        </header>
        <nav>
        <ul id="navbar">
            <li class="navitem"><a href="/FWRP/" >Home</a></li>
            <li class="navitem"><a href="FWRP/JSP/retailerlogin.html">Retailer</a></li>
            <li class="navitem"><a href="FWRP/JSP/consumerlogin.html">Consumer</a></li>
            <li class="navitem"><a class="active" href="./charitylogin.html">Charitable Organization</a></li>
        </ul>    
        </nav>
        <h2>Welcome Retailer Update Page!</h2><br>
        <% Stock stock = (Stock)request.getAttribute("stock"); %>
        <% Item item = (Item)request.getAttribute("item"); %>
                <form method="post" name="register" action="/FWRP/JSP/retailerupd">
                <label for="item_type">Item Type</label><br>
                <input type="text" name="item_type" readonly="true" value="<%=item.getItemtype()%>"><br> 
                <label for="item_name">Item Name</label><br>
                <input type="text" name="item_name" readonly="true" value="<%=item.getName()%>"><br> 
                <label for="exp_date">Expiration Date</label><br>
                <input type="text"  name="exp_date" readonly="true" value="<%=stock.getExpiryDateWidgetFmt()%>"><br>
                <label for="price">Discounted Price</label><br>
                <input type="text"  name="price" value="<%=stock.getDiscountedPrice()%>"><br>
                <label for="quantity">Quantity</label><br>
                <input type="number"  name="quantity" value="<%=stock.getQuantity()%>"><br>
                <label for="surplus">Surplus</label><br>
                <input type="checkbox"  name="surplus" value="<%=stock.IsSurplus()%>"><br>
                <input type="submit"  name="submit"><br>
                </form>
    </body>
</html>
