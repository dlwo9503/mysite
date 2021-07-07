package com.douzone.mysite.repository;

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
	
	public List<GuestbookVo> findAll(Long no) {
		return sqlSession.selectList("guestbook.findAllByNo", no); // selectList 형태로 받아옴, guestbook을 찾아서 그 안에 findAll을 실행함 (guestbook.xml)
	}
	
	public boolean insert(GuestbookVo vo) {
		int count = sqlSession.insert("guestbook.insert", vo); // count = insert 된 수
		return count == 1;
	}
	
	public boolean delete(GuestbookVo vo) {
		int count = sqlSession.delete("guestbook.delete", vo);
		return count == 1;
	}
	
	public GuestbookVo findAll2(Long no) {
		return sqlSession.selectOne("guestbook.findAll2", no);
	}
}