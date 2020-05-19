package net.lehre_online.android.recipefinder1;

import java.sql.Blob;

public class RecipeMemo {

    private long rez_id;
    private String rez_name;
    private String rez_beschreibung;
    private String rez_dauer;
    private Blob rez_bild;
    private long rez_kalorien;




    public RecipeMemo(long rez_id,String rez_name, String rez_beschreibung, String rez_dauer, Blob rez_bild, long rez_kalorien) {
        this.rez_name = rez_name;
        this.rez_beschreibung = rez_beschreibung;
        this.rez_id = rez_id;
        this.rez_dauer = rez_dauer;
        this.rez_bild= rez_bild;
        this.rez_kalorien = rez_kalorien;
    }

    public long getRez_id() {
        return rez_id;
    }

    public void setRez_id(long rez_id) {
        this.rez_id = rez_id;
    }


    public String getRez_name() {
        return rez_name;
    }

    public void setRez_name(String rez_name) {
        this.rez_name = rez_name;
    }


    public String getRez_beschreibung() {
        return rez_beschreibung;
    }

    public void setRez_beschreibung(String rez_beschreibung) {
        this.rez_beschreibung = rez_beschreibung;
    }

    public String getRez_dauer(){
        return rez_dauer;
    }

    public void setRez_dauer(){
        this.rez_dauer = rez_dauer;
    }

    public Blob getRez_bild(){
        return rez_bild;
    }
    public void setRez_bild(){
        this.rez_bild = rez_bild;
    }

    public long getRez_kalorien(){
        return rez_kalorien;
    }

    public void setRez_kalorien(){
        this.rez_kalorien = rez_kalorien;
    }



}
