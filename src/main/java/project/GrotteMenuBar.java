package project;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class GrotteMenuBar extends MenuBar {
    private CaveSimulation simulation; // Instance de la classe CaveSimulation

    public GrotteMenuBar(CaveSimulation simulation) {
        this.simulation = simulation;

        // Crée le menu "File"
        Menu fileMenu = new Menu("File");

        // Crée l'élément de menu "Restart" et définit son événement de clic
        MenuItem restartMenuItem = new MenuItem("Restart");
        restartMenuItem.setOnAction(event -> {
            restartSimulation();
        });

        // Ajoute l'élément de menu "Restart" au menu "File"
        fileMenu.getItems().add(restartMenuItem);

        // Crée le menu "Speed"
        Menu speedMenu = new Menu("Speed");

        // Crée les éléments de menu "Slow", "Medium" et "Fast" et définit leurs événements de clic
        MenuItem slowMenuItem = new MenuItem("Slow");
        slowMenuItem.setOnAction(event -> setSpeed(0.25));

        MenuItem mediumMenuItem = new MenuItem("Medium");
        mediumMenuItem.setOnAction(event -> setSpeed(4));

        MenuItem fastMenuItem = new MenuItem("Fast");
        fastMenuItem.setOnAction(event -> setSpeed(8));

        // Ajoute les éléments de menu "Slow", "Medium" et "Fast" au menu "Speed"
        speedMenu.getItems().addAll(slowMenuItem, mediumMenuItem, fastMenuItem);

        // Ajoute les menus "File" et "Speed" à la barre de menu
        getMenus().add(fileMenu);
        getMenus().add(speedMenu);
    }

    private void restartSimulation() {
        simulation.resetSimulation(); // Réinitialise la simulation
        simulation.startSimulation(); // Démarre la simulation
    }

    private void setSpeed(double speed) {
        simulation.setDropSpeed(speed); // Définit la vitesse de chute des gouttes dans la simulation

        // Ajuste la fréquence de création de gouttes en fonction de la vitesse
        if (speed == 0.25) {
            simulation.setDropCreationFrequency(0.05); // Fréquence de création pour la vitesse "Slow"
        } else if (speed == 4) {
            simulation.setDropCreationFrequency(0.2); // Fréquence de création pour la vitesse "Medium"
        } else if (speed == 8) {
            simulation.setDropCreationFrequency(0.5); // Fréquence de création pour la vitesse "Fast"
        }
    }
}
