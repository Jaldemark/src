package testProject;

import java.awt.*;

import app.*;
import app.TextField;

import java.awt.geom.*;
import java.util.*;
import java.util.List;

import java.awt.*;
import java.awt.geom.*;
import java.io.Serializable;
import java.util.*;
import java.util.List;

import javax.swing.*;


public abstract class Graph {
	private ArrayList<Node> nodes;
	private final ArrayList<Line> lines = new ArrayList<Line>();
	public TextField shop;
	
	public Graph() {
		nodes = new ArrayList<Node>();
		
	}

	public void add(Node n, Point2D p) {
		Rectangle2D bounds = n.getBounds();
		n.translate(p.getX() - bounds.getX(), p.getY() - bounds.getY());
		nodes.add(n);
	}

	public void draw(Graphics2D g2) {
		for (Node n : nodes)
			n.draw(g2);
		for(Line l : lines)
			l.draw(g2);
	}

	public abstract Node[] getNodePrototypes();

	public List<Node> getNodes() {
		return Collections.unmodifiableList(nodes);
	}
	public TextField shop(int n, String s){
		return new TextField(n,s);
	}
	/**
	 * checks if the mousepoint is on the node
	 * @return true if the pointer is on a node
	 */
	public Node findNode(Point2D p){
		for(int i = 0; i<nodes.size(); i++ ){
			Node newNode = nodes.get(i);
			if(newNode.contains(p))
				return newNode;
			
		}
		return null;
	}
	public Line findLine(Point2D p){
		for(int i = 0; i<lines.size(); i++ ){
			Line newLine = lines.get(i);
			if(newLine.contains(p))
				return newLine;
		}
		return null;
	}
	public boolean findGate(Point2D p){
		Ellipse2D temp;
		for(int i = 0; i<nodes.size(); i++ ){
			Node newNode = nodes.get(i);
			for(int j=0;j<3;j++){
				temp = newNode.getGates(j);
				if(temp.contains(p)){
					return true;
				}
			}
		}
		return false;
	}

	public void deleteSelected(Node theNode) {
		nodes.remove(theNode);
		
	}
	public void deleteSelected(Line theLine){
		lines.remove(theLine);
	}
	public void deleteAll(){
		while(nodes.size()!=0 || lines.size()!=0){
			if(nodes.size()!=0)
				nodes.remove(nodes.get(0));//removes first in the list
			if(lines.size()!=0)
				lines.remove(lines.get(0));
		}
	}
	public boolean checkOverPrint(Node a){
		Node newNode;
		Rectangle2D bounds;
		for(int i=0;i<nodes.size();i++){
			newNode = nodes.get(i);
			bounds = newNode.getBounds();
			
		return true;	
		}
			return false;
				
	}
	 public Line2D addLine(Point2D p1,Point2D p2) {
	        this.lines.add(new Line(p1,p2));
	        Line2D theLine =new Line2D.Double(p1,p2);
	        return theLine;
	    }


	

}
