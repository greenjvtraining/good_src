package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dto.BoardDto;

public class BoardDao {
	
	private Connection conn = DBcon.getConnection();
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public List<BoardDto> getList(){
		List<BoardDto> boardList = new ArrayList<>();
		
		String query = "SELECT * FROM tbl_board";
		try {
			stmt = conn.prepareStatement(query);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Integer bno = rs.getInt("bno");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer = rs.getString("writer");
				String filename = rs.getString("filename");
				Timestamp regdate = rs.getTimestamp("regdate");
				Timestamp modifydate = rs.getTimestamp("modifydate");
				
				BoardDto board = new BoardDto(bno, title, content, writer, filename, regdate, modifydate);
				boardList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resourceClose(rs, stmt);
		}
		
		return boardList;
	}
	
	
	
	
	private void resourceClose(PreparedStatement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	private void resourceClose(ResultSet rs, PreparedStatement stmt) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		resourceClose(stmt);
		
	}
}
