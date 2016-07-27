package com.example.carlosdv93.prestacaodecontas.bancoDeDados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by CarlosDv93 on 19/07/2016. //0800 942 9999 Numero da Master
 */
public class BancoDeDados {
    private SQLiteDatabase db;
    private CriaBanco banco;


    public BancoDeDados(Context context) {
        banco = new CriaBanco(context);
    }

    public String inserir(String item, float valor) {
        ContentValues valores = new ContentValues();
        long resultado;

        db = banco.getWritableDatabase();
        valores.put(CriaBanco.ITEM, item);
        valores.put(CriaBanco.VALOR, valor);

        resultado = db.insert(CriaBanco.TABELA, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    public Cursor buscar() {

//        SQLiteDatabase dbBD;
//        dbBD = banco.getReadableDatabase();
        db = banco.getReadableDatabase();
        String[] colunas = new String[]{"_id", "item", "valor"};
        //     Cursor cursor = dbBD.query("conta", colunas, null, null, null, null, "_id");
        Cursor cursor = db.query("conta", colunas, null, null, null, null, "_id");

        if (cursor != null) {
            cursor.moveToFirst();
        }
        // dbBD.close();
        db.close();
        return cursor;
    }


    public boolean editar(long id, String item, float valor) {
        db = banco.getWritableDatabase();
        String aux = Long.toString(id);
        ContentValues contentValues = new ContentValues();
        contentValues.put(CriaBanco.ITEM, item);
        contentValues.put(CriaBanco.VALOR, valor);

        int update = db.update(CriaBanco.TABELA, contentValues, "_id = ?", new String[]{aux});
        db.close();

        if (update == 1) {
            return true;
        } else {
            return false;
        }
    }

    //public Cursor buscarID(int id){
    public Cursor buscarID(long id) {
        db = banco.getReadableDatabase();
        String[] colunas = new String[]{"_id", "item", "valor"};

        Cursor cursor = db.query("conta", colunas, "_id = " + id, null, null, null, "_id");
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public float somaTotal() {
        db = banco.getReadableDatabase();
        String selectSum = "SELECT SUM(" + CriaBanco.VALOR + ") FROM " + CriaBanco.TABELA + ";";
        float soma;
        Cursor cursor;

        cursor = db.rawQuery(selectSum, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        soma = cursor.getFloat(0);
        db.close();
        return soma;
    }

}









