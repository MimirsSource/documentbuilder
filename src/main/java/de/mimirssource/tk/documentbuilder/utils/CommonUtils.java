package de.mimirssource.tk.documentbuilder.utils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A collection of common helper methods.
 * 
 * @author thomas.kloppe
 *
 */
public final class CommonUtils {
	
	/**
	 * Transfers a string containing a comma separated list to a collection.
	 * After separating the elements of the string each substring is trimmed. 
	 * 
	 * @param commaSeparated - a string containing comma separated values
	 * @return a collection
	 */
	public static Collection<String> collectionFromCommaSeparatedList(final String commaSeparated) {
		Collection<String> resultList = new ArrayList<String>();
		String[] elements = commaSeparated.split(",");
		for( String element : elements ) {
			resultList.add(element.trim());
		}
		return resultList;
	}

}
