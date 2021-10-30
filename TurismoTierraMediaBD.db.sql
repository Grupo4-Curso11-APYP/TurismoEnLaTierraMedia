BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "TipoAtraccion" (
	"id_tipoAtraccion"	TEXT NOT NULL,
	PRIMARY KEY("id_tipoAtraccion")
);
CREATE TABLE IF NOT EXISTS "Promocion" (
	"ID_Promocion"	INTEGER NOT NULL,
	"ID_Atraccion1"	INTEGER,
	"ID_Atraccion2"	INTEGER,
	"Nombre"	TEXT NOT NULL,
	"Tipo"	TEXT NOT NULL,
	"Monto"	NUMERIC,
	"Tiempo"	NUMERIC,
	"AtraccionGratis"	TEXT,
	"Descuento"	INTEGER,
	"PromocionTipo"	TEXT,
	FOREIGN KEY("Tipo") REFERENCES "TipoAtraccion"("id_tipoAtraccion"),
	FOREIGN KEY("ID_Atraccion2") REFERENCES "Atraccion"("ID_Atraccion"),
	FOREIGN KEY("ID_Atraccion1") REFERENCES "Atraccion"("ID_Atraccion"),
	PRIMARY KEY("ID_Promocion" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Itinerario" (
	"ID_Promocion"	INTEGER,
	"ID_Itinerario"	INTEGER NOT NULL,
	"ID_Usuario"	INTEGER NOT NULL,
	"ID_Atraccion"	INTEGER,
	FOREIGN KEY("ID_Usuario") REFERENCES "Usuario"("ID_Usuario"),
	FOREIGN KEY("ID_Atraccion") REFERENCES "Atraccion"("ID_Atraccion"),
	FOREIGN KEY("ID_Promocion") REFERENCES "Promocion"("ID_Promocion"),
	PRIMARY KEY("ID_Itinerario" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Atraccion" (
	"ID_Atraccion"	INTEGER NOT NULL,
	"Nombre"	TEXT NOT NULL UNIQUE,
	"Costo"	NUMERIC NOT NULL,
	"Tiempo"	NUMERIC NOT NULL,
	"Cupo_Disponible"	INTEGER NOT NULL,
	"TipoDeAtraccion"	TEXT NOT NULL,
	FOREIGN KEY("TipoDeAtraccion") REFERENCES "TipoAtraccion"("id_tipoAtraccion"),
	PRIMARY KEY("ID_Atraccion" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "Usuario" (
	"ID_Usuario"	INTEGER NOT NULL,
	"Nombre"	TEXT NOT NULL,
	"Presupuesto"	NUMERIC NOT NULL,
	"TiempoDisponible"	NUMERIC NOT NULL,
	"TipoFavorito"	TEXT NOT NULL,
	PRIMARY KEY("ID_Usuario" AUTOINCREMENT)
);
INSERT INTO "TipoAtraccion" ("id_tipoAtraccion") VALUES ('AVENTURA');
INSERT INTO "TipoAtraccion" ("id_tipoAtraccion") VALUES ('DEGUSTACION');
INSERT INTO "TipoAtraccion" ("id_tipoAtraccion") VALUES ('PAISAJE');
INSERT INTO "Promocion" ("ID_Promocion","ID_Atraccion1","ID_Atraccion2","Nombre","Tipo","Monto","Tiempo","AtraccionGratis","Descuento","PromocionTipo") VALUES (1,8,6,'Pack Aventura','AVENTURA','','','NULL',20,'PORCENTUAL');
INSERT INTO "Promocion" ("ID_Promocion","ID_Atraccion1","ID_Atraccion2","Nombre","Tipo","Monto","Tiempo","AtraccionGratis","Descuento","PromocionTipo") VALUES (2,4,1,'Pack Adrenalina','AVENTURA','','','NULL',20,'PORCENTUAL');
INSERT INTO "Promocion" ("ID_Promocion","ID_Atraccion1","ID_Atraccion2","Nombre","Tipo","Monto","Tiempo","AtraccionGratis","Descuento","PromocionTipo") VALUES (3,6,3,'Pack Degustación','DEGUSTACION',36,NULL,'',NULL,'ABSOLUTA');
INSERT INTO "Promocion" ("ID_Promocion","ID_Atraccion1","ID_Atraccion2","Nombre","Tipo","Monto","Tiempo","AtraccionGratis","Descuento","PromocionTipo") VALUES (4,2,7,'Pack Paisaje','PAISAJE',NULL,NULL,'5',NULL,'AxB');
INSERT INTO "Atraccion" ("ID_Atraccion","Nombre","Costo","Tiempo","Cupo_Disponible","TipoDeAtraccion") VALUES (1,'Moria',10,2,6,'AVENTURA');
INSERT INTO "Atraccion" ("ID_Atraccion","Nombre","Costo","Tiempo","Cupo_Disponible","TipoDeAtraccion") VALUES (2,'Minas Tirith',5,2.5,25,'PAISAJE');
INSERT INTO "Atraccion" ("ID_Atraccion","Nombre","Costo","Tiempo","Cupo_Disponible","TipoDeAtraccion") VALUES (3,'La Comarca',3,6.5,150,'DEGUSTACION');
INSERT INTO "Atraccion" ("ID_Atraccion","Nombre","Costo","Tiempo","Cupo_Disponible","TipoDeAtraccion") VALUES (4,'Mordor',25,3,4,'AVENTURA');
INSERT INTO "Atraccion" ("ID_Atraccion","Nombre","Costo","Tiempo","Cupo_Disponible","TipoDeAtraccion") VALUES (5,'Abismo de Helm',5,2,15,'PAISAJE');
INSERT INTO "Atraccion" ("ID_Atraccion","Nombre","Costo","Tiempo","Cupo_Disponible","TipoDeAtraccion") VALUES (6,'Lothlórien',35,1,30,'DEGUSTACION');
INSERT INTO "Atraccion" ("ID_Atraccion","Nombre","Costo","Tiempo","Cupo_Disponible","TipoDeAtraccion") VALUES (7,'Erebor',12,3,32,'PAISAJE');
INSERT INTO "Atraccion" ("ID_Atraccion","Nombre","Costo","Tiempo","Cupo_Disponible","TipoDeAtraccion") VALUES (8,'Bosque Negro',3,4,12,'AVENTURA');
INSERT INTO "Usuario" ("ID_Usuario","Nombre","Presupuesto","TiempoDisponible","TipoFavorito") VALUES (1,'Gandalf',100,5,'PAISAJE');
INSERT INTO "Usuario" ("ID_Usuario","Nombre","Presupuesto","TiempoDisponible","TipoFavorito") VALUES (2,'Sam',36,8,'DEGUSTACION');
INSERT INTO "Usuario" ("ID_Usuario","Nombre","Presupuesto","TiempoDisponible","TipoFavorito") VALUES (3,'Galadriel',120,6,'PAISAJE');
INSERT INTO "Usuario" ("ID_Usuario","Nombre","Presupuesto","TiempoDisponible","TipoFavorito") VALUES (6,'Eowyn',10,8,'AVENTURA');
COMMIT;
