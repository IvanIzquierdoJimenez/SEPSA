package dmi;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Archivo {
	
	public String[] ReadConfig(String path)
	{
		
		String[] LineRead = new String[24];
		String LineArchiveRead;
		int index = 0;
		try {
			BufferedReader bf = new BufferedReader(new FileReader(path));
			while ((LineArchiveRead = bf.readLine())!=null) 
			{
				LineRead[index] = LineArchiveRead;
				index += 1;
			}
			bf.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return LineRead;
	}
	
	
}
