package pl.kompanpawel.spaceshoooter.Entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

    private Vector2 velocity;
    private Vector2 location;

    public abstract void draw(SpriteBatch batch, float delta);

    public void dispose() {}

    public Vector2 getVelocity() {
        return velocity;
    }

    void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Vector2 getLocation() {
        return location;
    }

    public void setLocation(Vector2 location) {
        this.location = location;
    }

}
