package com.example.massoterapeuta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.massoterapeuta.adapter.ClienteSelecionadoAdapter;
import com.example.massoterapeuta.bancoLocal.entidade.ClienteEntidade;
import com.example.massoterapeuta.viewModel.ClienteViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ClienteSelecionadoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ClienteViewModel clienteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_selecionado);

        Long idCliente = getIntent().getLongExtra("idCliente", 1);
        recyclerView = findViewById(R.id.recyclerViewClienteSelecionado);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final ClienteSelecionadoAdapter adapter = new ClienteSelecionadoAdapter(new ClienteSelecionadoAdapter.clienteDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        clienteViewModel = new ViewModelProvider(this).get(ClienteViewModel.class);

        clienteViewModel.retornarClienteSelecionado(idCliente).observe(this, cliente -> {
            if (cliente !=  null){
                List<ClienteEntidade> singleItemList = new ArrayList<>();
                singleItemList.add(cliente);
                adapter.submitList(singleItemList);
            }
            else{
                Toast.makeText(this, "Não foi possível carregar o cliente", Toast.LENGTH_SHORT).show();
            }

        });

        ImageView botaoEditar = findViewById(R.id.ic_editar);

        botaoEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClienteSelecionadoActivity.this, EditarClienteActivity.class);

                intent.putExtra("idCliente", idCliente);

                startActivity(intent);
            }

    });
        ImageView botaoVoltar = findViewById(R.id.ic_voltar);

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClienteSelecionadoActivity.this, ExibirListaClientesActivity.class);
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_item1) {
                Intent intent1 = new Intent(ClienteSelecionadoActivity.this, ExibirListaClientesActivity.class);
                startActivity(intent1);
                return true;
            } else if (item.getItemId() == R.id.menu_item2) {
                Intent intent2 = new Intent(ClienteSelecionadoActivity.this, ExibirListaServicosActivity.class);
                startActivity(intent2);
                return true;
            } else if (item.getItemId() == R.id.menu_item3) {
                Intent intent2 = new Intent(ClienteSelecionadoActivity.this, ExibirListaSessaoActivity.class);
                startActivity(intent2);
                return true;
            } else if (item.getItemId() == R.id.menu_item4) {
                Intent intent4 = new Intent(ClienteSelecionadoActivity.this, ExibirListaContratoActivity.class);
                startActivity(intent4);
                return true;
            } else if (item.getItemId() == R.id.menu_item5) {
                Intent intent5 = new Intent(ClienteSelecionadoActivity.this, RelatorioActivity.class);
                startActivity(intent5);
                return true;
            }
            return false;
        });

    }


}
