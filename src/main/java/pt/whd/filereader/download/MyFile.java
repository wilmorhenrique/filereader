package pt.whd.filereader.download;

public class MyFile {
	// just a comment from GIT
	private String url;
	private String path;
	private String filename;
	private String extension;
	private String fullName;
	private String content;
	private Long fileSize;

	/**
	 * 
	 * @param url - URL of the file will be downloaded 
	 * @param path - Path where the file will be saved and unziped if necessary
	 * @param fullName 
	 * @throws Exception
	 */
	public MyFile(String url, String path,String fullName) throws Exception {
		this.url = url;
		this.setPath(path);
		this.fullName = fullName;
		extractName();
		extractExtension();
	}
	public MyFile(String path,String fullName) throws Exception {
		this.setPath(path);
		this.fullName = fullName;
		extractName();
		extractExtension();
	}

	
	private void extractExtension() throws Exception {
		this.extension = this.fullName.substring(getDotPosition()+1);
		
	}

	private void extractName() throws Exception {
		this.filename = this.fullName.substring(0, getDotPosition());
	}

	private int getDotPosition() throws Exception {
		int position = fullName.indexOf(".");
		if (position < 0 ) throw new Exception("Invalid name");
		return position;
	}

	public String getFilename() {
		return filename;
	}
	public String getExtension() {
		return extension;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	

}
