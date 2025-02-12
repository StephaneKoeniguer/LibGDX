package com.badlogic.drop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Main implements ApplicationListener {

    private SpriteBatch spriteBatch;
    private FitViewport viewport;
    private InputManager inputManager;
    private GameLogic gameLogic;
    private Renderer renderer;


    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(8, 5);

        Assets.load(); // Charger les ressources partagées
        inputManager = new InputManager();
        gameLogic = new GameLogic(viewport);
        renderer = new Renderer(spriteBatch, viewport, gameLogic);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        inputManager.handleInput(gameLogic.getBucketSprite(), viewport);
        gameLogic.update();
        renderer.render();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        Assets.dispose(); // Libérer les ressources
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

}
