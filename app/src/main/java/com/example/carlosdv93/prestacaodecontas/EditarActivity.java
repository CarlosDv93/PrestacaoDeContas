package com.example.carlosdv93.prestacaodecontas;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.carlosdv93.prestacaodecontas.bancoDeDados.BancoDeDados;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditarActivity extends AppCompatActivity {

    @BindView(R.id.ListViewEditar)
    ListView listViewEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        long id = extras.getLong("ID");

        BancoDeDados db = new BancoDeDados(getBaseContext());

        Cursor cursor = db.buscarID(id);
        // Toast.makeText(ListaItens.this, "ID: "+id, Toast.LENGTH_SHORT).show();
        String[] nomeCampos = new String[]{"item", "valor"};
        int[] idViews = new int[]{R.id.etxtItemEditar, R.id.etxtValorEditar};

        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.editar_item, cursor, nomeCampos, idViews, 0);
        listViewEditar.setAdapter(cursorAdapter);

    }

}











