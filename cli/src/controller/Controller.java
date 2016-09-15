package controller;
/**
 * <h2> Controller Interface<h2>
 * <p> implemented by MYController class
 * <p> holds three methods to be override- notifyMazeIsReady, notifySolutionIsReady and displayMessage
 * 
 * @author Tal Oyar & Tomer Cohen
 * @since 09-15-2016
 * @version 1.0
 * 
 *@see MyController
 *
 */


public interface Controller {
	//view.print "the maze is ready   
	public void notifyMazeIsReady(String namemaze);
	public void notifySolutionIsReady(String name);
	public void displayMessage(String msg);
}
