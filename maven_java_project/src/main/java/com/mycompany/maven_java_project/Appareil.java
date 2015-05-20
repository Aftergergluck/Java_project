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
 * @author 21204416
 */
public class Appareil {
    	private String nomApp;
    private String emplacement;
    private String systEx;
    private String typeApp;
    private boolean actif;
    private List<CarteReseau> listInterface;

    public Appareil(String nomApp, String emplacement, String systEx, String typeApp, boolean actif, List<CarteReseau> listInterface) {
        this.nomApp = nomApp;
        this.emplacement = emplacement;
        this.systEx = systEx;
        this.typeApp = typeApp;
        this.actif = actif;
        this.listInterface = listInterface;


    }

    public String getNomApp() {
        return nomApp;
    }

    public void setNomApp(String nomApp) {
        this.nomApp = nomApp;
    }

    private List<CarteReseau> obtenirListeInterface() {
        this.listInterface = new ArrayList<CarteReseau>();
        try {
            BDD bd = new BDD();
            bd.connect();
            bd.request("SELECT adrmacappareil FROM carte_reseau WHERE nomapp = '" + this.getNomApp() + "';");
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
	
    
    
}
