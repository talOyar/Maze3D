package view;

import java.io.File;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import presenter.Command;
import presenter.Presenter;


public class MazeWindow extends BasicWindow implements View {

	/*************************DATA MEMBERS********************/
		
	private MazeDisplay mazeDisplay;
    int Qstyle = SWT.APPLICATION_MODAL | SWT.YES | SWT.NO;
    Maze3d maze;
    
    Listener exitListener=new Listener() {    // exit handler
		
    	
		@Override
		public void handleEvent(Event arg0) {

            MessageBox messageBox = new MessageBox(shell, Qstyle);
            messageBox.setText("Exit");
            messageBox.setMessage("Are you sure?");
            if(messageBox.open()==SWT.YES){
			setChanged();
			notifyObservers("exit");
			shell.dispose();	}
		}
	};
    
    
    
	
	@Override
	protected void initWidgets() {
		
		GridLayout gridLayout = new GridLayout(2, false);
		shell.setLayout(gridLayout);
		
		//******************red X- closes the program************************
		
		 shell.addListener(SWT.Close,exitListener);
		  
			 
		// ***********buttons**********
		
		
		// button generate maze
		
		 
			Button btnGenerateMaze = new Button(shell, SWT.PUSH);
			btnGenerateMaze.setLayoutData(new GridData(SWT.FILL,SWT.NONE, false,false,1, 1));

			btnGenerateMaze.setText("Generate maze");	
			btnGenerateMaze.setToolTipText("Click to generate the maze");

		
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

		
		mazeDisplay=new MazeDisplay(shell, SWT.BORDER);
		mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 5));
		
		//Text t=new Text(shell, SWT.BORDER);
		//t.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 5));

		
		
		// display maze button

		Button btnDisplayMaze = new Button(shell, SWT.PUSH);
		btnDisplayMaze.setLayoutData(new GridData(SWT.FILL,SWT.NONE, false,false,1, 1));
		btnDisplayMaze.setText("Display maze");
		btnDisplayMaze.setToolTipText("Click to display the maze");
		
		
		btnDisplayMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				setChanged();
				notifyObservers("display_maze");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		//get an hint button
		
		Button btnHintMaze = new Button(shell, SWT.PUSH|SWT.FILL);
		btnHintMaze.setLayoutData(new GridData(SWT.FILL,SWT.NONE, false,false,1, 1));
		
		btnHintMaze.setText("Give me an hint");
		btnHintMaze.setToolTipText("Click to get an hint");

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
		
		
		
		
		//******************* solve maze button************************/
		
		Button btnSolveMaze = new Button(shell, SWT.PUSH|SWT.FILL);
		btnSolveMaze.setLayoutData(new GridData(SWT.FILL,SWT.NONE, false,false,1, 1));

		btnSolveMaze.setText("Solve maze");
		btnSolveMaze.setToolTipText("Click to solve the maze");

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
		
		
		//**************exit button****************/
		
		//Button btnExit = new Button(btnGroup, SWT.PUSH|SWT.FILL);
		Button btnExit = new Button(shell, SWT.PUSH|SWT.FILL);
		btnExit.setLayoutData(new GridData(SWT.FILL,SWT.NONE, false,false,1, 1));

		btnExit.setText("Exit");
		btnExit.setToolTipText("Click to exit");

		btnExit.addListener(SWT.Selection,exitListener);

		
		
		
		//create the general bar menu
		
		Menu barMenu = new Menu(shell, SWT.BAR);
		
		// sub bar menus 'file' menu and 'help' menu
		Menu fileMenu = new Menu(barMenu);
		
		
		// creating the file menu
		MenuItem fileItem = new MenuItem(barMenu, SWT.CASCADE);
		fileItem.setText("File");
		fileItem.setMenu(fileMenu);
		
		MenuItem loadPropertiesItem=new MenuItem(fileMenu,SWT.CASCADE);
		loadPropertiesItem.setText("Open properties");
		
		loadPropertiesItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			FileDialog fd=new FileDialog(shell, SWT.BORDER);
			fd.setText("open");
					setChanged();
					notifyObservers("load_xml");
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		MenuItem exitItem= new MenuItem(fileMenu,SWT.BORDER);
		exitItem.setText("Exit");
		exitItem.addListener(SWT.Selection,exitListener);

		
		
		// creating the help menu
		Menu helpMenu = new Menu(barMenu);
		
		MenuItem helpItem= new MenuItem(barMenu, SWT.CASCADE);
		helpItem.setText("Help");
		helpItem.setMenu(helpMenu);
		MenuItem aboutItem=new MenuItem(helpMenu, SWT.CASCADE);
		aboutItem.setText("About");
		aboutItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				MessageBox msg=new MessageBox(shell);
				msg.setText("About");
				msg.setMessage("*************************************************************\n"+
				"This game is a 3 dimentions maze game developed by Tal Oyar & Tomer Cohen TMNT style!\n  "
				+"Enjoy!\n"+"*************************************************************");
				//Developed
			msg.open();	
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		
		//adding the bar menu to the shell
		shell.setMenuBar(barMenu);
		
	}
	

	
	protected void showGenerateMazeOptions() {
		
		//generate maze window- insert name for the maze and number of rows, cols and levels.
		
		
		Shell shell = new Shell();// new window for generate maze
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
				setChanged();
				notifyObservers("generate_maze " +txtName.getText()+" "+txtLevels.getText()+" "+ txtRows.getText() + " " + txtCols.getText());
				setChanged();
				
				notifyObservers("display_maze "+txtName.getText());
				shell.close();
				
				
			}
			
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		//creating a maze display window
		
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
		
		mazeDisplay.setMaze(maze);
		mazeDisplay.redraw();
		
	}

}
