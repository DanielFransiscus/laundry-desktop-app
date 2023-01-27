-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 27, 2023 at 03:48 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `laundry`
--

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `no_transaksi` varchar(10) NOT NULL,
  `no_order` varchar(10) NOT NULL,
  `id_admin` int(2) NOT NULL,
  `tgl_transaksi` date NOT NULL,
  `total_harga` int(10) NOT NULL,
  `dibayar` int(10) NOT NULL,
  `kembali` int(10) NOT NULL,
  `keluhan` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`no_transaksi`, `no_order`, `id_admin`, `tgl_transaksi`, `total_harga`, `dibayar`, `kembali`, `keluhan`) VALUES
('TRX0001', 'ORD0001', 1, '2022-01-17', 18000, 70000, 52000, 'Kancing putus'),
('TRX0002', 'ORD0002', 1, '2022-01-26', 72000, 90000, 18000, 'KANCING putus'),
('TRX0003', 'ORD0004', 1, '2023-01-28', 180000, 190000, 10000, 'Baju Robek'),
('TRX0004', 'ORD0005', 1, '2023-01-30', 240000, 250000, 10000, 'Kancing Putus dan warna pudar');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`no_transaksi`),
  ADD KEY `no_order` (`no_order`),
  ADD KEY `id_admin` (`id_admin`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`no_order`) REFERENCES `orderku` (`no_order`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`id_admin`) REFERENCES `admin` (`id_admin`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
