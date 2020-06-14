package net.lehre_online.android.recipefinder1;

import android.content.Context;
import android.database.Cursor;
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

        //Tabelle Mail Rezepte
    public static final String TABLE_REZEPT_MAIL = "rezept_mail";

    public static final String COLUMN_REZ_MAIL_ID = "rez_mail_id";
    public static final String COLUMN_MAIL = "rez_mail";
    public static final String COLUMN_NAME_REZ  = "name_rez";



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

    //CREATE für Tabelle Rezept_Mail
    public static final String SQL_CREATE_REZEPT_MAIL =
            "CREATE TABLE " + TABLE_REZEPT_MAIL +
                    "(" + COLUMN_REZ_MAIL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_MAIL + " TEXT NOT NULL, " +
                    COLUMN_NAME_REZ + " TEXT NOT NULL, " +
                    "FOREIGN KEY(name_rez) REFERENCES rezept(rez_name));";



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
                Log.d(LOG_TAG, "Die Tabelle wird mit SQL-Befehl: " + SQL_CREATE_REZEPT_MAIL + " angelegt.");
                db.execSQL(SQL_CREATE_REZEPT_MAIL);
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

         public Cursor getAllRecipes(){
         SQLiteDatabase db = this.getWritableDatabase();
         Cursor res = db.rawQuery("Select rez_id, rez_name from " + TABLE_REZEPT, null);
         return res;
    }

    public Cursor getRecipeFit(String zut1, String zut2, String zut3, String zut4, String zut5 ){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor fitRec = db.rawQuery("Select DISTINCT rez_name from " + TABLE_REZEPT + " Join " + TABLE_REZEPT_ZUTATEN +
          " ON rez_id = id_rez Join " + TABLE_ZUTATEN + " ON id_zut = zut_id WHERE zut_name like '" + zut1 +
          "' OR zut_name like '" + zut2 +
          "' OR zut_name like '" + zut3 +
          "' OR zut_name like '" + zut4 +
                "' OR zut_name like '" + zut5 + "';" , null);
        return fitRec;
}

    public Cursor getZutatenFit(String rezept){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor fitZut = db.rawQuery("Select zut_name, zut_anzahl from " + TABLE_REZEPT + " Join " + TABLE_REZEPT_ZUTATEN +
                " ON rez_id = id_rez Join " + TABLE_ZUTATEN + " ON id_zut = zut_id WHERE rez_name like '" + rezept + "';" , null);
        return fitZut;
    }

    public Cursor getBeschreibung(String rezept){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor fitBez = db.rawQuery("Select rez_beschreibung from " + TABLE_REZEPT + " WHERE rez_name like '" + rezept + "';" , null);
        return fitBez;
    }

    public Cursor getRecipeMailFit(String mail){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor fitRezMail = db.rawQuery("Select rez_name from " + TABLE_REZEPT + " Join " + TABLE_REZEPT_MAIL + " ON rez_name = name_rez WHERE rez_mail like '" + mail + "';" , null);
        return fitRezMail;
    }


    public void safeRecipe(String mail, String rezeptid){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d(LOG_TAG, "Der Datensatz: " + mail + rezeptid + " wurde angelegt.");
        db.execSQL("INSERT INTO " + TABLE_REZEPT_MAIL +  " (rez_mail, name_rez) VALUES('" + mail + "', '" + rezeptid + "' );");
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

    // Curry
    public static final String SQL_INSERT_CURRY = "INSERT INTO " + TABLE_REZEPT + "" +
            " (rez_id, rez_name, rez_beschreibung, rez_dauer, rez_kalorien) VALUES (3, 'Annas Massaman-Curry', 'Die Hühnerbrust in feine Streifen schneiden, die Frühlingszwiebeln in Ringe schneiden, die Kartoffeln schälen und in etwa 1 cm große Würfel schneiden, den Ingwer schälen und ganz fein hacken, den Stängel Zitronengras in drei Stücke schneiden.\n" +
            "Den Koriander ebenfalls hacken, dabei Stiele und ggf. Wurzeln (sind nicht bei jedem Bund Koriander dabei, wenn, dann können sie aber bedenkenlos mitgegessen werden) von den Blättern grob trennen. Das muss nicht absolut exakt sein, die Stiele und Wurzeln gare ich nur meist länger mit, damit sie weich werden.\n" +
            "Öl in einem Wok erhitzen, Fleischstreifen darin anbraten. Kartoffelwürfel zugeben und kurz mitbraten. Mit der Kokosmilch ablöschen. Currypaste, Erdnussbutter, Zucker, Essig, Fischsauce, Erdnüsse, Ingwer, Zitronengras, Stiele und Wurzeln vom Koriander zugeben und etwa 40 Minuten bei häufigem Umrühren garen. Wenn zuviel Flüssigkeit verdampft oder die Kartoffeln das Curry zu stark binden, ggf. noch etwas Wasser oder Brühe zufügen.\n" +
            "Am Ende der Garzeit das Zitronengras herausfischen, die Frühlingszwiebeln untermischen, nochmals knapp 5 Minuten köcheln lassen, dann das Koriandergrün dazugeben und servieren.', '60min', '630');";

    // Bauzerl
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

    // für Spagetthie
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

    //Für Hähnchen Geschnetzeltes
    public static final String SQL_INSERT_HAENCHENBRUST= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (7, 'Hähnchenbrustfilet')";
    public static final String SQL_INSERT_PAPRIKA= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (8, 'Paprika')";
    public static final String SQL_INSERT_ZUCCHINI= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (9, 'Zucchini')";
    public static final String SQL_INSERT_ROSMARIN= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (10, 'Rosmarin')";
    public static final String SQL_INSERT_THYMIAN= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (11, 'Thymian')";
    public static final String SQL_INSERT_HUEHNERFOND= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (12, 'Hühnerfond')";
    public static final String SQL_INSERT_ZUCKER= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (13, 'Zucker')";
    public static final String SQL_INSERT_CREME= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (14, 'Creme fraiche')";


    //Für Curry
    public static final String SQL_INSERT_FRUEHLINGSZWIEBEL= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (15, 'Frühlingszwiebel(n)')";
    public static final String SQL_INSERT_CURRYPASTE= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (16, 'Currypaste')";
    public static final String SQL_INSERT_KOKOSMILCH= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (17, 'Kokosmilch')";
    public static final String SQL_INSERT_ERDNUSSBUTTER= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (18, 'Erdnussbutter')";
    public static final String SQL_INSERT_ESSIG= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (19, 'Essig')";
    public static final String SQL_INSERT_FISCHSAUCE= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (20, 'Fischsauce')";
    public static final String SQL_INSERT_ERDNUESSE= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (21, 'Erdnüsse')";
    public static final String SQL_INSERT_KARTOFFEL= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (22, 'Kartoffel(n)')";
    public static final String SQL_INSERT_INGWER= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (23, 'Ingwer')";
    public static final String SQL_INSERT_KORIANDER= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (24, 'Koriander')";
    public static final String SQL_INSERT_ZITRONENGRAS= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (25, 'Zitronengras')";


    //Für Bautzerl
    public static final String SQL_INSERT_EIER= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (26, 'Ei(er)')";
    public static final String SQL_INSERT_MEHL= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (27, 'Mehl')";
    public static final String SQL_INSERT_BUTTER= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (28, 'Butter')";

    //Für Spitzkohl-Champignon-Hackpfanne mit Reis
    public static final String SQL_INSERT_REIS= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (29, 'Reis')";
    public static final String SQL_INSERT_SPITZKOHL= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (30, 'Spitzkohl')";
    public static final String SQL_INSERT_CHAMPIGNONS= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (31, 'Champignons')";
    public static final String SQL_INSERT_GEMUESEBRUEHE= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (32, 'Gemüsebrühe')";
    public static final String SQL_INSERT_SENF= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (33, 'Senf')";
    public static final String SQL_INSERT_MAJORAN= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (34, 'Majoran')";
    public static final String SQL_INSERT_PAPRIKAPULVER= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (35, 'Paprikapulver')";
    public static final String SQL_INSERT_KUEMMEL= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (36, 'Kümmel')";
    public static final String SQL_INSERT_FRISCHKAESE= "INSERT INTO " + TABLE_ZUTATEN +
            "(zut_id, zut_name) VALUES (37, 'Frischkäse')";





    //Rezept_Zutaten
    //Spaghettie
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

    //Hähnchen Geschnetzeltes
    public static final String SQL_INSERT_HAENCHEN_HAENCHEN = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (7, 7, 2, '400g')";
    public static final String SQL_INSERT_HAENCHEN_PAPRIKA = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (8, 8, 2, '1')";
    public static final String SQL_INSERT_HAENCHEN_ZUCCINI = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (9, 9, 2, '1')";
    public static final String SQL_INSERT_HAENCHEN_ZWIEBEL = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (10, 3, 2, '1')";
    public static final String SQL_INSERT_HAENCHEN_TOMATE = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (11, 2, 2, '150g')";
    public static final String SQL_INSERT_HAENCHEN_KNOBLAUCH = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (12, 4, 2, '1/2 Zehen')";
    public static final String SQL_INSERT_HAENCHEN_ROSMARIN = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (13, 10, 2, '2 Zweige')";
    public static final String SQL_INSERT_HAENCHEN_THYMIAN = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (14, 11, 2, '1 TL')";
    public static final String SQL_INSERT_HAENCHEN_TOMATENMARK = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (15, 5, 2, '1/2 Tube')";
    public static final String SQL_INSERT_HAENCHEN_HUEHNERFOND = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (16, 12, 2, '50 ml')";
    public static final String SQL_INSERT_HAENCHEN_ZUCKER = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (17, 13, 2, '1 TL')";
    public static final String SQL_INSERT_HAENCHEN_CREME = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (18, 14, 2, '2 EL')";


    //Curry
    public static final String SQL_INSERT_CURRY_HAENCHEN = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (19, 7, 3, '250g')";
    public static final String SQL_INSERT_CURRY_FRUEHLINGSZWIEBEL = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (20, 15, 3, '3-4 Stück')";
    public static final String SQL_INSERT_CURRY_CURRYPASTE = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (21, 16, 3, '3 EL')";
    public static final String SQL_INSERT_CURRY_KOKUSMILCH = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (22, 17, 3, '200ml')";
    public static final String SQL_INSERT_CURRY_ERDNUSSBUTTER = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (23, 18, 3, '1 EL')";
    public static final String SQL_INSERT_CURRY_ZUCKER = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (24, 13, 3, '1/2 EL')";
    public static final String SQL_INSERT_CURRY_ESSIG = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (25, 19, 3, '1/2 EL')";
    public static final String SQL_INSERT_CURRY_FISCHSAUCE = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (26, 20, 3, '2 EL')";
    public static final String SQL_INSERT_CURRY_ERDNÜSSE = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (27, 21, 3, '50g')";
    public static final String SQL_INSERT_CURRY_KARTOFFEL = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (28, 22, 3, '2')";
    public static final String SQL_INSERT_CURRY_INGWER = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (29, 23, 3, '1 1/2cm')";
    public static final String SQL_INSERT_CURRY_KORIANDER = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (30, 24, 3, '1/4 Bund')";
    public static final String SQL_INSERT_CURRY_ZITRONENGRAS = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (31, 25, 3, '1/2 Stängel')";


    //Bautzerl
    public static final String SQL_INSERT_BAUTZERL_KARTOFFELN = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (32, 22, 4, '8')";
    public static final String SQL_INSERT_BAUTZERL_EIER = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (33, 26, 4, '1')";
    public static final String SQL_INSERT_BAUTZERL_MEHL = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (34, 27, 4, '5EL')";
    public static final String SQL_INSERT_BAUTZERL_BUTTER = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (35, 28, 4, '30g')";


    //Für Spitzkohl-Champignon-Hackpfanne mit Reis
    public static final String SQL_INSERT_PFANNE_REIS = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (36, 29, 5, '100g')";
    public static final String SQL_INSERT_PFANNE_HACKFLEISCH = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (37, 1, 5, '400g')";
    public static final String SQL_INSERT_PFANNE_SPITZKOHL = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (38, 30, 5, '500g')";
    public static final String SQL_INSERT_PFANNE_CHAMPIGNONS = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (39, 31, 5, '250g')";
    public static final String SQL_INSERT_PFANNE_ZWIEBELN = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (40, 3, 5, '1')";
    public static final String SQL_INSERT_PFANNE_GEMUESEBRUEHE = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (41, 32, 5, '1 EL')";
    public static final String SQL_INSERT_PFANNE_SENF = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (42, 33, 5, '1 TL')";
    public static final String SQL_INSERT_PFANNE_MAJORAN = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (43, 34, 5, '1TL')";
    public static final String SQL_INSERT_PFANNE_PAPRIKAPULVER = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (44, 35, 5, '1TL')";
    public static final String SQL_INSERT_PFANNE_KUEMMEL = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (45, 36, 5, '1TL')";
    public static final String SQL_INSERT_PFANNE_TOMATENMARK = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (46, 5, 5, '2 EL')";
    public static final String SQL_INSERT_PFANNE_FRISCHKAESE = "INSERT INTO " + TABLE_REZEPT_ZUTATEN +
            "(rez_zut_id, id_zut, id_rez, zut_anzahl) VALUES (47, 37, 5, '2 EL')";




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
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_HAENCHENBRUST + " wurde angelegt.");
            db.execSQL(SQL_INSERT_HAENCHENBRUST);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_PAPRIKA + " wurde angelegt.");
            db.execSQL(SQL_INSERT_PAPRIKA);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_ZUCCHINI + " wurde angelegt.");
            db.execSQL(SQL_INSERT_ZUCCHINI);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_ROSMARIN + " wurde angelegt.");
            db.execSQL(SQL_INSERT_ROSMARIN);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_THYMIAN + " wurde angelegt.");
            db.execSQL(SQL_INSERT_THYMIAN);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_HUEHNERFOND + " wurde angelegt.");
            db.execSQL(SQL_INSERT_HUEHNERFOND);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_ZUCKER + " wurde angelegt.");
            db.execSQL(SQL_INSERT_ZUCKER);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_CREME + " wurde angelegt.");
            db.execSQL(SQL_INSERT_CREME);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_FRUEHLINGSZWIEBEL + " wurde angelegt.");
            db.execSQL(SQL_INSERT_FRUEHLINGSZWIEBEL);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_CURRYPASTE + " wurde angelegt.");
            db.execSQL(SQL_INSERT_CURRYPASTE);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_KOKOSMILCH + " wurde angelegt.");
            db.execSQL(SQL_INSERT_KOKOSMILCH);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_ERDNUSSBUTTER + " wurde angelegt.");
            db.execSQL(SQL_INSERT_ERDNUSSBUTTER);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_ESSIG + " wurde angelegt.");
            db.execSQL(SQL_INSERT_ESSIG);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_FISCHSAUCE + " wurde angelegt.");
            db.execSQL(SQL_INSERT_FISCHSAUCE);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_ERDNUESSE + " wurde angelegt.");
            db.execSQL(SQL_INSERT_ERDNUESSE);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_KARTOFFEL + " wurde angelegt.");
            db.execSQL(SQL_INSERT_KARTOFFEL);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_INGWER + " wurde angelegt.");
            db.execSQL(SQL_INSERT_INGWER);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_KORIANDER + " wurde angelegt.");
            db.execSQL(SQL_INSERT_KORIANDER);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_ZITRONENGRAS + " wurde angelegt.");
            db.execSQL(SQL_INSERT_ZITRONENGRAS);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_EIER + " wurde angelegt.");
            db.execSQL(SQL_INSERT_EIER);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_MEHL + " wurde angelegt.");
            db.execSQL(SQL_INSERT_MEHL);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_BUTTER + " wurde angelegt.");
            db.execSQL(SQL_INSERT_BUTTER);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_REIS + " wurde angelegt.");
            db.execSQL(SQL_INSERT_REIS);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_SPITZKOHL + " wurde angelegt.");
            db.execSQL(SQL_INSERT_SPITZKOHL);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_CHAMPIGNONS + " wurde angelegt.");
            db.execSQL(SQL_INSERT_CHAMPIGNONS);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_GEMUESEBRUEHE + " wurde angelegt.");
            db.execSQL(SQL_INSERT_GEMUESEBRUEHE);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_SENF + " wurde angelegt.");
            db.execSQL(SQL_INSERT_SENF);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_MAJORAN + " wurde angelegt.");
            db.execSQL(SQL_INSERT_MAJORAN);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_PAPRIKAPULVER + " wurde angelegt.");
            db.execSQL(SQL_INSERT_PAPRIKAPULVER);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_KUEMMEL + " wurde angelegt.");
            db.execSQL(SQL_INSERT_KUEMMEL);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_FRISCHKAESE + " wurde angelegt.");
            db.execSQL(SQL_INSERT_FRISCHKAESE);



            //RezeptZutaten
            //Spaghettie Bolo
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

            //Hähnchen
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_HAENCHEN_HAENCHEN + " wurde angelegt.");
            db.execSQL(SQL_INSERT_HAENCHEN_HAENCHEN);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_HAENCHEN_PAPRIKA + " wurde angelegt.");
            db.execSQL(SQL_INSERT_HAENCHEN_PAPRIKA);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_HAENCHEN_ZUCCINI + " wurde angelegt.");
            db.execSQL(SQL_INSERT_HAENCHEN_ZUCCINI);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_HAENCHEN_ZWIEBEL + " wurde angelegt.");
            db.execSQL(SQL_INSERT_HAENCHEN_ZWIEBEL);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_HAENCHEN_TOMATE + " wurde angelegt.");
            db.execSQL(SQL_INSERT_HAENCHEN_TOMATE);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_HAENCHEN_KNOBLAUCH + " wurde angelegt.");
            db.execSQL(SQL_INSERT_HAENCHEN_KNOBLAUCH);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_HAENCHEN_ROSMARIN + " wurde angelegt.");
            db.execSQL(SQL_INSERT_HAENCHEN_ROSMARIN);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_HAENCHEN_THYMIAN + " wurde angelegt.");
            db.execSQL(SQL_INSERT_HAENCHEN_THYMIAN);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_HAENCHEN_TOMATENMARK + " wurde angelegt.");
            db.execSQL(SQL_INSERT_HAENCHEN_TOMATENMARK);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_HAENCHEN_HUEHNERFOND + " wurde angelegt.");
            db.execSQL(SQL_INSERT_HAENCHEN_HUEHNERFOND);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_HAENCHEN_ZUCKER + " wurde angelegt.");
            db.execSQL(SQL_INSERT_HAENCHEN_ZUCKER);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_HAENCHEN_CREME + " wurde angelegt.");
            db.execSQL(SQL_INSERT_HAENCHEN_CREME);

            //Curry
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_CURRY_HAENCHEN + " wurde angelegt.");
            db.execSQL(SQL_INSERT_CURRY_HAENCHEN);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_CURRY_FRUEHLINGSZWIEBEL + " wurde angelegt.");
            db.execSQL(SQL_INSERT_CURRY_FRUEHLINGSZWIEBEL);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_CURRY_CURRYPASTE + " wurde angelegt.");
            db.execSQL(SQL_INSERT_CURRY_CURRYPASTE);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_CURRY_KOKUSMILCH + " wurde angelegt.");
            db.execSQL(SQL_INSERT_CURRY_KOKUSMILCH);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_CURRY_ERDNUSSBUTTER + " wurde angelegt.");
            db.execSQL(SQL_INSERT_CURRY_ERDNUSSBUTTER);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_CURRY_ZUCKER + " wurde angelegt.");
            db.execSQL(SQL_INSERT_CURRY_ZUCKER);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_CURRY_ESSIG + " wurde angelegt.");
            db.execSQL(SQL_INSERT_CURRY_ESSIG);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_CURRY_FISCHSAUCE + " wurde angelegt.");
            db.execSQL(SQL_INSERT_CURRY_FISCHSAUCE);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_CURRY_ERDNÜSSE + " wurde angelegt.");
            db.execSQL(SQL_INSERT_CURRY_ERDNÜSSE);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_CURRY_KARTOFFEL + " wurde angelegt.");
            db.execSQL(SQL_INSERT_CURRY_KARTOFFEL);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_CURRY_INGWER + " wurde angelegt.");
            db.execSQL(SQL_INSERT_CURRY_INGWER);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_CURRY_KORIANDER + " wurde angelegt.");
            db.execSQL(SQL_INSERT_CURRY_KORIANDER);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_CURRY_ZITRONENGRAS + " wurde angelegt.");
            db.execSQL(SQL_INSERT_CURRY_ZITRONENGRAS);

            //Bauzerl
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_BAUTZERL_KARTOFFELN + " wurde angelegt.");
            db.execSQL(SQL_INSERT_BAUTZERL_KARTOFFELN);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_BAUTZERL_EIER + " wurde angelegt.");
            db.execSQL(SQL_INSERT_BAUTZERL_EIER);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_BAUTZERL_MEHL + " wurde angelegt.");
            db.execSQL(SQL_INSERT_BAUTZERL_MEHL);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_BAUTZERL_BUTTER + " wurde angelegt.");
            db.execSQL(SQL_INSERT_BAUTZERL_BUTTER);


            //Für Spitzkohl-Champignon-Hackpfanne mit Reis
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_PFANNE_REIS + " wurde angelegt.");
            db.execSQL(SQL_INSERT_PFANNE_REIS);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_PFANNE_HACKFLEISCH + " wurde angelegt.");
            db.execSQL(SQL_INSERT_PFANNE_HACKFLEISCH);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_PFANNE_SPITZKOHL + " wurde angelegt.");
            db.execSQL(SQL_INSERT_PFANNE_SPITZKOHL);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_PFANNE_CHAMPIGNONS + " wurde angelegt.");
            db.execSQL(SQL_INSERT_PFANNE_CHAMPIGNONS);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_PFANNE_ZWIEBELN + " wurde angelegt.");
            db.execSQL(SQL_INSERT_PFANNE_ZWIEBELN);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_PFANNE_GEMUESEBRUEHE + " wurde angelegt.");
            db.execSQL(SQL_INSERT_PFANNE_GEMUESEBRUEHE);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_PFANNE_SENF + " wurde angelegt.");
            db.execSQL(SQL_INSERT_PFANNE_SENF);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_PFANNE_MAJORAN + " wurde angelegt.");
            db.execSQL(SQL_INSERT_PFANNE_MAJORAN);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_PFANNE_PAPRIKAPULVER + " wurde angelegt.");
            db.execSQL(SQL_INSERT_PFANNE_PAPRIKAPULVER);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_PFANNE_KUEMMEL + " wurde angelegt.");
            db.execSQL(SQL_INSERT_PFANNE_KUEMMEL);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_PFANNE_TOMATENMARK + " wurde angelegt.");
            db.execSQL(SQL_INSERT_PFANNE_TOMATENMARK);
            Log.d(LOG_TAG, "Der Datensatz: " + SQL_INSERT_PFANNE_FRISCHKAESE + " wurde angelegt.");
            db.execSQL(SQL_INSERT_PFANNE_FRISCHKAESE);




        } catch (Exception ex) {
            Log.e(LOG_TAG, "Fehler beim Anlegen der Tabelle: " + ex.getMessage());
        }

        }


    }