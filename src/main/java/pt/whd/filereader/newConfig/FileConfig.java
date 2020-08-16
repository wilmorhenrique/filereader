package pt.whd.filereader.newConfig;

import java.util.List;

/**
 * Class where the configuration of each type of line of the file 
 * @author whneto
 * 
 */
public abstract class FileConfig {

	protected List<LineConfig> lines;
	protected int positionOfLineType;

	public List<LineConfig> getLines() {
		return lines;
	}
	
	public int getPositionOfLineType() {
		return positionOfLineType;
	}
	
}
