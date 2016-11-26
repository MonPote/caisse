DROP TABLE IF EXISTS "public"."product" CASCADE;
DROP TABLE IF EXISTS "public"."store" CASCADE;
DROP TABLE IF EXISTS "public"."category" CASCADE;
DROP TABLE IF EXISTS "public"."brand" CASCADE;
DROP TABLE IF EXISTS "public"."localization" CASCADE;

CREATE TABLE public.Store
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    adress TEXT NOT NULL,
    zipcode VARCHAR(16) NOT NULL
);

CREATE TABLE public.Localization
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL
);

CREATE TABLE public.Category
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL
);

CREATE TABLE public.Brand
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(64)
);

CREATE TABLE public.Product
(
    id SERIAL PRIMARY KEY,
    reference VARCHAR(32) NOT NULL,
    designation VARCHAR(64),
    quantity INT DEFAULT 0 NOT NULL,
    brand_id INT NOT NULL,
    category_id INT NOT NULL,
    localization_id INT NOT NULL,
    store_id INT NOT NULL,
    CONSTRAINT Product_brand_id_fk FOREIGN KEY (brand_id) REFERENCES brand (id),
    CONSTRAINT Product_category_id_fk FOREIGN KEY (category_id) REFERENCES category (id),
    CONSTRAINT Product_localization_id_fk FOREIGN KEY (localization_id) REFERENCES localization (id),
    CONSTRAINT Product_store_id_fk FOREIGN KEY (store_id) REFERENCES store (id)
);

CREATE UNIQUE INDEX "Product_reference_uindex" ON public.Product (reference);

INSERT INTO public.Brand (name) VALUES ('Lacoste'), ('Asus');

INSERT INTO public.Category (name) VALUES ('Pull'), ('PC');

INSERT INTO public.Localization (name) VALUES ('7G'), ('8B');

INSERT INTO public.Store (name, adress, zipcode) VALUES ('Epita', '66, rue guy mocquet', '94800'),
  ('NeaStore', 'God Palace', '00000');

INSERT INTO public.Product (reference, designation, quantity, brand_id, category_id, localization_id, store_id)
  VALUES ('PULL001', 'Polo vert', 10, 1, 1, 1, 1), ('ROG1', 'GW501JW', 5, 2, 2, 1, 1);

