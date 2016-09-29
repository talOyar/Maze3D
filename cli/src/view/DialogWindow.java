package view;
import java.util.Observable;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
/**
 * <h2>DialogWindow class<h2>
 * <p> extends Observable
 * <p>this is an abstract class for a dialog window
 * 
 * 
 * @author Tal Oyar & Tomer Cohen
 * @version 1.0
 * @since 20-09-2016
 * 
 * 
 * @see Observable
 *
 */
public abstract class DialogWindow extends Observable {
	
	protected Shell shell;	
	
	protected abstract void initWidgets();
	
	public void start(Display display) {		
		shell = new Shell(display);
		initWidgets();
		shell.open();		
	}
}
