package presenter;
/**
 * <h2>Interface Command<h2>
 * <p>implements by the CommandManager.
 * <p> contains one method to be override-doCommand. 
 * 
 * 
 * @author Tal Oyar & Tomer cohen
 * @version 1.0
 * @since 2016-08-30
 *
 * @see CommandManager
 */

public interface Command {

	void doCommand(String[] args);
}
