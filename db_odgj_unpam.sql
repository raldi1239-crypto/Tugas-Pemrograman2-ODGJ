-- Database: db_odgj_unpam
CREATE DATABASE IF NOT EXISTS db_odgj_unpam;
USE db_odgj_unpam;

DROP TABLE IF EXISTS pasien_odgj;

CREATE TABLE pasien_odgj (
    id_pasien VARCHAR(10) PRIMARY KEY,
    nama VARCHAR(100) NOT NULL,
    usia INT NOT NULL,
    kategori_diagnosa VARCHAR(50) NOT NULL,
    tingkat_risiko VARCHAR(20) NOT NULL,
    jadwal_obat VARCHAR(100) NOT NULL,
    status_rawat VARCHAR(30) NOT NULL
);

-- Data Sampel
INSERT INTO pasien_odgj VALUES 
('PSG001', 'Budi Santoso', 35, 'Skizofrenia Halusinasi', 'Tinggi', 'Risperidone 2x2mg', 'Rawat Inap'),
('PSG002', 'Siti Aminah', 28, 'Depresi Berat', 'Sedang', 'Sertraline 1x50mg', 'Rawat Jalan'),
('PSG003', 'Ahmad Hidayat', 42, 'Bipolar Fase Mania', 'Tinggi', 'Lithium 3x300mg', 'Rawat Inap');
