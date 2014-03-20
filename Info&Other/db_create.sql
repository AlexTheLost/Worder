CREATE DATABASE worder;
USE worder;

-- CREATE TABLE words (words_id INT(5) NOT NULL AUTO_INCREMENT PRIMARY KEY, word VARCHAR(20) NOT NULL UNIQUE, lang VARCHAR(3) NOT NULL CHECK(lang IN('EN', 'RU')));

CREATE TABLE translations (
	id_translation INT(5) NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	translation VARCHAR(20) NOT NULL UNIQUE);

drop table words;

CREATE TABLE words (id_word INT(5) NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	word VARCHAR(20) NOT NULL UNIQUE, complexity FLOAT(4,3) 
	UNSIGNED NOT NULL CHECK(complexity>=0 and complexity<=1));

insert into words(word, complexity) values('t4', 0.349);
select * from words;

CREATE TABLE words_translations (
	id_words_translations INT(5) NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	word_id INT(5) NOT NULL, 
	translation_id INT(5) NOT NULL, 
	FOREIGN KEY (word_id) REFERENCES words (id_word), 
	FOREIGN KEY (translation_id) REFERENCES translations (id_translation));

SELECT * FROM words_translations wt INNER JOIN words w ON wt.word_id = w.id_word WHERE word = 'Test1';

SELECT translation FROM translations WHERE id_translation IN (SELECT translation_id FROM words_translations wt INNER JOIN words w ON wt.word_id = w.id_word WHERE word = 'Test1');