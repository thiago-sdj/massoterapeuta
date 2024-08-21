package com.example.massoterapeuta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.massoterapeuta.adapter.ContratoSelecionadoAdapter;
import com.example.massoterapeuta.bancoLocal.entidade.ContratoEntidade;
import com.example.massoterapeuta.viewModel.ContratoViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class ContratoSelecionadoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContratoViewModel contratoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contrato_selecionado);

        Long idContrato = getIntent().getLongExtra("idContrato", 1);
        recyclerView = findViewById(R.id.recyclerViewContratoSelecionado);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ContratoSelecionadoAdapter adapter = new ContratoSelecionadoAdapter(new ContratoSelecionadoAdapter.contratoDiff(), this);
        recyclerView.setAdapter(adapter);

        contratoViewModel = new ViewModelProvider(this).get(ContratoViewModel.class);

        contratoViewModel.retornarContratoSelecionado(idContrato).observe(this, contrato -> {
            List<ContratoEntidade> singleItemList = new ArrayList<>();
            singleItemList.add(contrato);
            adapter.submitList(singleItemList);
        });

        MaterialButton visualizarSessoes = findViewById(R.id.botao_visualizarSessoes);
        visualizarSessoes.setOnClickListener(view ->{
            Intent intent = new Intent(view.getContext(), ExibirListaSessaoContratoActivity.class);
            intent.putExtra("idContrato", idContrato);
            view.getContext().startActivity(intent);
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_item1) {
                Intent intent1 = new Intent(ContratoSelecionadoActivity.this, ExibirListaClientesActivity.class);
                startActivity(intent1);
                return true;
            } else if (item.getItemId() == R.id.menu_item2) {
                Intent intent2 = new Intent(ContratoSelecionadoActivity.this, ExibirListaServicosActivity.class);
                startActivity(intent2);
                return true;
            } else if (item.getItemId() == R.id.menu_item3) {
                Intent intent2 = new Intent(ContratoSelecionadoActivity.this, ExibirListaSessaoActivity.class);
                startActivity(intent2);
                return true;
            } else if (item.getItemId() == R.id.menu_item4) {
                Intent intent4 = new Intent(ContratoSelecionadoActivity.this, ExibirListaContratoActivity.class);
                startActivity(intent4);
                return true;
            } else if (item.getItemId() == R.id.menu_item5) {
                Intent intent5 = new Intent(ContratoSelecionadoActivity.this, RelatorioActivity.class);
                startActivity(intent5);
                return true;
            }
            return false;
        });

        ImageView backIcon = findViewById(R.id.botao_voltar_contratoselecionado);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContratoSelecionadoActivity.this, ExibirListaContratoActivity.class);
                startActivity(intent);
            }
        });

    }


}