
package com.mycompany.maven_java_project;

import java.sql.*;

/**
 * Cette classe sert à créer et remplir les tables de la BDD.
 * @author 21204416
 */
public class CreationInsertionTable {
    
    public static void main (String[] args) {
        try {
            // Récupération driver
            Class.forName("org.postgresql.Driver");
            
            // Identifiants de connexion à la base
            String url = "jdbc:postgresql://localhost:5432/java";
            String user = "postgres";
            String pwd = "postgres";
            
            // Connexion à la base
            Connection conn = DriverManager.getConnection(url,user,pwd);
            
            // Création de la requête
            Statement request = conn.createStatement();
            String query = "INSERT INTO Appareil (idAppareil,nomApp,typeAppareil,SystExpAppareil,nomFirmware,Active,lieuAppareil,numSalle) VALUES (5,'chirac','ordinateur', 'windows','BIOS','TRUE','U2', 6);";
            
            // Exécution de la requête et récupération du résultat
            ResultSet res = request.executeQuery(query);
            
            // Affichage éventuel
            System.out.println("Tout s'est bien passé.\n");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
