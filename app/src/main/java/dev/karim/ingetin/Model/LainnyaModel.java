package dev.karim.ingetin.Model;

/**
 * Created by Karim on 12/29/2017.
 */

public class LainnyaModel {
    private int id;
    private String judul;
    private String deadline;
    private String deskripsi;
    private String done;

    public LainnyaModel(int id, String judul, String deadline, String deskripsi, String done) {
        this.id = id;
        this.judul = judul;
        this.deadline = deadline;
        this.deskripsi = deskripsi;
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

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }
}
