package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
	static Properties property;
	FileInputStream fis;

	public ReadConfig() throws IOException {
		File src = new File(("./src/test/resources/config.properties"));
		fis = new FileInputStream(src);
		property = new Properties();
		property.load(fis);

	}

	public static String getBaseUrl() {
		String url = property.getProperty("baseUrl");
		return url;
	}
	public String getBrowser() {
		String url = property.getProperty("Browser");
		return url;
	}
}
