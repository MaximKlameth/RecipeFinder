package net.lehre_online.android.recipefinder1;

/**Diese Klasse dient zur Erstellung von Zutaten Objekten.
 * Die Klasse wird in der aktuellen Version nicht benutzt, da die Zutaten direkt aus der DB in einer Liste abgelegt werden.
 * Da die Klasse nicht verwendet wird ist diese auch nicht weiterhin erklärt
 * @author Kevin Giesen
 * @version 05.07.2020
 */
public class ZutatenMemo {

    private long zut_id;
    private String zut_name;
    private long zut_kalorien;

    public ZutatenMemo(long zut_id, String zut_name, long zut_kalorien) {
        this.zut_id=zut_id;
        this.zut_name = zut_name;
        this.zut_kalorien = zut_kalorien;
    }
    public long getZut_id() {
        return zut_id;
    }

    public void setZut_id(long zut_id) {
        this.zut_id = zut_id;
    }

    public String getZut_name() {
        return zut_name;
    }

    public void setZut_name(String zut_name) {
        this.zut_name = zut_name;
    }

    public long getZut_kalorien() {
        return zut_kalorien;
    }

    public void setZut_kalorien(long zut_kalorien) {
        this.zut_kalorien = zut_kalorien;
    }
}
