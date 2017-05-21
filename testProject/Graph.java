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


public abstract class Graph implements Serializable{
	private ArrayList<Node> nodes;
	private final ArrayList<Line> lines = new ArrayList<Line>();
	private final ArrayList<Gate> gates = new ArrayList<Gate>();
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
			
		for(Line l : lines){
			g2.setStroke(new BasicStroke(3));
			l.draw(g2);
		}
	}

	public abstract Node[] getNodePrototypes();

	public List<Node> getNodes() {
		return Collections.unmodifiableList(nodes);
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
	public Gate findGate(Point2D p){
		Gate temp;
		for(int i = 0; i<nodes.size(); i++ ){
			Node newNode = nodes.get(i);
			for(int j=0;j<newNode.nrOfConn();j++){
				temp = newNode.getGates(j);
				if(temp.contains(p)){
					return temp;
				}
			}
		}
		return null;
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

	 public Line2D addLine(Point2D p1,Point2D p2) {
		 Line l = new Line (p1,p2);
		 try{
			 if(lines.size()==0){
				 lines.add(new Line(p1,p2));
		        	Line2D theLine =new Line2D.Double(p1,p2);
		        	return theLine; 
			 }
			for(Line line : lines){
				for(Node n : nodes)
				if(!l.checkIntersection(line)){
					lines.add(new Line(p1,p2));
		        	Line2D theLine =new Line2D.Double(p1,p2);
		        	return theLine;
				}
			}
			return null;
		 }
		 catch(java.lang.NullPointerException e){
			 return null;
		 }

	        
	 }
	 public Line checkSelected(Node a){
		 for(int i=0;i<lines.size();i++){
			 for(int j=0;j<a.nrOfConn();j++){
				 if(lines.get(i).contains(a.getGates(j).getLineCordinate())){
					 return lines.get(i);
				 }
				 
			 }
		 }
		 return null;
	 }
	 public ArrayList<Line> getLines(){
		 return lines;
	 }

	
	public Line nodeContainsStart(Gate g) {
		Point2D p = new Point2D.Double(0,0);
			for(int j=0;j<lines.size();j++){
				if(g.contains(lines.get(j).getStartPoint())){
					p.setLocation(g.getCenterX(), g.getCenterY());
					lines.get(j).setStartPoint(p);
					return lines.get(j);
				}
			}
		return null;
		
	}
	public Line nodeContainsEnd(Node n) {
		Point2D p = new Point2D.Double(0,0);
		for(int i=0;i<n.nrOfConn();i++){
			Gate g = n.getGates(i);
			for(int j=0;j<lines.size();j++){
				if(g.contains(lines.get(j).getEndPoint())){
					p.setLocation(g.getCenterX(), g.getCenterY());
					lines.get(j).setEndPoint(p);
					return lines.get(j);
				}
			}
		}
		return null;
		
	}
	public Line nodeContainsEnd(Gate g) {
		Point2D p = new Point2D.Double(0,0);
			for(int j=0;j<lines.size();j++){
				if(g.contains(lines.get(j).getEndPoint())){
					p.setLocation(g.getCenterX(), g.getCenterY());
					lines.get(j).setEndPoint(p);
					return lines.get(j);
				}
			}
		return null;
	}

	public Object nodeContainsStart(Node n) {
		Point2D p = new Point2D.Double(0,0);
		for(int i=0;i<n.nrOfConn();i++){
			Gate g=n.getGates(i);
			for(int j=0;j<lines.size();j++){
				if(g.contains(lines.get(j).getStartPoint())){
					p.setLocation(g.getCenterX(), g.getCenterY());
					lines.get(j).setStartPoint(p);
					return lines.get(j);
				}
			}
		}
	
		return null;
	}
	public void updateShop(){
		Shop.updateShop(nodes);
		
	}

	public boolean nodeContainsNode(Node newNode, Point2D p) {
		double tx = p.getX()+30;
		double bx = p.getX();
		double ty = p.getY();
		double by = p.getY()+30;
		
		for(Node n : nodes){
			if(n.getBounds().contains(tx, by)){
				return true;
			}
			else if(n.getBounds().contains(tx,ty)){
				return true;
			}
			else if(n.getBounds().contains(bx,by)){
				return true;
			}
		}
		return false;
		
	}
	public boolean checkIntersection(){
		
		try{
			for(Line l : lines){
				for(Line l2 : lines){
					for(Node n : nodes){
						if(l!=l2&&(l.checkIntersection(l2))){
							lines.remove(l2);
							return true;
						}
						else if(l.checkIntersection(n)){
							lines.remove(l);
							return true;
						}
					}	
				}
			}
			return false;
			
		}
		catch(java.util.ConcurrentModificationException e){
			return false;
		}
	}

	public boolean placedOnNode(Node a) {

		try{	
			for(Node n : nodes){
				if(nodes.size()>1&&(n.getBounds().intersects(a.getBounds()))&&n!=a)
					nodes.remove(n);
					return true;
			}
			return false;
		}
		catch(java.lang.NullPointerException e){
			return false;
		}
	}

	

}
