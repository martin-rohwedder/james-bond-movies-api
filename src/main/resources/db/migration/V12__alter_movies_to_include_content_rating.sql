/**
  Alter table movies to include column for content rating
 */
ALTER TABLE movies
ADD COLUMN content_rating VARCHAR(10) AFTER world_premiere;

/**
  Update content rating for every movie
 */
UPDATE movies
SET content_rating = CASE
-- PG Movies
WHEN title IN (
            'Dr. No', 'From Russia with Love', 'Goldfinger', 'Thunderball',
            'You Only Live Twice', 'Diamonds Are Forever', 'On Her Majesty''s Secret Service',
            'Live and Let Die', 'The Man with the Golden Gun', 'The Spy Who Loved Me',
            'Moonraker', 'For Your Eyes Only', 'Octopussy', 'A View to a Kill',
            'The Living Daylights'
 ) THEN 'PG'

-- PG-13 Movies
WHEN title IN (
            'License To Kill', 'GoldenEye', 'Tomorrow Never Dies', 'The World Is Not Enough',
            'Die Another Day', 'Casino Royale', 'Quantum of Solace', 'Skyfall',
            'Spectre', 'No Time to Die'
 ) THEN 'PG-13'

-- Keeps the current rating if the movie is not a Bond film
ELSE content_rating
END
WHERE title IN (
'Dr. No', 'From Russia with Love', 'Goldfinger', 'Thunderball',
'You Only Live Twice', 'Diamonds Are Forever', 'On Her Majesty''s Secret Service',
'Live and Let Die', 'The Man with the Golden Gun', 'The Spy Who Loved Me',
'Moonraker', 'For Your Eyes Only', 'Octopussy', 'A View to a Kill',
'The Living Daylights', 'License To Kill', 'GoldenEye', 'Tomorrow Never Dies',
'The World Is Not Enough', 'Die Another Day', 'Casino Royale', 'Quantum of Solace',
'Skyfall', 'Spectre', 'No Time to Die'
);