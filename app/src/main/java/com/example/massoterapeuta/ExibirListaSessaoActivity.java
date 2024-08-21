package com.example.massoterapeuta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.massoterapeuta.adapter.SessaoAdapter;
import com.example.massoterapeuta.bancoLocal.entidade.SessaoEntidade;
import com.example.massoterapeuta.viewModel.SessaoViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.materialswitch.MaterialSwitch;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ExibirListaSessaoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_lista_sessao);
        List<SessaoEntidade> sessoesHoje = new ArrayList<>();
        List<SessaoEntidade> sessoesAmanha = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerViewListaSessao);
        final SessaoAdapter adapter = new SessaoAdapter(new SessaoAdapter.sessaoDiff(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SessaoViewModel sessaoViewModel = new ViewModelProvider(this).get(SessaoViewModel.class);



        MaterialSwitch marcados = findViewById(R.id.switch_marcados);
        marcados.setChecked(true);
        sessaoViewModel.retornarTodasSessoesMarcadas().observe(this, sessoes -> {
            adapter.submitList(sessoes);
        });

        marcados.setOnCheckedChangeListener((buttonViewMarcados, isCheckedMarcados) -> {
            if (isCheckedMarcados){
                sessaoViewModel.retornarTodasSessoesMarcadas().observe(this, sessoes -> {
                    adapter.submitList(sessoes);
                });
            }
            else{
                sessaoViewModel.retornarTodasSessoesNaoMarcadas().observe(this, sessoes -> {
                    adapter.submitList(sessoes);
                });
            }
        });


        RadioButton botaoTodas = findViewById(R.id.opção_todas);
        botaoTodas.setChecked(true);
        botaoTodas.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                marcados.setVisibility(View.VISIBLE);
                marcados.setChecked(true);
                sessaoViewModel.retornarTodasSessoesMarcadas().observe(this, sessoes -> {
                    adapter.submitList(sessoes);
                });
                marcados.setOnCheckedChangeListener((buttonViewMarcados, isCheckedMarcados) -> {
                    if (isCheckedMarcados){
                        sessaoViewModel.retornarTodasSessoesMarcadas().observe(this, sessoes -> {
                            adapter.submitList(sessoes);
                        });
                    }
                    else{
                        sessaoViewModel.retornarTodasSessoesNaoMarcadas().observe(this, sessoes -> {
                            adapter.submitList(sessoes);
                        });
                    }
                });
            }
        });

        RadioButton botaoHoje = findViewById(R.id.opção_hoje);
        botaoHoje.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                sessaoViewModel.retornarTodasSessoes().observe(this, sessoes -> {
                    if (sessoes != null){
                        LocalDate hoje = LocalDate.now();
                        sessoesHoje.clear();
                        marcados.setVisibility(View.GONE);
                        for (SessaoEntidade sessao: sessoes){
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                            LocalDate dataInicio = LocalDate.parse(sessao.getDataInicio(), formatter);
                            if (dataInicio.isEqual(hoje)){
                                sessoesHoje.add(sessao);
                            }
                        }
                        adapter.submitList(sessoesHoje);
                    }
                });
            }
        });

        RadioButton botaoAmanha = findViewById(R.id.opção_amanha);
        botaoAmanha.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                sessaoViewModel.retornarTodasSessoes().observe(this, sessoes -> {
                    if (sessoes != null){
                        LocalDate amanha = LocalDate.now().plusDays(1);
                        sessoesAmanha.clear();
                        marcados.setVisibility(View.GONE);
                        for (SessaoEntidade sessao: sessoes){
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                            LocalDate dataInicio = LocalDate.parse(sessao.getDataInicio(), formatter);
                            if (dataInicio.isEqual(amanha)){
                                sessoesAmanha.add(sessao);
                            }
                        }
                        adapter.submitList(sessoesAmanha);
                    }
                });
            }
        });



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_item1) {
                Intent intent1 = new Intent(ExibirListaSessaoActivity.this, ExibirListaClientesActivity.class);
                startActivity(intent1);
                return true;
            } else if (item.getItemId() == R.id.menu_item2) {
                Intent intent2 = new Intent(ExibirListaSessaoActivity.this, ExibirListaServicosActivity.class);
                startActivity(intent2);
                return true;
            } else if (item.getItemId() == R.id.menu_item3) {
                Intent intent2 = new Intent(ExibirListaSessaoActivity.this, ExibirListaSessaoActivity.class);
                startActivity(intent2);
                return true;
            } else if (item.getItemId() == R.id.menu_item4) {
                Intent intent4 = new Intent(ExibirListaSessaoActivity.this, ExibirListaContratoActivity.class);
                startActivity(intent4);
                return true;
            } else if (item.getItemId() == R.id.menu_item5) {
                Intent intent5 = new Intent(ExibirListaSessaoActivity.this, RelatorioActivity.class);
                startActivity(intent5);
                return true;
            }
            return false;
        });


        FloatingActionButton botaoRestaurarBackup = findViewById(R.id.botaoBackup);
        botaoRestaurarBackup.setOnClickListener(view -> {
            sessaoViewModel.restaurarBackupBanco(ExibirListaSessaoActivity.this);
            Toast.makeText(this, "Arquivo de backup restaurado", Toast.LENGTH_LONG).show();
            restartApplication();
        });


    }
    private void restartApplication() {
        finish();
        startActivity(getIntent());
        System.exit(0);
    }




}


