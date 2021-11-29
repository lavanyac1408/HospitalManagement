package com.jnit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DoctorRegister extends HttpServlet {
	Connection con = null;
	PreparedStatement ps = null;

	public void init(ServletConfig config) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "jdbc:mysql://localhost:3306/hospital";
		String username = "root";
		String password = "root";
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int doctor_id = Integer.parseInt(request.getParameter("doctor_id"));
		String doctor_name = request.getParameter("doctor_name");
		String doctor_specialisation = request.getParameter("doctor_specialisation");
		int doctor_phone = Integer.parseInt(request.getParameter("doctor_phone"));
		String doctor_location = request.getParameter("doctor_location");
		String doctor_password = request.getParameter("doctor_password");

		PrintWriter pw = response.getWriter();
		try {
			ps = con.prepareStatement("insert into doctor values(?,?,?,?,?,?)");
			ps.setInt(1, doctor_id);
			ps.setString(2, doctor_name);
			ps.setString(3, doctor_specialisation);
			ps.setInt(4, doctor_phone);
			ps.setString(5, doctor_location);
			ps.setString(6, doctor_password);
			int x = ps.executeUpdate();
			if (x != 0)
			pw.println("<html><body bgcolor='wheat'> <h1> Registered Successful </h1></body></html>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}