/**
  Parents Guide Table
 */
CREATE TABLE parents_guides (
    id BINARY(16) NOT NULL,
    sex_and_nudity VARCHAR(10) NOT NULL,
    violence_and_gore VARCHAR(10) NOT NULL,
    profanity VARCHAR(10) NOT NULL,
    alcohol_drugs_and_smoking VARCHAR(10) NOT NULL,
    frightening_and_intense_scenes VARCHAR(10) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,

    PRIMARY KEY (id)
);

/**
  Alter table movies to include foreign key to table parents guide
 */
ALTER TABLE movies
ADD COLUMN parents_guide_id BINARY(16) AFTER content_rating,
ADD CONSTRAINT fk_movies_parents_guides FOREIGN KEY (parents_guide_id) REFERENCES parents_guides (id);

/*******************************************************
  Insert parents guide data and add references to movies
 *******************************************************/

-- Dr. No
INSERT INTO parents_guides (id, sex_and_nudity, violence_and_gore, profanity, alcohol_drugs_and_smoking, frightening_and_intense_scenes, created_at, updated_at)
VALUES (
        UUID_TO_BIN('c4593b5d-8c3e-4814-a705-91eb81a2b9a3'),
        'mild',
        'mild',
        'mild',
        'mild',
        'mild',
        CURRENT_TIMESTAMP(6),
        CURRENT_TIMESTAMP(6)
);

UPDATE movies
SET movies.parents_guide_id = UUID_TO_BIN('c4593b5d-8c3e-4814-a705-91eb81a2b9a3')
WHERE movies.title = 'Dr. No';

