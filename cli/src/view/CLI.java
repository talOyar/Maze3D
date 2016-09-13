package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;

public class CLI extends Thread {
BufferedReader in;
PrintWriter out;
HashMap<String,Command> commands;

public CLI (BufferedReader in ,PrintWriter out ,HashMap<String, Command> command){
	this.out=new PrintWriter(out);
	this.in = new BufferedReader(in);
	this.commands = command;
}



	public void run(){
		String input;
		try {
			while((input = in.readLine())!= "exit"){
				//put
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}




