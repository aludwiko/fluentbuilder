/* 
 * Created on 10-03-2013 11:16:45 by Andrzej Ludwikowski
 */

package info.ludwikowski.processor;

import java.io.Writer;
import java.util.List;


public class AbstractBuilderPrinter extends ClassPrinter {

	public AbstractBuilderPrinter(Writer writer) {
		super(writer);
		// TODO Auto-generated constructor stub
	}


	private ProcessorContext processorContext;


	@Override
	public void printClassWithBody() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getFullClassNamesForImports() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPackageName() {
		// TODO Auto-generated method stub
		return null;
	}

}
