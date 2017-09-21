import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Movable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Movable extends Actor
{
    private double x;
    private double y;
    private boolean moved = true;

    enum Direction {
        LEFT(Math.PI/2*3), LEFT_UP(Math.PI/4*5), UP(Math.PI), RIGHT_UP(Math.PI/4*3), RIGHT(Math.PI/2), RIGHT_DOWN(Math.PI/4), DOWN(0d), LEFT_DOWN(Math.PI/4*7);

        private final double direction;
        
        private Direction(double direction) {
            this.direction = direction;
        }

        public double getDirection() {
            return direction;
        }
    }

    public void moveBy(double dx, double dy) {
        this.x += dx;
        this.y += dy;
        this.setLocation((int)x, (int)y);
    }

    public void moveInDirection(double direction, double speed) {
        double dx = Math.sin(direction) * speed;
        double dy = Math.cos(direction) * speed;
        this.moveBy(dx, dy);
    }

    public void moveInDirection(Direction direction, double speed) {
        double dx = Math.sin(direction.getDirection()) * speed;
        double dy = Math.cos(direction.getDirection()) * speed;
        this.moveBy(dx, dy);
    }

    public double getXPos() {
        return x;
    }

    public double getYPos() {
        return y;
    }

    /**
     * Act - do whatever the Movable wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {}    
}
