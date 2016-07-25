package com.example.carlosdv93.prestacaodecontas;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.carlosdv93.prestacaodecontas.bancoDeDados.BancoDeDados;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditarActivity extends AppCompatActivity {

    @BindView(R.id.ListViewEditar)
    ListView listViewEditar;
    @BindView(R.id.btnSalvarEditar)
    Button btnSalvarEditar;
    @BindView(R.id.btnCancelarEditar)
    Button btnCancelarEditar;

    private long ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        long id = extras.getLong("ID");
        ID = id;
        BancoDeDados db = new BancoDeDados(getBaseContext());

        Cursor cursor = db.buscarID(id);
        // Toast.makeText (ListaItens.this, "ID: "+id, Toast.LENGTH_SHORT).show();
        String[] nomeCampos = new String[]{"item", "valor"};
        int[] idViews = new int[]{R.id.etxtItemEditar, R.id.etxtValorEditar};

        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.editar_item, cursor, nomeCampos, idViews, 0);
        listViewEditar.setAdapter(cursorAdapter);

    }


    @OnClick(R.id.btnSalvarEditar)
    public void salvarEdicao() {
        BancoDeDados db = new BancoDeDados(this);
        String str = ((EditText) this.findViewById(R.id.etxtItemEditar)).getText().toString();
        Float valor = Float.parseFloat(((EditText)this.findViewById(R.id.etxtValorEditar)).getText().toString());
        Toast.makeText(EditarActivity.this, "Item: " + str + "\nValor: " + valor, Toast.LENGTH_SHORT).show();
        boolean editar = db.editar(ID, str, valor);
        if (editar) {
            finish();
        } else {
            Toast.makeText(EditarActivity.this, "Não foi possível editar!", Toast.LENGTH_SHORT).show();
        }
    }

}











