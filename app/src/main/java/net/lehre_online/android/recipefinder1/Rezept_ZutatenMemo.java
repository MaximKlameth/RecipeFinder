package net.lehre_online.android.recipefinder1;

import java.sql.Blob;

/**Diese Klasse dient zur Erstellung von Rezept-Zutaten Objekten.
 * Die Klasse wird in der aktuellen Version nicht benutzt, da die Rezepte-Zutaten direkt aus der DB verarbeitet werden.
 * Da die Klasse nicht verwendet wird ist diese auch nicht weiterhin erklärt
 * @author Kevin Giesen
 * @version 05.07.2020
 */
public class Rezept_ZutatenMemo {

    private long rez_zut_id;
    private long id_zut;
    private long id_rez;
    private String zut_anzahl;


    public Rezept_ZutatenMemo(long rez_zut_id, long id_zut, long id_rez, String zut_anzahl) {
        this.rez_zut_id = rez_zut_id;
        this.id_zut = id_zut;
        this.id_rez= id_rez;
        this.zut_anzahl = zut_anzahl;
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

    public String getZut_anzahl() { return zut_anzahl; }

    public void setZut_anzahl(String zut_anzahl) {
        this.zut_anzahl = zut_anzahl;
    }

}
