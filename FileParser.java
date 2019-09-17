import java.io.*;
import java.util.Scanner;

public class FileParser {
	
	String category;
	String sender;
	String organization;
	String subject;
	String body;
	String parsedLine;
	
	File file = new File("C:\\Users\\Carter Jones\\Desktop\\Big Data\\20-newsgroups\\sci.space\\59849");
	
	void parseFile(File file) {
		
		String value;
		
		try {
			
			Scanner output = new Scanner(file);
			category = "space";
			
			while(output.hasNextLine()) {
				value = output.nextLine();
				if(value.contains(">")) {
					value = null;
				} else if(value.contains("From:")) {
					sender = value;
				} else if(value.contains("Subject:")) {
					subject = value;
				} else if(value.contains("Organization:")) {
					organization = value;
				} else if(value.contains(":")) {
					value = null;
				}  else {
					body += value;
				}	
			}
			
			body = body.replaceAll("\\s+", " ");
			output.close();
			
			//System.out.println(category + "\t" + sender + "\t" + subject + "\t" + organization + "\t" + body);
			parsedLine = category + "\t" + sender + "\t" + subject + "\t" + organization + "\t" + body;
		} catch(IOException ex) {
			System.out.printf("ERROR: %s\n", ex );
		}
	}
	
	String returnParsedLine() {
		return parsedLine;
	}
}
