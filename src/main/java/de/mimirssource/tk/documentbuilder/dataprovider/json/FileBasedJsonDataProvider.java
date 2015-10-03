package de.mimirssource.tk.documentbuilder.dataprovider.json;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

import de.mimirssource.tk.documentbuilder.dataprovider.DataProvider;

/**
 * This implementation of {@link de.mimirssource.tk.documentbuilder.dataprovider.DataProvider} works on local files and
 * expects a json data structure to parse inside the files.
 * 
 * @author thomas.kloppe
 *
 */
public final class FileBasedJsonDataProvider implements DataProvider {
	
	Logger logger = Logger.getRootLogger();
	
	private static final String DEFAULT_ALLOWED_EXTENSION = "json";
	
	/**
	 * {@inheritDoc}
	 */
	public Map<String, Object> obtainData(URI location) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(location != null) {
			File file = new File(location);
			if( file.isDirectory() ) {
				File[] fileList = file.listFiles();
				for( File currentFile : fileList ) {
					addJson(result, currentFile);
				}
			} else {
				addJson(result, file);
			}
		}
		return result;
	}

	private void addJson(Map<String, Object> result, File currentFile) {
		JsonNode jsonNode = readFile(currentFile);
		result.put(FilenameUtils.getBaseName(currentFile.getName()),jsonNode);
	}

	private JsonNode readFile(File file) {
		JsonNode jsonNode = null;
		if( file.exists() && FilenameUtils.getExtension(file.getName()).equalsIgnoreCase(DEFAULT_ALLOWED_EXTENSION) ) {
			ObjectMapper mapper = new ObjectMapper();
			JsonFactory factory = mapper.getJsonFactory();
			try {
				JsonParser jsonParser = factory.createJsonParser(file);
				jsonNode = jsonParser.readValueAsTree();
			} catch (IOException e ) {
				logger.error("Could not parse data from json file "+file.getName()+":"+e.getMessage(), e);
				throw new IllegalStateException("Could not parse data from json file "+file.getName()+":"+e.getMessage(), e);
			} 
		}
		return jsonNode;
	}

}
