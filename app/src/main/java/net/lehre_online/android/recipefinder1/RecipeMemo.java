package net.lehre_online.android.recipefinder1;

public class RecipeMemo {

    private String rezept;
    private String beschreibung;
    private long id;


    public RecipeMemo(String rezept, String beschreibung, long id) {
        this.rezept = rezept;
        this.beschreibung = beschreibung;
        this.id = id;
    }


    public String getRezept() {
        return rezept;
    }

    public void setRezept(String rezept) {
        this.rezept = rezept;
    }


    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
