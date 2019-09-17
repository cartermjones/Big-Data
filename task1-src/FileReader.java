import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileReader {
	
	String parsedLine;
	int categoryCount = 1;
	String category;
	
	public void readFiles(File[] files, FileWriter tsvWriter) throws IOException {
		
		FileParser gumbo = new FileParser();
		
		for (File file : files) 
		{
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
	
	public void generateFile(FileWriter tsvWriter, String parsedLine) {
		try {
			tsvWriter.write(parsedLine);
			tsvWriter.write("\n");
		} catch(IOException ex) {
			System.out.printf("ERROR: %s\n", ex);
		}
	}
	
	public void flushAndCloseWriter(FileWriter tsvWriter) {
		try {
			tsvWriter.flush();
			tsvWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
