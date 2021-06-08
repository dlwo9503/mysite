package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> findAll(int page) {
		return sqlSession.selectList("board.findAll", page * 5);
	}
	
	public List<BoardVo> findAll2(int page, String keyword) {
		Map<String, Object> map = new HashMap<>();
		map.put("page", page * 5);
		map.put("keyword", keyword);
		return sqlSession.selectList("board.findAll2", map);
	}
	
	public boolean insert(BoardVo vo) {
		int count = sqlSession.insert("board.insert", vo); // count = insert 된 수
		return count == 1;
	}
	
	public BoardVo findById(Long no) {
		return sqlSession.selectOne("board.findById", no);
	}
	
	public boolean delete(Long no, Long userNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("userNo", userNo);
		int count = sqlSession.delete("board.delete", map);
		return count == 1;
	}

	public int updateHit(BoardVo vo) {
		return sqlSession.update( "board.updateHit", vo );
	}
	
	public int modify(BoardVo vo) {
		return sqlSession.update( "board.modify", vo );
	}

//	public boolean modify(BoardVo vo) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		boolean result = false;
//		try {
//			conn = getConnection();
//
//			String sql = "update board set title=?, contents=? where no=? and user_no=?";
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setString(1, vo.getTitle());
//			pstmt.setString(2, vo.getContents());
//			pstmt.setLong(3, vo.getNo());
//			pstmt.setLong(4, vo.getUserNo());
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
//	}

	public boolean insertComment(BoardVo vo) {
		int count = sqlSession.insert("board.insertComment", vo); // count = insert 된 수
		return count == 1;
	}
	
	public int count() {
		return sqlSession.selectOne("board.count");
	}

//	public int count() {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			conn = getConnection();
//
//			String sql = "select count(*) from board";
//			pstmt = conn.prepareStatement(sql);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				return rs.getInt(1);
//			}
//		} catch (SQLException e) {
//			System.out.println("error: " + e);
//		} finally {
//			try {
//				if (rs != null) {
//					rs.close();
//				}
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
//		return -1;
//	}
	
	public int findcount(String keyword) {
		return sqlSession.selectOne("board.findcount");
	}

//	public int findcount(String keyword) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			conn = getConnection();
//
//			String sql = " select count(*) from board b join user a on a.no = b.user_no"
//					+ " where b.title like ? or b.contents like ? or a.name like ?";
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setString(1, "%" + keyword + "%");
//			pstmt.setString(2, "%" + keyword + "%");
//			pstmt.setString(3, "%" + keyword + "%");
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				return rs.getInt(1);
//			}
//		} catch (SQLException e) {
//			System.out.println("error: " + e);
//		} finally {
//			try {
//				if (rs != null) {
//					rs.close();
//				}
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
//		return -1;
//	}
	
	public int updatComment(int no) {
		return sqlSession.update( "board.updatComment", no );
	}

//	public Boolean updatComment(int no) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		boolean result = false;
//
//		try {
//			conn = getConnection();
//
//			String sql = "update board set order_no=order_no+1\r\n" + "where group_no = ? and order_no>=1";
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setInt(1, no);
//
//			int count = pstmt.executeUpdate();
//			result = count == 1;
//
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//
//				if (conn != null) {
//					conn.close();
//				}
//
//			} catch (Exception e2) {
//			}
//		}
//
//		return result;
//	}
	
	public int updatComment2(int no, int orderNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("orderNo", orderNo);
		return sqlSession.update( "board.updatComment2", map );
	}

//	public Boolean updatComment2(int no, int orderNo) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		boolean result = false;
//
//		try {
//			conn = getConnection();
//
//			String sql = "update board set order_no=order_no+1\r\n" + "where group_no = ? and order_no>?";
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setInt(1, no);
//			pstmt.setInt(2, orderNo);
//
//			int count = pstmt.executeUpdate();
//			result = count == 1;
//
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//
//				if (conn != null) {
//					conn.close();
//				}
//
//			} catch (Exception e2) {
//			}
//		}
//
//		return result;
//	}

//	private Connection getConnection() throws SQLException {
//		Connection conn = null;
//
//		try {
//			Class.forName("org.mariadb.jdbc.Driver");
//			String url = "jdbc:mysql://192.168.254.31:3307/webdb?characterEncoding=utf8";
//			conn = DriverManager.getConnection(url, "webdb", "webdb");
//		} catch (ClassNotFoundException e) {
//			System.out.println("드라이버 로딩 실패:" + e);
//		}
//		return conn;
//	}
}