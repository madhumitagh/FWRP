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
                <li class="navitem"><a href="/FWRP/">Home</a></li>
                <li class="navitem"><a href="./JSP/retailerlogin">Retailer</a></li>
                <li class="navitem"><a href="./JSP/consumerlogin">Consumer</a></li>
                <li class="navitem"><a href="./JSP/charitylogin">Charitable Organization</a></li>
                <li class="navitem"><a class="active">Consumption</a></li>
            </ul>    
        </nav>
            
        <main>
        <h2>Purchase Table Entry</h2>
        <form>
            <table>
                <tr>
                    <th>Item ID</th>
                    <th>Retailer ID</th>
                    <th>Expiration Date</th>
                    <th>Consumer ID</th>
                    <th>Quantity</th>
                    <th>Date Purchased</th>
                    <th>Price Purchased</th>
                </tr>
                <tr>
                    <td><input type="number" name="itemId" required></td>
                    <td><input type="number" name="retailerId" required></td>
                    <td><input type="date" name="expirationDate" required></td>
                    <td><input type="number" name="consumerId" required></td>
                    <td><input type="number" name="quantity" min="1" required></td>
                    <td><input type="date" name="datePurchased" required></td>
                    <td><input type="text" name="pricePurchased" required></td>
                </tr>
            </table>
        </form>
    </main>
    <footer>
        <p>&copy; Final Project, 2024</p>
    </footer>
</body>
</html>