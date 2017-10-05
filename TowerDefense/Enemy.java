import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Map;

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Movable
{
    private int health;
    private double speed;
    
    public Enemy(int health, double speed){
        this.health = health;
        this.speed = speed;
    }
    
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(health <= 0){
            getWorld().removeObject(this);
            return;
        }
        
        moveToGoal();
    }
    
    public void moveToGoal(){
        int cellsize = ((MyWorld)getWorld()).getTowerCellSize();
        int x = getX()/cellsize;
        int y = getY()/cellsize;
        Location location = Location.of(x,y);
        System.out.println("X: " + x + " Y: " + y);
        
        
        // Remove if already at goal
        if(((MyWorld) getWorld()).getGoal().equals(location)){
            getWorld().removeObject(this);
        } else{
            super.moveInDirection(direction(x, y), speed);
        }
    }
    
    private Map<Location,Double> getMap(){
        return ((MyWorld)getWorld()).getMap();
    }
    
    private double direction(int x, int y){
        //System.out.println("X: " + x + " Y: " + y);
        // This might be done better. But it was a fast hack ;)       
        Direction dir = Direction.UP;
        Double best = getValue(x,y-1);        
        
        Double tmp = getValue(x-1,y);
        if(tmp.compareTo(best) > 0){
            dir = Direction.LEFT;
            best = tmp;
        }
        tmp = getValue(x+1,y);
        if(tmp.compareTo(best) > 0){
            dir = Direction.RIGHT;
            best = tmp;
        }
        tmp = getValue(x,y+1);
        if(tmp.compareTo(best) > 0){
            dir = Direction.DOWN;
            best = tmp;
        }
        tmp = getValue(x-1,y-1);
        if(tmp.compareTo(best) > 0){
            dir = Direction.LEFT_UP;
            best = tmp;
        }
        tmp = getValue(x+1,y-1);
        if(tmp.compareTo(best) > 0){
            dir = Direction.RIGHT_UP;
            best = tmp;
        }        
        tmp = getValue(x-1,y+1);
        if(tmp.compareTo(best) > 0){
            dir = Direction.LEFT_DOWN;
            best = tmp;
        }
        tmp = getValue(x+1,y+1);
        if(tmp.compareTo(best) > 0){
            dir = Direction.RIGHT_DOWN;
            best = tmp;
        }
        return dir.getDirection();
    }
    
    private Double getValue(int x, int y){
        Double result = getMap().get(Location.of(x,y));
        if(result == null){
            return new Double(Double.MIN_VALUE);
        }
        return result;
    }
}
