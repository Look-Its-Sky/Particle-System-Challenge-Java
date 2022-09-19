package com.jude.particlesystem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class FPSCounter 
{
    Vector2 pos;
    BitmapFont font;
    Long lastTimeCounted;
    float sinceChange;
    int frameRate;

    public FPSCounter(float x, float y)
    {
        pos = new Vector2(x, y);
        font = new BitmapFont();
        lastTimeCounted = TimeUtils.millis();
        
    }

    public void render(SpriteBatch batch)
    {
        long delta = TimeUtils.timeSinceMillis(TimeUtils.millis());
        lastTimeCounted = TimeUtils.millis();

        sinceChange += delta;
        if(sinceChange >= 1000)
        {
            sinceChange = 0;
            frameRate = Gdx.graphics.getFramesPerSecond();
        }
        font.draw(batch, (int) Gdx.graphics.getFramesPerSecond() + "FPS", pos.x, pos.y);
    }
}
