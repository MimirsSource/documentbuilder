package de.mimirssource.tk.documentbuilder.exceptions;

public class TemplateEvaluationException extends DocumentProcessorException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TemplateEvaluationException(final String message) {
		super(message);
	}
	
	public TemplateEvaluationException(final String message, Throwable e) {
		super(message, e);
	}

}
