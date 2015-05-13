/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maven_java_project;

import java.sql.*;

/**
 * Cette classe sert à se connecter à la BDD.
 * @author 21204416
 */
public class Connect {
    
    public static void main (String[] args)
    {
        try {
          Class.forName("org.postgresql.Driver");
          System.out.println("Driver O.K.");

          String url = "jdbc:postgresql://localhost:5432/AppliReseau";
          String user = "postgres";
          String passwd = "postgres";

          Connection conn = DriverManager.getConnection(url, user, passwd);
          System.out.println("Connexion effective !");         

        } catch (Exception e) {
          e.printStackTrace();
        }
    }
}