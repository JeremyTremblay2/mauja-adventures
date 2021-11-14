package com.mauja.maujaadventures.modele;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Jeu {


    public void boucle(GraphicsContext gc,int x,int y, ArrayList<String> input,Entite e){
        final long startNanoTime = System.nanoTime();
        DeplaceurEntite de=new DeplaceurEntite();
        new AnimationTimer()
        {

            public void handle(long currentNanoTime)
            {
                //gc.clearRect(0, 0, x,y);
                if (input.contains("LEFT"))
                    de.deplaceur(e,e.getX()-1,e.getY());
                if (input.contains("RIGHT"))
                    de.deplaceur(e,e.getX()+1,e.getY());
                if (input.contains("UP"))
                    de.deplaceur(e,e.getX(),e.getY()+1);
                if (input.contains("DOWN"))
                    de.deplaceur(e,e.getX(),e.getY()-1);
                System.out.println(e.toString());

            }

        }.start();


    };


}