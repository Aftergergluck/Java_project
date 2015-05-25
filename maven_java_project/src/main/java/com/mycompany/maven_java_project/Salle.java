/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maven_java_project;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vincent
 */
public class Salle {
    
    // Attributes
    private String nomSalle;
    private List<Appareil> listeApp;
    private int capa;

    public Salle(String nomSalle, int capa) {
        this.nomSalle = nomSalle;
        this.capa = capa;
    }

    public String getNomSalle() {
        return nomSalle;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }

    public void setCapa(int capa) {
        this.capa = capa;
    }
    
    public void affecterApp (Appareil app) {
        listeApp.add(app);
    }
    
    public void desaffecterApp (Appareil app) {
        listeApp.remove(app);
    }
    
    public void ajoutSalleBDD (String nomLocal) {
        try {
            BDD bdd = new BDD();
            bdd.connect();
            if (!bdd.exist("Salle","nomSalle",nomSalle))
                bdd.request("INSERT INTO Salle VALUES ('"+nomSalle+"','"+nomLocal+"')");
        } catch (SQLException e) {
            Logger.getLogger(Ajouter.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void modifSalleBDD (String nomLocal) {
        try {
            BDD bdd = new BDD();
            bdd.connect();
            if (!bdd.exist("Salle","nomSalle",nomSalle))
                bdd.request("UPDATE Salle SET nomsalle = '"+nomSalle+"' AND nomlocal = '"+nomLocal+"')");
        } catch (SQLException e) {
            Logger.getLogger(Ajouter.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
