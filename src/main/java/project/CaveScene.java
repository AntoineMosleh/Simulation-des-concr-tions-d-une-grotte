package project;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;

public class CaveScene {
    private Pane pane; // Conteneur de la scène
    private Rectangle ceiling; // Rectangle représentant le plafond
    private Rectangle floor; // Rectangle représentant le sol
    private CaveSimulation simulation; // Instance de la classe de simulation de grotte

    public CaveScene(CaveSimulation simulation) {
        this.simulation = simulation;
        pane = new Pane(); // Initialisation du conteneur de la scène
        ceiling = new Rectangle(0, 0, simulation.getCaveWidth(), 10); // Création du rectangle pour le plafond
        floor = new Rectangle(0, simulation.getCaveHeight() - 10, simulation.getCaveWidth(), 10); // Création du rectangle pour le sol

        ceiling.setFill(simulation.getCeilingColor()); // Définition de la couleur du plafond
        floor.setFill(simulation.getFloorColor()); // Définition de la couleur du sol

        pane.getChildren().addAll(ceiling, floor); // Ajout des rectangles du plafond et du sol au conteneur de la scène
        simulation.setCaveScene(this); // Définition de la scène de la grotte dans la simulation
    }

    public void update() {
        // Suppression des cercles (gouttes d'eau) dont le rayon est inférieur ou égal à zéro
        pane.getChildren().removeIf(node -> node instanceof Circle && ((Circle) node).getRadius() <= 0);

        // Parcours des gouttes d'eau dans la simulation
        for (Drop drop : simulation.getDrops()) {
            // Ajout de la forme de la goutte d'eau à la scène si elle n'est pas déjà présente et si elle n'a pas disparu
            if (!pane.getChildren().contains(drop.getShape()) && !drop.disappeared) {
                pane.getChildren().add(drop.getShape());
            }
        }

        // Parcours des structures dans la simulation
        for (Structure structure : simulation.getStructures()) {
            // Ajout de la forme de la structure à la scène si elle n'est pas déjà présente
            if (!pane.getChildren().contains(structure.getShape())) {
                pane.getChildren().add(structure.getShape());
            }
        }
    }

    public Pane getPane() {
        return pane; // Renvoie le conteneur de la scène
    }
}
