package app;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Line {
    public Point2D p1;
    public Point2D p2;
    public Line(Point2D p1, Point2D p2) {
        this.p1 = p1;
        this.p2 = p2;
     
    }
    public void draw(Graphics g) {
    	Graphics2D g2 = (Graphics2D) g;
        g2.draw(new Line2D.Double(this.p1,this.p2));
    }
	public boolean contains(Point2D p) {
		Line2D line= new Line2D.Double(p1,p2);
		if(line.contains(p))
			return true;
		return false;
	}

}