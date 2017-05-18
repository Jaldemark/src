package testProject;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
	JMenuItem shop, clearShop,saveFile,loadFile;
	
	
	public GraphFrame(final Graph graph) {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.graph = graph;

		setUpMenu();
		/////
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
		
		saveFile = new JMenuItem("Save File");
		saveFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				saveFile();
			}
		});
		file.add(saveFile);
		
		loadFile = new JMenuItem("Load File");
		loadFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				loadFile();
				theArea.setText(Shop.getShop());
			}
		});
		file.add(loadFile);
		
			
		
		
		/////
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
		toolBar = new ToolBar(graph);
		panel = new GraphPanel(toolBar, graph);
		scrollPane = new JScrollPane(panel);
		theArea = new JTextArea(20,10);
	
		this.add(theArea, BorderLayout.EAST);
		this.add(toolBar, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(new JPanel());
		this.add(scrollPane,BorderLayout.CENTER);
		
		
		
	}
	/*
	 * Let's the user save the file, name it and store it anywhere on the computer.
	 * */
	private void saveFile(){
		JFileChooser fileChooser = new JFileChooser();
	      if (fileChooser.showSaveDialog(this)
	         == JFileChooser.APPROVE_OPTION)
	      {
	         try
	         {
	            File file = fileChooser.getSelectedFile();
	            ObjectOutputStream out = new ObjectOutputStream(
	               new FileOutputStream(file));
	            out.writeObject(graph);
	            out.writeObject(theArea);
	            out.close();
	         }
	         catch (IOException exception)
	         {
	            JOptionPane.showMessageDialog(null,
	               exception);
	         }
	      }
	}
	private void loadFile(){
		JFileChooser fileChooser = new JFileChooser();
	      int r = fileChooser.showOpenDialog(this);
	      if (r == JFileChooser.APPROVE_OPTION)
	      {
	         // open the file that the user selected
	         try
	         {
	            File file = fileChooser.getSelectedFile();
	            ObjectInputStream in = new ObjectInputStream(
	               new FileInputStream(file));
	            graph = (Graph) in.readObject();
	            theArea = (JTextArea) in.readObject();
	            in.close();
	            this.remove(scrollPane);
	            this.remove(toolBar);
	            setUpMenu();
	            validate();
	            repaint();
	         }
	         catch (IOException exception)
	         {
	            JOptionPane.showMessageDialog(null,
	               exception);
	         }
	         catch (ClassNotFoundException exception)
	         {
	            JOptionPane.showMessageDialog(null,
	               exception);
	         }
	      }
	}
	

	

	public static final int FRAME_WIDTH = 600;
	public static final int FRAME_HEIGHT = 400;


}
