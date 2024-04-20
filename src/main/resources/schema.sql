DROP TABLE IF EXISTS cellar_wine;
DROP TABLE IF EXISTS wine;
DROP TABLE IF EXISTS cellar;
DROP TABLE IF EXISTS owner;

CREATE TABLE owner
(
   owner_id bigint NOT NULL AUTO_INCREMENT,
   username varchar (255) NOT NULL,
   password varchar (255) NOT NULL,
   PRIMARY KEY (owner_id)
);

CREATE TABLE cellar
(
   cellar_id bigint NOT NULL AUTO_INCREMENT,
   cellar_name varchar (256) NOT NULL,
   location varchar (128) NOT NULL,
   capacity integer,
   owner_id bigint NOT NULL,
   PRIMARY KEY (cellar_id),
   FOREIGN KEY (owner_id) REFERENCES owner (owner_id) ON DELETE CASCADE
);

CREATE TABLE wine
(
   wine_id bigint NOT NULL AUTO_INCREMENT,
   wine_name varchar (256) NOT NULL,
   vintage_year integer,
   varietal varchar (128) NOT NULL,
   color varchar (128) NOT NULL,
   appellation varchar (256) NOT NULL,
   quantity integer,
   bottle_price decimal,
   tasting_note varchar (512),
   PRIMARY KEY (wine_id)
);

CREATE TABLE cellar_wine
(
   wine_id bigint NOT NULL,
   cellar_id bigint NOT NULL,
   FOREIGN KEY (wine_id) REFERENCES wine (wine_id) ON DELETE CASCADE,
   FOREIGN KEY (cellar_id) REFERENCES cellar (cellar_id) ON DELETE CASCADE
);