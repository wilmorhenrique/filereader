package pt.whd.filereader.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Class where the configuration of each type of line of the file 
 * @author whneto
 * 
 */
public class FileConfig {

	private int positionOfLineType;
	private int sizeOfLineType;
	Map<String, Line> lines;
	
	/**
	 * Constructor of Class
	 * @param positionOfLineType  - Position where the string that identify the type of line ( header, body...) begins 
	 * @param sizeOfLineType  - Size if the string that that identify the type of line
	 */
	public FileConfig(int positionOfLineType, int sizeOfLineType) {
		super();
		this.setPositionOfLineType(positionOfLineType);
		this.setSizeOfLineType(sizeOfLineType);
		lines = new HashMap<String, Line>();
	}

	public Line getLineConfigByType(String line) throws Exception {
		if (sizeOfLineType == 0) {
			return lines.get(lines.keySet().toArray()[0]);
		}
		
		Line lineConfig = lines.get(line.substring(positionOfLineType, positionOfLineType + sizeOfLineType));
		if (lineConfig  == null  ) {
			throw new Exception("Type of line not found");
		}
		return lineConfig;
	}
	
	public void addLine(String identificatorOfLine, Line line) {
		lines.put(identificatorOfLine, line);
	}


	public int getPositionOfLineType() {
		return positionOfLineType;
	}


	public void setPositionOfLineType(int positionOfLineType) {
		this.positionOfLineType = positionOfLineType;
	}


	public int getSizeOfLineType() {
		return sizeOfLineType;
	}


	public void setSizeOfLineType(int sizeOfLineType) {
		this.sizeOfLineType = sizeOfLineType;
	}
	
}
