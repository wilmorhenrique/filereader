package pt.whd.filereader.newConfig;

public class FieldConfig {

	private String name;
	private String type;
	private int size;
	private int inicialPos;
	private int position;

	private FieldConfig(Builder builder) {
		this.name = builder.name;
		this.type = builder.type;
		this.size = builder.size;
		this.inicialPos = builder.inicialPos;
		this.position = builder.position;
	}

	@Override
	public String toString() {
		return "\nFieldConfig [name=" + name + ", type=" + type + ", size=" + size + ", inicialPos=" + inicialPos
				+ ", position=" + position + "]";
	}

	public int getFinalPos() {
		return this.inicialPos + this.size;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public int getSize() {
		return size;
	}

	public int getInicialPos() {
		return inicialPos;
	}

	public int getPosition() {
		return position;
	}	

	public static class Builder {
		private String name;
		private String type;
		private int size;
		private int inicialPos;
		private int position;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder type(String type) {
			this.type = type;
			return this;
		}

		public Builder size(int size) {
			this.size = size;
			return this;
		}

		public Builder inicialPos(int inicialPos) {
			this.inicialPos = inicialPos;
			return this;
		}

		public Builder position(int position) {
			this.position = position;
			return this;
		}

		public FieldConfig build() {
			return new FieldConfig(this);
		}



	}



}
