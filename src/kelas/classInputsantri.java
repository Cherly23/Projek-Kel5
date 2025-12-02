/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kelas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ThinkPad
 */
public class classInputsantri extends koneksi{
    private String NamaSantri, tempatLahir, alamat, jenisKelamin, waliSantri, noHP, tanggalLahir, tanggalMasuk;
    private int id_santri, Status;
    private final Connection koneksi;
    private PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    private String query;
    
    public classInputsantri(){
        koneksi = super.configDB();
    }

    public String getNamaSantri() {
        return NamaSantri;
    }

    public void setNamaSantri(String NamaSantri) {
        this.NamaSantri = NamaSantri;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getWaliSantri() {
        return waliSantri;
    }

    public void setWaliSantri(String waliSantri) {
        this.waliSantri = waliSantri;
    }

    public String getNoHP() {
        return noHP;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getTanggalMasuk() {
        return tanggalMasuk;
    }

    public void setTanggalMasuk(String tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public int getId_santri() {
        return id_santri;
    }

    public void setId_santri(int id_santri) {
        this.id_santri = id_santri;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public PreparedStatement getPs() {
        return ps;
    }

    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    public Statement getSt() {
        return st;
    }

    public void setSt(Statement st) {
        this.st = st;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
    public ResultSet jumlahPutra (){
        query = "SELECT COUNT(*) AS jumlahPutra FROM santri WHERE jenisKelamin = 'Laki-Laki';";
        try {
            st = koneksi.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menampilkan Putra: " + e.getMessage());
        }
    return rs;
}
    public ResultSet jumlahPutri (){
        query = "SELECT COUNT(*) AS jumlahPutri FROM santri WHERE jenis_kelamin = 'Perempuan';";
        try {
            st = koneksi.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Gagal menampilkan Putri: " + e.getMessage());
        }
        return rs;
    }
    public void tambahSantri() {
         query = "INSERT INTO santri (NamaSantri, tempat_lahir, tanggal_lahir, alamat, "
                 + "jenis_kelamin, wali_santri, no_hp, tanggal_masuk, Status) VALUES (?,?,?,?,?,?,?,?,?)";
         try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, NamaSantri);
            ps.setString(2, tempatLahir);
            ps.setString(3, tanggalLahir);
            ps.setString(4, alamat);
            ps.setString(5, jenisKelamin);
            ps.setString(6, waliSantri);
            ps.setString(7, noHP);
            ps.setString(8, tanggalMasuk);
            ps.setInt(9, Status);
            ps.executeUpdate();
            ps.close();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditambahkan, Eror : " + sQLException.getMessage());
        }
    }
    public void UbahSantri(){
        query = "UPDATE santri SET NamaSantri=?, tempat_lahir=?, tanggal_lahir=?, alamat=?, jenis_kelamin=?, wali_santri=?, no_hp=?, tanggal_masuk=?, Status=? WHERE id_santri=?";
        try {
             ps = koneksi.prepareStatement(query);
            ps.setString(1, NamaSantri);
            ps.setString(2, tempatLahir);
            ps.setString(3, tanggalLahir);
            ps.setString(4, alamat);
            ps.setString(5, jenisKelamin);
            ps.setString(6, waliSantri);
            ps.setString(7, noHP);
            ps.setString(8, tanggalMasuk);
            ps.setInt(9, Status);
            ps.executeUpdate();
            ps.close();
            
            JOptionPane.showMessageDialog(null, "Perubahan Berhasil DiSimpan");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Perubahan Gagal DiSimpan");
    }
        }
    public void HapusSantri(){
        query = "DELETE FROM santri WHERE id_santri = ? ";
        
        try {
            ps = koneksi.prepareStatement(query);
            ps.setInt(1, id_santri);
            ps.executeUpdate();
            ps.close();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil DiHapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal DiHapus");
        }
    }
    public void SimpanSantri() {
        // Jika id_santri = 0 artinya data baru
    if (id_santri == 0) {
        UbahSantri();
    } else {
        UbahSantri();
    }
    
    }
    public ResultSet TampilSantri(){
        query = "SELECT * FROM santri";
        
        try {
            st = koneksi.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan");
        }
        return rs;
    }
    public ResultSet DetailSantri(int id) {
        query = "SELECT * FROM santri WHERE id_santri= ?";
        try {
            ps = koneksi.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return rs;
}
    public ResultSet cariSantri(String key) {
    query = "SELECT * FROM santri WHERE "
            + "NamaSantri LIKE ? OR "
            + "tempat_lahir LIKE ? OR "
            + "tanggal_lahir LIKE ? OR "
            + "alamat LIKE ? OR "
            + "jenis_kelamin LIKE ? OR "
            + "wali_santri LIKE ? OR "
            + "no_hp LIKE ? OR "
            + "tanggal_masuk LIKE ? OR "
            + "Status LIKE ?";
        try {
            ps = koneksi.prepareStatement(query);
        key = "%" + key + "%";

        ps.setString(1, key);  // nama
        ps.setString(2, key);  // tempat lahir
        ps.setString(3, key);  // tanggal lahir
        ps.setString(4, key);  // alamat
        ps.setString(5, key);  // jenis kelamin
        ps.setString(6, key);  // wali santri
        ps.setString(7, key);  // no hp
        ps.setString(8, key);  // tanggal masuk
        ps.setString(9, key);  // status

        rs = ps.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Gagal mencari data santri: " + e.getMessage());
        }
        return rs;
        }
}


