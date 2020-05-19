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

        public static final String COLUMN_ID = "rez_id";
        public static final String COLUMN_NAME = "rez_name";
        public static final String COLUMN_BESCHREIBUNG = "rez_beschreibung";
        public static final String COLUMN_DAUER = "rez_dauer";
        public static final String COLUMN_BILD = "rez_bild";
        public static final String COLUMN_KALORIEN = "rez_kalorien";

         //TABELLE ZUTATEN



    public static final String SQL_CREATE_REZEPT =
                "CREATE TABLE " + TABLE_REZEPT +
                        "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT NOT NULL, " +
                        COLUMN_BESCHREIBUNG + " TEXT NOT NULL, " +
                        COLUMN_DAUER + " TEXT NOT NULL, " +
                        COLUMN_BILD + " BLOB NOT NULL, " +
                        COLUMN_KALORIEN + " INTEGER NOT NULL + );";


        public RecipeMemoDbHelper(Context context) {
            //super(context, "PLATZHALTER_DATENBANKNAME", null, 1);
            super(context, DB_NAME, null, DB_VERSION);
            Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
        }

        // Die onCreate-Methode wird nur aufgerufen, falls die Datenbank noch nicht existiert
        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_REZEPT + " angelegt.");
                db.execSQL(SQL_CREATE_REZEPT);

            }
            catch (Exception ex) {
                Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }


    }

