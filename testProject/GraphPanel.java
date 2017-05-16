package testProject;

import java.awt.*;



import app.Shop;

import java.awt.geom.*;
import java.util.List;
import java.awt.event.*;
import javax.swing.*;

import app.Shop;


public class GraphPanel extends JComponent {
	private Graph graph;
	private ToolBar toolBar;
	Shop shop;
	int xPointer;
	int yPointer;
	Boolean mouse;
	
	private Point2D thePointer;
	private Rectangle2D theBounds;
	private Object clickedObject;
	private Point2D currentPointer;
	
	public GraphPanel(ToolBar aToolBar, Graph aGraph) {
		toolBar = aToolBar;
		graph = aGraph;
		setBackground(Color.WHITE);

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				Point2D mousePoint = event.getPoint();
				Object tool = toolBar.getSelectedTool();
				Node theNode = graph.findNode(mousePoint);
				if(tool==null){
					if(theNode!=null){
						clickedObject = theNode;
						thePointer = mousePoint;
						theBounds = theNode.getBounds();
					}
					else
						clickedObject = null;
				}
				else /*if (tool != null)*/ {
					Node prototype = (Node) tool;
					Node newNode = (Node) prototype.clone();
					if(theNode==null&&!SwingUtilities.isRightMouseButton(event)){
						graph.add(newNode, mousePoint);	
						Shop.addToShop(newNode);
						GraphFrame.theArea.setText(Shop.getShop());
					}

					repaint();
				}
				if(theNode==null&&SwingUtilities.isRightMouseButton(event)) {
					JPopupMenu pMenu = new JPopupMenu();
					JMenuItem delete = new JMenuItem("Delete all");
					
					delete.addActionListener(new
							ActionListener()
					{
						public void actionPerformed(ActionEvent event)
						{
							Shop.clearShop();
							GraphFrame.theArea.setText(Shop.getShop());
							graph.deleteAll();
							repaint();
						}
						
						
					});
					
					pMenu.add(delete);
					pMenu.show(GraphPanel.this, event.getX(), event.getY());
				}
				
				else if((SwingUtilities.isRightMouseButton(event))&&theNode!=null){
					clickedObject=theNode;
					JPopupMenu pMenu = new JPopupMenu();
					JMenuItem delete = new JMenuItem("Delete");
					pMenu.add(delete);
					delete.addActionListener(new
							ActionListener()
					{
						public void actionPerformed(ActionEvent event)
						{
							Shop.removeFromShop(theNode);
							GraphFrame.theArea.setText(Shop.getShop());
							deleteNode();
						}

						
					});
					pMenu.show(GraphPanel.this, event.getX(), event.getY());
					
				}
				
				
			}
				public void mouseReleased(MouseEvent event){
					Object tool = toolBar.getSelectedTool();
				
					Point2D mousePoint = event.getPoint();
					
					repaint();

					thePointer = null;
					currentPointer = null;
					theBounds = null;
					
				
				}
			});
				
				addMouseMotionListener(new MouseMotionAdapter(){
					public void mouseDragged(MouseEvent event){
						Object tool = toolBar.getSelectedTool();
						Point2D newPoint = event.getPoint();
						
							if(theBounds != null&&!SwingUtilities.isRightMouseButton(event)){
									Node n = (Node) clickedObject;
									n.setY(newPoint.getY());
									n.setX(newPoint.getX());
							}
						
						currentPointer = newPoint;
						repaint();
						}
					
					});
						
			}

	private void deleteNode() {
		graph.deleteSelected((Node)clickedObject);
		repaint();
		
	}
	

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		//int width = getWidth();
	    //int height = getHeight();
	    
		graph.draw(g2);
	}

	
}
