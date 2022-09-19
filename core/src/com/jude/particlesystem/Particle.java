package com.jude.particlesystem;

import com.badlogic.gdx.physics.box2d.World;

public class Particle extends Entity
{
    final float dieAtY = -15/10;
    
    public Particle(float x, float y, World world)
    {
        super(x, y, world);
    }

    public boolean isDead()
    {
        if(body.getPosition().y < dieAtY) return true;
        return false;
    }
}
