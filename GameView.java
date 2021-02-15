import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameView {

	private Stage window;

	public Pane pane;       // basic layout pane
	public Canvas canvas;   // canvas to draw game on
	public Label infoText;  // info at top of screen


	public GameView(Stage window) {
		this.window = window;
	}

	public void initWindow(int width, int height, EventHandler<KeyEvent> actionHandler, EventHandler<KeyEvent> releaseHandler) {
		pane = new Pane();       // a simple layout pane
		pane.setId("Breakout");  // Id to use in CSS file to style the pane if needed

		canvas = new Canvas(width,height);  
		pane.getChildren().add(canvas);     // add the canvas to the pane

		// infoText box for the score - a label which we position in front of
		// the canvas (by adding it to the pane after the canvas)
		infoText = new Label("BreakOut: Score = " + 10);
		infoText.setTranslateX(50);  // these commands setthe position of the text box
		infoText.setTranslateY(10);  // (measuring from the top left corner)
		pane.getChildren().add(infoText);  // add label to the pane

		Scene scene = new Scene(pane);   
				scene.getStylesheets().add("breakout.css");

		scene.setOnKeyPressed(actionHandler);
		scene.setOnKeyReleased(releaseHandler);

		// put the scene in the window and display it
		window.setScene(scene);
		window.show();
	}

	public void displayGameObj( GraphicsContext gc, GameObj go ) {
		gc.setFill( go.getColor() );
		if (go instanceof BallObj) gc.fillOval( go.getX(), go.getY(), go.getWidth(), go.getHeight() );
		else gc.fillRect( go.getX(), go.getY(), go.getWidth(), go.getHeight() );
	}

	// drawing the game image
	public void drawPicture(int score, GameObj... objects) {
		// get the 'paint brush' to pdraw on the canvas
		GraphicsContext gc = canvas.getGraphicsContext2D();

		// clear the whole canvas to white
		gc.setFill( Color.WHITE );
		gc.fillRect( 0, 0, 1200, 800 );
		
		for (GameObj obj : objects) if (obj.isVisible()) displayGameObj(gc, obj);

		// update the score
		infoText.setText("BreakOut: Score = " + score);
	}

}
