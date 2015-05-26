/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maven_java_project;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Entité représentant les locaux contenant les salles.
 * @author Vincent
 */
public class Local {
    
    private String nomLocal;
    private String adr;
    private int capa;
    private List<Salle> listeSalle;

    /**
     * Constructeur d'un local à partir de plusieurs informations.
     * @param nomLocal nom du local.
     * @param capa nombre maximum de salle possible dans ce local.
     * @param adr adresse du local.
     */
    public Local(String nomLocal, String adr, int capa) {
        this.nomLocal = nomLocal;
        this.capa = capa;
        this.adr = adr;
        this.listeSalle = new ArrayList<Salle>();
    }

    public List<Salle> getListeSalle() {
        return listeSalle;
    }

    
    
    /**
     * Getter de l'attribut nomLocal.
     * @return le nom du local.
     */
    public String getNomLocal() {
        return nomLocal;
    }

    /**
     * Setter de l'attribut nomLocal
     * @param nomLocal nom du local.
     */
    public void setNomLocal(String nomLocal) {
        this.nomLocal = nomLocal;
    }

    /**
     * Setter de l'attribut adr
     * @param adr adresse du local.
     */
    public void setAdr(String adr) {
        this.adr = adr;
    }

    /**
     * Setter de l'attribut capa.
     * @param capa nombre maximum de salle contenu dans le local.
     */
    public void setCapa(int capa) {
        this.capa = capa;
    }

    /**
     * Ajouter une salle à la liste des salles du local.
     * @param s salle à ajouter.
     */
    public void affecterSalle (Salle s) {
        listeSalle.add(s);
    }

    /**
     * Retirer une salle de la liste des salles du local.
     * @param s salle à retirer.
     */
    public void desaffecterSalle (Salle s) {
        listeSalle.remove(s);
    }
    
    /**
     * Ajouter un nouveau local dans la base de données.
     */
    public void ajoutLocalBDD () {
        try {
            BDD bdd = new BDD();
            bdd.connect();
            if (!bdd.exist("Local","nomLocal",nomLocal))
                bdd.request("INSERT INTO Local VALUES ('"+nomLocal+"','"+adr+"','"+capa+"')");
        } catch (SQLException e) {
            Logger.getLogger(Local.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    /**
     * Modifier un local existant dans la base de données.
     * @param ancienNom nom du local avant modification.
     */
    public void modifLocalBDD (String ancienNom) {
        BDD bdd = new BDD();
        bdd.connect();
        bdd.request("ALTER TABLE local disable trigger all;UPDATE local SET nomlocal = '"+nomLocal+"', lieulocal = '"+adr+"', nbrsalle = '"+capa+"' WHERE nomLocal = '"+ancienNom+"';ALTER TABLE local enable trigger all;");
        bdd.request("UPDATE salle SET nomlocal = '"+nomLocal+"' WHERE nomlocal = '" +ancienNom+"'");
    }
    
}
