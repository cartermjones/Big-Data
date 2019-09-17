import java.io.*;

public class Testing {

	public static void main(String[] args) {
		File file = new File("C:\\Users\\Carter Jones\\Desktop\\Big Data\\20-newsgroups\\sci.space\\59849");
		String parsedLine;
		
		FileParser gumbo = new FileParser();
		gumbo.parseFile(file);
		parsedLine = gumbo.returnParsedLine();
		System.out.println(parsedLine);
	}
}
