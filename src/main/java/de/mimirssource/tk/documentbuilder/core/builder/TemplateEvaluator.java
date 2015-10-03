package de.mimirssource.tk.documentbuilder.core.builder;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * The TemplateEvaluator is responsible to parse a template and generate the output of one file by merging the template with the provided data.
 * 
 * @author thomas.kloppe
 *
 */
public interface TemplateEvaluator {
	
	/**
	 * 
	 * @param input - an input stream providing the content of the template
	 * @param output - the output stream to write the resulting document to
	 * @param dataObject - a POJO data object containing the information to merge into the template.
	 */
	void evaluateTemplate(final InputStream input, final OutputStream output, final Object dataObject);

}
