CREATE TABLE states (
    id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(250) NOT NULL,
    code VARCHAR(10),
    country_name VARCHAR(50) NOT NULL,
    country_iso3 VARCHAR(3) NOT NULL,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_states PRIMARY KEY (id),
    INDEX idx_state_country_iso (country_iso3),
    INDEX idx_state_code (code)
);