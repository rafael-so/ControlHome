package rafael.controlhome.modell.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import rafael.controlhome.modell.ModelAddressIP;

public class DaoAddressIP {

    rafael.controlhome.connection.db.DataBase DataBase;
    ModelAddressIP modelAddressIP = new ModelAddressIP();

    private final String TABELA = "ENDERECO_IP";
    private final String CONSULTA = "SELECT ID, IP FROM ENDERECO_IP";

    public DaoAddressIP(Context context, ModelAddressIP modelAddressIP){
        DataBase = DataBase.getInstance(context);
        this.modelAddressIP = modelAddressIP;
    }

    public void salvar(ModelAddressIP modelAddressIP){

        ContentValues contentValues =  new ContentValues();
        contentValues.put("IP",   modelAddressIP.getIP());
        DataBase.getConexaoDataBase().insert(TABELA,null,contentValues);

    }

    public void alterar(ModelAddressIP modelAddressIP){
        ContentValues contentValues =  new ContentValues();
        contentValues.put("IP",   modelAddressIP.getIP());
        DataBase.getConexaoDataBase().update(TABELA, contentValues, "ID = 1",null);
    }


    public void consultar(){
        Cursor cursor = DataBase.getConexaoDataBase().rawQuery(CONSULTA, null);
        cursor.moveToLast();
            modelAddressIP.setID(cursor.getInt(0));
            modelAddressIP.setIP(cursor.getString(1));
            cursor.moveToNext();
    }

    public String consultarUltimoIP(){
        Cursor cursor = DataBase.getConexaoDataBase().rawQuery(CONSULTA, null);
        cursor.moveToFirst();
        modelAddressIP.setID(cursor.getInt(0));
        modelAddressIP.setIP(cursor.getString(1));
        cursor.moveToNext();
        return modelAddressIP.getIP();

    }

}
