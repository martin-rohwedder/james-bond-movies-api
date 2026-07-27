/**
  1. Dr. No
 */
INSERT INTO movies_actors (movie_id, actor_id)
VALUES
    ((SELECT id FROM movies WHERE title = 'Dr. No'), (SELECT id FROM actors WHERE name = 'Sean Connery')),
    ((SELECT id FROM movies WHERE title = 'Dr. No'), (SELECT id FROM actors WHERE name = 'Ursula Andress')),
    ((SELECT id FROM movies WHERE title = 'Dr. No'), (SELECT id FROM actors WHERE name = 'Joseph Wiseman')),
    ((SELECT id FROM movies WHERE title = 'Dr. No'), (SELECT id FROM actors WHERE name = 'Bernard Lee')),
    ((SELECT id FROM movies WHERE title = 'Dr. No'), (SELECT id FROM actors WHERE name = 'Lois Maxwell')),
    ((SELECT id FROM movies WHERE title = 'Dr. No'), (SELECT id FROM actors WHERE name = 'Jack Lord')),
    ((SELECT id FROM movies WHERE title = 'Dr. No'), UUID_TO_BIN('fd195e53-fddf-47c1-927f-6959437d3af1')), -- Anthony Dawson (Dr. No)
    ((SELECT id FROM movies WHERE title = 'Dr. No'), (SELECT id FROM actors WHERE name = 'Zena Marshall')),
    ((SELECT id FROM movies WHERE title = 'Dr. No'), (SELECT id FROM actors WHERE name = 'Eunice Gayson')),
    ((SELECT id FROM movies WHERE title = 'Dr. No'), (SELECT id FROM actors WHERE name = 'John Kitzmiller')),
    ((SELECT id FROM movies WHERE title = 'Dr. No'), (SELECT id FROM actors WHERE name = 'Peter Burton'));

/**
  2. From Russia With Love
 */
INSERT INTO movies_actors (movie_id, actor_id)
VALUES
    ((SELECT id FROM movies WHERE title = 'From Russia With Love'), (SELECT id FROM actors WHERE name = 'Sean Connery')),
    ((SELECT id FROM movies WHERE title = 'From Russia With Love'), (SELECT id FROM actors WHERE name = 'Bernard Lee')),
    ((SELECT id FROM movies WHERE title = 'From Russia With Love'), (SELECT id FROM actors WHERE name = 'Lois Maxwell')),
    ((SELECT id FROM movies WHERE title = 'From Russia With Love'), (SELECT id FROM actors WHERE name = 'Robert Shaw')),
    ((SELECT id FROM movies WHERE title = 'From Russia With Love'), (SELECT id FROM actors WHERE name = 'Eunice Gayson')),
    ((SELECT id FROM movies WHERE title = 'From Russia With Love'), UUID_TO_BIN('91027fc5-7388-4f21-92da-d2ad3e24c339')), -- Anthony Dawson (From Russia With Love, Thunderball)
    ((SELECT id FROM movies WHERE title = 'From Russia With Love'), (SELECT id FROM actors WHERE name = 'Lotte Lenya')),
    ((SELECT id FROM movies WHERE title = 'From Russia With Love'), (SELECT id FROM actors WHERE name = 'Daniela Bianchi')),
    ((SELECT id FROM movies WHERE title = 'From Russia With Love'), UUID_TO_BIN('c3a4a81d-42b8-4101-82a6-aa0e0f95e633')), -- Pedro Armendáriz
    ((SELECT id FROM movies WHERE title = 'From Russia With Love'), (SELECT id FROM actors WHERE name = 'Vladek Shyebal')),
    ((SELECT id FROM movies WHERE title = 'From Russia With Love'), UUID_TO_BIN('6a6da807-3476-4aa6-970e-3de36e2631e3')), -- Walter Gotell (From Russia With Love)
    ((SELECT id FROM movies WHERE title = 'From Russia With Love'), (SELECT id FROM actors WHERE name = 'Francis De Wolff')),
    ((SELECT id FROM movies WHERE title = 'From Russia With Love'), (SELECT id FROM actors WHERE name = 'Desmond Llewelyn'));

/**
  3. Goldfinger
 */
INSERT INTO movies_actors (movie_id, actor_id)
VALUES
    ((SELECT id FROM movies WHERE title = 'Goldfinger'), (SELECT id FROM actors WHERE name = 'Sean Connery')),
    ((SELECT id FROM movies WHERE title = 'Goldfinger'), (SELECT id FROM actors WHERE name = 'Bernard Lee')),
    ((SELECT id FROM movies WHERE title = 'Goldfinger'), (SELECT id FROM actors WHERE name = 'Desmond Llewelyn')),
    ((SELECT id FROM movies WHERE title = 'Goldfinger'), UUID_TO_BIN('bd0e5683-5691-43b2-8d26-9882af45826a')), -- Gert Fröbe (Goldfinger)
    ((SELECT id FROM movies WHERE title = 'Goldfinger'), (SELECT id FROM actors WHERE name = 'Honor Blackman')),
    ((SELECT id FROM movies WHERE title = 'Goldfinger'), (SELECT id FROM actors WHERE name = 'Shirley Eaton')),
    ((SELECT id FROM movies WHERE title = 'Goldfinger'), (SELECT id FROM actors WHERE name = 'Tania Mallet')),
    ((SELECT id FROM movies WHERE title = 'Goldfinger'), (SELECT id FROM actors WHERE name = 'Harold Sakata')),
    ((SELECT id FROM movies WHERE title = 'Goldfinger'), (SELECT id FROM actors WHERE name = 'Martin Benson')),
    ((SELECT id FROM movies WHERE title = 'Goldfinger'), (SELECT id FROM actors WHERE name = 'Cec Linder')),
    ((SELECT id FROM movies WHERE title = 'Goldfinger'), (SELECT id FROM actors WHERE name = 'Richard Vernon')),
    ((SELECT id FROM movies WHERE title = 'Goldfinger'), (SELECT id FROM actors WHERE name = 'Austin Willis')),
    ((SELECT id FROM movies WHERE title = 'Goldfinger'), (SELECT id FROM actors WHERE name = 'Lois Maxwell'));

/**
  4. Thunderball
 */
INSERT INTO movies_actors (movie_id, actor_id)
VALUES
    ((SELECT id FROM movies WHERE title = 'Thunderball'), (SELECT id FROM actors WHERE name = 'Sean Connery')),
    ((SELECT id FROM movies WHERE title = 'Thunderball'), (SELECT id FROM actors WHERE name = 'Bernard Lee')),
    ((SELECT id FROM movies WHERE title = 'Thunderball'), (SELECT id FROM actors WHERE name = 'Desmond Llewelyn')),
    ((SELECT id FROM movies WHERE title = 'Thunderball'), UUID_TO_BIN('91027fc5-7388-4f21-92da-d2ad3e24c339')), -- Anthony Dawson (From Russia With Love, Thunderball)
    ((SELECT id FROM movies WHERE title = 'Thunderball'), (SELECT id FROM actors WHERE name = 'Claudine Auger')),
    ((SELECT id FROM movies WHERE title = 'Thunderball'), (SELECT id FROM actors WHERE name = 'Adolfo Celi')),
    ((SELECT id FROM movies WHERE title = 'Thunderball'), (SELECT id FROM actors WHERE name = 'Luciana Paluzzi')),
    ((SELECT id FROM movies WHERE title = 'Thunderball'), (SELECT id FROM actors WHERE name = 'Rik Van Nutter')),
    ((SELECT id FROM movies WHERE title = 'Thunderball'), (SELECT id FROM actors WHERE name = 'Guy Doleman')),
    ((SELECT id FROM movies WHERE title = 'Thunderball'), (SELECT id FROM actors WHERE name = 'Molly Peters')),
    ((SELECT id FROM movies WHERE title = 'Thunderball'), (SELECT id FROM actors WHERE name = 'Martine Beswick')),
    ((SELECT id FROM movies WHERE title = 'Thunderball'), (SELECT id FROM actors WHERE name = 'Philip Locke')),
    ((SELECT id FROM movies WHERE title = 'Thunderball'), (SELECT id FROM actors WHERE name = 'Lois Maxwell'));

/**
  5. You Only Live Twice
 */
