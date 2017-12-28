package dev.karim.ingetin;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import dev.karim.ingetin.Model.OrganisasiModel;
import dev.karim.ingetin.Model.TugasModel;
import dev.karim.ingetin.RealmObject.Organisasi;
import dev.karim.ingetin.RealmObject.Tugas;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Karim on 12/28/2017.
 */

public class RealmHelper {
    private static final String TAG = "Realm Helper";

    private Realm realm;
    private RealmResults<Organisasi> realmResultOrganisasi;
    private RealmResults<Tugas> realmResultTugas;
    public Context context;

    /**
     * constructor untuk membuat instance realm
     *
     * @param context
     */
    public RealmHelper(Context context) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(config);
        this.context = context;
    }

    /**
     * menambah data
     *
     * @param judul
     * @param jenis
     * @param deadline
     * @param deskripsi
     * @param done
     */
    public void addTugas(String judul, String jenis, String deadline, String deskripsi, String done){
        Tugas tugas = new Tugas();
        tugas.setId((int) (System.currentTimeMillis() / 1000));
        tugas.setJudul(judul);
        tugas.setJenis(jenis);
        tugas.setDeadline(deadline);
        tugas.setDeskripsi(deskripsi);
        tugas.setDone(done);

        realm.beginTransaction();
        realm.copyToRealm(tugas);
        realm.commitTransaction();

        showLog("Added ; " + judul);
        showToast(judul + " berhasil disimpan");
    }

    /**
     * menambah data
     *
     * @param judul
     * @param jenis
     * @param deadline
     * @param deskripsi
     * @param presensi
     * @param notulensi
     * @param done
     */
    public void addOrganisasi(String judul, String jenis, String deadline, String deskripsi, String presensi, String notulensi, String done){
        Organisasi organisasi = new Organisasi();
        organisasi.setId((int) (System.currentTimeMillis() / 1000));
        organisasi.setJudul(judul);
        organisasi.setJenis(jenis);
        organisasi.setDeadline(deadline);
        organisasi.setDeskripsi(deskripsi);
        organisasi.setPresensi(presensi);
        organisasi.setNotulensi(notulensi);
        organisasi.setDone(done);

        realm.beginTransaction();
        realm.copyToRealm(organisasi);
        realm.commitTransaction();

        showLog("Added ; " + judul);
        showToast(judul + " berhasil disimpan");
    }



    /**
     * method mencari semua Tugas
     */
    public ArrayList<TugasModel> findAllTugas(){
        ArrayList<TugasModel> data = new ArrayList<>();

        realmResultTugas = realm.where(Tugas.class).findAll();
        realmResultTugas.sort("id", Sort.ASCENDING);
        if (realmResultTugas.size() > 0){
            showLog("Size : " + realmResultTugas.size());

            for (int i = 0; i < realmResultTugas.size(); i++) {
                String judul, jenis, deadline, deskripsi, done;
                int id = realmResultTugas.get(i).getId();
                judul = realmResultTugas.get(i).getJudul();
                jenis = realmResultTugas.get(i).getJenis();
                deadline = realmResultTugas.get(i).getDeadline();
                deskripsi = realmResultTugas.get(i).getDeskripsi();
                done = realmResultTugas.get(i).getDone();
                data.add(new TugasModel(id, judul, jenis, deadline, deskripsi, done));
            }

        } else {
            showLog("Size : 0");
        }

        return data;
    }

    /**
     * method mencari semua Organisasi
     */
    public ArrayList<OrganisasiModel> findAllOrganisasi(){
        ArrayList<OrganisasiModel> data = new ArrayList<>();

        realmResultOrganisasi = realm.where(Organisasi.class).findAll();
        realmResultOrganisasi.sort("id", Sort.ASCENDING);
        if (realmResultOrganisasi.size() > 0){
            showLog("Size : " + realmResultOrganisasi.size());

            for (int i = 0; i < realmResultOrganisasi.size(); i++) {
                String judul, jenis, deadline, deskripsi, presensi, notulensi, done;
                int id = realmResultOrganisasi.get(i).getId();
                judul = realmResultOrganisasi.get(i).getJudul();
                jenis = realmResultOrganisasi.get(i).getJenis();
                deadline = realmResultOrganisasi.get(i).getDeadline();
                deskripsi = realmResultOrganisasi.get(i).getDeskripsi();
                presensi = realmResultOrganisasi.get(i).getPresensi();
                notulensi = realmResultOrganisasi.get(i).getNotulensi();
                done = realmResultOrganisasi.get(i).getDone();
                data.add(new OrganisasiModel(id, judul, jenis, deadline, deskripsi, presensi, notulensi, done));
            }

        } else {
            showLog("Size : 0");
        }

        return data;
    }





    /**
     * method update Tugas
     *
     * @param id
     * @param judul
     * @param jenis
     * @param deadline
     * @param deskripsi
     * @param done
     */
    public void updateTugas(int id, String judul, String jenis, String deadline, String deskripsi, String done){
        realm.beginTransaction();
        Tugas tugas = realm.where(Tugas.class).equalTo("id", id).findFirst();
        tugas.setJudul(judul);
        tugas.setJenis(jenis);
        tugas.setDeadline(deadline);
        tugas.setDeskripsi(deskripsi);
        tugas.setDone(done);
        realm.commitTransaction();
        showLog("Updated : " + judul);
        showToast(judul + " berhasil diupdate.");
    }

    /**
     * method update Organisasi
     *
     * @param id
     * @param judul
     * @param jenis
     * @param deadline
     * @param deskripsi
     * @param presensi
     * @param notulensi
     * @param done
     */
    public void updateOrganisasi(int id, String judul, String jenis, String deadline, String deskripsi, String presensi, String notulensi, String done){
        realm.beginTransaction();
        Organisasi organisasi = realm.where(Organisasi.class).equalTo("id", id).findFirst();
        organisasi.setJudul(judul);
        organisasi.setJenis(jenis);
        organisasi.setDeadline(deadline);
        organisasi.setDeskripsi(deskripsi);
        organisasi.setPresensi(presensi);
        organisasi.setNotulensi(notulensi);
        organisasi.setDone(done);
        realm.commitTransaction();
        showLog("Updated : " + judul);
        showToast(judul + " berhasil diupdate.");
    }

    /**
     * method menghapus Organisasi berdasarkan id
     *
     * @param id
     */




    /**
     * method menghapus Tugas berdasarkan id
     *
     * @param id
     */

    public void deleteTugas(int id) {
        realm.beginTransaction();
        RealmResults<Tugas> dataResults = realm.where(Tugas.class).equalTo("id", id).findAll();

        dataResults.deleteAllFromRealm();

        realm.commitTransaction();

        showToast("Hapus data berhasil.");
    }

    public void deleteOrganisasi(int id) {
        realm.beginTransaction();
        RealmResults<Organisasi> dataResults = realm.where(Organisasi.class).equalTo("id", id).findAll();

        dataResults.deleteAllFromRealm();

        realm.commitTransaction();

        showToast("Hapus data berhasil.");
    }


    /**
     * membuat log
     *
     * @param s
     */

    private void showLog(String s) {
        Log.d(TAG, s);
    }

    /**
     * Membuat Toast Informasi Tersimpan
     */
    private void showToast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}