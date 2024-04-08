<%-- 
    Document   : consumerpage
    Created on : Mar. 29, 2024, 10:23:32 p.m.
    Author     : shilp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.fwrp.model.Entity,java.util.ArrayList,com.fwrp.model.Stock" %>

<!DOCTYPE html>
<html lang = "en">
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="About me">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Consumer Page</title>
        <link rel="stylesheet" href="../style.css">
    </head>
    <body>
        <header>
            <h1 id="title">Food Waste Reduction Platform</h1>
        </header>
        <nav>
            <ul id="navbar">
            <li class="navitem"><a href="/FWRP/" >Home</a></li>
            <li class="navitem"><a href="FWRP/JSP/retailerlogin">Retailer</a></li>
            <li class="navitem"><a class="active">Consumer</a></li>
            <li class="navitem"><a href="FWRP/JSP/charitylogin">Charitable Organization</a></li>
            <li class="navitem"><a href="FWRP/JSP/consumptionpage">Consumption</a></li>
            </ul>    
        </nav>
        
         <% Entity en = (Entity)session.getAttribute("individual");%>
        <h2>Welcome Consumer!<%=en.getName()%>! ID:<%=en.getId()%></h2><br>
        <form  action="/FWRP/JSP/purchasepage">
        
        
        <table border="1">
            <tr>
                <th>Item Id</th>
                <th>Retailer Id</th>
                <th>Expiration</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Purchase</th>
            </tr>
                <% ArrayList<Stock> list = (ArrayList)request.getAttribute("item_list"); 
                    for (Stock st: list) { %>
             <tr>
                <td><%=st.getItemId()%></td>
                <td><%=st.getRetailerId()%></td>
                <td><%=st.getExpiryDate()%></td>
                <td><%=st.getQuantity()%></td>
                <td><%=st.getDiscountedPrice()%></td>
                <td>
                    <div class=buttons>
            <button class=item name="stockupd" value="<%=st.getItemId() + "_" + st.getRetailerId()+ "_" + st.getExpiryDateStr()%>" type="submit">Purchase</button>
            
        </div>
                </td>
            </tr>
            <% } %>
        </table>
        </form>
          
        <footer>
            <p>&copy; Final Project, 2024</p>
        </footer>
    </body>
</html>
