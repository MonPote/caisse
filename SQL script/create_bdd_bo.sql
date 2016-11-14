--Drop table

DROP TABLE "Back Office"."Client";
DROP TABLE "Back Office"."Command";
DROP TABLE "Back Office"."Command_detail";
DROP TABLE "Back Office"."Coupon";
DROP TABLE "Back Office"."Product";
DROP TABLE "Back Office"."Product_coupon";
DROP TABLE "Back Office"."Product_promotion";
DROP TABLE "Back Office"."Local_promotion";
DROP TABLE "Back Office"."Rule";

--Drop sequence
DROP SEQUENCE "Back Office"."Client_id_seq";
DROP SEQUENCE "Back Office"."Commande_id_seq";
DROP SEQUENCE "Back Office"."Coupon_id_seq";
DROP SEQUENCE "Back Office"."Produit_id_seq";
DROP SEQUENCE "Back Office"."Promotion_locale_id_seq";
DROP SEQUENCE "Back Office"."Regle_id_seq";

--Sequences


-- Sequence: "Back Office"."Client_id_seq"

CREATE SEQUENCE "Back Office"."Client_id_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE "Back Office"."Client_id_seq"
  OWNER TO postgres;

-- Sequence: "Back Office"."Commande_id_seq"

CREATE SEQUENCE "Back Office"."Commande_id_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE "Back Office"."Commande_id_seq"
  OWNER TO postgres;


-- Sequence: "Back Office"."Coupon_id_seq"

CREATE SEQUENCE "Back Office"."Coupon_id_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE "Back Office"."Coupon_id_seq"
  OWNER TO postgres;


-- Sequence: "Back Office"."Produit_id_seq"

CREATE SEQUENCE "Back Office"."Produit_id_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE "Back Office"."Produit_id_seq"
  OWNER TO postgres;



-- Sequence: "Back Office"."Promotion_locale_id_seq"

CREATE SEQUENCE "Back Office"."Promotion_locale_id_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE "Back Office"."Promotion_locale_id_seq"
  OWNER TO postgres;


-- Sequence: "Back Office"."Regle_id_seq"

CREATE SEQUENCE "Back Office"."Regle_id_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE "Back Office"."Regle_id_seq"
  OWNER TO postgres;


DROP DOMAIN IF EXISTS "Unit_type";

CREATE DOMAIN "Unit_type" VARCHAR(10)
     CHECK (UPPER(VALUE) IN ('percentage', 'euros', 'unit'));



-- Table: "Back Office"."Client"

