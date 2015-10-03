package de.mimirssource.tk.documentbuilder.core.builder.velocity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import de.mimirssource.tk.documentbuilder.core.builder.TemplateEvaluator;

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
	public void evaluateTemplate(final InputStream input, final OutputStream output, final Object dataObject) {
		VelocityEngine ve = new VelocityEngine();
		ve.init();
		VelocityContext context = new VelocityContext();
		context.put("data", dataObject);
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(output);
		ve.evaluate(context, outputStreamWriter, this.getClass().getName(), new InputStreamReader(input));
		try {
			outputStreamWriter.flush();
			outputStreamWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
