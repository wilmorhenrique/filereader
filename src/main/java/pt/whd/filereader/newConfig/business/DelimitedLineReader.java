package pt.whd.filereader.newConfig.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.whd.filereader.newConfig.DelimitedFileConfig;
import pt.whd.filereader.newConfig.FieldConfig;
import pt.whd.filereader.newConfig.LineConfig;

public class DelimitedLineReader implements LineReader {

	private DelimitedFileConfig config;
	private List<String> linesToBeRead;
	Map<Integer, List<Field>> extractedLines = new HashMap<Integer, List<Field>>();

	
		public DelimitedLineReader(DelimitedFileConfig config) {
			
			this.config = config;
	}
	
	public DelimitedLineReader setLinesToBeRead(List<String> linesToBeRead) {
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

	private List<Field> processLine(String lineToBeRead) {
		List<Field> readFields = new ArrayList<Field>();

		String[] lineSplited = lineToBeRead.split(config.getDelimitedBy());
		LineConfig lineConfig = getLineConfig(geTypeOfLine(lineSplited));
		
		List<FieldConfig> fields = lineConfig.getFields();
		for (FieldConfig fieldConfig : fields) {
			Field newField = new Field();
			newField.setName(fieldConfig.getName());
			newField.setType(fieldConfig.getType());
			newField.setContent( lineSplited[fieldConfig.getPosition()]);		
			
			readFields.add(newField);
		}
		return readFields;
	}

	private LineConfig getLineConfig(String typeOfLine) {
		if (config.getPositionOfLineType() == -1 ) return config.getLines().get(0); 

		// getting the configuration for that type of line
		return config.getLines().stream()
				  .filter(line -> typeOfLine.equals(line.getType()))
				  .findAny()
				  .orElse(config.getLines().get(0));
		
	}


	private String geTypeOfLine(String[] lineToBeRead) {
		return lineToBeRead[config.getPositionOfLineType()];
	}

	@Override
	public Map<Integer, List<Field>> getExtractedLines() {
		return extractedLines;
	}

	
}
