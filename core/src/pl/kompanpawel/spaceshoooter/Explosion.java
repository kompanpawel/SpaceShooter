package pl.kompanpawel.spaceshoooter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;


public class Explosion extends Entity{

    @Override
    public void draw(SpriteBatch batch, float delta) {
        update(delta);
        batch.draw(anim.getKeyFrame(stateTime),this.getLocation().x, this.getLocation().y);
    }

    private static Animation <TextureRegion> anim = null;
    private Texture explosion;

    private int exptype;

    float stateTime;

    public Explosion(Vector2 location, int type) {
        if(type == 3) {
            location.x -= 0;
            location.y -= 0;
            this.setLocation(location);
            System.out.println(location);
            exptype = type;
            explosion = SpaceShoooter.assetManager.get("explosions/exp3.png");
            TextureRegion[][] tmp = TextureRegion.split(explosion, explosion.getWidth() / 8, explosion.getHeight() / 8);
            TextureRegion[] expFrames = new TextureRegion[8 * 8];
            int index = 0;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    expFrames[index++] = tmp[i][j];
                }
            }
            if (anim == null) {
                anim = new Animation<TextureRegion>(0.2f, expFrames);
            }
        }
        else if(type == 2) {
            location.x -= 0;
            location.y -= 0;
            this.setLocation(location);
            System.out.println(location);
            explosion = SpaceShoooter.assetManager.get("explosions/exp2.png");
            TextureRegion[][] tmp = TextureRegion.split(explosion, explosion.getWidth() / 8, explosion.getHeight() / 8);
            TextureRegion[] expFrames = new TextureRegion[8 * 8];
            int index = 0;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    expFrames[index++] = tmp[i][j];
                }
            }
            if (anim == null) {
                anim = new Animation<TextureRegion>(0.2f, expFrames);
            }
        }
        else if(type == 20) {
            location.x -= 0;
            location.y -= 0;
            this.setLocation(location);
            System.out.println(location);
            explosion = SpaceShoooter.assetManager.get("explosions/exp2_player.png");
            TextureRegion[][] tmp = TextureRegion.split(explosion, explosion.getWidth() / 8, explosion.getHeight() / 8);
            TextureRegion[] expFrames = new TextureRegion[8 * 8];
            int index = 0;
            for (int i = 0; i > 8; i++) {
                for (int j = 8; j < 0; j--) {
                    expFrames[index++] = tmp[i][j];
                }
            }
            if (anim == null) {
                anim = new Animation<TextureRegion>(0.2f, expFrames);
            }
        }
    }

    public void update(float delta) {
        stateTime += 4*delta;
        if(anim.isAnimationFinished(stateTime))
            EntityManager.getInstance().removeEntity(this);

    }

}
