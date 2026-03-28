CREATE TABLE category (
                          id   BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL
);

CREATE TABLE product (
                         id          BIGSERIAL PRIMARY KEY,
                         code        VARCHAR(100) NOT NULL UNIQUE,
                         name        VARCHAR(255) NOT NULL,
                         id_category BIGINT NOT NULL,

                         CONSTRAINT fk_product_category
                             FOREIGN KEY (id_category)
                                 REFERENCES category (id)
                                 ON DELETE RESTRICT
                                 ON UPDATE CASCADE
);

CREATE INDEX idx_product_id_category ON product (id_category);