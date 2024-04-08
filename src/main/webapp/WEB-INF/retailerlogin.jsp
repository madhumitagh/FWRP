<%-- 
    Document   : welcome.jsp
    Created on : Mar. 29, 2024, 6:58:13 p.m.
    Author     : shilpi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang = "en">
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="About me">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Retailer Login</title>
        <link rel="stylesheet" href="../style.css">
    </head>
    <body>
        <header>
            <h1 id="title">Food Waste Reduction Platform</h1>
        </header>
        <nav>
            <ul id="navbar">
            <li class="navitem"><a href="/FWRP/" >Home</a></li>
            <li class="navitem"><a class="active" href="./JSP/retailerlogin">Retailer</a></li>
            <li class="navitem"><a href="./JSP/consumerlogin">Consumer</a></li>
            <li class="navitem"><a href="./JSP/charitylogin">Charitable Organization</a></li>
            <li class="navitem"><a href="./JSP/consumptionpage">Consumption</a></li>
            </ul>     
        </nav>
        <h2>Welcome Retailer!</h2><br>
        <% boolean ret_val = false;
           try {
            ret_val = (boolean)request.getAttribute("ret_reg_val");
           if (ret_val) { %>
        Registration Success
        <% } else { %>
        Username Exists
       <% }
        } catch (Exception e) {} 
        try {
            ret_val = (boolean)request.getAttribute("ret_login_val");
            if (ret_val == false) { %>
                Authentication Error
         <% }
        } catch (Exception e) {} 
       %>        
        <div id="userlogin">
            <div class="userinfo">
                <p class="info"><b>Register as a New User</b></p>
                <form method="post" name="register" action="/FWRP/JSP/retailerregister">
                <label for="retailername">Name of Retailer</label><br>
                <input type="text" name="name"><br> 
                <label for="username">Username</label><br>
                <input type="text" name="username"><br> 
                <label for="password">Password</label><br>
                <input type="text"  name="password"><br>
                <input class="submitregistration" type="submit" value="Register">
                </form>
            </div>
            <div class="userinfo">
                <p class="info"><b>Login as a Returning User</b></p>
                <form method="post" name="login" action="/FWRP/JSP/retailerlogin">
                <label for="username">Username</label><br>
                <input type="text" name="usernamelogin"><br>  
                <label for="password">Password</label><br>
                <input type="text"  name="passwordlogin"><br>
                <a href="./retailerpage.html">
                    <input class="submitlogin" type="submit" value="Login">  
                </a>
                </form>
            </div>
        </div>
        <footer>
            <p>&copy; Final Project, 2024</p>
        </footer>
    </body>
</html>