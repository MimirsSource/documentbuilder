package de.mimirssource.tk.documentbuilder.dataprovider;

import java.net.URI;
import java.util.Map;

/**
 * The DataProvider parses a location given by an URI for data to merge into documents.
 * 
 * @author thomas.kloppe
 *
 */
public interface DataProvider {
	
	/**
	 * 
	 * @param location - an URI providing the entry point where to look for data objects
	 * 
	 * @return a map containing content data. The key is a unique name tag for each data set.
	 */
	Map<String,Object> obtainData(final URI location);

}
