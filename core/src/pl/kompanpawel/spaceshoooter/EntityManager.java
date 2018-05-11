package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashSet;
import java.util.Set;

public class EntityManager {
    private static EntityManager ourInstance = new EntityManager();

    public static EntityManager getInstance() {
        return ourInstance;
    }

    private Set<Entity> entitySet;
    private Set<Entity> entityToAdd;
    private Set<Entity> entityToRemove;

    private EntityManager() {
        entitySet = new HashSet<Entity>();
        entityToAdd = new HashSet<Entity>();
        entityToRemove = new HashSet<Entity>();
    }

    public void draw(SpriteBatch batch, float delta) {
        if(!entityToRemove.isEmpty()) {
            for(Entity e : entityToRemove)
                e.dispose();
            entitySet.removeAll(entityToRemove);
            entityToRemove.clear();
        }

        if(!entityToAdd.isEmpty()) {
            entitySet.addAll(entityToAdd);
            entityToAdd.clear();
        }
        for (Entity e : entitySet) {
            e.draw(batch, delta);
        }
    }

    public void addEntity(Entity ent) {
        entityToAdd.add(ent);
    }

    public Set<Entity> getEntities() {
        return entitySet;
    }

    public void removeEntity(Entity e) {
        entityToRemove.add(e);
    }

    public void removeAllEntities() { entityToRemove.addAll(entitySet);}
}
