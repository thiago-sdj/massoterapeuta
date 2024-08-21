package com.example.massoterapeuta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.massoterapeuta.bancoLocal.entidade.ClienteEntidade;
import com.example.massoterapeuta.viewModel.ClienteViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.massoterapeuta.adapter.ClienteAdapter;

public class ExibirListaClientesActivity extends AppCompatActivity {

    public static final int CADASTRAR_CLIENTES_ACTIVITY_REQUEST_CODE = 1;
    private RecyclerView recyclerView;
    private ClienteViewModel clienteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);

        recyclerView = findViewById(R.id.recyclerViewListaClientes);
        final ClienteAdapter adapter = new ClienteAdapter(new ClienteAdapter.clienteDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        clienteViewModel = new ViewModelProvider(this).get(ClienteViewModel.class);

        clienteViewModel.retornarTodosClientes().observe(this, clientes -> {
            adapter.submitList(clientes);
        });

        ImageView botaoVoltar = findViewById(R.id.ic_voltar);
        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExibirListaClientesActivity.this, ExibirListaSessaoActivity.class);
                startActivity(intent);
            }
        });

        ImageView botaoAdicionar = findViewById(R.id.ic_adicionar_cliente);
        botaoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExibirListaClientesActivity.this, CadastrarClienteActivity.class);
                startActivityForResult(intent, CADASTRAR_CLIENTES_ACTIVITY_REQUEST_CODE);
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
                if (item.getItemId() == R.id.menu_item1) {
                    Intent intent1 = new Intent(ExibirListaClientesActivity.this, ExibirListaClientesActivity.class);
                    startActivity(intent1);
                    return true;
                } else if (item.getItemId() == R.id.menu_item2) {
                    Intent intent2 = new Intent(ExibirListaClientesActivity.this, ExibirListaServicosActivity.class);
                    startActivity(intent2);
                    return true;
                } else if (item.getItemId() == R.id.menu_item3) {
                    Intent intent2 = new Intent(ExibirListaClientesActivity.this, ExibirListaSessaoActivity.class);
                    startActivity(intent2);
                    return true;
                } else if (item.getItemId() == R.id.menu_item4) {
                    Intent intent4 = new Intent(ExibirListaClientesActivity.this, ExibirListaContratoActivity.class);
                    startActivity(intent4);
                    return true;
                } else if (item.getItemId() == R.id.menu_item5) {
                    Intent intent5 = new Intent(ExibirListaClientesActivity.this, RelatorioActivity.class);
                    startActivity(intent5);
                    return true;
                }
                return false;
        });


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CADASTRAR_CLIENTES_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            ClienteEntidade clienteEntidade = new ClienteEntidade();
            clienteEntidade.setNome(data.getStringExtra("nome"));
            clienteEntidade.setCep(data.getStringExtra("cep"));
            clienteEntidade.setLogradouro(data.getStringExtra("logradouro"));
            clienteEntidade.setNumero(data.getStringExtra("numero"));
            clienteEntidade.setComplemento(data.getStringExtra("complemento"));
            clienteEntidade.setBairro(data.getStringExtra("bairro"));
            clienteEntidade.setOcupacao(data.getStringExtra("ocupacao"));
            clienteEntidade.setTelefone(data.getStringExtra("telefone"));
            clienteEntidade.setSexo(data.getStringExtra("sexo"));
            clienteEntidade.setDataNascimento(data.getStringExtra("dataNascimento"));
            clienteEntidade.setEmail(data.getStringExtra("email"));
            clienteEntidade.setQueixas(data.getStringExtra("queixas"));
            clienteEntidade.setHistorico(data.getStringExtra("historico"));
            clienteEntidade.setMedicamentos(data.getStringExtra("medicamentos"));
            clienteEntidade.setSono(data.getStringExtra("sono"));
            clienteEntidade.setAlimentacao(data.getStringExtra("alimentacao"));
            clienteEntidade.setDigestao(data.getStringExtra("digestao"));
            clienteEntidade.setExcrecao(data.getStringExtra("excrecao"));
            clienteEntidade.setDoresFisicas(data.getStringExtra("doresFisicas"));
            clienteEntidade.setDoresEmocionais(data.getStringExtra("doresEmocionais"));
            clienteEntidade.setMenstruacao(data.getStringExtra("menstruacao"));
            clienteEntidade.setParto(data.getStringExtra("parto"));
            clienteEntidade.setCirurgias(data.getStringExtra("cirurgias"));
            clienteEntidade.setMobilidade(data.getStringExtra("mobilidade"));
            clienteEntidade.setVicios(data.getStringExtra("vicios"));
            clienteEntidade.setHormonal(data.getStringExtra("hormonal"));
            clienteEntidade.setObservacao(data.getStringExtra("observacao"));
            clienteViewModel.inserir(clienteEntidade);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Não foi possível salvar pois há campos obrigatórios vazios",
                    Toast.LENGTH_LONG).show();
        }
    }

}