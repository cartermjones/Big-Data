package MyPackage;
//This class is for generating Category objects that combine category name, number of documents, and number of words attributes.
//These are necessary for doing calculations.
public class Category {
	
	String category_name;
	int docs_in_category;
	int words_in_category;
	
	public Category(String category_name, int docs_in_category, int words_in_category) {
		this.category_name = category_name;
		this.docs_in_category = docs_in_category;
		this.words_in_category = words_in_category;
	}
	
	public String getCategoryName() {
		return category_name;
	}
	
	public int getDocsInCategory() {
		return docs_in_category;
	}
	
	public int getWordsInCategory() {
		return words_in_category;
	}
	
	public int calculateAverageVerbosity() {
		return words_in_category / docs_in_category;
	}
}
