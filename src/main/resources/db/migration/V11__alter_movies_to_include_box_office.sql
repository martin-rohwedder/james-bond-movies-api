/**
  Box Office Table
 */
CREATE TABLE box_office (
    id BINARY(16) NOT NULL,
    budget_usd BIGINT NOT NULL,
    gross_revenue_us_and_canada_usd BIGINT NOT NULL,
    gross_revenue_worldwide_usd BIGINT NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,

    PRIMARY KEY (id)
);

/**
  Alter table movies to include foreign key to table box_office
 */
ALTER TABLE movies
ADD COLUMN box_office_id BINARY(16) AFTER locations,
ADD CONSTRAINT fk_movies_box_office FOREIGN KEY (box_office_id) REFERENCES box_office (id);

/****************************************************
  Insert box office data and add references to movies
 ****************************************************/

-- Dr. No
INSERT INTO box_office (id, budget_usd, gross_revenue_us_and_canada_usd, gross_revenue_worldwide_usd, created_at, updated_at)
VALUES (
        UUID_TO_BIN('ef4f07ce-2cae-4291-92e1-ca49e26473fa'),
        1100000,
        16067035,
        16135890,
        CURRENT_TIMESTAMP(6),
        CURRENT_TIMESTAMP(6)
);

UPDATE movies
SET movies.box_office_id = UUID_TO_BIN('ef4f07ce-2cae-4291-92e1-ca49e26473fa')
WHERE movies.id = UUID_TO_BIN('18fda8f7-10a4-4ad4-84a9-4573dd8704d4');

