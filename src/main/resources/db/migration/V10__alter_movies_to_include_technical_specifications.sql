/**
  Technical Specifications Table
 */
CREATE TABLE technical_specifications (
    id BINARY(16) NOT NULL,
    runtime_in_minutes INT NOT NULL,
    sound_mix VARCHAR(255) NOT NULL,
    aspect_ratio VARCHAR(255) NOT NULL,
    printed_film_format VARCHAR(255) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,

    PRIMARY KEY (id)
);

/**
  Alter table movies to include foreign key to table technical_specifications
 */
ALTER TABLE movies
ADD COLUMN technical_specifications_id BINARY(16) AFTER locations,
ADD CONSTRAINT fk_movies_technical_specifications FOREIGN KEY (technical_specifications_id) REFERENCES technical_specifications (id);

/******************************************************************
  Insert technical specifications data and add references to movies
 ******************************************************************/

-- Dr. No
INSERT INTO technical_specifications (id, runtime_in_minutes, sound_mix, aspect_ratio, printed_film_format, created_at, updated_at)
VALUES (
        UUID_TO_BIN('da18ae6f-419d-4f08-82af-4e171d1a5b15'),
        110,
        'Mono, DTS (4.1 Surround)',
        '1.37:1, 1.66:1, 1.75:1, 1.85:1',
        '35 mm',
        CURRENT_TIMESTAMP(6),
        CURRENT_TIMESTAMP(6)
);

UPDATE movies
SET movies.technical_specifications_id = UUID_TO_BIN('da18ae6f-419d-4f08-82af-4e171d1a5b15')
WHERE movies.id = UUID_TO_BIN('18fda8f7-10a4-4ad4-84a9-4573dd8704d4');

