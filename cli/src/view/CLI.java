
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
package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;


import controller.Command;



public class CLI {
	
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
		out.print("Choose command: (");
		for (String command : commands.keySet()) {
			out.print(command + " ,");
		}
		out.println(")");
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
	public void run() {
	
			while(true){
				printMenu();
				try {
					String commandLine = in.readLine();
					String arr[] = commandLine.split(" ");
					String command = arr[0];			
					
					if(!commands.containsKey(command)) {
						out.println("Command doesn't exist");
						out.flush();
					}
					else {
						String[] args = null;
						if (arr.length > 1) {
							String commandArgs = commandLine.substring(
									commandLine.indexOf(" ") + 1);
							args = commandArgs.split(" ");							
						}
						Command cmd = commands.get(command);
						cmd.doCommand(args);				

						if (command.equals("exit")){
							Command exit=commands.get(command);
							exit.doCommand(null);;
							break;
						}
					}
				} catch (IOException e) {
					//out.println("Wrong input,please try again!");
					//e.printStackTrace();
				}
			}
	
	}
		
});

thread.start();

	}
	

}




