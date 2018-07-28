package by.grsu.edu.store.utile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesWorker {
	private Properties prop;
	
	public PropertiesWorker(String path) throws FileNotFoundException, IOException {
		prop = new Properties();
		try(FileInputStream stream = new FileInputStream(path)){
			prop.load(stream);
		} 
	}

	public String getProperty(String key) {
		return prop.getProperty(key);
	}
}
