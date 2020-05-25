package net.lehre_online.android.recipefinder1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RecipeMemoDbHelper extends SQLiteOpenHelper{

        private static final String LOG_TAG = RecipeMemoDbHelper.class.getSimpleName();


        public static final String DB_NAME = "rezept_list.db";
        public static final int DB_VERSION = 1;


        //TABELLE REZEPT
        public static final String TABLE_REZEPT = "rezept";

        public static final String COLUMN_REZ_ID = "rez_id";
        public static final String COLUMN_REZ_NAME = "rez_name";
        public static final String COLUMN_REZ_BESCHREIBUNG = "rez_beschreibung";
        public static final String COLUMN_REZ_DAUER = "rez_dauer";
        public static final String COLUMN_REZ_BILD = "rez_bild";
        public static final String COLUMN_REZ_KALORIEN = "rez_kalorien";

         //TABELLE ZUTATEN
        public static final String TABLE_ZUTATEN = "zutaten";

        public static final String COLUMN_ZUT_ID = "zut_id";
        public static final String COLUMN_ZUT_NAME = "zut_name";
        public static final String COLUMN_ZUT_KALORIEN  = "zut_kalorien";

    //TABELLE REZEPT_ZUTATEN
        public static final String TABLE_REZEPT_ZUTATEN = "rezept_zutaten";

        public static final String COLUMN_REZ_ZUT_ID = "rez_zut_id";
        public static final String COLUMN_ID_ZUT = "id_zut";
        public static final String COLUMN_ID_REZ  = "id_rez";



//CREATE für Tabelle Rezept
    public static final String SQL_CREATE_REZEPT =
                "CREATE TABLE " + TABLE_REZEPT +
                        "(" + COLUMN_REZ_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_REZ_NAME + " TEXT NOT NULL, " +
                        COLUMN_REZ_BESCHREIBUNG + " TEXT NOT NULL, " +
                        COLUMN_REZ_DAUER + " TEXT NOT NULL, " +
                        COLUMN_REZ_BILD + " BLOB NOT NULL, " +
                        COLUMN_REZ_KALORIEN + " INTEGER NOT NULL + );";

    //CREATE für Tabelle Zutaten
    public static final String SQL_CREATE_ZUTATEN =
            "CREATE TABLE " + TABLE_ZUTATEN +
                    "(" + COLUMN_ZUT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ZUT_NAME + " TEXT NOT NULL, " +
                    COLUMN_ZUT_KALORIEN + " INTEGER NOT NULL + );";

    //CREATE für Tabelle Rezept_Zutaten
    public static final String SQL_CREATE_REZEPT_ZUTATEN =
            "CREATE TABLE " + TABLE_REZEPT_ZUTATEN +
                    "(" + COLUMN_REZ_ZUT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ID_ZUT + " INTEGER   FOREIGN KEY(id_zut) REFERENCES zutaten(zut_id)" +
                    COLUMN_ID_REZ + " INTEGER   FOREIGN KEY(id_rez) REFERENCES rezept(rez_id) + );";




    public RecipeMemoDbHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
        }

        // Die onCreate-Methode wird nur aufgerufen, falls die Datenbank noch nicht existiert
        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_REZEPT + " angelegt.");
                db.execSQL(SQL_CREATE_REZEPT);
                Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_ZUTATEN + " angelegt.");
                db.execSQL(SQL_CREATE_ZUTATEN);
                Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_REZEPT_ZUTATEN + " angelegt.");
                db.execSQL(SQL_CREATE_REZEPT_ZUTATEN);

            }
            catch (Exception ex) {
                Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }


    }

