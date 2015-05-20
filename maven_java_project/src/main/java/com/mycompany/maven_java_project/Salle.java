/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maven_java_project;

import java.util.ArrayList;

/**
 *
 * @author Vincent
 */
public class Salle {
    
    // Attributes
    String nomSalle;
    ArrayList<Appareil> listeApp;
    String emp;
    int capa;

    public Salle(String nomSalle, ArrayList<Appareil> listeApp, String emp, int capa) {
        this.nomSalle = nomSalle;
        this.listeApp = listeApp;
        this.emp = emp;
        this.capa = capa;
    }
    
}
