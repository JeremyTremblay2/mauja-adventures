package com.mauja.maujaadventures.interactions;

import com.mauja.maujaadventures.interactions.evenements.Evenement;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GestionnaireInteractions {
    private static GestionnaireInteractions gestionnaireInteractions;
    private static  List<Scenario> scenarios;
    private Queue<Evenement> fileCourante;
    private Queue<Evenement> fileSauvegarde;
    private Thread thread;
    private boolean enCours;
    private List<ElementInteractif> elementAAjouter;

    private GestionnaireInteractions() {
        fileCourante = new LinkedList<>();
        fileSauvegarde = new LinkedList<>();
        elementAAjouter = new ArrayList<>();
        enCours = false;
        initialisation();
    }

    public static GestionnaireInteractions getInstance() {
        if(gestionnaireInteractions == null) {
            gestionnaireInteractions = new GestionnaireInteractions();
        }
        return gestionnaireInteractions;
    }

    public List<ElementInteractif> getElementAAjouter() {
        return elementAAjouter;
    }

    private void initialisation() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            InputStream inputStream = new FileInputStream("ressources/interactionsTest.xml");
            SAXParser parseur = factory.newSAXParser();

            InteractionHandler handler = new InteractionHandler();

            parseur.parse(inputStream, handler);
            scenarios = handler.getListeScenarios();

            for(Scenario scenario : scenarios) { //temporaire
                for(ElementInteractif elementInteractif : scenario.getListeElemInteractif()) {
                    if(elementInteractif instanceof Levier levier) {
                        elementAAjouter.add(levier);
                    }
                }
            }

        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public void ajouter(Evenement evenement) {
        if (!enCours) {
            fileCourante.add(evenement);
            if(thread != null) {
                synchronized (this) {
                    notify();
                }
            }
            else {
                thread = new Thread(this::traitement, "Thread Traitement évènement");
                thread.start();
            }
        }
        else{
            fileSauvegarde.add(evenement);
        }
    }

    private void traitement() {
        enCours = true;
        while(true) {
            if(fileCourante.size() != 0){
                Evenement evenement = fileCourante.poll();
                evenement.traitement(scenarios, null);
            }
            else{
                if(fileSauvegarde.size() != 0){
                    fileCourante = new LinkedList<>(fileSauvegarde);
                    fileSauvegarde = new LinkedList<>();
                }
                else {
                    enCours = false;
                    synchronized (this) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
