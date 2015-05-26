/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maven_java_project;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.applet.Main;

/**
 * Controleur gérant toute la logique applicative à partir des choix de l'utilisateur
 * fait dans la partie dialogue.
 * @author Vincent
 */
public class Controleur {
    
    // Attributes
    private List<Local> listeLocaux;
    private List<Salle> listeSalles;
    private List<Appareil> listeApp;
    private List<CarteReseau> listeCarte;

    public Controleur() {
        Hashtable<Local, ArrayList<Salle>> lienSalle = new Hashtable<Local, ArrayList<Salle>>(); 
        Hashtable<Salle, ArrayList<Appareil>> lienAppareil = new Hashtable<Salle, ArrayList<Appareil>>();
        Hashtable<Appareil, ArrayList<CarteReseau>> lienCarte = new Hashtable<Appareil, ArrayList<CarteReseau>>();    
        BDD bdd = new BDD();
        listeCarte = bdd.chargerListeCartes();
        listeApp = bdd.chargerListeApp();
        listeSalles = bdd.chargerListeSalle();
        listeLocaux = bdd.chargerListeLocal();
        for (int i = 0; i < listeSalles.size(); i++) {
            System.out.println(listeSalles.get(i).getNomSalle());
            
        }
        lienSalle = bdd.chargerLienSalle(listeLocaux);
        for (int i = 0; i < listeLocaux.size(); i++) {
            for (int j = 0; j < lienSalle.get(listeLocaux.get(i)).size(); j++) {
                listeLocaux.get(i).affecterSalle(lienSalle.get(listeLocaux.get(i)).get(j));
            }
        }

        lienAppareil = bdd.chargerLienAppareil(listeSalles);
        for (int i = 0; i < listeSalles.size(); i++) {
            for (int j = 0; j < lienAppareil.get(listeSalles.get(i)).size(); j++) {
                listeSalles.get(i).affecterApp(lienAppareil.get(listeSalles.get(i)).get(j));
            }
        }        
        
        lienCarte = bdd.chargerLienCarte(listeApp);
        for (int i = 0; i < listeApp.size(); i++) {
            for (int j = 0; j < lienCarte.get(listeApp.get(i)).size(); j++) {
                listeApp.get(i).affecterCarte(lienCarte.get(listeApp.get(i)).get(j));
            }
        } 
        
        for (int i = 0; i < listeSalles.size(); i++) {
            System.out.println("Salle = "+listeSalles.get(i).getNomSalle());
            for (int j = 0; j < listeSalles.get(i).getListeApp().size(); j++) {
                System.out.println("App = "+listeSalles.get(i).getListeApp().get(j).getNomApp());
            }
        }
        
        for (int i = 0; i < listeLocaux.size(); i++) {
            System.out.println("local :"+listeLocaux.get(i).getNomLocal());
            for (int j = 0; j < listeLocaux.get(i).getListeSalle().size(); j++) {
                System.out.println("Salle : "+listeLocaux.get(i).getListeSalle().get(j).getNomSalle());
                System.out.println("kmax : "+listeLocaux.get(i).getListeSalle().get(j).getListeApp().size());
                for (int k = 0; k < listeLocaux.get(i).getListeSalle().get(j).getListeApp().size(); k++) {
                    System.out.println("App : "+listeLocaux.get(i).getListeSalle().get(j).getListeApp().get(k).getNomApp());
                }
                
            }
        }
        
        int z = 0;
        int y = 0;
        int x = 0;
        Enumeration key = lienAppareil.keys();
        ArrayList<ArrayList<Appareil>> a = new ArrayList<ArrayList<Appareil>>(lienAppareil.values());
        while (key.hasMoreElements() && z<10) {
            System.out.println(key);
            while (y < a.size()) {
                while (x < a.get(y).size()) {
                    System.out.println(" : "+a.get(y).get(x).getNomApp());
                    x++;
                }
                y++;
            }
            key.nextElement();
            z++;
        }
    }

    public List<Local> getListeLocaux() {
        return listeLocaux;
    }

    public List<Salle> getListeSalles() {
        return listeSalles;
    }

