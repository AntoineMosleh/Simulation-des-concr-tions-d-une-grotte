package project;

public class Stalagmite extends Structure {

    // Constructeur de la classe Stalagmite
    public Stalagmite(double startX, double startY, double height, double thickness, CaveSimulation simulation) {
        super(startX, startY, height, thickness, simulation);
    }

    // Méthode pour augmenter la hauteur de la stalagmite
    public void increaseHeight() {
        height += 2; // Ajoute 2 à la hauteur actuelle
        shape.setHeight(height); // Met à jour la hauteur de la forme de la stalagmite
        startY -= 2; // Déplace la position de départ vers le bas de 2 unités
        shape.setY(startY); // Met à jour la position Y de la forme de la stalagmite
    }

    // Méthode pour augmenter l'épaisseur de la stalagmite
    public void increaseThickness() {
        thickness += 0.1; // Ajoute 0.1 à l'épaisseur actuelle
        shape.setWidth(thickness); // Met à jour la largeur de la forme de la stalagmite
    }

    // Méthode qui vérifie si une goutte entre en collision avec la stalagmite
    @Override
    public boolean collidesWith(Drop drop) {
        // Vérifie si les coordonnées X et Y de la position de la goutte se trouvent à l'intérieur des limites de la stalagmite
        // La position Y de la goutte est comparée avec la position de départ de la stalagmite moins le rayon de la goutte
        return drop.getShape().getCenterX() >= startX && drop.getShape().getCenterX() <= startX + thickness
                && drop.getShape().getCenterY() >= startY - drop.getShape().getRadius();
    }
}
