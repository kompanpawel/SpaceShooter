package pl.kompanpawel.spaceshoooter.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import pl.kompanpawel.spaceshoooter.SpaceShoooter;

public class Laser extends Entity {
    private boolean isValid = true;

    private Texture laser;
    private Entity owner;
    private Explosion explosion;



    public Laser(Entity owner,int type, Vector2 sentLocation, Vector2 sentVelocity) {
        this.setLocation(sentLocation);
        this.setVelocity(sentVelocity);
        if(owner instanceof PlayerShip)
            laser = SpaceShoooter.assetManager.get("lasers/laserRed13.png");
        else if(owner instanceof Enemy)
            laser = SpaceShoooter.assetManager.get("lasers/laserGreen03.png");

        this.owner = owner;
    }

    public void update() {

        if(!isValid) {
            EntityManager.getInstance().removeEntity(this);
            return;
        }
        this.getLocation().x += this.getVelocity().x;
        this.getLocation().y += this.getVelocity().y;
        if((getLocation().x < 0 - laser.getWidth()) || (getLocation().x > SpaceShoooter.getWidth())) {
            isValid = false;
            return;
        }
        if((getLocation().y < 0 - laser.getHeight()) || (getLocation().y > SpaceShoooter.getHeight()+ laser.getHeight())) {
            isValid = false;
            return;
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float delta) {
        TextureRegion tr = new TextureRegion(laser);
        spriteBatch.draw(tr, getLocation().x, getLocation().y, 0,0, laser.getWidth(), laser.getHeight(),1,1,getVelocity().angle());
        update();
    }

    public void onHit(Vector2 pos) {
        EntityManager.getInstance().removeEntity(this);
    }

    public void hit(Entity e) {
        onHit(e.getLocation());
        if(e instanceof Enemy)
            explosion = new Explosion(this.getLocation(), 2);
        else if(e instanceof PlayerShip)
            explosion = new Explosion(this.getLocation(), 20);
        EntityManager.getInstance().addEntity(explosion);
    }

    public Entity getOwner() {
        return owner;
    }

    public int getWidth() {
        return laser.getWidth();
    }

    public int getHeight() {
        return laser.getHeight();
    }
}
