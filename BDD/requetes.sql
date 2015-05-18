
CREATE TABLE Local(
        nomLocal  Varchar (25) NOT NULL ,
        lieuLocal Varchar (25) NOT NULL ,
        nbrSalle  int,
        PRIMARY KEY (nomLocal )
);




CREATE TABLE Salle(
        nomSalle    Varchar (25) NOT NULL ,
        lieuSalle   Varchar (32) NOT NULL ,
        nomLocal    Varchar  (32) NOT NULL,
        PRIMARY KEY (nomSalle )
);




CREATE TABLE Appareil(
        nomApp          Varchar (32) NOT NULL ,
        typeAppareil    Varchar (32) NOT NULL ,
        SystExpAppareil Varchar (32) ,
        Nomfirmware     Varchar (32) NOT NULL ,
        Active          Bool ,
        lieuAppareil    Varchar (32) ,
        nomSalle        Varchar (32) NOT NULL ,
        PRIMARY KEY (nomApp )
);




CREATE TABLE Carte_Reseau(
        adrMacAppareil Varchar (32) NOT NULL ,
        nomApp         Varchar (32) NOT NULL ,
        PRIMARY KEY (adrMacAppareil )
);



CREATE TABLE etreConnecter(
        nomApp          Varchar (32) NOT NULL ,
        nomApp_Appareil Varchar (32) NOT NULL ,
        PRIMARY KEY (nomApp ,nomApp_Appareil )
);

ALTER TABLE Salle ADD CONSTRAINT FK_Salle_nomLocal FOREIGN KEY (nomLocal) REFERENCES Local(nomLocal);
ALTER TABLE Appareil ADD CONSTRAINT FK_Appareil_nomSalle FOREIGN KEY (nomSalle) REFERENCES Salle(nomSalle);
ALTER TABLE Carte_Reseau ADD CONSTRAINT FK_Carte_Reseau_nomApp FOREIGN KEY (nomApp) REFERENCES Appareil(nomApp);
ALTER TABLE etreConnecter ADD CONSTRAINT FK_etreConnecter_nomApp FOREIGN KEY (nomApp) REFERENCES Appareil(nomApp);
ALTER TABLE etreConnecter ADD CONSTRAINT FK_etreConnecter_nomApp_Appareil FOREIGN KEY (nomApp_Appareil) REFERENCES Appareil(nomApp);
