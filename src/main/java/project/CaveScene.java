package project;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;

public class CaveScene {
    private Pane pane;
    private Rectangle ceiling;
    private Rectangle floor;
    private CaveSimulation simulation;

    public CaveScene(CaveSimulation simulation) {
        this.simulation = simulation;
        pane = new Pane();
        ceiling = new Rectangle(0, 0, simulation.getCaveWidth(), 10);
        floor = new Rectangle(0, simulation.getCaveHeight() - 10, simulation.getCaveWidth(), 10);

        ceiling.setFill(simulation.getCeilingColor());
        floor.setFill(simulation.getFloorColor());

        pane.getChildren().addAll(ceiling, floor);
        simulation.setCaveScene(this);
    }

    public void update() {
        pane.getChildren().removeIf(node -> node instanceof Circle && ((Circle) node).getRadius() <= 0);
        for (Drop drop : simulation.getDrops()) {
            if (!pane.getChildren().contains(drop.getShape()) && !drop.disappeared) {
                pane.getChildren().add(drop.getShape());
            }
        }
        for (Structure structure : simulation.getStructures()) {
            if (!pane.getChildren().contains(structure.getShape())) {
                pane.getChildren().add(structure.getShape());
            }
        }
    }

    public Pane getPane() {
        return pane;
    }
}
