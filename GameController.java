import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class GameController {

	private GameModel model;
	private GameView view;

	private boolean leftPressed = false, rightPressed = false;

	public GameController(GameModel model, GameView view) {
		this.model = model;
		this.view = view;
		initGame();
	}

	private void initGame() {
		view.initWindow(model.getScreenWidth(), model.getScreenHeight(), keyPressedHandler(), keyReleasedHandler());
	}

	// Handles keyboard events from the GameView initWindow() method
	private EventHandler<KeyEvent> keyPressedHandler() {
		return new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch ( event.getCode() ) {
				case A:
				case LEFT:
					leftPressed = true;
					break;
				case D:
				case RIGHT:
					rightPressed = true;
					break;
				default:
					break;
				}
			}
		};
	}

	// Handles keyboard events from the GameView initWindow() method
	private EventHandler<KeyEvent> keyReleasedHandler() {
		return new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch ( event.getCode() ) {
				case A:
				case LEFT:
					leftPressed = false;
					break;
				case D:
				case RIGHT:
					rightPressed = false;
					break;
				default:
					break;
				}
			}
		};
	}

	private void moveBat() {
		if (leftPressed && !rightPressed) model.setBatMoveVector(new Vector(-model.getBatSpeed(), 0));
		if (!leftPressed && rightPressed) model.setBatMoveVector(new Vector(model.getBatSpeed(), 0));
		if (!leftPressed && !rightPressed) model.setBatMoveVector(new Vector(0, 0));
	}
	
	public void startGame() {
		Thread t = new Thread( this::runGame );     // create a thread running the runGame method
		t.setDaemon(true);                          // Tell system this thread can die when it finishes
		t.start();                                  // Start the thread running
	}

	private void runGame() {
		try {
			while (true) {
				// Handle actions from input
				Platform.runLater(() -> {
					
					// Handle key pressed and released for movement
					moveBat();

					// Get the ball object
					BallObj ball = model.getBallObject();

					// Keep ball in bounds
					Vector newPosition = Vector.addVectors(ball.getPosition(), ball.getMoveVector());
					if (newPosition.getX() < 0 || newPosition.getX() > model.getScreenWidth()-ball.getWidth()) ball.getMoveVector().setX(ball.getMoveVector().getX() * -1);
					if (newPosition.getY() < 0 || newPosition.getY() > model.getScreenHeight()-ball.getHeight()) ball.getMoveVector().setY(ball.getMoveVector().getY() * -1);

					// If ball hits ground, score penalty
					if (newPosition.getY() > model.getScreenHeight()-ball.getHeight()) model.addPenalty();

					// For each object in the model
					for (GameObj obj : model.getAllObjects()) {
						// If the object is not active/visible, don't check collision
						if (obj.isVisible()) {
							
							// For all objects that are not the ball, check if the ball is colliding
							if (!(obj instanceof BallObj)) {
								boolean separate =
									ball.getX() >= obj.getX()+obj.getWidth()     ||
									ball.getX()+ball.getWidth() <= obj.getX()    ||
									ball.getY() >= obj.getY()+obj.getHeight()    ||
									ball.getY()+ball.getHeight() <= obj.getY();
									
								boolean ix = false, iy = false, nx = false, ny = false;
								// Is currently intersecting?
								if (ball.getX() < obj.getX()+obj.getWidth() && ball.getX()+ball.getWidth() > obj.getX()) ix = true;
								if (ball.getY() < obj.getY()+obj.getHeight() && ball.getY()+ball.getHeight() > obj.getY()) iy = true;
								// Will intersect?
								if (ball.getX()+ball.getMoveVector().getX() < obj.getX()+obj.getWidth() && ball.getX()+ball.getMoveVector().getX()+ball.getWidth() > obj.getX()) nx = true;
								if (ball.getY()+ball.getMoveVector().getY() < obj.getY()+obj.getHeight() && ball.getY()+ball.getMoveVector().getY()+ball.getHeight() > obj.getY()) ny = true;
								
								if ((nx && ny) && obj instanceof BrickObj) {
									model.addScore();
									obj.setVisible(false);
								}
								
								if ((nx && ny) && obj instanceof BatObj) {
									ball.setMoveVector(new Vector(ball.getMoveVector().getX()+(obj.getMoveVector().getX()/5), ball.getMoveVector().getY()+(obj.getMoveVector().getY()/5)));
								}
								
								// If intersecting x but not y and will intersect y, reflect on y axis
								if (ix && !iy && ny) {
									// Reflect y
									Debug.trace("Reflect Y");
									ball.setMoveVector(new Vector(ball.getMoveVector().getX(), ball.getMoveVector().getY()*-1));
								// If intersecting y but not x and will intersect x, reflect on x axis
								} else if (!ix && iy && nx) {
									// Reflect x
									Debug.trace("Reflect X");
									ball.setMoveVector(new Vector(ball.getMoveVector().getX()*-1, ball.getMoveVector().getY()));
								// If neither are intersecting but will on both, reflect on both axis)
								} else if (!ix && !iy && nx && ny) {
									// Reflect x and y
									Debug.trace("Reflect X AND Y");
									ball.setMoveVector(new Vector(ball.getMoveVector().getX()*-1, ball.getMoveVector().getY()*-1));
								}
								
							}
						}
						// Update with set data
						obj.updatePosition();
					}

					// Draw to screen all (updated) model objects
					view.drawPicture( model.getScore(), model.getAllObjects());
				});
				Thread.sleep( 10 ); // wait a few milliseconds
			}
		} catch (Exception e) {
			e.printStackTrace();
			Debug.error("Model::runAsSeparateThread error: " + e.getMessage() );
		}
	}

}
