package project;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class GrotteMenuBar extends MenuBar {
    private CaveSimulation simulation;

    public GrotteMenuBar(CaveSimulation simulation) {
        this.simulation = simulation;

        Menu fileMenu = new Menu("File");
        MenuItem restartMenuItem = new MenuItem("Restart");
        restartMenuItem.setOnAction(event -> {
            restartSimulation();
        });
        fileMenu.getItems().add(restartMenuItem);

        Menu speedMenu = new Menu("Speed");
        MenuItem slowMenuItem = new MenuItem("Slow");
        slowMenuItem.setOnAction(event -> setSpeed(0.25));
        MenuItem mediumMenuItem = new MenuItem("Medium");
        mediumMenuItem.setOnAction(event -> setSpeed(4));
        MenuItem fastMenuItem = new MenuItem("Fast");
        fastMenuItem.setOnAction(event -> setSpeed(8));
        speedMenu.getItems().addAll(slowMenuItem, mediumMenuItem, fastMenuItem);

        getMenus().add(fileMenu);
        getMenus().add(speedMenu);
    }

    private void restartSimulation() {
        simulation.resetSimulation();
        simulation.startSimulation();
    }
    private void setSpeed(double speed) {
        simulation.setDropSpeed(speed);

        // Ajustez la fréquence de création de gouttes en fonction de la vitesse
        if (speed == 0.25) {
            simulation.setDropCreationFrequency(0.05); // Fréquence de création pour la vitesse "Slow"
        } else if (speed == 4) {
            simulation.setDropCreationFrequency(0.2); // Fréquence de création pour la vitesse "Medium"
        } else if (speed == 8) {
            simulation.setDropCreationFrequency(0.5); // Fréquence de création pour la vitesse "Fast"
        }
    }
}
