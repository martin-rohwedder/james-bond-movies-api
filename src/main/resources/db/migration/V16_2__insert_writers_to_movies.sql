-- Insert writers
INSERT INTO writers (id, name, date_of_birth, date_of_death, created_at, updated_at)
VALUES
    (UUID_TO_BIN('98fa6ec8-1892-417a-9c12-3ade28f664be'), 'Ian Fleming', '1908-05-28', '1964-08-12', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)), -- Dr. No, From Russia With Love, Goldfinger, Thunderball, You Only Live Twice, On Her Majesty's Secret Service, Diamonds Are Forever, Live And Let Die, The Man With The Golden Gun, The Spy Who Loved Me, Moonraker, For Your Eyes Only, Octopussy, The Living Daylights, Casino Royale
    (UUID_TO_BIN('4e554a3f-98a7-4035-a4d0-0af83b203713'), 'Richard Maibaum', '1909-05-26', '1991-01-04', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)), -- Dr. No, From Russia With Love, Goldfinger, Thunderball, On Her Majesty's Secret Service, Diamonds Are Forever, The Man With The Golden Gun, The Spy Who Loved Me, For Your Eyes Only, Octopussy, A View To A Kill, The Living Daylights, License To Kill
    (UUID_TO_BIN('bec816d2-031a-4dd5-9211-e60ec2926e0c'), 'Johanna Harwood', '1930-01-01', null, CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)), -- Dr. No, From Russia With Love, Goldfinger
    (UUID_TO_BIN('14b104f9-5bb0-4d23-98a8-6012e0ef1490'), 'Berkely Mather', '1909-02-25', '1996-04-07', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)), -- Dr. No, From Russia With Love, Goldfinger
    (UUID_TO_BIN('d63032af-f100-4272-aa6d-4b348385fde9'), 'Paul Dehn', '1912-11-05', '1976-09-30', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)), -- Goldfinger
    (UUID_TO_BIN('8c9884b0-76a2-42c3-b959-a38cad2eef49'), 'John Hopkins', '1931-01-27', '1998-07-23', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)), -- Thunderball
    (UUID_TO_BIN('349ad0a7-e716-4db7-87d9-2ca8017b5d75'), 'Jack Whittingham', '1910-08-02', '1972-07-03', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)), -- Thunderball
    (UUID_TO_BIN('b36e10c7-cd82-4f6e-94d9-b45cebf79ede'), 'Harold Jack Bloom', '1924-04-26', '1999-08-27', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)), -- You Only Live Twice
    (UUID_TO_BIN('54ce037f-dcb0-4160-9c45-0eb8a135e8f8'), 'Roald Dahl', '1916-09-13', '1990-11-23', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)), -- You Only Live Twice
    (UUID_TO_BIN('d5fbc549-707c-424c-8f2e-95be13f71e5b'), 'Simon Raven', '1927-12-28', '2001-05-12', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)), -- On Her Majesty's Secret Service
    (UUID_TO_BIN('e2e49a1e-e088-4c14-813b-93ce933fd85c'), 'Tom Mankiewicz', '1942-06-01', '2010-07-31', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)), -- Diamonds Are Forever, Live And Let Die, The Man With The Golden Gun
    (UUID_TO_BIN('818a27fa-a7d6-4ab8-a4ed-9afa390ad1d1'), 'Christopher Wood', '1935-11-05', '2015-05-09', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)), -- The Spy Who Loved Me, Moonraker
    (UUID_TO_BIN('89a37637-7cb6-4860-b3b1-207215105d30'), 'Gerry Anderson', '1929-04-14', '2012-12-26', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)), -- Moonraker
    (UUID_TO_BIN('c8a698f3-d9ae-434f-b995-af3665530742'), 'Tony Barwick', '1934-07-10', '1993-08-18', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)), -- Moonraker
    (UUID_TO_BIN('e1c74a4b-ca5d-4cb9-a94c-acbf6272ce3b'), 'Michael G. Wilson', '1942-01-21', null, CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)), -- For Your Eyes Only, Octopussy, A View To A Kill, The Living Daylights, License To Kill
    (UUID_TO_BIN('bb170053-13da-42ab-9bbc-cc0bdc72318b'), 'George MacDonald Fraser', '1925-04-02', '2008-01-02', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)), -- Octopussy
    (UUID_TO_BIN('a2e0b3b4-495c-4373-86ac-c4aeaac8878e'), 'Michael France', '1962-01-04', '2013-04-12', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)), -- Goldeneye
    (UUID_TO_BIN('7121caa1-9022-4e14-9ee1-5c07996483bc'), 'Jeffrey Caine', '1944-01-01', null, CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)), -- Goldeneye
    (UUID_TO_BIN('5a9f3235-dc1d-4691-a64c-20a6c4420b8c'), 'Bruce Feirstein', '1956-01-01', null, CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)), -- Goldeneye, Tomorrow Never Dies, The World Is Not Enough
    (UUID_TO_BIN('c833c196-779d-43df-b539-ed86e9c9f912'), 'Neal Purvis', '1961-09-09', null, CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)), -- The World Is Not Enough, Die Another Day, Casino Royale, Quantum Of Solace, Skyfall, Spectre, No Time To Die
    (UUID_TO_BIN('8d75b173-79be-4ebe-bc9c-5a7c3560451c'), 'Robert Wade', '1962-01-01', null, CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)), -- The World Is Not Enough, Die Another Day, Casino Royale, Quantum Of Solace, Skyfall, Spectre, No Time To Die
    (UUID_TO_BIN('1cb9c091-8b7d-4228-b183-161d85b637aa'), 'Paul Haggis', '1953-03-10', null, CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)), -- Casino Royale, Quantum Of Solace
    (UUID_TO_BIN('eb5a3093-3dd6-4f8a-bb3c-703dd911c99b'), 'John Logan', '1961-09-24', null, CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)), -- Skyfall. Spectre
    (UUID_TO_BIN('72ea1567-dcc4-45a8-8877-994f00a20b73'), 'Cary Joji Fukunaga', '1977-07-10', null, CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)), -- No Time To Die
    (UUID_TO_BIN('88938861-be31-490e-8d9f-27c999f7c91b'), 'Phoebe Waller-Bridge', '1985-07-14', null, CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)); -- No Time To Die

