package app;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;

import java.awt.geom.*;
import java.util.List;
import java.awt.event.*;
import javax.swing.*;

import testProject.*;

public class OrGate implements Node {

	Gate leftGate1;
	Gate leftGate2;
	Gate rightGate;
	Gate[] theGates = {leftGate1,leftGate2,rightGate};
	int draggedAtX,draggedAtY;
	
	private double x;
	private double y;
	private double size;
	private Color color;
	private static final int DEFAULT_SIZE = 30;
	public OrGate(Color aColor) {
		size = DEFAULT_SIZE;
		x = 0;
		y = 0;
		color = aColor;	 
	}
	public String getType(){
		return "OrGate";
	}
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public void setX(double x){
		this.x=x;
	}
	public void setY(double y){
		this.y=y;
	}
	@Override
	public void moveAtCursor(double x,double y){

		this.y=y;
		this.x=x;
	}
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException exception) {
			return null;
		}
	}

	public void draw(Graphics2D g2) {
		Rectangle2D square = new Rectangle2D.Double(x, y, size, size);
		setGates();
		Color oldColor = g2.getColor();
		g2.setColor(color);
		g2.fill(square);
		g2.setColor(oldColor);
		g2.draw(square);
		g2.drawString(getType(), (int)square.getMinX(), (int)square.getY()-2);
		g2.fill(leftGate1.get());
		g2.fill(leftGate2.get());
		g2.draw(leftGate1.get());
		g2.draw(leftGate2.get());
		g2.draw(rightGate.get());
	}

	public void translate(double dx, double dy) {
		x += dx;
		y += dy;
	}

	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x, y, size, size);
	}


	
	@Override
	public boolean contains(Point2D thePoint) {
		Rectangle2D square = new Rectangle2D.Double(x, y, size, size);
		return square.contains(thePoint);
	}
	public void setGates(){
		leftGate1  = new Gate(Color.BLACK, x-5,y+5);
		leftGate2 = new Gate(Color.black,x-5,y+20);
		rightGate = new Gate(Color.BLACK,x+30,y+12);
		theGates[0]=leftGate1;
		theGates[1]=leftGate2;
		theGates[2]=rightGate;
	}
	
	@Override
	public Gate getGates(int n){
		setGates();
		return theGates[n];
		
	}
	public int nrOfConn() {
		// TODO Auto-generated method stub
		return theGates.length;
	}


	
	
}