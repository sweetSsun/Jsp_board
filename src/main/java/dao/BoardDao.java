package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Board;

public class BoardDao {
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection
				("jdbc:oracle:thin:@//localhost:1521/xe","SERVLET","1111"); 
		return con;
	}
	
	public BoardDao() {
		try {
			con = getConnection();
			System.out.println("DB연결 성공!");
		} catch (Exception e) {
			System.out.println("DB연결 실패...");
			e.printStackTrace();
		}
	}

	// 글번호 최대값 조회
	public int getMaxBno() {
		String sql = "SELECT NVL(MAX(BNO), 0) FROM BOARD";
		int maxBno = 0;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				maxBno = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maxBno;
	}
	
	public int insertBoard(Board boardInfo) {
		String sql = "INSERT INTO BOARD VALUES (?,?,?,?,SYSDATE)";
		int insertResult = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardInfo.getBno());
			pstmt.setString(2, boardInfo.getBwriter());
			pstmt.setString(3, boardInfo.getBtitle());
			pstmt.setString(4, boardInfo.getBcontents());
			insertResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return insertResult;
	}

	public ArrayList<Board> selectBoardList() {
		String sql = "SELECT * FROM BOARD";
		ArrayList<Board> bdList = new ArrayList<Board>();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setBno(rs.getInt(1));
				board.setBwriter(rs.getString(2));
				board.setBtitle(rs.getString(3));
				board.setBcontents(rs.getString(4));
				board.setBdate(rs.getString(5));
				bdList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bdList;
	}

	public Board selectBoardInfo(int bno) {
		String sql = "SELECT * FROM BOARD WHERE BNO=?";
		Board board = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board = new Board();
				board.setBno(rs.getInt(1));
				board.setBwriter(rs.getString(2));
				board.setBtitle(rs.getString(3));
				board.setBcontents(rs.getString(4));
				board.setBdate(rs.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return board;
	}

	public int deleteBoard(int delBno) {
		String sql = "DELETE FROM BOARD WHERE BNO=?";
		int delResult = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, delBno);
			delResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return delResult;
	}

	public int boardModify(Board board) {
		String sql = "UPDATE BOARD SET BTITLE=?, BCONTENTS=? WHERE BNO=?";
		int updateResult = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getBtitle());
			pstmt.setString(2, board.getBcontents());
			pstmt.setInt(3, board.getBno());
			updateResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updateResult;
	}

	public ArrayList<Board> searchBoard(String searchText, String searchType) {
		String sql = "SELECT * FROM BOARD WHERE " + searchType + " LIKE '%'||?||'%'"
					+ "ORDER BY BNO";
		ArrayList<Board> searchList = new ArrayList<Board>();
		try {
			pstmt = con.prepareStatement(sql);
//			String text = "%" + searchText + "%";
			pstmt.setString(1, searchText);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setBno(rs.getInt(1));
				board.setBwriter(rs.getString(2));
				board.setBtitle(rs.getString(3));
				board.setBcontents(rs.getString(4));
				board.setBdate(rs.getString(5));
				searchList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return searchList;
	}

}
