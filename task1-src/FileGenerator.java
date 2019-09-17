import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileGenerator {
	
	public static void main(String[] args) throws IOException {
		//First we indicate which directory we will be traversing.
		File[] files = new File("C:\\Users\\Carter Jones\\Desktop\\Big Data\\20-newsgroups\\").listFiles();
		//We will also need a FileWriter to handle writing to the .tsv file.
		FileWriter tsvWriter = new FileWriter("task1-src.tsv");
		
		//We constuct a FileReader named julep.
		FileReader julep = new FileReader();
		
		//Then we read the files - FileReader also contains the code to push the processed data to the .tsv file.
		//It's important to flush and close a FileWriter before the program terminates.
		julep.readFiles(files, tsvWriter);	
		julep.flushAndCloseWriter(tsvWriter);
		
		System.out.println("Your .tsv file is ready for business! Thank you for your patience.");
	}
}
