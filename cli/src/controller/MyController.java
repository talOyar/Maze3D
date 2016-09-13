package controller;


import model.Model;
import view.View;

public class MyController implements Controller{
	private View view;
	private Model model;
	private CommandsManager commandsManager;
	
	public MyController(View view, Model model) {
		this.view = view;
		this.model = model;
		
		commandsManager = new CommandsManager(model,view);
		//return : view.commands HashMap (maze & display)   
		view.setCommands(commandsManager.getCommandsMap());
	}
	
	
	//view.print the maze name (after model Finished build the maze)
	@Override
	public void notifyMazeIsReady(String namemaze) {
		view.notifyMazeIsReady(namemaze);		
	}
	

	
	

}
