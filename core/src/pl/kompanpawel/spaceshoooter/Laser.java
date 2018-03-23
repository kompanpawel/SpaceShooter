package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.math.Vector2;

public class Laser {
    public Vector2 laserLocation1 = new Vector2(0,0);
    public Vector2 laserVelocity = new Vector2(0,0);

    public Laser(Vector2 sentLocation, Vector2 sentVelocity) {
        laserLocation1 = new Vector2(sentLocation.x, sentLocation.y);
        laserVelocity = new Vector2(sentVelocity.x, sentVelocity.y);

    }

    public void update() {
        laserLocation1.x += laserVelocity.x;
        laserLocation1.y += laserVelocity.y;
    }
}
