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



	public  void setCommands(HashMap<String, Command> commands) {
		this.commands = commands;
		
	}

	
	private void printMenu() {
		out.print("Choose command: (");
		for (String command : commands.keySet()) {
			out.print(command + " ,");
		}
		out.println(")");
		out.flush();
	}
	
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
	}
		
});

thread.start();

	}
	

}




