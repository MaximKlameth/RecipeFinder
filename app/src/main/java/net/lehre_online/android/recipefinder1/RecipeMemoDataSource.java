package net.lehre_online.android.recipefinder1;

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.util.Log;


public class RecipeMemoDataSource {

    private static final String LOG_TAG = RecipeMemoDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private RecipeMemoDataSource dbHelper;


    public RecipeMemoDataSource(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den dbHelper.");
        dbHelper = new RecipeMemoDataSource(context);
    }
}