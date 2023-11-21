package com.example.dao;

import com.example.bean.BoardVO;
import com.example.util.JDBCUtil2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class BoardDAO {
	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	private final String BOARD_INSERT = "insert into member (userid, uname, password, email, phone_num, photo) values (?,?,?,?,?,?)";
	private final String BOARD_UPDATE = "update member set userid=?, uname=?, password=?, email=?, phone_num=?, photo=? where sid=?";
	private final String BOARD_DELETE = "delete from member  where sid=?";
	private final String BOARD_GET = "select * from member  where sid=?";
	private final String BOARD_LIST = "select * from member order by sid desc";

	public int insertBoard(BoardVO vo) {
		System.out.println("===> JDBC로 insertBoard() 기능 처리");
		try {
			conn = JDBCUtil2.getConnection();
			stmt = conn.prepareStatement(BOARD_INSERT);
			stmt.setString(1, vo.getUserid());
			stmt.setString(2, vo.getUname());
			stmt.setString(3, vo.getPassword());
			stmt.setString(4, vo.getEmail());
			stmt.setString(5, vo.getPhone_num());
			stmt.setString(6, vo.getPhoto());
			stmt.executeUpdate();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// 글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> JDBC로 deleteBoard() 기능 처리");
		try {
			conn = JDBCUtil2.getConnection();
			stmt = conn.prepareStatement(BOARD_DELETE);
			stmt.setInt(1, vo.getSid());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int updateBoard(BoardVO vo) {
		System.out.println("===> JDBC로 updateBoard() 기능 처리");
		try {
			conn = JDBCUtil2.getConnection();
			stmt = conn.prepareStatement(BOARD_UPDATE);
			stmt.setString(1, vo.getUserid());
			stmt.setString(2, vo.getUname());
			stmt.setString(3, vo.getPassword());
			stmt.setString(4, vo.getEmail());
			stmt.setString(5, vo.getPhone_num());
			stmt.setString(6, vo.getPhoto());
			stmt.setInt(7, vo.getSid());
			
			
			System.out.println(vo.getUserid() + "-" + vo.getUname() + "-" + "-" + vo.getSid());
			stmt.executeUpdate();
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}	
	
	public BoardVO getBoard(int seq) {
		BoardVO one = new BoardVO();
		System.out.println("===> JDBC로 getBoard() 기능 처리");
		try {
			conn = JDBCUtil2.getConnection();
			stmt = conn.prepareStatement(BOARD_GET);
			stmt.setInt(1, seq);
			rs = stmt.executeQuery();
			if(rs.next()) {
				one.setSid(rs.getInt("sid"));
				one.setUserid(rs.getString("userid"));
				one.setUname(rs.getString("uname"));
				one.setPassword(rs.getString("password"));
				one.setEmail(rs.getString("email"));
				one.setPhone_num(rs.getString("phone_num"));
				one.setPhoto(rs.getString("photo"));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return one;
	}
	
	public List<BoardVO> getBoardList(){
		List<BoardVO> list = new ArrayList<BoardVO>();
		System.out.println("===> JDBC로 getBoardList() 기능 처리");
		try {
			conn = JDBCUtil2.getConnection();
			stmt = conn.prepareStatement(BOARD_LIST);
			rs = stmt.executeQuery();
			while(rs.next()) {
				BoardVO one = new BoardVO();
				one.setSid(rs.getInt("sid"));
				one.setUserid(rs.getString("userid"));
				one.setUname(rs.getString("uname"));
				one.setPassword(rs.getString("password"));
				one.setEmail(rs.getString("email"));
				one.setPhone_num(rs.getString("phone_num"));
				one.setRegdate(rs.getDate("regdate"));
				list.add(one);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	public String getPhotoFilename(int sid){
		String filename = null;
		try{
			conn = JDBCUtil2.getConnection();
			stmt = conn.prepareStatement(BOARD_GET);
			stmt.setInt(1, sid);
			rs = stmt.executeQuery();
			if(rs.next()){
				filename = rs.getString("photo");
				System.out.println("수정 파일 이름\n");
				System.out.println(filename);
			}
			rs.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		return filename;
	}
}
