import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.List;
import java.util.ArrayList;
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
    private static final int MENUSIZE = 100;
    private List<EnemySpawn> spawns = new ArrayList();

    private Set<Location> locations = new HashSet();
    private Map distanceMap;
    
    // act helping variables
    private int tmp = 0;
    private int step = 50;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        super(800, 800, 1);
        init();
        calculateField();
    }
    
    public void act(){
        if(tmp == 0){
            spawns.stream().forEach(EnemySpawn::generateEnemy);
            tmp += step;
        }
        tmp--;
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
        int goalX = getWidth() - 3 * getTowerCellSize();
        int goalY = getHeight() - 3 * getTowerCellSize() - MENUSIZE;
        this.goal = new Location(goalX/getTowerCellSize(),goalY/getTowerCellSize());
        this.addObject(new SafeHaven(), goalX, goalY);
        buildBorder();
        addSpawns();
    }
    
    public void buildBorder(){
        for(int x = getTowerCellSize()/2; x < getWidth(); x+=getTowerCellSize()){
            if( x == getTowerCellSize()/2 || x == getWidth() - getTowerCellSize()/2){
                for(int y = MENUSIZE; y <= getHeight() - MENUSIZE; y+=getTowerCellSize()){
                    addObject(new Wall(), x, y);
                }
            } else {
                addObject(new Wall(), x, MENUSIZE);
                addObject(new Wall(), x, getHeight() - MENUSIZE);
            }
            
        }
    }
    
    public void addSpawns(){
        EnemySpawn spawn = new EnemySpawn();
        spawns.add(spawn);
        addObject(spawn, 4 * getTowerCellSize(), 2 * getTowerCellSize() + MENUSIZE);
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
