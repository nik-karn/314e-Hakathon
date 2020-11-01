package com.e.service;
import java.util.List;
import java.util.Map;

public interface IWebPageService {
	public String getListOfWordsForWebPage (String url);
	public Map<Integer,String> countSingleWordFrequency(String pageContent);
}
