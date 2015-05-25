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
 *
 * @author Vincent
 */
public class Appareil {
    private String nomApp;
    private String systEx;
    private final String typeApp;
    private String firmware;
    private boolean actif;
    private List<CarteReseau> listInterface;

    public Appareil(String nomApp, String systEx, String typeApp, String firmware, boolean actif) {
        this.nomApp = nomApp;
        this.systEx = systEx;
        this.typeApp = typeApp;
        this.firmware = firmware;
        this.actif = actif;
    }


    public String getNomApp() {
        return nomApp;
    }

    public void setNomApp(String nomApp) {
        this.nomApp = nomApp;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public void setSystEx(String systEx) {
        this.systEx = systEx;
    }

    public void setFirmware(String firmware) {
        this.firmware = firmware;
    }
    
    /**
     * 
     * @return 
     */
    private List<CarteReseau> obtenirListeInterface() {
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

    public void affecterCarte(CarteReseau c) {
        listInterface.add(c);
    }
    
    public void desaffecterCarte(CarteReseau c) {
        listInterface.remove(c);
    }
	
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
