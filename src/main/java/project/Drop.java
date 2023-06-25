package project;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Drop {
    private static final double GRAVITY = 0.5; // Constante de gravité pour la simulation des gouttes

    private double x; // Position en x de la goutte
    private double y; // Position en y de la goutte
    private double diameter; // Diamètre de la goutte
    double fallDelay; // Délai de chute de la goutte
    double fallTimer; // Temps écoulé depuis le début de la chute de la goutte
    private double velocity; // Vitesse de la goutte en chute
    boolean disappeared; // Indicateur si la goutte a disparu
    boolean isCollided = false; // Indicateur si la goutte a collisionné avec une structure
    private Circle shape; // Objet Circle de JavaFX représentant la forme graphique de la goutte

    public Drop(double x, double y, double fallDelay, Color color) {
        this.x = x;
        this.y = y;
        diameter = 10;
        velocity = 0;
        disappeared = false;
        this.fallDelay = fallDelay;
        fallTimer = 0;

        shape = new Circle(x, y, diameter / 2, color); // Crée un objet Circle avec les coordonnées (x, y), le rayon et la couleur spécifiés
    }

    public void move(double speed) {
        velocity += GRAVITY * speed; // Ajoute la valeur de la gravité multipliée par la vitesse à la vitesse actuelle de la goutte
        y += velocity; // Met à jour la position en y de la goutte en fonction de sa vitesse
        shape.setCenterY(y); // Met à jour la position y du cercle graphique représentant la goutte
    }

    public boolean isOutOfBounds() {
        return y >= CaveSimulation.CAVE_HEIGHT*1.1; // Vérifie si la goutte est en dehors des limites de la grotte
    }

    public Circle getShape() {
        return shape; // Retourne l'objet Circle représentant la forme graphique de la goutte
    }

    public double getX() {
        return x; // Retourne la position en x de la goutte
    }

}
