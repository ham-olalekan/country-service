CREATE TABLE cities (
    id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(50) NOT NULL,
    country_name VARCHAR(50) NOT NULL,
    country_id BIGINT NOT NULL,
    population BIGINT DEFAULT 0,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_cities PRIMARY KEY (id),
    CONSTRAINT fk_CITY_COUNTRY_ID FOREIGN KEY (country_id) REFERENCES countries(id),
    INDEX idx_cities_country_id (country_id)
);