package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.math.Rectangle;

public class Space {
    private static Space instance = new Space();

    public static Space getInstance() { return instance; }
    private EntityManager entityManager = EntityManager.getInstance();


    public void laserCollisions(Laser laser) {
        for (Entity ent : entityManager.getEntities()) {
            if (ent instanceof PlayerShip) {
                PlayerShip playerShip = (PlayerShip) ent;
                Rectangle playerShipRect = new Rectangle(Math.round(playerShip.getLocation().x), Math.round(playerShip.getLocation().y), playerShip.getWidth()-10, playerShip.getHeight());
                Rectangle laserRect = new Rectangle(Math.round(laser.getLocation().x), Math.round(laser.getLocation().y), laser.getWidth(), laser.getHeight());
                if (playerShipRect.overlaps(laserRect) && laser.getOwner() instanceof Enemy) {
                    laser.hit(playerShip);
                    return;
                }
            }
            if (ent instanceof Enemy) {
                Enemy enemy = (Enemy) ent;
                Rectangle enemyRect = new Rectangle(Math.round(enemy.getLocation().x), Math.round(enemy.getLocation().y),enemy.getWidth()-10, enemy.getHeight());
                Rectangle laserRect = new Rectangle(Math.round(laser.getLocation().x), Math.round(laser.getLocation().y), laser.getWidth(), laser.getHeight());
                if(enemyRect.overlaps(laserRect) && laser.getOwner() instanceof PlayerShip) {
                    laser.hit(enemy);
                    return;
                }
            }
        }
    }
}
