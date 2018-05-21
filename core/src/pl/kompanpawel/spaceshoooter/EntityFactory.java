package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

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

    public static Entity factorDestroyer() {
        Enemy newDestroyer;
        newDestroyer = new Enemy(2, new Vector2(SpaceShoooter.getWidth()- 490,SpaceShoooter.getHeight()/2-150), new Vector2(200,20));
        return newDestroyer;
    }
}
