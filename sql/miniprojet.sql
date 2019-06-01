-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  ven. 31 mai 2019 à 16:38
-- Version du serveur :  10.1.37-MariaDB
-- Version de PHP :  7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `miniprojet`
--

-- --------------------------------------------------------

--
-- Structure de la table `activites`
--

CREATE TABLE `activites` (
  `idactivite` int(12) NOT NULL,
  `nomactivite` text NOT NULL,
  `membreactivite` text NOT NULL,
  `datedebutactivite` date NOT NULL,
  `datefinactivite` date NOT NULL,
  `descriptionactivite` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `activites`
--

INSERT INTO `activites` (`idactivite`, `nomactivite`, `membreactivite`, `datedebutactivite`, `datefinactivite`, `descriptionactivite`) VALUES
(1, 'faire un bilan', 'la secretaire', '2019-05-01', '2019-05-24', 'pas de description'),
(2, 'gerer les ressources', 'Mr Tresorier', '2018-12-03', '2019-11-01', 'null'),
(3, 'no', 'me', '2019-08-12', '2020-08-12', 'null');

-- --------------------------------------------------------

--
-- Structure de la table `evenements`
--

CREATE TABLE `evenements` (
  `id_evenement` int(20) NOT NULL,
  `nom_evenement` text,
  `objectif_evenement` text,
  `date_evenement` date DEFAULT NULL,
  `description_evenement` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `evenements`
--

INSERT INTO `evenements` (`id_evenement`, `nom_evenement`, `objectif_evenement`, `date_evenement`, `description_evenement`) VALUES
(1, 'Faire un plan', 'l\'objectif est de completer les activites en parallelle avec la formation', '2019-05-07', 'pas de description'),
(2, 'designerun nom du club', 'satisfaire le tout', '2018-12-04', 'null'),
(3, 'ecrire les rapports des differents activites', 'envoyer le rapport du club a l\'administration et inviter les autres etudiants de l\'ensaj d\'integrer le club', '2019-05-23', 'nul'),
(4, 'hakathon', 'faire une competition du codage', '2018-10-24', 'ambiance '),
(5, 'hello', 'no', '2018-07-05', '');

-- --------------------------------------------------------

--
-- Structure de la table `membres`
--

CREATE TABLE `membres` (
  `idmembre` int(11) NOT NULL,
  `nommembre` varchar(50) NOT NULL,
  `prenommembre` varchar(80) NOT NULL,
  `emailmembre` varchar(50) NOT NULL,
  `passwordmembre` text NOT NULL,
  `niveaumembre` text NOT NULL,
  `phonemembre` int(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `membres`
--

INSERT INTO `membres` (`idmembre`, `nommembre`, `prenommembre`, `emailmembre`, `passwordmembre`, `niveaumembre`, `phonemembre`) VALUES
(1, 'marie', 'marie2', 'marie@gmail.com', 'marie2662', 'premiere annee', 90808),
(2, 'parti', 'Charl', 'Charl@gmail.com', 'charl879797jjkhj', '89099', 89099),
(3, 'sas', 'rosi', 'Rosie@gmail.com', 'fifatoto', 'troisieme annee 2ite', 899778),
(4, 'john', 'Paul', 'Paul@gmail.com', 'paulmotde passe', '63698789', 63698789),
(7, 'Carlin', 'Sami', 'Carkin@gmail.com', 'toto454647', '678883', 678883),
(8, 'Paul', 'Andrain', 'paul@gmail.com', 'paulkiki', 'isic', 988);

-- --------------------------------------------------------

--
-- Structure de la table `operations`
--

CREATE TABLE `operations` (
  `idoperation` int(11) NOT NULL,
  `nomoperation` text NOT NULL,
  `objectifoperation` text NOT NULL,
  `dateoperation` date NOT NULL,
  `membreoperation` text NOT NULL,
  `montant` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `operations`
--

INSERT INTO `operations` (`idoperation`, `nomoperation`, `objectifoperation`, `dateoperation`, `membreoperation`, `montant`) VALUES
(1, 'creer compte', 'avoir un compte pour gerer les ressources', '2019-05-15', 'president', 0),
(2, 'consulter le compte', 'savoir le mantant existant dans la caisse', '2019-06-12', 'president et vice-president', 0),
(3, 'faire un virement', 'augmenter le budget ', '2019-04-02', 'vice-president', 0),
(4, 'retirer montant', 'besoin pour acheter les ressources', '2019-05-01', 'vice-president', 6686);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `activites`
--
ALTER TABLE `activites`
  ADD KEY `idactivite` (`idactivite`);

--
-- Index pour la table `evenements`
--
ALTER TABLE `evenements`
  ADD KEY `id_evenement` (`id_evenement`);

--
-- Index pour la table `membres`
--
ALTER TABLE `membres`
  ADD KEY `idmembre` (`idmembre`);

--
-- Index pour la table `operations`
--
ALTER TABLE `operations`
  ADD KEY `idoperation` (`idoperation`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `activites`
--
ALTER TABLE `activites`
  MODIFY `idactivite` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `evenements`
--
ALTER TABLE `evenements`
  MODIFY `id_evenement` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `membres`
--
ALTER TABLE `membres`
  MODIFY `idmembre` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `operations`
--
ALTER TABLE `operations`
  MODIFY `idoperation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
