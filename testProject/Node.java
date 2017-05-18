package testProject;

import java.awt.*;

import java.awt.geom.*;
import java.io.Serializable;

/*<<<<<<< HEAD
public interface Node extends Cloneable,Serializable {
=======*/
import app.Gate;

public interface Node extends Cloneable,Serializable {
/*>>>>>>> b527330150b41c5ab301ad96355a67078603cfb3*/

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
	int nrOfConn();

	Gate getGates(int n);



}
