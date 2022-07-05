-- liquibase formatted sql

-- changeset ekarpukhin:1
CREATE INDEX student_idx ON student (name);

-- changeset ekarpukhin:2
CREATE INDEX faculty_idx ON faculty (name, color);
