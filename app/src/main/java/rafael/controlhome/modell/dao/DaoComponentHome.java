package rafael.controlhome.modell.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import rafael.controlhome.connection.db.DataBase;
import rafael.controlhome.modell.ModelComponentHome;


public class DaoComponentHome {

    DataBase DataBase;
    private final String TABELA = "COMPONENTE_CASA";
    private final String CONSULTA = "SELECT ID, NOME FROM COMPONENTE_CASA";

    private final String CONSULTA_NOME = "SELECT ID FROM COMPONENTE_CASA WHERE NOME = ";

    public DaoComponentHome(Context context){
        DataBase = DataBase.getInstance(context);

    }

    public void salvar(ModelComponentHome modelComponentHome){

        ContentValues contentValues =  new ContentValues();
        contentValues.put("NOME",       modelComponentHome.getNome());
        DataBase.getConexaoDataBase().insert(TABELA,null,contentValues);

    }

    public void atualizar(ModelComponentHome modelComponentHome){

        ContentValues contentValues =  new ContentValues();
        contentValues.put("NOME",       modelComponentHome.getNome());
        DataBase.getConexaoDataBase().update(TABELA, contentValues, "ID = ?", new String[]{Integer.toString(modelComponentHome.getId())});
    }

    public Integer Excluir(int id){
        return DataBase.getConexaoDataBase().delete(TABELA,"ID = ?", new String[]{Integer.toString(id)});
    }

    public Integer consultarId(String nome){

        Cursor cursor = DataBase.getConexaoDataBase().rawQuery(CONSULTA_NOME + "'" +nome + "'",
                null);

        cursor.moveToFirst();

        ModelComponentHome modelComponentHome = null;

        while (!cursor.isAfterLast()){
            modelComponentHome =  new ModelComponentHome();
            modelComponentHome.setId(cursor.getInt(0));
            cursor.moveToNext();
        }

        return modelComponentHome.getId();
    }

    public List<ModelComponentHome> consultar(){

        List<ModelComponentHome> modelComponentHomeList = new ArrayList<ModelComponentHome>();

        Cursor cursor = DataBase.getConexaoDataBase().rawQuery(CONSULTA, null);

        cursor.moveToFirst();


        ModelComponentHome modelComponentHome;

        while (!cursor.isAfterLast()){
            modelComponentHome =  new ModelComponentHome();
            modelComponentHome.setId(cursor.getInt(0));
            modelComponentHome.setNome(cursor.getString(1));
            modelComponentHomeList.add(modelComponentHome);

            cursor.moveToNext();
        }

        return modelComponentHomeList;

    }

}
