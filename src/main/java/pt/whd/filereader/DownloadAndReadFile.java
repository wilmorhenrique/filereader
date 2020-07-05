

package pt.whd.filereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import pt.whd.filereader.config.Field;
import pt.whd.filereader.config.FileConfig;
import pt.whd.filereader.config.Line;
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
		
		// create a configurator to read and extrac values from the file
		FileConfig config = configFile();
		
		
		// get the downloaded and unziped file
 		File file = new File(myFile.getPath() + myFile.getFullName()); 

		BufferedReader br = new BufferedReader(new FileReader(file)); 

		String fileLine; 
		List<Line> linesProcessed = new ArrayList<Line>();
		while ((fileLine = br.readLine()) != null) {
			
			Line line;
			try {
				line = config.getLineConfigByType(fileLine);
				Line newLine = line.processLine(fileLine); 
				linesProcessed.add(newLine);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}	

//		linesProcessed is a array with processed lines and each line have a list of fields that have the values extracted by configuration   
		
		br.close();

	}

	private static FileConfig configFile() {
		FileConfig config = new FileConfig(0, 0);
		
		Field campo1 = new Field("primeiroCampo", "String", 10, 0);
		Field restoDaLinha = new Field("restodalinha", "String", 1000, 10);
		List<Field> fields = new ArrayList<Field>();
		fields.add(campo1);
		fields.add(restoDaLinha);
		config.addLine("", new Line(fields));
		return config;
	}

	
}
