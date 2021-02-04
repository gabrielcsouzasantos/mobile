package com.example.tarefas.repositorio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String NOME_DB = "tarefas.db";
    private static final int VERSAO_BD = 1;

    public static final String TAREFAS = "tarefas"; //nome da tabela
    public static final String ID = "id"; //coluna ID da tabela
    public static final String NOME = "nome"; //coluna NOME da tabela
    public static final String DESCRICAO = "descricao"; //coluna DESCRICAO da tabela
    public static final String FEITO = "feito"; //coluna DESCRICAO da tabela

    private static final String CRIA_DB = "CREATE TABLE " + TAREFAS +
            " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NOME + " TEXT NOT NULL, " +
            DESCRICAO + " TEXT NOT NULL," +
            FEITO + " INTEGER DEFAULT 0" +
            ")";


    public SQLiteHelper(Context context) {
        super(context, NOME_DB , null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CRIA_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
