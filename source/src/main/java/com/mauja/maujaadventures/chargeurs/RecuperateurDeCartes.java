package com.mauja.maujaadventures.chargeurs;

import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.monde.Calque;
import com.mauja.maujaadventures.monde.Carte;
import com.mauja.maujaadventures.monde.JeuDeTuiles;
import org.tiledreader.*;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RecuperateurDeCartes {

    private RecuperateurDeCalques recuperateurDeCalques;
    private RecuperateurDeJeuDeTuiles recuperateurDeJeuDeTuiles = new RecuperateurDeJeuDeTuiles();

    public Carte recupereCarte(String nomCarte) throws FileNotFoundException {
        System.out.println(nomCarte);
        TiledReader chargeur = new FileSystemTiledReader();
        System.out.println(nomCarte);

        TiledMap chargeurCarte = chargeur.getMap(nomCarte);
        List<JeuDeTuiles> lesJeuxDeTuiles = recupereJeuxDeTuiles(nomCarte);
        recuperateurDeCalques = new RecuperateurDeCalques(lesJeuxDeTuiles);
        List<Calque> lesCalques = recuperateurDeCalques.recupere(chargeurCarte);
        if (lesCalques == null) {
            return null;
        }
        System.out.println("toto");

        Dimension dimension = new Dimension(chargeurCarte.getWidth(), chargeurCarte.getHeight());
        Carte carte = new Carte(nomCarte, dimension, lesCalques, null);
        return carte;
    }

    public List<JeuDeTuiles> recupereJeuxDeTuiles(String nomCarte) throws FileNotFoundException {
        TiledReader chargeur = new FileSystemTiledReader();
        Path chemin = Paths.get(nomCarte);
        System.out.println(nomCarte);
        TiledMap chargeurCarte = chargeur.getMap(chemin.toAbsolutePath().toString());

        List<JeuDeTuiles> lesJeuxDeTuiles = recuperateurDeJeuDeTuiles.recupere(chargeurCarte);
        return lesJeuxDeTuiles;
    }
    @Override
    public String toString() {
        return "RecuperateurDeCartes{" +
                "recuperateurDeCalques=" + recuperateurDeCalques.toString() +
                ", recuperateurDeJeuDeTuiles=" + recuperateurDeJeuDeTuiles.toString() +
                '}';
    }

}
