package com.e.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;

import com.e.service.IWebPageService;
import com.e.service.WebPageServiceImpl;

public class Bootstrap {
	
	static IWebPageService webPageService;
	
	public static void testReadWebPage() {
		webPageService=new WebPageServiceImpl();
		String pageContent=webPageService.getListOfWordsForWebPage("https://www.314e.com/");
		System.out.println(webPageService.countSingleWordFrequency(pageContent));
	}
	

	
	
	
	
}
