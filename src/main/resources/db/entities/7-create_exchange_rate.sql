CREATE TABLE exchange_rates (
    id BIGINT AUTO_INCREMENT NOT NULL,
    source_currency_code VARCHAR(5) NOT NULL,
    destination_currency_code VARCHAR(5) NOT NULL,
    rate  DECIMAL NOT NULL DEFAULT 1,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_exchange_rates PRIMARY KEY (id),
    INDEX idx_exchange_rates_source_currency_code (source_currency_code),
    INDEX idx_exchange_rates_destination_currency_code (destination_currency_code)
);