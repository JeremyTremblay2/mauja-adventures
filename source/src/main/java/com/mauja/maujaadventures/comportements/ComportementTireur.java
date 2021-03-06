package com.mauja.maujaadventures.comportements;

import com.mauja.maujaadventures.deplaceurs.Deplaceur;
import com.mauja.maujaadventures.deplaceurs.DeplaceurBasique;
import com.mauja.maujaadventures.deplaceurs.DeplaceurDeDestructible;
import com.mauja.maujaadventures.entites.Destructible;
import com.mauja.maujaadventures.entites.Direction;
import com.mauja.maujaadventures.entites.Vivant;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.interactions.GestionnaireInteractions;
import com.mauja.maujaadventures.interactions.evenements.Evenement;
import com.mauja.maujaadventures.interactions.evenements.EvenementDeplacement;
import com.mauja.maujaadventures.interactions.evenements.EvenementImmobile;
import com.mauja.maujaadventures.jeu.BoucleDeJeu;
import com.mauja.maujaadventures.logique.*;
import com.mauja.maujaadventures.observateurs.ObservateurEvenementiel;
import com.mauja.maujaadventures.monde.Carte;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ComportementTireur implements Comportement, ObservateurEvenementiel {
    private static final Random ALEATOIRE = new Random();
    private static final List<Direction> DIRECTIONS_POSSIBLES = Arrays.asList(Direction.values());
    private static final int NOMBRE_MAXIMUM_DEPLACEMENTS_PAR_DEFAUT = 12;
    private static final int NOMBRE_DIRECTIONS = DIRECTIONS_POSSIBLES.size();
    private static final int INTERVALLE_DEPLACEMENT = BoucleDeJeu.FPS_CIBLE / 30;
    private static final double TEMPS_ATTENTE_INACTIF = BoucleDeJeu.FPS_CIBLE * 1.2;

    private Deplaceur deplaceur;
    private Carte carteCourante;
    private int iterations;
    private int nombreDeplacements;
    private Direction derniereDirection;
    private boolean avance;
    private Velocite vitesseProjectile;
    private int nombreMaximumDeplacements = 12;

    public ComportementTireur(Carte carte, Velocite vitesseProjectile, int nombreMaximumDeplacements)
            throws IllegalArgumentException {
        if (carte == null) {
            throw new IllegalArgumentException("La carte pass??e en param??tre du comportement de tireur ne peut pas "
                    + "??tre nulle.");
        }
        this.nombreMaximumDeplacements = nombreMaximumDeplacements > 0 ? nombreMaximumDeplacements
                : NOMBRE_MAXIMUM_DEPLACEMENTS_PAR_DEFAUT;
        this.vitesseProjectile = vitesseProjectile;
        carteCourante = carte;
        deplaceur = new DeplaceurBasique(carte);
        iterations = 0;
        nombreDeplacements = 0;
        avance = false;
    }

    @Override
    public void agit(Vivant vivant, float temps) {
        // S'il n'avance pas, il a ??t?? bloqu?? et il patiente.
        if (!avance) {
            if (iterations == 1) {
                tirerProjectile(vivant);
            }
            if (iterations >= TEMPS_ATTENTE_INACTIF) {
                avance = true;
                iterations = 0;
                derniereDirection = DIRECTIONS_POSSIBLES.get(ALEATOIRE.nextInt(NOMBRE_DIRECTIONS));
            }
            else {
                GestionnaireInteractions.getInstance().ajouter(new EvenementImmobile(vivant));
            }
        }

        // Il se d??place un certain nombre de fois.
        if (avance && iterations >= INTERVALLE_DEPLACEMENT) {
            if (nombreDeplacements < nombreMaximumDeplacements) {
                Evenement evenement = new EvenementDeplacement(vivant, derniereDirection, deplaceur);
                evenement.attacher(this);
                GestionnaireInteractions.getInstance().ajouter(evenement);
            }
            else {
                avance = false;
                nombreDeplacements = 0;
            }
            iterations = 0;
        }
        iterations++;
    }

    @Override
    public void miseAJour(ElementInteractif elementInteractif, Boolean resultat, Object... parametres) {
        if (!resultat) {
            derniereDirection = DIRECTIONS_POSSIBLES.get(ALEATOIRE.nextInt(NOMBRE_DIRECTIONS));
        }
        else {
            nombreDeplacements++;
        }
    }

    private void tirerProjectile(Vivant vivant) {
        Position positionDestructible = new Position(
                vivant.getPosition().getX() + (vivant.getDimension().getLargeur() - 11) / 2,
                vivant.getPosition().getY() + (vivant.getDimension().getHauteur() - 12) / 2);
        Destructible destructible = new Destructible(positionDestructible, new Dimension(20, 20),
                new Rectangle(0, 0, 11, 12), vitesseProjectile, 3,
                new DeplaceurDeDestructible(carteCourante, deplaceur), vivant);
        destructible.setDirection(vivant.getDirection());
        decalageProjectile(destructible, vivant);
        carteCourante.ajouterElementInteractif(destructible);
    }

    private void decalageProjectile(Destructible destructible, Vivant vivant) {
        Position positionProjectile;
        switch (destructible.getDirection()) {
            case DROITE -> positionProjectile = new Position(
                    destructible.getPosition().getX() + vivant.getCollision().getDimension().getLargeur() / 2,
                    destructible.getPosition().getY());
            case GAUCHE -> positionProjectile = new Position(
                    destructible.getPosition().getX() - vivant.getCollision().getDimension().getLargeur() / 2,
                    destructible.getPosition().getY());
            case HAUT -> positionProjectile = new Position(destructible.getPosition().getX(),
                    destructible.getPosition().getY() - vivant.getCollision().getDimension().getHauteur() / 2);
            case BAS -> positionProjectile = new Position(destructible.getPosition().getX(),
                    destructible.getPosition().getY() + vivant.getCollision().getDimension().getHauteur() / 2);
            default -> {
                return;
            }
        }
        destructible.installerMemento(new MementoPosition(positionProjectile));
    }
}
