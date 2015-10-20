package de.mimirssource.tk.documentbuilder.core.builder;

import java.io.Reader;
import java.io.Writer;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import de.mimirssource.tk.documentbuilder.exceptions.TemplateEvaluationException;

/**
 * This is a velocity based implementation of
 * {@link de.mimirssource.tk.documentbuilder.core.builder}
 * 
 * @author thomas.kloppe
 *
 */
public class VelocityTemplateEvaluator implements TemplateEvaluator {
	
	/**
	 * {@inheritDoc}
	 */
	public void evaluateTemplate(final Reader reader, final Writer writer, final Object dataObject) throws TemplateEvaluationException {
		if( reader == null || writer == null || dataObject == null ) {
			throw new TemplateEvaluationException("Null paremeter not permitted: Writer - "+writer+" Reader - "+reader+" Data - "+dataObject);
		} else {
			VelocityEngine ve = new VelocityEngine();
			ve.init();
			VelocityContext context = new VelocityContext();
			context.put("data", dataObject);
			ve.evaluate(context, writer, this.getClass().getName(), reader);
		}
	}

}
