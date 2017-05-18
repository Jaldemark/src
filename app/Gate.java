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

public class Gate implements Node {

	Rectangle2D cir;
	Rectangle2D cir1;
	Rectangle2D cir2;
	Rectangle2D[] theCircles = {cir,cir1,cir2};
	Point2D thePoint;
	private double x;
	private double y;
	private double size;
	private Color color;
	private static final int DEFAULT_SIZE = 5;
	public Gate(Color aColor, double x,double y) {
		size = DEFAULT_SIZE;
		cir = new Rectangle2D.Double(x,y,size,size);
		this.x = x;
		this.y = y;
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
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException exception) {
			return null;
		}
	}

	public void draw(Graphics2D g2) {
		/*Rectangle2D square = new Rectangle2D.Double(x, y, size, size);
		setGates();
		Color oldColor = g2.getColor();
		g2.setColor(color);
		g2.fill(square);
		g2.setColor(oldColor);
		g2.draw(square);
		g2.fill(cir);
		g2.draw(cir);*/
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

	}
	public Rectangle2D[] getGates(){
		return theCircles;
	}
	
	public Rectangle2D getGate(int n){
		setGates();
		return theCircles[n];
		
	}
	public Rectangle2D get(){
		return cir;
	}
	public void setLineCordinate(Point2D p){
		thePoint = p;
	}
	public Point2D getLineCordinate(){
		return thePoint;
	}
	@Override
	public Gate getGates(int n) {
		
		return this;
	}

	public double getCenterX(){
		return cir.getCenterX();
	}
	public double getCenterY(){
		return cir.getCenterY();
	}
	
	
}