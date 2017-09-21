import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.List;

/**
 * Write a description of class Tower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tower extends Obstacle
{
    private int cooldown;
    private int damage;
    private int timeToAttack = 0;
    private boolean singleTarget;

    private int range;

    public Tower(int cooldown, int damage, int range, boolean singleTarget) {
        this.cooldown = cooldown;
        this.damage = damage;
        this.range = range;
        this.singleTarget = singleTarget;
    }

    /**
     * Act - do whatever the Tower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(timeToAttack<=0) {
            shoot();
            timeToAttack = cooldown;
        } else {
            timeToAttack--;
        }
    }

    public void shoot(){
        List enemies = getObjectsInRange(range, Enemy.class);
        if(enemies.isEmpty()){
            return;
        }
        if(singleTarget){
            this.getWorld().removeObject((Actor)enemies.get(0));
        } else {
            this.getWorld().removeObjects(enemies);
        }
    }
}
