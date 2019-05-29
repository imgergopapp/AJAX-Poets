DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS works CASCADE;


CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    name TEXT NOT NULL,
    CONSTRAINT name_not_empty CHECK (name <> ''),
	CONSTRAINT email_not_empty CHECK (email <> ''),
	CONSTRAINT password_not_empty CHECK (password <> '')
);

CREATE TABLE works (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    date DATE NOT NULL,
    title VARCHAR(80) NOT NULL,
    content VARCHAR(300) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

INSERT INTO users (email, password, name) VALUES
	('user1@user1', 'user1', 'Sándor Petőfi'), -- 1
	('user2@user2', 'user2', 'Rejtő Jenő'), -- 2
	('user3@user3', 'user3', 'Stephen King'); -- 3

INSERT INTO works (user_id, date, title, content) VALUES
	(1, '1848-11-01', 'Anyám tyúkja', 'Ej mi a kő! tyúkanyó, kend\nA szobában lakik itt bent? Lám, csak jó az isten, jót ád, Hogy fölvitte a kend dolgát!'),   -- 1
	(1, '1844-01-01', 'János vitéz', 'Tüzesen süt le a nyári nap sugára Az ég tetejéről a juhászbojtárra. Fölösleges dolog sütnie oly nagyon, A juhásznak úgyis nagy melege vagyon.'),  -- 2
	(2, '1940-01-01', 'Piszkos Fred, a kapitány', '- Uram! A késemért jöttem!- Hol hagyta?- Valami matrózban.- Milyen kés volt?- Acél. Keskeny penge, kissé hajlott. Nem látta?- Várjunk... Csak lassan, kérem... Milyen volt a nyele?- Kagyló.- Hány részből?- Egy darabból készült.- Akkor nincs baj. Megvan a kés!- Hol?- A hátamban.- Köszönöm.'), -- 3
	(3, '2015-01-01', 'Rémálmok bazára', '– Felteszem, ma a saját nevét pillantotta meg a homokban – feleli Wayland.Beecher bíró egy pillanatra meghökken, aztán elmosolyodik. Ijesztő mosolya egyre szélesedik,míg a sápadt, beesett arcra kiülő halálvigyorrá nem torzul.– Dehogyis – szólal meg. – Nem az enyémet.'); -- 3



