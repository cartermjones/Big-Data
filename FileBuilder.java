package MyPackage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileBuilder {

	public static void main(String[] args) throws IOException {
		
		String fileName = args[0];
		String newFileName = args[1];
		
		try {
			
			FileReader tsvDocumentCountReader = new FileReader(fileName);
			BufferedReader tsvDocumentCountBuffReader = new BufferedReader(tsvDocumentCountReader);
			
			FileReader tsvCategoryCountReader = new FileReader(fileName);
			BufferedReader tsvCategoryCountBuffReader = new BufferedReader(tsvCategoryCountReader);
			
			FileReader tsvWordCountReader = new FileReader(fileName);
			BufferedReader tsvWordCountBuffReader = new BufferedReader(tsvWordCountReader);
			
			FileReader tsvWordPerCategoryReader = new FileReader(fileName);
			BufferedReader tsvWordPerCategoryBuffReader = new BufferedReader(tsvWordPerCategoryReader);
			
			FileWriter tsvWriter = new FileWriter(newFileName);
			
			Statilyzer gator = new Statilyzer();
			
			gator.countDocuments(tsvDocumentCountBuffReader);
			gator.countCategories(tsvCategoryCountBuffReader);
			gator.countBodyWords(tsvWordCountBuffReader);
			gator.countWordsPerCategory(tsvWordPerCategoryBuffReader);
			
			gator.buildCategories();
			gator.calculateMaxVerbosity();
			gator.calculateMinVerbosity();
			gator.generateFile(tsvWriter);
			
			tsvDocumentCountBuffReader.close();
			tsvCategoryCountBuffReader.close();
			tsvWordCountBuffReader.close();
			tsvWordPerCategoryBuffReader.close();
			tsvWriter.flush();
			tsvWriter.close();
			
			System.out.println("Your .tsv file has been generated. Thank you for your patience!");
			
		} catch (IOException ex){
			ex.printStackTrace();
		}
		
		
	}
}

