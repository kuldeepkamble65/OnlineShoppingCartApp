#################################################################################################################
-- ### Server version   :       5.7
-- ### Date (DD-MM-YYYY):       03-01-2024
-- ### Developer Name   :       kuldeep kamble
-- ### Comments         :       Add table to create user
-- ####################################################################################################


ALTER TABLE user
    ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;