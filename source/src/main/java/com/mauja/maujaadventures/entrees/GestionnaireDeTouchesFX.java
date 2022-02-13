package com.mauja.maujaadventures.entrees;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionnaireDeTouchesFX extends GestionnaireDeTouches {
    private Scene scene;
    private Map<KeyCode, Touche> dicoTouchesFX;

    public GestionnaireDeTouchesFX(Scene scene) throws IllegalArgumentException {
        if (scene == null) {
            throw new IllegalArgumentException("La scene passée en paramètre pour la récupération des touches ne "
                    + "peut pas être nulle.");
        }

        this.scene = scene;
        dicoTouchesFX = new HashMap<>();
    }

    public void ajouteToucheFX(KeyCode toucheFX, Touche touche) {
        dicoTouchesFX.put(toucheFX, touche);
    }

    public void supprimeToucheFX(KeyCode toucheFX) {
        dicoTouchesFX.remove(toucheFX);
    }

    @Override
    public List<Touche> detecte() {
        return super.getLesTouchesAppuyees();
    }

    public void initialisation() {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (touche) -> {
            KeyCode codeTouche = touche.getCode();
            if (dicoTouchesFX.containsKey(codeTouche)){
                lesTouchesAppuyees.add(dicoTouchesFX.get(codeTouche));
            }
        });

        scene.addEventHandler(KeyEvent.KEY_RELEASED, (touche) -> {
            KeyCode codeTouche = touche.getCode();
            lesTouchesAppuyees.remove(dicoTouchesFX.get(codeTouche));
        });
    }
}
