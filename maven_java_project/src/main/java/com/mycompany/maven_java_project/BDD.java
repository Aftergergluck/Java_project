/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maven_java_project;
import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Interaction avec la BDD
 * @author Florian
 * @author Vincent
 */
public class BDD {
    
    // Attributs
    Connection conn;
    ResultSet results;

    public ResultSet getResults() {
        return results;
    }
    
    /**
     * Fonction de connexion à la BDD
     * 
     * @author Florian
     */
    public void connect(){
        try {
          Class.forName("org.postgresql.Driver");
          System.out.println("Driver O.K.");

          String url = "jdbc:postgresql://localhost:5432/java";
          String user = "postgres";
          String passwd = "postgres";

          conn = DriverManager.getConnection(url, user, passwd);
          System.out.println("Connexion effective !");         

        } catch (Exception e) {
          e.printStackTrace();
        }
        
    }
    
    /**
     * Fonction permettant d'exécuter une requête passée en paramètre
     * 
     * @author Florian
     * @param req requête à exécuter dans la BDD.
     */
    public void request(String req){
                try {
           
            // Création de la requête
            Statement request = conn.createStatement();
          
            // Exécution de la requête et récupération du résultat
            request.executeUpdate(req);
            // Affichage éventuel
            System.out.println("Tout s'est bien passé.\n");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Fonction testant si un attribut est présent dans la BDD
     * 
     * @author Florian
     * @return Boolean
     * 
     * @param table table concernée par la requête.
     * @param attribut attribut concerné par la requête.
     * @param valeur valeur dont on cherche l'existence.
     */
    
    public boolean exist(String table, String attribut, String valeur) throws SQLException{
        Statement request = conn.createStatement();        
        String query = "SELECT * FROM " +table+ " WHERE " +attribut+ " = '" +valeur+ "';";

         return request.executeQuery(query).next();
    }
    
    /**
     * Fonction permettant de faire un Select
     * @author Florian
     * @param req requête à exécuter dans la BDD.
     */
    public void select(String req){
        try {
           
            // Création de la requête
            Statement request = conn.createStatement();
          
            // Exécution de la requête et récupération du résultat
           results = request.executeQuery(req);
            // Affichage éventuel
           
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    } 
    
    /**
     * Charge la totalité des cartes réseau de la BDD dans un liste. 
     * @return la liste de toutes les cartes réseau de la BDD.
     */
    public List<CarteReseau> chargerListeCartes() {
        List<CarteReseau> list = new ArrayList();
        try {
            BDD bd = new BDD();
            bd.connect();
            bd.select("SELECT * FROM Carte_reseau");
            ResultSet r = bd.getResults();

            while (r.next()) {
                CarteReseau cr = new CarteReseau(r.getString(1));
                list.add(cr);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    } 
    
    /**
     * Charge la totalité des appareils de la BDD dans un liste.
     * @return la liste de tous les appareils de la BDD.
     */
    public List<Appareil> chargerListeApp() {
        List<Appareil> list = new ArrayList();
        try {
            BDD bd = new BDD();
            bd.connect();
            bd.select("SELECT * FROM Appareil");
            ResultSet r = bd.getResults();

            while (r.next()) {
                Appareil a = new Appareil(r.getString(1),r.getString(3),r.getString(2),r.getString(4),r.getString(5));
                list.add(a);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    } 
    
    /**
     * Charge la totalité des salles de la BDD dans une liste.
     * @return la liste de toutes les salles de la BDD.
     */
    public List<Salle> chargerListeSalle() {
        List<Salle> list = new ArrayList();
        try {
            BDD bd = new BDD();
            bd.connect();
            bd.select("SELECT * FROM Salle");
            ResultSet r = bd.getResults();

            while (r.next()) {
                Salle s = new Salle(r.getString(1),r.getInt(2));
                list.add(s);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    } 
    
    /**
     * Charge la totalité des locaux de la BDD dans une liste.
     * @return la liste de tous les locaux de la BDD.
     */
    public List<Local> chargerListeLocal() {
        List<Local> list = new ArrayList();
        try {
            BDD bd = new BDD();
            bd.connect();
            bd.select("SELECT * FROM Local");
            ResultSet r = bd.getResults();

            while (r.next()) {
                Local l = new Local(r.getString(1),r.getString(2),r.getInt(3));
                list.add(l);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
 
    
    /**
     * Créé une hashtable qui contient les salles affectées à chaque local
     * @param listLocal Liste de la totalité des locaux
     * @return Hashtable(local, salle)
     */
    public Hashtable<Local, ArrayList<Salle>> chargerLienSalle(List<Local> listLocal){
        Hashtable<Local, ArrayList<Salle>> lienSalle = new Hashtable<Local, ArrayList<Salle>>();
        BDD bd = new BDD();
        bd.connect();
        ResultSet r;
        for (int i = 0; i < listLocal.size(); i++) {
            try {
                String nomLocal = listLocal.get(i).getNomLocal();
                bd.select("SELECT * FROM Salle WHERE nomLocal = '"+nomLocal+"';");
                r = bd.getResults();
                ArrayList<Salle> listSalle = new ArrayList<Salle>();
                while (r.next()) {
                    Salle s = new Salle(r.getString(1),r.getInt(2));
                    listSalle.add(s);            
                }
                System.out.println("Liste salle :" +listSalle);
                lienSalle.put(listLocal.get(i), listSalle);
                
            } catch (SQLException ex) {
                Logger.getLogger(BDD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lienSalle;
    }

    /**
     * Créé une hashtable qui contient les appareils affectées à chaque salle
     * @param listSalle
     * @return hashtable(salle, appareil)
     */
    public Hashtable<Salle, ArrayList<Appareil>> chargerLienAppareil(List<Salle> listSalle){
        Hashtable<Salle, ArrayList<Appareil>> lienAppareil = new Hashtable<Salle, ArrayList<Appareil>>();
        BDD bd = new BDD();
        bd.connect();
        ResultSet r;
        for (int i = 0; i < listSalle.size(); i++) {
            try {
                String nomSalle = listSalle.get(i).getNomSalle();
                bd.select("SELECT * FROM Appareil WHERE nomSalle = '"+nomSalle+"';");
                r = bd.getResults();
                ArrayList<Appareil> listApp = new ArrayList<Appareil>();
                while (r.next()) {
                    Appareil a = new Appareil(r.getString(1),r.getString(3),r.getString(2),r.getString(4),r.getString(5));
                    listApp.add(a);           
                }
                lienAppareil.put(listSalle.get(i), listApp);
            } catch (SQLException ex) {
                Logger.getLogger(BDD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lienAppareil;
    }

    /**
     * Créé une hashtable qui contient les cartes réseaux affectées à chaque appareil
     * @param listApp
     * @return hashtable(appareil, carte)
     */
    public Hashtable<Appareil, ArrayList<CarteReseau>> chargerLienCarte(List<Appareil> listApp){
        Hashtable<Appareil, ArrayList<CarteReseau>> lienCarte = new Hashtable<Appareil, ArrayList<CarteReseau>>();
        BDD bd = new BDD();
        bd.connect();
        ResultSet r;
        for (int i = 0; i < listApp.size(); i++) {
            try {
                String nomApp = listApp.get(i).getNomApp();
                bd.select("SELECT * FROM Carte_reseau WHERE nomapp = '" +nomApp+"';");
                r = bd.getResults();
                ArrayList<CarteReseau> listCarte = new ArrayList<CarteReseau>();
                while (r.next()) {
                    CarteReseau cr = new CarteReseau(r.getString(1));
                    listCarte.add(cr);          
                }
                lienCarte.put(listApp.get(i), listCarte);
                
            } catch (SQLException ex) {
                Logger.getLogger(BDD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lienCarte;
    }
    
}