-- From Russia With Love
INSERT INTO technical_specifications (id, runtime_in_minutes, sound_mix, aspect_ratio, printed_film_format, created_at, updated_at)
VALUES (
           UUID_TO_BIN('596153e0-1baf-4b65-ab53-d6d675f4014b'),
           115,
           'Mono, Dolby Digital (5.1 Surround)',
           '1.37:1, 1.75:1',
           '35 mm',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.technical_specifications_id = UUID_TO_BIN('596153e0-1baf-4b65-ab53-d6d675f4014b')
WHERE movies.id = UUID_TO_BIN('8316df48-88e6-43e3-aaa7-1c0d1ff93f82');

-- Goldfinger
INSERT INTO technical_specifications (id, runtime_in_minutes, sound_mix, aspect_ratio, printed_film_format, created_at, updated_at)
VALUES (
           UUID_TO_BIN('19732b03-fb18-4897-baa5-de99e45c9508'),
           110,
           'Mono',
           '1.37:1, 1.66:1, 1.75:1, 1.85:1',
           '35 mm',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.technical_specifications_id = UUID_TO_BIN('19732b03-fb18-4897-baa5-de99e45c9508')
WHERE movies.id = UUID_TO_BIN('2dc921ab-7bb2-4fc1-a3f9-ea22bc73094e');

-- Thunderball
INSERT INTO technical_specifications (id, runtime_in_minutes, sound_mix, aspect_ratio, printed_film_format, created_at, updated_at)
VALUES (
           UUID_TO_BIN('2da454b6-c6da-4e13-91a3-2d5287f98d3e'),
           130,
           'Mono, 6-Track Stereo',
           '2.35:1',
           '35 mm',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.technical_specifications_id = UUID_TO_BIN('2da454b6-c6da-4e13-91a3-2d5287f98d3e')
WHERE movies.id = UUID_TO_BIN('5a914395-9ddc-451e-8231-f14cc3490d94');

-- You Only Live Twice
INSERT INTO technical_specifications (id, runtime_in_minutes, sound_mix, aspect_ratio, printed_film_format, created_at, updated_at)
VALUES (
           UUID_TO_BIN('90f5ec6b-20ec-469f-931d-abae22deba9d'),
           117,
           'Mono, 4-Track Stereo',
           '2.35:1',
           '35 mm',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.technical_specifications_id = UUID_TO_BIN('90f5ec6b-20ec-469f-931d-abae22deba9d')
WHERE movies.id = UUID_TO_BIN('b545a06f-025d-4dc6-b454-bc13782630ab');

-- On Her Majesty's Secret Service
INSERT INTO technical_specifications (id, runtime_in_minutes, sound_mix, aspect_ratio, printed_film_format, created_at, updated_at)
VALUES (
           UUID_TO_BIN('5e7bf8ee-d9cc-494c-a36d-c9707f9d973c'),
           142,
           '4-Track Stereo',
           '2.35:1',
           '35 mm',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.technical_specifications_id = UUID_TO_BIN('5e7bf8ee-d9cc-494c-a36d-c9707f9d973c')
WHERE movies.id = UUID_TO_BIN('4a3515f9-577b-412c-8123-53f232e239ee');

-- Diamonds Are Forever
INSERT INTO technical_specifications (id, runtime_in_minutes, sound_mix, aspect_ratio, printed_film_format, created_at, updated_at)
VALUES (
           UUID_TO_BIN('d2ec181c-caee-4c82-be21-b533c64e20a4'),
           120,
           'Mono, Dolby Surround 7.1',
           '2.39:1',
           '35 mm',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.technical_specifications_id = UUID_TO_BIN('d2ec181c-caee-4c82-be21-b533c64e20a4')
WHERE movies.id = UUID_TO_BIN('9c415115-9be6-4f4f-8495-6f26b4fc60cb');

-- Live And Let Die
INSERT INTO technical_specifications (id, runtime_in_minutes, sound_mix, aspect_ratio, printed_film_format, created_at, updated_at)
VALUES (
           UUID_TO_BIN('456b82ab-2420-40ea-ab16-074fa6a322ea'),
           121,
           'Mono, 6-Track Stereo',
           '1.85:1',
           '35 mm',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.technical_specifications_id = UUID_TO_BIN('456b82ab-2420-40ea-ab16-074fa6a322ea')
WHERE movies.id = UUID_TO_BIN('bcbf8703-14fe-4999-a2cd-2336b91ca7a5');

-- The Man With The Golden Gun
INSERT INTO technical_specifications (id, runtime_in_minutes, sound_mix, aspect_ratio, printed_film_format, created_at, updated_at)
VALUES (
           UUID_TO_BIN('c9b2d846-694f-41d4-8905-24e9e92e008d'),
           125,
           'Mono, 3 Channel Stereo, Dolby Surround 7.1, Dolby Atmos',
           '1.66:1, 1.85:1',
           '35 mm',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.technical_specifications_id = UUID_TO_BIN('c9b2d846-694f-41d4-8905-24e9e92e008d')
WHERE movies.id = UUID_TO_BIN('3a6b6e28-5e31-4931-8c6f-8a5694877e54');

-- The Spy Who Loved Me
INSERT INTO technical_specifications (id, runtime_in_minutes, sound_mix, aspect_ratio, printed_film_format, created_at, updated_at)
VALUES (
           UUID_TO_BIN('d21bcbc2-5773-45b4-99d9-2c25af2194e1'),
           125,
           'Mono, 4-Track Stereo',
           '2.39:1',
           '35 mm',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.technical_specifications_id = UUID_TO_BIN('d21bcbc2-5773-45b4-99d9-2c25af2194e1')
WHERE movies.id = UUID_TO_BIN('7774936e-04e5-4609-aa1d-a7b26d782947');

-- Moonraker
INSERT INTO technical_specifications (id, runtime_in_minutes, sound_mix, aspect_ratio, printed_film_format, created_at, updated_at)
VALUES (
           UUID_TO_BIN('100c276e-01f8-4ffc-9c69-123654d6a588'),
           126,
           'Dolby Stereo, 70 mm 6-Track',
           '2.20:1, 2.39:1',
           '35 mm, 70 mm',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.technical_specifications_id = UUID_TO_BIN('100c276e-01f8-4ffc-9c69-123654d6a588')
WHERE movies.id = UUID_TO_BIN('3f511a7c-ee4c-4560-a621-2bd6b1b9faa8');

-- For Your Eyes Only
INSERT INTO technical_specifications (id, runtime_in_minutes, sound_mix, aspect_ratio, printed_film_format, created_at, updated_at)
VALUES (
           UUID_TO_BIN('afc93f03-0e86-4438-b28c-218cef7b998f'),
           127,
           'Dolby Stereo, DTS (3.1 Surround)',
           '2.39:1',
           '35 mm',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.technical_specifications_id = UUID_TO_BIN('afc93f03-0e86-4438-b28c-218cef7b998f')
WHERE movies.id = UUID_TO_BIN('3eb1763c-ad84-401e-b877-aa687acf7549');

-- Octopussy
INSERT INTO technical_specifications (id, runtime_in_minutes, sound_mix, aspect_ratio, printed_film_format, created_at, updated_at)
VALUES (
           UUID_TO_BIN('0f6a18a2-2ce5-4abd-8ca2-80b1cddafb63'),
           131,
           '70 mm 6-Track, Dolby Stereo, Dolby Surround 7.1',
           '2.20:1, 2.39:1',
           '35 mm, 70 mm',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.technical_specifications_id = UUID_TO_BIN('0f6a18a2-2ce5-4abd-8ca2-80b1cddafb63')
WHERE movies.id = UUID_TO_BIN('2375219d-1fc8-41f1-b47f-d8ee6b0edd6e');

-- A View To A Kill
INSERT INTO technical_specifications (id, runtime_in_minutes, sound_mix, aspect_ratio, printed_film_format, created_at, updated_at)
VALUES (
           UUID_TO_BIN('b0833ac7-a234-4377-9c2c-4695959edfb0'),
           131,
           'Dolby Stereo, Dolby Surround 7.1',
           '2.39:1',
           '35 mm',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.technical_specifications_id = UUID_TO_BIN('b0833ac7-a234-4377-9c2c-4695959edfb0')
WHERE movies.id = UUID_TO_BIN('c34f1b7b-3010-4155-8c40-bb4efb20dfb2');

-- The Living Daylights
INSERT INTO technical_specifications (id, runtime_in_minutes, sound_mix, aspect_ratio, printed_film_format, created_at, updated_at)
VALUES (
           UUID_TO_BIN('5b1155a1-097d-4e26-a633-39e50d8ab4a9'),
           130,
           'Dolby Stereo, Dolby Surround 7.1',
           '2.20:1, 2.39:1',
           '8 mm, 16 mm, 35 mm, 70 mm',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.technical_specifications_id = UUID_TO_BIN('5b1155a1-097d-4e26-a633-39e50d8ab4a9')
WHERE movies.id = UUID_TO_BIN('dc7940f8-ad41-42b8-bf5d-da25e99752e4');

-- License To Kill
INSERT INTO technical_specifications (id, runtime_in_minutes, sound_mix, aspect_ratio, printed_film_format, created_at, updated_at)
VALUES (
           UUID_TO_BIN('d5db6fb8-89ee-4bc1-9297-562b0ecb7715'),
           133,
           'Dolby SR, Dolby Surround 7.1',
           '2.39:1',
           '16 mm, 35 mm',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.technical_specifications_id = UUID_TO_BIN('d5db6fb8-89ee-4bc1-9297-562b0ecb7715')
WHERE movies.id = UUID_TO_BIN('179f3409-d39d-437d-b998-79f150ee1b30');

-- Goldeneye
INSERT INTO technical_specifications (id, runtime_in_minutes, sound_mix, aspect_ratio, printed_film_format, created_at, updated_at)
VALUES (
           UUID_TO_BIN('76590ef3-73ef-440d-9986-5da248a2a89f'),
           130,
           'DTS, Dolby Digital, DTS-Stereo, Dolby SR',
           '2.20:1, 2.39:1',
           '16 mm, 35 mm, 70 mm',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.technical_specifications_id = UUID_TO_BIN('76590ef3-73ef-440d-9986-5da248a2a89f')
WHERE movies.id = UUID_TO_BIN('06d701ab-f89f-4b57-9c6e-f43628cc4e6d');

-- Tomorrow Never Dies
INSERT INTO technical_specifications (id, runtime_in_minutes, sound_mix, aspect_ratio, printed_film_format, created_at, updated_at)
VALUES (
           UUID_TO_BIN('32d2dfb9-d97c-4b1f-a89b-3dcb236db03a'),
           119,
           'DTS 70 mm, Dolby Digital, DTS, SDDS',
           '2.20:1, 2.39:1',
           '35 mm, 70 mm',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.technical_specifications_id = UUID_TO_BIN('32d2dfb9-d97c-4b1f-a89b-3dcb236db03a')
WHERE movies.id = UUID_TO_BIN('28b1807d-93cc-4078-ad3a-44f00375d3dd');

-- The World Is Not Enough
INSERT INTO technical_specifications (id, runtime_in_minutes, sound_mix, aspect_ratio, printed_film_format, created_at, updated_at)
VALUES (
           UUID_TO_BIN('acfb79a6-471b-40ef-8c57-88d1175e77f6'),
           128,
           'Dolby Digital EX, SDDS, DTS-ES, Dolby Digital, DTS',
           '2.39:1',
           '35 mm',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.technical_specifications_id = UUID_TO_BIN('acfb79a6-471b-40ef-8c57-88d1175e77f6')
WHERE movies.id = UUID_TO_BIN('8ddc2a5a-c855-4768-9b70-3a3d383e621b');

-- Die Another Day
INSERT INTO technical_specifications (id, runtime_in_minutes, sound_mix, aspect_ratio, printed_film_format, created_at, updated_at)
VALUES (
           UUID_TO_BIN('fc775508-8bac-4b00-ad4b-dba3203f35ac'),
           133,
           'Dolby Digital EX, SDDS, DTS-ES',
           '2.39:1',
           '35 mm',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.technical_specifications_id = UUID_TO_BIN('fc775508-8bac-4b00-ad4b-dba3203f35ac')
WHERE movies.id = UUID_TO_BIN('f555fa79-8c7a-46df-b178-649ae651065f');

-- Casino Royale
INSERT INTO technical_specifications (id, runtime_in_minutes, sound_mix, aspect_ratio, printed_film_format, created_at, updated_at)
VALUES (
           UUID_TO_BIN('c4fd42cd-189c-40a7-9b51-a0425792a28f'),
           144,
           'Dolby Digital, SDDS, DTS',
           '2.39:1',
           '35 mm, D-Cinema',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.technical_specifications_id = UUID_TO_BIN('c4fd42cd-189c-40a7-9b51-a0425792a28f')
WHERE movies.id = UUID_TO_BIN('8218a578-0622-4d75-a86c-1501a3e48afc');

-- Quantum Of Solace
INSERT INTO technical_specifications (id, runtime_in_minutes, sound_mix, aspect_ratio, printed_film_format, created_at, updated_at)
VALUES (
           UUID_TO_BIN('fe160766-3ebd-43a7-96cf-8a70bc8b9e23'),
           106,
           'Dolby Digital, SDDS, DTS',
           '2.39:1',
           '35 mm, D-Cinema',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.technical_specifications_id = UUID_TO_BIN('fe160766-3ebd-43a7-96cf-8a70bc8b9e23')
WHERE movies.id = UUID_TO_BIN('32b40321-da45-4ff3-b417-2b2d79c10de9');

-- Skyfall
INSERT INTO technical_specifications (id, runtime_in_minutes, sound_mix, aspect_ratio, printed_film_format, created_at, updated_at)
VALUES (
           UUID_TO_BIN('64e531fd-b2a9-4158-97c1-26e762645c90'),
           143,
           'Dolby Digital, SDDS, Datasat, Sonics-DDP, Dolby Surround 7.1, Dolby Atmos, IMAX 6-Track',
           '1.90:1, 2.39:1',
           '35 mm, 70 mm, D-Cinema',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.technical_specifications_id = UUID_TO_BIN('64e531fd-b2a9-4158-97c1-26e762645c90')
WHERE movies.id = UUID_TO_BIN('2a85118c-7fd2-4096-9668-01fd8320ab17');

-- Spectre
INSERT INTO technical_specifications (id, runtime_in_minutes, sound_mix, aspect_ratio, printed_film_format, created_at, updated_at)
VALUES (
           UUID_TO_BIN('9fb12eee-82b1-4980-8ae5-2e6b8eb56a9e'),
           148,
           'Dolby Digital, 12-Track Digital Sound, Datasat, Dolby Surround 7.1, SDDS',
           '2.39:1',
           'D-Cinema',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.technical_specifications_id = UUID_TO_BIN('9fb12eee-82b1-4980-8ae5-2e6b8eb56a9e')
WHERE movies.id = UUID_TO_BIN('0d7d18ba-04a5-4f64-a720-8cbf39692862');

-- No Time To Die
INSERT INTO technical_specifications (id, runtime_in_minutes, sound_mix, aspect_ratio, printed_film_format, created_at, updated_at)
VALUES (
           UUID_TO_BIN('aa2f52fc-b926-4f9a-876b-07a73b876a58'),
           163,
           'Dolby Digital, 12-Track Digital Sound, Dolby Surround 7.1, DTS, Auro 11.1, Dolby Atmos, IMAX 6-Track, SDDS',
           '1.43:1, 1.90:1, 2.39:1',
           'D-Cinema',
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

UPDATE movies
SET movies.technical_specifications_id = UUID_TO_BIN('aa2f52fc-b926-4f9a-876b-07a73b876a58')
WHERE movies.id = UUID_TO_BIN('f1930593-6cae-45d0-a063-84582ac10fac');
