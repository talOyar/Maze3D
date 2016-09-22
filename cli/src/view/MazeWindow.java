package view;

import java.io.File;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Command;
import presenter.Presenter;


public class MazeWindow extends BasicWindow implements View {

	private MazeDisplay mazeDisplay;
	
	@Override
	protected void initWidgets() {
		GridLayout gridLayout = new GridLayout(2, false);
		shell.setLayout(gridLayout);				
		
		// ***********buttons**********
		
		
//		//create the buttons group layout
//		Composite btnGroup = new Composite(shell, SWT.BORDER);
//		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
//		btnGroup.setLayout(rowLayout);
//		
		
		
		
		// button generate maze
		
		Button btnGenerateMaze = new Button(shell, SWT.PUSH);
		btnGenerateMaze.setText("Generate maze");	
		btnGenerateMaze.setToolTipText("Click to generate the maze");
		btnGenerateMaze.setLayoutData(new GridData(SWT.FILL,SWT.NONE, false, false, 1, 1));
		
		btnGenerateMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				showGenerateMazeOptions();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		Text t = new Text(shell, SWT.SINGLE | SWT.LEAD | SWT.BORDER);
		t.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true,1,2));
		t.setText("maze display");
		
		
		// display maze button
		Button btnDisplayMaze = new Button(shell, SWT.PUSH);
		btnDisplayMaze.setText("Display maze");
		btnDisplayMaze.setToolTipText("Click to display the maze");
		btnDisplayMaze.setLayoutData(new GridData(SWT.FILL,SWT.NONE, false, false, 1, 1));

		btnDisplayMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		//get an hint button
		Button btnHintMaze = new Button(shell, SWT.PUSH|SWT.FILL);
		btnHintMaze.setText("Give me an hint");
		btnHintMaze.setToolTipText("Click to get an hint");
		btnDisplayMaze.setLayoutData(new GridData(SWT.FILL,SWT.NONE, false, false, 1, 1));

		btnHintMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		// solve maze button
		Button btnSolveMaze = new Button(shell, SWT.PUSH|SWT.FILL);
		btnSolveMaze.setText("Solve maze");
		btnSolveMaze.setToolTipText("Click to solve the maze");
		btnDisplayMaze.setLayoutData(new GridData(SWT.FILL,SWT.NONE, false, false, 1, 1));

		btnSolveMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button btnExit = new Button(shell, SWT.PUSH|SWT.FILL);
		btnExit.setText("Exit");
		btnExit.setToolTipText("Click to exit");
		btnDisplayMaze.setLayoutData(new GridData(SWT.FILL,SWT.NONE, false, false, 1, 1));

		btnExit.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		
		// bar menu
		
		
		
		
	}

	protected void showGenerateMazeOptions() {
		//generate maze window- insert name for the maze and number of rows, cols and levels.
		
		
		Shell shell = new Shell();// new window for generate maze
		shell.setText("Generate Maze");
		shell.setSize(300, 200);
		
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
				setChanged();
				notifyObservers("generate_maze " +txtName.getText()+" "+txtLevels.getText()+" "+ txtRows.getText() + " " + txtCols.getText());
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		//creating a maze display window
		
		mazeDisplay = new MazeDisplay(shell, SWT.NONE);			
		shell.open();		
	}

	
	@Override
	public void notifyMazeIsReady(String name) {
		
		display.syncExec(new Runnable() {
			
			@Override
			public void run() {
				MessageBox msg = new MessageBox(shell);
				msg.setMessage("Maze " + name + " is ready");
				msg.open();	
				
				setChanged();
				notifyObservers("display_maze " + name);
			}
		});			
	}


	
	@Override
	public void displayMessage(String msg) {
		
		display.syncExec(new Runnable() {
			
			@Override
			public void run() {
				MessageBox message = new MessageBox(shell);
				message.setMessage(msg);
				message.open();	

			}
		});				
	}

	
	@Override
	public void start() {
		run();		
	}


	
	@Override
	public void setCommands(HashMap<String, Command> commands) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayCrossSection(int[][] maze2d) {
		
		int[][] mazeData={
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,0,0,0,0,0,0,0,1,1,0,1,0,0,1},
				{0,0,1,1,1,1,1,0,0,1,0,1,0,1,1},
				{1,1,1,0,0,0,1,0,1,1,0,1,0,0,1},
				{1,0,1,0,1,1,1,0,0,0,0,1,1,0,1},
				{1,1,0,0,0,1,0,0,1,1,1,1,0,0,1},
				{1,0,0,1,0,0,1,0,0,0,0,1,0,1,1},
				{1,0,1,1,0,1,1,0,1,1,0,0,0,1,1},
				{1,0,0,0,0,0,0,0,0,1,0,1,0,0,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,0,1,1},
			};
		mazeDisplay.setMazeData(mazeData);		
	}

	@Override
	public void displayFolders(File[] path) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifySolutionIsReady(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMazeSolution(Solution<Position> solution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPresenter(Presenter presenter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayMaze(Maze3d maze) {
		// TODO Auto-generated method stub
		
	}

}
