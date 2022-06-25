CREATE TABLE person
(
    Id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    age INTEGER CHECK (age > 0),
    haveCarLicence BOOLEAN
    auto_id SERIAL REFERENCES auto(Id)
);

CREATE TABLE auto
(
    Id SERIAL PRIMARY KEY,
    model_id SERIAL REFERENCES model(Id)
    price NUMERIC(16,7)
);

CREATE TABLE model
(
    Id SERIAL PRIMARY KEY,
    model VARCHAR(50),
    marka_id SERIAL REFERENCES marka(Id)
);

CREATE TABLE marka
(
    Id SERIAL PRIMARY KEY,
    marka VARCHAR(50)
);
