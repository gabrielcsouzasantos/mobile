package com.example.tarefas.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tarefas.model.Tarefa;

import java.util.ArrayList;

public class TarefaDAO {

    private SQLiteDatabase sqLiteDatabase;

    private SQLiteHelper sqLiteHelper;

    public TarefaDAO(Context context){
        this.sqLiteHelper = new SQLiteHelper(context);
    }

    public ArrayList<Tarefa> buscarTodasTarefas(){
        this.sqLiteDatabase = sqLiteHelper.getReadableDatabase();

        ArrayList<Tarefa> tarefas = new ArrayList<>();

        Cursor cursor;

        String[] cols = new String[]{
                SQLiteHelper.ID,
                SQLiteHelper.NOME,
                SQLiteHelper.DESCRICAO,
                SQLiteHelper.FEITO
        };

        cursor = sqLiteDatabase.query(SQLiteHelper.TAREFAS, cols, null, null, null, null, SQLiteHelper.ID);

        while(cursor.moveToNext()){
            Tarefa tarefa = new Tarefa();

            tarefa.setId(cursor.getInt(0));
            tarefa.setNome(cursor.getString(1));
            tarefa.setDescricao(cursor.getString(2));
            tarefa.setFeito(cursor.getInt(3));

            tarefas.add(tarefa);

        }

        cursor.close();

        sqLiteDatabase.close();

        return tarefas;
    }


    public void salvarTarefa(Tarefa t){
        this.sqLiteDatabase = this.sqLiteHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(SQLiteHelper.NOME, t.getNome());
        contentValues.put(SQLiteHelper.DESCRICAO, t.getDescricao());

        //INSERT ou UPDATE?
        if(t.getId() > 0)
        {
            this.sqLiteDatabase.update(SQLiteHelper.TAREFAS, contentValues, SQLiteHelper.ID + "=" + t.getId(), null);
        }
        else
        {
            this.sqLiteDatabase.insert(SQLiteHelper.TAREFAS, null, contentValues);
        }

        sqLiteDatabase.close();
    }

    public void apagarTarefa(Tarefa t){
        this.sqLiteDatabase = this.sqLiteHelper.getWritableDatabase();

        this.sqLiteDatabase.delete(SQLiteHelper.TAREFAS, SQLiteHelper.ID + "=" + t.getId(), null);

        this.sqLiteDatabase.close();
    }

    public void realizarTarefa(Tarefa t){
        this.sqLiteDatabase = this.sqLiteHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(SQLiteHelper.FEITO, 1);

        this.sqLiteDatabase.update(SQLiteHelper.TAREFAS, contentValues, SQLiteHelper.ID + "=" + t.getId(), null);

        sqLiteDatabase.close();
    }

    public void desfazerTarefa(Tarefa t){
        this.sqLiteDatabase = this.sqLiteHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(SQLiteHelper.FEITO, 0);

        this.sqLiteDatabase.update(SQLiteHelper.TAREFAS, contentValues, SQLiteHelper.ID + "=" + t.getId(), null);

        sqLiteDatabase.close();
    }

}
