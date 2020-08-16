package pt.whd.filereader.newConfig;

import java.util.List;

public class LineConfig {

	private String type;
	List<FieldConfig> fields;

	public String getType() {
		return type;
	}

	public List<FieldConfig> getFields() {
		return fields;
	}
	
	private LineConfig(Builder builder) {
		this.type = builder.type;
		this.fields = builder.fields;
	}

	@Override
	public String toString() {
		return "\nLineConfig [type=" + type + ", fields=" + fields + "]";
	}

	public static class Builder {
		private String type;
		List<FieldConfig> fields;

		
		public Builder type(String type) {
			this.type = type;
			return this;
		}

		public Builder fields(List<FieldConfig> fields) {
			this.fields = fields;
			return this;
		}
		
		public LineConfig build() {
			return new LineConfig(this);
		}
		
	}

}
