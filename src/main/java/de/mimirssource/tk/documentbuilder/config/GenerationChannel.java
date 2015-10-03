package de.mimirssource.tk.documentbuilder.config;


public final class GenerationChannel {
	
	private final String uniqueName;
	private final String templateFilePath;
	private final String outputDirectoryPath;
	private final String fileExtension;
	
	public GenerationChannel(final String uniqueName, final String templaSFilePath, final String outputDirectoryPath, final String fileExtension ) {
		this.uniqueName = uniqueName;
		this.templateFilePath = templaSFilePath;
		this.outputDirectoryPath = outputDirectoryPath;
		this.fileExtension = fileExtension;
	}
	
	public String getUniqueName() {
		return uniqueName;
	}

	public String getTemplateFilePath() {
		return templateFilePath;
	}

	public String getOutputDirectoryPath() {
		return outputDirectoryPath;
	}
	
	public String getFileExtension() {
		return fileExtension;
	}

}
