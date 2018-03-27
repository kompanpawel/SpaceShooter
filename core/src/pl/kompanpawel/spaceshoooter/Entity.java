package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.math.Vector2;

public class Entity {

    private Vector2 velocity;
    private Vector2 location;
    private int acceleration;

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Vector2 getLocation() {
        return location;
    }

    public void setLocation(Vector2 location) {
        this.location = location;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }
}
