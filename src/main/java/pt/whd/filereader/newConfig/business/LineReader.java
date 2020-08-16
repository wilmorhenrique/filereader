package pt.whd.filereader.newConfig.business;

import java.util.List;

public interface LineReader {

	public void process();
	
	public List<Field> getReadFields();
}
