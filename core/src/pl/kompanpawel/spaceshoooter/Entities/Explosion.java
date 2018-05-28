package pl.kompanpawel.spaceshoooter.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import pl.kompanpawel.spaceshoooter.SpaceShoooter;


public class Explosion extends Entity {

    @Override
    public void draw(SpriteBatch batch, float delta) {
        update(delta);
        if(exp1)
            batch.draw(anim_enemy.getKeyFrame(stateTime), this.getLocation().x, this.getLocation().y);
        if(exp2)
            batch.draw(anim_player_laser.getKeyFrame(stateTime), this.getLocation().x, this.getLocation().y);
        if(exp3)
            batch.draw(anim_enemy_laser.getKeyFrame(stateTime), this.getLocation().x, this.getLocation().y);
    }



    private static Animation <TextureRegion> anim = null;
    private static Animation <TextureRegion> anim_enemy = null;
    private static Animation <TextureRegion> anim_player = null;
    private static Animation <TextureRegion> anim_enemy_laser = null;
    private static Animation <TextureRegion> anim_player_laser = null;

    private Texture explosion;
    private Texture explosion2;
    private Texture explosion3;

    private int exptype;

    private boolean exp1 = false;
    private boolean exp2 = false;
    private boolean exp3 = false;

    float stateTime;

    public Explosion(Vector2 location, int type) {
        if(type == 3) {
            location.x -= 62;
            location.y -= 55;
            this.setLocation(location);
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
            if(anim_enemy == null) {
                anim_enemy = new Animation<TextureRegion>(0.2f, expFrames);
                anim = anim_enemy;
            }
            exp1 = true;
        }
        else if(type == 2) {
            location.x += 25;
            location.y -= 30;
            this.setLocation(location);
            exptype = type;
            explosion2 = SpaceShoooter.assetManager.get("explosions/exp2.png");
            TextureRegion[][] tmp = TextureRegion.split(explosion2, explosion2.getWidth() / 8, explosion2.getHeight() / 8);
            TextureRegion[] expFrames = new TextureRegion[8 * 8];
            int index = 0;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    expFrames[index++] = tmp[i][j];
                }
            }
            if(anim_player_laser == null) {
                anim_player_laser = new Animation<TextureRegion>(0.2f, expFrames);
                anim = anim_player_laser;
            }
            exp2 = true;
        }
        else if(type == 20) {
            location.x -= 100;
            location.y -= 40;
            this.setLocation(location);
            exptype = type;
            explosion3 = SpaceShoooter.assetManager.get("explosions/exp2_player.png");
            TextureRegion[][] tmp = TextureRegion.split(explosion3, explosion3.getWidth() / 8, explosion3.getHeight() / 8);
            TextureRegion[] expFrames = new TextureRegion[8 * 8];
            int index = 0;
            for (int i = 0; i < 8; i++) {
                for (int j = 7; j > -1; j--) {
                    expFrames[index++] = tmp[i][j];
                }
            }
            if(anim_enemy_laser == null) {
                anim_enemy_laser = new Animation<TextureRegion>(0.2f, expFrames);
                anim = anim_enemy_laser;
            }
            exp3 = true;
        }
    }

    public void update(float delta) {
        if(EntityManager.getInstance().isPause()) {return;}
        stateTime += 4 * delta;
        this.getLocation().x -= 0.2;
        if (anim.isAnimationFinished(stateTime)) {
            if (exptype == 3)
                exp1 = false;
            if (exptype == 2)
                exp2 = false;
            if (exptype == 20)
                exp3 = false;
            EntityManager.getInstance().removeEntity(this);
        }
    }

}
