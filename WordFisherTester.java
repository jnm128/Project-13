package lab13;

public class WordFisherTester {
	
	public static void main(String[] args) {
		WordFisher moby= new WordFisher("moby-dick.txt", "stopwords.txt");
		WordFisher alice= new WordFisher("carroll-alice.txt", "stopwords.txt");
		
		
		
		//get stop words
		System.out.println("Moby");
		System.out.println("word count: "+ moby.getWordCount());
		System.out.println("Unique words: "+ moby.getNumUniqueWords());
		System.out.println("frequency of whale: " + moby.getFrequency("whale"));
		System.out.println("frequency of handkerchief: " + moby.getFrequency("handkerchief"));
		moby.pruneVocabulary();
		System.out.println("prune word count: "+ moby.getWordCount());
		System.out.println("prune top words: "+ moby.getTopWords(10));
		System.out.println("\n prune top words list: ");
		moby.getTopHelper(10);
		
		
		
		System.out.println("");
		System.out.println("carroll alice");
		System.out.println("word count: "+ alice.getWordCount());
		System.out.println("Unique words: "+ alice.getNumUniqueWords());
		System.out.println("frequency of whale: " + alice.getFrequency("whale"));
		System.out.println("frequency of handkerchief: " + alice.getFrequency("handkerchief"));
		alice.pruneVocabulary();
		System.out.println("prune word count: "+ alice.getWordCount());
		System.out.println("prune top words: "+ alice.getTopWords(10));
		System.out.println("prune common words between moby and alice: "+ alice.commonPopularWords(20,moby));
		System.out.println("\n prune top words list: ");
		alice.getTopHelper(10);
		
		
		
		
		
	}

}
