	/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maven_java_project;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vincent
 */
public class CarteReseau {

    private String addrMAC;

    /**
     * use mac
     *
     * @param addrMAC mac addrss
     */
    public CarteReseau(String addrMAC) {
        this.addrMAC = addrMAC;
    }

    public String getAddrMAC() {
        return addrMAC;
    }

    public void setAddrMAC(String addrMAC) {
        this.addrMAC = addrMAC;
    }
    
    public void ajoutCarteBDD(String nomApp) {
        try {
            BDD bdd = new BDD();
            bdd.connect();
            if (!bdd.exist("Carte_reseau","adrmacappareil",addrMAC))
                bdd.request("INSERT INTO carte_reseau VALUES ('"+addrMAC+"','"+nomApp+"')");
        } catch (SQLException e) {
            Logger.getLogger(Ajouter.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void modifCarteBDD(String nomApp) {
        try {
            BDD bdd = new BDD();
            bdd.connect();
            if (!bdd.exist("Carte_reseau","adrmacappareil",addrMAC))
                bdd.request("UPDATE carte_reseau SET adrmacappareil = '"+addrMAC+"' AND nomapp = '"+nomApp+"'");
        } catch (SQLException e) {
            Logger.getLogger(Ajouter.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
