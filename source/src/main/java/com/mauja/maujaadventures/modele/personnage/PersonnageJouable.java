package com.mauja.maujaadventures.modele.personnage;

import com.mauja.maujaadventures.modele.Collision;
import com.mauja.maujaadventures.modele.Position;

public class PersonnageJouable extends Personnage {

    private int attaque;
    /**
     * Constructeur de la classe PersonnageJouable qui instancie attaque et appelle sa classe mère Personnage pour les autres
     * paramètre
     *
     * @param position Correspond à la position du personnage Jouable
     * @param image Image du personnage que l'on joue
     * @param collision Element permettant de savoir la collision du personnage Jouable
     * @param attaque Entier de l'attaque du personnage
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public PersonnageJouable(Position position, String image, Collision collision, int attaque){
        super(position, image, collision);
        this.attaque = attaque;
    }
    /**
     * Redéfinition du toString
     *
     * @return La phrase que l'on souhaite affiché
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return super.toString() + ", "
                + "attaque = " + attaque + ", ";
    }
}
