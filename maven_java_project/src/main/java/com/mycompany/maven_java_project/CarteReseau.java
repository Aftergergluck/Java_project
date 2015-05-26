	/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maven_java_project;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Entité représentant les interfaces réseaux des appareils.
 * @author Vincent
 */
public class CarteReseau {

    private String addrMAC;

    /**
     * Constructeur de CarteReseau à partir d'une adresse MAC.
     * @param addrMAC mac addrss
     */
    public CarteReseau(String addrMAC) {
        this.addrMAC = addrMAC;
    }

    /**
     * Getter de l'attribut adresse MAC.
     * @return l'adresse MAC de la carte sous forme de String.
     */
    public String getAddrMAC() {
        return addrMAC;
    }

    /**
     * Setter de l'attribut adresse MAC.
     * @param addrMAC la nouvelle adresse MAC de la carte.
     */
    public void setAddrMAC(String addrMAC) {
        this.addrMAC = addrMAC;
    }
    
    /**
     * Ajoute une nouvelle CarteReseau dans la base de données, avec l'appareil
     * auquel elle est assignée.
     * @param nomApp nom de l'appareil contenant la carte réseau en question.
     */
    public void ajoutCarteBDD(String nomApp) {
        try {
            BDD bdd = new BDD();
            bdd.connect();
            if (!bdd.exist("Carte_reseau","adrmacappareil",addrMAC))
                bdd.request("INSERT INTO carte_reseau VALUES ('"+addrMAC+"','"+nomApp+"')");
        } catch (SQLException e) {
            Logger.getLogger(CarteReseau.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    /**
     * Modifier dans la base de données une carte existante.
     * @param nomApp nom de l'appareil contenant la carte réseau en question.
     */
    public void modifCarteBDD(String nomApp) {
        try {
            BDD bdd = new BDD();
            bdd.connect();
            if (!bdd.exist("Carte_reseau","adrmacappareil",addrMAC))
                bdd.request("UPDATE carte_reseau SET adrmacappareil = '"+addrMAC+"' AND nomapp = '"+nomApp+"'");
        } catch (SQLException e) {
            Logger.getLogger(CarteReseau.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
