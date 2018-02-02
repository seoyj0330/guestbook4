package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	public List<GuestbookVo> getList() {
		
		List<GuestbookVo> list =sqlSession.selectList("guestbook.getAllList");
		return list;
	}
	
	public void insert(GuestbookVo gbVo) {
		
		sqlSession.insert("guestbook.add", gbVo);
	}
	
	public void delete(int no, String password) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("password", password);
		
		sqlSession.delete("guestbook.delete",map);
	}
}

