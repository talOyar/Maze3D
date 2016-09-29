package view;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;

/**
 * <h2>PropetiesWindow class<h2>
 * <p> extends DialogWindow
 * <p> opens a properties  window when the properties  button is pushed
 * 
 * 
 * @author Tal Oyar & Tomer Cohen
 * @version 1.0
 * @since 20-09-2016
 * 
 * 
 * @see DialogWindow
 *
 */
public class PropetiesWindow extends DialogWindow {
	String generateAlgorithm;
	String SolveAlgorithm;
	
	@Override
	protected void initWidgets() {


		shell.setText("Propeties");
		shell.setSize(700, 250);

		GridLayout layout = new GridLayout(2, false);
		shell.setLayout(layout);
		GridLayout gridLayout = new GridLayout(2, true);
		shell.setLayout(gridLayout);
		
		
	
		Group generateAlgorithms=new Group(shell, SWT.BORDER);
		generateAlgorithms.setText("Chose your favorite generate algorithm:");
		generateAlgorithms.setLayout(new GridLayout(2, true));

		Button growingTreeLast=new Button(generateAlgorithms,SWT.RADIO); //generateAlgorithms, SWT.RADIO);
		growingTreeLast.setText("Growing tree by last");
		
		Button growingTreeRandum=new Button(generateAlgorithms,SWT.RADIO); //generateAlgorithms, SWT.RADIO);
		growingTreeRandum.setLayoutData(new GridData(SWT.FILL,SWT.NONE, false,false,1, 1));
		growingTreeRandum.setText("Growing tree by randum");
//		
//		Button simple=new Button(generateAlgorithms,SWT.RADIO); //generateAlgorithms, SWT.RADIO);
//		simple.setLayoutData(new GridData(SWT.FILL,SWT.NONE, false,false,1, 1));
//		simple.setText("simple");
		
		
		Group solveAlgorithms=new Group(shell, SWT.BORDER);
		solveAlgorithms.setLayout(new GridLayout(2, true));;	
		solveAlgorithms.setText("chose your favorite solve algorithm:");
		Button bfs=new Button(solveAlgorithms, SWT.RADIO);
		bfs.setText("BFS");
		bfs.setLayoutData(new  GridData(SWT.FILL,SWT.NONE, false,false,1, 1));
		Button dfs=new Button(solveAlgorithms, SWT.RADIO);
		dfs.setText("DFS");
		dfs.setLayoutData(new  GridData(SWT.FILL,SWT.NONE, false,false,1, 1));

		
		Button saveProp = new Button(shell, SWT.PUSH| SWT.BORDER);
		saveProp.setText("Save properties");
				

		Button loadProp=new Button(shell,SWT.PUSH|SWT.BORDER );	
		loadProp.setLayoutData(new GridData(SWT.NONE, SWT.NONE, false, false, 2, 1));
		loadProp.setText("Load properties");
		
		
		
		//*************************listeners**************************************
		
		
		//generate listeners
		
		growingTreeLast.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				generateAlgorithm="growingTreeByLast";
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		growingTreeRandum.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				generateAlgorithm="growingTreeByRandom";
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
//		
//		simple.addSelectionListener(new SelectionListener() {
//			
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
//				generateAlgorithm="simple";
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		
//		
		
		
		//solve listeners
		
		bfs.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				SolveAlgorithm="bfs";
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		dfs.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				SolveAlgorithm="dfs";
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		//save properties listeners
		saveProp.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String command= generateAlgorithm+ " "+SolveAlgorithm;
				setChanged();
				notifyObservers("set_properties "+command);

				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {			
				
			}
		});	
		
		//load from exist XML file
		
		loadProp.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog fd = new FileDialog(shell,SWT.OPEN);
				fd.setText("Load properties");
				fd.setFilterExtensions(new String[] { "*.xml" });
				String selected = fd.open();
				if(selected!=null){
				setChanged();
				notifyObservers("load_xml "+selected);}
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
