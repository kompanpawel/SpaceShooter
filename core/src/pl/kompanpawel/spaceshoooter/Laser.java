package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Laser extends Entity{
    private boolean isValid = true;

    private Texture laser;
    private Entity owner;



    public Laser(Entity owner, Vector2 sentLocation, Vector2 sentVelocity) {
        this.setLocation(sentLocation);
        this.setVelocity(sentVelocity);
        if(owner instanceof PlayerShip)
            laser = SpaceShoooter.assetManager.get("laserRed13.png");
        else if(owner instanceof Enemy)
            laser = SpaceShoooter.assetManager.get("laserGreen03.png");
        this.owner = owner;
    }

    public void update() {

        if(!isValid) {
            EntityManager.getInstance().removeEntity(this);
            return;
        }
        this.getLocation().x += this.getVelocity().x;
        this.getLocation().y += this.getVelocity().y;
        if((getLocation().x < 0 - laser.getWidth()) || (getLocation().x > Gdx.graphics.getWidth())) {
            isValid = false;
            return;
        }
        if((getLocation().y < 0 + laser.getHeight()) || (getLocation().y > Gdx.graphics.getHeight())) {
            isValid = false;
            return;
        }
    }

    @Override
    public void draw(SpriteBatch batch, float delta) {
        batch.draw(laser, getLocation().x, getLocation().y);
        update();
    }

    public Entity getOwner() {
        return owner;
    }

}