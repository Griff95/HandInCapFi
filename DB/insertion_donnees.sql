INSERT INTO Batiments (nom,description,orientation_Ordonnee_NordM) VALUES ('Turing','Batiment Turing',0);
INSERT INTO Batiments (nom,description,orientation_Ordonnee_NordM) VALUES ('Condorcet','Batiment Condorcet',225);
INSERT INTO Batiments (nom,description,orientation_Ordonnee_NordM) VALUES ('Cauchy','Batiment Cauchy',135);

LOAD DATA LOCAL INFILE 'poi_BDD.csv'
INTO TABLE Points_Interets
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n'
(poi_id,device_type,device_code); 


-- Exemple importation fichier.csv
LOAD DATA LOCAL INFILE 'pts_BDD.csv'
INTO TABLE Points
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n'
(pt_id,nom,description,batiment_id,x,y,z,poi_id);

LOAD DATA LOCAL INFILE 'rejoindre.csv'
INTO TABLE Rejoindre
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n'
(pt_id1,pt_id2);


show warnings ;


UPDATE Points SET description=NULL WHERE description='';
UPDATE Points SET poi_id=NULL where poi_id = -1 ;

