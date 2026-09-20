package com.example.cadastroproduto;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.util.Log;

import android.database.Cursor;
import java.util.ArrayList;

public class ProdutoDbHelper extends SQLiteOpenHelper {
    public ProdutoDbHelper(Context context) {
        super(context, "produtos.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE produtos(" +
                "id integer primary key autoincrement," +
                "nome text, " +
                "preco REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion){

    }
    public void inserirProduto(Produto produto) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("nome", produto.getNome());
        values.put("preco", produto.getPreco());

        long resultado = db.insert(
                "produtos",
                null,
                values
        );

        if (resultado == -1) {
            Log.e("DB", "Erro ao inserir produto");
        } else {
            Log.d("DB", "Produto inserido com ID: " + resultado);
        }

        db.close();
    }


    public ArrayList<String> listarProdutos() {

        ArrayList<String> lista = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT nome, preco FROM produtos",
                null
        );

        while (cursor.moveToNext()) {

            String nome = cursor.getString(
                    cursor.getColumnIndexOrThrow("nome")
            );

            double preco = cursor.getDouble(
                    cursor.getColumnIndexOrThrow("preco")
            );

            lista.add(nome + " - R$ " + preco);
        }

        cursor.close();
        db.close();

        return lista;
    }

}