package dev.karim.ingetin.ItemObject;

/**
 * Created by Karim on 11/17/2017.
 */

public class ItemObjectTugas {
    public String judul, jenis, deskripsi, deadline, create, done;

    public ItemObjectTugas(String judul, String jenis, String deskripsi, String deadline, String create, String done) {
        this.judul = judul;
        this.jenis = jenis;
        this.deskripsi = deskripsi;
        this.deadline = deadline;
        this.create = create;
        this.done = done;
    }
}
