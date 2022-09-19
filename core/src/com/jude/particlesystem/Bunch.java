package com.jude.particlesystem;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;

public class Bunch
{
    ArrayList<Particle> arr;
    boolean isDead;
    final int initParticles = 5;

    public Bunch(float midX, float midY, World world)
    {
        arr = new ArrayList<Particle>();
        float offset = -initParticles/10;
        for(int i = 0; i <= initParticles; i++)
        {
            arr.add(new Particle(offset + midX, midY, world));
        }

        isDead = false;
    }

    public ArrayList<Particle> getArr()
    {
        return arr;
    }

    public void clean()
    {
		for(Particle p: arr)
		{
			if(p.isDead()) arr.remove(p);
		}
        
        int count = 0;
        for(Particle p: arr)
        {
            count++;
        }

        if(count <= 0) isDead = true;
	}
}   
