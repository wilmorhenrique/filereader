package pt.whd.filereader.newConfig.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.whd.filereader.newConfig.FieldConfig;
import pt.whd.filereader.newConfig.FixedLayoutFileConfig;
import pt.whd.filereader.newConfig.LineConfig;

public class FixedLayoutLineReader implements LineReader {

	private FixedLayoutFileConfig config;
	private List<String> linesToBeRead;
	Map<Integer, List<Field>> extractedLines = new HashMap<Integer, List<Field>>();
	
		public FixedLayoutLineReader(FixedLayoutFileConfig config) {
			
			this.config = config;
	}
	
	public FixedLayoutLineReader setLinesToBeRead(List<String> linesToBeRead) {
		this.linesToBeRead = linesToBeRead;
		return this;
	}

	public void process() {
		Integer lineNumber = 0;
		for (String line : linesToBeRead) {
			extractedLines.put(lineNumber, processLine(line));
			lineNumber++;
		}

	}

	private List<Field> processLine( String lineToBeRead) {
		List<Field> readFields = new ArrayList<Field>();
		LineConfig lineConfig = getLineConfig(geTypeOfLine(lineToBeRead));

		List<FieldConfig> fields = lineConfig.getFields();
		for (FieldConfig fieldConfig : fields) {
			Field newField = new Field();
			newField.setName(fieldConfig.getName());
			newField.setType(fieldConfig.getType());

			int finalPos = ( fieldConfig.getFinalPos() > lineToBeRead.length()) ? (lineToBeRead.length()) : fieldConfig.getFinalPos() ;  
			newField.setContent( lineToBeRead.substring(fieldConfig.getInicialPos(), finalPos));		

			readFields.add(newField);
		}
		return readFields;
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



	@Override
	public Map<Integer, List<Field>> getExtractedLines() {
		return extractedLines;
	}

	
}
