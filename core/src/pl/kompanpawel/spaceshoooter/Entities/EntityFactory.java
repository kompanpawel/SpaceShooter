package pl.kompanpawel.spaceshoooter.Entities;

import com.badlogic.gdx.math.Vector2;
import pl.kompanpawel.spaceshoooter.Entities.Enemy;
import pl.kompanpawel.spaceshoooter.Entities.Entity;
import pl.kompanpawel.spaceshoooter.SpaceShoooter;

public class EntityFactory {

    public static Entity factorTIE1() {
        Enemy newTIE;
        newTIE = new Enemy(1,new Vector2(SpaceShoooter.getWidth()+50, SpaceShoooter.getHeight()/10 - 40),new Vector2(200, 20));
        return newTIE;
    }


    public static Entity factorTIE2() {
        Enemy newTIE;
        newTIE = new Enemy(1,new Vector2(SpaceShoooter.getWidth()+80, (SpaceShoooter.getHeight()/10) * 2 -40),new Vector2(200, 20));
        return newTIE;
    }

    public static Entity factorTIE3() {
        Enemy newTIE;
        newTIE = new Enemy(1,new Vector2(SpaceShoooter.getWidth()+50, (SpaceShoooter.getHeight()/10) * 3-40),new Vector2(200, 20));
        return newTIE;
    }
    public static Entity factorTIE4() {
        Enemy newTIE;
        newTIE = new Enemy(1,new Vector2(SpaceShoooter.getWidth()+80, (SpaceShoooter.getHeight()/10) * 4-40),new Vector2(200, 20));
        return newTIE;
    }

    public static Entity factorTIE5() {
        Enemy newTIE;
        newTIE = new Enemy(1,new Vector2(SpaceShoooter.getWidth()+50, (SpaceShoooter.getHeight()/10) * 5-40),new Vector2(200, 20));
        return newTIE;
    }

    public static Entity factorTIE6() {
        Enemy newTIE;
        newTIE = new Enemy(1,new Vector2(SpaceShoooter.getWidth()+80, (SpaceShoooter.getHeight()/10) * 6-40),new Vector2(200, 20));
        return newTIE;
    }

    public static Entity factorTIE7() {
        Enemy newTIE;
        newTIE = new Enemy(1,new Vector2(SpaceShoooter.getWidth()+50, (SpaceShoooter.getHeight()/10) * 7-40),new Vector2(200, 20));
        return newTIE;
    }

    public static Entity factorTIE8() {
        Enemy newTIE;
        newTIE = new Enemy(1,new Vector2(SpaceShoooter.getWidth()+80, (SpaceShoooter.getHeight()/10) * 8-40),new Vector2(200, 20));
        return newTIE;
    }

    public static Entity factorTIE9() {
        Enemy newTIE;
        newTIE = new Enemy(1,new Vector2(SpaceShoooter.getWidth()+50, (SpaceShoooter.getHeight()/10) * 9-40),new Vector2(200, 20));
        return newTIE;
    }

    public static Entity factorTIE10() {
        Enemy newTIE;
        newTIE = new Enemy(1,new Vector2(SpaceShoooter.getWidth()+80, (SpaceShoooter.getHeight()/10) * 10-40),new Vector2(200, 20));
        return newTIE;
    }

    public static Entity factorExecutor() {
        Enemy newExecutor;
        newExecutor = new Enemy(3, new Vector2(SpaceShoooter.getWidth()+60,SpaceShoooter.getHeight()/2-90), new Vector2(200,20));
        return newExecutor;
    }

    public static Entity factorDestroyer1() {
        Enemy newDestroyer;
        newDestroyer = new Enemy(2, new Vector2(-1000, 10), new Vector2(200, 20));
        return newDestroyer;
    }
    public static Entity factorDestroyer2() {
        Enemy newDestroyer;
        newDestroyer = new Enemy(2, new Vector2(-1000, 500), new Vector2(200, 20));
        return newDestroyer;
    }
}