INSERT INTO movies_actors (movie_id, actor_id)
VALUES
    ((SELECT id FROM movies WHERE title = 'You Only Live Twice'), (SELECT id FROM actors WHERE name = 'Sean Connery')),
    ((SELECT id FROM movies WHERE title = 'You Only Live Twice'), (SELECT id FROM actors WHERE name = 'Bernard Lee')),
    ((SELECT id FROM movies WHERE title = 'You Only Live Twice'), (SELECT id FROM actors WHERE name = 'Desmond Llewelyn')),
    ((SELECT id FROM movies WHERE title = 'You Only Live Twice'), (SELECT id FROM actors WHERE name = 'Akiko Wakabayashi')),
    ((SELECT id FROM movies WHERE title = 'You Only Live Twice'), (SELECT id FROM actors WHERE name = 'Mie Hama')),
    ((SELECT id FROM movies WHERE title = 'You Only Live Twice'), UUID_TO_BIN('9cd4010c-66d4-476b-9af8-7b149dda8d12')), -- Tetsurô Tanba (You Only Live Twice)
    ((SELECT id FROM movies WHERE title = 'You Only Live Twice'), (SELECT id FROM actors WHERE name = 'Teru Shimada')),
    ((SELECT id FROM movies WHERE title = 'You Only Live Twice'), (SELECT id FROM actors WHERE name = 'Karin Dor')),
    ((SELECT id FROM movies WHERE title = 'You Only Live Twice'), (SELECT id FROM actors WHERE name = 'Donald Pleasence')),
    ((SELECT id FROM movies WHERE title = 'You Only Live Twice'), UUID_TO_BIN('9001bc38-41a3-4d30-bcc6-982d129c8996')), -- Charles Gray (You Only Live Twice)
    ((SELECT id FROM movies WHERE title = 'You Only Live Twice'), (SELECT id FROM actors WHERE name = 'Lois Maxwell'));

/**
  6. On Her Majesty's Secret Service
 */
