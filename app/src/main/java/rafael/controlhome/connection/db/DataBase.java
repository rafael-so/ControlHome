package rafael.controlhome.connection.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    private static DataBase DataBase;
    private static final String NOME_BASE_DE_DADOS   = "uhul.db";
    private static final int    VERSAO_BASE_DE_DADOS = 1;

    public DataBase(Context context){
        super(context,NOME_BASE_DE_DADOS,null,VERSAO_BASE_DE_DADOS);
    }

    public static DataBase getInstance(Context context){
        if(DataBase == null)
            DataBase = new DataBase(context);
        return DataBase;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        criarTabela(db);
        insertTabela(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS estacao");
        onCreate(db);

    }

    public SQLiteDatabase getConexaoDataBase(){

        return this.getWritableDatabase();
    }

    public void criarTabela(SQLiteDatabase db){
        StringBuilder stringBuilderCreateTable1 = new StringBuilder();
        //StringBuilder stringBuilderCreateTable2 = new StringBuilder();

        stringBuilderCreateTable1.append("CREATE TABLE ENDERECO_IP ( " +
                "ID      INTEGER PRIMARY KEY AUTOINCREMENT," +
                "IP      TEXT    NOT NULL" +
                ");");
//        stringBuilderCreateTable2.append("CREATE TABLE REGISTRO ( " +
//                "ID             INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "DATA           TEXT    NOT NULL, "  +
//                "STATUS         TEXT    NOT NULL, "  +
//                "ID_COMPONENTE_CASA  INTGER  NOT NULL, "  +
//                "FOREIGN KEY(ID_COMPONENTE_CASA) REFERENCES COMPONENTE_CASA(ID)" +
//                ");");

        db.execSQL(stringBuilderCreateTable1.toString());
//        db.execSQL(stringBuilderCreateTable2.toString());
    }

    public void insertTabela(SQLiteDatabase db){
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//        Date date = new Date();
//        String dataFormatada ;
//        dataFormatada = simpleDateFormat.format(date);

        db.execSQL("INSERT INTO ENDERECO_IP values (null, '192.168.201.246') ");
//        db.execSQL("INSERT INTO COMPONENTE_CASA values (null, 'Luz Sala') ");
//        db.execSQL("INSERT INTO COMPONENTE_CASA values (null, 'Tomada Sala') ");
//
//        db.execSQL("INSERT INTO COMPONENTE_CASA values (null, 'Luz Cozinha') ");
//        db.execSQL("INSERT INTO COMPONENTE_CASA values (null, 'Tomada Cozinha') ");
//
//
//        db.execSQL("INSERT INTO REGISTRO values (null, '" + dataFormatada + "', 'Desligado', 1) ");
//        db.execSQL("INSERT INTO REGISTRO values (null, '" + dataFormatada + "', 'Desligado', 2) ");
//        db.execSQL("INSERT INTO REGISTRO values (null, '" + dataFormatada + "', 'Desligado', 3) ");
//        db.execSQL("INSERT INTO REGISTRO values (null, '" + dataFormatada + "', 'Desligado', 4) ");
    }

}