/**
  Add writers to every movie using join table
 */

INSERT INTO movies_writers (movie_id, writer_id)
VALUES
    ((SELECT id FROM movies WHERE title = 'Dr. No'), (SELECT id FROM writers WHERE name = 'Ian Fleming')),
    ((SELECT id FROM movies WHERE title = 'From Russia With Love'), (SELECT id FROM writers WHERE name = 'Ian Fleming')),
    ((SELECT id FROM movies WHERE title = 'Goldfinger'), (SELECT id FROM writers WHERE name = 'Ian Fleming')),
    ((SELECT id FROM movies WHERE title = 'Thunderball'), (SELECT id FROM writers WHERE name = 'Ian Fleming')),
    ((SELECT id FROM movies WHERE title = 'You Only Live Twice'), (SELECT id FROM writers WHERE name = 'Ian Fleming')),
    ((SELECT id FROM movies WHERE title = 'On Her Majesty''s Secret Service'), (SELECT id FROM writers WHERE name = 'Ian Fleming')),
    ((SELECT id FROM movies WHERE title = 'Diamonds Are Forever'), (SELECT id FROM writers WHERE name = 'Ian Fleming')),
    ((SELECT id FROM movies WHERE title = 'Live And Let Die'), (SELECT id FROM writers WHERE name = 'Ian Fleming')),
    ((SELECT id FROM movies WHERE title = 'The Man With The Golden Gun'), (SELECT id FROM writers WHERE name = 'Ian Fleming')),
    ((SELECT id FROM movies WHERE title = 'The Spy Who Loved Me'), (SELECT id FROM writers WHERE name = 'Ian Fleming')),
    ((SELECT id FROM movies WHERE title = 'Moonraker'), (SELECT id FROM writers WHERE name = 'Ian Fleming')),
    ((SELECT id FROM movies WHERE title = 'For Your Eyes Only'), (SELECT id FROM writers WHERE name = 'Ian Fleming')),
    ((SELECT id FROM movies WHERE title = 'Octopussy'), (SELECT id FROM writers WHERE name = 'Ian Fleming')),
    ((SELECT id FROM movies WHERE title = 'The Living Daylights'), (SELECT id FROM writers WHERE name = 'Ian Fleming')),
    ((SELECT id FROM movies WHERE title = 'Casino Royale'), (SELECT id FROM writers WHERE name = 'Ian Fleming')),
    ((SELECT id FROM movies WHERE title = 'Dr. No'), (SELECT id FROM writers WHERE name = 'Richard Maibaum')),
    ((SELECT id FROM movies WHERE title = 'From Russia With Love'), (SELECT id FROM writers WHERE name = 'Richard Maibaum')),
    ((SELECT id FROM movies WHERE title = 'Goldfinger'), (SELECT id FROM writers WHERE name = 'Richard Maibaum')),
    ((SELECT id FROM movies WHERE title = 'Thunderball'), (SELECT id FROM writers WHERE name = 'Richard Maibaum')),
    ((SELECT id FROM movies WHERE title = 'On Her Majesty''s Secret Service'), (SELECT id FROM writers WHERE name = 'Richard Maibaum')),
    ((SELECT id FROM movies WHERE title = 'Diamonds Are Forever'), (SELECT id FROM writers WHERE name = 'Richard Maibaum')),
    ((SELECT id FROM movies WHERE title = 'The Man With The Golden Gun'), (SELECT id FROM writers WHERE name = 'Richard Maibaum')),
    ((SELECT id FROM movies WHERE title = 'The Spy Who Loved Me'), (SELECT id FROM writers WHERE name = 'Richard Maibaum')),
    ((SELECT id FROM movies WHERE title = 'For Your Eyes Only'), (SELECT id FROM writers WHERE name = 'Richard Maibaum')),
    ((SELECT id FROM movies WHERE title = 'Octopussy'), (SELECT id FROM writers WHERE name = 'Richard Maibaum')),
    ((SELECT id FROM movies WHERE title = 'A View To A Kill'), (SELECT id FROM writers WHERE name = 'Richard Maibaum')),
    ((SELECT id FROM movies WHERE title = 'The Living Daylights'), (SELECT id FROM writers WHERE name = 'Richard Maibaum')),
    ((SELECT id FROM movies WHERE title = 'License To Kill'), (SELECT id FROM writers WHERE name = 'Richard Maibaum')),
    ((SELECT id FROM movies WHERE title = 'Dr. No'), (SELECT id FROM writers WHERE name = 'Johanna Harwood')),
    ((SELECT id FROM movies WHERE title = 'From Russia With Love'), (SELECT id FROM writers WHERE name = 'Johanna Harwood')),
    ((SELECT id FROM movies WHERE title = 'Goldfinger'), (SELECT id FROM writers WHERE name = 'Johanna Harwood')),
    ((SELECT id FROM movies WHERE title = 'Dr. No'), (SELECT id FROM writers WHERE name = 'Berkely Mather')),
    ((SELECT id FROM movies WHERE title = 'From Russia With Love'), (SELECT id FROM writers WHERE name = 'Berkely Mather')),
    ((SELECT id FROM movies WHERE title = 'Goldfinger'), (SELECT id FROM writers WHERE name = 'Berkely Mather')),
    ((SELECT id FROM movies WHERE title = 'Goldfinger'), (SELECT id FROM writers WHERE name = 'Paul Dehn')),
    ((SELECT id FROM movies WHERE title = 'Thunderball'), (SELECT id FROM writers WHERE name = 'John Hopkins')),
    ((SELECT id FROM movies WHERE title = 'Thunderball'), (SELECT id FROM writers WHERE name = 'Jack Whittingham')),
    ((SELECT id FROM movies WHERE title = 'You Only Live Twice'), (SELECT id FROM writers WHERE name = 'Harold Jack Bloom')),
    ((SELECT id FROM movies WHERE title = 'You Only Live Twice'), (SELECT id FROM writers WHERE name = 'Roald Dahl')),
    ((SELECT id FROM movies WHERE title = 'On Her Majesty''s Secret Service'), (SELECT id FROM writers WHERE name = 'Simon Raven')),
    ((SELECT id FROM movies WHERE title = 'Diamonds Are Forever'), (SELECT id FROM writers WHERE name = 'Tom Mankiewicz')),
    ((SELECT id FROM movies WHERE title = 'Live And Let Die'), (SELECT id FROM writers WHERE name = 'Tom Mankiewicz')),
    ((SELECT id FROM movies WHERE title = 'The Man With The Golden Gun'), (SELECT id FROM writers WHERE name = 'Tom Mankiewicz')),
    ((SELECT id FROM movies WHERE title = 'The Spy Who Loved Me'), (SELECT id FROM writers WHERE name = 'Christopher Wood')),
    ((SELECT id FROM movies WHERE title = 'Moonraker'), (SELECT id FROM writers WHERE name = 'Christopher Wood')),
    ((SELECT id FROM movies WHERE title = 'Moonraker'), (SELECT id FROM writers WHERE name = 'Gerry Anderson')),
    ((SELECT id FROM movies WHERE title = 'Moonraker'), (SELECT id FROM writers WHERE name = 'Tony Barwick')),
    ((SELECT id FROM movies WHERE title = 'For Your Eyes Only'), (SELECT id FROM writers WHERE name = 'Michael G. Wilson')),
    ((SELECT id FROM movies WHERE title = 'Octopussy'), (SELECT id FROM writers WHERE name = 'Michael G. Wilson')),
    ((SELECT id FROM movies WHERE title = 'A View To A Kill'), (SELECT id FROM writers WHERE name = 'Michael G. Wilson')),
    ((SELECT id FROM movies WHERE title = 'The Living Daylights'), (SELECT id FROM writers WHERE name = 'Michael G. Wilson')),
    ((SELECT id FROM movies WHERE title = 'License To Kill'), (SELECT id FROM writers WHERE name = 'Michael G. Wilson')),
    ((SELECT id FROM movies WHERE title = 'Octopussy'), (SELECT id FROM writers WHERE name = 'George MacDonald Fraser')),
    ((SELECT id FROM movies WHERE title = 'Goldeneye'), (SELECT id FROM writers WHERE name = 'Michael France')),
    ((SELECT id FROM movies WHERE title = 'Goldeneye'), (SELECT id FROM writers WHERE name = 'Jeffrey Caine')),
    ((SELECT id FROM movies WHERE title = 'Goldeneye'), (SELECT id FROM writers WHERE name = 'Bruce Feirstein')),
    ((SELECT id FROM movies WHERE title = 'Tomorrow Never Dies'), (SELECT id FROM writers WHERE name = 'Bruce Feirstein')),
    ((SELECT id FROM movies WHERE title = 'The World Is Not Enough'), (SELECT id FROM writers WHERE name = 'Bruce Feirstein')),
    ((SELECT id FROM movies WHERE title = 'The World Is Not Enough'), (SELECT id FROM writers WHERE name = 'Neal Purvis')),
    ((SELECT id FROM movies WHERE title = 'Die Another Day'), (SELECT id FROM writers WHERE name = 'Neal Purvis')),
    ((SELECT id FROM movies WHERE title = 'Casino Royale'), (SELECT id FROM writers WHERE name = 'Neal Purvis')),
    ((SELECT id FROM movies WHERE title = 'Quantum Of Solace'), (SELECT id FROM writers WHERE name = 'Neal Purvis')),
    ((SELECT id FROM movies WHERE title = 'Skyfall'), (SELECT id FROM writers WHERE name = 'Neal Purvis')),
    ((SELECT id FROM movies WHERE title = 'Spectre'), (SELECT id FROM writers WHERE name = 'Neal Purvis')),
    ((SELECT id FROM movies WHERE title = 'No Time To Die'), (SELECT id FROM writers WHERE name = 'Neal Purvis')),
    ((SELECT id FROM movies WHERE title = 'The World Is Not Enough'), (SELECT id FROM writers WHERE name = 'Robert Wade')),
    ((SELECT id FROM movies WHERE title = 'Die Another Day'), (SELECT id FROM writers WHERE name = 'Robert Wade')),
    ((SELECT id FROM movies WHERE title = 'Casino Royale'), (SELECT id FROM writers WHERE name = 'Robert Wade')),
    ((SELECT id FROM movies WHERE title = 'Quantum Of Solace'), (SELECT id FROM writers WHERE name = 'Robert Wade')),
    ((SELECT id FROM movies WHERE title = 'Skyfall'), (SELECT id FROM writers WHERE name = 'Robert Wade')),
    ((SELECT id FROM movies WHERE title = 'Spectre'), (SELECT id FROM writers WHERE name = 'Robert Wade')),
    ((SELECT id FROM movies WHERE title = 'No Time To Die'), (SELECT id FROM writers WHERE name = 'Robert Wade')),
    ((SELECT id FROM movies WHERE title = 'Casino Royale'), (SELECT id FROM writers WHERE name = 'Paul Haggis')),
    ((SELECT id FROM movies WHERE title = 'Quantum Of Solace'), (SELECT id FROM writers WHERE name = 'Paul Haggis')),
    ((SELECT id FROM movies WHERE title = 'Skyfall'), (SELECT id FROM writers WHERE name = 'John Logan')),
    ((SELECT id FROM movies WHERE title = 'Spectre'), (SELECT id FROM writers WHERE name = 'John Logan')),
    ((SELECT id FROM movies WHERE title = 'No Time To Die'), (SELECT id FROM writers WHERE name = 'Cary Joji Fukunaga')),
    ((SELECT id FROM movies WHERE title = 'No Time To Die'), (SELECT id FROM writers WHERE name = 'Phoebe Waller-Bridge'));
