package com.dota2.store.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dota2.store.beans.User;

import com.dota2.store.model.DBConnection;
import com.dota2.store.util.GenerateUUID;

/**
 * Servlet implementation class RegisterServlet
 */
@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {

	private Connection	conn;
	String				forwardUrl;
	User				user	= null;

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean flag = registerUser(request);
		//System.out.println(flag);

		if (flag == false) {
			
			long uid = GenerateUUID.generateUUID();
			insertActivationDetails(uid);
			forwardUrl = "/userRegister.jsp";

		} else {
			forwardUrl = "/userLogin.jsp";
		}

		RequestDispatcher rd = getServletContext().getRequestDispatcher(forwardUrl);
		rd.forward(request, response);

	}


	private boolean insertActivationDetails(long uid) {
		return true;
	}


	private boolean registerUser(HttpServletRequest request) {
		try {
			conn = DBConnection.connect();
			String insertStatement = "insert into userdetails (UserName,Password,Email,PhoneNumber) values (?,?,?,?)";
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			//System.out.println(username);
			PreparedStatement pst = conn.prepareStatement(insertStatement);
			pst.setString(1, username);
			pst.setString(2, password);
			pst.setString(3, email);
			pst.setString(4, phone);

			int result = pst.executeUpdate();

			if (result > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
