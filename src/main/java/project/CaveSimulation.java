package project;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CaveSimulation {
     static final int CAVE_WIDTH = 800;
     static final int CAVE_HEIGHT = 600;
    private static final Color CEILING_COLOR = Color.BROWN;
    private static final Color FLOOR_COLOR = Color.BROWN;
    private static final Color DROP_COLOR = Color.BLUE;
    private static final Color STRUCTURE_COLOR = Color.GRAY;

    private double dropSpeed;
    private double dropCreationFrequency;

    private CaveScene caveScene;
    private List<Drop> drops;
    private List<Structure> structures;
    private Random random;

    public CaveSimulation() {
        caveScene = null;
        drops = new ArrayList<>();
        structures = new ArrayList<>();
        random = new Random();
        dropSpeed = 1.0; // vitesse par defaut
        dropCreationFrequency = 0.2; // fréquence de création pour la vitesse par défaut
    }

    public void setCaveScene(CaveScene caveScene) {
        this.caveScene = caveScene;
    }

    public void startSimulation() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                caveScene.update();
            }
        }.start();
    }

    private void update() {
        createDrops();
        updateDrops();
        checkCollisions();
    }

    private void createDrops() {
        if (random.nextDouble() < dropCreationFrequency ){ // Adjust the drop creation frequency as needed
            double x = random.nextDouble() * CAVE_WIDTH;
            Drop drop = new Drop(x, 0, DROP_COLOR);

            // Vérifier s'il y a une stalactite dans un rayon de 20 autour de la goutte
            boolean stalactiteFound = false;
            for (Structure structure : structures) {
                if (structure instanceof Stalactite) {
                    double distance = Math.abs(x - structure.getShape().getX());
                    if (distance <= 30) {
                        ((Stalactite) structure).increaseHeight();
                        ((Stalactite) structure).increaseThickness();
                        stalactiteFound = true;
                        break;
                    }
                }
            }

            // Si aucune stalactite n'est trouvée, créer une nouvelle stalactite
            if (!stalactiteFound) {
                Structure stalactite = new Stalactite(x, 0, 1, 10, this);
                structures.add(stalactite);
            }

            drops.add(drop);
        }
    }



    private void updateDrops() {
        List<Drop> dropsToRemove = new ArrayList<>();

        for (Drop drop : drops) {
            drop.move(dropSpeed);

            if (drop.isOutOfBounds()) {
                dropsToRemove.add(drop);
            }
        }

        drops.removeAll(dropsToRemove);
    }




    private void checkCollisions() {
        List<Drop> dropsToRemove = new ArrayList<>();
        List<Structure> structuresToAdd = new ArrayList<>();

        for (Drop drop : drops) {
            boolean collided = false;




            for (Structure structure : structures) {
                if (structure.collidesWith(drop)) {
                    if (structure instanceof Stalagmite) {
                        double distance = Math.abs(drop.getX() - structure.getShape().getX());
                        if (distance <= 3-0) {
                            ((Stalagmite) structure).increaseHeight();
                            ((Stalagmite) structure).increaseThickness();
                            collided = true;
                            break;
                        }
                    }
                }
            }

            boolean stalagmiteFound = false;
            for (Structure structure : structures) {
                if (structure instanceof Stalagmite) {
                    double distance = Math.abs(drop.getX() - structure.getShape().getX());
                    if (distance <= 30) {
                        stalagmiteFound = true;
                        break;
                    }
                }
            }
            // Si aucune stalactite n'est trouvée, créer une nouvelle stalactite
            if (!stalagmiteFound) {
                Structure stalagmite = new Stalagmite(drop.getX(), CaveSimulation.CAVE_HEIGHT - 1, 1, 10, this);
                structuresToAdd.add(stalagmite);
            }

            //dropsToRemove.add(drop);
        }

        drops.removeAll(dropsToRemove);
        structures.addAll(structuresToAdd);
    }


    public void resetSimulation() {
        drops.clear();
        structures.clear();
        caveScene.getPane().getChildren().clear();
        // Réinitialise les autres propriétés de la simulation si nécessaire
    }

    public void setDropSpeed(double speed) {
        dropSpeed = speed;
    }

    public void setDropCreationFrequency(double frequency) {
        dropCreationFrequency = frequency;
    }
    public void addStructure(Structure structure) {
        structures.add(structure);
    }

    public List<Structure> getStructures() {
        return structures;
    }

    public int getCaveWidth() {
        return CAVE_WIDTH;
    }

    public int getCaveHeight() {
        return CAVE_HEIGHT;
    }

    public Color getCeilingColor() {
        return CEILING_COLOR;
    }

    public Color getFloorColor() {
        return FLOOR_COLOR;
    }

    public Color getDropColor() {
        return DROP_COLOR;
    }

    public Color getStructureColor() {
        return STRUCTURE_COLOR;
    }

    public List<Drop> getDrops() {
        return drops;
    }
}
