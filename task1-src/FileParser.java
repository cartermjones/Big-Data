import java.io.*;
import java.util.Scanner;

public class FileParser {
	
	//String category;
	String sender = "None";
	String organization = "None";
	String subject = "None";
	String body ="None";
	String parsedLine;
	
	//File file = new File("C:\\Users\\Carter Jones\\Desktop\\Big Data\\20-newsgroups\\sci.space\\59849");
	
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
