package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

public class displayMazeWIndow extends DialogWindow {

	@Override
	protected void initWidgets() {
	
			shell.setText("Display maze");
			shell.setSize(320, 160);

			GridLayout layout = new GridLayout(2, false);
			shell.setLayout(layout);
			
			//Buttons to insert the data
			Label lblName = new Label(shell, SWT.NONE);
			lblName.setText("Enter the name of the maze: ");
			Text txtName = new Text(shell, SWT.BORDER);
			
			// the generate button
			Button btnDisplay = new Button(shell, SWT.PUSH);
			btnDisplay.setText("Display");
			btnDisplay.addSelectionListener(new SelectionListener() {
			
				
				//sending the generate maze command with the data to the presenter
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {

					String name= txtName.getText();
					setChanged();
					notifyObservers("display_maze "+name);

					shell.close();
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {			
					
				}
			});	
			
		}
	}


