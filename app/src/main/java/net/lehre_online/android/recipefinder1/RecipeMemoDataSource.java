package net.lehre_online.android.recipefinder1;

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.util.Log;

/**Diese Klasse dient zur Unterstützung bei der Db-Verbindung
 * Die Klasse wird in der aktuellen Version nicht benutzt, da Db-Verbindung direkt über RecipeMemoDbHelper hergestellt wird
 * Da die Klasse nicht verwendet wird ist diese auch nicht weiterhin erklärt
 * @author Kevin Giesen
 * @version 05.07.2020
 */
public class RecipeMemoDataSource {

    private static final String LOG_TAG = RecipeMemoDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private RecipeMemoDataSource dbHelper;


    public RecipeMemoDataSource(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den dbHelper.");
        dbHelper = new RecipeMemoDataSource(context);
    }
}