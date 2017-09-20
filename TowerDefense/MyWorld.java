import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.Set;
import java.util.HashSet;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private Location goal;
    private final int cellSize = 20;

    private Set<Location> locations = new HashSet();
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        super(200, 200, 1);
    }

    public void calculateField(){
        System.out.println("HEY: " + getObjects(Actor.class));
        locations.clear();
        for (int x = 0; x < getWidth()/getTowerCellSize(); x++){
            for (int y = 0; y < getHeight()/getTowerCellSize(); y++){
                //System.out.println("X: " + (x*getTowerCellSize() + getTowerCellSize()/2) + " - Y: " + (y*getTowerCellSize() + getTowerCellSize()/2));
                if(getObjectsAt(x*getTowerCellSize() + getTowerCellSize()/2, y*getTowerCellSize() + getTowerCellSize()/2, null).isEmpty()){
                    locations.add(Location.of(x,y));
                } else {
                    System.out.println("HEY BOI");
                }
            }
        }
    }
    
    public Location getGoal(){
        return goal;
    }
    
    public int getTowerCellSize(){
        return cellSize;
    }

    public Set<Location> getLocations() {
        return locations;
    }
}
