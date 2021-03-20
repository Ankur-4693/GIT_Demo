package com.nt.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nt.ExceltoJAva;
import com.nt.bean.Student;

@org.springframework.stereotype.Controller
public class Controller {
	@RequestMapping("/dashboard")
	public String getDashboard() {
		return "Dashboard";
	}

	@RequestMapping("/marklist")
	public String getmarklist(Model map) {
		List<Student> list=ExceltoJAva.getAllDataFromExcel();
		System.out.println(list);
		map.addAttribute("list", list);
		return "MarkList";
	}

	@RequestMapping("/marksheet")
	public String getMarksheet() {
		return "MarkSheet";
	}
}