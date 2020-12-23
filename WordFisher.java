package lab13;
import java.util.*;
import java.util.Map.Entry;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class WordFisher {
	private String inputTextFile;
	private String stopwordsFile;
	private List<String> stopwords = new  ArrayList<String>();
	public HashMap<String, Integer> vocabulary= new HashMap<String,Integer>();

	public WordFisher(String inputTextFile, String stopwordsFile) {
		//recieves an input file name and second file name containing stopwords
		//initalize member variables
		this.inputTextFile= inputTextFile;
		this.stopwordsFile = stopwordsFile;
		getStopwords();
		buildVocabulary();
	}
	private static class WordNode{
		public String word;
		public int frequency;
		
		public WordNode(String word, int frequency) {
			this.word= word;
			this.frequency = frequency;
		}
	}
	protected class FrequencyComparator implements Comparator<WordNode>{
		public int compare(WordNode word1, WordNode word2) {
			if(word1.frequency<word2.frequency) {
				return 1;
			}
			else if(word1.frequency>word2.frequency) {
				return -1;
			}
			else {
				return 0;
			}
		}
	}
	private void getStopwords() {
		/*
		 * this method populates the stopwords list from a file containing all stopwords, 
		 * as pointed to by the member variable stopwordsFile. 
		 * This file contains one stopword per line.
		 */

			BufferedReader words;
			try {
				words = new BufferedReader(
						new FileReader(stopwordsFile));
				String line= words.readLine();
				while(line!=null){
					stopwords.add(line);
					line=words.readLine();	
				}
				words.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
		
	}
	
	private void buildVocabulary(){
		String reader="";
		try {
			reader = new String(Files.readAllBytes(Paths.get(inputTextFile))).toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "");
			String [] allWords = reader.split("\\s+");
			for(int i=0;i<allWords.length;i++) {
			if (vocabulary.get(allWords[i])==null) {
				vocabulary.put(allWords[i],1);
			}
			else {
				int newvalue=Integer.valueOf(String.valueOf(vocabulary.get(allWords[i])));
				newvalue++;
				
				vocabulary.put(allWords[i],newvalue);
		
			}
		}
			
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		
		
	
	
	}
	public int getWordCount() {
		//return vocabulary.values().size();
		 //Moby Dick is 218,620. Alice has 27,337.  
     
		
		
int count=0;
		for (Integer value:vocabulary.values()) {
			count+=value;
			//System.out.println("Word: " + c + "\tCounts: " + c.getValue());
		//System.out.println(count);			
		}
		return count ;
		
		
	
		
	}
	public int getNumUniqueWords() {
		return vocabulary.keySet().size();
		
	}
	public int getFrequency(String word) {
		int count=-1;
		if(!vocabulary.containsKey(word)) 
			return -1;
		
		for(Object key: vocabulary.keySet()) {
			if(key.equals(word)) {
		     count= vocabulary.get(word);
			}
			
		}
         return count;
				
				
		///loop through map
		//if specific location is the word , counter +1
	}
	
	
	public void pruneVocabulary() {
		for(int i=0;i<stopwords.size();i++) {
			vocabulary.remove(stopwords.get(i));
		}
		
		
	}
	public ArrayList<String> getTopWords(int n){
		PriorityQueue<WordNode> topWords= new PriorityQueue<WordNode>(new FrequencyComparator()) ;
		for(String key: vocabulary.keySet()) {
			WordNode newWord = new WordNode(key,vocabulary.get(key));
			topWords.add(newWord);
			
		}
		ArrayList<String> topWord2 = new ArrayList<String>();
		for(int i=0;i<n;i++) {
			topWord2.add(topWords.poll().word);
		
		}
		
		return topWord2;
		
			
		
		
	}
	
	public ArrayList<String> commonPopularWords(int n, WordFisher other){
		ArrayList<String> common= new ArrayList<String>();
		
		ArrayList<String>text1= getTopWords(n);
		ArrayList<String>text2=other.getTopWords(n);
		
		for(int i=0;i<text1.size();i++) {
			for (int j=0;j<text2.size();j++) {
				if (!text1.isEmpty()&& !text2.isEmpty()&& text1.get(i).equals(text2.get(j))) {
					common.add(text1.get(i));
				}
				
			}
		}
		return common;
		 
		
	}
	public void getTopHelper(int n) {
		ArrayList<String>text= getTopWords(n);
	
		for(int i=0;i<text.size();i++) {
			int freq= getFrequency(text.get(i));
			 System.out.println("word= " + text.get(i) + ", frequency = " + freq);
			
		}

		
		
		
	}
	
	
	
	
	
	
}
