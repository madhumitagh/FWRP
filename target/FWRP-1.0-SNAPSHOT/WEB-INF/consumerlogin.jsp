<%-- 
    Document   : customerlogin
    Created on : Mar. 29, 2024, 7:09:09 p.m.
    Author     : shilp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang = "en">
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="About me">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Consumer Login</title>
        <link rel="stylesheet" href="../style.css">
    </head>
    <body>
        <header>
            <h1 id="title">Food Waste Reduction Platform</h1>
        </header>
        <nav>
            <ul id="navbar">
            <li class="navitem"><a href="./index.html" >Home</a></li>
            <li class="navitem"><a href="./retailerlogin.html">Retailer</a></li>
            <li class="navitem"><a class="active" href="./consumerlogin.html">Consumer</a></li>
            <li class="navitem"><a href="./charitylogin.html">Charitable Organization</a></li>
            </ul>    
        </nav>
        <h2>Welcome Consumer!</h2><br>
        <div id="userlogin">
            <div class="userinfo">
                <p class="info"><b>Register as a New User</b></p>
                <label for="firstname">First Name</label><br>
                <input type="text" name="firstname"><br>  
                <label for="lastname">Last Name</label><br>
                <input type="text"  name="lastName"><br>
                <label for="username">Username</label><br>
                <input type="text" name="username"><br>  
                <label for="password">Password</label><br>
                <input type="text"  name="password"><br>
                <a href="./consumerpage.html">
                    <input class="submit" type="submit" value="Register">  
                </a> 
            </div>
            <div class="userinfo">
                <p class="info"><b>Login as a Returning User</b></p>
                <label for="username">Username</label><br>
                <input type="text" name="username"><br>  
                <label for="password">Password</label><br>
                <input type="text"  name="password"><br>
                <a href="./consumerpage.html">
                    <input class="submit" type="submit" value="Login">  
                </a>
            </div>
        </div>
        <footer>
            <p>&copy; Final Project, 2024</p>
        </footer>
    </body>
</html>