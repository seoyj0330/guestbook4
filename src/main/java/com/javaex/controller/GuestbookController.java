package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Controller
public class GuestbookController {
	
	@Autowired		
	private GuestbookDao guestbookDao;		//자동 연결로 dao객체 선언

	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute GuestbookVo guestbookVo) {	//GuestbookVo를 받아오기
		System.out.println("insert 진입");
		System.out.println(guestbookVo.toString());
		
		guestbookDao.insert(guestbookVo);
		
		return "redirect:/list";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		System.out.println("list 진입");
		List<GuestbookVo> glist = guestbookDao.getList();
		//System.out.println(glist.toString());
		
		model.addAttribute("list", glist);
		
		return "list";
	}
	
	@RequestMapping(value="/deleteform", method=RequestMethod.GET)
	public String deleteform() {
		System.out.println("deleteform 진입");
	
		return "deleteform";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@RequestParam("no") int no, @RequestParam("password") String password ) {
		System.out.println("delete 진입");
		
		guestbookDao.delete(no, password);
		System.out.println("no :" + no + "," + "password" + password);
		return "redirect:/list";
		
	}
	
}
