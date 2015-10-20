package de.mimirssource.tk.documentbuilder.core.io;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import de.mimirssource.tk.documentbuilder.core.processor.streams.callback.OutputListener;

public class CallbackOutputProvider extends StringResultOutputProvider {
	
	
	private final List<OutputListener> outputListenerList = new ArrayList<OutputListener>();

	@Override
	public void handleFinaleActions(Writer writer) {
		try {
			writer.flush();
			if(writer instanceof StringWriter) {
				String result = ((StringWriter) writer).toString();
				for(OutputListener outputListener : this.outputListenerList ) {
					outputListener.readNextOutput(result);
				}
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addOutputListener(final OutputListener outputListener) {
		this.outputListenerList.add(outputListener);
	}

}
