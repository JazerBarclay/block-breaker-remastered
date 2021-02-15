import java.util.ArrayList;
import javafx.scene.paint.Color;

public class GameModel {

	private int SCREEN_WIDTH = 1200;
	private int SCREEN_HEIGHT = 800;

	private int BALL_SIZE = 40;

	private int BAT_WIDTH = 120;
	private int BAT_HEIGHT = 10;

	private int BRICK_COLS = 30;
	private int BRICK_ROWS = 20;
	private int BRICK_HEIGHT = 20;

	private int BALL_SPEED = 2;
	private int BAT_SPEED = 5;

	private int SCORE = 0;
	
	private int BRICK_SCORE = 50;
	private int PENALTY_SCORE = -200;
	

	private BatObj bat;
	private BallObj ball;
	private BrickObj[] bricks;

	
	public GameModel() {
		// New bat at bottom center
		bat = new BatObj(new Vector((getScreenWidth()/2)-(BAT_WIDTH/2), getScreenHeight()-BAT_HEIGHT-50), BAT_WIDTH, BAT_HEIGHT, Color.BLUE);
		
		// New ball at center center
		ball = new BallObj(new Vector((getScreenWidth()/2)-(BALL_SIZE/2), getScreenHeight()-BAT_HEIGHT-50-BALL_SIZE), BALL_SIZE, Color.RED);
		
		// New collection of bricks

//		bricks = new BrickObj[] {new BrickObj(new Vector(0, 0), SCREEN_WIDTH, BRICK_HEIGHT, Color.BLACK)};
		ArrayList<BrickObj> brickList = new ArrayList<BrickObj>();
		
		Color colors[] = {Color.RED, Color.YELLOW, Color.PINK, Color.GREEN, Color.ORANGE, Color.YELLOW, Color.BLUE};

		int brickWidth = SCREEN_WIDTH/BRICK_COLS;
		for (int y = 0; y <= BRICK_ROWS * BRICK_HEIGHT; y += BRICK_HEIGHT) {
		    for (int x = 0; x <= SCREEN_WIDTH; x += brickWidth) {
		        
		        
		        int rand = (int)(Math.random() * (colors.length - 0));
		        brickList.add(new BrickObj(new Vector(x, y+50), brickWidth, BRICK_HEIGHT, colors[rand]));
		    }
		}
		
		bricks = brickList.toArray(new BrickObj[brickList.size()]);
		
	
		ball.setMoveVector(new Vector(getBallSpeed(),getBallSpeed()));
	}
	
	public int getScreenWidth() {
		return SCREEN_WIDTH;
	}
	
	public int getScreenHeight() {
		return SCREEN_HEIGHT;
	}
	
	public GameObj[] getAllObjects() {
		GameObj[] allObj = new GameObj[bricks.length+2];
		allObj[0] = bat;
		allObj[1] = ball;
		for (int i = 0; i < bricks.length; i++) allObj[i+2] = bricks[i];
		return allObj;
	}
	
	public BallObj getBallObject() {
		return ball;
	}
	
	public void setBatSpeed(int speed) {
		this.BAT_SPEED = speed;
	}
	
	public int getBatSpeed() {
		return BAT_SPEED;
	}
	
	public void setBallSpeed(int speed) {
		this.BALL_SPEED = speed;
	}
	
	public int getBallSpeed() {
		return BALL_SPEED;
	}
	
	public void setScore(int score) {
		this.SCORE = score;
	}
	
	public void addScore(int points) {
		this.SCORE+=points;
	}
	
	public void addScore() {
		SCORE+=BRICK_SCORE;
	}
	
	public void addPenalty() {
		SCORE+=PENALTY_SCORE;
	}
	
	public int getScore() {
		return SCORE;
	}
	
	public void setPentaltyPoints(int penalty) {
		this.PENALTY_SCORE = penalty;
	}
	
	public int getPenaltyPoints() {
		return PENALTY_SCORE;
	}

	public void setBatMoveVector(Vector v) {
		bat.setMoveVector(v);
	}

	public void setBallMoveVector(Vector v) {
		ball.setMoveVector(v);
	}
	
	public void updateBatPosition() {
		updateObjPosition(bat);
	}
	
	public void updateBalPosition() {
		updateObjPosition(ball);
	}
	
	public void updateObjPosition(GameObj go) {
		go.updatePosition();
	}
	
}
