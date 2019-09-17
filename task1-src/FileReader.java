import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileReader {
	
	String parsedLine;
	int categoryCount = 1;
	String category;
	
	public void readFiles(File[] files, FileWriter tsvWriter) throws IOException {
		
		FileParser gumbo = new FileParser();
		
		//This is lightly modified boilerplate code for traversing a file system.
		for (File file : files) 
		{
			//If the file is a directory, we take the name of the directory and designate it the category for the upcoming messages.
			//We also alert the user to the progress through the file system.
			//If the file is not a directory, our FileParser (gumbo) will parse the file, 
			//move the data to a parsedLine variable, and add it to the .tsv file under construction.
			if(file.isDirectory()) {
				category = file.getName();
				System.out.printf("%d of 20 directories analyzed \n", categoryCount);
				categoryCount++;
				readFiles(file.listFiles(), tsvWriter);
			} else {
				gumbo.parseFile(file, category);
				parsedLine = gumbo.returnParsedLine();
				generateFile(tsvWriter, parsedLine);
			}
		}
	}
	
	//This method is used to write to the .tsv file under construction.
	public void generateFile(FileWriter tsvWriter, String parsedLine) {
		try {
			tsvWriter.write(parsedLine);
			tsvWriter.write("\n");
		} catch(IOException ex) {
			System.out.printf("ERROR: %s\n", ex);
		}
	}
	
	//This method flushes and closes the FileWriter - everything packaged neatly into one method for ease of use.
	public void flushAndCloseWriter(FileWriter tsvWriter) {
		try {
			tsvWriter.flush();
			tsvWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
