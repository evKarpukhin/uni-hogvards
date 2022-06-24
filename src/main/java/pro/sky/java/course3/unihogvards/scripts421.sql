--Возраст студента не может быть меньше 16 лет.
ALTER TABLE student ADD CONSTRAINT age_constraint CHECK (age > 0);

--Имена студентов должны быть уникальными и не равны нулю.
ALTER TABLE student ALTER COLUMN name UNIQUE NOT NULL;

-- Пара “значение названия” - “цвет факультета” должна быть уникальной.
ALTER TABLE faculty ADD CONSTRAINT mame_color_unique UNIQUE (name, color);

--При создании студента без возраста ему автоматически должно присваиваться 20 лет.
CREATE TABLE student
(
    ...
    age INTEGER DEFAULT 20,
    ...
);


