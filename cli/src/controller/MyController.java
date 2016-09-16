



package controller;

import model.Model;
import view.View;
/**
 * <h2> MyController class<h2>
 * <p> implements Controller interface
 * <p> override three methods- notifyMazeIsReady, notifySolutionIsReady and displayMessage
 * <p>accepts input from the view and converts it to commands for the model or view using CommandManager
 * <p> initialize CommandManager with the view and model
 * 
 * 
 * @author Tal Oyar & Tomer Cohen
 * @since 09-15-2016
 * @version 1.0
 * 
 *@see Controller
 *@see View
 *@see Model
 *@see CommandManager 
 */
public class MyController implements Controller{
	private View view;
	@SuppressWarnings("unused")
	private Model model;
	private CommandsManager commandsManager;
	/**
	 * <p> contractor -initialize with view and model also generate a commandManager
	 * and with the view and model.
	 * 
	 * @param view
	 * @param model
	 */
	public MyController(View view, Model model) {
		this.view = view;
		this.model = model;
		
		commandsManager = new CommandsManager(model,view);
		//return : view.commands HashMap (maze & display)   
		view.setCommands(commandsManager.getCommandsMap());
	}
	
	/**
	 * <p>notifyMazeIsReady method
	 * <p> display a message to the output when the maze is ready.
	 * uses the view method- notifyMazeIsReady
	 * 
	 * @see MyView
	 */
	//view.print the maze name (after model Finished build the maze)
	@Override
	public void notifyMazeIsReady(String namemaze) {
		view.notifyMazeIsReady(namemaze);		
	}
	
	/**
	 * <p> notifySolutionIsReady method
	 * <p> display a message to the output  when the solution for the maze is ready.
	 * uses the view method- notifySolutionIsReady
	 * @see MyView
	 */
	@Override
	public void notifySolutionIsReady(String name) {
		view.notifySolutionIsReady(name);
		
	}

	
	/**
	 * <p> displayMessage method
	 * <p> display a message to the output(user) using the view displayMessage method
	 * @see MyView
	 */
	@Override
	public void displayMessage(String msg) {
		view.displayMessage(msg);		
	}

}
