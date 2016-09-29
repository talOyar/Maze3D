package view;

import java.util.Observable;


import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
/**
 * <h2>BasicWindow class<h2>
 * 
 * <p> extends Observable and implements Runnable
 * <p>this class defines the main event loop and run the graphic user interface as long as the main shell is not disposed 
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
public abstract class BasicWindow extends Observable implements Runnable {
	
	protected Display display;
	protected Shell shell;
	
	public BasicWindow() {
	display= new Display();
	shell=new Shell();
	}

	
	abstract void initWidgets();
	
	@Override
	public void run() {
		initWidgets();
		shell.open();
		
		// main event loop
		 while(!shell.isDisposed()){ // while window isn't closed
			 
		    // 1. read events, put then in a queue.
		    // 2. dispatch the assigned listener
		    if(!display.readAndDispatch()){ 	// if the queue is empty
		       display.sleep(); 			// sleep until an event occurs 
		    }

		 } // shell is disposed

		 display.dispose(); // dispose OS components

	}

}
