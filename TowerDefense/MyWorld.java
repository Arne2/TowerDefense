import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.Map;
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
    private Location goal = new Location(5,5);
    private final int cellSize = 20;

    private Set<Location> locations = new HashSet();
    private Map distanceMap;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        super(200, 200, 1);
        init();
    }

    public void calculateField(){
        locations.clear();
        for (int x = 0; x < getWidth()/getTowerCellSize(); x++){
            for (int y = 0; y < getHeight()/getTowerCellSize(); y++){
                if(getObjectsAt(x*getTowerCellSize() + getTowerCellSize()/2, y*getTowerCellSize() + getTowerCellSize()/2, null).isEmpty()){
                    locations.add(Location.of(x,y));
                }
            }
        }

        distanceMap = FastMarching.use(this);
    }
    
    public void init(){
        this.goal = new Location(5,5);
    }
    
    private void setGoal(Location goal){
        this.goal = goal;
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

    public Map getMap() {
        return distanceMap;
    }

    public void printDistanceField(){
        System.out.println(StringView.toStringDistanceMap(distanceMap));
    }
    
    public void printField(){
        System.out.println(StringView.toStringMap(this));
    }
}
