import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemySpawn here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemySpawn extends Actor
{
    /**
     * Act - do whatever the EnemySpawn wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
    public void generateEnemy(){
        Enemy enemy = new Enemy(20, 1);
        getWorld().addObject(enemy, getX(), getY());
        enemy.setPos();
    }
}
