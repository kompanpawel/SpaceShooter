package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Laser extends Entity{
    private Vector2 laserLocation;
    private Vector2 laserVelocity;
    private boolean isValid = true;

    Texture laser;
    public Laser(Vector2 sentLocation, Vector2 sentVelocity) {
        laserLocation = new Vector2(sentLocation.x, sentLocation.y);
        laserVelocity = new Vector2(sentVelocity.x, sentVelocity.y);
        laser = SpaceShoooter.assetManager.get("laserGreen03.png");

    }

    public void update() {
        if(isValid == false) {return;}
        laserLocation.x += laserVelocity.x;
        laserLocation.y += laserVelocity.y;
        if((laserLocation.x < 0 - laser.getWidth()) && (laserLocation.x > Gdx.graphics.getWidth())) {
            isValid = false;
            return;
        }
        if((laserLocation.y < 0 + laser.getHeight()) && (laserLocation.y > Gdx.graphics.getHeight())) {
            isValid = false;
            return;
        }
    }
}
