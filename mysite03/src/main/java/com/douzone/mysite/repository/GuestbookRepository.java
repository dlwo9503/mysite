package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private DataSource dataSource;
	
	public List<GuestbookVo> findAll() {
		return sqlSession.selectList("guestbook.findAll"); // selectList 형태로 받아옴, guestbook을 찾아서 그 안에 findAll을 실행함 (guestbook.xml)
	}
	
	public boolean insert(GuestbookVo vo) {
		int count = sqlSession.insert("guestbook.insert", vo); // count = insert 된 수
		return count == 1;
	}
	
	public boolean delete(GuestbookVo vo) {
		int count = sqlSession.delete("guestbook.delete", vo);
		return count == 1;
	}
	
	public GuestbookVo findAll2(Long no2) {
		GuestbookVo vo = new GuestbookVo();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();

			String sql = "select no, password from guestbook where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no2);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				Long no = rs.getLong(1);
				String password = rs.getString(2);
//
				vo.setNo(no);
				vo.setPassword(password);
			}
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return vo;
//		return sqlSession.selectOne("guestbook.findAll2");
	}
}