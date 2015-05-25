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
public class Local {
    
    // Attributes
    private String nomLocal;
    private String adr;
    private int capa;
    private List<Salle> listeSalle;

    /**
     * 
     * @param nomLocal
     * @param capa
     * @param adr 
     */
    public Local(String nomLocal, String adr, int capa) {
        this.nomLocal = nomLocal;
        this.capa = capa;
        this.adr = adr;
    }

    public String getNomLocal() {
        return nomLocal;
    }

    public void setNomLocal(String nomLocal) {
        this.nomLocal = nomLocal;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public void setCapa(int capa) {
        this.capa = capa;
    }

    public void affecterSalle (Salle s) {
        listeSalle.add(s);
    }

    public void desaffecterSalle (Salle s) {
        listeSalle.remove(s);
    }
    
    public void ajoutLocalBDD () {
        try {
            BDD bdd = new BDD();
            bdd.connect();
            if (!bdd.exist("Local","nomLocal",nomLocal))
                bdd.request("INSERT INTO Local VALUES ('"+nomLocal+"','"+adr+"','"+capa+"')");
        } catch (SQLException e) {
            Logger.getLogger(Ajouter.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void modifLocalBDD () {
        try {
            BDD bdd = new BDD();
            bdd.connect();
            if (!bdd.exist("Local","nomLocal",nomLocal))
                bdd.request("UPDATE local SET nomlocal = '"+nomLocal+"' AND lieulocal = '"+adr+"' AND nbrsalle = '"+capa+"')");
        } catch (SQLException e) {
            Logger.getLogger(Ajouter.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}
