--
-- Sample dataset containing a number of Hotels in various Cities across the world.  The reviews are entirely fictional :)
--

-- =================================================================================================
-- AUSTRALIA

-- Brisbane
insert into transmission_line(name,capacity) values ('SOUTHERN',80)
insert into generation(name, generation_level, transmission_line_id) values ('SD1', 20.0, 1)
insert into transmission_line(name,capacity) values ('NORTHERN',120)
insert into generation(name, generation_level, transmission_line_id) values ('KH1', 60.0, 2)
insert into generation(name, generation_level, transmission_line_id) values ('KH2', 40.0, 2)
