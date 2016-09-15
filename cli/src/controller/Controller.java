package controller;



public interface Controller {
	//view.print "the maze is ready   
	public void notifyMazeIsReady(String namemaze);
	public void notifySolutionIsReady(String name);
	public void displayMessage(String msg);
}
