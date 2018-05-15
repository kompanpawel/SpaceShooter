package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.math.Rectangle;

public class Space {
    private static Space instance = new Space();

    public static Space getInstance() { return instance; }
    private EntityManager entityManager = EntityManager.getInstance();



    public void addEnemies() {
        entityManager.addEntity(EntityFactory.factorTIE1());
        entityManager.addEntity(EntityFactory.factorTIE2());
        entityManager.addEntity(EntityFactory.factorTIE3());
        entityManager.addEntity(EntityFactory.factorTIE4());
        entityManager.addEntity(EntityFactory.factorTIE5());
    }

    public void destroyEnemy() {
        for (Entity ent : entityManager.getEntities()) {
            if(ent instanceof Enemy)
                if(((Enemy) ent).getHealth()<=0)
                    ((Enemy) ent).destroyed();
        }
    }

    public void laserCollisions(Laser laser) {
        for (Entity ent : entityManager.getEntities()) {
            if (ent instanceof PlayerShip) {
                PlayerShip playerShip = (PlayerShip) ent;
                if(playerShip.isDead())
                    continue;
                Rectangle playerShipRect = new Rectangle(Math.round(playerShip.getLocation().x), Math.round(playerShip.getLocation().y), playerShip.getWidth()-10, playerShip.getHeight());
                Rectangle laserRect = new Rectangle(Math.round(laser.getLocation().x), Math.round(laser.getLocation().y), laser.getWidth(), laser.getHeight());
                if (playerShipRect.overlaps(laserRect) && laser.getOwner() instanceof Enemy) {
                    laser.hit(playerShip);
                    playerShip.getHited();
                    return;
                }
            }
            if (ent instanceof SecondShip) {
                SecondShip secondShip = (SecondShip) ent;
                if(secondShip.isDead())
                    continue;
                Rectangle playerShipRect = new Rectangle(Math.round(secondShip.getLocation().x), Math.round(secondShip.getLocation().y), secondShip.getWidth()-10, secondShip.getHeight());
                Rectangle laserRect = new Rectangle(Math.round(laser.getLocation().x), Math.round(laser.getLocation().y), laser.getWidth(), laser.getHeight());
                if (playerShipRect.overlaps(laserRect) && laser.getOwner() instanceof Enemy) {
                    laser.hit(secondShip);
                    secondShip.getHited();
                    return;
                }
            }
            if (ent instanceof Enemy) {
                Enemy enemy = (Enemy) ent;
                if(enemy.isDead())
                    continue;
                Rectangle enemyRect = new Rectangle(Math.round(enemy.getLocation().x), Math.round(enemy.getLocation().y),enemy.getWidth()-10, enemy.getHeight());
                Rectangle laserRect = new Rectangle(Math.round(laser.getLocation().x), Math.round(laser.getLocation().y), laser.getWidth(), laser.getHeight());
                if((enemyRect.overlaps(laserRect) && laser.getOwner() instanceof PlayerShip) || enemyRect.overlaps(laserRect) && laser.getOwner() instanceof SecondShip) {
                    laser.hit(enemy);
                    enemy.getHited();
                    return;
                }
            }
        }
    }
}
