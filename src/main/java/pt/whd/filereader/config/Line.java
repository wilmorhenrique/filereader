package pt.whd.filereader.config;

import java.util.ArrayList;
import java.util.List;

public class Line {

	List<Field> fields;
	private String content;
	
	public Line() {
		super();
		this.fields = new ArrayList<Field>();
	}

	public Line(List<Field> fields) {
		super();
		this.fields = fields;
	}
	
	public List<Field> getFields() {
		return fields;
	}


	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	
	public void addField(Field field) {
		fields.add(field);
				
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}

	public List<Field> readFields() {
		List<Field> fieldsProcessed = new ArrayList<Field>();
		for (Field field : fields) {
			fieldsProcessed.add(field.process(this.content));
		}
		
		return fieldsProcessed;
	}

	public Line processLine(String lineContent) {
		this.content = lineContent;
		Line newLine = new Line();
		newLine.setContent(this.content);
		newLine.setFields(this.readFields());
		return newLine;
	}
	

	
}
