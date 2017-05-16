package testProject;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import app.Shop;
import app.TextField;
import testProject.*;
/**
 * This frame shows the toolbar and the graph.
 */
public class GraphFrame extends JFrame {

	private Graph graph;
	private GraphPanel panel;
	private JScrollPane scrollPane;
	private ToolBar toolBar;
	static JTextArea theArea;
	
	JMenuBar menuBar;
	JMenu menu, file;
	JMenuItem shop, clearShop;
	
	
	public GraphFrame(final Graph graph) {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.graph = graph;

		setUpMenu();
		
		toolBar = new ToolBar(graph);
		panel = new GraphPanel(toolBar, graph);
		scrollPane = new JScrollPane(panel);
		theArea = new JTextArea(20,10);
	
		this.add(theArea, BorderLayout.EAST);
		this.add(toolBar, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(new JPanel());
		this.add(scrollPane,BorderLayout.CENTER);
		JButton b = new JButton();
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
			}
		});
	}
	
	/**
	 * Setting up the MenuBar
	 */
	public void setUpMenu(){
		menuBar= new JMenuBar();
		setJMenuBar(menuBar);
		menu = new JMenu("Open");
		file = new JMenu("File");
		menuBar.add(menu);
		menuBar.add(file);
		shop = new JMenuItem("shop");
		shop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
				if(theArea.isVisible())
					theArea.setVisible(false);
				else
					theArea.setVisible(true);
			}
		});
		menu.add(shop);
		clearShop = new JMenuItem("Clear shop");
		clearShop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
			Shop.setShop(0,0);
			theArea.setText(Shop.getShop());
			}
		});
		menu.add(clearShop);
		
			
		
	}

	

	public static final int FRAME_WIDTH = 600;
	public static final int FRAME_HEIGHT = 400;


}
