-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 02 Juin 2016 à 03:35
-- Version du serveur :  10.1.9-MariaDB
-- Version de PHP :  5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `gamification`
--

-- --------------------------------------------------------

--
-- Structure de la table `badge`
--

CREATE TABLE `badge` (
  `id` int(11) NOT NULL,
  `nomBadge` varchar(30) NOT NULL,
  `image` varchar(1000) NOT NULL,
  `pointMin` int(11) NOT NULL,
  `pointMax` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `badge`
--

INSERT INTO `badge` (`id`, `nomBadge`, `image`, `pointMin`, `pointMax`) VALUES
(6, 'Medaille D''Or', 'https://api-us.buddyplatform.com/pictures/bvc.ljCBKKDrwHjkc/file?sig=b416835eb55183fd29d0993173a46c16ff02447c8f9622535ff5f865b2e67fe4&sigValues=eyJwYXJhbXMiOnt9LCJwTGlzdCI6W10sInQiOm51bGwsImFjSWQiOiJiYmJidi5GTmNERHJLYkNGY2tjIiwiYXBJZCI6ImJiYmJiYy5GakpicnRGSG1xaGtjIiwidUlkIjoiYnYuSkdDQkt6dkNLSGhrYyJ9', 0, 0),
(7, 'Medaille D''Argent', 'https://api-us.buddyplatform.com/pictures/bvc.vgGkcKrJxHjkc/file?sig=bd5fbf8f31155050741d5b7f9f776310e0047d9992790d81e8d16ae4dbea608c&sigValues=eyJwYXJhbXMiOnt9LCJwTGlzdCI6W10sInQiOm51bGwsImFjSWQiOiJiYmJidi5GTmNERHJLYkNGY2tjIiwiYXBJZCI6ImJiYmJiYy5GakpicnRGSG1xaGtjIiwidUlkIjoiYnYuSkdDQkt6dkNLSGhrYyJ9', 0, 0),
(8, 'Medaille De Bronze', 'https://api-us.buddyplatform.com/pictures/bvc.NFCBKPdqzHjkc/file?sig=8e8b3218a31b69edd32d2ddc9ad9338e6bce34da30b0c4145fb4d9bc21c52f22&sigValues=eyJwYXJhbXMiOnt9LCJwTGlzdCI6W10sInQiOm51bGwsImFjSWQiOiJiYmJidi5GTmNERHJLYkNGY2tjIiwiYXBJZCI6ImJiYmJiYy5GakpicnRGSG1xaGtjIiwidUlkIjoiYnYuSkdDQkt6dkNLSGhrYyJ9', 0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `club`
--

CREATE TABLE `club` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `description` varchar(300) NOT NULL,
  `image` varchar(1000) NOT NULL,
  `slogan` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `club`
--

INSERT INTO `club` (`id`, `nom`, `description`, `image`, `slogan`) VALUES
(5, 'microsoft issatso club', 'mic', 'https://api-us.buddyplatform.com/pictures/bvc.ltCBKzdfwGjkc/file?sig=96824cadbfe41022505ac388958de81800c087a2a2d0e88e4b0758cb8e57b1f2&sigValues=eyJwYXJhbXMiOnt9LCJwTGlzdCI6W10sInQiOm51bGwsImFjSWQiOiJiYmJidi5GTmNERHJLYkNGY2tjIiwiYXBJZCI6ImJiYmJiYy5GakpicnRGSG1xaGtjIiwidUlkIjoiYnYuSkdDQkt6dkNLSGhrYyJ9', 'be what next'),
(6, 'issatso google club', 'igc', 'https://api-us.buddyplatform.com/pictures/bvc.pBJbrttDwGjkc/file?sig=94aec25548a404dd972dfff69714581854f9d51e5f302a81823d3b1cf5e80bf0&sigValues=eyJwYXJhbXMiOnt9LCJwTGlzdCI6W10sInQiOm51bGwsImFjSWQiOiJiYmJidi5GTmNERHJLYkNGY2tjIiwiYXBJZCI6ImJiYmJiYy5GakpicnRGSG1xaGtjIiwidUlkIjoiYnYuSkdDQkt6dkNLSGhrYyJ9', 'igc'),
(8, 'Club ', 'Club', 'https://api-us.buddyplatform.com/pictures/bvc.CBJbrPksBdkkc/file?sig=2b91090aeda147b022b78e80d69ef5e7efc03ce971c742c2893c69c448a703f5&sigValues=eyJwYXJhbXMiOnt9LCJwTGlzdCI6W10sInQiOm51bGwsImFjSWQiOiJiYmJidi5GTmNERHJLYkNGY2tjIiwiYXBJZCI6ImJiYmJiYy5GakpicnRGSG1xaGtjIiwidUlkIjoiYnYuSkdDQkt6dkNLSGhrYyJ9', 'Club');

-- --------------------------------------------------------

--
-- Structure de la table `clubbadge`
--

CREATE TABLE `clubbadge` (
  `id` int(11) NOT NULL,
  `idClub` int(11) NOT NULL,
  `idBadge` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

CREATE TABLE `evenement` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `lieux` varchar(300) NOT NULL,
  `longitude` varchar(20) NOT NULL,
  `latitude` varchar(20) NOT NULL,
  `date` date NOT NULL,
  `heure` varchar(10) NOT NULL,
  `idClub` int(11) NOT NULL,
  `image` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `evenement`
--

INSERT INTO `evenement` (`id`, `nom`, `description`, `lieux`, `longitude`, `latitude`, `date`, `heure`, `idClub`, `image`) VALUES
(6, 'coding moon  challenge', 'cmc', 'orient palace sousse', '151', '235', '2016-02-01', '?10:13', 5, 'https://api-us.buddyplatform.com/pictures/bvc.pLGkwJHpHHjkc/file?sig=9324f528d6a8e8b9f8dbb64bf351284c082410c5d5d2d54629acfdada45b00e9&sigValues=eyJwYXJhbXMiOnt9LCJwTGlzdCI6W10sInQiOm51bGwsImFjSWQiOiJiYmJidi5GTmNERHJLYkNGY2tjIiwiYXBJZCI6ImJiYmJiYy5GakpicnRGSG1xaGtjIiwidUlkIjoiYnYuSGZHa3dQUEJDSGprYyJ9'),
(7, 'Coding Land', 'Cl', 'Mannouba', '364.333343505859', '165.66667175293', '2016-04-01', '?17:31', 5, 'https://api-us.buddyplatform.com/pictures/bvc.BdcgHFMxPKjkc/file?sig=80f1c27a63ed873c790b50e0a4cc89a54bc8948accc296bc726792720b8da75e&sigValues=eyJwYXJhbXMiOnt9LCJwTGlzdCI6W10sInQiOm51bGwsImFjSWQiOiJiYmJidi5GTmNERHJLYkNGY2tjIiwiYXBJZCI6ImJiYmJiYy5GakpicnRGSG1xaGtjIiwidUlkIjoiYnYuSGZHa3dQUEJDSGprYyJ9'),
(8, 'Formation windows phone UWP', 'UWP', 'Issat Sousse', '402.333343505859', '297.666687011719', '2016-06-01', '?19:19', 5, 'https://api-us.buddyplatform.com/pictures/bvc.DpnBLGdmdPjkc/file?sig=874b0213d19a1c5ddbd0a2a780f3749b6a7b871949fb6d3bbbb1f8ba4b63909e&sigValues=eyJwYXJhbXMiOnt9LCJwTGlzdCI6W10sInQiOm51bGwsImFjSWQiOiJiYmJidi5GTmNERHJLYkNGY2tjIiwiYXBJZCI6ImJiYmJiYy5GakpicnRGSG1xaGtjIiwidUlkIjoiYnYuSGZHa3dQUEJDSGprYyJ9'),
(9, 'coding moon challenge', 'cmc', 'orient palace sousse', '65.6666717529297', '203', '2016-06-01', '?22:12', 5, 'https://api-us.buddyplatform.com/pictures/bvc.cGcgHPnjbgkkc/file?sig=602dca96d95c9ba393484db4864cd1cfcbc53a73f10551536e379540dba1b19d&sigValues=eyJwYXJhbXMiOnt9LCJwTGlzdCI6W10sInQiOm51bGwsImFjSWQiOiJiYmJidi5GTmNERHJLYkNGY2tjIiwiYXBJZCI6ImJiYmJiYy5GakpicnRGSG1xaGtjIiwidUlkIjoiYnYuSGZHa3dQUEJDSGprYyJ9');

-- --------------------------------------------------------

--
-- Structure de la table `points`
--

CREATE TABLE `points` (
  `id` int(11) NOT NULL,
  `point` int(11) NOT NULL,
  `idclub` varchar(255) NOT NULL,
  `cause` varchar(200) NOT NULL,
  `date` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `points`
--

INSERT INTO `points` (`id`, `point`, `idclub`, `cause`, `date`) VALUES
(4, 50, '5', 'formation', '2016-06-01'),
(3, 25, '6', 'formation', '2016-06-01'),
(5, 30, '6', 'formation', '2016-06-01'),
(6, 20, '5', 'formation', '2016-06-01'),
(7, 40, '8', 'Formation', '2016-06-01');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `idClub` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `username`, `idClub`) VALUES
(2, 'nasreddinejrebi@outlook.fr', '123456987', 'nasrijr', 2);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `badge`
--
ALTER TABLE `badge`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `club`
--
ALTER TABLE `club`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `clubbadge`
--
ALTER TABLE `clubbadge`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `points`
--
ALTER TABLE `points`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `badge`
--
ALTER TABLE `badge`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT pour la table `club`
--
ALTER TABLE `club`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT pour la table `clubbadge`
--
ALTER TABLE `clubbadge`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT pour la table `points`
--
ALTER TABLE `points`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
