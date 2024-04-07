<%-- 
    Document   : consumptionpage
    Created on : Apr. 6, 2024, 9:25:35 p.m.
    Author     : Madhumita, Piyalee, Pooja, Shilpi 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <html lang = "en">
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Consumed Items Entry">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Food Waste Reduction Platform - Consumed Items Entry</title>
        <link rel="stylesheet" href="../style.css">
    </head>
    <body>
        <header>
            <h1 id="title">Food Waste Reduction Platform</h1>
        </header>
        <nav>
            <ul id="navbar">
                <li class="navitem"><a href="./index.html">Home</a></li>
                <li class="navitem"><a href="./JSP/retailerlogin">Retailer</a></li>
                <li class="navitem"><a href="./JSP/consumerlogin">Consumer</a></li>
                <li class="navitem"><a href="./JSP/charitylogin">Charitable Organization</a></li>
                <li class="navitem"><a class="active">Consumption</a></li>
            </ul>    
        </nav>
            
        <main>
            <h2>Consumed Items Entry</h2>
             <form method="post" name="PurchaseForm" action="/FWRP/JSP/consumptionpage">
            <label for="expirationDate">Expiration Date:</label>
            <input type="date" name="expirationDate" readonly value="YYYY-MM-DD"><br><br>
            
            <label for="consumerType">Consumer Type:</label>
            <input type="text" name="consumerType" value="Type"><br><br>
            
            <label for="quantity">Quantity:</label>
            <input type="number" name="quantity" min="1"><br><br>
            
            <label for="datePurchased">Date Purchased:</label>
            <input type="date" name="datePurchased"
                   
            <label for="price purchased">Price Purchased:</label>
            <input typr="number" name="PricePurchased"

            <input type="submit" value="Submit">
        </form>
    </main>
    <footer>
        <p>&copy; Final Project, 2024</p>
    </footer>
</body>
</html>