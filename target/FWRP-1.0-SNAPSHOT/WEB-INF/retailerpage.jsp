<%-- 
    Document   : retailerpage
    Created on : Mar. 29, 2024, 10:22:18 p.m.
    Author     : shilp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang = "en">
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="About me">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Retailer Page</title>
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <header>
            <h1 id="title">Food Waste Reduction Platform</h1>
        </header>
        <nav>
            <ul id="navbar">
              <li class="navitem"><a href="./index.html" >Home</a></li>
              <li class="navitem"><a class="active">Retailer</a></li>
              <li class="navitem"><a href="./consumerlogin.html">Consumer</a></li>
              <li class="navitem"><a href="./charitylogin.html">Charitable Organization</a></li>
            </ul>    
          </nav>
        <h2>Welcome Retailer!</h2><br>
        <div class=buttons>
            <button class="item">Insert Item</button>
            <button class="item">Update Item</button>
            <button class="item">Delete Item</button>
        </div>
        <footer>
            <p>&copy; Final Project, 2024</p>
        </footer>
    </body>
</html>
