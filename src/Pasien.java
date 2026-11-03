package src;

// Class Parent (Konsep OOP Inheritance)
class Orang {
    protected String nama;
    protected int usia;

    public Orang(String nama, int usia) {
        this.nama = nama;
        this.usia = usia;
    }
}

// Class Child (Inheritance & Encapsulation)
public class Pasien extends Orang {
    private String idPasien;
    private String kategoriDiagnosa;
    private String tingkatRisiko;
    private String jadwalObat;
    private String statusRawat;

    public Pasien(String idPasien, String nama, int usia, String kategoriDiagnosa, 
                  String tingkatRisiko, String jadwalObat, String statusRawat) {
        super(nama, usia);
        this.idPasien = idPasien;
        this.kategoriDiagnosa = kategoriDiagnosa;
        this.tingkatRisiko = tingkatRisiko;
        this.jadwalObat = jadwalObat;
        this.statusRawat = statusRawat;
    }

    // Getter (Encapsulation)
    public String getIdPasien() { return idPasien; }
    public String getNama() { return nama; }
    public int getUsia() { return usia; }
    public String getKategoriDiagnosa() { return kategoriDiagnosa; }
    public String getTingkatRisiko() { return tingkatRisiko; }
    public String getJadwalObat() { return jadwalObat; }
    public String getStatusRawat() { return statusRawat; }
}
