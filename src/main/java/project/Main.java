package project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        CaveSimulation simulation = new CaveSimulation();
        CaveScene caveScene = new CaveScene(simulation);

        GrotteMenuBar menuBar = new GrotteMenuBar(simulation);

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(caveScene.getPane());

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Cave Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();

        simulation.startSimulation();
    }
}
