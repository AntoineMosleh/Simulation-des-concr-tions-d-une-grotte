package project;

import javafx.scene.shape.Rectangle;

public abstract class Structure {
    protected double startX;
    protected double startY;
    protected double height;
    protected double thickness;

    protected Rectangle shape;

    // Constructeur de la classe abstraite Structure
    public Structure(double startX, double startY, double height, double thickness, CaveSimulation simulation) {
        this.startX = startX;
        this.startY = startY;
        this.height = height;
        this.thickness = thickness;

        // Crée une nouvelle instance de Rectangle en utilisant les paramètres startX, startY, thickness et height
        shape = new Rectangle(startX, startY, thickness, height);
        shape.setFill(simulation.getStructureColor()); // Remplit la forme du rectangle avec la couleur de la structure de la simulation
    }

    // Méthode abstraite qui vérifie si une goutte entre en collision avec la structure
    public abstract boolean collidesWith(Drop drop);

    // Méthode pour obtenir la forme (Rectangle) de la structure
    public Rectangle getShape() {
        return shape;
    }

    // Méthode pour obtenir la hauteur de la structure
    public double getHeight() {
        return height;
    }
}

