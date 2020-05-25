package net.lehre_online.android.recipefinder1;

import java.sql.Blob;

public class Rezept_ZutatenMemo {

    private long rez_zut_id;
    private long id_zut;
    private long id_rez;


    public Rezept_ZutatenMemo(long rez_zut_id, long id_zut, long id_rez) {
        this.rez_zut_id = rez_zut_id;
        this.id_zut = id_zut;
        this.id_rez= id_rez;
    }

    public long getRez_zut_id() {
        return rez_zut_id;
    }

    public void setRez_zut_id(long rez_zut_id) {
        this.rez_zut_id = rez_zut_id;
    }

    public long getId_zut() {
        return id_zut;
    }

    public void setId_zut(long id_zut) {
        this.id_zut = id_zut;
    }

    public long getId_rez() {
        return id_rez;
    }

    public void setId_rez(long id_rez) {
        this.id_rez = id_rez;
    }
}
