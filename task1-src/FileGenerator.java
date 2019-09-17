import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileGenerator {
	
	public static void main(String[] args) throws IOException {
		File[] files = new File("C:\\Users\\Carter Jones\\Desktop\\Big Data\\20-newsgroups\\").listFiles();
		FileWriter tsvWriter = new FileWriter("task1-src.tsv");
		
		FileReader julep = new FileReader();
		
		julep.readFiles(files, tsvWriter);	
		julep.flushAndCloseWriter(tsvWriter);
		System.out.println("Your .tsv file is ready for business! Thank you for your patience.");
	}
}
