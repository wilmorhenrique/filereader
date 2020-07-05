package pt.whd.filereader.download;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class GetRemoteFile {

	private String fileURL ;
	private MyFile file = null;
	
	public GetRemoteFile(MyFile file) {
		super();
		this.file = file;
		this.fileURL = file.getUrl()  ;
		
	}
	
	public void downloadFile() throws Exception {
		java.net.CookieManager cm = new java.net.CookieManager();
		java.net.CookieHandler.setDefault(cm);
		URL url = new URL(fileURL);

		ReadableByteChannel rbc = Channels.newChannel(url.openStream());
		FileOutputStream fos = new FileOutputStream(file.getPath() + file.getFullName());
		file.setFileSize(fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE));
		fos.close();
	}
	
}