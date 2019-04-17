DROP TABLE IF EXISTS Rejoindre ;
DROP TABLE IF EXISTS Points ;
DROP TABLE IF EXISTS Batiments ; 
DROP TABLE IF EXISTS Points_Interets ;

CREATE TABLE IF NOT EXISTS Batiments(
	batiment_id int PRIMARY KEY NOT NULL AUTO_INCREMENT ,
	nom varchar(255) NOT NULL,
	description varchar(255) DEFAULT NULL,
	orientation_Ordonnee_NordM int NOT NULL 
);


CREATE TABLE IF NOT EXISTS Points_Interets(
	poi_id int PRIMARY KEY NOT NULL,
	device_type varchar(16) NOT NULL,
	device_code varchar(128) NOT NULL
);


CREATE TABLE IF NOT EXISTS Points(
	pt_id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
	nom varchar(255),
	description varchar(255),
	x int NOT NULL,
	y int NOT NULL,
	z int NOT NULL,
	batiment_id int NOT NULL,
	poi_id int DEFAULT NULL,
	FOREIGN KEY (batiment_id) REFERENCES Batiments(batiment_id),
	FOREIGN KEY (poi_id) REFERENCES Points_Interets(poi_id)
);

CREATE TABLE IF NOT EXISTS Rejoindre(
	pt_id1 int NOT NULL,
	pt_id2 int NOT NULL,
	FOREIGN KEY (pt_id1) REFERENCES Points(pt_id),
	FOREIGN KEY (pt_id2) REFERENCES Points(pt_id),
	CONSTRAINT PRIMARY KEY (pt_id1,pt_id2)
);

CREATE UNIQUE INDEX couple_device ON Points_Interets(device_code,device_type) ;


