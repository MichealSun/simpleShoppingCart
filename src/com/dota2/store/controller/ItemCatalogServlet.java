package com.dota2.store.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dota2.store.beans.SetDetails;
import com.dota2.store.cart.ShoppingCart;
import com.dota2.store.model.StoreController;


public class ItemCatalogServlet extends HttpServlet {
	private static final long	serialVersionUID	= 1L;
	HttpSession					hs;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		hs = request.getSession();
		ShoppingCart cart = (ShoppingCart) hs.getAttribute("cart");

		if (cart == null) {
			cart = new ShoppingCart();
			hs.setAttribute("cart", cart);
		}

		String servletName = request.getServletPath();

		if (servletName.equals("/itemcatalog")) {

			String itemID = request.getParameter("Add");

			SetDetails sets = null;
			try {				
				sets = StoreController.getSetDetailsByID(itemID);

				if (!(itemID == null)) {					
					cart.add(itemID, sets);
				}

				RequestDispatcher rd = getServletContext().getRequestDispatcher("/store.jsp?page=setDetails");
				rd.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		if (servletName.equals("/wishlist")) {
			int userid = (Integer) hs.getAttribute("id");
			//String userID = request.getParameter("userID");
			String setID = request.getParameter("setID");
			try {
				StoreController.setWishByID(userid, setID);
				request.setAttribute("userid", userid);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/store.jsp?page=setDetails");
				rd.forward(request, response);
			}catch (SQLException e) {
				e.printStackTrace();
			} 
			
			
		}

		if (servletName.equals("/showcart")) {

			String removeParam = request.getParameter("Remove");

			if (removeParam != null) {
				cart.remove(removeParam);
			}

			RequestDispatcher rd = getServletContext().getRequestDispatcher("/cartStore.jsp?page=showcart");
			rd.forward(request, response);
		}
		if (servletName.equals("/showwish")) {
			String setID = request.getParameter("Remove");
			int userID = (Integer) hs.getAttribute("id");
			if (setID != null) {
				try {
					StoreController.deleteWishlist(userID, setID);
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}

			
			//System.out.println("userid" + userID);
			try {
				List<SetDetails> list = StoreController.getWishList(userID);
				request.setAttribute("list", list);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			

			
			String setid = request.getParameter("Add");
			//System.out.println("ItemCatalogServlet setid " + setid);

			SetDetails sets = null;
			try {				
				sets = StoreController.getSetDetailsByID(setid);
				//System.out.println("ItemCatalogServlet sets " + sets.toString());

				if (!(setid == null)) {					
					cart.add(setid, sets);
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/wish.jsp");
			rd.forward(request, response);
		}

	}

}
