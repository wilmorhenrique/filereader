package pt.whd.filereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pt.whd.filereader.config.Field;
import pt.whd.filereader.config.FileConfig;
import pt.whd.filereader.config.Line;

public class TestReadFile 
{

	public static void main(String[] args) throws IOException  {
	
		// create a configurator to read and extract values from the file
		FileConfig config = configFile();

		File file = new File("src/main/resources/teste.txt"); 

		BufferedReader br = new BufferedReader(new FileReader(file)); 

		String fileLine; 
		List<Line> linesProcessed = new ArrayList<Line>();
		while ((fileLine = br.readLine()) != null) {
			
			Line line;
			try {
				line = config.getLineConfigByType(fileLine);
				Line newLine = line.processLine(fileLine); 
				linesProcessed.add(newLine);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}	

		System.out.println("linhas=" + linesProcessed.size());
		// loop into processed lines and shows each field extracted
		for (Line processada : linesProcessed) {
			System.out.println(processada.getContent());
			 List<Field> fields = processada.getFields(); 
			 for (Field field : fields) {
				 System.out.println(field.getName() + " - " + field.getContent());
			}
		}
		br.close();
		
	}
	
	
	private static FileConfig configFile() {
		// create a config with a position of the type of the line ( if it not exits, put 0 and 0.
		FileConfig config = new FileConfig(0, 2);
		
		List<Field> fields = new ArrayList<Field>();
		Field campo1 = new Field("Campo_1", "String", 10, 2);
		fields.add(campo1);

		Field campo2 = new Field("Campo_2", "String", 10, 12);
		fields.add(campo2);
	
		// create a comfoguration of line tipe "01"
		config.addLine("01", new Line(fields));

		fields = new ArrayList<Field>();
		campo1 = new Field("name", "String", 29, 24);
		fields.add(campo1);

		campo2 = new Field("BirthDate", "String", 10, 53);
		fields.add(campo2);
	
		// create a comfoguration of line tipe "01"
		config.addLine("02", new Line(fields));
	
		
		return config;
	}
	
}
