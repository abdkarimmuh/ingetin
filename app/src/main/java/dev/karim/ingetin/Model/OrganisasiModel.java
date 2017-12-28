package dev.karim.ingetin.Model;

/**
 * Created by Karim on 12/28/2017.
 */

public class OrganisasiModel {
    private int id;
    private String judul;
    private String jenis;
    private String deadline;
    private String deskripsi;
    private String presensi;
    private String notulensi;
    private String done;

    public OrganisasiModel(int id, String judul, String jenis, String deadline, String deskripsi, String presensi, String notulensi, String done) {
        this.id = id;
        this.judul = judul;
        this.jenis = jenis;
        this.deadline = deadline;
        this.deskripsi = deskripsi;
        this.presensi = presensi;
        this.notulensi = notulensi;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getPresensi() {
        return presensi;
    }

    public void setPresensi(String presensi) {
        this.presensi = presensi;
    }

    public String getNotulensi() {
        return notulensi;
    }

    public void setNotulensi(String notulensi) {
        this.notulensi = notulensi;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }
}
