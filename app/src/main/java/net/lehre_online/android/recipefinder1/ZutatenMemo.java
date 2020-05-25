package net.lehre_online.android.recipefinder1;

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
