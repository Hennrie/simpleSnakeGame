package com.snake.screen.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.snake.SimpleSnakeGame;
import com.snake.assets.AssetDescriptors;
import com.snake.collision.CollisionListener;
import com.snake.common.GameManager;
import com.snake.screen.menu.MenuScreen;

/**
 * Created by goran on 26/09/2016.
 */
@Deprecated
public class GameScreenOld extends ScreenAdapter {

    // == attributes ==
    private final SimpleSnakeGame game;
    private final AssetManager assetManager;
    private final CollisionListener listener;

    private GameControllerOld controller;
    private GameRendererOld renderer;

    private Sound coinSound;
    private Sound loseSound;

    // == constructors ==
    public GameScreenOld(SimpleSnakeGame game) {
        this.game = game;
        assetManager = game.getAssetManager();

        listener = new CollisionListener() {
            @Override
            public void hitCoin() {
                coinSound.play();
            }

            @Override
            public void lose() {
                loseSound.play();
            }
        };
    }

    // == public methods ==
    @Override
    public void show() {
        coinSound = assetManager.get(AssetDescriptors.COIN_SOUND);
        loseSound = assetManager.get(AssetDescriptors.LOSE_SOUND);

        controller = new GameControllerOld(listener);
        renderer = new GameRendererOld(game.getBatch(), assetManager, controller);
    }

    @Override
    public void render(float delta) {
        controller.update(delta);
        renderer.render(delta);

        if (GameManager.INSTANCE.isGameOver()) {
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
