package com.e.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import com.e.constant.Constants;

import java.nio.charset.StandardCharsets;

@Component
public class WebPageServiceImpl implements IWebPageService{


	@Override
	public String getListOfWordsForWebPage (String url) {
		String result="";
		
		Connection conn = Jsoup.connect(url);
		//executing the get request
		Document doc;
		try {
			doc = conn.get();
			//Retrieving the contents (body) of the web page
			result= doc.body().text();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Map<Integer, String> countSingleWordFrequency(String pageContent){
		long time = System.currentTimeMillis();
		
		Map<String, Word> countMap = new HashMap<String, Word>();
		Map<Integer,String> result=new HashMap<Integer,String>();	
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(pageContent.getBytes(StandardCharsets.UTF_8))));
        String line;
        try {
			while ((line = reader.readLine()) != null) {
			    String[] words = line.split("[^A-ZÃƒâ€¦Ãƒâ€žÃƒâ€“a-zÃƒÂ¥ÃƒÂ¤ÃƒÂ¶]+");
			    for (String word : words) {
			        if ("".equals(word)) {
			            continue;
			        }

			        Word wordObj = countMap.get(word);
			        if (wordObj == null) {
			            wordObj = new Word();
			            wordObj.word = word;
			            wordObj.count = 0;
			            countMap.put(word, wordObj);
			        }

			        wordObj.count++;
			    }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        SortedSet<Word> sortedWords = new TreeSet<Word>(countMap.values());
        int i = 0;
        int maxWordsToDisplay =Constants.MAX_WORD_TO_COUNT;

        String[] wordsToIgnore = {"the", "and", "a"};
        int rank=1;
        for (Word word : sortedWords) {
            if (i >= maxWordsToDisplay) { 
                break;
            }

            if (Arrays.asList(wordsToIgnore).contains(word.word)) {
                i++;
                maxWordsToDisplay++;
            } else {
                System.out.println(word.count + "\t" + word.word);
                result.put(rank, word.word);
                i++;
                rank++;
            }

        }
		return result;
	}
	public static class Word implements Comparable<Word> {
        String word;
        int count;

        @Override
        public int hashCode() { return word.hashCode(); }

        @Override
        public boolean equals(Object obj) { return word.equals(((Word)obj).word); }

        @Override
        public int compareTo(Word b) { return b.count - count; }
    }
}
