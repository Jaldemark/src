package testProject;

import java.awt.*;

import java.awt.geom.*;

import app.Gate;

public interface Node extends Cloneable {

	void draw(Graphics2D g2);

	void translate(double dx, double dy);

	Rectangle2D getBounds();

	Object clone();
	boolean contains(Point2D aPoint);
	void setX(double x);
	void setY(double y);
	double getX();
	double getY();
	String getType();

	Gate getGates(int n);



}