-- From Russia With Love
INSERT INTO box_office (id, budget_usd, gross_revenue_us_and_canada_usd, gross_revenue_worldwide_usd, created_at, updated_at)
VALUES (
           UUID_TO_BIN('4bc07127-c335-4b58-84b8-6431fe1e0338'),
           2000000,
           24796765,
           24812843,
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.box_office_id = UUID_TO_BIN('4bc07127-c335-4b58-84b8-6431fe1e0338')
WHERE movies.id = UUID_TO_BIN('8316df48-88e6-43e3-aaa7-1c0d1ff93f82');

-- Goldfinger
INSERT INTO box_office (id, budget_usd, gross_revenue_us_and_canada_usd, gross_revenue_worldwide_usd, created_at, updated_at)
VALUES (
           UUID_TO_BIN('62eedfd1-735a-43aa-9e38-4480673d7ca2'),
           3000000,
           51081062,
           51222709,
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.box_office_id = UUID_TO_BIN('62eedfd1-735a-43aa-9e38-4480673d7ca2')
WHERE movies.id = UUID_TO_BIN('2dc921ab-7bb2-4fc1-a3f9-ea22bc73094e');

-- Thunderball
INSERT INTO box_office (id, budget_usd, gross_revenue_us_and_canada_usd, gross_revenue_worldwide_usd, created_at, updated_at)
VALUES (
           UUID_TO_BIN('66716fa1-3a9b-4879-bb37-07e794274046'),
           9000000,
           63595658,
           63636365,
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.box_office_id = UUID_TO_BIN('66716fa1-3a9b-4879-bb37-07e794274046')
WHERE movies.id = UUID_TO_BIN('5a914395-9ddc-451e-8231-f14cc3490d94');

-- You Only Live Twice
INSERT INTO box_office (id, budget_usd, gross_revenue_us_and_canada_usd, gross_revenue_worldwide_usd, created_at, updated_at)
VALUES (
           UUID_TO_BIN('ba9abd98-1a41-4fd2-ac41-e740b6d3c1db'),
           9500000,
           43084787,
           43117650,
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.box_office_id = UUID_TO_BIN('ba9abd98-1a41-4fd2-ac41-e740b6d3c1db')
WHERE movies.id = UUID_TO_BIN('b545a06f-025d-4dc6-b454-bc13782630ab');

-- On Her Majesty's Secret Service
INSERT INTO box_office (id, budget_usd, gross_revenue_us_and_canada_usd, gross_revenue_worldwide_usd, created_at, updated_at)
VALUES (
           UUID_TO_BIN('cdc01d79-abb2-4c40-8bb4-2f89ce2e089a'),
           7000000,
           22774493,
           22829982,
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.box_office_id = UUID_TO_BIN('cdc01d79-abb2-4c40-8bb4-2f89ce2e089a')
WHERE movies.id = UUID_TO_BIN('4a3515f9-577b-412c-8123-53f232e239ee');

-- Diamonds Are Forever
INSERT INTO box_office (id, budget_usd, gross_revenue_us_and_canada_usd, gross_revenue_worldwide_usd, created_at, updated_at)
VALUES (
           UUID_TO_BIN('a2872469-6627-4f8c-aa32-504977f7f737'),
           7200000,
           43819547,
           43831448,
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.box_office_id = UUID_TO_BIN('a2872469-6627-4f8c-aa32-504977f7f737')
WHERE movies.id = UUID_TO_BIN('9c415115-9be6-4f4f-8495-6f26b4fc60cb');

-- Live And Let Die
INSERT INTO box_office (id, budget_usd, gross_revenue_us_and_canada_usd, gross_revenue_worldwide_usd, created_at, updated_at)
VALUES (
           UUID_TO_BIN('e6b72728-4e8d-4fec-b62f-76a148f0ab75'),
           7000000,
           35377836,
           35385507,
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.box_office_id = UUID_TO_BIN('e6b72728-4e8d-4fec-b62f-76a148f0ab75')
WHERE movies.id = UUID_TO_BIN('bcbf8703-14fe-4999-a2cd-2336b91ca7a5');

-- The Man With The Golden Gun
INSERT INTO box_office (id, budget_usd, gross_revenue_us_and_canada_usd, gross_revenue_worldwide_usd, created_at, updated_at)
VALUES (
           UUID_TO_BIN('60b1d4f8-5031-4181-a484-866f41d1a0ee'),
           7000000,
           20972000,
           20974165,
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.box_office_id = UUID_TO_BIN('60b1d4f8-5031-4181-a484-866f41d1a0ee')
WHERE movies.id = UUID_TO_BIN('3a6b6e28-5e31-4931-8c6f-8a5694877e54');

-- The Spy Who Loved Me
INSERT INTO box_office (id, budget_usd, gross_revenue_us_and_canada_usd, gross_revenue_worldwide_usd, created_at, updated_at)
VALUES (
           UUID_TO_BIN('14b1a5a5-62d1-413a-9cf3-02e8dccb0d80'),
           13500000,
           43838673,
           46877861,
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.box_office_id = UUID_TO_BIN('14b1a5a5-62d1-413a-9cf3-02e8dccb0d80')
WHERE movies.id = UUID_TO_BIN('7774936e-04e5-4609-aa1d-a7b26d782947');

-- Moonraker
INSERT INTO box_office (id, budget_usd, gross_revenue_us_and_canada_usd, gross_revenue_worldwide_usd, created_at, updated_at)
VALUES (
           UUID_TO_BIN('fd4962ee-def0-41b3-8c1d-812e34aac153'),
           34000000,
           70308099,
           210308099,
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.box_office_id = UUID_TO_BIN('fd4962ee-def0-41b3-8c1d-812e34aac153')
WHERE movies.id = UUID_TO_BIN('3f511a7c-ee4c-4560-a621-2bd6b1b9faa8');

-- For Your Eyes Only
INSERT INTO box_office (id, budget_usd, gross_revenue_us_and_canada_usd, gross_revenue_worldwide_usd, created_at, updated_at)
VALUES (
           UUID_TO_BIN('6bc2c06d-3ef2-446a-9232-debcac8a9812'),
           28000000,
           54812802,
           54839098,
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.box_office_id = UUID_TO_BIN('6bc2c06d-3ef2-446a-9232-debcac8a9812')
WHERE movies.id = UUID_TO_BIN('3eb1763c-ad84-401e-b877-aa687acf7549');

-- Octopussy
INSERT INTO box_office (id, budget_usd, gross_revenue_us_and_canada_usd, gross_revenue_worldwide_usd, created_at, updated_at)
VALUES (
           UUID_TO_BIN('9c6f80f8-1de0-4bb2-a3f4-9439b914bf68'),
           27500000,
           67893619,
           67918457,
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.box_office_id = UUID_TO_BIN('9c6f80f8-1de0-4bb2-a3f4-9439b914bf68')
WHERE movies.id = UUID_TO_BIN('2375219d-1fc8-41f1-b47f-d8ee6b0edd6e');

-- A View To A Kill
INSERT INTO box_office (id, budget_usd, gross_revenue_us_and_canada_usd, gross_revenue_worldwide_usd, created_at, updated_at)
VALUES (
           UUID_TO_BIN('befdb6c1-5e96-4969-8687-6aa590b064d1'),
           30000000,
           50327960,
           50338851,
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.box_office_id = UUID_TO_BIN('befdb6c1-5e96-4969-8687-6aa590b064d1')
WHERE movies.id = UUID_TO_BIN('c34f1b7b-3010-4155-8c40-bb4efb20dfb2');

-- The Living Daylights
INSERT INTO box_office (id, budget_usd, gross_revenue_us_and_canada_usd, gross_revenue_worldwide_usd, created_at, updated_at)
VALUES (
           UUID_TO_BIN('181d3bdd-0bbd-490d-b307-7e217fa478a8'),
           40000000,
           51185897,
           51220890,
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.box_office_id = UUID_TO_BIN('181d3bdd-0bbd-490d-b307-7e217fa478a8')
WHERE movies.id = UUID_TO_BIN('dc7940f8-ad41-42b8-bf5d-da25e99752e4');

-- License To Kill
INSERT INTO box_office (id, budget_usd, gross_revenue_us_and_canada_usd, gross_revenue_worldwide_usd, created_at, updated_at)
VALUES (
           UUID_TO_BIN('51195944-1b45-496b-8bad-6996986d727b'),
           32000000,
           34667015,
           156167015,
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.box_office_id = UUID_TO_BIN('51195944-1b45-496b-8bad-6996986d727b')
WHERE movies.id = UUID_TO_BIN('179f3409-d39d-437d-b998-79f150ee1b30');

-- Goldeneye
INSERT INTO box_office (id, budget_usd, gross_revenue_us_and_canada_usd, gross_revenue_worldwide_usd, created_at, updated_at)
VALUES (
           UUID_TO_BIN('f4131e33-608d-4ff2-9341-8e97b9ff9d14'),
           60000000,
           106429941,
           352194034,
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.box_office_id = UUID_TO_BIN('f4131e33-608d-4ff2-9341-8e97b9ff9d14')
WHERE movies.id = UUID_TO_BIN('06d701ab-f89f-4b57-9c6e-f43628cc4e6d');

-- Tomorrow Never Dies
INSERT INTO box_office (id, budget_usd, gross_revenue_us_and_canada_usd, gross_revenue_worldwide_usd, created_at, updated_at)
VALUES (
           UUID_TO_BIN('7b9f5ac0-94cf-4402-8b6f-e6505871e96d'),
           110000000,
           125304276,
           333011068,
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.box_office_id = UUID_TO_BIN('7b9f5ac0-94cf-4402-8b6f-e6505871e96d')
WHERE movies.id = UUID_TO_BIN('28b1807d-93cc-4078-ad3a-44f00375d3dd');

-- The World Is Not Enough
INSERT INTO box_office (id, budget_usd, gross_revenue_us_and_canada_usd, gross_revenue_worldwide_usd, created_at, updated_at)
VALUES (
           UUID_TO_BIN('b373da5d-fe8a-4495-969d-e69103eb96e2'),
           135000000,
           126943684,
           361832400,
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.box_office_id = UUID_TO_BIN('b373da5d-fe8a-4495-969d-e69103eb96e2')
WHERE movies.id = UUID_TO_BIN('8ddc2a5a-c855-4768-9b70-3a3d383e621b');

-- Die Another Day
INSERT INTO box_office (id, budget_usd, gross_revenue_us_and_canada_usd, gross_revenue_worldwide_usd, created_at, updated_at)
VALUES (
           UUID_TO_BIN('c25d35e4-ef7f-4a6e-ad76-ea7efefe43c9'),
           142000000,
           160942139,
           431971781,
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.box_office_id = UUID_TO_BIN('c25d35e4-ef7f-4a6e-ad76-ea7efefe43c9')
WHERE movies.id = UUID_TO_BIN('f555fa79-8c7a-46df-b178-649ae651065f');

-- Casino Royale
INSERT INTO box_office (id, budget_usd, gross_revenue_us_and_canada_usd, gross_revenue_worldwide_usd, created_at, updated_at)
VALUES (
           UUID_TO_BIN('078996ed-597d-4e78-b0c6-2781d8c3467f'),
           150000000,
           167445960,
           606278957,
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.box_office_id = UUID_TO_BIN('078996ed-597d-4e78-b0c6-2781d8c3467f')
WHERE movies.id = UUID_TO_BIN('8218a578-0622-4d75-a86c-1501a3e48afc');

-- Quantum Of Solace
INSERT INTO box_office (id, budget_usd, gross_revenue_us_and_canada_usd, gross_revenue_worldwide_usd, created_at, updated_at)
VALUES (
           UUID_TO_BIN('529f18c6-8063-4036-8fe2-b3326a1d71df'),
           200000000,
           168368427,
           589593933,
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.box_office_id = UUID_TO_BIN('529f18c6-8063-4036-8fe2-b3326a1d71df')
WHERE movies.id = UUID_TO_BIN('32b40321-da45-4ff3-b417-2b2d79c10de9');

-- Skyfall
INSERT INTO box_office (id, budget_usd, gross_revenue_us_and_canada_usd, gross_revenue_worldwide_usd, created_at, updated_at)
VALUES (
           UUID_TO_BIN('f02f0a9c-27ab-4cbb-9dd4-283b1f96f8e8'),
           200000000,
           304360277,
           1108594137,
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.box_office_id = UUID_TO_BIN('f02f0a9c-27ab-4cbb-9dd4-283b1f96f8e8')
WHERE movies.id = UUID_TO_BIN('2a85118c-7fd2-4096-9668-01fd8320ab17');

-- Spectre
INSERT INTO box_office (id, budget_usd, gross_revenue_us_and_canada_usd, gross_revenue_worldwide_usd, created_at, updated_at)
VALUES (
           UUID_TO_BIN('fa57cb26-ebe5-4e79-8368-1e2ac4e495d7'),
           245000000,
           200074609,
           880707597,
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.box_office_id = UUID_TO_BIN('fa57cb26-ebe5-4e79-8368-1e2ac4e495d7')
WHERE movies.id = UUID_TO_BIN('0d7d18ba-04a5-4f64-a720-8cbf39692862');

-- No Time To Die
INSERT INTO box_office (id, budget_usd, gross_revenue_us_and_canada_usd, gross_revenue_worldwide_usd, created_at, updated_at)
VALUES (
           UUID_TO_BIN('670e1f20-afa3-46a4-ab8a-b5f4842dcc04'),
           250000000,
           160891007,
           774153007,
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.box_office_id = UUID_TO_BIN('670e1f20-afa3-46a4-ab8a-b5f4842dcc04')
WHERE movies.id = UUID_TO_BIN('f1930593-6cae-45d0-a063-84582ac10fac');
