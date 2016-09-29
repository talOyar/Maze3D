package view;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
/**
 * <h2>GenerateMazeWindow class<h2>
 * <p> extends DialogWindow
 * <p> opens a generate maze window when the generate maze button is pushed
 * 
 * 
 * @author Tal Oyar & Tomer Cohen
 * @version 1.0
 * @since 20-09-2016
 * 
 * 
 * @see DialogWindow
 *
 */
public class GenerateMazeWindow extends DialogWindow {
	
	
	
	@Override
	protected void initWidgets() {
		shell.setText("Generate Maze");
		shell.setSize(300, 250);

		GridLayout layout = new GridLayout(2, false);
		shell.setLayout(layout);
		
		//Buttons to insert the data
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setText("Name of the maze: ");
		Text txtName = new Text(shell, SWT.BORDER);
		
		Label lblRows = new Label(shell, SWT.NONE);
		lblRows.setText("Rows: ");
		Text txtRows = new Text(shell, SWT.BORDER);
		
		Label lblCols = new Label(shell, SWT.NONE);
		lblCols.setText("Cols: ");
		Text txtCols = new Text(shell, SWT.BORDER);
		
		Label lblLevels = new Label(shell, SWT.NONE);
		lblLevels.setText("Levels: ");
		Text txtLevels = new Text(shell, SWT.BORDER);
		
		// the generate button
		
		Button btnGenerate = new Button(shell, SWT.PUSH);
		btnGenerate.setText("Generate");
		btnGenerate.addSelectionListener(new SelectionListener() {
		
			
			//sending the generate maze command with the data to the presenter
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				MessageBox msg = new MessageBox(shell, SWT.OK);
				msg.setText("Generate maze");
				try {
					String name=txtName.getText();
					int cols = Integer.parseInt(txtCols.getText());
					int levels=Integer.parseInt(txtLevels.getText());
					int rows = Integer.parseInt(txtRows.getText());

					setChanged();
					notifyObservers("generate_maze "+name+ " "+levels+" "+rows + " " + cols);

				} catch (Exception e) {
					setChanged();
					notifyObservers("display_message Wrong Input,Please try again!");
				}
				finally{
				shell.close();}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {			
				
			}
		});	
		
	}

}
