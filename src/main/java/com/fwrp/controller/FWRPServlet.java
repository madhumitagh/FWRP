/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.fwrp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fwrp.model.factory.*;
import com.fwrp.model.*;
import com.fwrp.dao.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Madhumita, Piyalee, Pooja, Shilpi
 */
public class FWRPServlet extends HttpServlet {
    private static String servDir = null;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String messageType = request.getMethod();
        String uri = request.getRequestURI();
        if (FWRPServlet.servDir == null) {
            FWRPServlet.servDir = getServletContext().getRealPath("/");
            DaoGlobals.setServDir(servDir);
        }
        
        // Retailer Invocations
        if (uri.equals("/FWRP/JSP/retailerlogin")) {
            if (messageType.equals("GET")) {
                request.getRequestDispatcher("/WEB-INF/retailerlogin.jsp").forward(request, response);
            } else {
                String username = request.getParameter("usernamelogin");
                String password = request.getParameter("passwordlogin");
                Entity retail = RetailerFactory.create(username, password, "dummy");
                if (RetailerDaoImpl.getInstance().authenticate(retail) != null) {
                    request.setAttribute("ret_login_val", true);
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        session.invalidate();
                    }
                    session = request.getSession(true);
                    session.setAttribute("retail", retail);
                    response.sendRedirect("/FWRP/JSP/retailerpage");
                } else {
                    request.setAttribute("ret_login_val", false);
                    request.getRequestDispatcher("/WEB-INF/retailerlogin.jsp").forward(request, response);
                }
            }
            
        } else if (uri.equals("/FWRP/JSP/retailerregister")) {
                //Register
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String name = request.getParameter("name");
                Retailer retail = RetailerFactory.create(username, password, name);
                
                if (!RetailerDaoImpl.getInstance().check(username)) {
                    RetailerDaoImpl.getInstance().enList(retail);
                    request.setAttribute("ret_reg_val", true);
                    request.getRequestDispatcher("/WEB-INF/retailerlogin.jsp").forward(request, response);                    
                }  else {
                    request.setAttribute("ret_reg_val", false);
                    request.getRequestDispatcher("/WEB-INF/retailerlogin.jsp").forward(request, response);
                }
                
        } else if (uri.equals("/FWRP/JSP/retailerpage")) {
            if (request.getSession().getAttribute("retail") != null) {
                Entity ent = (Entity)request.getSession().getAttribute("retail");
                ArrayList itemsList = StockDaoImpl.getInstance().getAll(ent.getId());
                request.setAttribute("item_list", itemsList);
                request.getRequestDispatcher("/WEB-INF/retailerpage.jsp").forward(request, response);
            } else {
                request.setAttribute("ret_login_val", false);
                request.getRequestDispatcher("/WEB-INF/retailerlogin.jsp").forward(request, response);
            }
        } else if (uri.equals("/FWRP/JSP/retailerinsert")) {
            if (request.getSession().getAttribute("retail") != null) {
                if (messageType.equals("GET")) {
                    request.getRequestDispatcher("/WEB-INF/retailerinsert.jsp").forward(request, response);
                } else {
                    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                    Entity ent = (Entity)request.getSession().getAttribute("retail");
                    String itemType = request.getParameter("item_type");
                    String itemName = request.getParameter("item_name");
                    try {
                        Date expDate = fmt.parse(request.getParameter("exp_date"));
                        Double price = Double.valueOf(request.getParameter("price"));
                        int quantity = Integer.parseInt(request.getParameter("quantity"));
                        boolean surplus = false;
                        if (request.getParameter("surplus") != null) {
                            surplus = request.getParameter("surplus").equals("on");
                        }
                        Item item = ItemDaoImpl.getInstance().check(itemType, itemName);
                        if (item == null) {
                            item = ItemFactory.create(itemType, itemName);
                            ItemDaoImpl.getInstance().insert(item);
                        }
                        Stock stock = StockFactory.create(item.getId(), ent.getId(),
                                                          expDate, price, quantity,
                                                          surplus);
                        StockDaoImpl.getInstance().insert(stock);
                        ArrayList itemsList = StockDaoImpl.getInstance().getAll(ent.getId());
                        request.setAttribute("item_list", itemsList);
                        response.sendRedirect("/FWRP/JSP/retailerpage");
                    } catch (ParseException e) {
                        System.out.println(e.getMessage());
                        response.sendRedirect("/FWRP/JSP/retailerpage");
                    }
                }
            } else { 
                request.setAttribute("ret_login_val", false);
                request.getRequestDispatcher("/WEB-INF/retailerlogin.jsp").forward(request, response);
            }
        } else if (uri.equals("/FWRP/JSP/retailerupd")) {
             if (request.getSession().getAttribute("retail") != null) {
                Stock stock;
                Entity ent = (Entity)request.getSession().getAttribute("retail");
                 if (messageType.equals("GET")) {
                    String upd = request.getParameter("stockupd");
                    String del = request.getParameter("stockdel");
                    if (upd != null) {
                        try {
                            //Update
                            String tok[] = upd.split("_");
                            int itemId = Integer.parseInt(tok[0]);
                            SimpleDateFormat fmt = new SimpleDateFormat("MMM dd,yyyy");
                            Date dt = fmt.parse(tok[1]);
                            stock = StockFactory.create(itemId, ent.getId(), dt);
                            stock = StockDaoImpl.getInstance().get(stock);
                            request.setAttribute("stock", stock);
                            request.setAttribute("item", ItemDaoImpl.getInstance().get(stock.getItemId()));
                            request.getRequestDispatcher("/WEB-INF/retailerupdate.jsp").forward(request, response);
                        } catch (ParseException ex) {
                            Logger.getLogger(FWRPServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (del != null) {
                        //Delete
                        try {
                            //Update
                            String tok[] = del.split("_");
                            int itemId = Integer.parseInt(tok[0]);
                            SimpleDateFormat fmt = new SimpleDateFormat("MMM dd,yyyy");
                            Date dt = fmt.parse(tok[1]);
                            stock = StockFactory.create(itemId, ent.getId(), dt);
                            StockDaoImpl.getInstance().delete(stock);
                            //request.setAttribute("ret_del", ret);
                            ArrayList itemsList = StockDaoImpl.getInstance().getAll(ent.getId());
                            request.setAttribute("item_list", itemsList);
                            response.sendRedirect("/FWRP/JSP/retailerpage");
                        } catch (ParseException ex) {
                            Logger.getLogger(FWRPServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                    String itemType = request.getParameter("item_type");
                    String itemName = request.getParameter("item_name");
                    try {
                        Date expDate = fmt.parse(request.getParameter("exp_date"));
                        Item item = ItemDaoImpl.getInstance().check(itemType, itemName);
                        if (item != null) {
                            stock = StockFactory.create(item.getId(), ent.getId(), expDate);    
                            stock.setDiscountedPrice(Double.valueOf(request.getParameter("price")));
                            stock.setQuantity(Integer.parseInt(request.getParameter("quantity")));
                            if (request.getParameter("surplus") != null) {
                                stock.setSurplus(request.getParameter("surplus").equals("on"));    
        
                            } else {
                                stock.setSurplus(false);
                            }
                            StockDaoImpl.getInstance().update(stock);
                            ArrayList itemsList = StockDaoImpl.getInstance().getAll(ent.getId());
                            request.setAttribute("item_list", itemsList);
                            response.sendRedirect("/FWRP/JSP/retailerpage");
                        }
                    } catch (ParseException e) {
                        System.out.println(e.getMessage());
                        ArrayList itemsList = StockDaoImpl.getInstance().getAll(ent.getId());
                        request.setAttribute("item_list", itemsList);
                        response.sendRedirect("/FWRP/JSP/retailerpage");
                    }
                 }                
            } else { 
                request.setAttribute("ret_login_val", false);
                request.getRequestDispatcher("/WEB-INF/retailerlogin.jsp").forward(request, response);
            }
            
            
        // Charity Invocations
        } else if (uri.equals("/FWRP/JSP/charitylogin")) {
            if (messageType.equals("GET")) {
                request.getRequestDispatcher("/WEB-INF/charitylogin.jsp").forward(request, response);
            } else {
                String username = request.getParameter("usernamelogin");
                String password = request.getParameter("passwordlogin");
                Entity charity = CharityFactory.getInstance().getConsumer(username, password, "");
                if (CharityDaoImpl.getInstance().authenticate(charity) != null) {
                    request.setAttribute("cha_login_val", true);
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        session.invalidate();
                    }
                    session = request.getSession(true);
                    session.setAttribute("charity", charity);
                    response.sendRedirect("/FWRP/JSP/charitypage");
                } else {
                    request.setAttribute("cha_login_val", false);
                    request.getRequestDispatcher("/WEB-INF/charitylogin.jsp").forward(request, response);
                }
            }
                        
        } else if (uri.equals("/FWRP/JSP/charityregister")) {
                //Register
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String name = request.getParameter("name");
                Entity charity = CharityFactory.getInstance().getConsumer(username,password,name);
                
                if (!CharityDaoImpl.getInstance().check(username)) {
                    CharityDaoImpl.getInstance().enList(charity);
                    request.setAttribute("cha_reg_val", true);
                    request.getRequestDispatcher("/WEB-INF/charitylogin.jsp").forward(request, response);                    
                }  else {
                    request.setAttribute("cha_reg_val", false);
                    request.getRequestDispatcher("/WEB-INF/charitylogin.jsp").forward(request, response);
                }
        } else if (uri.equals("/FWRP/JSP/charitypage")) {
            if (request.getSession().getAttribute("charity") != null) {
                Entity ent = (Entity)request.getSession().getAttribute("charity");
                ArrayList itemsList = StockDaoImpl.getInstance().getAll(ent.getId());
                request.setAttribute("item_list", itemsList);
                request.getRequestDispatcher("/WEB-INF/charitypage.jsp").forward(request, response);
            } else {
                request.setAttribute("cha_login_val", false);
                request.getRequestDispatcher("/WEB-INF/charitylogin.jsp").forward(request, response);
            }
            
        } else if (uri.equals("/FWRP/JSP/charityinsert")) {
            if (request.getSession().getAttribute("charity") != null) {
                Entity ent = (Entity)request.getSession().getAttribute("charity");
                //Test Data
                Stock stock = new Stock(1, ent.getId(), new Date(), 50.88, 50, true);
                StockDaoImpl.getInstance().insert(stock);
                ArrayList itemsList = StockDaoImpl.getInstance().getAll(ent.getId());
                request.setAttribute("item_list", itemsList);
                request.getRequestDispatcher("/WEB-INF/charitypage.jsp").forward(request, response);
            } else {
                request.setAttribute("cha_login_val", false);
                request.getRequestDispatcher("/WEB-INF/charitylogin.jsp").forward(request, response);
            }
            
            
            
         // Individual Invocations
        } else if (uri.equals("/FWRP/JSP/consumerlogin")) {       
        
            if (messageType.equals("GET")) {
                request.getRequestDispatcher("/WEB-INF/consumerlogin.jsp").forward(request, response);
            } else {
                String username = request.getParameter("usernamelogin");
                String password = request.getParameter("passwordlogin");
                Entity individual = IndividualFactory.getInstance().getConsumer(username, password, "dummy");
                if (IndividualDaoImpl.getInstance().authenticate(individual) != null) {
                    request.setAttribute("ret_login_val", true);
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        session.invalidate();
                    }
                    session = request.getSession(true);
                    session.setAttribute("individual", individual);
                    response.sendRedirect("/FWRP/JSP/consumerpage");
                } else {
                    request.setAttribute("ret_login_val", false);
                    request.getRequestDispatcher("/WEB-INF/consumerlogin.jsp").forward(request, response);
                }
            }
            
        } else if (uri.equals("/FWRP/JSP/consumerregister")) {
                //Register
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String name = request.getParameter("firstname") + " " + request.getParameter("lastname");
                Entity individual = IndividualFactory.getInstance().getConsumer(username, password, name);
                
                if (!IndividualDaoImpl.getInstance().check(username)) {
                    IndividualDaoImpl.getInstance().enList(individual);
                    request.setAttribute("ret_reg_val", true);
                    request.getRequestDispatcher("/WEB-INF/consumerlogin.jsp").forward(request, response);                    
                }  else {
                    request.setAttribute("ret_reg_val", false);
                    request.getRequestDispatcher("/WEB-INF/consumerlogin.jsp").forward(request, response);
                }
                
        } else if (uri.equals("/FWRP/JSP/consumerpage")) {
            if (request.getSession().getAttribute("individual") != null) {
                //Entity ent = (Entity)request.getSession().getAttribute("individual");
                ArrayList itemsList = StockDaoImpl.getInstance().getAll();
                request.setAttribute("item_list", itemsList);
                request.getRequestDispatcher("/WEB-INF/consumerpage.jsp").forward(request, response);
            } else {
                request.setAttribute("ret_login_val", false);
                request.getRequestDispatcher("/WEB-INF/consumerlogin.jsp").forward(request, response);
            }
            
        } else if (uri.equals("/FWRP/JSP/consumerpurchase")) {
            if (request.getSession().getAttribute("individual") != null) {
                Entity ent = (Entity)request.getSession().getAttribute("individual");
                //Test Data
                Stock stock = new Stock(1, ent.getId(), new Date(), 50.88, 50, true);
                StockDaoImpl.getInstance().insert(stock);
                ArrayList itemsList = StockDaoImpl.getInstance().getAll(ent.getId());
                request.setAttribute("item_list", itemsList);
                request.getRequestDispatcher("/WEB-INF/consumerpage.jsp").forward(request, response);
            } else {
                request.setAttribute("ret_login_val", false);
                request.getRequestDispatcher("/WEB-INF/consumerlogin.jsp").forward(request, response);
            }
            request.getRequestDispatcher("/WEB-INF/consumerlogin.jsp").forward(request, response);

        } else if (uri.equals("/FWRP/JSP/consumptionpage")) {
            request.getRequestDispatcher("/WEB-INF/consumptionpage.jsp").forward(request, response);
            
        } else if (uri.equals("/FWRP/JSP/purchasepage")) {
            if (request.getSession().getAttribute("charity") != null) {
                // Charity
                Stock stock;
                Entity ent = (Entity)request.getSession().getAttribute("charity");
                 if (messageType.equals("GET")) {
                    String upd = request.getParameter("stockupd");
                  
                    if (upd != null) {
                        try {
                            //Update
                            String tok[] = upd.split("_");
                            int itemId = Integer.parseInt(tok[0]);
                            int retailerId = Integer.parseInt(tok[1]);
                            SimpleDateFormat fmt = new SimpleDateFormat("MMM dd,yyyy");
                            Date dt = fmt.parse(tok[2]);
                            stock = StockFactory.create(itemId, retailerId, dt);
                            stock = StockDaoImpl.getInstance().get(stock);
                            request.setAttribute("stock", stock);
                            request.setAttribute("item", ItemDaoImpl.getInstance().get(stock.getItemId()));
                            request.getRequestDispatcher("/WEB-INF/purchasepage.jsp").forward(request, response);
                        } catch (ParseException ex) {
                            Logger.getLogger(FWRPServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } 
                } else {
                    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                    String itemType = request.getParameter("itemType");
                    String itemName = request.getParameter("itemName");

                    try {
                        int retailerId = Integer.parseInt(request.getParameter("retailer"));
                        Date expDate = fmt.parse(request.getParameter("expirationDate"));
                        Item item = ItemDaoImpl.getInstance().check(itemType, itemName);
                        if (item != null) {
                            stock = StockFactory.create(item.getId(), retailerId, expDate); 
                            stock.setQuantity(Integer.parseInt(request.getParameter("quantity")));
                            Stock  presentStock = StockDaoImpl.getInstance().get(stock);
                           
                           if (presentStock != null){
                               int remainingQuantity = presentStock.getQuantity() - stock.getQuantity();
                               Consumption entry = new Consumption(presentStock.getItemId(), presentStock.getRetailerId(), ent.getId(),ent.getType(), stock.getQuantity(), new Date (), presentStock.getExpiryDate(), presentStock.getDiscountedPrice());
                               ConsumerDaoImpl.getInstance().insert(entry);
                               if (remainingQuantity <= 0) {
                                   StockDaoImpl.getInstance().delete(presentStock);
                               } else {
                                   stock.setQuantity(remainingQuantity);
                                   StockDaoImpl.getInstance().update(stock);
                               }
                           }
                        }
                        ArrayList itemsList = StockDaoImpl.getInstance().getAll();
                        request.setAttribute("item_list", itemsList);
                        response.sendRedirect("/FWRP/JSP/charitypage");                        
                    } catch (ParseException e) {
                        System.out.println(e.getMessage());
                        ArrayList itemsList = StockDaoImpl.getInstance().getAll();
                        request.setAttribute("item_list", itemsList);
                        response.sendRedirect("/FWRP/JSP/charitypage");
                    }
                 }       
            } else if (request.getSession().getAttribute("individual") != null) {
                // Consumer/Individual
                Stock stock;
                Entity ent = (Entity)request.getSession().getAttribute("individual");
                 if (messageType.equals("GET")) {
                    String upd = request.getParameter("stockupd");
                    if (upd != null) {
                        try {
                            //Update
                            
                            String tok[] = upd.split("_");
                            int itemId = Integer.parseInt(tok[0]);
                            SimpleDateFormat fmt = new SimpleDateFormat("MMM dd,yyyy");
                            int retailerId = Integer.parseInt(tok[1]);
                            Date dt = fmt.parse(tok[2]);
                            stock = StockFactory.create(itemId,retailerId , dt);
                            stock = StockDaoImpl.getInstance().get(stock);
                            request.setAttribute("stock", stock);
                            request.setAttribute("item", ItemDaoImpl.getInstance().get(stock.getItemId()));
                            request.getRequestDispatcher("/WEB-INF/purchasepage.jsp").forward(request, response);
                        } catch (ParseException ex) {
                            Logger.getLogger(FWRPServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } 
                } else {
                    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                    String itemType = request.getParameter("itemType");
                    String itemName = request.getParameter("itemName");
                    try {
                        int retailerId = Integer.parseInt(request.getParameter("retailer"));
                        Date expDate = fmt.parse(request.getParameter("expirationDate"));
                        Item item = ItemDaoImpl.getInstance().check(itemType, itemName);
                        if (item != null) {
                            stock = StockFactory.create(item.getId(), retailerId, expDate);
                            stock.setQuantity(Integer.parseInt(request.getParameter("quantity")));
                            
                            Stock presentStock = StockDaoImpl.getInstance().get(stock);
                            if (presentStock != null) {
                                int remainingQuantity = presentStock.getQuantity() - stock.getQuantity();
                                Consumption entry = new Consumption(presentStock.getItemId(), presentStock.getRetailerId(),
                                                                    ent.getId(), ent.getType(), stock.getQuantity(),
                                                                    new Date(), presentStock.getExpiryDate(),
                                                                    presentStock.getDiscountedPrice());
                                ConsumerDaoImpl.getInstance().insert(entry);
                                if (remainingQuantity <= 0) {
                                    StockDaoImpl.getInstance().delete(presentStock);
                                } else {
                                    stock.setQuantity(remainingQuantity);
                                    stock.setDiscountedPrice(presentStock.getDiscountedPrice());
                                    StockDaoImpl.getInstance().update(stock);
                                }
                            }
                            ArrayList itemsList = StockDaoImpl.getInstance().getAll();
                            request.setAttribute("item_list", itemsList);
                            response.sendRedirect("/FWRP/JSP/consumerpage");
                        }
                    } catch (ParseException e) {
                        System.out.println(e.getMessage());
                        ArrayList itemsList = StockDaoImpl.getInstance().getAll();
                        request.setAttribute("item_list", itemsList);
                        response.sendRedirect("/FWRP/JSP/consumerpage");
                    }
                 }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

   }

