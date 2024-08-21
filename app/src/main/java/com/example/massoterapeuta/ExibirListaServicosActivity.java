package com.example.massoterapeuta;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.massoterapeuta.adapter.ServicoAdapter;
import com.example.massoterapeuta.bancoLocal.entidade.ServicoEntidade;
import com.example.massoterapeuta.viewModel.ServicoViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ExibirListaServicosActivity extends AppCompatActivity {

    public static final int CADASTRAR_SERVICOS_ACTIVITY_REQUEST_CODE = 1;
    private RecyclerView recyclerView;
    private ServicoViewModel servicoViewModel;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_servicos);

        recyclerView = findViewById(R.id.recyclerViewListaServicos);
        final ServicoAdapter adapter = new ServicoAdapter(new ServicoAdapter.servicoDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        servicoViewModel = new ViewModelProvider(this).get(ServicoViewModel.class);

        servicoViewModel.retornarTodosServicos().observe(this, servicos -> {
            adapter.submitList(servicos);
        });

        ImageView botaoVoltar = findViewById(R.id.ic_voltar);
        botaoVoltar.setOnClickListener(view -> {
            Intent intent = new Intent(ExibirListaServicosActivity.this, ExibirListaSessaoActivity.class);
            startActivity(intent);
        });

        ImageView botaoAdicionar = findViewById(R.id.ic_adicionar);
        botaoAdicionar.setOnClickListener(view -> {
            Intent intent = new Intent(ExibirListaServicosActivity.this, CadastrarServicoActivity.class);
            startActivityForResult(intent, CADASTRAR_SERVICOS_ACTIVITY_REQUEST_CODE);
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_item1) {
                Intent intent1 = new Intent(ExibirListaServicosActivity.this, ExibirListaClientesActivity.class);
                startActivity(intent1);
                return true;
            } else if (item.getItemId() == R.id.menu_item2) {
                Intent intent2 = new Intent(ExibirListaServicosActivity.this, ExibirListaServicosActivity.class);
                startActivity(intent2);
                return true;
            } else if (item.getItemId() == R.id.menu_item3) {
                Intent intent2 = new Intent(ExibirListaServicosActivity.this, ExibirListaSessaoActivity.class);
                startActivity(intent2);
                return true;
            } else if (item.getItemId() == R.id.menu_item4) {
                Intent intent4 = new Intent(ExibirListaServicosActivity.this, ExibirListaContratoActivity.class);
                startActivity(intent4);
                return true;
            } else if (item.getItemId() == R.id.menu_item5) {
                Intent intent5 = new Intent(ExibirListaServicosActivity.this, RelatorioActivity.class);
                startActivity(intent5);
                return true;
            }
            return false;
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CADASTRAR_SERVICOS_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            ServicoEntidade servicoEntidade = new ServicoEntidade();
            servicoEntidade.setDescricao(data.getStringExtra("descricao"));
            servicoEntidade.setValorUnitario(new Float(10));
            servicoEntidade.setValorUnitario(data.getFloatExtra("valorUnitario", 00.0f));
            servicoEntidade.setValorPacote(data.getFloatExtra("valorPacote", 00.0f));
            servicoEntidade.setTempo(data.getIntExtra("tempo", 50));
            servicoEntidade.setPeso(data.getFloatExtra("peso", 1.0f));
            servicoViewModel.inserir(servicoEntidade);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Não foi possível salvar",
                    Toast.LENGTH_LONG).show();
        }
    }

}
