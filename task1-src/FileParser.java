import java.io.*;
import java.util.Scanner;

public class FileParser {
	
	//These variables represent the types of data we're looking for.
	String sender = "None";
	String organization = "None";
	String subject = "None";
	String body ="None";
	
	//This variable will hold the parsed line that will be inserted into the .tsv file.
	String parsedLine;
	
	//This method takes in a file and a category determined by the directory name, and cleans the data and formats it properly.
	void parseFile(File file, String category) {
		
		String value;
		
		try {
			
			Scanner output = new Scanner(file);
			
			while(output.hasNextLine()) {
				value = output.nextLine();
	
				if(value.contains(">")) {
					continue;
				} else if(value.contains("From:")) {
					sender = value;
				} else if(value.contains("Subject:")) {
					subject = value;
				} else if(value.contains("Organization:")) {
					organization = value;
				} else if(value.contains(":")) {
					continue;
				}   else {
					body += value + " ";
				}	
			}
			
			body = body.replaceAll("\\s+", " ");
			body = body.replaceAll("\\n", " ");
			body = body.replaceAll("\\t", " ");
			
			output.close();
			
			parsedLine = category + "\t" + sender + "\t" + subject + "\t" + organization + "\t" + body;
			body = "";
		} catch(IOException ex) {
			System.out.printf("ERROR: %s\n", ex );
		}
	}
	
	String returnParsedLine() {
		return parsedLine;
	}
}
	
