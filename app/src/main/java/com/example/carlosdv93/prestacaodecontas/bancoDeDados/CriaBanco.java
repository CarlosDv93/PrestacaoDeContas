package com.example.carlosdv93.prestacaodecontas.bancoDeDados;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CarlosDv93 on 19/07/2016.
 */
public class CriaBanco extends SQLiteOpenHelper {

    public final static String NOME_BD = "PrestacaoDeContas";
    private static final int VERSAO_BD= 1;
    static final String TABELA = "conta";
    private static final String ID = "_id";
    static final String ITEM = "item";
    static final String VALOR = "valor";
    private static final String TIMESTAMP = "hora";



    public CriaBanco(Context context) {
        super(context, NOME_BD, null, VERSAO_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE "+ TABELA+"("
                + ID + " integer primary key autoincrement,"
                + ITEM + " text,"
                + VALOR + " float,"
                + TIMESTAMP + " DEFAULT CURRENT_TIMESTAMP NOT NULL"
                +")";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table " + TABELA + ";");
        onCreate(sqLiteDatabase);
    }
}











