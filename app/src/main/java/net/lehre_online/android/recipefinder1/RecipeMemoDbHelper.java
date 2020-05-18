package net.lehre_online.android.recipefinder1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

    public class RecipeMemoDbHelper extends SQLiteOpenHelper{

        private static final String LOG_TAG = RecipeMemoDbHelper.class.getSimpleName();


        public RecipeMemoDbHelper(Context context) {
            super(context, "PLATZHALTER_DATENBANKNAME", null, 1);
            Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

