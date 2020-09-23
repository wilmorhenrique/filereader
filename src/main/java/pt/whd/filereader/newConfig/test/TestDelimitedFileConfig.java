package pt.whd.filereader.newConfig.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pt.whd.filereader.newConfig.DelimitedFileConfig;
import pt.whd.filereader.newConfig.FieldConfig;
import pt.whd.filereader.newConfig.LineConfig;
import pt.whd.filereader.newConfig.business.DelimitedLineReader;
import pt.whd.filereader.newConfig.business.Field;

public class TestDelimitedFileConfig {

	public static void main(String[] args) throws IOException  {
	
		// create a configurator to read and extract values from the file
		DelimitedFileConfig config = configFile();
		System.out.println(config);
		
		List<String> linesToBeRead = getLinesFromFile();
		DelimitedLineReader reader = new DelimitedLineReader(config);
		reader.setLinesToBeRead(linesToBeRead);
		
		reader.process();
		
		for (Map.Entry<Integer, List<Field>> entry : reader.getExtractedLines().entrySet()) {
			List<Field> fields = entry.getValue();
			System.out.println("Linha: " + entry.getKey() );
			for (Field field : fields) {
				System.out.println(field);
			}
		}
		
	}
	
	private static List<String> getLinesFromFile() {
		File file = new File("src/main/resources/testeDelimited.txt"); 


		String fileLine; 
		List<String> lines = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			while ((fileLine = br.readLine()) != null) {
					lines.add(fileLine);
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lines;

	}

	
	private static DelimitedFileConfig configFile() {
		// create a config with a position of the type of the line ( if it not exits, put 0 and 0.
			
		List<FieldConfig> fields = new ArrayList<FieldConfig>();
		List<LineConfig> lines= new ArrayList<LineConfig>();
			
		fields.add(new FieldConfig.Builder().name("Campo_1").type("String").position(1).build());
		fields.add(new FieldConfig.Builder().name("Campo_2").type("String").position(2).build());
		
		lines.add(new LineConfig.Builder().fields(fields).type("01").build());
		
		fields = new ArrayList<FieldConfig>();
		
		// montando outro tipo de linha 
		fields.add(new FieldConfig.Builder().name("name").type("String").position(3).build());
		fields.add(new FieldConfig.Builder().name("BirthDate").type("String").position(4).build());
		LineConfig line = new LineConfig.Builder().fields(fields).type("02").build();
		lines.add(line);

		DelimitedFileConfig config = new DelimitedFileConfig.Builder().positionOfLineType(0).lines(lines).delimitedBy(",").build();
	
		return config;
	}
}
