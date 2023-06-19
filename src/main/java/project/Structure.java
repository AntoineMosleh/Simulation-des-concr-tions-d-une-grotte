package project;

import javafx.scene.shape.Rectangle;


public abstract class Structure {
    protected double startX;
    protected double startY;
    protected double height;
    protected double thickness;

    protected Rectangle shape;

    public Structure(double startX, double startY, double height, double thickness, CaveSimulation simulation) {
        this.startX = startX;
        this.startY = startY;
        this.height = height;
        this.thickness = thickness;

        shape = new Rectangle(startX, startY, thickness, height);
        shape.setFill(simulation.getStructureColor());
    }

    public abstract boolean collidesWith(Drop drop);

    public Rectangle getShape() {
        return shape;
    }

    public double getHeight() {
        return height;
    }
}
