	/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maven_java_project;

/**
 *
 * @author 21204416
 */
public class CarteReseau {

    String addrMAC;

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
}
