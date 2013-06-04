package gov.nih.nci.camod.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
	
	public static String getProperty(String property) {
		//Get external properties file
		Properties camodProperties = new Properties();
		String camodPropertiesFileName = null;

		camodPropertiesFileName = System.getProperty("gov.nih.nci.camod.camodProperties");
		
		try {
		
			FileInputStream in = new FileInputStream(camodPropertiesFileName);
			camodProperties.load(in);
			
			return camodProperties.getProperty(property);
	
		} 
		catch (FileNotFoundException e) {
			//log.error("Caught exception finding file for properties: ", e);
			e.printStackTrace();			
		} catch (IOException e) {
			//log.error("Caught exception finding file for properties: ", e);
			e.printStackTrace();			
		}
		
		return null;
	}

}
