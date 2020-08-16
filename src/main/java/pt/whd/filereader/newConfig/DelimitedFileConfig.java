package pt.whd.filereader.newConfig;

import java.util.List;

/**
 * Class where the configuration of each type of line of the file 
 * @author whneto
 * 
 */
public class DelimitedFileConfig extends FileConfig{

	private String delimitedBy;
	
	private DelimitedFileConfig(Builder builder) {
		this.positionOfLineType = builder.positionOfLineType;
		this.delimitedBy = builder.delimitedBy;
		this.lines = builder.lines;
	}

	public String getDelimitedBy() {
		return delimitedBy;
	}
	

	@Override
	public String toString() {
		return "DelimitedFileConfig [delimitedBy=" + delimitedBy + ", \nlines=" + lines + ", positionOfLineType="
				+ positionOfLineType + "]";
	}


	public static class Builder {
		private int positionOfLineType;
		private String delimitedBy;
		List<LineConfig> lines;

		/*
		* @param positionOfLineType  - Position where the string that identify the type of line ( header, body...) begins 
		*/
		public Builder positionOfLineType(int positionOfLineType) {
			this.positionOfLineType = positionOfLineType;
			return this;
		}

		/*
		* @param sizeOfLineType  - Size if the string that that identify the type of line 
		*/
		public Builder delimitedBy(String delimitedBy) {
			this.delimitedBy = delimitedBy;
			return this;
		}

		public Builder lines(List<LineConfig> lines) {
			this.lines = lines;
			return this;
		}

		public DelimitedFileConfig build() {
			return new DelimitedFileConfig(this);
		}

	}	
}
