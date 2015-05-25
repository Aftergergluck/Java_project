/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maven_java_project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Entité représentant les appareils (ordinateurs, tablettes, routeur).
 * @author Vincent
 */
public class Appareil {
    
    private String nomApp;
    private String systEx;
    private final String typeApp;
    private String firmware;
    private boolean actif;
    private List<CarteReseau> listInterface;

    /**
     * Constructeur d'un Appareil à partir de plusieurs informations.
     * @param nomApp nom de l'appareil.
     * @param systEx système d'exploitation de l'appareil.
     * @param typeApp type de l'appareil (ordinateur, tablette, routeur).
     * @param firmware nom du firmware de l'appareil.
     * @param actif état de l'appareil (true = actif, false = inactif).
     */
    public Appareil(String nomApp, String systEx, String typeApp, String firmware, boolean actif) {
        this.nomApp = nomApp;
        this.systEx = systEx;
        this.typeApp = typeApp;
        this.firmware = firmware;
        this.actif = actif;
    }

    /**
     * Getter de l'attribut nomApp de l'appareil.
     * @return le nom de l'appareil sous forme de String.
     */
    public String getNomApp() {
        return nomApp;
    }

    /**
     * Setter de l'attribut nomApp de l'appareil.
     * @param nomApp nouveau nom de l'appareil.
     */
    public void setNomApp(String nomApp) {
        this.nomApp = nomApp;
    }

    /**
     * Setter de l'état de l'appareil.
     * @param actif booléen représentant l'état d'activité de l'appareil.
     */
    public void setActif(boolean actif) {
        this.actif = actif;
    }

    /**
     * Setter de l'attribut systEx de l'appareil.
     * @param systEx nouveau système d'exploitation de l'appareil.
     */
    public void setSystEx(String systEx) {
        this.systEx = systEx;
    }

    /**
     * Setter de l'attribut firmware de l'appareil.
     * @param firmware nouveau nom du firmware de l'appareil.
     */
    public void setFirmware(String firmware) {
        this.firmware = firmware;
    }
    
    /**
     * Demande à la base de données la liste des cartes réseau d'un appareil.
     * @return liste contenant les cartes réseau de l'appareil.
     */
    private List<CarteReseau> obtenirListeCartes() {
        this.listInterface = new ArrayList();
        try {
            BDD bd = new BDD();
            bd.connect();
            bd.select("SELECT adrmacappareil FROM carte_reseau WHERE nomapp = '" + this.getNomApp() + "';");
            ResultSet result = bd.getResults();

            while (result.next()) {
                CarteReseau cr = new CarteReseau(result.getString(1));
                listInterface.add(cr);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Appareil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listInterface;

    }

    /**
     * Ajouter une CarteReseau à la liste des cartes de l'appareil.
     * @param c nouvelle carte réseau.
     */
    public void affecterCarte(CarteReseau c) {
        listInterface.add(c);
    }
    
    /**
     * Retirer une CarteReseau de la liste des cartes de l'appareil.
     * @param c carte réseau à retirer.
     */
    public void desaffecterCarte(CarteReseau c) {
        listInterface.remove(c);
    }

    /**
     * Ajouter un nouvel appareil à la base de données, avec la salle à laquelle
     * il est assigné.
     * @param nomSalle nom de la salle où l'appareil est assigné.
     */
    public void ajoutAppBDD (String nomSalle) {
        try {
            BDD bdd = new BDD();
            bdd.connect();
            if (!bdd.exist("Appareil","nomApp",nomApp))
                bdd.request("INSERT INTO Appareil VALUES ('"+nomApp+"','"+typeApp+"','"+systEx+"','"+firmware+"',"+actif+",'"+nomSalle+"')");
        } catch (SQLException e) {
            Logger.getLogger(Ajouter.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    /**
     * Modifier dans la base de données un appareil existant.
     * @param nomSalle nom de la salle où l'appareil est assigné.
     */
    public void modifAppBDD (String nomSalle) {
        try {
            BDD bdd = new BDD();
            bdd.connect();
            if (bdd.exist("Appareil","nomApp",nomApp))
                bdd.request("UPDATE Appareil SET nomApp='"+nomApp+"' AND typeappareil='"+typeApp+"' AND sysexpappareil='"+systEx+"' AND nomfirmware='"+firmware+"' AND active="+actif+" AND nomSalle='"+nomSalle+"';");
        } catch (SQLException e) {
            Logger.getLogger(Ajouter.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}
