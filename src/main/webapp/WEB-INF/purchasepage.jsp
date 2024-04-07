<%-- 
    Document   : purchasepage
    Created on : Apr. 6, 2024, 10:54:31 p.m.
    Author     : Madhumita, Piyalee, Pooja, Shilpi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Unified Purchase and Claim Entry">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Food Waste Reduction Platform - Unified Purchase/Claim Entry</title>
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
            <li class="navitem"><a href="./JSP/consumptionpage">Consumption</a></li>
        </ul>    
    </nav>
    <main>
        <main>
        <h2>Purchase/Claim Entry Form</h2>
        <form id="PurchaseForm">
            <label for="itemType">Item Type:</label>
            <select id="itemType" name="itemType" required>
                <option value="food">Food</option>
                <option value="beverage">Beverage</option>
            </select><br><br>
            
            <label for="itemName">Item Name:</label>
            <input type="text" id="itemName" name="itemName" required><br><br>
            
            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" min="" max="" required><br><b
            
            <input type="submit" value="Submit">
        </form>
    </main>
    <footer>
        <p>&copy; Final Project, 2024</p>
    </footer>
</body>
</html>