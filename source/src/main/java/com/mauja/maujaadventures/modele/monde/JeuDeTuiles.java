package com.mauja.maujaadventures.modele.monde;


import java.util.ArrayList;

public class JeuDeTuiles {
    private String image;
    private int nombreTuiles;
    private int largeur;
    private int hauteur;
    private ArrayList<Tuile> listeDeTuiles;
    /**
     * Constructeur du jeu de tuiles
     * @param image Image de la tuile
     * @param largeur Largeur de la tuile
     * @param hauteur Hauteur de la tuile
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public JeuDeTuiles(String image, int largeur, int hauteur){
        setImage(image);
        setLargeur(largeur);
        setHauteur(hauteur);
        listeDeTuiles= new ArrayList<Tuile>();
        nombreTuiles=listeDeTuiles.size();
    }
    /**
     * Getter de la largeur
     * @return Largeur de l'élément
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public int getLargeur() {
        return largeur;
    }
    /**
     * Setter de la largeur
     * @param largeur Nouvelle largeur que va comporter l'élément
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }
    /**
     * Getter de l'hauteur
     * @return L'hauteur de l'élément
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public int getHauteur() {
        return hauteur;
    }
    /**
     * Setter de l'hauteur
     * @param hauteur Nouvelle hauteur de l'élément
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }
    /**
     * Getter du nombre de tuile
     * @return Le nombre de Tuile
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public int getNombreTuiles() {
        return nombreTuiles;
    }
    /**
     * Getter de l'image
     * @return l'image de la Tuile
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public String getImage() {
        return image;
    }
    /**
     * Setter de l'image
     * @param image Nouvelle image de la tuile
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setImage(String image) {
        this.image = image;
    }

}
