-- /**
--   Release dates for all James Bond movies
--  */
-- INSERT INTO release_dates (id, date_of_release, country, country_code, movie_id, created_at, updated_at)
-- VALUES (
--        UUID_TO_BIN(''),
--        '',
--        '',
--        '',
--        UUID_TO_BIN(''),
--        CURRENT_TIMESTAMP(6),
--        CURRENT_TIMESTAMP(6)
-- ),(
--     UUID_TO_BIN(''),
--     '',
--     '',
--     '',
--     UUID_TO_BIN(''),
--     CURRENT_TIMESTAMP(6),
--     CURRENT_TIMESTAMP(6)
-- );


/**
  Release dates for all James Bond movies (UK and US)
 */

-- 1. Dr. No
INSERT INTO release_dates (id, date_of_release, country, country_code, movie_id, created_at, updated_at)
VALUES (
           UUID_TO_BIN('0a878e29-31fb-4bd0-bca6-a04c0ba977ec'),
           '1962-10-06',
           'United Kingdom',
           'UK',
           UUID_TO_BIN('18fda8f7-10a4-4ad4-84a9-4573dd8704d4'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       ),(
           UUID_TO_BIN('323ae68f-a417-4331-bc78-02898426c3db'),
           '1963-05-08',
           'United States',
           'US',
           UUID_TO_BIN('18fda8f7-10a4-4ad4-84a9-4573dd8704d4'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

-- 2. From Russia with Love
INSERT INTO release_dates (id, date_of_release, country, country_code, movie_id, created_at, updated_at)
VALUES (
           UUID_TO_BIN('57ae9154-8f8f-49c1-a1fe-9f47fd67f103'),
           '1963-10-10',
           'United Kingdom',
           'UK',
           UUID_TO_BIN('8316df48-88e6-43e3-aaa7-1c0d1ff93f82'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       ),(
           UUID_TO_BIN('f446b768-198c-499f-b500-148f067f94c0'),
           '1964-04-08',
           'United States',
           'US',
           UUID_TO_BIN('8316df48-88e6-43e3-aaa7-1c0d1ff93f82'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

-- 3. Goldfinger
INSERT INTO release_dates (id, date_of_release, country, country_code, movie_id, created_at, updated_at)
VALUES (
           UUID_TO_BIN('9eb9dc23-7408-426b-b924-c85fb9d11c45'),
           '1964-09-17',
           'United Kingdom',
           'UK',
           UUID_TO_BIN('2dc921ab-7bb2-4fc1-a3f9-ea22bc73094e'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       ),(
           UUID_TO_BIN('a278a532-e7eb-4ce6-90d2-31b43beb8213'),
           '1964-12-22',
           'United States',
           'US',
           UUID_TO_BIN('2dc921ab-7bb2-4fc1-a3f9-ea22bc73094e'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

-- 4. Thunderball
INSERT INTO release_dates (id, date_of_release, country, country_code, movie_id, created_at, updated_at)
VALUES (
           UUID_TO_BIN('4bc9df33-99cf-4d1c-9d90-492e427fbf88'),
           '1965-12-29',
           'United Kingdom',
           'UK',
           UUID_TO_BIN('5a914395-9ddc-451e-8231-f14cc3490d94'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       ),(
           UUID_TO_BIN('36daa339-4e57-4a3b-9312-b0beb4e0a5e0'),
           '1965-12-21',
           'United States',
           'US',
           UUID_TO_BIN('5a914395-9ddc-451e-8231-f14cc3490d94'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

-- 5. You Only Live Twice
INSERT INTO release_dates (id, date_of_release, country, country_code, movie_id, created_at, updated_at)
VALUES (
           UUID_TO_BIN('ec38f870-7637-40e7-aa87-99c44f74804f'),
           '1967-06-12',
           'United Kingdom',
           'UK',
           UUID_TO_BIN('b545a06f-025d-4dc6-b454-bc13782630ab'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       ),(
           UUID_TO_BIN('8389cc8d-5535-4786-bb37-7f6f30d030c7'),
           '1967-06-13',
           'United States',
           'US',
           UUID_TO_BIN('b545a06f-025d-4dc6-b454-bc13782630ab'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

-- 6. On Her Majesty's Secret Service
INSERT INTO release_dates (id, date_of_release, country, country_code, movie_id, created_at, updated_at)
VALUES (
           UUID_TO_BIN('97636011-1a13-4f2e-9b71-347ca5563787'),
           '1969-12-18',
           'United Kingdom',
           'UK',
           UUID_TO_BIN('4a3515f9-577b-412c-8123-53f232e239ee'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       ),(
           UUID_TO_BIN('35ad0849-c3b1-4aaa-bb2e-2a8170de1eed'),
           '1969-12-18',
           'United States',
           'US',
           UUID_TO_BIN('4a3515f9-577b-412c-8123-53f232e239ee'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

-- 7. Diamonds Are Forever
INSERT INTO release_dates (id, date_of_release, country, country_code, movie_id, created_at, updated_at)
VALUES (
           UUID_TO_BIN('3ea2aeed-47ab-4cbf-9fb0-3a6095df3a99'),
           '1971-12-30',
           'United Kingdom',
           'UK',
           UUID_TO_BIN('9c415115-9be6-4f4f-8495-6f26b4fc60cb'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       ),(
           UUID_TO_BIN('ec6aa56d-51ef-4754-ab7c-8621b846046f'),
           '1971-12-17',
           'United States',
           'US',
           UUID_TO_BIN('9c415115-9be6-4f4f-8495-6f26b4fc60cb'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

-- 8. Live and Let Die
INSERT INTO release_dates (id, date_of_release, country, country_code, movie_id, created_at, updated_at)
VALUES (
           UUID_TO_BIN('46ffc792-0079-48bc-8bb6-c9790d454dfb'),
           '1973-07-06',
           'United Kingdom',
           'UK',
           UUID_TO_BIN('bcbf8703-14fe-4999-a2cd-2336b91ca7a5'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       ),(
           UUID_TO_BIN('c041ba86-3168-428d-b713-8636a0f21f95'),
           '1973-06-27',
           'United States',
           'US',
           UUID_TO_BIN('bcbf8703-14fe-4999-a2cd-2336b91ca7a5'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

-- 9. The Man with the Golden Gun
INSERT INTO release_dates (id, date_of_release, country, country_code, movie_id, created_at, updated_at)
VALUES (
           UUID_TO_BIN('aee38b41-6cfe-4dca-9ddb-f9188f60e779'),
           '1974-12-19',
           'United Kingdom',
           'UK',
           UUID_TO_BIN('3a6b6e28-5e31-4931-8c6f-8a5694877e54'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       ),(
           UUID_TO_BIN('9e05ba75-7ff4-4790-89bc-bd047ec6daeb'),
           '1974-12-19',
           'United States',
           'US',
           UUID_TO_BIN('3a6b6e28-5e31-4931-8c6f-8a5694877e54'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

-- 10. The Spy Who Loved Me
INSERT INTO release_dates (id, date_of_release, country, country_code, movie_id, created_at, updated_at)
VALUES (
           UUID_TO_BIN('f66b5bb9-2c19-4ce0-ab14-64244bb6e98d'),
           '1977-07-07',
           'United Kingdom',
           'UK',
           UUID_TO_BIN('7774936e-04e5-4609-aa1d-a7b26d782947'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       ),(
           UUID_TO_BIN('358e501a-cdd2-4eba-968a-4b96ea6c3d86'),
           '1977-07-13',
           'United States',
           'US',
           UUID_TO_BIN('7774936e-04e5-4609-aa1d-a7b26d782947'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

-- 11. Moonraker
INSERT INTO release_dates (id, date_of_release, country, country_code, movie_id, created_at, updated_at)
VALUES (
           UUID_TO_BIN('a72b260f-f0c7-4494-8809-3b8880ef5d7a'),
           '1979-06-26',
           'United Kingdom',
           'UK',
           UUID_TO_BIN('3f511a7c-ee4c-4560-a621-2bd6b1b9faa8'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       ),(
           UUID_TO_BIN('d6fe50de-de7f-4d44-ba9e-968bf8885c5d'),
           '1979-06-29',
           'United States',
           'US',
           UUID_TO_BIN('3f511a7c-ee4c-4560-a621-2bd6b1b9faa8'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

-- 12. For Your Eyes Only
INSERT INTO release_dates (id, date_of_release, country, country_code, movie_id, created_at, updated_at)
VALUES (
           UUID_TO_BIN('65065a6d-961f-42a4-a243-377be0c9c1b6'),
           '1981-06-24',
           'United Kingdom',
           'UK',
           UUID_TO_BIN('3eb1763c-ad84-401e-b877-aa687acf7549'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       ),(
           UUID_TO_BIN('e473afcc-4498-49ac-a1e4-cbcc756877b2'),
           '1981-06-26',
           'United States',
           'US',
           UUID_TO_BIN('3eb1763c-ad84-401e-b877-aa687acf7549'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

-- 13. Octopussy
INSERT INTO release_dates (id, date_of_release, country, country_code, movie_id, created_at, updated_at)
VALUES (
           UUID_TO_BIN('9f6fedbf-c721-4797-811d-5e68086a3222'),
           '1983-06-06',
           'United Kingdom',
           'UK',
           UUID_TO_BIN('2375219d-1fc8-41f1-b47f-d8ee6b0edd6e'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       ),(
           UUID_TO_BIN('06effcb1-6be8-482e-a7c2-4f87002f52d3'),
           '1983-06-10',
           'United States',
           'US',
           UUID_TO_BIN('2375219d-1fc8-41f1-b47f-d8ee6b0edd6e'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

-- 14. A View to a Kill
INSERT INTO release_dates (id, date_of_release, country, country_code, movie_id, created_at, updated_at)
VALUES (
           UUID_TO_BIN('1d95a806-2024-47fb-bdef-72eec8638e4a'),
           '1985-06-13',
           'United Kingdom',
           'UK',
           UUID_TO_BIN('c34f1b7b-3010-4155-8c40-bb4efb20dfb2'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       ),(
           UUID_TO_BIN('4262d0d0-4fe8-4351-a227-57364c5b83cd'),
           '1985-05-24',
           'United States',
           'US',
           UUID_TO_BIN('c34f1b7b-3010-4155-8c40-bb4efb20dfb2'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

-- 15. The Living Daylights
INSERT INTO release_dates (id, date_of_release, country, country_code, movie_id, created_at, updated_at)
VALUES (
           UUID_TO_BIN('3a295806-3eff-488d-8079-fbac4bec1efd'),
           '1987-06-29',
           'United Kingdom',
           'UK',
           UUID_TO_BIN('dc7940f8-ad41-42b8-bf5d-da25e99752e4'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       ),(
           UUID_TO_BIN('566b82a8-aaa0-4772-aa63-3c2943fdc3c0'),
           '1987-07-31',
           'United States',
           'US',
           UUID_TO_BIN('dc7940f8-ad41-42b8-bf5d-da25e99752e4'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

-- 16. Licence to Kill
INSERT INTO release_dates (id, date_of_release, country, country_code, movie_id, created_at, updated_at)
VALUES (
           UUID_TO_BIN('b8630bcd-05f1-48e1-b5a8-c16b951a2819'),
           '1989-06-13',
           'United Kingdom',
           'UK',
           UUID_TO_BIN('179f3409-d39d-437d-b998-79f150ee1b30'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       ),(
           UUID_TO_BIN('29a17374-4bac-4c07-8b0d-2056463c4fad'),
           '1989-07-14',
           'United States',
           'US',
           UUID_TO_BIN('179f3409-d39d-437d-b998-79f150ee1b30'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

-- 17. GoldenEye
INSERT INTO release_dates (id, date_of_release, country, country_code, movie_id, created_at, updated_at)
VALUES (
           UUID_TO_BIN('8cf63472-f08d-4c89-984e-dc17f20d1557'),
           '1995-11-24',
           'United Kingdom',
           'UK',
           UUID_TO_BIN('06d701ab-f89f-4b57-9c6e-f43628cc4e6d'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       ),(
           UUID_TO_BIN('18dd7b0b-17a3-4797-9e7b-794e4ac5813e'),
           '1995-11-17',
           'United States',
           'US',
           UUID_TO_BIN('06d701ab-f89f-4b57-9c6e-f43628cc4e6d'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

-- 18. Tomorrow Never Dies
INSERT INTO release_dates (id, date_of_release, country, country_code, movie_id, created_at, updated_at)
VALUES (
           UUID_TO_BIN('6297a8d1-9a8d-4fb2-95b1-46d451d3ef84'),
           '1997-12-12',
           'United Kingdom',
           'UK',
           UUID_TO_BIN('28b1807d-93cc-4078-ad3a-44f00375d3dd'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       ),(
           UUID_TO_BIN('b119516e-081a-4d83-91dc-d38fbc28767c'),
           '1997-12-19',
           'United States',
           'US',
           UUID_TO_BIN('28b1807d-93cc-4078-ad3a-44f00375d3dd'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

-- 19. The World Is Not Enough
INSERT INTO release_dates (id, date_of_release, country, country_code, movie_id, created_at, updated_at)
VALUES (
           UUID_TO_BIN('2ee641d1-7d83-4e68-82e6-cfb74dcd3c7f'),
           '1999-11-26',
           'United Kingdom',
           'UK',
           UUID_TO_BIN('8ddc2a5a-c855-4768-9b70-3a3d383e621b'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       ),(
           UUID_TO_BIN('a2f9daae-833e-4bbf-87c2-1541a95160ef'),
           '1999-11-19',
           'United States',
           'US',
           UUID_TO_BIN('8ddc2a5a-c855-4768-9b70-3a3d383e621b'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

-- 20. Die Another Day
INSERT INTO release_dates (id, date_of_release, country, country_code, movie_id, created_at, updated_at)
VALUES (
           UUID_TO_BIN('670c9f7a-ae02-4b10-94a7-9b56d108eae9'),
           '2002-11-20',
           'United Kingdom',
           'UK',
           UUID_TO_BIN('f555fa79-8c7a-46df-b178-649ae651065f'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       ),(
           UUID_TO_BIN('4d61bd0e-dc1b-424a-837a-bb6a86e8d90c'),
           '2002-11-22',
           'United States',
           'US',
           UUID_TO_BIN('f555fa79-8c7a-46df-b178-649ae651065f'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

-- 21. Casino Royale (2006)
INSERT INTO release_dates (id, date_of_release, country, country_code, movie_id, created_at, updated_at)
VALUES (
           UUID_TO_BIN('106b0c04-01bb-4887-b8fc-6824d90cf0a5'),
           '2006-11-16',
           'United Kingdom',
           'UK',
           UUID_TO_BIN('8218a578-0622-4d75-a86c-1501a3e48afc'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       ),(
           UUID_TO_BIN('e8ccfc78-a5e0-4aef-b2b1-ccef57a20f44'),
           '2006-11-17',
           'United States',
           'US',
           UUID_TO_BIN('8218a578-0622-4d75-a86c-1501a3e48afc'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

-- 22. Quantum of Solace
INSERT INTO release_dates (id, date_of_release, country, country_code, movie_id, created_at, updated_at)
VALUES (
           UUID_TO_BIN('5edcbb7a-c09e-4db8-b2f2-8222b245f062'),
           '2008-10-31',
           'United Kingdom',
           'UK',
           UUID_TO_BIN('32b40321-da45-4ff3-b417-2b2d79c10de9'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       ),(
           UUID_TO_BIN('71af8ca9-57a6-4161-a96d-88f2dffdd5ab'),
           '2008-11-14',
           'United States',
           'US',
           UUID_TO_BIN('32b40321-da45-4ff3-b417-2b2d79c10de9'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

-- 23. Skyfall
INSERT INTO release_dates (id, date_of_release, country, country_code, movie_id, created_at, updated_at)
VALUES (
           UUID_TO_BIN('f1661d80-2eb7-4c3d-a0a7-e789925a7ebb'),
           '2012-10-26',
           'United Kingdom',
           'UK',
           UUID_TO_BIN('2a85118c-7fd2-4096-9668-01fd8320ab17'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       ),(
           UUID_TO_BIN('b79321d5-bacf-43e3-b468-9de9e4621c31'),
           '2012-11-09',
           'United States',
           'US',
           UUID_TO_BIN('2a85118c-7fd2-4096-9668-01fd8320ab17'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

-- 24. Spectre
INSERT INTO release_dates (id, date_of_release, country, country_code, movie_id, created_at, updated_at)
VALUES (
           UUID_TO_BIN('faf05386-ca57-4584-8de7-9fbc67ae6b51'),
           '2015-10-26',
           'United Kingdom',
           'UK',
           UUID_TO_BIN('0d7d18ba-04a5-4f64-a720-8cbf39692862'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       ),(
           UUID_TO_BIN('2d0efb34-c0b3-4594-8831-4d7949cc8158'),
           '2015-11-06',
           'United States',
           'US',
           UUID_TO_BIN('0d7d18ba-04a5-4f64-a720-8cbf39692862'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );

-- 25. No Time to Die
INSERT INTO release_dates (id, date_of_release, country, country_code, movie_id, created_at, updated_at)
VALUES (
           UUID_TO_BIN('e852faa2-6587-46fa-afbc-d212001b99bd'),
           '2021-09-30',
           'United Kingdom',
           'UK',
           UUID_TO_BIN('f1930593-6cae-45d0-a063-84582ac10fac'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       ),(
           UUID_TO_BIN('da603d37-2183-4df8-8449-d0912e3b44b5'),
           '2021-10-08',
           'United States',
           'US',
           UUID_TO_BIN('f1930593-6cae-45d0-a063-84582ac10fac'),
           CURRENT_TIMESTAMP(6),
           CURRENT_TIMESTAMP(6)
       );