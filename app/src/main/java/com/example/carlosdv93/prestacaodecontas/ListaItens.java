package com.example.carlosdv93.prestacaodecontas;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carlosdv93.prestacaodecontas.bancoDeDados.BancoDeDados;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemLongClick;
import butterknife.OnLongClick;

public class ListaItens extends AppCompatActivity {

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

    public void preencher() {

        BancoDeDados db = new BancoDeDados(getBaseContext());

        Cursor cursor = db.buscar();

        String[] nomeCampos = new String[]{"_id", "item", "valor"};
        int[] idViews = new int[]{R.id.text3, R.id.text1, R.id.text2};

        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.itens_list_view, cursor, nomeCampos, idViews, 0);
        listView.setAdapter(cursorAdapter);

//        if (listView.isLongClickable()) {
//            editar();
//        }

    }


    public void editar() {
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = ((TextView) findViewById(R.id.text1)).getText().toString();
                String auxFloat = ((TextView) findViewById(R.id.text2)).getText().toString();
                int i = Integer.parseInt((((TextView) findViewById(R.id.text3)).getText().toString()));
                Float valor = Float.parseFloat(auxFloat);

                Toast.makeText(ListaItens.this, "id: " + i + "\nItem: " + item + "\nValor: " + valor, Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        menu.setHeaderTitle("Menu de Contexto");
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(ListaItens.this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.editarMenu:
                Toast.makeText(ListaItens.this, "ID: " +info.id+ "Position:" + info.position, Toast.LENGTH_SHORT).show();
                //editar();
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return false;
    }
}






















