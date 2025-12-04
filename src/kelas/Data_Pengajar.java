/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kelas;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
/**
 *
 * @author cherly
 */
public class Data_Pengajar extends koneksi{
    
    private String nama_ustadz, alamat, jenis_kelamin, no_hp;
    private int id_ustadz;
    private final Connection koneksi;
    private PreparedStatement ps, dp;
    private Statement st;
    private ResultSet rs;
    private String query;
    
    public Data_Pengajar(){
        koneksi = super.configDB();
    }
    public int getId_ustadz() {
        return id_ustadz;
    }

    public void setId_ustadz(int id_ustadz) {
        this.id_ustadz = id_ustadz;
    }
    public String getNama_ustadz() {
        return nama_ustadz;
    }

    public void setNama_ustadz(String nama_ustadz) {
        this.nama_ustadz = nama_ustadz;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }
    

    
    public void tambahDataUstadz (){
        query = "INSERT INTO ustadz (nama_ustadz, alamat, no_hp, jenis_kelamin) VALUES(?,?,?,?) ";
        try {
            dp = koneksi.prepareStatement(query);
            dp.setString(1, nama_ustadz);
            dp.setString(2, alamat);
            dp.setString(3, no_hp);
            dp.setString(4, jenis_kelamin);
            dp.executeUpdate();
            dp.close();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Ditambahkan");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditambahkan, Eror : " 
                + e.getMessage());
    }
    }
    
    public void ubahDataUstadz() {
           query = "UPDATE ustadz SET nama_ustadz=?, alamat=?, no_hp=?, "
                   + "jenis_kelamin=? WHERE id_ustadz=?  ";
    
        try {
            dp = koneksi.prepareStatement(query);
            dp.setString(1, nama_ustadz);
            dp.setString(2, alamat);
            dp.setString(3, no_hp);
            dp.setString(4, jenis_kelamin);
            dp.setInt(5, id_ustadz);
            dp.executeUpdate();
            dp.close();
            
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah. Eror: "
                 + e.getMessage());
        }
    }
    
    public void hapusDataUstadz(){
        query = "DELETE FROM ustadz WHERE id_ustadz = ? ";
        
        try {
            dp = koneksi.prepareStatement(query);
            dp.setInt(1, id_ustadz);
            dp.executeUpdate();
            dp.close();
            JOptionPane.showMessageDialog(null, "Data Berhasil DiHapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal DiHapus. Eror: " 
                    + e.getMessage());
        }
        
    }
    
    public void SimpanUstadz(){
    if (id_ustadz == 0){
        tambahDataUstadz();
    } else{
        ubahDataUstadz();
    }
    }
    
    
    public ResultSet tampilUstadz(){
        query = "SELECT * FROM ustadz";
        
        try {
            st = koneksi.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan");
        }
        return rs;
    }
    
    public ResultSet cariUstadz(String key){
        query = "SELECT * FROM ustadz WHERE "
                + "nama_ustadz LIKE ? OR "
                + "alamat LIKE ? OR "
                + "jenis_kelamin LIKE ? OR "
                + "no_hp LIKE ? ";
        
        try {
            ps = koneksi.prepareStatement(query);
            key = "%" + key + "%";
            
            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);
            ps.setString(4, key);
            
            rs = ps.executeQuery();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Gagal mencari data ustadz: " 
                    + e.getMessage());
        }
        return rs;
    }
}