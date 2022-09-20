package com.jude.particlesystem;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;

public class Bunch
{
    ArrayList<Particle> arr = new ArrayList<Particle>();;
    final int initParticles = 5;
    int deadParticles = 0;

    public String type;

    public Bunch(float midX, float midY, World world)
    {
        float offset = -initParticles/10;
        for(int i = 0; i <= initParticles; i++)
        {
            arr.add(new Particle(offset + midX, midY, world));
        }

        deadParticles = 0;
        type = "drip";
    }

    public Bunch(float midX, float midY, String type, World world)
    {
        float offset = -initParticles/10;
        for(int i = 0; i <= initParticles; i++)
        {
            arr.add(new Particle(offset + midX, midY, world));
        }
        
        //determine type 
        //TODO: fix this dogshit with something better thx
        switch(type)
        {
            case "explosion":
                this.type = type;
                break;

            default:
                System.err.println("Invalid Bunch Type!");
                System.exit(-1);
        }
    }

    public ArrayList<Particle> getArr()
    {
        return arr;
    }

    public void clean()
    {
        for(int i = 0; i < arr.size(); i++)
        {
            if(arr.get(i).isDead()) arr.remove(arr.get(i));
            deadParticles++;
        }
	}

    public boolean isDead()
    {
        if(deadParticles >= initParticles) return true;
        else return false;
    }
}   
