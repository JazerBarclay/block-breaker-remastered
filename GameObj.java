 

import javafx.scene.paint.Color;

public class GameObj {

	private int width, height;
	private Vector position, move;
	private Color color;
	private boolean isVisible;
	
	public GameObj(double x, double y, int width, int height, Color color) {
		this.position = new Vector(x, y);
		this.width = width;
		this.height = height;
		this.color = color;
		this.move = new Vector(0,0);
		this.isVisible = true;
	}
	
	public GameObj(double x, double y, int width, int height, int colR, int colG, int colB, double opacity) {
		this(x, y, width, height, new Color(colR, colG, colB, opacity));
	}
	
	public GameObj(Vector position, int width, int height, int colR, int colG, int colB, double opacity) {
		this(position.getX(), position.getY(), width, height, new Color(colR, colG, colB, opacity));
	}
	
	public GameObj(Vector position, int width, int height, Color color) {
		this(position.getX(), position.getY(), width, height, color);
	}
	
	public GameObj(double x, double y, int width, int height) {
		this(new Vector(x, y), width, height);
	}
	
	public GameObj(Vector position, int width, int height) {
		this(position, width, height, new Color(.5, .5, .5, 1));
	}
	
	public void setPosition(Vector position) {
		this.position = position;
	}
	
	public Vector getPosition() {
		return position;
	}
	
	public void setX(double x) {
		position.setX(x);
	}
	
	public void setY(double y) {
		position.setY(y);
	}
	
	public double getX() {
		return position.getX();
	}
	
	public double getY() {
		return position.getY();
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setColor(int red, int green, int blue, double opacity) {
		this.color = new Color(red, green, blue, opacity);
	}
	
	public void setColor(int red, int green, int blue) {
		this.color = new Color(red, green, blue, 1);
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getRed() {
		return (int) color.getRed();
	}
	
	public int getGreen() {
		return (int) color.getGreen();
	}
	
	public int getBlue() {
		return (int) color.getBlue();
	}
	
	public void setMoveVector(Vector moveVector) {
		this.move = moveVector;
	}
	
	public Vector getMoveVector() {
		return move;
	}

	public void updatePosition() {
		this.position.add(move);
	}
	
	@Deprecated
	public void updatePosition(Vector v) {
		this.position.add(v);
	}
	
	public void setVisible(boolean visible) {
		this.isVisible = visible;
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	
}