    public List<Appareil> getListeApp() {
        return listeApp;
    }

    public List<CarteReseau> getListeCarte() {
        return listeCarte;
    }
    
    
    
    /**
     * Chercher un appareil en particulier dans la liste des appareils.
     * @param nom nom de l'appareil à chercher.
     * @return numéro d'index où se trouve l'appareil, ou -1 sinon.
     */
    public int chercherNomListeApp (String nom) {
        int i = 0;
        while (!listeApp.get(i).getNomApp().equals(nom)) {i++;}
        if (listeApp.get(i).getNomApp().equals(nom))
            return i;
        else
            return -1;
    }
    
    /**
     * Cherche une salle en particulier dans la liste des salles.
     * @param nom nom de la salle à chercher.
     * @return numéro d'index où se trouve la salle, ou -1 sinon.
     */
    public int chercherNomListeSalle (String nom) {
        /*int i = 0;
        while (!listeSalles.get(i).getNomSalle().equals(nom)) {i++;}
        if (listeSalles.get(i).getNomSalle().equals(nom))
            return i;
        else
            return -1;*/
        for (int i = 0; i < listeSalles.size(); i++) {
            if (listeSalles.get(i).getNomSalle().equals(nom))
                return i;
        }
        return -1;
    }
    
    /**
     * Cherche un local en particulier dans la liste des locaux.
     * @param nom nom du local à chercher.
     * @return numéro d'index où se trouve le local, ou -1 sinon.
     */
    public int chercherNomListeLocal (String nom) {
        /*int i = 0;
        while (!listeLocaux.get(i).getNomLocal().equals(nom)) {i++;}
        if (listeLocaux.get(i).getNomLocal().equals(nom))
            return i;
        else
            return -1;*/

         for (int i = 0; i < listeLocaux.size(); i++) {
            if (listeLocaux.get(i).getNomLocal().equals(nom)){
                return i;                
            }

        }
        return -1;       
        
    }
    
    /**
     * Activer un appareil à partir de son nom.
     * @param nomApp nom de l'appareil à activer.
     */
    public void activerApp (String nomApp) {
        int trouve;
        trouve = chercherNomListeApp(nomApp);
        if (trouve > -1)
            listeApp.get(trouve).setActif("true");
    }
    
    /**
     * Désactiver un appareil à partir de son nom.
     * @param nomApp nom de l'appareil à désactiver.
     */
    public void desactiverApp (String nomApp) {
        int trouve;
        trouve = chercherNomListeApp(nomApp);
        if (trouve > -1)
            listeApp.get(trouve).setActif("false");
    }
    
    /**
     * Ajouter un appareil dans l'application. Ne peut se faire sans salle dans laquelle le mettre.
     * @param nomApp nom de l'appareil à ajouter.
     * @param typeApp type d'appareil (tablette, ordinateur ou routeur).
     * @param sysExp système d'exploitation de l'appareil.
     * @param firm firmware installé sur l'appareil.
     * @param actif état de l'appareil (activé ou désactivé).
     * @param emp salle dans laquelle se trouve le nouvel appareil.
     */
    public void ajouterApp (String nomApp, String sysExp, String typeApp, String firm, String actif, String emp) {
        Appareil app = new Appareil(nomApp,sysExp,typeApp,firm,actif);
        listeApp.add(app);
        listeSalles.get(chercherNomListeSalle(emp)).affecterApp(app);
        app.ajoutAppBDD(emp);
    }
    
    /**
     * Ajouter une salle dans l'application. Ne peut se faire sans local dans lequel la mettre.
     * @param nomSalle nom de la salle à ajouter
     * @param capa capacité de la salle en nombre d'appareils
     * @param emp local dans lequel se trouve la salle
     */
    public void ajouterSalle (String nomSalle, int capa, String emp) {
        Salle s = new Salle(nomSalle,capa);
        listeSalles.add(s);
        listeLocaux.get(chercherNomListeLocal(emp)).affecterSalle(s);
        s.ajoutSalleBDD(emp);
    }
    
