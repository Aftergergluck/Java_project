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
 * Entité représentant les salles contenues dans les locaux et contenant les
 * appareils.
 * @author Vincent
 */
public class Salle {
    
    private String nomSalle;
    private List<Appareil> listeApp;
    private int capa;

    /**
     * Constructeur d'une salle à partir de plusieurs informations.
     * @param nomSalle nom de la salle.
     * @param capa capacité de la salle en nombre d'appareils.
     */
    public Salle(String nomSalle, int capa) {
        this.nomSalle = nomSalle;
        this.capa = capa;
    }

    /**
     * Getter de l'attribut nomSalle.
     * @return le nom de la salle.
     */
    public String getNomSalle() {
        return nomSalle;
    }

    /**
     * Setter de l'attribut nomSalle.
     * @param nomSalle nouveau nom de la Salle.
     */
    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }

    /**
     * Setter de l'attribut capa.
     * @param capa nouvelle capacité de la salle.
     */
    public void setCapa(int capa) {
        this.capa = capa;
    }
    
    /**
     * Affecter un appareil à cette Salle.
     * @param app appareil à assigné.
     */
    public void affecterApp (Appareil app) {
        listeApp.add(app);
    }
    
    /**
     * Retirer un appareil de cette salle.
     * @param app appareil à retirer.
     */
    public void desaffecterApp (Appareil app) {
        listeApp.remove(app);
    }
    
    /**
     * Ajouter une nouvelle salle dans la base de données, avec le local auquel
     * elle est affectée.
     * @param nomLocal nom du local où est affectée la salle.
     */
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
    
    /**
     * Modifier dans la base de données une salle existante.
     * @param nomLocal nom du local où est affectée la salle.
     */
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
