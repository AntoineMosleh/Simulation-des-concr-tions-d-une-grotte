package project;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CaveSimulation {
    // Constantes pour la largeur et la hauteur de la grotte
    static final int CAVE_WIDTH = 800;
    static final int CAVE_HEIGHT = 600;

    // Couleurs utilisées dans la simulation
    private static final Color CEILING_COLOR = Color.BROWN;
    private static final Color FLOOR_COLOR = Color.BROWN;
    private static final Color DROP_COLOR = Color.BLUE;
    private static final Color STRUCTURE_COLOR = Color.GRAY;

    // Variables de la simulation
    private double dropSpeed; // Vitesse des gouttes
    private double dropCreationFrequency; // Fréquence de création des gouttes
    private CaveScene caveScene; // Scène de la grotte
    private List<Drop> drops; // Liste des gouttes
    private List<Structure> structures; // Liste des structures (stalactites et stalagmites)
    private Random random; // Générateur de nombres aléatoires

    public CaveSimulation() {
        caveScene = null;
        drops = new ArrayList<>();
        structures = new ArrayList<>();
        random = new Random();
        dropSpeed = 1.0; // Vitesse par défaut des gouttes
        dropCreationFrequency = 0.2; // Fréquence de création des gouttes par défaut
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
        if (random.nextDouble() < dropCreationFrequency) { // Vérifie si une nouvelle goutte doit être créée
            double x = random.nextDouble() * CAVE_WIDTH; // Position horizontale aléatoire de la goutte
            double fallDelay = random.nextDouble() * 2.0; // Délai de chute aléatoire pour la goutte
            Drop drop = new Drop(x, 0, fallDelay, DROP_COLOR); // Crée une nouvelle goutte à la position (x, 0)

            // Vérifie s'il y a une stalactite dans un rayon de 30 autour de la position de la goutte
            boolean stalactiteFound = false;
            for (Structure structure : structures) {
                if (structure instanceof Stalactite) {
                    double distance = Math.abs(x - structure.getShape().getX());
                    if (distance <= 30) {
                        ((Stalactite) structure).increaseHeight(); // Augmente la hauteur de la stalactite
                        ((Stalactite) structure).increaseThickness(); // Augmente l'épaisseur de la stalactite
                        stalactiteFound = true;
                        break;
                    }
                }
            }

            // Si aucune stalactite n'est trouvée, crée une nouvelle stalactite à la position de la goutte
            if (!stalactiteFound) {
                Structure stalactite = new Stalactite(x, 0, 1, 10, this);
                structures.add(stalactite);
            }

            drops.add(drop); // Ajoute la goutte à la liste des gouttes
        }
    }

    private void updateDrops() {
        List<Drop> dropsToRemove = new ArrayList<>();

        for (Drop drop : drops) {
            if (drop.fallTimer >= drop.fallDelay) { // Vérifie si la goutte a atteint son délai de chute
                drop.move(dropSpeed); // Fait avancer la goutte en fonction de la vitesse

                if (drop.isOutOfBounds()) { // Vérifie si la goutte est en dehors des limites de la grotte
                    dropsToRemove.add(drop); // Ajoute la goutte à la liste des gouttes à supprimer
                }
            } else {
                drop.fallTimer += 0.016; // Incrémente le compteur de délai de chute par le temps écoulé (supposé à 60 FPS)
            }
        }

        drops.removeAll(dropsToRemove); // Supprime les gouttes de la liste des gouttes
    }

    private void checkCollisions() {
        List<Drop> dropsToRemove = new ArrayList<>();
        List<Structure> structuresToAdd = new ArrayList<>();

        for (Drop drop : drops) {
            boolean collided = false;
            for (Structure structure : structures) {
                if (structure.collidesWith(drop)) { // Vérifie s'il y a une collision entre la goutte et une structure
                    if (structure instanceof Stalagmite) {
                        double distance = Math.abs(drop.getX() - structure.getShape().getX());
                        if (distance <= 3) { // Vérifie si la distance entre la goutte et la structure est inférieure ou égale à 3
                            ((Stalagmite) structure).increaseHeight(); // Augmente la hauteur de la stalagmite
                            ((Stalagmite) structure).increaseThickness(); // Augmente l'épaisseur de la stalagmite
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

            // Si aucune stalagmite n'est trouvée, crée une nouvelle stalagmite à la position de la goutte
            if (!stalagmiteFound) {
                Structure stalagmite = new Stalagmite(drop.getX(), CaveSimulation.CAVE_HEIGHT - 1, 1, 10, this);
                structuresToAdd.add(stalagmite);
            }

            //dropsToRemove.add(drop);
        }

        drops.removeAll(dropsToRemove); // Supprime les gouttes de la liste des gouttes
        structures.addAll(structuresToAdd); // Ajoute les nouvelles structures à la liste des structures
    }

    public void resetSimulation() {
        drops.clear(); // Efface la liste des gouttes
        structures.clear(); // Efface la liste des structures
        caveScene.getPane().getChildren().clear(); // Efface les éléments graphiques de la scène de la grotte
        // Réinitialise les autres propriétés de la simulation si nécessaire
    }

    public void setDropSpeed(double speed) {
        dropSpeed = speed; // Modifie la vitesse des gouttes
    }

    public void setDropCreationFrequency(double frequency) {
        dropCreationFrequency = frequency; // Modifie la fréquence de création des gouttes
    }

    public List<Structure> getStructures() {
        return structures; // Retourne la liste des structures
    }

    public int getCaveWidth() {
        return CAVE_WIDTH; // Retourne la largeur de la grotte
    }

    public int getCaveHeight() {
        return CAVE_HEIGHT; // Retourne la hauteur de la grotte
    }

    public Color getCeilingColor() {
        return CEILING_COLOR; // Retourne la couleur du plafond
    }

    public Color getFloorColor() {
        return FLOOR_COLOR; // Retourne la couleur du sol
    }

    public Color getStructureColor() {
        return STRUCTURE_COLOR; // Retourne la couleur des structures
    }

    public List<Drop> getDrops() {
        return drops; // Retourne la liste des gouttes
    }
}
