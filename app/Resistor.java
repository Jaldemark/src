package app;

import testProject.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;

import java.awt.geom.*;
import java.util.List;
import java.awt.event.*;
import javax.swing.*;

public class Resistor implements Node {
	int draggedAtX,draggedAtY;
	 
	Gate leftGate;
	Gate rightGate;
	//Gate cir2;
	Gate[] theGates = {leftGate,rightGate/*cir2*/};
	private double x;
	private double y;
	private double size;
	private Color color;
	private static final int DEFAULT_SIZE = 30;
	
	public Resistor(Color aColor) {
		size = DEFAULT_SIZE;
		x = 0;
		y = 0;
		color = aColor;
		
	}
	public String getType(){
		return "Resistor";
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
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException exception) {
			return null;
		}
	}
	public void setGates(){
		leftGate  = new Gate(Color.BLACK, x-5,y+12);
		rightGate = new Gate(Color.black,x+30,y+12);
		//cir2 = new Gate(Color.BLACK,x+30,y+12);
		theGates[0]=leftGate;
		theGates[1]=rightGate;
		//theGates[2]=cir2;
	}


	public void draw(Graphics2D g2) {
		Rectangle2D square = new Rectangle2D.Double(x, y, size, size);
		setGates();
		//Ellipse2D cir3 = new Ellipse2D.Double(x+20,y+14,5,5);
		Color oldColor = g2.getColor();
		g2.setColor(color);
		g2.setStroke(new BasicStroke(1));
		
		g2.fill(square);
		g2.setColor(oldColor);
		g2.draw(square);
		g2.drawString("R", (int)square.getCenterX()-4, (int)square.getCenterY()+3);
		g2.fill(leftGate.get());
		g2.draw(leftGate.get());
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
	@Override
	public Gate getGates(int n){
		setGates();
		return theGates[n];
		
	}
	public int nrOfConn() {
		return theGates.length;
	}
	@Override
	public void moveAtCursor(double x,double y){

		this.y=y;
		this.x=x;
	}
	
}