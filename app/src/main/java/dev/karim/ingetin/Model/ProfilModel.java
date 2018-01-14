package dev.karim.ingetin.Model;

/**
 * Created by Karim on 1/2/2018.
 */

public class ProfilModel {
    private int id;
    private String nama;
    private String email;
    private String instansi;

    public ProfilModel(int id, String nama, String email, String instansi) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.instansi = instansi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstansi() {
        return instansi;
    }

    public void setInstansi(String instansi) {
        this.instansi = instansi;
    }
}
