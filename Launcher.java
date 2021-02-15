 

import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage window) {
		
        Debug.setDebugMode(true);         
        Debug.trace("Main :: start : Breakout starting");
        
        GameModel model = new GameModel();
        GameView view = new GameView(window);
        GameController controller = new GameController(model, view);
        
        controller.startGame();
        
	}
}
