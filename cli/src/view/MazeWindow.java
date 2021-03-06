package view;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Event;
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
import algorithms.search.State;
import presenter.Command;
import presenter.Properties;


/**
 * <h2>MazeWindow class<h2>
 * <p> extends basic window and implements view and java observer interface
 * <p>This is the general maze window which contains all the widgets
 * 
 * @author Tal Oyar & Tomer Cohen
 * @version 1.0
 * @since  09-20-2016
 * 
 * @see view
 * @see observer
 * @see basic window
 *
 */

public class MazeWindow extends BasicWindow implements View,Observer {

	/*************************DATA MEMBERS********************/
		
	private MazeDisplay mazeDisplay;
    int Qstyle = SWT.APPLICATION_MODAL | SWT.YES | SWT.NO;
    Maze3d maze;
    String mazeName;
	Solution<Position> solution;
	Boolean wantHint=false;
	Properties properties;
	Label floorLable;
	Combo mazeList;
	Clip Playmusic;
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
    
    
    /**
     * <p>initWidgets class
     * <p> overrides basicWindow method
     * <p> initialize all the widgets of the game window
     * 
     */
	
	@Override
	protected void initWidgets() {
		
		
		// set grid layout
		GridLayout gridLayout = new GridLayout(2, false);
		shell.setLayout(gridLayout);
		
		
		
		//*******************************red X- closes the program********************************
		
		 shell.addListener(SWT.Close,exitListener);
		 
			 
		 
		//****************************buttons********************************
		
		
		 
		 
		// button generate maze
		
		 	
			Button btnGenerateMaze = new Button(shell, SWT.PUSH);
			btnGenerateMaze.setLayoutData(new GridData(SWT.FILL,SWT.NONE, false,false,1, 1));

			btnGenerateMaze.setText("Generate maze");	
			btnGenerateMaze.setToolTipText("Click to generate the maze");

			
			mazeDisplay=new MazeDisplay(shell, SWT.BORDER|SWT.DOUBLE_BUFFERED);
			mazeDisplay.setBackground(new Color(null, 255,255,255));
			//mazeDisplay.setBackgroundImage(new Image(null,"images/cover.jpg"));
			mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1,8));
		
		
		
		// display maze button

		Button btnDisplayMaze = new Button(shell, SWT.PUSH);
		btnDisplayMaze.setLayoutData(new GridData(SWT.FILL,SWT.NONE, false,false,1, 1));
		btnDisplayMaze.setText("Display maze");
		btnDisplayMaze.setToolTipText("Pick a maze from the list then press to display it!");
		//btnDisplayMaze.setEnabled(false);
		
		// maze list to display
		
		mazeList=new Combo(shell,SWT.DROP_DOWN);
		btnDisplayMaze.setLayoutData(new GridData(SWT.FILL,SWT.NONE, false,false,1, 1));
		mazeList.setToolTipText("Press to see all your mazes!");
		
		
		//get an hint button
		
		Button btnHintMaze = new Button(shell, SWT.PUSH|SWT.FILL);
		btnHintMaze.setLayoutData(new GridData(SWT.FILL,SWT.NONE, false,false,1, 1));
		
		btnHintMaze.setText("Give me an hint");
		btnHintMaze.setToolTipText("Click to get an hint");
		btnHintMaze.setEnabled(false);
		
		
		//solve maze button
		
		Button btnSolveMaze = new Button(shell, SWT.PUSH|SWT.FILL);
		btnSolveMaze.setLayoutData(new GridData(SWT.FILL,SWT.NONE, false,false,1, 1));

		btnSolveMaze.setText("Solve maze");
		btnSolveMaze.setToolTipText("Click to solve the maze");
		btnSolveMaze.setEnabled(false);
		
		// music button
		Button btnMusic = new Button(shell, SWT.PUSH|SWT.FILL);
		btnMusic.setLayoutData(new GridData(SWT.FILL,SWT.NONE, false,false,1, 1));
		btnMusic.setText("Play Music");
		btnMusic.setToolTipText("Click to play some music :)");
		
		//button stop music
		Button btnStopMusic = new Button(shell, SWT.PUSH|SWT.FILL);
		btnStopMusic.setLayoutData(new GridData(SWT.FILL,SWT.NONE, false,false,1, 1));
		btnStopMusic.setText("Stop music");
		btnStopMusic.setToolTipText("Click to stop the music");
		
		
		//exit button
		
		Button btnExit = new Button(shell, SWT.PUSH|SWT.FILL);
		btnExit.setLayoutData(new GridData(SWT.FILL,SWT.NONE, false,false,1, 1));

		btnExit.setText("Exit");
		btnExit.setToolTipText("Click to exit");
		
		// show which floor the player 
		floorLable= new Label(shell, SWT.BORDER);
		floorLable.setText("You are at floor:"+mazeDisplay.currentLevel);


		
	
		
		//*************************buttons listeners***************************//
	
		
		
		// generate maze listener
		GenerateMazeWindow generate = new GenerateMazeWindow();	
		generate.addObserver(this);

		btnGenerateMaze.addSelectionListener(new SelectionListener() {
			
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			generate.start(display);
						
		}
		
		@Override
		public void widgetDefaultSelected(SelectionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	});
		
		
		btnDisplayMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				System.out.println(mazeList.getText());
				
				if(mazeList.getText().equals(""))
				{
					setChanged();
					notifyObservers("display_message You need to choose a maze from the list first!");
				}
				
				else{
					
				setChanged();
				notifyObservers("display_maze "+mazeList.getText());
				btnHintMaze.setEnabled(true);
				btnSolveMaze.setEnabled(true);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

		
		//drop down listener
		
		mazeList.addFocusListener(new FocusListener() {
			
			
			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				getMazeList();
			}
		});
		
		// hint listener
		btnHintMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				wantHint=true;
				
				setChanged();
				notifyObservers("solve_maze "+mazeName+ " byGui "+mazeDisplay.characterPos.x+" "+mazeDisplay.characterPos.y+" "+mazeDisplay.characterPos.z);
								
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			
			}
		});
		
		
		// solve maze listener
		btnSolveMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(maze==null){
				setChanged();
				notifyObservers("display_message Generate/load maze first!");
				
				}
				else {
					
					setChanged();
					notifyObservers("solve_maze "+mazeName+ " byGui "+mazeDisplay.characterPos.x+" "+mazeDisplay.characterPos.y+" "+mazeDisplay.characterPos.z);}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		//music listener
		btnMusic.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				try {
					
					Playmusic = AudioSystem.getClip();
					
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("resources/TeenageMutantNinjaTurtles.wav")));
					Playmusic.open(inputStream);
					
					Playmusic.setLoopPoints(0, -1);
					Playmusic.loop(Clip.LOOP_CONTINUOUSLY);

				} catch (Exception e) {
					e.printStackTrace();
								
			}	
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//music listener

		
		btnStopMusic.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				try {
					Playmusic.stop();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		// exit listener
		btnExit.addListener(SWT.Selection,exitListener);
		
		
		
		
		
		
		//maze display listener for mouse scroll for zoom in/out
		
		mazeDisplay.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseScrolled(MouseEvent mouse) {
				if((mouse.stateMask & SWT.CONTROL) == SWT.CONTROL) {
				mazeDisplay.setSize(mazeDisplay.getSize().x + mouse.count, mazeDisplay.getSize().y + mouse.count);
					
				}
			}
		});
	
		
		//maze display key listener
	
		mazeDisplay.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {

				
				switch (e.keyCode) {
				
				case SWT.ARROW_RIGHT:
					if(mazeDisplay.characterPos.z+1<maze.getCols() && maze.getCellVal(mazeDisplay.characterPos.x,mazeDisplay.characterPos.y,mazeDisplay.characterPos.z+1)==0){
					mazeDisplay.character.moveRight();
					mazeDisplay.redraw();	}

					break;
				
				case SWT.ARROW_LEFT:	
					if(mazeDisplay.characterPos.z-1>0 && maze.getCellVal(mazeDisplay.characterPos.x,mazeDisplay.characterPos.y,mazeDisplay.characterPos.z-1)==0){
					mazeDisplay.character.moveLeft();
					mazeDisplay.redraw();}

					break;
					
				case SWT.ARROW_UP:
					if(mazeDisplay.characterPos.y-1>0 && maze.getCellVal(mazeDisplay.characterPos.x,mazeDisplay.characterPos.y-1,mazeDisplay.characterPos.z)==0){
						mazeDisplay.character.moveForward();
						mazeDisplay.redraw();}

					break;
					
				case SWT.ARROW_DOWN:
					
					if(mazeDisplay.characterPos.y+1<maze.getRows() && maze.getCellVal(mazeDisplay.characterPos.x,mazeDisplay.characterPos.y+1,mazeDisplay.characterPos.z)==0){
						mazeDisplay.character.moveBackward();
						mazeDisplay.redraw();}

					break;
					
				case SWT.PAGE_DOWN:	
					
					if(mazeDisplay.characterPos.x-1>0 && maze.getCellVal(mazeDisplay.characterPos.x-1,mazeDisplay.characterPos.y,mazeDisplay.characterPos.z)==0){
						mazeDisplay.character.moveDown();
						mazeDisplay.currentLevel-=2;
						mazeDisplay.mazeData=maze.getCrossSectionByX(mazeDisplay.currentLevel);
						mazeDisplay.redraw();	
						floorLable.setText("You are at floor:"+(mazeDisplay.currentLevel-1)/2);}

					break;
					
				case SWT.PAGE_UP:
					
					if(mazeDisplay.characterPos.x+1<maze.getLevels() && maze.getCellVal(mazeDisplay.characterPos.x+1,mazeDisplay.characterPos.y,mazeDisplay.characterPos.z)==0){
						mazeDisplay.character.moveUp();
						mazeDisplay.currentLevel+=2;
						mazeDisplay.mazeData=maze.getCrossSectionByX(mazeDisplay.currentLevel);
						mazeDisplay.redraw();	
						floorLable.setText("You are at floor:"+(mazeDisplay.currentLevel-1)/2);}

					break;				
					}
			}
		});		
			
		
		
		//****************************BAR MENU******************************
		
		Menu barMenu = new Menu(shell, SWT.BAR);
		
		// sub bar menus 'file' menu and 'help' menu
		Menu fileMenu = new Menu(barMenu);
		
		
		// creating the file menu
		MenuItem fileItem = new MenuItem(barMenu, SWT.CASCADE);
		fileItem.setText("File");
		fileItem.setMenu(fileMenu);
		
		MenuItem loadPropertiesItem=new MenuItem(fileMenu,SWT.CASCADE);
		loadPropertiesItem.setText("Open properties");
		
		
		PropetiesWindow propertiesWindow= new PropetiesWindow();
		propertiesWindow.addObserver(this);

		loadPropertiesItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {

				propertiesWindow.start(display);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		MenuItem LoadMazeItem= new MenuItem(fileMenu,SWT.BORDER);
		LoadMazeItem.setText("Load  maze");
		LoadMazeItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				Shell shell=new Shell();
				shell.setText("Load maze");
				shell.setSize(250, 150);
				GridLayout layout = new GridLayout(2, false);
				shell.setLayout(layout);

				//Buttons to insert the data
				Label lblName = new Label(shell, SWT.NONE);
				lblName.setText("Name of the maze: ");
				Text txtName = new Text(shell, SWT.BORDER);
				
				Button loadMazeBtn= new Button(shell, SWT.BORDER);
				loadMazeBtn.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));
				loadMazeBtn.setText("Load");

				shell.open();

				loadMazeBtn.addSelectionListener(new SelectionListener() {
							
							@Override
							public void widgetSelected(SelectionEvent arg0) {
							String name=txtName.getText();
							setChanged();
							notifyObservers("load_maze "+name+" "+name);
							shell.close();
							}
							
							@Override
							public void widgetDefaultSelected(SelectionEvent arg0) {
								// TODO Auto-generated method stub
								
							}
						});
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		MenuItem saveMazeBtn= new MenuItem(fileMenu,SWT.BORDER);
		saveMazeBtn.setText("Save  maze");
		
		saveMazeBtn.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Shell shell=new Shell();
				shell.setText("Save maze");
				shell.setSize(250, 150);

				GridLayout layout = new GridLayout(2, false);
				shell.setLayout(layout);
				
				//Buttons to insert the data
				Label lblName = new Label(shell, SWT.NONE);
				lblName.setText("Name of the maze: ");
				Text txtName = new Text(shell, SWT.BORDER);
				
				Button saveMazeBtn= new Button(shell, SWT.BORDER);
				saveMazeBtn.setText("Save");
				saveMazeBtn.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));
				
				shell.open();
				saveMazeBtn.addSelectionListener(new SelectionListener() {
							
							@Override
							public void widgetSelected(SelectionEvent arg0) {
							String name=txtName.getText();
							
							setChanged();
							notifyObservers("save_maze "+name+" "+name);
							shell.close();
							}
							
							@Override
							public void widgetDefaultSelected(SelectionEvent arg0) {
								// TODO Auto-generated method stub
								
							}
						});
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
	
	/**
	 * <p> Start method
	 * <p>starts the main loop of the basic window
	 */
	@Override
	public void start() {
		run();		
	}
	
	/**
	 * <p> getMazeList method
	 * <> gets an array of strings that  contains all the mazes names
	 */
	protected void getMazeList() {
		setChanged();
		notifyObservers("get_maze_list");
	}

	/**
	 *<p> notifyMazeIsReady method
	 *<p> display a message to the user when the maze that was generated is ready
	 */
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

	/**
	 * <p>displayMessage method
	 * <p> display a message, in a message box, to the user 
	 */
	
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

	
/**
 * <p>notifySolutionIsReady method
 * <p> display a message to the user when the solution for the maze is ready
 */


	@Override
	public void notifySolutionIsReady(String name) {
		display.syncExec(new Runnable() {
			
			@Override
			public void run() {
				
				if(!wantHint){
					
				MessageBox msg = new MessageBox(shell);
				msg.setMessage("Solution for the maze: '" + name + "' is ready");
				msg.open();	
				
				}
				
				setChanged();
				notifyObservers("display_solution "+mazeName);
				
			}
		});					
	}

	
	/**
	 * <p>displayMazeSolution method
	 * <p> display the maze solution to the user, if the user just want an hint- display an hint in the next move
	 */
	
	@Override
	public void displayMazeSolution(Solution<Position> solution) {
		
		mazeDisplay.setSolution(solution);	
		ArrayList<State<Position>> solutionStates=solution.getStates();
		solutionStates.remove(0);
		
		if(wantHint)
		{
			if(solutionStates.get(0).getValue().x>mazeDisplay.character.getPos().x)
			{
				setChanged();
				notifyObservers("display_message You need to go up! :)");
			
			}
			if(solutionStates.get(0).getValue().x<mazeDisplay.character.getPos().x)
			{	
				setChanged();
				notifyObservers("display_message You need to go down! :)");
			}
			else{
			mazeDisplay.setWantHint(true);
			mazeDisplay.hint.setPos(solutionStates.get(0).getValue());
			mazeDisplay.redraw();	}
			
			wantHint=false;
			
		}
		
		else{
					TimerTask task = new TimerTask() {
				@Override
	
				public void run() {
					
					mazeDisplay.getDisplay().syncExec(new Runnable() {
		
						@Override
						public void run() {
							

							if(solutionStates.size()!=0)
									
							{
								if(mazeDisplay.currentLevel==solutionStates.get(0).getValue().x)
								{
									mazeDisplay.character.setPos(solutionStates.get(0).getValue());
									solutionStates.remove(0);
									floorLable.setText("You are at floor:"+mazeDisplay.currentLevel);
									mazeDisplay.redraw();


								}
								
								else{		
								mazeDisplay.character.setPos(solutionStates.get(1).getValue());
								mazeDisplay.currentLevel=solutionStates.get(1).getValue().x;
								mazeDisplay.mazeData=maze.getCrossSectionByX(mazeDisplay.currentLevel);
								solutionStates.remove(0);
								solutionStates.remove(0);
								floorLable.setText("You are at floor:"+mazeDisplay.currentLevel);
								mazeDisplay.redraw();

								}
							
								
							if(mazeDisplay.character.getPos().equals(mazeDisplay.goalPosition))
								cancel();
															
								}
							}
		
						});
 
				}
			};
			
			
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(task, 0, 500);
		}
	}

/**
 * <p> displayMaze  method
 * <p> set the maze data for the maze display then draw the maze to the user
 */
	@Override
	public void displayMaze(Maze3d maze) {
		this.mazeName=mazeList.getText();
		this.maze=maze;		
		
		mazeDisplay.setMazeData(maze);
		floorLable.setText("You are at floor:"+((mazeDisplay.currentLevel-1)/2));
		mazeDisplay.redraw();
		
	}

	/**
	 * <p> update  method
	 * <p> gets a notification that some changes accrued and pass it to the presenter
	 */
	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers(arg);		
	}
	/**
	 * sets the array of strings that contains the mazes names to the drop down button 
	 */

	@Override
	public void setMazeList(String[] list) {
		mazeList.setItems(list);		
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

}
