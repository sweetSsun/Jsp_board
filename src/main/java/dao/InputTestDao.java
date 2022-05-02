package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.InputTest;

public class InputTestDao {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection
				("jdbc:oracle:thin:@//localhost:1521/xe","JS_MOVIE","1111"); 
		return con;
	}
	
	public InputTestDao() {
		try {
			con = getConnection();
			System.out.println("DB연결 성공!");
		} catch (Exception e) {
			System.out.println("DB연결 실패...");
			e.printStackTrace();
		}
	}
	
	public int insertTest(String testData01, String testData02) {
		String sql = "INSERT INTO INPUTTEST VALUES(?,?)";
		int insertResult = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, testData01);
			pstmt.setString(2, testData02);
			insertResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insertResult;
	}

	public ArrayList<InputTest> selectTest() {
		String sql = "SELECT * FROM INPUTTEST";
		ArrayList<InputTest> testList = new ArrayList<InputTest>();
		InputTest ipt = null;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ipt = new InputTest();
				ipt.setTestcol1(rs.getString(1));
				ipt.setTestcol2(rs.getString(2));
				testList.add(ipt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return testList;
	}
}
