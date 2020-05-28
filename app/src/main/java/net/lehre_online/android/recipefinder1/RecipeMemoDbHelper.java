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

    //TABELLE REZEPT_ZUTATEN
        public static final String TABLE_REZEPT_ZUTATEN = "rezept_zutaten";

        public static final String COLUMN_REZ_ZUT_ID = "rez_zut_id";
        public static final String COLUMN_ID_ZUT = "id_zut";
        public static final String COLUMN_ID_REZ  = "id_rez";
        public static final String COLUMN_ZUT_ANZAHL  = "zut_anzahl";




    //CREATE für Tabelle Rezept
    public static final String SQL_CREATE_REZEPT =
                "CREATE TABLE " + TABLE_REZEPT +
                        "(" + COLUMN_REZ_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_REZ_NAME + " TEXT NOT NULL, " +
                        COLUMN_REZ_BESCHREIBUNG + " TEXT NOT NULL, " +
                        COLUMN_REZ_DAUER + " TEXT NOT NULL, " +
                        COLUMN_REZ_BILD + " BLOB , " +
                        COLUMN_REZ_KALORIEN + " INTEGER NOT NULL);";

    //CREATE für Tabelle Zutaten
    public static final String SQL_CREATE_ZUTATEN =
            "CREATE TABLE " + TABLE_ZUTATEN +
                    "(" + COLUMN_ZUT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ZUT_NAME + " TEXT NOT NULL);";


    //CREATE für Tabelle Rezept_Zutaten
    public static final String SQL_CREATE_REZEPT_ZUTATEN =
            "CREATE TABLE " + TABLE_REZEPT_ZUTATEN +
                    "(" + COLUMN_REZ_ZUT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ID_ZUT + " INTEGER NOT NULL, " +
                    COLUMN_ZUT_ANZAHL + " STRING NOT NULL, " +
                    COLUMN_ID_REZ + " INTEGER NOT NULL, " +
                    "FOREIGN KEY(id_zut) REFERENCES zutaten(zut_id), " +
                    "FOREIGN KEY(id_rez) REFERENCES rezept(rez_id));";



    public RecipeMemoDbHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            Log.d(LOG_TAG, "DbHelper hat die Datenbank: " + getDatabaseName() + " erzeugt.");
            SQLiteDatabase db = this.getWritableDatabase();
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
                onInsert(db);
            }
            catch (Exception ex) {
                Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
            }
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REZEPT + ";");
    }


    //Rezepte Inserts

    //Spaghettie Bolognese
    public static final String SQL_INSERT_SPAGHETTI = "INSERT INTO " + TABLE_REZEPT + "" +
            " (rez_id, rez_name, rez_beschreibung, rez_dauer, rez_kalorien) VALUES (1, 'Spaghetti Bolognese', 'Zwiebeln in Scheiben schneiden, in eine Pfanne geben, salzen und in Öl glasig schmoren . Tomaten vom Strunk befreien und in Scheiben schneiden. Nord und Südpol entfernen. Zu den Zwiebeln geben. Einmal durchrühren und dann einen Teller drüberlegen, sodass der gesamte Inhalt der Pfanne unter dem Teller verborgen ist, und ca. 15 Minuten bei geringer Hitze schmoren lassen.\n" +
            "Das Beefhack in eine zweite Pfanne geben und ohne die Zugabe von Fett braten, wobei mit einer Gabel das Hack auseinander gedrückt wird, bis sich das gesamte Hack in kleine braune Kügelchen oder Klümpchen verwandelt hat. Wenn die Zwiebeln und Tomaten zu einer weichen Masse geschmort sind, das Beefhack dazu geben sowie 4- 5 ungeschälte Knoblauchzehen. \n" +
            "Jetzt die Spaghettis in Wasser mit Salz und einem guten Schuss Olivenöl aufsetzen.\n" +
            "Dann den Inhalt der Tube Tomatenmark in die Pfanne dazugeben sowie nach Bedarf ca. 0.2 L Wasser.\n" +
            "Das Wasser runterkochen lassen und hin- und wieder umrühren. Mit einer guten Portion Olivenöl auffüllen. Abermals aufkochen lassen und etwas Wasser dazu, damit die Soße nicht zu fest wird.\n" +
            "Dann reichlich Oregano dazu, gut salzen und etwas Pfeffer. Zum Schluss noch eine Prise Zucker und bei bedarf noch etwas kleingeschnittener Knoblauch\n" +
            "Die Spaghetti probieren und das Wasser abgießen, bevor sie keinen Wiederstand beim Biss mehr bieten. Spaghetti sowie die Pfanne mit der Soße auf den Tisch und mit geriebenen Parmesankäse servieren. Dazu ein kräftiger Italienischer Rotwein, z. B. ein Amerone\n', '45min', '754');";

    //Hähnchen Geschnetzeltest
    public static final String SQL_INSERT_HÄHNCHEN = "INSERT INTO " + TABLE_REZEPT + "" +
            " (rez_id, rez_name, rez_beschreibung, rez_dauer, rez_kalorien) VALUES (2, 'Hähnchen Geschnetzeltes', 'Die Hähnchenbrustfilets in gleichmäßige Streifen schneiden. \n" +
            "Zwiebel und Paprika in feine Streifen schneiden. Zucchini vierteln, entkernen und in feine Scheiben schneiden. Cocktailtomaten je nach Größe halbieren oder vierteln.\n" +
            "Öl in einer Pfanne erhitzen und das Fleisch darin anbraten. Mit Salz und Pfeffer würzen, dann aus der Pfanne nehmen und warm stellen.\n" +
            "Eventuell noch etwas Öl in die Pfanne geben und darin die Zwiebel mit den gehackten Kräutern anbraten. Den Knoblauch dazu pressen, dann den Zucker darüber verteilen und karamellisieren lassen. \n" +
            "Paprika und Zucchini dazugeben und das Tomatenmark kurz mit anschwitzen. Die Tomaten hinzufügen und das Ganze mit Hühnerfond ablöschen. Kurz köcheln lassen und anschließend mit Salz, Pfeffer und Paprikapulver abschmecken.\n" +
            "Das Fleisch wieder in die Pfanne geben und in der Soße gar ziehen lassen. \n" +
            "Zum Schluss dann Creme fraiche unterrühren und sofort servieren.\n" +
            "Als Beilage passen Reis, Nudeln oder auch Röstis!\n', '30min', '803');";

    //Spaghettie Curry
    public static final String SQL_INSERT_CURRY = "INSERT INTO " + TABLE_REZEPT + "" +
            " (rez_id, rez_name, rez_beschreibung, rez_dauer, rez_kalorien) VALUES (3, 'Annas Massaman-Curry', 'Die Hühnerbrust in feine Streifen schneiden, die Frühlingszwiebeln in Ringe schneiden, die Kartoffeln schälen und in etwa 1 cm große Würfel schneiden, den Ingwer schälen und ganz fein hacken, den Stängel Zitronengras in drei Stücke schneiden.\n" +
            "Den Koriander ebenfalls hacken, dabei Stiele und ggf. Wurzeln (sind nicht bei jedem Bund Koriander dabei, wenn, dann können sie aber bedenkenlos mitgegessen werden) von den Blättern grob trennen. Das muss nicht absolut exakt sein, die Stiele und Wurzeln gare ich nur meist länger mit, damit sie weich werden.\n" +
            "Öl in einem Wok erhitzen, Fleischstreifen darin anbraten. Kartoffelwürfel zugeben und kurz mitbraten. Mit der Kokosmilch ablöschen. Currypaste, Erdnussbutter, Zucker, Essig, Fischsauce, Erdnüsse, Ingwer, Zitronengras, Stiele und Wurzeln vom Koriander zugeben und etwa 40 Minuten bei häufigem Umrühren garen. Wenn zuviel Flüssigkeit verdampft oder die Kartoffeln das Curry zu stark binden, ggf. noch etwas Wasser oder Brühe zufügen.\n" +
            "Am Ende der Garzeit das Zitronengras herausfischen, die Frühlingszwiebeln untermischen, nochmals knapp 5 Minuten köcheln lassen, dann das Koriandergrün dazugeben und servieren.', '60min', '630');";

    //Spaghettie Bauzerl
    public static final String SQL_INSERT_BAUZERL = "INSERT INTO " + TABLE_REZEPT + "" +
            " (rez_id, rez_name, rez_beschreibung, rez_dauer, rez_kalorien) VALUES (4, 'Erdäpfel-Bauzerl', '" +
            "Die geschälten Kartoffeln kochen und abkühlen lassen. Das kann man am Vortag machen. Wenn man ein grobes Sieb hat, kann man dieses nehmen, um die Kartoffeln durchzudrücken, aber das Zerdrücken geht auch ganz einfach mit einer Gabel oder mit einer Kartoffelpresse.\n" +
            "Ei und Mehl dazugeben und am besten mit den Händen mischen. Die Mehlmenge hängt von der Kartoffelart ab. Der Teig sollte halt nicht mehr so stark kleben, damit man ihn formen kann.\n" +
            "Aus dem Teig längliche Würstchen formen und auf eine bemehlte Unterlage legen (damit man sie auch wieder wegnehmen kann). Ein wenig platt drücken, damit sie nicht so dick sind.\n" +
            "In einer Pfanne in Butter goldgelb braten und warm servieren.\n" +
            "Dazu passt am besten Apfelmus. Wer es süß mag, kann sie noch mit Zimt und Zucker bestreuen.\n', '15min', '467');";


    //Spaghettie Pfanne
    public static final String SQL_INSERT_PFANNE = "INSERT INTO " + TABLE_REZEPT + "" +
            " (rez_id, rez_name, rez_beschreibung, rez_dauer, rez_kalorien) VALUES (5, 'Spitzkohl-Champignon-Hack-Pfanne mit Reis', 'Den Reis nach Packungsanweisung gar kochen.\n" +
            "Den Kohl inzwischen in Streifen, die Zwiebel in Würfel und die Champignons in mundgerechte Stücke schneiden.\n" +
            "Das Hackfleisch ohne Zugabe von Fett krümelig braten, mit Salz, Pfeffer und Paprikapulver würzen und beiseitestellen.\n" +
            "Das Öl in der \"Hackfleisch\"-Pfanne erhitzen und die Zwiebelwürfel darin glasig anbraten. Den Kohl zufügen und gut anbraten. Falls das Ganze anzusetzen droht, etwas Wasser angießen. Als nächstes die Champignons in die Pfanne geben und mit anbraten. Nun das Hackfleisch, die Gemüsebrühe, ggfs. noch etwas Wasser, Senf, Majoran und Tomatenmark zufügen und mit Salz, Pfeffer, Paprika und Kümmel abschmecken. Wer mag, kann noch etwas Frischkäse unterrühren. Es schmeckt aber auch ohne sehr gut.\n" +
            "Auf Portionsteller verteilen und mit dem Reis servieren.', '30min', '565');";


    //Zutaten Inserts
    //Hackfleisch
    public static final String SQL_INSERT_HACKFLEISCH = "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (1, 'Hackfleisch')";
    //Tomate
    public static final String SQL_INSERT_TOMATE = "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (2, 'Tomate')";
    //Zwiebel
    public static final String SQL_INSERT_ZWIEBEL = "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (3, 'Zwiebel')";
    //Knoblauch
    public static final String SQL_INSERT_KNOBLAUCH = "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (4, 'Knoblauch')";
    //Tomatenmark
    public static final String SQL_INSERT_TOMATENMARK = "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (5, 'Tomatenmark')";
    //Spaghettie
    public static final String SQL_INSERT_SPAGHETTIS = "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (6, 'Spaghetti')";

    //Rezept_Zutaten
    public static final String SQL_INSERT_SPAGHETTI_HACKFLEISCH = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (1, 1, 1, '250g')";

    public static final String SQL_INSERT_SPAGHETTI_TOMATE = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (2, 2, 1, '3')";

    public static final String SQL_INSERT_SPAGHETTI_ZWIEBEL = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (3, 3, 1, '1')";

    public static final String SQL_INSERT_SPAGHETTI_KNOBLAUCH = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (4, 4, 1, '1/2 Knolle')";

    public static final String SQL_INSERT_SPAGHETTI_TOMATENMARK = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (5, 5, 1, '1/2 Tube')";

    public static final String SQL_INSERT_SPAGHETTI_SPAGHETTI = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (6, 6, 1, '250g')";





    public void onInsert(SQLiteDatabase db){
        try {
            //Rezept
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_SPAGHETTI + " wurde angelegt.");
            db.execSQL(SQL_INSERT_SPAGHETTI);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_HÄHNCHEN + " wurde angelegt.");
            db.execSQL(SQL_INSERT_HÄHNCHEN);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_CURRY + " wurde angelegt.");
            db.execSQL(SQL_INSERT_CURRY);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_BAUZERL + " wurde angelegt.");
            db.execSQL(SQL_INSERT_BAUZERL);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_PFANNE + " wurde angelegt.");
            db.execSQL(SQL_INSERT_PFANNE);

            //Zutaten
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_HACKFLEISCH + " wurde angelegt.");
            db.execSQL(SQL_INSERT_HACKFLEISCH);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_TOMATE + " wurde angelegt.");
            db.execSQL(SQL_INSERT_TOMATE);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_ZWIEBEL + " wurde angelegt.");
            db.execSQL(SQL_INSERT_ZWIEBEL);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_KNOBLAUCH + " wurde angelegt.");
            db.execSQL(SQL_INSERT_KNOBLAUCH);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_TOMATENMARK + " wurde angelegt.");
            db.execSQL(SQL_INSERT_TOMATENMARK);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_SPAGHETTIS + " wurde angelegt.");
            db.execSQL(SQL_INSERT_SPAGHETTIS);

            //RezeptZutaten
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_SPAGHETTI_HACKFLEISCH + " wurde angelegt.");
            db.execSQL(SQL_INSERT_SPAGHETTI_HACKFLEISCH);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_SPAGHETTI_TOMATE + " wurde angelegt.");
            db.execSQL(SQL_INSERT_SPAGHETTI_TOMATE);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_SPAGHETTI_ZWIEBEL + " wurde angelegt.");
            db.execSQL(SQL_INSERT_SPAGHETTI_ZWIEBEL);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_SPAGHETTI_KNOBLAUCH + " wurde angelegt.");
            db.execSQL(SQL_INSERT_SPAGHETTI_KNOBLAUCH);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_SPAGHETTI_TOMATENMARK + " wurde angelegt.");
            db.execSQL(SQL_INSERT_SPAGHETTI_TOMATENMARK);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_SPAGHETTI_SPAGHETTI + " wurde angelegt.");
            db.execSQL(SQL_INSERT_SPAGHETTI_SPAGHETTI);



        } catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }

        }


    }