package project;

import java.util.Random;

public class Stalactite extends Structure {
    private Random random = new Random();

    // Constructeur de la classe Stalactite
    public Stalactite(double startX, double startY, double height, double thickness, CaveSimulation simulation) {
        super(startX, startY, height, thickness, simulation);
    }

    // Méthode pour augmenter la hauteur de la stalactite
    public void increaseHeight() {
        double randomFactor = random.nextDouble(); // Génère un nombre aléatoire entre 0 et 1

        // Vérifie si le nombre aléatoire est inférieur à 0.01 (1% de probabilité)
        if (randomFactor < 0.01) {
            height = height / 2; // Divise la hauteur avec une probabilité de 1%
        } else {
            height = height + 1.5; // Augmente la hauteur avec une probabilité de 99%
        }

        shape.setHeight(height); // Met à jour la hauteur de la forme de la stalactite
    }

    // Méthode pour augmenter l'épaisseur de la stalactite
    public void increaseThickness() {
        thickness += 0.1; // Ajoute 0.1 à l'épaisseur actuelle
        shape.setWidth(thickness); // Met à jour la largeur de la forme de la stalactite
    }

    // Méthode qui vérifie si une goutte entre en collision avec la stalactite
    @Override
    public boolean collidesWith(Drop drop) {
        // Vérifie si les coordonnées X et Y de la position de la goutte se trouvent à l'intérieur des limites de la stalactite
        return drop.getShape().getCenterX() >= startX && drop.getShape().getCenterX() <= startX + thickness
                && drop.getShape().getCenterY() >= startY && drop.getShape().getCenterY() <= startY + height;
    }
}
