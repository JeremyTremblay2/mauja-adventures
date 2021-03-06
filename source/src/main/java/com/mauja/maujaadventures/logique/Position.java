package com.mauja.maujaadventures.logique;

import com.mauja.maujaadventures.annotations.ConstructeurXml;
import com.mauja.maujaadventures.annotations.Param;

import java.util.Objects;

/**
 * Position X et Y d'un vivant pouvant être récupérer et/ ou modifier
 */
public class Position {
    private double x;
    private double y;
    /**
     * Constructeur de la classe Position
     * @param x Position de l'objet en X
     * @param y Position de l'obejt en Y
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @ConstructeurXml
    public Position(@Param(nom = "x") double x, @Param(nom = "y") double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter position X
     * @return La position X de l'objet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public double getX() {
        return x;
    }

    /**
     * Getter de position Y
     * @return Position Y de l'objet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public double getY() {
        return y;
    }

    /**
     * Génère une nouvelle position en se basant sur le milieu de la position courante.
     * @return La nouvelle position
     */
    public Position getMilieu() {
        return new Position(x / 2, y / 2);
    }

    /**
     * Redéfinition du hashCode
     * @return Hachage des attributs de Positions
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Redéfinition du equals
     * @param obj Objet que l'on veut comparer
     * @return True si égalité sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Position position = (Position) obj;
        return equals(position);
    }

    /**
     * Méthode equals
     * @param position Position que l'on veut comparer
     * @return True si égalité sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean equals(Position position) {
        return position.getX() == x
                && position.getY() == y;
    }

    /**
     * Redéfinition du toString
     * @return Chaîne que l'on veut afficher
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return "{" + x + "; " + y + "}";
    }
}
