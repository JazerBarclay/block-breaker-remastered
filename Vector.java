 

public class Vector {

	double x,y;

	public Vector(double x, double y) {
		setX(x);
		setY(y);
	}
	
	public Vector() {
		setX(0);
		setY(0);
	}
	
	public void set(double x, double y) {
		setX(x);
		setY(y);
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void add(Vector v) {
		this.x += v.x;
		this.y += v.y;
	}
	
	public static Vector addVectors(Vector v1, Vector v2) {
		Vector newVector = new Vector(v1.getX(), v1.getY());
		newVector.add(v2);
		return newVector;
	}
	
//	public static boolean isIntersecting(Vector a, Vector b, Vector c, Vector d) {
//	    float denominator = (float) (((b.getX() - a.getX()) * (d.getY() - c.getY())) - ((b.getY() - a.getY()) * (d.getX() - c.getX())));
//	    float numerator1 = (float) (((a.getY() - c.getY()) * (d.getX() - c.getX())) - ((a.getX() - c.getX()) * (d.getY() - c.getY())));
//	    float numerator2 = (float) (((a.getY() - c.getY()) * (b.getX() - a.getX())) - ((a.getX() - c.getX()) * (b.getY() - a.getY())));
//
//	    // Detect coincident lines (has a problem, read below)
//	    if (denominator == 0) return numerator1 == 0 && numerator2 == 0;
//	    
//	    float r = numerator1 / denominator;
//	    float s = numerator2 / denominator;
//
//	    return (r >= 0 && r <= 1) && (s >= 0 && s <= 1);
//	}
	
}
