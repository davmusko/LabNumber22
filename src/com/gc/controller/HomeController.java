package com.gc.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
/*
 * author: Antonella Solomon
 *
 */

@Controller
public class HomeController {

	@RequestMapping("/welcome")
	public ModelAndView helloWorld() {


		return new ModelAndView("welcome", "message", "");
	
	}
	private Connection getDBConntion() throws ClassNotFoundException, SQLException {
		// prep for step # 3
		String url = "jdbc:mysql://localhost:3306/coffeeshopdb";
		String userName = "root";
		String password = "Grandcircus2018";

		// Step #2: Load and Register Driver
		Class.forName("com.mysql.jdbc.Driver");

		// Step #3: Create Connection
		Connection con = DriverManager.getConnection(url, userName, password);
		return con;
	}

	@RequestMapping("register")
	public ModelAndView registerUser(@RequestParam("fName") String fName,
									@RequestParam("lName") String lName,
									@RequestParam("uName") String uName,
									@RequestParam("eMail") String eMail,
									@RequestParam("password") String password) throws ClassNotFoundException, SQLException {
		
		Connection con = getDBConntion();
		String sql = "insert into users(UserName, Password, Email, FirstName, LastName)" + "values(?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, uName);
		ps.setString(2, password);
		ps.setString(3, eMail);
		ps.setString(4, fName);
		ps.setString(5, lName);
		ps.executeUpdate();
		con.close();
		return new ModelAndView("updatesuccess","success","User Added");
	}
	@RequestMapping("/index")
	public ModelAndView menu() throws ClassNotFoundException, SQLException {
		Connection con = getDBConntion();
		String query = "select * from items";
		PreparedStatement st = con.prepareStatement(query);
		
		ResultSet rs = st.executeQuery();
		
		ArrayList<String> list = new ArrayList<String>();
		
		while(rs.next()) {
			String name = rs.getString(1);
			String description = rs.getString(2);
			String price = rs.getString(3);
			list.add(name + " " + description + " " + price);
		}
		return new ModelAndView("index","message",list);





	}


}
