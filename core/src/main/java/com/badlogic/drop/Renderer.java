package com.badlogic.drop;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Renderer {
    private final SpriteBatch spriteBatch;
    private final Viewport viewport;
    private final GameLogic gameLogic;

    public Renderer(SpriteBatch spriteBatch, Viewport viewport, GameLogic gameLogic) {
        this.spriteBatch = spriteBatch;
        this.viewport = viewport;
        this.gameLogic = gameLogic;
    }

    public void render() {
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();

        spriteBatch.draw(Assets.backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        gameLogic.getBucketSprite().draw(spriteBatch);

        for (Sprite dropSprite : gameLogic.getDropSprites()) {
            dropSprite.draw(spriteBatch);
        }

        spriteBatch.end();
    }
}
