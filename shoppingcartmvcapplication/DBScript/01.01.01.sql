#################################################################################################################
-- ### Server version   :       5.7
-- ### Date (DD-MM-YYYY):       03-01-2024
-- ### Developer Name   :       kuldeep kamble
-- ### Comments         :       Add table to create user
-- ####################################################################################################

CREATE TABLE User(
                       userId INT AUTO_INCREMENT PRIMARY KEY,
                       firstname VARCHAR(255) NOT NULL,
                       lastname VARCHAR(255) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       mobileNo VARCHAR(15),
                       password VARCHAR(255) NOT NULL
);