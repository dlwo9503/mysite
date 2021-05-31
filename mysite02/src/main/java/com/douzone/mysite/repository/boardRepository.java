package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

public class boardRepository {
	public boolean insert(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = "insert into board values(null, ?, ?, now(), 0,(select IF(isnull(max(group_no)+1), 1, max(group_no)+1) from board ALIAS_FOR_SUBQUERY), 0, 0, ?)";
			pstmt = conn.prepareStatement(sql); // 명령 문장, 연결을 한 후 질의수행을 하기위함

			pstmt.setString(1, vo.getTitle()); // 바인딩
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getUserNo());

			int count = pstmt.executeUpdate(); // 질의 수행, insert문이니 executeUpdate
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
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

		return result;
	}

	public boolean insert2(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = "insert into board values(null, ?, ?, now(), ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql); // 명령 문장, 연결을 한 후 질의수행을 하기위함

			pstmt.setString(1, vo.getTitle()); // 바인딩
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getHit());
			pstmt.setLong(4, vo.getGroup_no());
			pstmt.setLong(5, vo.getOrder_no());
			pstmt.setLong(6, vo.getDepth());
			pstmt.setLong(7, vo.getUserNo());

			int count = pstmt.executeUpdate(); // 질의 수행, insert문이니 executeUpdate
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
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

		return result;
	}

	public Boolean update(BoardVo vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			conn = getConnection();

			String sql = "update board set order_no = order_no + 1 where group_no = ? and order_no >= 1";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getGroup_no());

			int count = pstmt.executeUpdate(); // 쿼리 업데이트
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
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

		return result;

	}

	public List<BoardVo> findAll() {
		List<BoardVo> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select a.no, a.title, a.depth, a.hit, b.no as userNo, b.name from board a, user b where a.user_no = b.no order by a.group_no DESC, a.order_no ASC";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String depth = rs.getString(3);
				Long hit = rs.getLong(4);
				Long userNo = rs.getLong(5);
				String name = rs.getString(6);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setDepth(0);
				vo.setHit(0);
				vo.setUserNo(userNo);
				vo.setUserName(name);

				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
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

		return list;
	}

	public BoardVo findAll2(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = null;
		try {
			conn = getConnection();

			String sql = "select title, contents from board where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String title = rs.getString(1);
				String contents = rs.getString(2);

				vo = new BoardVo();
				vo.setTitle(title);
				vo.setContents(contents);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
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
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver"); // 드라이버 로
			String url = "jdbc:mysql://192.168.254.31:3307/webdb?characterEncoding=utf8"; // url 정의
			conn = DriverManager.getConnection(url, "webdb", "webdb"); // Connection 얻기
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}
		return conn;
	}
}
