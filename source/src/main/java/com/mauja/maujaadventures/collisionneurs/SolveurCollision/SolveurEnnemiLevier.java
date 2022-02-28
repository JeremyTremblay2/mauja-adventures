package com.mauja.maujaadventures.collisionneurs.SolveurCollision;

import com.mauja.maujaadventures.collisionneurs.CollisionneurAABB;
import com.mauja.maujaadventures.entites.Ennemi;
import com.mauja.maujaadventures.entites.Projectile;
import com.mauja.maujaadventures.interactions.Condition;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.interactions.Levier;
import com.mauja.maujaadventures.jeu.TableauDeJeu;

public class SolveurEnnemiLevier extends SolveurCollision{
    private TableauDeJeu tableauDeJeu;
    private CollisionneurAABB collisionneur;
    private Condition condition;

    /**
     * Resolution de la collision entre un levier et un ennemi
     * @param e1 Element interactif du première élément à tester la collision
     * @param e2 Element interactif du second élément à tester
     * @return true si collision sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public void resoud(ElementInteractif e1, ElementInteractif e2) {
        Ennemi ennemi = (Ennemi) e1;
        Levier levier = (Levier) e2;
            levier.setActive(true);
    }
}