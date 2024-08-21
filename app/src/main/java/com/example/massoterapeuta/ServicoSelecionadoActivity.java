package com.example.massoterapeuta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.massoterapeuta.adapter.ServicoSelecionadoAdapter;
import com.example.massoterapeuta.bancoLocal.entidade.ServicoEntidade;
import com.example.massoterapeuta.viewModel.ServicoViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

    public class ServicoSelecionadoActivity extends AppCompatActivity {

        private RecyclerView recyclerView;

        private ServicoViewModel servicoViewModel;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_servico_selecionado);

            Long idServico = getIntent().getLongExtra("idServico", 1);
            recyclerView = findViewById(R.id.recyclerViewServicoSelecionado);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            final ServicoSelecionadoAdapter adapter = new ServicoSelecionadoAdapter(new ServicoSelecionadoAdapter.servicoDiff());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            servicoViewModel = new ViewModelProvider(this).get(ServicoViewModel.class);

            servicoViewModel.retornarServicoSelecionado(idServico).observe(this, servico -> {
                List<ServicoEntidade> singleItemList = new ArrayList<>();
                singleItemList.add(servico);
                adapter.submitList(singleItemList);
            });

            ImageView botaoVoltar = findViewById(R.id.ic_voltar);

            botaoVoltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ServicoSelecionadoActivity.this, ExibirListaServicosActivity.class);

                    startActivity(intent);
                }
            });

            ImageView botarEditar = findViewById(R.id.ic_editar);

            botarEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ServicoSelecionadoActivity.this, EditarServicoActivity.class);

                    intent.putExtra("idServico", idServico);

                    startActivity(intent);
                }
            });

            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
            bottomNavigationView.setOnItemSelectedListener(item -> {
                if (item.getItemId() == R.id.menu_item1) {
                    Intent intent1 = new Intent(ServicoSelecionadoActivity.this, ExibirListaClientesActivity.class);
                    startActivity(intent1);
                    return true;
                } else if (item.getItemId() == R.id.menu_item2) {
                    Intent intent2 = new Intent(ServicoSelecionadoActivity.this, ExibirListaServicosActivity.class);
                    startActivity(intent2);
                    return true;
                } else if (item.getItemId() == R.id.menu_item3) {
                    Intent intent2 = new Intent(ServicoSelecionadoActivity.this, ExibirListaSessaoActivity.class);
                    startActivity(intent2);
                    return true;
                } else if (item.getItemId() == R.id.menu_item4) {
                    Intent intent4 = new Intent(ServicoSelecionadoActivity.this, ExibirListaContratoActivity.class);
                    startActivity(intent4);
                    return true;
                } else if (item.getItemId() == R.id.menu_item5) {
                    Intent intent5 = new Intent(ServicoSelecionadoActivity.this, RelatorioActivity.class);
                    startActivity(intent5);
                    return true;
                }
                return false;
            });
        }



    }