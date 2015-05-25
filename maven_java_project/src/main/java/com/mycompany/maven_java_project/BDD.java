/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maven_java_project;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Interaction avec la BDD
 * @author Florian
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
     * 
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
     * Paramètres : table , attribut , valeur attribut
     * 
     * @author Florian
     * @return Boolean
     * 
     * @param attribut
     * @param valeur
     */
    
    public boolean exist(String table, String attribut, String valeur) throws SQLException{
        Statement request = conn.createStatement();        
        String query = "SELECT * FROM " +table+ " WHERE " +attribut+ " = '" +valeur+ "';";

         return request.executeQuery(query).next();
    }
    
    /**
     * Fonction permettant de faire un Select
     * @author Florian
     * @return Resultat
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
            Logger.getLogger(Appareil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    } 
    
    public List<Appareil> chargerListeApp() {
        List<Appareil> list = new ArrayList();
        try {
            BDD bd = new BDD();
            bd.connect();
            bd.select("SELECT * FROM Appareil");
            ResultSet r = bd.getResults();

            while (r.next()) {
                Appareil a = new Appareil(r.getString(1),r.getString(3),r.getString(2),r.getString(4),r.getBoolean(5));
                list.add(a);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Appareil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    } 
    
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
            Logger.getLogger(Appareil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    } 
    
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
            Logger.getLogger(Appareil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}
