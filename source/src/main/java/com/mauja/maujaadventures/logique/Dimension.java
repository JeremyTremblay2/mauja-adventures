package com.mauja.maujaadventures.logique;

import com.mauja.maujaadventures.annotations.Param;

import java.util.Objects;

/**
 * Classe de taille des éléments contient la largeur et la hauteur de l'élément
 */
public class Dimension {
    private double largeur;
    private double hauteur;

    /**
     * Constructeur de la classe
     * @param largeur Largeur de l'élément
     * @param hauteur Hauteur de l'élément
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Dimension(@Param(nom = "largeur") double largeur, @Param(nom = "hauteur") double hauteur) {
        this.setHauteur(hauteur);
        this.setLargeur(largeur);
    }
    /**
     * Getter de la largeur
     * @return Largeur de l'élément
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public double getLargeur() {
        return largeur;
    }

    /**
     * Setter de la largeur
     * @param largeur Nouvelle largeur que va comporter l'élément
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    private void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    /**
     * Getter de l'hauteur
     * @return L'hauteur de l'élément
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public double getHauteur() {
        return hauteur;
    }

    /**
     * Setter de l'hauteur
     * @param hauteur Nouvelle hauteur de l'élément
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    private void setHauteur(double hauteur) {
        this.hauteur = hauteur;
    }

    /**
     * Redéfinition du hashCode
     * @return Hachage des attributs de dimension
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() {
        return Objects.hash(largeur, hauteur);
    }

    /**
     * Redéfinition du equals
     * @param obj Objet que l'on veut comparer
     * @return true si égalité sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Dimension dimension = (Dimension) obj;
        return equals(dimension);
    }

    /**
     * Méthode equals
     * @param dimension Dimension que l'on veut comparer
     * @return true si égalité sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean equals(Dimension dimension) {
        return dimension.getHauteur() == hauteur
                && dimension.getLargeur() == largeur;
    }

    /**
     * Redéfinition du toString
     * @return chaîne que l'on veut afficher
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return largeur + "x" + hauteur;
    }
}
