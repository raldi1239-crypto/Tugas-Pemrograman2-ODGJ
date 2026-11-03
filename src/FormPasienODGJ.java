package src;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FormPasienODGJ extends javax.swing.JFrame {

    private JTextField txtId, txtNama, txtUsia, txtObat;
    private JComboBox<String> cbDiagnosa, cbRisiko, cbStatus;
    private JTable tblPasien;
    private DefaultTableModel model;

    public FormPasienODGJ() {
        initComponentsCustom();
        tampilData();
    }

    private void initComponentsCustom() {
        setTitle("Aplikasi Rekam Medis & Manajemen Pasien ODGJ - UNPAM");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Header
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(new Color(41, 128, 185));
        JLabel lblTitle = new JLabel("SISTEM MANAJEMEN DATA PASIEN ODGJ");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);
        panelHeader.add(lblTitle);
        add(panelHeader, BorderLayout.NORTH);

        // Form Input Panel
        JPanel panelForm = new JPanel(new GridLayout(8, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        panelForm.add(new JLabel("ID Pasien:"));
        txtId = new JTextField();
        panelForm.add(txtId);

        panelForm.add(new JLabel("Nama Pasien:"));
        txtNama = new JTextField();
        panelForm.add(txtNama);

        panelForm.add(new JLabel("Usia:"));
        txtUsia = new JTextField();
        panelForm.add(txtUsia);

        panelForm.add(new JLabel("Kategori Diagnosa:"));
        String[] diagnosaList = {"Skizofrenia Halusinasi", "Depresi Berat", "Bipolar Fase Mania", "Gangguan Kecemasan", "Lainnya"};
        cbDiagnosa = new JComboBox<>(diagnosaList);
        panelForm.add(cbDiagnosa);

        panelForm.add(new JLabel("Tingkat Risiko:"));
        String[] risikoList = {"Rendah", "Sedang", "Tinggi"};
        cbRisiko = new JComboBox<>(risikoList);
        panelForm.add(cbRisiko);

        panelForm.add(new JLabel("Jadwal & Dosis Obat:"));
        txtObat = new JTextField();
        panelForm.add(txtObat);

        panelForm.add(new JLabel("Status Perawatan:"));
        String[] statusList = {"Rawat Inap", "Rawat Jalan", "Rujukan RS Jiwa", "Selesai/Sembuh"};
        cbStatus = new JComboBox<>(statusList);
        panelForm.add(cbStatus);

        // Buttons
        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton btnSimpan = new JButton("Simpan");
        JButton btnEdit = new JButton("Edit");
        JButton btnHapus = new JButton("Hapus");
        JButton btnReset = new JButton("Reset");

        panelButton.add(btnSimpan);
        panelButton.add(btnEdit);
        panelButton.add(btnHapus);
        panelButton.add(btnReset);
        panelForm.add(panelButton);

        add(panelForm, BorderLayout.WEST);

        // Table
        tblPasien = new JTable();
        model = new DefaultTableModel(new String[]{"ID Pasien", "Nama", "Usia", "Diagnosa", "Risiko", "Jadwal Obat", "Status"}, 0);
        tblPasien.setModel(model);
        JScrollPane scrollPane = new JScrollPane(tblPasien);
        add(scrollPane, BorderLayout.CENTER);

        // Event Listeners
        btnSimpan.addActionListener(e -> btnSimpanActionPerformed());
        btnEdit.addActionListener(e -> btnEditActionPerformed());
        btnHapus.addActionListener(e -> btnHapusActionPerformed());
        btnReset.addActionListener(e -> resetForm());

        tblPasien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPasienMouseClicked();
            }
        });
    }

    private void tampilData() {
        model.setRowCount(0);
        try {
            String sql = "SELECT * FROM pasien_odgj";
            Connection conn = Koneksi.getKoneksi();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery(sql);

            while (res.next()) {
                model.addRow(new Object[]{
                    res.getString("id_pasien"),
                    res.getString("nama"),
                    res.getInt("usia"),
                    res.getString("kategori_diagnosa"),
                    res.getString("tingkat_risiko"),
                    res.getString("jadwal_obat"),
                    res.getString("status_rawat")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error Tampil Data: " + e.getMessage());
        }
    }

    private void resetForm() {
        txtId.setText("");
        txtNama.setText("");
        txtUsia.setText("");
        txtObat.setText("");
        cbDiagnosa.setSelectedIndex(0);
        cbRisiko.setSelectedIndex(0);
        cbStatus.setSelectedIndex(0);
        txtId.setEditable(true);
    }

    private void btnSimpanActionPerformed() {
        try {
            Pasien p = new Pasien(
                txtId.getText(),
                txtNama.getText(),
                Integer.parseInt(txtUsia.getText()),
                cbDiagnosa.getSelectedItem().toString(),
                cbRisiko.getSelectedItem().toString(),
                txtObat.getText(),
                cbStatus.getSelectedItem().toString()
            );

            String sql = "INSERT INTO pasien_odgj VALUES ('" + p.getIdPasien() + "', '" 
                        + p.getNama() + "', " + p.getUsia() + ", '" 
                        + p.getKategoriDiagnosa() + "', '" + p.getTingkatRisiko() + "', '" 
                        + p.getJadwalObat() + "', '" + p.getStatusRawat() + "')";

            Connection conn = Koneksi.getKoneksi();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();

            JOptionPane.showMessageDialog(null, "Data Pasien Berhasil Disimpan!");
            tampilData();
            resetForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Simpan: " + e.getMessage());
        }
    }

    private void btnEditActionPerformed() {
        try {
            String sql = "UPDATE pasien_odgj SET nama='" + txtNama.getText() 
                        + "', usia=" + txtUsia.getText() 
                        + ", kategori_diagnosa='" + cbDiagnosa.getSelectedItem().toString() 
                        + "', tingkat_risiko='" + cbRisiko.getSelectedItem().toString() 
                        + "', jadwal_obat='" + txtObat.getText() 
                        + "', status_rawat='" + cbStatus.getSelectedItem().toString() 
                        + "' WHERE id_pasien='" + txtId.getText() + "'";

            Connection conn = Koneksi.getKoneksi();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();

            JOptionPane.showMessageDialog(null, "Data Berhasil Diperbarui!");
            tampilData();
            resetForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Edit: " + e.getMessage());
        }
    }

    private void btnHapusActionPerformed() {
        try {
            int konfirmasi = JOptionPane.showConfirmDialog(this, 
                "Apakah Anda yakin ingin menghapus data pasien ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);

            if (konfirmasi == JOptionPane.YES_OPTION) {
                String sql = "DELETE FROM pasien_odgj WHERE id_pasien='" + txtId.getText() + "'";
                Connection conn = Koneksi.getKoneksi();
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();

                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus!");
                tampilData();
                resetForm();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal Hapus: " + e.getMessage());
        }
    }

    private void tblPasienMouseClicked() {
        int baris = tblPasien.getSelectedRow();
        if (baris != -1) {
            txtId.setText(tblPasien.getValueAt(baris, 0).toString());
            txtNama.setText(tblPasien.getValueAt(baris, 1).toString());
            txtUsia.setText(tblPasien.getValueAt(baris, 2).toString());
            cbDiagnosa.setSelectedItem(tblPasien.getValueAt(baris, 3).toString());
            cbRisiko.setSelectedItem(tblPasien.getValueAt(baris, 4).toString());
            txtObat.setText(tblPasien.getValueAt(baris, 5).toString());
            cbStatus.setSelectedItem(tblPasien.getValueAt(baris, 6).toString());

            txtId.setEditable(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormPasienODGJ().setVisible(true));
    }
}