    /**
     * Ajouter un local dans l'application
     * @param nomLocal nom du local à ajouter.
     * @param adr adresse où se situe le local.
     * @param capa nombre de salles que nous pouvons placer dans ce local.
     */
    public void ajouterLocal (String nomLocal, int capa, String adr) {
        Local l = new Local(nomLocal,adr,capa);
        l.ajoutLocalBDD();
        listeLocaux.add(l);
    }
    
    /**
     * Ajouter une carte réseau dans l'application. Ne peut se faire sans appareil dans lequel la mettre.
     * @param adrMac adresse MAC de la carte réseau.
     * @param nomApp nom de l'appareil dans lequel va se trouver la carte.
     */
    public void ajouterCarteReseau (String adrMac, String nomApp) {
        CarteReseau c = new CarteReseau(adrMac);
        listeCarte.add(c);
        listeApp.get(chercherNomListeApp(nomApp)).affecterCarte(c);
        c.ajoutCarteBDD(nomApp);
    }
    
    /**
     * Modifier un ou plusieurs paramètres d'un appareil.
     * @param a l'appareil à modifier.
     * @param nouvNom le nouveau nom de l'appareil (peut être identique à nom).
     * @param emp la salle dans laquelle se trouve l'appareil.
     * @param sys le système d'exploitation de l'appareil.
     * @param firm le nom du firmware de l'appareil.
     */
    public void modifierApp (Appareil a, String nouvNom, String emp, String sys, String firm) {
        try {
            BDD bd = new BDD();
            bd.connect();
            bd.select("SELECT nomSalle FROM Appareil WHERE nomapp = '"+a.getNomApp()+"'");
            listeSalles.get(chercherNomListeLocal(bd.getResults().getString(1))).desaffecterApp(a);
        } catch (SQLException e) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, e);
        }
        listeSalles.get(chercherNomListeApp(emp)).affecterApp(a);
        a.setNomApp(nouvNom);
        a.setSystEx(sys);
        a.setFirmware(firm);
        a.modifAppBDD(emp);
    }
    
    /**
     * Modifier un ou plusieurs paramètres d'une salle
     * @param s salle à modifier.
     * @param nom nom de la salle.
     * @param emp local contenant la salle.
     * //@param capa nombre d'appareil pouvant être ajouté à la salle.
     */
    public void modifierSalle (Salle s, String nom, String emp/*, int capa*/) {
        try {
            BDD bd = new BDD();
            bd.connect();
            bd.select("SELECT nomLocal FROM Salle WHERE nomSalle = '"+s.getNomSalle()+"'");
            listeLocaux.get(chercherNomListeLocal(bd.getResults().getString(1))).desaffecterSalle(s);
        } catch (SQLException e) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, e);
        }
        listeLocaux.get(chercherNomListeApp(emp)).affecterSalle(s);
        s.setNomSalle(nom);
        //s.setCapa(capa);
        s.modifSalleBDD(emp);
    }
    
    /**
     * Modifier un ou plusieurs paramètres d'un local
     * @param ancienNom ancien nom du local.
     * @param nom nom du local.
     */
    public void modifierLocal (String ancienNom, String nom) {
        Local l = listeLocaux.get(chercherNomListeLocal(ancienNom));
        l.setNomLocal(nom);
        l.modifLocalBDD(ancienNom);
    }
    
    /**
     * Modifier l'adresse MAC d'une interface.
     * @param c carte réseau à modifier.
     * @param adrMac modifier l'adresse mac.
     * @param nomApp nom de l'appareil contenant la carte.
     */
    public void modifierCarteReseau (CarteReseau c, String adrMac, String nomApp) {
        try {
            BDD bd = new BDD();
            bd.connect();
            bd.select("SELECT nomApp FROM Carte_reseau WHERE adrMac='"+c.getAddrMAC()+"'");
            listeApp.get(chercherNomListeApp(bd.getResults().getString(1))).desaffecterCarte(c);
        } catch (SQLException e) {
            Logger.getLogger(Controleur.class.getName()).log(Level.SEVERE, null, e);
        }
        listeApp.get(chercherNomListeApp(nomApp)).affecterCarte(c);
        c.setAddrMAC(adrMac);
        c.modifCarteBDD(nomApp);
    }
    

}