CREATE TABLE "Back Office"."Client"
(
  id serial NOT NULL,
  "Nom" character varying(64) NOT NULL,
  CONSTRAINT "cle primaire client" PRIMARY KEY (id),
  CONSTRAINT "unique client" UNIQUE (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "Back Office"."Client"
  OWNER TO postgres;




-- Table: "Back Office"."Command"

CREATE TABLE "Back Office"."Command"
(
  id integer NOT NULL DEFAULT nextval('"Back Office"."Commande_id_seq"'::regclass),
  "Ref" character varying(32) NOT NULL,
  "Date_envoi" date NOT NULL,
  "Date_reception" date,
  id_client integer,
  CONSTRAINT "cle primaire commande" PRIMARY KEY (id),
  CONSTRAINT "unique commande" UNIQUE (id, "Ref")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "Back Office"."Command"
  OWNER TO postgres;



-- Table: "Back Office"."Command_detail"

CREATE TABLE "Back Office"."Command_detail"
(
  id_commande integer NOT NULL,
  id_produit integer NOT NULL,
  "Quantite" integer NOT NULL,
  "Pertes" integer NOT NULL,
  CONSTRAINT "cle primaire commande detail" PRIMARY KEY (id_commande, id_produit)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "Back Office"."Command_detail"
  OWNER TO postgres;


-- Table: "Back Office"."Coupon"

CREATE TABLE "Back Office"."Coupon"
(
  id serial NOT NULL,
  "Ref" character varying(32) NOT NULL,
  "Designation" character varying(64) NOT NULL,
  id_regle integer NOT NULL,
  "Date_start" date NOT NULL,
  "Date_stop" date NOT NULL,
  "Valide" boolean NOT NULL,
  id_client integer NOT NULL,
  CONSTRAINT "clé primaire coupon" PRIMARY KEY (id),
  CONSTRAINT "unique coupon" UNIQUE (id, "Ref")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "Back Office"."Coupon"
  OWNER TO postgres;


-- Table: "Back Office"."Product"

CREATE TABLE "Back Office"."Product"
(
  id integer NOT NULL DEFAULT nextval('"Back Office"."Produit_id_seq"'::regclass),
  "Ref" character varying(32) NOT NULL,
  "Designation" character varying(64) NOT NULL,
  "Quantite" integer NOT NULL,
  "Prix" integer NOT NULL,
  "Stock_min" integer NOT NULL,
  "Stock_max" integer NOT NULL,
  CONSTRAINT "cle primaire produit" PRIMARY KEY (id),
  CONSTRAINT "unique produit" UNIQUE (id, "Ref"),
  CONSTRAINT "Produit.Prix" CHECK ("Prix" > 0),
  CONSTRAINT "Produit.Stock_max" CHECK ("Stock_max" >= 0),
  CONSTRAINT "Produit.Stock_min" CHECK ("Stock_min" >= 0)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "Back Office"."Product"
  OWNER TO postgres;

-- Table: "Back Office"."Product_coupon"

CREATE TABLE "Back Office"."Product_coupon"
(
  id_produit integer NOT NULL,
  id_coupon integer NOT NULL,
  CONSTRAINT "cle primaire produit coupon" PRIMARY KEY (id_produit, id_coupon)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "Back Office"."Product_coupon"
  OWNER TO postgres;

-- Table: "Back Office"."Product_promotion"

CREATE TABLE "Back Office"."Product_promotion"
(
  id_produit integer NOT NULL,
  id_promotion integer NOT NULL,
  CONSTRAINT "cle primaire produit promotion" PRIMARY KEY (id_produit, id_promotion)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "Back Office"."Product_promotion"
  OWNER TO postgres;


-- Table: "Back Office"."Local_promotion"

CREATE TABLE "Back Office"."Local_promotion"
(
  id integer NOT NULL DEFAULT nextval('"Back Office"."Promotion_locale_id_seq"'::regclass),
  "Ref" character varying(32) NOT NULL,
  "Designation" character(64) NOT NULL,
  id_regle integer NOT NULL,
  "Date_start" date NOT NULL,
  "Date_stop" date NOT NULL,
  "Recurence" boolean NOT NULL,
  CONSTRAINT "clé primaire id promo" PRIMARY KEY (id),
  CONSTRAINT "unique promo" UNIQUE (id, "Ref")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "Back Office"."Local_promotion"
  OWNER TO postgres;
COMMENT ON TABLE "Back Office"."Local_promotion"
  IS 'Table des promotions locales au magasin.';


-- Table: "Back Office"."Rule"

CREATE TABLE "Back Office"."Rule"
(
  id integer NOT NULL DEFAULT nextval('"Back Office"."Regle_id_seq"'::regclass),
  "Montant" integer NOT NULL,
  "Resultat" integer,
  "Unit" "Unit_type",
  CONSTRAINT "clé primaire regle" PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "Back Office"."Rule"
  OWNER TO postgres;


SELECT 
  * 
FROM 
  "Back Office"."Client", 
  "Back Office"."Command", 
  "Back Office"."Command_detail", 
  "Back Office"."Coupon", 
  "Back Office"."Product", 
  "Back Office"."Product_coupon", 
  "Back Office"."Product_promotion", 
  "Back Office"."Local_promotion", 
  "Back Office"."Rule"
WHERE 
  "Client".id = "Command".id_client AND
  "Command".id = "Command_detail".id_commande AND
  "Command_detail".id_produit = "Product".id AND
  "Coupon".id_regle = "Rule".id AND
  "Coupon".id = "Product_coupon".id_coupon AND
  "Coupon".id_client = "Client".id AND
  "Product_coupon".id_produit = "Product".id AND
  "Product_promotion".id_produit = "Product".id AND
  "Product_promotion".id_promotion = "Local_promotion".id AND
  "Local_promotion".id_regle = "Rule".id;
