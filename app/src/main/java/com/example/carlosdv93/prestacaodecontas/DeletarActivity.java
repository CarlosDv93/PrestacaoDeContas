package com.example.carlosdv93.prestacaodecontas;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carlosdv93.prestacaodecontas.bancoDeDados.BancoDeDados;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DeletarActivity extends AppCompatActivity {

    @BindView(R.id.ListViewDeletar)
    ListView listViewDeletar;
    @BindView(R.id.btnSalvarDeletar)
    Button btnSalvarDeletar;
    @BindView(R.id.btnCancelarDeletar)
    Button btnCancelarDeletar;
    @BindView(R.id.TerceiroLinearDeletar)
    LinearLayout TerceiroLinearDeletar;
    private long ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletar_);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        long id = extras.getLong("ID");
        ID = id;
        BancoDeDados db = new BancoDeDados(getBaseContext());

        Cursor cursor = db.buscarID(id);

        String[] nomeCampos = new String[]{"item", "valor"};
        int[] idViews = new int[]{R.id.etxtItemDeletar, R.id.etxtValorDeletar};

        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.deletar_item, cursor, nomeCampos, idViews, 0);
        listViewDeletar.setAdapter(cursorAdapter);

    }

    @Nullable
    @OnClick(R.id.btnSalvarDeletar)
    public void deletar() {
        BancoDeDados db = new BancoDeDados(this);
        String item = ((TextView) this.findViewById(R.id.etxtItemDeletar)).getText().toString();
        String valoraux = ((TextView) this.findViewById(R.id.etxtValorDeletar)).getText().toString();

        boolean deletar = db.deletarID(ID);
        if (deletar) {
            finish();
        } else {
            Toast.makeText(DeletarActivity.this, "Não foi possível Deletar!", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(DeletarActivity.this, "Deletado \nItem: " + item + "\nValor: " + valoraux, Toast.LENGTH_SHORT).show();

    }

    @OnClick(R.id.btnCancelarDeletar)
    public void cancelarDeletar() {
        finish();
    }
}
