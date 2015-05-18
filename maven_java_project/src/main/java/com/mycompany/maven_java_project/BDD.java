/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maven_java_project;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BADENS
 */
public class BDD {
Connection conn;

ResultSet results;


    
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
    
    public boolean exist(String table, String attribut, String valeur) throws SQLException{
        Statement request = conn.createStatement();        
        String query = "SELECT * FROM " +table+ " WHERE " +attribut+ " = '" +valeur+ "';";

         return request.executeQuery(query).next();
    }
    
    
    public ResultSet select(String req){
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
    return results;
        
    } 
    
}
