/**
  Add UNIQUE constraints to the foreign key columns for box office id, technical specifications id and parents guide id.
 */

ALTER TABLE movies
ADD CONSTRAINT uk_movies_box_office_id UNIQUE (box_office_id);

ALTER TABLE movies
ADD CONSTRAINT uk_movies_technical_specifications_id UNIQUE (technical_specifications_id);

ALTER TABLE movies
ADD CONSTRAINT uk_movies_parents_guide_id UNIQUE (parents_guide_id);
