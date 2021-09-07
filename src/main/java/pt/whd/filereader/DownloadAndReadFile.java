

package pt.whd.filereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import pt.whd.filereader.download.GetRemoteFile;
import pt.whd.filereader.download.MyFile;
import pt.whd.filereader.download.UnzipFile;

public class DownloadAndReadFile {

	public static void main(String[] args) throws Exception {
		MyFile myFile = new MyFile("http://www1.caixa.gov.br/loterias/_arquivos/loterias/D_megase.zip", "src/main/resources/" ,"batata.zip");
		GetRemoteFile rmt = new GetRemoteFile(myFile);
		
		// proceed download of the file and save it on the specified path 
		rmt.downloadFile();

		// unzip the downloaded file	
		UnzipFile.unzip(myFile.getPath(), myFile.getFullName());
		
		// get the downloaded and unziped file
 		File file = new File(myFile.getPath() + myFile.getFullName()); 

		BufferedReader br = new BufferedReader(new FileReader(file)); 

		String fileLine; 
		while ((fileLine = br.readLine()) != null) {
			try {
				System.out.println(fileLine);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}	

//		linesProcessed is a array with processed lines and each line have a list of fields that have the values extracted by configuration   
		
		br.close();

	}
	
}
