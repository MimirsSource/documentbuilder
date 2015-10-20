package de.mimirssource.tk.documentbuilder.core.io;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ReturnValueOutputProvider extends StringResultOutputProvider {
	
	private List<String> resultDocumentList = new ArrayList<String>();

	public List<String> getResultDocumentList() {
		return resultDocumentList;
	}

	@Override
	public void handleFinaleActions(Writer writer) {
		try {
			writer.flush();
			if(writer instanceof StringWriter) {
				String result = ((StringWriter) writer).toString();
				this.resultDocumentList.add(result);
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
