package pt.whd.filereader.newConfig;

import java.util.List;

/**
 * Class where the configuration of each type of line of the file 
 * @author whneto
 * 
 */
public class FixedLayoutFileConfig extends FileConfig{

	private int sizeOfLineType;

	private FixedLayoutFileConfig(Builder builder) {
		this.positionOfLineType = builder.positionOfLineType;
		this.sizeOfLineType = builder.sizeOfLineType;
		this.lines = builder.lines;
	}

	public int getSizeOfLineType() {
		return sizeOfLineType;
	}


	
	@Override
	public String toString() {
		return "FixedLayoutFileConfig [sizeOfLineType=" + sizeOfLineType + ", \nlines=" + lines + ", positionOfLineType="
				+ positionOfLineType + "]";
	}



	public static class Builder {
		private int positionOfLineType;
		private int sizeOfLineType;
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
		public Builder sizeOfLineType(int sizeOfLineType) {
			this.sizeOfLineType = sizeOfLineType;
			return this;
		}

		public Builder lines(List<LineConfig> lines) {
			this.lines = lines;
			return this;
		}

		public FixedLayoutFileConfig build() {
			return new FixedLayoutFileConfig(this);
		}

		
	}
}
