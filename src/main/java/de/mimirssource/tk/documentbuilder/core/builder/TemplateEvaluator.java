package de.mimirssource.tk.documentbuilder.core.builder;

import java.io.Reader;
import java.io.Writer;

import de.mimirssource.tk.documentbuilder.exceptions.TemplateEvaluationException;

/**
 * The TemplateEvaluator is responsible to parse a template and generate the output of one file by merging the template with the provided data.
 * 
 * @author thomas.kloppe
 *
 */
public interface TemplateEvaluator {

	/**
	 * @param reader -  a reader providing the content of the template
	 * @param writer - a writer to write the resulting document to
	 * @param dataObject - a POJO data object containing the information to merge into the template.
	 * @throws TemplateEvaluationException 
	 */
	void evaluateTemplate(Reader reader, Writer writer, final Object dataObject) throws TemplateEvaluationException;

}
