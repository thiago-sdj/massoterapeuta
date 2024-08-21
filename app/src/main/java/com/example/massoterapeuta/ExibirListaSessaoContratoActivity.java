package com.example.massoterapeuta;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.massoterapeuta.adapter.SessaoAdapter;
import com.example.massoterapeuta.bancoLocal.entidade.SessaoEntidade;
import com.example.massoterapeuta.viewModel.SessaoViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ExibirListaSessaoContratoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_lista_sessao_contrato);

        List<SessaoEntidade> sessoesSelecionadas = new ArrayList<>();

        Long idContrato = getIntent().getLongExtra("idContrato", 1);
        recyclerView = findViewById(R.id.recyclerViewListaSessao);
        final SessaoAdapter adapter = new SessaoAdapter(new SessaoAdapter.sessaoDiff(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ImageView botaoVoltar = findViewById(R.id.ic_voltar);
        botaoVoltar.setOnClickListener(view -> {
            Intent intent = new Intent(ExibirListaSessaoContratoActivity.this, ContratoSelecionadoActivity.class);
            intent.putExtra("idContrato", idContrato);
            startActivity(intent);
        });

        SessaoViewModel sessaoViewModel = new ViewModelProvider(this).get(SessaoViewModel.class);
        TextView textView = findViewById(R.id.id_contrato);
        textView.setText(idContrato.toString());
        sessaoViewModel.retornarTodasSessoes().observe(this, sessoes -> {
            sessoesSelecionadas.clear();
            for (SessaoEntidade sessao: sessoes){
                if (sessao.getIdContrato().equals(idContrato)){
                    sessoesSelecionadas.add(sessao);
                }
            }
            adapter.submitList(sessoesSelecionadas);
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_item1) {
                Intent intent1 = new Intent(ExibirListaSessaoContratoActivity.this, ExibirListaClientesActivity.class);
                startActivity(intent1);
                return true;
            } else if (item.getItemId() == R.id.menu_item2) {
                Intent intent2 = new Intent(ExibirListaSessaoContratoActivity.this, ExibirListaServicosActivity.class);
                startActivity(intent2);
                return true;
            } else if (item.getItemId() == R.id.menu_item3) {
                Intent intent2 = new Intent(ExibirListaSessaoContratoActivity.this, ExibirListaSessaoActivity.class);
                startActivity(intent2);
                return true;
            } else if (item.getItemId() == R.id.menu_item4) {
                Intent intent4 = new Intent(ExibirListaSessaoContratoActivity.this, ExibirListaContratoActivity.class);
                startActivity(intent4);
                return true;
            } else if (item.getItemId() == R.id.menu_item5) {
                Intent intent5 = new Intent(ExibirListaSessaoContratoActivity.this, RelatorioActivity.class);
                startActivity(intent5);
                return true;
            }
            return false;
        });

    }
}