INSERT INTO movies_actors (movie_id, actor_id)
VALUES
    ((SELECT id FROM movies WHERE title = 'On Her Majesty''s Secret Service'), (SELECT id FROM actors WHERE name = 'George Lazenby')),
    ((SELECT id FROM movies WHERE title = 'On Her Majesty''s Secret Service'), (SELECT id FROM actors WHERE name = 'Bernard Lee')),
    ((SELECT id FROM movies WHERE title = 'On Her Majesty''s Secret Service'), (SELECT id FROM actors WHERE name = 'Desmond Llewelyn')),
    ((SELECT id FROM movies WHERE title = 'On Her Majesty''s Secret Service'), (SELECT id FROM actors WHERE name = 'Diana Rigg')),
    ((SELECT id FROM movies WHERE title = 'On Her Majesty''s Secret Service'), (SELECT id FROM actors WHERE name = 'Telly Savalas')),
    ((SELECT id FROM movies WHERE title = 'On Her Majesty''s Secret Service'), (SELECT id FROM actors WHERE name = 'Gabriele Ferzetti')),
    ((SELECT id FROM movies WHERE title = 'On Her Majesty''s Secret Service'), (SELECT id FROM actors WHERE name = 'Ilse Steppat')),
    ((SELECT id FROM movies WHERE title = 'On Her Majesty''s Secret Service'), UUID_TO_BIN('36593d65-2413-46d7-a771-4bf11ed8ebfd')), -- George Baker (On Her Majesty's Secret Service)
    ((SELECT id FROM movies WHERE title = 'On Her Majesty''s Secret Service'), (SELECT id FROM actors WHERE name = 'Bernard Horsfall')),
    ((SELECT id FROM movies WHERE title = 'On Her Majesty''s Secret Service'), (SELECT id FROM actors WHERE name = 'Yuri Borienko')),
    ((SELECT id FROM movies WHERE title = 'On Her Majesty''s Secret Service'), (SELECT id FROM actors WHERE name = 'Virginia North')),
    ((SELECT id FROM movies WHERE title = 'On Her Majesty''s Secret Service'), (SELECT id FROM actors WHERE name = 'Irvin Allen')),
    ((SELECT id FROM movies WHERE title = 'On Her Majesty''s Secret Service'), (SELECT id FROM actors WHERE name = 'Terence Mountain')),
    ((SELECT id FROM movies WHERE title = 'On Her Majesty''s Secret Service'), (SELECT id FROM actors WHERE name = 'James Bree')),
    ((SELECT id FROM movies WHERE title = 'On Her Majesty''s Secret Service'), (SELECT id FROM actors WHERE name = 'Angela Scoular')),
    ((SELECT id FROM movies WHERE title = 'On Her Majesty''s Secret Service'), (SELECT id FROM actors WHERE name = 'Catherine Von Schell')),
    ((SELECT id FROM movies WHERE title = 'On Her Majesty''s Secret Service'), (SELECT id FROM actors WHERE name = 'Lois Maxwell'));

/**
  7. Diamonds Are Forever
 */
INSERT INTO movies_actors (movie_id, actor_id)
VALUES
    ((SELECT id FROM movies WHERE title = 'Diamonds Are Forever'), (SELECT id FROM actors WHERE name = 'Sean Connery')),
    ((SELECT id FROM movies WHERE title = 'Diamonds Are Forever'), (SELECT id FROM actors WHERE name = 'Bernard Lee')),
    ((SELECT id FROM movies WHERE title = 'Diamonds Are Forever'), (SELECT id FROM actors WHERE name = 'Desmond Llewelyn')),
    ((SELECT id FROM movies WHERE title = 'Diamonds Are Forever'), (SELECT id FROM actors WHERE name = 'Jill St. John')),
    ((SELECT id FROM movies WHERE title = 'Diamonds Are Forever'), UUID_TO_BIN('7e6259d6-a505-4e4f-82ab-1972a738238b')), -- Charles Gray (Diamonds Are Forever)
    ((SELECT id FROM movies WHERE title = 'Diamonds Are Forever'), (SELECT id FROM actors WHERE name = 'Lana Wood')),
    ((SELECT id FROM movies WHERE title = 'Diamonds Are Forever'), (SELECT id FROM actors WHERE name = 'Jimmy Dean')),
    ((SELECT id FROM movies WHERE title = 'Diamonds Are Forever'), (SELECT id FROM actors WHERE name = 'Bruce Cabot')),
    ((SELECT id FROM movies WHERE title = 'Diamonds Are Forever'), (SELECT id FROM actors WHERE name = 'Putter Smith')),
    ((SELECT id FROM movies WHERE title = 'Diamonds Are Forever'), (SELECT id FROM actors WHERE name = 'Bruce Glover')),
    ((SELECT id FROM movies WHERE title = 'Diamonds Are Forever'), (SELECT id FROM actors WHERE name = 'Norman Burton')),
    ((SELECT id FROM movies WHERE title = 'Diamonds Are Forever'), UUID_TO_BIN('42e556f9-d0c6-460b-8ca0-be8ec5f5f4a1')), -- Joseph Fürst (Diamonds Are Forever)
    ((SELECT id FROM movies WHERE title = 'Diamonds Are Forever'), (SELECT id FROM actors WHERE name = 'Leonard Barr')),
    ((SELECT id FROM movies WHERE title = 'Diamonds Are Forever'), (SELECT id FROM actors WHERE name = 'Margaret Lacey')),
    ((SELECT id FROM movies WHERE title = 'Diamonds Are Forever'), (SELECT id FROM actors WHERE name = 'Joe Robinson')),
    ((SELECT id FROM movies WHERE title = 'Diamonds Are Forever'), (SELECT id FROM actors WHERE name = 'Laurence Naismith')),
    ((SELECT id FROM movies WHERE title = 'Diamonds Are Forever'), UUID_TO_BIN('2734a744-c52e-4d99-bcfa-77101f51b093')), -- Marc Lawrence (Diamonds Are Forever)
    ((SELECT id FROM movies WHERE title = 'Diamonds Are Forever'), (SELECT id FROM actors WHERE name = 'Lois Maxwell'));

/**
  8. Live And Let Die
 */
INSERT INTO movies_actors (movie_id, actor_id)
VALUES
    ((SELECT id FROM movies WHERE title = 'Live And Let Die'), (SELECT id FROM actors WHERE name = 'Roger Moore')),
    ((SELECT id FROM movies WHERE title = 'Live And Let Die'), (SELECT id FROM actors WHERE name = 'Bernard Lee')),
    ((SELECT id FROM movies WHERE title = 'Live And Let Die'), (SELECT id FROM actors WHERE name = 'Yaphet Kotto')),
    ((SELECT id FROM movies WHERE title = 'Live And Let Die'), (SELECT id FROM actors WHERE name = 'Jane Seymour')),
    ((SELECT id FROM movies WHERE title = 'Live And Let Die'), (SELECT id FROM actors WHERE name = 'Clifton James')),
    ((SELECT id FROM movies WHERE title = 'Live And Let Die'), (SELECT id FROM actors WHERE name = 'Julius Harris')),
    ((SELECT id FROM movies WHERE title = 'Live And Let Die'), (SELECT id FROM actors WHERE name = 'Geoffrey Holder')),
    ((SELECT id FROM movies WHERE title = 'Live And Let Die'), (SELECT id FROM actors WHERE name = 'Gloria Hendry')),
    ((SELECT id FROM movies WHERE title = 'Live And Let Die'), (SELECT id FROM actors WHERE name = 'Tommy Lane')),
    ((SELECT id FROM movies WHERE title = 'Live And Let Die'), (SELECT id FROM actors WHERE name = 'Roy Stewart')),
    ((SELECT id FROM movies WHERE title = 'Live And Let Die'), (SELECT id FROM actors WHERE name = 'Earl Jolly Brown')),
    ((SELECT id FROM movies WHERE title = 'Live And Let Die'), (SELECT id FROM actors WHERE name = 'Lon Satton')),
    ((SELECT id FROM movies WHERE title = 'Live And Let Die'), (SELECT id FROM actors WHERE name = 'David Hedison')),
    ((SELECT id FROM movies WHERE title = 'Live And Let Die'), (SELECT id FROM actors WHERE name = 'Lois Maxwell'));

/**
  9. The Man With The Golden Gun
 */
INSERT INTO movies_actors (movie_id, actor_id)
VALUES
    ((SELECT id FROM movies WHERE title = 'The Man With The Golden Gun'), (SELECT id FROM actors WHERE name = 'Roger Moore')),
    ((SELECT id FROM movies WHERE title = 'The Man With The Golden Gun'), (SELECT id FROM actors WHERE name = 'Bernard Lee')),
    ((SELECT id FROM movies WHERE title = 'The Man With The Golden Gun'), (SELECT id FROM actors WHERE name = 'Desmond Llewelyn')),
    ((SELECT id FROM movies WHERE title = 'The Man With The Golden Gun'), (SELECT id FROM actors WHERE name = 'Clifton James')),
    ((SELECT id FROM movies WHERE title = 'The Man With The Golden Gun'), (SELECT id FROM actors WHERE name = 'Christopher Lee')),
    ((SELECT id FROM movies WHERE title = 'The Man With The Golden Gun'), (SELECT id FROM actors WHERE name = 'Britt Ekland')),
    ((SELECT id FROM movies WHERE title = 'The Man With The Golden Gun'), UUID_TO_BIN('838d5cd3-6c2d-423b-b10b-04a5cad3d05d')), -- Hervé Villechaize (The Man With The Golden Gun)
    ((SELECT id FROM movies WHERE title = 'The Man With The Golden Gun'), (SELECT id FROM actors WHERE name = 'Richard Loo')),
    ((SELECT id FROM movies WHERE title = 'The Man With The Golden Gun'), (SELECT id FROM actors WHERE name = 'Soon-Tek Oh')),
    ((SELECT id FROM movies WHERE title = 'The Man With The Golden Gun'), UUID_TO_BIN('09b53fdd-bcbd-4feb-997c-3b4454ed17d3')), -- Marc Lawrence (The Man With The Golden Gun)
    ((SELECT id FROM movies WHERE title = 'The Man With The Golden Gun'), (SELECT id FROM actors WHERE name = 'Marne Maitland')),
    ((SELECT id FROM movies WHERE title = 'The Man With The Golden Gun'), (SELECT id FROM actors WHERE name = 'James Cossins')),
    ((SELECT id FROM movies WHERE title = 'The Man With The Golden Gun'), (SELECT id FROM actors WHERE name = 'Yao Lin Chen')),
    ((SELECT id FROM movies WHERE title = 'The Man With The Golden Gun'), (SELECT id FROM actors WHERE name = 'Carmen du Sautoy')),
    ((SELECT id FROM movies WHERE title = 'The Man With The Golden Gun'), UUID_TO_BIN('45dedb1b-a285-48a7-bc75-14de502a9906')), -- Maud Adams (The Man With The Golden Gun)
    ((SELECT id FROM movies WHERE title = 'The Man With The Golden Gun'), (SELECT id FROM actors WHERE name = 'Lois Maxwell'));

/**
  10. The Spy Who Loved Me
 */
INSERT INTO movies_actors (movie_id, actor_id)
VALUES
    ((SELECT id FROM movies WHERE title = 'The Spy Who Loved Me'), (SELECT id FROM actors WHERE name = 'Roger Moore')),
    ((SELECT id FROM movies WHERE title = 'The Spy Who Loved Me'), (SELECT id FROM actors WHERE name = 'Bernard Lee')),
    ((SELECT id FROM movies WHERE title = 'The Spy Who Loved Me'), (SELECT id FROM actors WHERE name = 'Desmond Llewelyn')),
    ((SELECT id FROM movies WHERE title = 'The Spy Who Loved Me'), UUID_TO_BIN('cdd278bb-6e17-4b6d-8587-c5f3436bdf40')), -- Walter Gotell (The spy who loved me, moonraker, for your eyes only, octopussy, a view to a kill, the living daylights)
    ((SELECT id FROM movies WHERE title = 'The Spy Who Loved Me'), UUID_TO_BIN('6671b6e6-0abb-460e-a0d0-137e268ccf99')), -- George Baker (The Spy Who Loved Me)
    ((SELECT id FROM movies WHERE title = 'The Spy Who Loved Me'), (SELECT id FROM actors WHERE name = 'Barbara Bach')),
    ((SELECT id FROM movies WHERE title = 'The Spy Who Loved Me'), UUID_TO_BIN('b2c3d4e5-f6a7-8b9c-0d1e-2f3a4b5c6d7e')), -- Curd Jürgens (The spy who loved me)
    ((SELECT id FROM movies WHERE title = 'The Spy Who Loved Me'), (SELECT id FROM actors WHERE name = 'Caroline Munro')),
    ((SELECT id FROM movies WHERE title = 'The Spy Who Loved Me'), (SELECT id FROM actors WHERE name = 'Michael Billington')),
    ((SELECT id FROM movies WHERE title = 'The Spy Who Loved Me'), (SELECT id FROM actors WHERE name = 'Olga Bisera')),
    ((SELECT id FROM movies WHERE title = 'The Spy Who Loved Me'), (SELECT id FROM actors WHERE name = 'Edward de Souza')),
    ((SELECT id FROM movies WHERE title = 'The Spy Who Loved Me'), (SELECT id FROM actors WHERE name = 'Vernon Dobtcheff')),
    ((SELECT id FROM movies WHERE title = 'The Spy Who Loved Me'), UUID_TO_BIN('d01e2f3a-4b5c-6d7e-8f9a-0b1c2d3e4f5a')), -- Nadim Sawalha (The spy who loved me)
    ((SELECT id FROM movies WHERE title = 'The Spy Who Loved Me'), (SELECT id FROM actors WHERE name = 'Richard Kiel')),
    ((SELECT id FROM movies WHERE title = 'The Spy Who Loved Me'), (SELECT id FROM actors WHERE name = 'Geoffrey Keen')),
    ((SELECT id FROM movies WHERE title = 'The Spy Who Loved Me'), UUID_TO_BIN('a1b2c3d4-e5f6-7a8b-9c0d-0e1f2a3b4c5d')), -- Robert Brown (The Spy Who Loved Me)
    ((SELECT id FROM movies WHERE title = 'The Spy Who Loved Me'), (SELECT id FROM actors WHERE name = 'Lois Maxwell'));

/**
  11. Moonraker
 */
INSERT INTO movies_actors (movie_id, actor_id)
VALUES
    ((SELECT id FROM movies WHERE title = 'Moonraker'), (SELECT id FROM actors WHERE name = 'Roger Moore')),
    ((SELECT id FROM movies WHERE title = 'Moonraker'), (SELECT id FROM actors WHERE name = 'Bernard Lee')),
    ((SELECT id FROM movies WHERE title = 'Moonraker'), (SELECT id FROM actors WHERE name = 'Desmond Llewelyn')),
    ((SELECT id FROM movies WHERE title = 'Moonraker'), UUID_TO_BIN('cdd278bb-6e17-4b6d-8587-c5f3436bdf40')), -- Walter Gotell (The spy who loved me, moonraker, for your eyes only, octopussy, a view to a kill, the living daylights)
    ((SELECT id FROM movies WHERE title = 'Moonraker'), (SELECT id FROM actors WHERE name = 'Richard Kiel')),
    ((SELECT id FROM movies WHERE title = 'Moonraker'), (SELECT id FROM actors WHERE name = 'Geoffrey Keen')),
    ((SELECT id FROM movies WHERE title = 'Moonraker'), (SELECT id FROM actors WHERE name = 'Lois Chiles')),
    ((SELECT id FROM movies WHERE title = 'Moonraker'), (SELECT id FROM actors WHERE name = 'Michael Lonsdale')),
    ((SELECT id FROM movies WHERE title = 'Moonraker'), UUID_TO_BIN('3c4d5e6f-7a8b-9c0d-1e2f-3a4b5c6d7e8f')), -- Corinne Cléry (Moonraker)
    ((SELECT id FROM movies WHERE title = 'Moonraker'), UUID_TO_BIN('4d5e6f7a-8b9c-0d1e-2f3a-4b5c6d7e8f9a')), -- Toshirô Suga (Moonraker)
    ((SELECT id FROM movies WHERE title = 'Moonraker'), (SELECT id FROM actors WHERE name = 'Emily Bolton')),
    ((SELECT id FROM movies WHERE title = 'Moonraker'), (SELECT id FROM actors WHERE name = 'Blanche Ravalec')),
    ((SELECT id FROM movies WHERE title = 'Moonraker'), (SELECT id FROM actors WHERE name = 'Anne Lonnberg')),
    ((SELECT id FROM movies WHERE title = 'Moonraker'), (SELECT id FROM actors WHERE name = 'Lois Maxwell'));

/**
  12. For Your Eyes Only
 */
INSERT INTO movies_actors (movie_id, actor_id)
VALUES
    ((SELECT id FROM movies WHERE title = 'For Your Eyes Only'), (SELECT id FROM actors WHERE name = 'Roger Moore')),
    ((SELECT id FROM movies WHERE title = 'For Your Eyes Only'), (SELECT id FROM actors WHERE name = 'Desmond Llewelyn')),
    ((SELECT id FROM movies WHERE title = 'For Your Eyes Only'), UUID_TO_BIN('cdd278bb-6e17-4b6d-8587-c5f3436bdf40')), -- Walter Gotell (The spy who loved me, moonraker, for your eyes only, octopussy, a view to a kill, the living daylights)
    ((SELECT id FROM movies WHERE title = 'For Your Eyes Only'), (SELECT id FROM actors WHERE name = 'Geoffrey Keen')),
    ((SELECT id FROM movies WHERE title = 'For Your Eyes Only'), (SELECT id FROM actors WHERE name = 'Carole Bouquet')),
    ((SELECT id FROM movies WHERE title = 'For Your Eyes Only'), (SELECT id FROM actors WHERE name = 'Chaim Topol')),
    ((SELECT id FROM movies WHERE title = 'For Your Eyes Only'), (SELECT id FROM actors WHERE name = 'Lynn-Holly Johnson')),
    ((SELECT id FROM movies WHERE title = 'For Your Eyes Only'), (SELECT id FROM actors WHERE name = 'Julian Glover')),
    ((SELECT id FROM movies WHERE title = 'For Your Eyes Only'), (SELECT id FROM actors WHERE name = 'Cassandra Harris')),
    ((SELECT id FROM movies WHERE title = 'For Your Eyes Only'), (SELECT id FROM actors WHERE name = 'Jill Bennett')),
    ((SELECT id FROM movies WHERE title = 'For Your Eyes Only'), (SELECT id FROM actors WHERE name = 'Michael Gothard')),
    ((SELECT id FROM movies WHERE title = 'For Your Eyes Only'), (SELECT id FROM actors WHERE name = 'John Wyman')),
    ((SELECT id FROM movies WHERE title = 'For Your Eyes Only'), (SELECT id FROM actors WHERE name = 'Jack Hedley')),
    ((SELECT id FROM movies WHERE title = 'For Your Eyes Only'), (SELECT id FROM actors WHERE name = 'James Villiers')),
    ((SELECT id FROM movies WHERE title = 'For Your Eyes Only'), (SELECT id FROM actors WHERE name = 'John Moreno')),
    ((SELECT id FROM movies WHERE title = 'For Your Eyes Only'), (SELECT id FROM actors WHERE name = 'Charles Dance')),
    ((SELECT id FROM movies WHERE title = 'For Your Eyes Only'), (SELECT id FROM actors WHERE name = 'Lois Maxwell'));

/**
  13. Octopussy
 */
INSERT INTO movies_actors (movie_id, actor_id)
VALUES
    ((SELECT id FROM movies WHERE title = 'Octopussy'), (SELECT id FROM actors WHERE name = 'Roger Moore')),
    ((SELECT id FROM movies WHERE title = 'Octopussy'), (SELECT id FROM actors WHERE name = 'Desmond Llewelyn')),
    ((SELECT id FROM movies WHERE title = 'Octopussy'), UUID_TO_BIN('cdd278bb-6e17-4b6d-8587-c5f3436bdf40')), -- Walter Gotell (The spy who loved me, moonraker, for your eyes only, octopussy, a view to a kill, the living daylights)
    ((SELECT id FROM movies WHERE title = 'Octopussy'), (SELECT id FROM actors WHERE name = 'Geoffrey Keen')),
    ((SELECT id FROM movies WHERE title = 'Octopussy'), UUID_TO_BIN('4c539789-9dd8-40fa-bd30-35154f502e16')), -- Maud Adams (Octopussy)
    ((SELECT id FROM movies WHERE title = 'Octopussy'), (SELECT id FROM actors WHERE name = 'Louis Jourdan')),
    ((SELECT id FROM movies WHERE title = 'Octopussy'), (SELECT id FROM actors WHERE name = 'Kristina Wayborn')),
    ((SELECT id FROM movies WHERE title = 'Octopussy'), (SELECT id FROM actors WHERE name = 'Kabir Bedi')),
    ((SELECT id FROM movies WHERE title = 'Octopussy'), (SELECT id FROM actors WHERE name = 'Steven Berkoff')),
    ((SELECT id FROM movies WHERE title = 'Octopussy'), (SELECT id FROM actors WHERE name = 'David Meyer')),
    ((SELECT id FROM movies WHERE title = 'Octopussy'), (SELECT id FROM actors WHERE name = 'Anthony Meyer')),
    ((SELECT id FROM movies WHERE title = 'Octopussy'), (SELECT id FROM actors WHERE name = 'Michaela Clavell')),
    ((SELECT id FROM movies WHERE title = 'Octopussy'), (SELECT id FROM actors WHERE name = 'Vijay Amritraj')),
    ((SELECT id FROM movies WHERE title = 'Octopussy'), (SELECT id FROM actors WHERE name = 'Albert Moses')),
    ((SELECT id FROM movies WHERE title = 'Octopussy'), (SELECT id FROM actors WHERE name = 'Douglas Wilmer')),
    ((SELECT id FROM movies WHERE title = 'Octopussy'), (SELECT id FROM actors WHERE name = 'Andy Bradford')),
    ((SELECT id FROM movies WHERE title = 'Octopussy'), UUID_TO_BIN('d8046dca-f263-48b4-bb74-7806ee35685c')), -- Robert Brown (Octopussy, A View To a Kill, The living daylights, License to kill)
    ((SELECT id FROM movies WHERE title = 'Octopussy'), (SELECT id FROM actors WHERE name = 'Lois Maxwell'));

/**
  14. A View To A Kill
 */
INSERT INTO movies_actors (movie_id, actor_id)
VALUES
    ((SELECT id FROM movies WHERE title = 'A View To A Kill'), (SELECT id FROM actors WHERE name = 'Roger Moore')),
    ((SELECT id FROM movies WHERE title = 'A View To A Kill'), (SELECT id FROM actors WHERE name = 'Desmond Llewelyn')),
    ((SELECT id FROM movies WHERE title = 'A View To A Kill'), UUID_TO_BIN('cdd278bb-6e17-4b6d-8587-c5f3436bdf40')), -- Walter Gotell (The spy who loved me, moonraker, for your eyes only, octopussy, a view to a kill, the living daylights)
    ((SELECT id FROM movies WHERE title = 'A View To A Kill'), UUID_TO_BIN('d8046dca-f263-48b4-bb74-7806ee35685c')), -- Robert Brown (Octopussy, A View To a Kill, The living daylights, License to kill)
    ((SELECT id FROM movies WHERE title = 'A View To A Kill'), (SELECT id FROM actors WHERE name = 'Geoffrey Keen')),
    ((SELECT id FROM movies WHERE title = 'A View To A Kill'), (SELECT id FROM actors WHERE name = 'Christopher Walken')),
    ((SELECT id FROM movies WHERE title = 'A View To A Kill'), (SELECT id FROM actors WHERE name = 'Tanya Roberts')),
    ((SELECT id FROM movies WHERE title = 'A View To A Kill'), (SELECT id FROM actors WHERE name = 'Grace Jones')),
    ((SELECT id FROM movies WHERE title = 'A View To A Kill'), (SELECT id FROM actors WHERE name = 'Patrick Macnee')),
    ((SELECT id FROM movies WHERE title = 'A View To A Kill'), (SELECT id FROM actors WHERE name = 'Patrick Bauchau')),
    ((SELECT id FROM movies WHERE title = 'A View To A Kill'), (SELECT id FROM actors WHERE name = 'David Yip')),
    ((SELECT id FROM movies WHERE title = 'A View To A Kill'), (SELECT id FROM actors WHERE name = 'Fiona Fullerton')),
    ((SELECT id FROM movies WHERE title = 'A View To A Kill'), (SELECT id FROM actors WHERE name = 'Manning Redwood')),
    ((SELECT id FROM movies WHERE title = 'A View To A Kill'), (SELECT id FROM actors WHERE name = 'Alison Doody')),
    ((SELECT id FROM movies WHERE title = 'A View To A Kill'), (SELECT id FROM actors WHERE name = 'Willoughby Gray')),
    ((SELECT id FROM movies WHERE title = 'A View To A Kill'), (SELECT id FROM actors WHERE name = 'Jean Rougerie')),
    ((SELECT id FROM movies WHERE title = 'A View To A Kill'), (SELECT id FROM actors WHERE name = 'Daniel Benzali')),
    ((SELECT id FROM movies WHERE title = 'A View To A Kill'), (SELECT id FROM actors WHERE name = 'Lois Maxwell'));

/**
  15. The Living Daylights
 */
INSERT INTO movies_actors (movie_id, actor_id)
VALUES
    ((SELECT id FROM movies WHERE title = 'The Living Daylights'), (SELECT id FROM actors WHERE name = 'Timothy Dalton')),
    ((SELECT id FROM movies WHERE title = 'The Living Daylights'), (SELECT id FROM actors WHERE name = 'Desmond Llewelyn')),
    ((SELECT id FROM movies WHERE title = 'The Living Daylights'), UUID_TO_BIN('cdd278bb-6e17-4b6d-8587-c5f3436bdf40')), -- Walter Gotell (The spy who loved me, moonraker, for your eyes only, octopussy, a view to a kill, the living daylights)
    ((SELECT id FROM movies WHERE title = 'The Living Daylights'), UUID_TO_BIN('d8046dca-f263-48b4-bb74-7806ee35685c')), -- Robert Brown (Octopussy, A View To a Kill, The living daylights, License to kill)
    ((SELECT id FROM movies WHERE title = 'The Living Daylights'), (SELECT id FROM actors WHERE name = 'Geoffrey Keen')),
    ((SELECT id FROM movies WHERE title = 'The Living Daylights'), UUID_TO_BIN('80452376-2f71-403b-9833-4f6b9a541980')), -- Nadim Sawalha (The Living Daylights)
    ((SELECT id FROM movies WHERE title = 'The Living Daylights'), UUID_TO_BIN('7a95027a-f9ad-41da-8591-5ea343a6f90e')), -- Maryam d'Abo (The Living Daylights)
    ((SELECT id FROM movies WHERE title = 'The Living Daylights'), UUID_TO_BIN('91c4b061-cd3e-45e3-80c0-c3669ed4190c')), -- Jeroen Krabbé (The Living Daylights)
    ((SELECT id FROM movies WHERE title = 'The Living Daylights'), UUID_TO_BIN('1b7ff4e7-06be-43d0-b110-051fbc3d84cb')), -- Joe Don Baker (The Living Daylights)
    ((SELECT id FROM movies WHERE title = 'The Living Daylights'), (SELECT id FROM actors WHERE name = 'John Rhys-Davies')),
    ((SELECT id FROM movies WHERE title = 'The Living Daylights'), (SELECT id FROM actors WHERE name = 'Art Malik')),
    ((SELECT id FROM movies WHERE title = 'The Living Daylights'), (SELECT id FROM actors WHERE name = 'Andreas Wisniewski')),
    ((SELECT id FROM movies WHERE title = 'The Living Daylights'), (SELECT id FROM actors WHERE name = 'Thomas Wheatley')),
    ((SELECT id FROM movies WHERE title = 'The Living Daylights'), (SELECT id FROM actors WHERE name = 'John Terry')),
    ((SELECT id FROM movies WHERE title = 'The Living Daylights'), (SELECT id FROM actors WHERE name = 'Virginia Hey')),
    ((SELECT id FROM movies WHERE title = 'The Living Daylights'), (SELECT id FROM actors WHERE name = 'John Bowe')),
    ((SELECT id FROM movies WHERE title = 'The Living Daylights'), (SELECT id FROM actors WHERE name = 'Julie T. Wallace')),
    ((SELECT id FROM movies WHERE title = 'The Living Daylights'), (SELECT id FROM actors WHERE name = 'Caroline Bliss'));

/**
  16. License To Kill
 */
INSERT INTO movies_actors (movie_id, actor_id)
VALUES
    ((SELECT id FROM movies WHERE title = 'License To Kill'), (SELECT id FROM actors WHERE name = 'Timothy Dalton')),
    ((SELECT id FROM movies WHERE title = 'License To Kill'), (SELECT id FROM actors WHERE name = 'Desmond Llewelyn')),
    ((SELECT id FROM movies WHERE title = 'License To Kill'), (SELECT id FROM actors WHERE name = 'David Hedison')),
    ((SELECT id FROM movies WHERE title = 'License To Kill'), UUID_TO_BIN('d8046dca-f263-48b4-bb74-7806ee35685c')), -- Robert Brown (Octopussy, A View To a Kill, The living daylights, License to kill)
    ((SELECT id FROM movies WHERE title = 'License To Kill'), (SELECT id FROM actors WHERE name = 'Robert Davi')),
    ((SELECT id FROM movies WHERE title = 'License To Kill'), (SELECT id FROM actors WHERE name = 'Carey Lowell')),
    ((SELECT id FROM movies WHERE title = 'License To Kill'), (SELECT id FROM actors WHERE name = 'Talisa Soto')),
    ((SELECT id FROM movies WHERE title = 'License To Kill'), (SELECT id FROM actors WHERE name = 'Anthony Zerbe')),
    ((SELECT id FROM movies WHERE title = 'License To Kill'), (SELECT id FROM actors WHERE name = 'Frank McRae')),
    ((SELECT id FROM movies WHERE title = 'License To Kill'), (SELECT id FROM actors WHERE name = 'Everett McGill')),
    ((SELECT id FROM movies WHERE title = 'License To Kill'), (SELECT id FROM actors WHERE name = 'Wayne Newton')),
    ((SELECT id FROM movies WHERE title = 'License To Kill'), (SELECT id FROM actors WHERE name = 'Benicio Del Toro')),
    ((SELECT id FROM movies WHERE title = 'License To Kill'), (SELECT id FROM actors WHERE name = 'Anthony Starke')),
    ((SELECT id FROM movies WHERE title = 'License To Kill'), UUID_TO_BIN('abe80de5-1c4c-4111-9270-3df4fc91ac54')), -- Pedro Armendáriz Jr. (License to kill)
    ((SELECT id FROM movies WHERE title = 'License To Kill'), (SELECT id FROM actors WHERE name = 'Priscilla Barnes')),
    ((SELECT id FROM movies WHERE title = 'License To Kill'), (SELECT id FROM actors WHERE name = 'Don Stroud')),
    ((SELECT id FROM movies WHERE title = 'License To Kill'), (SELECT id FROM actors WHERE name = 'Grand L. Bush')),
    ((SELECT id FROM movies WHERE title = 'License To Kill'), (SELECT id FROM actors WHERE name = 'Caroline Bliss'));

/**
  17. Goldeneye
 */
INSERT INTO movies_actors (movie_id, actor_id)
VALUES
    ((SELECT id FROM movies WHERE title = 'Goldeneye'), (SELECT id FROM actors WHERE name = 'Pierce Brosnan')),
    ((SELECT id FROM movies WHERE title = 'Goldeneye'), (SELECT id FROM actors WHERE name = 'Judi Dench')),
    ((SELECT id FROM movies WHERE title = 'Goldeneye'), (SELECT id FROM actors WHERE name = 'Desmond Llewelyn')),
    ((SELECT id FROM movies WHERE title = 'Goldeneye'), (SELECT id FROM actors WHERE name = 'Sean Bean')),
    ((SELECT id FROM movies WHERE title = 'Goldeneye'), (SELECT id FROM actors WHERE name = 'Izabella Scorupco')),
    ((SELECT id FROM movies WHERE title = 'Goldeneye'), (SELECT id FROM actors WHERE name = 'Famke Janssen')),
    ((SELECT id FROM movies WHERE title = 'Goldeneye'), UUID_TO_BIN('1d8f8986-5bcb-4745-9da2-fc4d21fd8bbb')), -- Tchéky Karyo (Goldeneye)
    ((SELECT id FROM movies WHERE title = 'Goldeneye'), (SELECT id FROM actors WHERE name = 'Gottfried John')),
    ((SELECT id FROM movies WHERE title = 'Goldeneye'), (SELECT id FROM actors WHERE name = 'Alan Cumming')),
    ((SELECT id FROM movies WHERE title = 'Goldeneye'), (SELECT id FROM actors WHERE name = 'Serena Gordon')),
    ((SELECT id FROM movies WHERE title = 'Goldeneye'), (SELECT id FROM actors WHERE name = 'Billy J. Mitchell')),
    ((SELECT id FROM movies WHERE title = 'Goldeneye'), UUID_TO_BIN('fbb22cbe-6e73-4a48-bcda-71254604a27d')), -- Joe Don Baker (Goldeneye, Tomorrow Never Dies)
    ((SELECT id FROM movies WHERE title = 'Goldeneye'), (SELECT id FROM actors WHERE name = 'Michael Kitchen')),
    ((SELECT id FROM movies WHERE title = 'Goldeneye'), (SELECT id FROM actors WHERE name = 'Robbie Coltrane')),
    ((SELECT id FROM movies WHERE title = 'Goldeneye'), (SELECT id FROM actors WHERE name = 'Samantha Bond'));

/**
  18. Tomorrow Never Dies
 */
INSERT INTO movies_actors (movie_id, actor_id)
VALUES
    ((SELECT id FROM movies WHERE title = 'Tomorrow Never Dies'), (SELECT id FROM actors WHERE name = 'Pierce Brosnan')),
    ((SELECT id FROM movies WHERE title = 'Tomorrow Never Dies'), (SELECT id FROM actors WHERE name = 'Judi Dench')),
    ((SELECT id FROM movies WHERE title = 'Tomorrow Never Dies'), (SELECT id FROM actors WHERE name = 'Desmond Llewelyn')),
    ((SELECT id FROM movies WHERE title = 'Tomorrow Never Dies'), UUID_TO_BIN('fbb22cbe-6e73-4a48-bcda-71254604a27d')), -- Joe Don Baker (Goldeneye, Tomorrow Never Dies)
    ((SELECT id FROM movies WHERE title = 'Tomorrow Never Dies'), (SELECT id FROM actors WHERE name = 'Jonathan Pryce')),
    ((SELECT id FROM movies WHERE title = 'Tomorrow Never Dies'), (SELECT id FROM actors WHERE name = 'Michelle Yeoh')),
    ((SELECT id FROM movies WHERE title = 'Tomorrow Never Dies'), (SELECT id FROM actors WHERE name = 'Teri Hatcher')),
    ((SELECT id FROM movies WHERE title = 'Tomorrow Never Dies'), (SELECT id FROM actors WHERE name = 'Ricky Jay')),
    ((SELECT id FROM movies WHERE title = 'Tomorrow Never Dies'), UUID_TO_BIN('8b373885-a994-4607-837d-84ca53b92cd3')), -- Götz Otto (Tomorrow Never Dies)
    ((SELECT id FROM movies WHERE title = 'Tomorrow Never Dies'), (SELECT id FROM actors WHERE name = 'Vincent Schiavelli')),
    ((SELECT id FROM movies WHERE title = 'Tomorrow Never Dies'), (SELECT id FROM actors WHERE name = 'Geoffrey Palmer')),
    ((SELECT id FROM movies WHERE title = 'Tomorrow Never Dies'), (SELECT id FROM actors WHERE name = 'Julian Fellowes')),
    ((SELECT id FROM movies WHERE title = 'Tomorrow Never Dies'), (SELECT id FROM actors WHERE name = 'Terence Rigby')),
    ((SELECT id FROM movies WHERE title = 'Tomorrow Never Dies'), (SELECT id FROM actors WHERE name = 'Cecilie Thomsen')),
    ((SELECT id FROM movies WHERE title = 'Tomorrow Never Dies'), (SELECT id FROM actors WHERE name = 'Nina Young')),
    ((SELECT id FROM movies WHERE title = 'Tomorrow Never Dies'), (SELECT id FROM actors WHERE name = 'Colin Salmon')),
    ((SELECT id FROM movies WHERE title = 'Tomorrow Never Dies'), (SELECT id FROM actors WHERE name = 'Samantha Bond'));

/**
  19. The World Is Not Enough
 */
INSERT INTO movies_actors (movie_id, actor_id)
VALUES
    ((SELECT id FROM movies WHERE title = 'The World Is Not Enough'), (SELECT id FROM actors WHERE name = 'Pierce Brosnan')),
    ((SELECT id FROM movies WHERE title = 'The World Is Not Enough'), (SELECT id FROM actors WHERE name = 'Judi Dench')),
    ((SELECT id FROM movies WHERE title = 'The World Is Not Enough'), (SELECT id FROM actors WHERE name = 'Desmond Llewelyn')),
    ((SELECT id FROM movies WHERE title = 'The World Is Not Enough'), (SELECT id FROM actors WHERE name = 'Michael Kitchen')),
    ((SELECT id FROM movies WHERE title = 'The World Is Not Enough'), (SELECT id FROM actors WHERE name = 'Robbie Coltrane')),
    ((SELECT id FROM movies WHERE title = 'The World Is Not Enough'), (SELECT id FROM actors WHERE name = 'Colin Salmon')),
    ((SELECT id FROM movies WHERE title = 'The World Is Not Enough'), (SELECT id FROM actors WHERE name = 'Sophie Marceau')),
    ((SELECT id FROM movies WHERE title = 'The World Is Not Enough'), (SELECT id FROM actors WHERE name = 'Robert Carlyle')),
    ((SELECT id FROM movies WHERE title = 'The World Is Not Enough'), (SELECT id FROM actors WHERE name = 'Denise Richards')),
    ((SELECT id FROM movies WHERE title = 'The World Is Not Enough'), UUID_TO_BIN('562b23ec-ce34-4854-9b23-65e59c18b533')), -- John Cleese (The World Is Not Enough)
    ((SELECT id FROM movies WHERE title = 'The World Is Not Enough'), (SELECT id FROM actors WHERE name = 'Maria Grazia Cucinotta')),
    ((SELECT id FROM movies WHERE title = 'The World Is Not Enough'), (SELECT id FROM actors WHERE name = 'Goldie')),
    ((SELECT id FROM movies WHERE title = 'The World Is Not Enough'), (SELECT id FROM actors WHERE name = 'David Calder')),
    ((SELECT id FROM movies WHERE title = 'The World Is Not Enough'), (SELECT id FROM actors WHERE name = 'Serena Scott Thomas')),
    ((SELECT id FROM movies WHERE title = 'The World Is Not Enough'), (SELECT id FROM actors WHERE name = 'Ulrich Thomsen')),
    ((SELECT id FROM movies WHERE title = 'The World Is Not Enough'), (SELECT id FROM actors WHERE name = 'John Seru')),
    ((SELECT id FROM movies WHERE title = 'The World Is Not Enough'), (SELECT id FROM actors WHERE name = 'Claude-Oliver Rudolph')),
    ((SELECT id FROM movies WHERE title = 'The World Is Not Enough'), (SELECT id FROM actors WHERE name = 'Samantha Bond'));

/**
  20. Die Another Day
 */
INSERT INTO movies_actors (movie_id, actor_id)
VALUES
    ((SELECT id FROM movies WHERE title = 'Die Another Day'), (SELECT id FROM actors WHERE name = 'Pierce Brosnan')),
    ((SELECT id FROM movies WHERE title = 'Die Another Day'), (SELECT id FROM actors WHERE name = 'Judi Dench')),
    ((SELECT id FROM movies WHERE title = 'Die Another Day'), (SELECT id FROM actors WHERE name = 'Colin Salmon')),
    ((SELECT id FROM movies WHERE title = 'Die Another Day'), UUID_TO_BIN('7d0cd58d-12bd-493e-bc9d-c530944e7063')), -- John Cleese (Die Another Day)
    ((SELECT id FROM movies WHERE title = 'Die Another Day'), (SELECT id FROM actors WHERE name = 'Halle Berry')),
    ((SELECT id FROM movies WHERE title = 'Die Another Day'), (SELECT id FROM actors WHERE name = 'Rosamund Pike')),
    ((SELECT id FROM movies WHERE title = 'Die Another Day'), (SELECT id FROM actors WHERE name = 'Toby Stephens')),
    ((SELECT id FROM movies WHERE title = 'Die Another Day'), (SELECT id FROM actors WHERE name = 'Rick Yune')),
    ((SELECT id FROM movies WHERE title = 'Die Another Day'), (SELECT id FROM actors WHERE name = 'Michael Madsen')),
    ((SELECT id FROM movies WHERE title = 'Die Another Day'), (SELECT id FROM actors WHERE name = 'Will Yun Lee')),
    ((SELECT id FROM movies WHERE title = 'Die Another Day'), (SELECT id FROM actors WHERE name = 'Kenneth Tsang')),
    ((SELECT id FROM movies WHERE title = 'Die Another Day'), UUID_TO_BIN('e3048a5e-775e-4ee0-9505-120ba6064a5a')), -- Emilio Echevarría (Die Another Day)
    ((SELECT id FROM movies WHERE title = 'Die Another Day'), (SELECT id FROM actors WHERE name = 'Michael Gor')),
    ((SELECT id FROM movies WHERE title = 'Die Another Day'), (SELECT id FROM actors WHERE name = 'Lawrence Makoare')),
    ((SELECT id FROM movies WHERE title = 'Die Another Day'), (SELECT id FROM actors WHERE name = 'Rachel Grant')),
    ((SELECT id FROM movies WHERE title = 'Die Another Day'), (SELECT id FROM actors WHERE name = 'Samantha Bond'));

/**
  21. Casino Royale
 */
INSERT INTO movies_actors (movie_id, actor_id)
VALUES
    ((SELECT id FROM movies WHERE title = 'Casino Royale'), (SELECT id FROM actors WHERE name = 'Daniel Craig')),
    ((SELECT id FROM movies WHERE title = 'Casino Royale'), (SELECT id FROM actors WHERE name = 'Eva Green')),
    ((SELECT id FROM movies WHERE title = 'Casino Royale'), (SELECT id FROM actors WHERE name = 'Mads Mikkelsen')),
    ((SELECT id FROM movies WHERE title = 'Casino Royale'), (SELECT id FROM actors WHERE name = 'Caterina Murino')),
    ((SELECT id FROM movies WHERE title = 'Casino Royale'), (SELECT id FROM actors WHERE name = 'Simon Abkarian')),
    ((SELECT id FROM movies WHERE title = 'Casino Royale'), UUID_TO_BIN('3614144b-6a18-48ab-baf0-0d53674e2605')), -- Isaach De Bankolé (Casino Royale)
    ((SELECT id FROM movies WHERE title = 'Casino Royale'), (SELECT id FROM actors WHERE name = 'Ivana Milicevic')),
    ((SELECT id FROM movies WHERE title = 'Casino Royale'), (SELECT id FROM actors WHERE name = 'Tobias Menzies')),
    ((SELECT id FROM movies WHERE title = 'Casino Royale'), (SELECT id FROM actors WHERE name = 'Claudio Santamaria')),
    ((SELECT id FROM movies WHERE title = 'Casino Royale'), (SELECT id FROM actors WHERE name = 'Sebastien Foucan')),
    ((SELECT id FROM movies WHERE title = 'Casino Royale'), (SELECT id FROM actors WHERE name = 'Malcolm Sinclair')),
    ((SELECT id FROM movies WHERE title = 'Casino Royale'), (SELECT id FROM actors WHERE name = 'Richard Sammel')),
    ((SELECT id FROM movies WHERE title = 'Casino Royale'), (SELECT id FROM actors WHERE name = 'Ludger Pistor')),
    ((SELECT id FROM movies WHERE title = 'Casino Royale'), (SELECT id FROM actors WHERE name = 'Joseph Millson')),
    ((SELECT id FROM movies WHERE title = 'Casino Royale'), (SELECT id FROM actors WHERE name = 'Jeffrey Wright')),
    ((SELECT id FROM movies WHERE title = 'Casino Royale'), (SELECT id FROM actors WHERE name = 'Jesper Christensen')),
    ((SELECT id FROM movies WHERE title = 'Casino Royale'), (SELECT id FROM actors WHERE name = 'Giancarlo Giannini')),
    ((SELECT id FROM movies WHERE title = 'Casino Royale'), (SELECT id FROM actors WHERE name = 'Judi Dench'));

/**
  22. Quantum Of Solace
 */
INSERT INTO movies_actors (movie_id, actor_id)
VALUES
    ((SELECT id FROM movies WHERE title = 'Quantum Of Solace'), (SELECT id FROM actors WHERE name = 'Daniel Craig')),
    ((SELECT id FROM movies WHERE title = 'Quantum Of Solace'), (SELECT id FROM actors WHERE name = 'Jeffrey Wright')),
    ((SELECT id FROM movies WHERE title = 'Quantum Of Solace'), (SELECT id FROM actors WHERE name = 'Jesper Christensen')),
    ((SELECT id FROM movies WHERE title = 'Quantum Of Solace'), (SELECT id FROM actors WHERE name = 'Giancarlo Giannini')),
    ((SELECT id FROM movies WHERE title = 'Quantum Of Solace'), (SELECT id FROM actors WHERE name = 'Olga Kurylenko')),
    ((SELECT id FROM movies WHERE title = 'Quantum Of Solace'), (SELECT id FROM actors WHERE name = 'Mathieu Amalric')),
    ((SELECT id FROM movies WHERE title = 'Quantum Of Solace'), (SELECT id FROM actors WHERE name = 'Gemma Arterton')),
    ((SELECT id FROM movies WHERE title = 'Quantum Of Solace'), (SELECT id FROM actors WHERE name = 'David Harbour')),
    ((SELECT id FROM movies WHERE title = 'Quantum Of Solace'), (SELECT id FROM actors WHERE name = 'Anatole Taubman')),
    ((SELECT id FROM movies WHERE title = 'Quantum Of Solace'), (SELECT id FROM actors WHERE name = 'Tim Pigott-Smith')),
    ((SELECT id FROM movies WHERE title = 'Quantum Of Solace'), UUID_TO_BIN('ff5217a6-93f6-46d2-85dc-2a5590faad77')), -- Joaquín Cosio (Quantum Of Solace)
    ((SELECT id FROM movies WHERE title = 'Quantum Of Solace'), UUID_TO_BIN('205bbeb9-d065-4e50-b3e2-372a43bb02bb')), -- Fernando Guillén Cuervo (Quantum Of Solace)
    ((SELECT id FROM movies WHERE title = 'Quantum Of Solace'), UUID_TO_BIN('d077319f-08fc-41ff-9dad-232bb738f98b')), -- Jesús Ochoa (Quantum Of Solace)
    ((SELECT id FROM movies WHERE title = 'Quantum Of Solace'), (SELECT id FROM actors WHERE name = 'Lucrezia Lante della Rovere')),
    ((SELECT id FROM movies WHERE title = 'Quantum Of Solace'), (SELECT id FROM actors WHERE name = 'Glenn Foster')),
    ((SELECT id FROM movies WHERE title = 'Quantum Of Solace'), (SELECT id FROM actors WHERE name = 'Paul Ritter')),
    ((SELECT id FROM movies WHERE title = 'Quantum Of Solace'), (SELECT id FROM actors WHERE name = 'Rory Kinnear')),
    ((SELECT id FROM movies WHERE title = 'Quantum Of Solace'), (SELECT id FROM actors WHERE name = 'Judi Dench'));

/**
  23. Skyfall
 */
INSERT INTO movies_actors (movie_id, actor_id)
VALUES
    ((SELECT id FROM movies WHERE title = 'Skyfall'), (SELECT id FROM actors WHERE name = 'Daniel Craig')),
    ((SELECT id FROM movies WHERE title = 'Skyfall'), (SELECT id FROM actors WHERE name = 'Rory Kinnear')),
    ((SELECT id FROM movies WHERE title = 'Skyfall'), (SELECT id FROM actors WHERE name = 'Javier Bardem')),
    ((SELECT id FROM movies WHERE title = 'Skyfall'), UUID_TO_BIN('9fa1dd80-94d0-4f0c-ad79-b29f7f52ac35')), -- Bérénice Marlohe (Skyfall)
    ((SELECT id FROM movies WHERE title = 'Skyfall'), (SELECT id FROM actors WHERE name = 'Albert Finney')),
    ((SELECT id FROM movies WHERE title = 'Skyfall'), (SELECT id FROM actors WHERE name = 'Ola Rapace')),
    ((SELECT id FROM movies WHERE title = 'Skyfall'), (SELECT id FROM actors WHERE name = 'Helen McCrory')),
    ((SELECT id FROM movies WHERE title = 'Skyfall'), (SELECT id FROM actors WHERE name = 'Nicholas Woodeson')),
    ((SELECT id FROM movies WHERE title = 'Skyfall'), (SELECT id FROM actors WHERE name = 'Bill Buckhurst')),
    ((SELECT id FROM movies WHERE title = 'Skyfall'), (SELECT id FROM actors WHERE name = 'Elize du Toit')),
    ((SELECT id FROM movies WHERE title = 'Skyfall'), (SELECT id FROM actors WHERE name = 'Ben Whishaw')),
    ((SELECT id FROM movies WHERE title = 'Skyfall'), (SELECT id FROM actors WHERE name = 'Naomie Harris')),
    ((SELECT id FROM movies WHERE title = 'Skyfall'), UUID_TO_BIN('17ce05f5-1f39-4d6d-a57f-c6974afde0ef')), -- Ralph Fiennes (Skyfall)
    ((SELECT id FROM movies WHERE title = 'Skyfall'), (SELECT id FROM actors WHERE name = 'Judi Dench'));

/**
  24. Spectre
 */
INSERT INTO movies_actors (movie_id, actor_id)
VALUES
    ((SELECT id FROM movies WHERE title = 'Spectre'), (SELECT id FROM actors WHERE name = 'Daniel Craig')),
    ((SELECT id FROM movies WHERE title = 'Spectre'), (SELECT id FROM actors WHERE name = 'Rory Kinnear')),
    ((SELECT id FROM movies WHERE title = 'Spectre'), (SELECT id FROM actors WHERE name = 'Ben Whishaw')),
    ((SELECT id FROM movies WHERE title = 'Spectre'), (SELECT id FROM actors WHERE name = 'Naomie Harris')),
    ((SELECT id FROM movies WHERE title = 'Spectre'), UUID_TO_BIN('3da9300b-1cd7-4cf8-ab81-542f19bc865b')), -- Ralph Fiennes (Spectre, No Time To Die)
    ((SELECT id FROM movies WHERE title = 'Spectre'), (SELECT id FROM actors WHERE name = 'Monica Bellucci')),
    ((SELECT id FROM movies WHERE title = 'Spectre'), (SELECT id FROM actors WHERE name = 'Dave Bautista')),
    ((SELECT id FROM movies WHERE title = 'Spectre'), (SELECT id FROM actors WHERE name = 'Andrew Scott')),
    ((SELECT id FROM movies WHERE title = 'Spectre'), (SELECT id FROM actors WHERE name = 'Alessandro Cremona')),
    ((SELECT id FROM movies WHERE title = 'Spectre'), (SELECT id FROM actors WHERE name = 'Stephanie Sigman')),
    ((SELECT id FROM movies WHERE title = 'Spectre'), (SELECT id FROM actors WHERE name = 'Domenico Fortunato')),
    ((SELECT id FROM movies WHERE title = 'Spectre'), (SELECT id FROM actors WHERE name = 'Christoph Waltz')),
    ((SELECT id FROM movies WHERE title = 'Spectre'), UUID_TO_BIN('fd485a8b-d86a-4cec-a03d-4bfe3b12cab0')), -- Léa Seydoux (Spectre, No Time To Die)
    ((SELECT id FROM movies WHERE title = 'Spectre'), (SELECT id FROM actors WHERE name = 'Jesper Christensen'));

/**
  25. No Time To Die
 */
INSERT INTO movies_actors (movie_id, actor_id)
VALUES
    ((SELECT id FROM movies WHERE title = 'No Time To Die'), (SELECT id FROM actors WHERE name = 'Daniel Craig')),
    ((SELECT id FROM movies WHERE title = 'No Time To Die'), (SELECT id FROM actors WHERE name = 'Rory Kinnear')),
    ((SELECT id FROM movies WHERE title = 'No Time To Die'), (SELECT id FROM actors WHERE name = 'Ben Whishaw')),
    ((SELECT id FROM movies WHERE title = 'No Time To Die'), (SELECT id FROM actors WHERE name = 'Naomie Harris')),
    ((SELECT id FROM movies WHERE title = 'No Time To Die'), UUID_TO_BIN('3da9300b-1cd7-4cf8-ab81-542f19bc865b')), -- Ralph Fiennes (Spectre, No Time To Die)
    ((SELECT id FROM movies WHERE title = 'No Time To Die'), (SELECT id FROM actors WHERE name = 'Christoph Waltz')),
    ((SELECT id FROM movies WHERE title = 'No Time To Die'), UUID_TO_BIN('fd485a8b-d86a-4cec-a03d-4bfe3b12cab0')), -- Léa Seydoux (Spectre, No Time To Die)
    ((SELECT id FROM movies WHERE title = 'No Time To Die'), (SELECT id FROM actors WHERE name = 'Ana de Armas')),
    ((SELECT id FROM movies WHERE title = 'No Time To Die'), (SELECT id FROM actors WHERE name = 'Rami Malek')),
    ((SELECT id FROM movies WHERE title = 'No Time To Die'), (SELECT id FROM actors WHERE name = 'Lashana Lynch')),
    ((SELECT id FROM movies WHERE title = 'No Time To Die'), (SELECT id FROM actors WHERE name = 'Billy Magnussen')),
    ((SELECT id FROM movies WHERE title = 'No Time To Die'), (SELECT id FROM actors WHERE name = 'David Dencik')),
    ((SELECT id FROM movies WHERE title = 'No Time To Die'), (SELECT id FROM actors WHERE name = 'Dali Benssalah')),
    ((SELECT id FROM movies WHERE title = 'No Time To Die'), (SELECT id FROM actors WHERE name = 'Lisa-Dorah Sonnet')),
    ((SELECT id FROM movies WHERE title = 'No Time To Die'), (SELECT id FROM actors WHERE name = 'Coline Defaud')),
    ((SELECT id FROM movies WHERE title = 'No Time To Die'), (SELECT id FROM actors WHERE name = 'Mathilde Bourbin')),
    ((SELECT id FROM movies WHERE title = 'No Time To Die'), (SELECT id FROM actors WHERE name = 'Hugh Dennis')),
    ((SELECT id FROM movies WHERE title = 'No Time To Die'), (SELECT id FROM actors WHERE name = 'Jeffrey Wright'));