package pl.kompanpawel.spaceshoooter;

public class EntityManager {
    private static EntityManager ourInstance = new EntityManager();

    public static EntityManager getInstance() {
        return ourInstance;
    }

    private EntityManager() {
    }
}