-- From Russia With Love
INSERT INTO parents_guides (id, sex_and_nudity, violence_and_gore, profanity, alcohol_drugs_and_smoking, frightening_and_intense_scenes, created_at, updated_at)
VALUES (
           UUID_TO_BIN('4c38c2c4-8212-4640-b908-d901342c4a95'),
           'mild',
           'moderate',
           'mild',
           'mild',
           'mild',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.parents_guide_id = UUID_TO_BIN('4c38c2c4-8212-4640-b908-d901342c4a95')
WHERE movies.title = 'From Russia With Love';

-- Goldfinger
INSERT INTO parents_guides (id, sex_and_nudity, violence_and_gore, profanity, alcohol_drugs_and_smoking, frightening_and_intense_scenes, created_at, updated_at)
VALUES (
           UUID_TO_BIN('349c07e8-8df5-4b91-a68d-2c0779797e99'),
           'mild',
           'mild',
           'mild',
           'mild',
           'mild',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.parents_guide_id = UUID_TO_BIN('349c07e8-8df5-4b91-a68d-2c0779797e99')
WHERE movies.title = 'Goldfinger';

-- Thunderball
INSERT INTO parents_guides (id, sex_and_nudity, violence_and_gore, profanity, alcohol_drugs_and_smoking, frightening_and_intense_scenes, created_at, updated_at)
VALUES (
           UUID_TO_BIN('1ab9af8e-b4e3-4734-aca4-3507b1b0cd8e'),
           'mild',
           'moderate',
           'mild',
           'mild',
           'mild',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.parents_guide_id = UUID_TO_BIN('1ab9af8e-b4e3-4734-aca4-3507b1b0cd8e')
WHERE movies.title = 'Thunderball';

-- You Only Live Twice
INSERT INTO parents_guides (id, sex_and_nudity, violence_and_gore, profanity, alcohol_drugs_and_smoking, frightening_and_intense_scenes, created_at, updated_at)
VALUES (
           UUID_TO_BIN('50b604c5-b693-4bc8-a1ce-c09b65ae3f2e'),
           'mild',
           'moderate',
           'mild',
           'mild',
           'mild',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.parents_guide_id = UUID_TO_BIN('50b604c5-b693-4bc8-a1ce-c09b65ae3f2e')
WHERE movies.title = 'You Only Live Twice';

-- Diamonds Are Forever
INSERT INTO parents_guides (id, sex_and_nudity, violence_and_gore, profanity, alcohol_drugs_and_smoking, frightening_and_intense_scenes, created_at, updated_at)
VALUES (
           UUID_TO_BIN('8375929e-13ce-4282-9303-970ca1dde00b'),
           'mild',
           'moderate',
           'mild',
           'mild',
           'mild',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.parents_guide_id = UUID_TO_BIN('8375929e-13ce-4282-9303-970ca1dde00b')
WHERE movies.title = 'Diamonds Are Forever';

-- On Her Majesty's Secret Service
INSERT INTO parents_guides (id, sex_and_nudity, violence_and_gore, profanity, alcohol_drugs_and_smoking, frightening_and_intense_scenes, created_at, updated_at)
VALUES (
           UUID_TO_BIN('8ce1ffba-36a5-4267-9cf2-e983dbd5fc99'),
           'mild',
           'moderate',
           'mild',
           'mild',
           'mild',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.parents_guide_id = UUID_TO_BIN('8ce1ffba-36a5-4267-9cf2-e983dbd5fc99')
WHERE movies.title = 'On Her Majesty''s Secret Service';

-- Live And Let Die
INSERT INTO parents_guides (id, sex_and_nudity, violence_and_gore, profanity, alcohol_drugs_and_smoking, frightening_and_intense_scenes, created_at, updated_at)
VALUES (
           UUID_TO_BIN('b7354f54-ea55-43b2-a3da-0b82bcc07dc2'),
           'mild',
           'moderate',
           'mild',
           'mild',
           'mild',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.parents_guide_id = UUID_TO_BIN('b7354f54-ea55-43b2-a3da-0b82bcc07dc2')
WHERE movies.title = 'Live And Let Die';

-- The Man With The Golden Gun
INSERT INTO parents_guides (id, sex_and_nudity, violence_and_gore, profanity, alcohol_drugs_and_smoking, frightening_and_intense_scenes, created_at, updated_at)
VALUES (
           UUID_TO_BIN('e86a3280-eb64-456e-8b8a-bde918498fea'),
           'mild',
           'moderate',
           'mild',
           'mild',
           'mild',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.parents_guide_id = UUID_TO_BIN('e86a3280-eb64-456e-8b8a-bde918498fea')
WHERE movies.title = 'The Man With The Golden Gun';

-- The Spy Who Loved Me
INSERT INTO parents_guides (id, sex_and_nudity, violence_and_gore, profanity, alcohol_drugs_and_smoking, frightening_and_intense_scenes, created_at, updated_at)
VALUES (
           UUID_TO_BIN('12b621d2-d613-458d-9eb2-a90582904576'),
           'mild',
           'moderate',
           'mild',
           'mild',
           'mild',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.parents_guide_id = UUID_TO_BIN('12b621d2-d613-458d-9eb2-a90582904576')
WHERE movies.title = 'The Spy Who Loved Me';

-- Moonraker
INSERT INTO parents_guides (id, sex_and_nudity, violence_and_gore, profanity, alcohol_drugs_and_smoking, frightening_and_intense_scenes, created_at, updated_at)
VALUES (
           UUID_TO_BIN('f02e6a10-339a-4e92-a7e3-1fef6c3b12a2'),
           'mild',
           'mild',
           'mild',
           'mild',
           'mild',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.parents_guide_id = UUID_TO_BIN('f02e6a10-339a-4e92-a7e3-1fef6c3b12a2')
WHERE movies.title = 'Moonraker';

-- For Your Eyes Only
INSERT INTO parents_guides (id, sex_and_nudity, violence_and_gore, profanity, alcohol_drugs_and_smoking, frightening_and_intense_scenes, created_at, updated_at)
VALUES (
           UUID_TO_BIN('912e02b8-7ca4-439d-9f12-849be11eee32'),
           'mild',
           'moderate',
           'none',
           'mild',
           'mild',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.parents_guide_id = UUID_TO_BIN('912e02b8-7ca4-439d-9f12-849be11eee32')
WHERE movies.title = 'For Your Eyes Only';

-- Octopussy
INSERT INTO parents_guides (id, sex_and_nudity, violence_and_gore, profanity, alcohol_drugs_and_smoking, frightening_and_intense_scenes, created_at, updated_at)
VALUES (
           UUID_TO_BIN('ee0e0477-926a-4382-85ce-f8be2816c73a'),
           'mild',
           'moderate',
           'mild',
           'mild',
           'mild',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.parents_guide_id = UUID_TO_BIN('ee0e0477-926a-4382-85ce-f8be2816c73a')
WHERE movies.title = 'Octopussy';

-- A View To A Kill
INSERT INTO parents_guides (id, sex_and_nudity, violence_and_gore, profanity, alcohol_drugs_and_smoking, frightening_and_intense_scenes, created_at, updated_at)
VALUES (
           UUID_TO_BIN('6f2e101d-8030-466c-9a31-fc0a8b386b7c'),
           'mild',
           'moderate',
           'mild',
           'mild',
           'moderate',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.parents_guide_id = UUID_TO_BIN('6f2e101d-8030-466c-9a31-fc0a8b386b7c')
WHERE movies.title = 'A View To A Kill';

-- The Living Daylights
INSERT INTO parents_guides (id, sex_and_nudity, violence_and_gore, profanity, alcohol_drugs_and_smoking, frightening_and_intense_scenes, created_at, updated_at)
VALUES (
           UUID_TO_BIN('a42c4c3d-69e0-449f-ae4b-ae0aeabdfac7'),
           'mild',
           'moderate',
           'mild',
           'mild',
           'mild',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.parents_guide_id = UUID_TO_BIN('a42c4c3d-69e0-449f-ae4b-ae0aeabdfac7')
WHERE movies.title = 'The Living Daylights';

-- License To Kill
INSERT INTO parents_guides (id, sex_and_nudity, violence_and_gore, profanity, alcohol_drugs_and_smoking, frightening_and_intense_scenes, created_at, updated_at)
VALUES (
           UUID_TO_BIN('8748ee13-327c-430c-bc16-4c99f0b33ea7'),
           'mild',
           'severe',
           'mild',
           'moderate',
           'moderate',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.parents_guide_id = UUID_TO_BIN('8748ee13-327c-430c-bc16-4c99f0b33ea7')
WHERE movies.title = 'License To Kill';

-- Goldeneye
INSERT INTO parents_guides (id, sex_and_nudity, violence_and_gore, profanity, alcohol_drugs_and_smoking, frightening_and_intense_scenes, created_at, updated_at)
VALUES (
           UUID_TO_BIN('d59aac3c-76b9-43fc-949e-385ec033fef4'),
           'moderate',
           'moderate',
           'mild',
           'mild',
           'moderate',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.parents_guide_id = UUID_TO_BIN('d59aac3c-76b9-43fc-949e-385ec033fef4')
WHERE movies.title = 'Goldeneye';

-- Tomorrow Never Dies
INSERT INTO parents_guides (id, sex_and_nudity, violence_and_gore, profanity, alcohol_drugs_and_smoking, frightening_and_intense_scenes, created_at, updated_at)
VALUES (
           UUID_TO_BIN('38d9becb-e7ba-4fae-b9c2-55aca5c18462'),
           'mild',
           'moderate',
           'mild',
           'mild',
           'moderate',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.parents_guide_id = UUID_TO_BIN('38d9becb-e7ba-4fae-b9c2-55aca5c18462')
WHERE movies.title = 'Tomorrow Never Dies';

-- The World Is Not Enough
INSERT INTO parents_guides (id, sex_and_nudity, violence_and_gore, profanity, alcohol_drugs_and_smoking, frightening_and_intense_scenes, created_at, updated_at)
VALUES (
           UUID_TO_BIN('05941333-24f6-4779-8326-7cffa96c8a1a'),
           'moderate',
           'moderate',
           'mild',
           'mild',
           'moderate',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.parents_guide_id = UUID_TO_BIN('05941333-24f6-4779-8326-7cffa96c8a1a')
WHERE movies.title = 'The World Is Not Enough';

-- Die Another Day
INSERT INTO parents_guides (id, sex_and_nudity, violence_and_gore, profanity, alcohol_drugs_and_smoking, frightening_and_intense_scenes, created_at, updated_at)
VALUES (
           UUID_TO_BIN('f243f45b-9c70-4bac-8d57-908ba02a7985'),
           'mild',
           'moderate',
           'mild',
           'mild',
           'moderate',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.parents_guide_id = UUID_TO_BIN('f243f45b-9c70-4bac-8d57-908ba02a7985')
WHERE movies.title = 'Die Another Day';

-- Casino Royale
INSERT INTO parents_guides (id, sex_and_nudity, violence_and_gore, profanity, alcohol_drugs_and_smoking, frightening_and_intense_scenes, created_at, updated_at)
VALUES (
           UUID_TO_BIN('c8c22fa2-4180-44d9-ba1a-d1921a65da1f'),
           'moderate',
           'moderate',
           'mild',
           'mild',
           'moderate',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.parents_guide_id = UUID_TO_BIN('c8c22fa2-4180-44d9-ba1a-d1921a65da1f')
WHERE movies.title = 'Casino Royale';

-- Quantum Of Solace
INSERT INTO parents_guides (id, sex_and_nudity, violence_and_gore, profanity, alcohol_drugs_and_smoking, frightening_and_intense_scenes, created_at, updated_at)
VALUES (
           UUID_TO_BIN('7e47e419-5145-4f33-a81c-0dde5fc83664'),
           'mild',
           'moderate',
           'mild',
           'mild',
           'moderate',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.parents_guide_id = UUID_TO_BIN('7e47e419-5145-4f33-a81c-0dde5fc83664')
WHERE movies.title = 'Quantum Of Solace';

-- Skyfall
INSERT INTO parents_guides (id, sex_and_nudity, violence_and_gore, profanity, alcohol_drugs_and_smoking, frightening_and_intense_scenes, created_at, updated_at)
VALUES (
           UUID_TO_BIN('d8ea803f-91a1-4129-9112-f954ce299112'),
           'mild',
           'moderate',
           'mild',
           'mild',
           'severe',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.parents_guide_id = UUID_TO_BIN('d8ea803f-91a1-4129-9112-f954ce299112')
WHERE movies.title = 'Skyfall';

-- Spectre
INSERT INTO parents_guides (id, sex_and_nudity, violence_and_gore, profanity, alcohol_drugs_and_smoking, frightening_and_intense_scenes, created_at, updated_at)
VALUES (
           UUID_TO_BIN('3b662231-73f6-4d27-abc9-708204762421'),
           'mild',
           'moderate',
           'mild',
           'mild',
           'moderate',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.parents_guide_id = UUID_TO_BIN('3b662231-73f6-4d27-abc9-708204762421')
WHERE movies.title = 'Spectre';

-- No Time To Die
INSERT INTO parents_guides (id, sex_and_nudity, violence_and_gore, profanity, alcohol_drugs_and_smoking, frightening_and_intense_scenes, created_at, updated_at)
VALUES (
           UUID_TO_BIN('506b8a6a-e8ea-480e-8bf2-b5ee78ff03ef'),
           'mild',
           'moderate',
           'mild',
           'mild',
           'moderate',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.parents_guide_id = UUID_TO_BIN('506b8a6a-e8ea-480e-8bf2-b5ee78ff03ef')
WHERE movies.title = 'No Time To Die';
