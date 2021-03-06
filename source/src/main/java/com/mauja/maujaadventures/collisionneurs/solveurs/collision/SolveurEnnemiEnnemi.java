package com.mauja.maujaadventures.collisionneurs.solveurs.collision;

import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.monde.Carte;

/**
 * Solveur de collision entre 2 ennemi, la position de l'ennemi n°1 est restoré à la position avant collision
 */
public class SolveurEnnemiEnnemi extends SolveurCollision{
    /**
     * Constructeur de la classe SolveurEnnemiEnnemi
     * @param carte Carte actuelle sur laquelle se déroule le projet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public SolveurEnnemiEnnemi(Carte carte) {
        super(carte);
    }

    /**
     * Resolution de la collision entre deux ennemis
     * Lorsqu'il y a collision entre 2 ennemi, leur position n'est pas changé le déplacement n'a pas pu s'effectuer
     * @param e1 Element interactif du première élément à tester la collision
     * @param e2 Element interactif du second élément à tester
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public void resoud(ElementInteractif e1, ElementInteractif e2) {
        e1.restorerMemento();
    }
}
