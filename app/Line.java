package app;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.Serializable;

public class Line implements Serializable {
    public Point2D p1;
    public Point2D p2;
    double x1;
    double x2;
    double y1;
    double y2;
    public Line(Point2D p1, Point2D p2) {
        this.p1 = p1;
        this.p2 = p2;
     
    }
    public Line(double x,double y,double x1,double y1){
    	
    }
    public Point2D getStartPoint(){
    	return p1;
    }
    public Point2D getEndPoint(){
    	return p2;
    }
    public void setStartPoint(Point2D p1){
    	this.p1=p1;
    }
    public void setEndPoint(Point2D p2){
    	this.p2=p2;
    }
    public void draw(Graphics g) {
    	Graphics2D g2 = (Graphics2D) g;
        g2.draw(new Line2D.Double(this.p1,this.p2));
        g2.setStroke(new BasicStroke(2));
    }
	public boolean contains(Point2D p) {
		Line2D line= new Line2D.Double(p1,p2);
		if(line.contains(p))
			return true;
		return false;
	}
	public Line2D getConnectionPoints(){
		return new Line2D.Double(p1,p2);
	}
	public void setConnectionPoints(Point2D p, Point2D q){
		p1=p;
		p2=q;
	}
	

}