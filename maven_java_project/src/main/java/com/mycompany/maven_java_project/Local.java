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
public class Local {
    
    // Attributes
    String nomLocal;
    ArrayList<Salle> listeSalle;

    public Local(String nomLocal, ArrayList<Salle> listeSalle) {
        this.nomLocal = nomLocal;
        this.listeSalle = listeSalle;
    }
    
    
}
