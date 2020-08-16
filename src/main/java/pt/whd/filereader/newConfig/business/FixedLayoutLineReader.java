package pt.whd.filereader.newConfig.business;

import java.util.ArrayList;
import java.util.List;


import pt.whd.filereader.newConfig.FieldConfig;
import pt.whd.filereader.newConfig.FixedLayoutFileConfig;
import pt.whd.filereader.newConfig.LineConfig;

public class FixedLayoutLineReader implements LineReader {

	private FixedLayoutFileConfig config;
	private List<String> linesToBeRead;
	List<Field> readFields = new ArrayList<Field>();
	
		public FixedLayoutLineReader(FixedLayoutFileConfig config) {
			
			this.config = config;
	}
	
	public FixedLayoutLineReader setLinesToBeRead(List<String> linesToBeRead) {
		this.linesToBeRead = linesToBeRead;
		return this;
	}

	public void process() {
		for (String line : linesToBeRead) {
			processLine(line);
		}

	}

	private void processLine(String lineToBeRead) {
		LineConfig lineConfig = getLineConfig(geTypeOfLine(lineToBeRead));

		List<FieldConfig> fields = lineConfig.getFields();
		for (FieldConfig fieldConfig : fields) {
			Field newField = new Field();
			newField.setName(fieldConfig.getName());
			newField.setType(fieldConfig.getType());

			int finalPos = ( fieldConfig.getFinalPos() > lineToBeRead.length()) ? (lineToBeRead.length()) : fieldConfig.getFinalPos() ;  
			newField.setContent( lineToBeRead.substring(fieldConfig.getInicialPos(), finalPos));		

			getReadFields().add(newField);
		}
	}

	private LineConfig getLineConfig(String typeOfLine) {
		if (config.getSizeOfLineType() == -1 ) return config.getLines().get(0); 

		// getting the configuration for that type of line
		return config.getLines().stream()
				  .filter(line -> typeOfLine.equals(line.getType()))
				  .findAny()
				  .orElse(config.getLines().get(0));
		
	}


	private String geTypeOfLine(String lineToBeRead) {
		return lineToBeRead.substring(config.getPositionOfLineType(), config.getSizeOfLineType());
	}

	public List<Field> getReadFields() {
		return readFields;
	}

	
}
