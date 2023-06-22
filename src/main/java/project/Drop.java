package project;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Drop {
    private static final double GRAVITY = 0.5;

    private double x;
    private double y;
    private double diameter;
    double fallDelay;
    double fallTimer;
    private double velocity;
    boolean disappeared;
    boolean isCollided = false;
    private Circle shape;

    public Drop(double x, double y, double fallDelay, Color color) {
        this.x = x;
        this.y = y;
        diameter = 10;
        velocity = 0;
        disappeared = false;
        this.fallDelay = fallDelay;
        fallTimer = 0;

        shape = new Circle(x, y, diameter / 2, color);
    }

    public void move(double speed) {
        velocity += GRAVITY * speed;
        y += velocity;
        shape.setCenterY(y);
    }

    public boolean isOutOfBounds() {
        return y >= CaveSimulation.CAVE_HEIGHT*1.1;
    }

    public void disappear() {
        disappeared = true;
        shape.setFill(Color.TRANSPARENT);
    }

    public Circle getShape() {
        return shape;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
