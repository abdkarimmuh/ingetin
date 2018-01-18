package dev.karim.ingetin.RealmObject;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Karim on 12/28/2017.
 */

public class Organisasi extends RealmObject {
    @PrimaryKey
    private int id;
    private String judul;
    private String jenis;
    private String deadline;
    private String deskripsi;
    private String option;
    private String sebagai;
    private String tugas;
    private String presensi;
    private String notulensi;
    private String done;

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

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getSebagai() {
        return sebagai;
    }

    public void setSebagai(String sebagai) {
        this.sebagai = sebagai;
    }

    public String getTugas() {
        return tugas;
    }

    public void setTugas(String tugas) {
        this.tugas = tugas;
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
