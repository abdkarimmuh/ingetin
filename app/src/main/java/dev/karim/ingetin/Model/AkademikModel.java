package dev.karim.ingetin.Model;

/**
 * Created by Karim on 12/29/2017.
 */

public class AkademikModel {
    private int id;
    private String judul;
    private String jenis;
    private String option;
    private String deadline;
    private String deskripsi;
    private String done;

    public AkademikModel(int id, String judul, String jenis, String option, String deadline, String deskripsi, String done) {
        this.id = id;
        this.judul = judul;
        this.jenis = jenis;
        this.option = option;
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

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
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
