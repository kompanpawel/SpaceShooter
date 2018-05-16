package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class EntityFactory {

    public static Entity factorTIE1() {
        Enemy newTIE;
        newTIE = new Enemy(1,new Vector2(SpaceShoooter.getWidth()-50, SpaceShoooter.getHeight()/5 - 80),new Vector2(200, 20));
        return newTIE;
    }


    public static Entity factorTIE2() {
        Enemy newTIE;
        newTIE = new Enemy(1,new Vector2(SpaceShoooter.getWidth()-50, (SpaceShoooter.getHeight()/5) * 2 -80),new Vector2(200, 20));
        return newTIE;
    }

    public static Entity factorTIE3() {
        Enemy newTIE;
        newTIE = new Enemy(1,new Vector2(SpaceShoooter.getWidth()-50, (SpaceShoooter.getHeight()/5) * 3-80),new Vector2(200, 20));
        return newTIE;
    }
    public static Entity factorTIE4() {
        Enemy newTIE;
        newTIE = new Enemy(1,new Vector2(SpaceShoooter.getWidth()-50, (SpaceShoooter.getHeight()/5) * 4-80),new Vector2(200, 20));
        return newTIE;
    }

    public static Entity factorTIE5() {
        Enemy newTIE;
        newTIE = new Enemy(1,new Vector2(SpaceShoooter.getWidth()-50, SpaceShoooter.getHeight()-80 ),new Vector2(200, 20));
        return newTIE;
    }

}
