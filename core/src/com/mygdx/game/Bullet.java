package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

class Bullet {
    private final Vector2 position;
    private final float speed;

    Bullet(float x, float y) {
        position = new Vector2(x, y);
        speed = 400.0f;
    }

    Vector2 getPosition() {
        return position;
    }

    void update(long dt) {
        position.x += speed * dt / 1000;
    }

    void render(SpriteBatch batch, Texture texture) {
        batch.draw(texture, position.x, position.y);
    }
}
