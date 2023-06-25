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
        // Crée une instance de la classe CaveSimulation
        CaveSimulation simulation = new CaveSimulation();

        // Crée une instance de la classe CaveScene en lui passant la simulation
        CaveScene caveScene = new CaveScene(simulation);

        // Crée une instance de la classe GrotteMenuBar en lui passant la simulation
        GrotteMenuBar menuBar = new GrotteMenuBar(simulation);

        // Crée un conteneur BorderPane pour organiser les éléments de la fenêtre
        BorderPane root = new BorderPane();
        root.setTop(menuBar); // Place la barre de menu en haut du BorderPane
        root.setCenter(caveScene.getPane()); // Place la scène de la grotte au centre du BorderPane

        // Crée une instance de la classe Scene en utilisant le BorderPane comme contenu et les dimensions spécifiées
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Cave Simulation"); // Définit le titre de la fenêtre principale
        primaryStage.setScene(scene); // Définit la scène de la fenêtre principale
        primaryStage.show(); // Affiche la fenêtre principale

        simulation.startSimulation(); // Démarre la simulation de la grotte
    }
}

