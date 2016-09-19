
package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Observable;

import presenter.Command;

/**
 * <h2>Class CLI<h2>
 * <p>CLI- command line user interface
 * <p> uses to interact the program with the user.
 * <p> displays the menu of command to the interface and receiving
 * the desired command to be performed
 * <p> activated from the View start method that activates 
 * the start method of the CLI.
 * 
 * 
 * @author Tal Oyar & Tomer cohen
 * @version 1.0
 * @since 2016-08-30
 *
 *
 * @param in- a generic input parameter that reads data from the input. 
 * @param out- a generic output parameter that display data to the output
 *
 *
 * @see Command
 * @see CommandManager
 * @see view
 *
 */


public class CLI extends Observable {
	
BufferedReader in;
PrintWriter out;
HashMap<String,Command> commands;

public CLI (BufferedReader in ,PrintWriter out ){
	this.out=new PrintWriter(out);
	this.in = new BufferedReader(in);
	
}


/**
 * <p> setCommands method
 * <p> sets the commands on the hashMap commands
 * 
 * @param commands- an hashMap of commands.
 */
	public  void setCommands(HashMap<String, Command> commands) {
		this.commands = commands;
		
	}

	/**
	 * <p> printMenu method
	 * <p> prints all the command available to the output(user)
	 * 
	 * @param commands- an hashMap of commands.
	 */
	
	private void printMenu() {
		out.println("**Command Menu**");
		out.println("(1.)generate_maze- enter the name of the maze and 3 demention sizes");
		out.println("(2.)display_maze- enter the name of the maze to display");
		out.println("(3.)solve_maze- enter the name of the maze to be solved and the desired algorithm to solve with");
		out.println("(4.)display_solution- enter the name of the maze to get the solution");
		out.println("(5.)display_cross_section- enter the name of the maze the section and the index of the section ");
		out.println("(6.)save_maze- enter the name of the maze to be solved and the name of the file to save into ");
		out.println("(7.)load_maze- enter the name of the maze to load and the name of the file to load it from ");
		out.println("(8.)directory- enter a path ");
		out.println("(9.)exit ");

		out.flush();
		}
		
	
	

	/**
	 * <p> start method
	 * <p> activated the printMenu method- to display to the user the available commands
	 * then receive the desired command and activate it using the commandManager methods
	 * 
	 * 
	 * @param commands- an hashMap of commands.
	 * @see CommandManager
	 * @see Myview
	 */
	public void start() {
		
Thread thread=new Thread(new  Runnable() {
		@Override
		public void run() {
			
			while (true) {
			
				printMenu();
				try {
					String commandLine = in.readLine();
					setChanged();
					notifyObservers(commandLine);
				
					if (commandLine.equals("exit"))
						break;
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}			
	});
		thread.start();
	}
}




