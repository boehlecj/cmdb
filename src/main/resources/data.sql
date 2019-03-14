insert into director(`director_id`, `first_name`, `last_name`)
values (1, 'Martin', 'Scorsese');

insert into director(`director_id`, `first_name`, `last_name`)
values (2, 'Joel', 'Coen');

insert into director(`director_id`, `first_name`, `last_name`)
values (3, 'Peter', 'Farrelly');

insert into movie (`id`, `name`, `genre`, `year`, `director_id`, `rating`, `description`)
values (1, 'Goodfellas', 'Crime', '1990', 1, 5, 'I wanted to be a gangster.');

insert into movie (`id`, `name`, `genre`, `year`, `director_id`, `rating`, `description`)
values (2, 'Millers Crossing', 'Crime', '1990', 2, 5, 'Im sick of the high hat!');

insert into movie (`id`, `name`, `genre`, `year`, `director_id`, `rating`, `description`)
values (3, 'Kingpin', 'Comedy', '1996', 3, 4.1, 'Bill Murray goes bowling!');