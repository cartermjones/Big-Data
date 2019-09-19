package MyPackage;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Statilyzer 
{
	
	int categoryCount = 1;
	int number_of_documents = 0;
	int average_docs_per_category;
	int total_body_words = 0;
	int average_body_words;
	
	String most_verbose_category_name;
	String least_verbose_category_name;
	
	int most_verbose_category_average;
	int least_verbose_category_average = 100000;
	
	List<String> category_names = new ArrayList<String>();
	List<Integer> words_per_category = new ArrayList<Integer>();
	List<Integer> docs_per_category = new ArrayList<Integer>();
	List<Category> categories = new ArrayList<Category>();
	
	//This method determines how many documents there are in the .tsv file.
	public void countDocuments(BufferedReader bufferedReader) throws IOException 
	{
		String line = null;
		
		while((line = bufferedReader.readLine()) != null)
		{
			number_of_documents++;
		}
		
	}
	
	//This method counts our categories and aggregates them to the categories ArrayList.
	//It is also supposed to gather the number of documents per category.
	public void countCategories(BufferedReader bufferedReader) throws IOException
	{
		String line = null;
		String[] dataRow;	
		int docs_in_category = 0;
		
		//While there are files to read in our buffered reader...
		while((line = bufferedReader.readLine()) != null)
		{
			//...we split the row at the tabs.
			dataRow = line.split("\\t");
			
			//If the category column is not already in categories...
			if(!category_names.contains(dataRow[0])) 
			{
				//...we add it to categories.
				category_names.add(dataRow[0]);
				
				if(docs_in_category > 0){
					
					docs_per_category.add(docs_in_category);
					
					docs_in_category = 0;
				} 
				
			} 
			
			else if (category_names.contains(dataRow[0]))
			{
				docs_in_category++;
				continue;
			} 
			
			else 
			{
				continue;
			}
			
		}
		
		//We flush any values left into the last slot, allowing us to capture all the data.
		docs_per_category.add(docs_in_category);
		categoryCount = category_names.size();
	}
	
	//This method determines the amount of words in the bodies of the documents stored in the .tsv file.
	public void countBodyWords(BufferedReader bufferedReader) throws IOException
	{
		String line = null;
		String[] dataRow;
		String[] bodyWords;
		
		while((line = bufferedReader.readLine()) != null) 
		{
			dataRow = line.split("\\t");
			bodyWords = dataRow[4].split("\\s");
			total_body_words += bodyWords.length;
		}
	}
	
	//This method captures how many words there are in the bodies of each category. 
	//The logic is similar to that in the countCategories() method.
	public void countWordsPerCategory(BufferedReader bufferedReader) throws IOException 
	{
		String line = null;
		String[] dataRow;	
		String[] bodyWords;
		List<String> local_categories = new ArrayList<String>();
		int words_in_category = 0;
	
		while((line = bufferedReader.readLine()) != null)
		{
			dataRow = line.split("\\t");
			
			if(!local_categories.contains(dataRow[0])) 
			{	
				local_categories.add(dataRow[0]);
				if(words_in_category > 0){
					words_per_category.add(words_in_category);
					words_in_category = 0;
				} 
				
				else 
				{	
					continue;
				}
			} 
			
			else 
			{
				bodyWords = dataRow[4].split("\\s");
				words_in_category += bodyWords.length;
				continue;
			}
		}
		words_per_category.add(words_in_category);
	}
	
	
	
	//This method calculates some relevant values and builds our .tsv file.
	public void generateFile(FileWriter fileWriter) {
		average_docs_per_category = number_of_documents / categoryCount;
		average_body_words = total_body_words / number_of_documents;
		
		String line = Integer.toString(number_of_documents) + "\t" + Integer.toString(average_body_words) + "\t" + Integer.toString(average_docs_per_category) + "\t" + most_verbose_category_name + "\t" + least_verbose_category_name; 
		try {
		fileWriter.write(line);
		} catch(IOException ex) {
			ex.getStackTrace();
		}
	}
	
	//This method builds a List of Category objects used for calculations.
	public void buildCategories() {
		for(int i = 0; i < category_names.size(); i++)
		{
			Category category = new Category(category_names.get(i), docs_per_category.get(i), words_per_category.get(i));
			categories.add(category);
		}
	}
	
	//This method calculates the category with the highest average words-per-document.
	public void calculateMaxVerbosity() {
		for(Category category : categories)
		{
			if(category.calculateAverageVerbosity() > most_verbose_category_average) {
				most_verbose_category_average = category.calculateAverageVerbosity();
				most_verbose_category_name = category.getCategoryName();
			} 
			else
			{
				continue;
			}
		}
	}
	
	//This method calculates the category with the lowest average words-per-document.
	public void calculateMinVerbosity() {
		for(Category category : categories)
		{
			if(category.calculateAverageVerbosity() < least_verbose_category_average) {
				least_verbose_category_average = category.calculateAverageVerbosity();
				least_verbose_category_name = category.getCategoryName();
			} 
			else
			{
				continue;
			}
		}
	}

	//This method is used for testing values.
	public void printNumbers() 
	{
		average_docs_per_category = number_of_documents / categoryCount;
		average_body_words = total_body_words / number_of_documents;
		System.out.println("Number of categories: " + categoryCount);
		System.out.println("Number of documents: " + number_of_documents);
		System.out.println("There is an average of " + average_docs_per_category + " documents per category.");
		System.out.println("There are " + total_body_words + " words in total.");
		System.out.println("There is an average of " + average_body_words + " words per document");
	
	}
	
	//This method is used for testing values.
	public void displayCategories() 
	{
		System.out.println(category_names);
		System.out.println(words_per_category);
		System.out.println(docs_per_category);
		System.out.println("Category with the highest average words-per-document: " + most_verbose_category_name);
		System.out.println("Category with the lowest average words-per-document: " + least_verbose_category_name);
	}
}
