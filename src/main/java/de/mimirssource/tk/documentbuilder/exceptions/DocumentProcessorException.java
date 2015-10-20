package de.mimirssource.tk.documentbuilder.exceptions;

public class DocumentProcessorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DocumentProcessorException(final String message) {
		super(message);
	}
	
	public DocumentProcessorException(final String message, Throwable e) {
		super(message, e);
	}

}
