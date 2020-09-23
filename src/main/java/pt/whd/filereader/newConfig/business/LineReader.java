package pt.whd.filereader.newConfig.business;

import java.util.List;
import java.util.Map;

public interface LineReader {

	public void process();
	
	public Map<Integer, List<Field>> getExtractedLines();
}
