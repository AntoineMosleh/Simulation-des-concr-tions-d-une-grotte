package project;

public class Stalagmite extends Structure {

    public Stalagmite(double startX, double startY, double height, double thickness, CaveSimulation simulation) {
        super(startX, startY, height, thickness, simulation);
    }

    public void increaseHeight() {
        height++;
        shape.setHeight(height);
        startY--;
        shape.setY(startY);
    }

    public void increaseThickness(){
        thickness+= 0.1;
        shape.setWidth(thickness);
    }

    @Override
    public boolean collidesWith(Drop drop) {
        return drop.getShape().getCenterX() >= startX && drop.getShape().getCenterX() <= startX + thickness
                && drop.getShape().getCenterY() >= startY - drop.getShape().getRadius();
    }
}
