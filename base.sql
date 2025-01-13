-- Active: 1719525958588@@127.0.0.1@5432@madamovie
INSERT INTO bouquet (nom, prix)
VALUES
    ('Bouquet Comédie', 29.99),
    ('Bouquet Action', 39.99),
    ('Bouquet Science-Fiction', 34.50),
    ('Bouquet Drame', 24.99),
    ('Bouquet Aventure', 27.50);

INSERT INTO abonnement (date_debut, datefin, ref, id_bouquet, id_client)
VALUES
    ('2025-01-01', '2025-12-31', 'ABO001', 1, 2),
    ('2025-02-01', '2025-08-31', 'ABO002', 2, 2),
    ('2025-03-01', '2025-11-30', 'ABO003', 3, 2),
    ('2025-01-15', '2025-07-15', 'ABO004', 4, 2),
    ('2025-04-01', '2025-09-30', 'ABO005', 5, 2);

INSERT INTO categorie (nom)
VALUES
    ('Action'),
    ('Comédie'),
    ('Science-Fiction'),
    ('Drame'),
    ('Aventure');

INSERT INTO bouquet_categorie (id_bouquet, id_categorie)
VALUES
    (1, 1),  -- Bouquet 1 (Bouquet Comédie) associé à la catégorie Action
    (1, 2),  -- Bouquet 1 (Bouquet Comédie) associé à la catégorie Comédie
    (2, 1),  -- Bouquet 2 (Bouquet Action) associé à la catégorie Action
    (2, 3),  -- Bouquet 2 (Bouquet Action) associé à la catégorie Science-Fiction
    (3, 4),  -- Bouquet 3 (Bouquet Science-Fiction) associé à la catégorie Drame
    (4, 5);  -- Bouquet 4 (Bouquet Drame) associé à la catégorie Aventure

INSERT INTO film (annee, chemin, date_ajout, dure, image, is_delete, nom, synopsis, id_categorie)
VALUES
    (1994, '/path/to/the_shawshank_redemption.mp4', '2025-01-10', 142, '/path/to/shawshank_redemption.jpg', 0, 'The Shawshank Redemption', 
    'Andy Dufresne, un banquier condamné à la prison à vie, se lie d amitié avec un détenu nommé Red. Ensemble, ils affrontent les difficultés de la vie en prison et espèrent une vie meilleure.', 1),
    
    (1999, '/path/to/fight_club.mp4', '2025-01-10', 139, '/path/to/fight_club.jpg', 0, 'Fight Club', 
    'Un homme, frustré par la routine de sa vie quotidienne, rencontre un charismatique vendeur de savon nommé Tyler Durden. Ensemble, ils fondent un club de combat underground.', 2),
    
    (2010, '/path/to/inception.mp4', '2025-01-11', 148, '/path/to/inception.jpg', 0, 'Inception', 
    'Un voleur spécialisé dans l’extraction de secrets en entrant dans les rêves des gens reçoit une mission risquée : implanter une idée dans l esprit d un dirigeant d’entreprise.', 3),
    
    (2014, '/path/to/interstellar.mp4', '2025-01-12', 169, '/path/to/interstellar.jpg', 0, 'Interstellar', 
    'Dans un futur où la Terre est en déclin, un groupe d’astronautes voyage à travers un trou de ver pour trouver un nouveau foyer pour l humanité.', 3),
    
    (1997, '/path/to/titanic.mp4', '2025-01-13', 195, '/path/to/titanic.jpg', 0, 'Titanic', 
    'Rose, une jeune aristocrate, rencontre Jack, un artiste modeste, à bord du RMS Titanic. Leur romance se développe pendant que le navire est en train de sombrer dans l océan.', 4);
