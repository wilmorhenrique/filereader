package pt.whd.filereader.config;

public class Field {
	
	private String name;
	private String type;
	private int size;
	private int inicialPos;
	private String content;
	
	public Field(String name, String type, int size, int inicialPos) {
		super();
		this.name = name;
		this.type = type;
		this.size = size;
		this.inicialPos = inicialPos;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public int getInicialPos() {
		return inicialPos;
	}


	public void setInicialPos(int inicialPos) {
		this.inicialPos = inicialPos;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getFinalPos() {
		return this.inicialPos + this.size;
	}

	public Field process(String lineContent) {
		Field newField = new Field(this.name, this.type, this.size, this.inicialPos);
		int finalPos = ( this.getFinalPos() > lineContent.length()) ? (lineContent.length()) : this.getFinalPos() ;  
		newField.setContent( lineContent.substring(this.getInicialPos(), finalPos));		
		return newField;
	}



	
	
}
