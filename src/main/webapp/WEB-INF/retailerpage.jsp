<%-- 
    Document   : retailerpage
    Created on : Mar. 29, 2024, 10:22:18 p.m.
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
        <title>Retailer Page</title>
        <link rel="stylesheet" href="../style.css">
    </head>
    <body>
        <header>
            <h1 id="title">Food Waste Reduction Platform</h1>
        </header>
        <nav>
            <ul id="navbar">
              <li class="navitem"><a href="/FWRP/" >Home</a></li>
              <li class="navitem"><a class="active">Retailer</a></li>
              <li class="navitem"><a href="./JSP/consumerlogin">Consumer</a></li>
              <li class="navitem"><a href="./JSP/charitylogin">Charitable Organization</a></li>
              <li class="navitem"><a href="./JSP/consumptionpage">Consumption</a></li>
            </ul>    
          </nav>
        <% Entity en = (Entity)session.getAttribute("retail");%>    
        <h2>Welcome Retailer <%=en.getName()%>! ID:<%=en.getId()%></h2><br>
        <form action="/FWRP/JSP/retailerinsert">
        <div class=buttons>
            <button name="insert" type="submit"class=item>Insert Item</button>
        </div>
        </form>
        
        <form action="/FWRP/JSP/retailerupd">
        <table border="1">
  <tr>
    <th>Item Id</th>
    <th>Expiration</th>
    <th>Quantity</th>
    <th>Price</th>
    <th>Surplus</th>
    <th>Actions</th>
  </tr>
  <% ArrayList<Stock> list = (ArrayList)request.getAttribute("item_list"); 
     for (Stock st: list) { %>
  <tr>
    <td><%=st.getItemId()%></td>
    <td><%=st.getExpiryDateStr()%></td>
    <td><%=st.getQuantity()%></td>
    <td><%=st.getDiscountedPrice()%></td>
    <td><%=st.IsSurplus()%></td>
    <td>
          <div class=buttons>
            <button class=item name="stockupd" value="<%=st.getItemId() + "_" + st.getExpiryDateStr()%>" type="submit">Update Item</button>
            <button class=item name="stockdel" value="<%=st.getItemId() + "_" + st.getExpiryDateStr()%>" type="submit">Delete Item</button>
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
