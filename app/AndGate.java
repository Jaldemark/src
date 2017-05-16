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

public class AndGate implements Node {

	int draggedAtX,draggedAtY;
	public AndGate(Color aColor) {
		size = DEFAULT_SIZE;
		x = 0;
		y = 0;
		color = aColor;
		this.add(1);
	}
	public String getType(){
		return "AndGate";
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
	/**
	 * 
	 * @param n is the amount of gates to be added
	 */
	public void add(int n){
		Gate temp = new Gate(Color.BLACK);
		Rectangle2D test =new Rectangle2D.Double(x, y, size, size);
		test.add(temp.getBounds());
	}

	public void draw(Graphics2D g2) {
		Rectangle2D square = new Rectangle2D.Double(x, y, size, size);
		Ellipse2D cir = new Ellipse2D.Double(x-5,y+1,5,5);
		Ellipse2D cir1 = new Ellipse2D.Double(x-5,y+14,5,5);
		Ellipse2D cir2 = new Ellipse2D.Double(x+20,y+1,5,5);
		Ellipse2D cir3 = new Ellipse2D.Double(x+20,y+14,5,5);
		Color oldColor = g2.getColor();
		g2.setColor(color);
		g2.setStroke(new BasicStroke(1));
		
		g2.fill(square);
		g2.setColor(oldColor);
		g2.draw(square);
		g2.draw(cir);
		g2.draw(cir1);
		g2.draw(cir2);
		g2.draw(cir3);
	}

	public void translate(double dx, double dy) {
		x += dx;
		y += dy;
	}

	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x, y, size, size);
	}

	private double x;
	private double y;
	private double size;
	private Color color;
	private static final int DEFAULT_SIZE = 20;
	
	@Override
	public boolean contains(Point2D thePoint) {
		Rectangle2D square = new Rectangle2D.Double(x, y, size, size);
		return square.contains(thePoint);
	}
	
	
}