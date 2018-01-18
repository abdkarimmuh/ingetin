package dev.karim.ingetin;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import dev.karim.ingetin.Model.LainnyaModel;
import dev.karim.ingetin.Model.OrganisasiModel;
import dev.karim.ingetin.Model.ProfilModel;
import dev.karim.ingetin.Model.AkademikModel;
import dev.karim.ingetin.RealmObject.Akademik;
import dev.karim.ingetin.RealmObject.Lainnya;
import dev.karim.ingetin.RealmObject.Organisasi;
import dev.karim.ingetin.RealmObject.Profil;
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
    private RealmResults<Akademik> realmResultAkademiks;
    private RealmResults<Lainnya> realmResultLainnya;
    private RealmResults<Profil> realmResultProfil;
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
     * @param nama
     * @param email
     * @param instansi
     */
    public void addProfil(String nama, String email, String instansi){
        Profil profil = new Profil();

//        profil.setId(0);
        profil.setId((int) (System.currentTimeMillis() / 1000));
        profil.setNama(nama);
        profil.setEmail(email);
        profil.setInstansi(instansi);


        realm.beginTransaction();
        realm.copyToRealm(profil);
        realm.commitTransaction();

        showLog("Added ; " + profil);
        showToast(profil + " berhasil disimpan");
    }

    /**
     * menambah data
     *
     * @param judul
     * @param jenis
     * @param option
     * @param deadline
     * @param deskripsi
     * @param done
     */
    public void addAkademik(String judul, String jenis, String option, String deadline, String deskripsi, String done){
        Akademik akademik = new Akademik();
        akademik.setId((int) (System.currentTimeMillis() / 1000));
        akademik.setJudul(judul);
        akademik.setJenis(jenis);
        akademik.setOption(option);
        akademik.setDeadline(deadline);
        akademik.setDeskripsi(deskripsi);
        akademik.setDone(done);

        realm.beginTransaction();
        realm.copyToRealm(akademik);
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
     * @param option
     * @param sebagai
     * @param tugas
     * @param presensi
     * @param notulensi
     * @param done
     */
    public void addOrganisasi(String judul, String jenis, String deadline, String deskripsi, String option, String sebagai, String tugas, String presensi, String notulensi, String done){
        Organisasi organisasi = new Organisasi();
        organisasi.setId((int) (System.currentTimeMillis() / 1000));
        organisasi.setJudul(judul);
        organisasi.setJenis(jenis);
        organisasi.setDeadline(deadline);
        organisasi.setDeskripsi(deskripsi);
        organisasi.setOption(option);
        organisasi.setSebagai(sebagai);
        organisasi.setTugas(tugas);
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
     * menambah data
     *
     * @param judul
     * @param deadline
     * @param deskripsi
     * @param done
     */
    public void addLainnya(String judul, String deadline, String deskripsi, String done){
        Lainnya lainnya = new Lainnya();
        lainnya.setId((int) (System.currentTimeMillis() / 1000));
        lainnya.setJudul(judul);
        lainnya.setDeadline(deadline);
        lainnya.setDeskripsi(deskripsi);
        lainnya.setDone(done);

        realm.beginTransaction();
        realm.copyToRealm(lainnya);
        realm.commitTransaction();

        showLog("Added ; " + judul);
        showToast(judul + " berhasil disimpan");
    }





    /**
     * method mencari semua Profil
     */
    public ArrayList<ProfilModel> findAllProfil(){
        ArrayList<ProfilModel> data = new ArrayList<>();

        realmResultProfil = realm.where(Profil.class).findAll();
        realmResultProfil.sort("id", Sort.DESCENDING);
        if (realmResultProfil.size() > 0){
            showLog("Size : " + realmResultProfil.size());

            for (int i = 0; i < realmResultProfil.size(); i++) {
                String nama, email, instansi;
                int id = realmResultProfil.get(i).getId();
                nama = realmResultProfil.get(i).getNama();
                email = realmResultProfil.get(i).getEmail();
                instansi = realmResultProfil.get(i).getInstansi();
                data.add(new ProfilModel(id, nama, email, instansi));
            }

        } else {
            showLog("Size : 0");
        }

        return data;
    }


    /**
     * method mencari semua Akademik
     */
    public ArrayList<AkademikModel> findAllAkademik(){
        ArrayList<AkademikModel> data = new ArrayList<>();

        realmResultAkademiks = realm.where(Akademik.class).findAll();
        realmResultAkademiks.sort("id", Sort.DESCENDING);
        if (realmResultAkademiks.size() > 0){
            showLog("Size : " + realmResultAkademiks.size());

            for (int i = 0; i < realmResultAkademiks.size(); i++) {
                String judul, jenis, deadline, option, deskripsi, done;
                int id = realmResultAkademiks.get(i).getId();
                judul = realmResultAkademiks.get(i).getJudul();
                jenis = realmResultAkademiks.get(i).getJenis();
                option = realmResultAkademiks.get(i).getOption();
                deadline = realmResultAkademiks.get(i).getDeadline();
                deskripsi = realmResultAkademiks.get(i).getDeskripsi();
                done = realmResultAkademiks.get(i).getDone();
                data.add(new AkademikModel(id, judul, jenis, option, deadline, deskripsi, done));
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
        realmResultOrganisasi.sort("id", Sort.DESCENDING);
        if (realmResultOrganisasi.size() > 0){
            showLog("Size : " + realmResultOrganisasi.size());

            for (int i = 0; i < realmResultOrganisasi.size(); i++) {
                String judul, jenis, deadline, deskripsi, option, sebagai, tugas, presensi, notulensi, done;
                int id = realmResultOrganisasi.get(i).getId();
                judul = realmResultOrganisasi.get(i).getJudul();
                jenis = realmResultOrganisasi.get(i).getJenis();
                deadline = realmResultOrganisasi.get(i).getDeadline();
                deskripsi = realmResultOrganisasi.get(i).getDeskripsi();
                option = realmResultOrganisasi.get(i).getOption();
                sebagai = realmResultOrganisasi.get(i).getSebagai();
                tugas = realmResultOrganisasi.get(i).getTugas();
                presensi = realmResultOrganisasi.get(i).getPresensi();
                notulensi = realmResultOrganisasi.get(i).getNotulensi();
                done = realmResultOrganisasi.get(i).getDone();
                data.add(new OrganisasiModel(id, judul, jenis, deadline, deskripsi, option, sebagai, tugas, presensi, notulensi, done));
            }

        } else {
            showLog("Size : 0");
        }

        return data;
    }

    /**
     * method mencari semua Lainnya
     */
    public ArrayList<LainnyaModel> findAllLainnya(){
        ArrayList<LainnyaModel> data = new ArrayList<>();

        realmResultLainnya = realm.where(Lainnya.class).findAll();
        realmResultLainnya.sort("id", Sort.DESCENDING);
        if (realmResultLainnya.size() > 0){
            showLog("Size : " + realmResultLainnya.size());

            for (int i = 0; i < realmResultLainnya.size(); i++) {
                String judul, jenis, deadline, deskripsi, done;
                int id = realmResultLainnya.get(i).getId();
                judul = realmResultLainnya.get(i).getJudul();
                deadline = realmResultLainnya.get(i).getDeadline();
                deskripsi = realmResultLainnya.get(i).getDeskripsi();
                done = realmResultLainnya.get(i).getDone();
                data.add(new LainnyaModel(id, judul, deadline, deskripsi, done));
            }

        } else {
            showLog("Size : 0");
        }

        return data;
    }




    /**
     * method update Profil
     *
     * @param id
     * @param nama
     * @param email
     * @param instansi
     */
    public void updateProfil(int id, String nama, String email, String instansi){
        realm.beginTransaction();
        Profil profil = realm.where(Profil.class).equalTo("id", id).findFirst();
        profil.setNama(nama);
        profil.setEmail(email);
        profil.setInstansi(instansi);
        realm.commitTransaction();
        showLog("Updated : " + nama);
        showToast("Profil berhasil diupdate.");
    }


    /**
     * method update Akademik
     *
     * @param id
     * @param judul
     * @param jenis
     * @param option
     * @param deadline
     * @param deskripsi
     * @param done
     */
    public void updateAkademik(int id, String judul, String jenis, String option, String deadline, String deskripsi, String done){
        realm.beginTransaction();
        Akademik akademik = realm.where(Akademik.class).equalTo("id", id).findFirst();
        akademik.setJudul(judul);
        akademik.setJenis(jenis);
        akademik.setOption(option);
        akademik.setDeadline(deadline);
        akademik.setDeskripsi(deskripsi);
        akademik.setDone(done);
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
     * @param option
     * @param sebagai
     * @param tugas
     * @param presensi
     * @param notulensi
     * @param done
     */
    public void updateOrganisasi(int id, String judul, String jenis, String deadline, String deskripsi, String option, String sebagai, String tugas, String presensi, String notulensi, String done){
        realm.beginTransaction();
        Organisasi organisasi = realm.where(Organisasi.class).equalTo("id", id).findFirst();
        organisasi.setJudul(judul);
        organisasi.setJenis(jenis);
        organisasi.setDeadline(deadline);
        organisasi.setDeskripsi(deskripsi);
        organisasi.setOption(option);
        organisasi.setSebagai(sebagai);
        organisasi.setTugas(tugas);
        organisasi.setPresensi(presensi);
        organisasi.setNotulensi(notulensi);
        organisasi.setDone(done);
        realm.commitTransaction();
        showLog("Updated : " + judul);
        showToast(judul + " berhasil diupdate.");
    }

    /**
     * method update Lainnya
     *
     * @param id
     * @param judul
     * @param deadline
     * @param deskripsi
     * @param done
     */
    public void updateLainnya(int id, String judul, String deadline, String deskripsi, String done){
        realm.beginTransaction();
        Lainnya lainnya = realm.where(Lainnya.class).equalTo("id", id).findFirst();
        lainnya.setJudul(judul);
        lainnya.setDeadline(deadline);
        lainnya.setDeskripsi(deskripsi);
        lainnya.setDone(done);
        realm.commitTransaction();
        showLog("Updated : " + judul);
        showToast(judul + " berhasil diupdate.");
    }




    /**
     * method menghapus Profil berdasarkan id
     *
     * @param id
     */
    public void deleteProfil(int id) {
        realm.beginTransaction();
        RealmResults<Profil> dataResults = realm.where(Profil.class).equalTo("id", id).findAll();

        dataResults.deleteAllFromRealm();

        realm.commitTransaction();

        showToast("Hapus data berhasil.");
    }

    /**
     * method menghapus Akademik berdasarkan id
     *
     * @param id
     */
    public void deleteAkademmik(int id) {
        realm.beginTransaction();
        RealmResults<Akademik> dataResults = realm.where(Akademik.class).equalTo("id", id).findAll();

        dataResults.deleteAllFromRealm();

        realm.commitTransaction();

        showToast("Hapus data berhasil.");
    }

    /**
     * method menghapus Organisasi berdasarkan id
     *
     * @param id
     */
    public void deleteOrganisasi(int id) {
        realm.beginTransaction();
        RealmResults<Organisasi> dataResults = realm.where(Organisasi.class).equalTo("id", id).findAll();

        dataResults.deleteAllFromRealm();

        realm.commitTransaction();

        showToast("Hapus data berhasil.");
    }

    /**
     * method menghapus Lainnya berdasarkan id
     *
     * @param id
     */
    public void deleteLainnya(int id) {
        realm.beginTransaction();
        RealmResults<Lainnya> dataResults = realm.where(Lainnya.class).equalTo("id", id).findAll();

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
