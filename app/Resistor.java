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
	 
	Gate cir;
	Gate cir1;
	//Gate cir2;
	Gate[] theCircles = {cir,cir1/*cir2*/};
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
		cir  = new Gate(Color.BLACK, x-5,y+12);
		cir1 = new Gate(Color.black,x+30,y+12);
		//cir2 = new Gate(Color.BLACK,x+30,y+12);
		theCircles[0]=cir;
		theCircles[1]=cir1;
		//theCircles[2]=cir2;
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
		g2.fill(cir.get());
		g2.draw(cir.get());
		g2.draw(cir1.get());
		//.draw(cir2.get());
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
		return theCircles[n];
		
	}
	public int nrOfConn() {
		return theCircles.length;
	}
	
}
