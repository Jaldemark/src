package testProject;

import java.awt.*;

import app.Gate;
import app.Line;
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
	Boolean mouse=false;
	double offSetX;
	double offSetY;
	
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
				Gate gate = graph.findGate(mousePoint);
				Line theLine = graph.findLine(mousePoint);
				
				if(tool==null){
					if(theNode!=null){
						clickedObject = theNode;
						thePointer = mousePoint;
						theBounds = theNode.getBounds();
						offSetX=mousePoint.getX()-theNode.getX();
						offSetY=mousePoint.getY()-theNode.getY();
					}
					else if(gate!=null){
						clickedObject = gate;
						thePointer = mousePoint;
						currentPointer = thePointer;
					}
					else if(theLine!=null)
						clickedObject = theLine;
					else
						clickedObject = null;
				}
				else  {
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
				
				else if((SwingUtilities.isRightMouseButton(event))){

						JPopupMenu pMenu = new JPopupMenu();
						JMenuItem delete = new JMenuItem("Delete");
						pMenu.add(delete);
						if(theNode!=null){
							clickedObject = theNode;
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
						}
						else if(theLine!=null){
							clickedObject = theLine;
							delete.addActionListener(new
									ActionListener()
							{
								public void actionPerformed(ActionEvent event)
								{
									deleteLine();
								}
							});
						}
						pMenu.show(GraphPanel.this, event.getX(), event.getY());	
						
					
				}
				currentPointer = mousePoint;
				repaint();
			}
				public void mouseReleased(MouseEvent event){
					Point2D mousePoint = event.getPoint();
					Gate gate = graph.findGate(mousePoint);
					if(gate!=null){
						graph.addLine(thePointer,currentPointer);
					}
					else{
					
					thePointer = null;
					currentPointer = null;
					theBounds = null;
					mouse = false;
					}
					repaint();

				}
			});
				
				addMouseMotionListener(new MouseMotionAdapter(){
					public void mouseDragged(MouseEvent event){
						Point2D newPoint = event.getPoint();
						Object tool = toolBar.getSelectedTool();
						Gate gate = graph.findGate(newPoint);
						Node n = (Node)clickedObject;
						double x = newPoint.getX();
						double y= newPoint.getY();
						if(tool==null){
							if(theBounds != null&&!SwingUtilities.isRightMouseButton(event)){
								if(graph.nodeContainsStart(n)!=null){
									for(int i=0;i<n.nrOfConn();i++){
										Gate g = n.getGates(i);
										graph.nodeContainsEnd(g);
										repaint();
									}
								}
								else if(graph.nodeContainsEnd(n)!=null){
									for(int i=0;i<n.nrOfConn();i++){
										Gate g = n.getGates(i);
										graph.nodeContainsEnd(g);
										repaint();
									}
								}
								else if(graph.nodeContainsEnd(n)!=null &&graph.nodeContainsStart(n)!=null){
									for(int i=0;i<n.nrOfConn();i++){
										Gate g = n.getGates(i);
										graph.nodeContainsEnd(g);
										graph.nodeContainsEnd(g);
										repaint();
									}
								}
									n.moveAtCursor(x-offSetX,y-offSetY);
									mouse = false;
							}
							else if(gate!=null||mouse){
								
								mouse = true;
								if(gate!=null){					
									gate.setLineCordinate(newPoint);
									currentPointer = gate.getLineCordinate();									
								}
							}
								
								currentPointer = newPoint;
								repaint();
							
							}
						}
					});
						
			}

	private void deleteNode() {
		graph.deleteSelected((Node)clickedObject);
		repaint();
	}
	private void deleteLine() {
		graph.deleteSelected((Line)clickedObject);
		repaint();
		
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		graph.draw(g2);
		if(mouse){
			Line l = new Line(thePointer,currentPointer);
			g2.setColor(Color.RED);
			l.draw(g2);
		}
	
	
	}

	
}
