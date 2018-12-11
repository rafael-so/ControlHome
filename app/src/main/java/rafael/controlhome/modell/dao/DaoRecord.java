package rafael.controlhome.modell.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import rafael.controlhome.connection.db.DataBase;
import rafael.controlhome.modell.ModelRecord;

public class DaoRecord {

    DataBase DataBase;

    private final String TABELA = "REGISTRO";
    private final String CONSULTA = " SELECT COMPONENTE_CASA.ID, COMPONENTE_CASA.NOME," +
            " REGISTRO.DATA, REGISTRO.STATUS FROM COMPONENTE_CASA" +
            " INNER JOIN REGISTRO ON REGISTRO.ID_COMPONENTE_CASA = COMPONENTE_CASA.ID" +
            " ORDER BY REGISTRO.DATA DESC";

    private final String CONSULTA_STATUS = "SELECT COMPONENTE_CASA.ID, REGISTRO.STATUS FROM REGISTRO";

//    private final String CONSULTA_STATUS = "SELECT REGISTRO.STATUS" +
//            " FROM REGISTRO " +
//            "ORDER BY DATA DESC ";


    public DaoRecord(Context context){
        DataBase = DataBase.getInstance(context);

    }

    public void salvar(ModelRecord modelRecord){

        ContentValues contentValues =  new ContentValues();
        contentValues.put("DATA",       modelRecord.getData_hora());
        contentValues.put("STATUS",   modelRecord.getStatus());
        DataBase.getConexaoDataBase().insert(TABELA,null,contentValues);

    }

    public Integer excluir(int id){
        return DataBase.getConexaoDataBase().delete(TABELA,"ID = ?", new String[]{Integer.toString(id)});
    }

    public String consultarStatus(int id ){
        String sql = "SELECT REGISTRO.STATUS FROM REGISTRO" +
                " WHERE ID_COMPONENTE_CASA = " + id;
        Cursor cursor = DataBase.getConexaoDataBase().rawQuery(sql, null);
        cursor.moveToLast();

        ModelRecord modelRecord = null;

        while (cursor.isLast()){
            modelRecord = new ModelRecord();
            modelRecord.setStatus(cursor.getString(0));

            cursor.moveToNext();
        }
        return modelRecord.getStatus();
    }

    public List<ModelRecord> consultar(){

        List<ModelRecord> modelRecordList = new ArrayList<>();

        Cursor cursor = DataBase.getConexaoDataBase().rawQuery(CONSULTA, null);

        cursor.moveToFirst();

        ModelRecord modelRecord;

        while (!cursor.isAfterLast()){
            modelRecord = new ModelRecord();
            modelRecord.setData_hora(cursor.getString(2));
            modelRecord.setStatus(cursor.getString(3));
            modelRecordList.add(modelRecord);

            cursor.moveToNext();
        }

        return modelRecordList;

    }

}
