package project;

import java.util.Random;

public class Stalactite extends Structure {
    private Random random = new Random();

    private Random random = new Random();

    public Stalactite(double startX, double startY, double height, double thickness, CaveSimulation simulation) {
        super(startX, startY, height, thickness, simulation);
    }


    public void increaseHeight() {
        double randomFactor = random.nextDouble(); // Génère un nombre aléatoire entre 0 et 1
        if (randomFactor < 0.01) {
            height = height/2; // Divise la hauteur avec une probabilité de 1%
        } else {
            height = height+1.5; // Augmente la hauteur avec une probabilité de 99%
        }
        shape.setHeight(height);
    }

    public void increaseThickness(){
        thickness+= 0.1;
        shape.setWidth(thickness);
    }

    @Override
    public boolean collidesWith(Drop drop) {
        return drop.getShape().getCenterX() >= startX && drop.getShape().getCenterX() <= startX + thickness
                && drop.getShape().getCenterY() >= startY && drop.getShape().getCenterY() <= startY + height;
    }
}
