# Aplikasi Manajemen & Rekam Medis Pasien ODGJ

Tugas Pemrograman 2 - Universitas Pamulang (UNPAM)

## FITUR UTAMA
1. **CRUD Data Pasien ODGJ**: Tambah, Tampil, Edit, dan Hapus data pasien.
2. **Penerapan Konsep OOP**:
   - **Inheritance**: Class `Pasien` menurunkan properti dari class `Orang`.
   - **Encapsulation**: Atribut bernilai `private` diakses dengan method *getter*.
3. **Database Integration**: Menggunakan MySQL JDBC Driver untuk integrasi penuh dengan `JTable`.

## PETUNJUK PENGGUNAAN
1. **Database Setup**:
   - Buka XAMPP dan jalankan **Apache** & **MySQL**.
   - Buka `http://localhost/phpmyadmin/`.
   - Buat database baru bernama `db_odgj_unpam` atau langsung **Import** file `db_odgj_unpam.sql` yang ada di folder ini.
2. **Menjalankan di NetBeans**:
   - Open Project folder `Tugas-Pemrograman2-ODGJ` di NetBeans IDE.
   - Tambahkan library `MySQL JDBC Driver` pada folder **Libraries** project jika belum terpasang.
   - Run `FormPasienODGJ.java`.
