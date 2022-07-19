--liquibase formatted sql

--changeset bakulin:1
   CREATE INDEX students_name_index ON student(name);

--changeset bakulin:2
CREATE INDEX faculty_name_color_index ON faculty(name,color);
