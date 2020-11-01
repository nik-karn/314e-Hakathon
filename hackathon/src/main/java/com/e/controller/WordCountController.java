package com.e.controller;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


public class WordCountController {
	
	@RequestMapping("/index")
	public String index(Model model){
		model.addAttribute("result", "WELCOME TO THE HACKATHON ASSINGMENT");
		return "index";
	}
	
	
}
