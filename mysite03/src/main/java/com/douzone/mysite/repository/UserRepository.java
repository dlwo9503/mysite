package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(UserVo vo) {
		int count = sqlSession.insert("user.insert", vo);
		return count == 1;
	}
	
	public UserVo findByEmailAndPassword(String email, String password) {
		
		Map<String, String> map = new HashMap<>();
		map.put("e", email);
		map.put("p", password);
		return sqlSession.selectOne("user.findByEmailAndPassword", map);
	}
	
	public UserVo findByNo(Long userNo) { // update문
		return sqlSession.selectOne("user.findByNo", userNo);
	}
	
	public void update(UserVo userVo) {
		sqlSession.update("user.update", userVo);
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		boolean result = false;
//		try {
//			conn = sqlSession.getConnection();
//
//			String sql = "update user set name=?, password=?, gender=? where no=?";
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setString(1, vo.getName());
//			pstmt.setString(2, vo.getPassword());
//			pstmt.setString(3, vo.getGender());
//			pstmt.setLong(4, vo.getNo());
//
//			int count = pstmt.executeUpdate();
//			result = count == 1;
//
//		} catch (SQLException e) {
//			System.out.println("error: " + e);
//		} finally {
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return result;
	}
}