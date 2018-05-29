package pl.kompanpawel.spaceshoooter.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import pl.kompanpawel.spaceshoooter.SpaceShoooter;


public class MainMenu implements Screen {

    private Stage stage;
    private Table table;
    private SpriteBatch spriteBatch;

    private Texture background;


    private SpaceShoooter game;


    public MainMenu(SpaceShoooter game){
        this.game = game;
        setCamera();
        setSprite();
    }

    private void setCamera(){
        SpaceShoooter.getCamera().setToOrtho(false, 1280,720);
    }

    private void setSprite() {
        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(SpaceShoooter.getCamera().combined);
    }
    @Override
    public void show() {
        background = new Texture("backgrounds/Parallax100.png");
        stage = new Stage(new StretchViewport(SpaceShoooter.getCamera().viewportWidth, SpaceShoooter.getCamera().viewportHeight));

        TextButton playButton = new TextButton("Play", SpaceShoooter.assetManager.get("fonts_and_others/uiskin.json", Skin.class));
        TextButton coopButton = new TextButton("Co-op play", SpaceShoooter.assetManager.get("fonts_and_others/uiskin.json", Skin.class));
        TextButton loadButton = new TextButton("Load game", SpaceShoooter.assetManager.get("fonts_and_others/uiskin.json", Skin.class));
        TextButton help = new TextButton("Help", SpaceShoooter.assetManager.get("fonts_and_others/uiskin.json", Skin.class));
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game,1));
                stage.clear();
            }
        });
        coopButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game, 2));
                stage.clear();
            }
        });
        loadButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game, 3));
                stage.clear();
            }
        });
        help.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new HelpScreen(game));
            }
        });
        table = new Table(SpaceShoooter.assetManager.get("fonts_and_others/uiskin.json", Skin.class));

        table.setFillParent(true);
        Label title = new Label("SpaceShoooter", SpaceShoooter.assetManager.get("fonts_and_others/uiskin.json", Skin.class));
        Label owner = new Label("made by Pawel Miskiewicz", SpaceShoooter.assetManager.get("fonts_and_others/uiskin.json", Skin.class));
        //table.debug();

        table.add(title).center().colspan(2).row();
        table.add(playButton).expand().width(300).height(50);
        table.add(coopButton).expand().width(300).height(50).row();
        table.add(loadButton).expand().width(300).height(50);
        table.add(help).expand().width(300).height(50).row();
        table.add(owner).colspan(2);
        table.setTouchable(Touchable.childrenOnly);
        Gdx.input.setInputProcessor(stage);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        spriteBatch.draw(background, 0,0,SpaceShoooter.getCamera().viewportWidth, SpaceShoooter.getCamera().viewportHeight);
        spriteBatch.end();
        stage.act(delta);
        stage.draw();



    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height);
        table.setSize(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
