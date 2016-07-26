package com.example.carlosdv93.prestacaodecontas;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.carlosdv93.prestacaodecontas.bancoDeDados.BancoDeDados;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaItens extends MenuAll {

    @BindView(R.id.listView)
    ListView listView;
    // BancoDeDados db = new BancoDeDados(getBaseContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_itens);
        ButterKnife.bind(this);
        registerForContextMenu(listView);
        preencher();
    }

    @Override
    protected void onResume() {
        super.onResume();
        preencher();
    }

    public void preencher() {

        BancoDeDados db = new BancoDeDados(getBaseContext());

        Cursor cursor = db.buscar();

        String[] nomeCampos = new String[]{"_id", "item", "valor"};
        int[] idViews = new int[]{R.id.text3, R.id.text1, R.id.text2};

        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.itens_list_view, cursor, nomeCampos, idViews, 0);
        listView.setAdapter(cursorAdapter);

    }

    public void editar(long id){
        Intent intent = new Intent(this, EditarActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        menu.setHeaderTitle(R.string.Opcoes);
        inflater.inflate(R.menu.menu_opcoes, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.deletar:

                Toast.makeText(ListaItens.this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.editarMenu:
             //   Toast.makeText(ListaItens.this, "ID: " +info.id+ "Position:" + info.position, Toast.LENGTH_SHORT).show();
                editar(info.id);
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return false;
    }
}






















