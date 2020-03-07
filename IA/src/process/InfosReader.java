package process;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InfosReader {

	public static String ReadInfos(String name) {
		String info="";
		try {
			BufferedReader file = new BufferedReader(new FileReader(name));	
			String str;
			while ((str = file.readLine()) != null) {
				info+=str +"\n";
			}
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;
	}
	
}
