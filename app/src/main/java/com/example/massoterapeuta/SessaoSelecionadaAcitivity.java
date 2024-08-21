package com.example.massoterapeuta;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.massoterapeuta.adapter.SessaoSelecionadaAdapter;
import com.example.massoterapeuta.bancoLocal.entidade.SessaoEntidade;
import com.example.massoterapeuta.viewModel.SessaoViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SessaoSelecionadaAcitivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private SessaoViewModel sessaoViewModel;

    private Long idContrato;

    private Long idSessao;

    public Long getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(Long idSessao) {
        this.idSessao = idSessao;
    }

    private LocalDate dataInicio;

    private Boolean eUltima;

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Long getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Long idContrato) {
        this.idContrato = idContrato;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessao_selecionada);

        FloatingActionButton mensagemFimContrato = findViewById(R.id.botaoEnviarMensagemFimContrato);
        mensagemFimContrato.setVisibility(View.GONE);


        List <LocalDate> datasSessoes = new ArrayList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");



        Log.d("ClientesAdapter", "onClick: idSessao = " + getIntent().getLongExtra("idSessao", 1));
        setIdSessao(getIntent().getLongExtra("idSessao", 1));
        recyclerView = findViewById(R.id.recyclerViewSessaoSelecionada);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final SessaoSelecionadaAdapter adapter = new SessaoSelecionadaAdapter(new SessaoSelecionadaAdapter.sessaoDiff(), this);
        recyclerView.setAdapter(adapter);

        sessaoViewModel = new ViewModelProvider(this).get(SessaoViewModel.class);
        sessaoViewModel.retornarSessaoSelecionada(idSessao).observe(this, sessao -> {
                setIdContrato(sessao.getIdContrato());
                setDataInicio(LocalDate.parse(sessao.getDataInicio(), formatter));
                List<SessaoEntidade> singleItemList = new ArrayList<>();
                singleItemList.add(sessao);
                adapter.submitList(singleItemList);
                });

        SessaoViewModel sessaoViewModel = new ViewModelProvider(this).get(SessaoViewModel.class);
        sessaoViewModel.retornarTodasSessoes().observe(this, sessoes -> {
            for (SessaoEntidade sessao: sessoes){
                if (sessao.getIdContrato().equals(getIdContrato()) && sessao.getStatus().equals("MARCADA")){
                    LocalDate data =  LocalDate.parse(sessao.getDataInicio(), formatter);
                    datasSessoes.add(data);
                }
            }
            eUltima = false;
            for (LocalDate data: datasSessoes){
                if (getDataInicio().isAfter(data) || getDataInicio().isEqual(data)){
                    eUltima = true;
                }
                else{
                    eUltima = false;
                }
            }
            initializeButtom(eUltima);

        });

        FloatingActionButton botaoEnviarMensagem = findViewById(R.id.botaoEnviarMensagemConfirmacao);
        botaoEnviarMensagem.setOnClickListener(view -> {
            sessaoViewModel.retornarSessaoSelecionada(idSessao).observe(this, sessao -> {
                sessaoViewModel.retornarTelefoneClienteUsandoIdContrato(sessao.getIdContrato()).observe(this, telefone -> {
                    if (telefone != null){
                        String dataInicio = sessao.getDataInicio().substring(8,10) + "/" + sessao.getDataInicio().substring(5,7);
                        String mensagem = "Olá! Sua massagem está confirmada para o dia "+dataInicio+ " às "+sessao.getHoraInicio()+ "?" ;
                        String numeroTelefone = "+55" + telefone;
                        String uri = "https://wa.me/" + numeroTelefone + "/?text=" + mensagem;
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(uri));
                        startActivity(intent);
                    }
                });
            });
        });




        ImageView botaoEditar = findViewById(R.id.edit_icon);

        botaoEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SessaoSelecionadaAcitivity.this, EditarSessaoActivity.class);

                intent.putExtra("idSessao", idSessao);

                startActivity(intent);
            }

        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_item1) {
                Intent intent1 = new Intent(SessaoSelecionadaAcitivity.this, ExibirListaClientesActivity.class);
                startActivity(intent1);
                return true;
            } else if (item.getItemId() == R.id.menu_item2) {
                Intent intent2 = new Intent(SessaoSelecionadaAcitivity.this, ExibirListaServicosActivity.class);
                startActivity(intent2);
                return true;
            } else if (item.getItemId() == R.id.menu_item3) {
                Intent intent2 = new Intent(SessaoSelecionadaAcitivity.this, ExibirListaSessaoActivity.class);
                startActivity(intent2);
                return true;
            } else if (item.getItemId() == R.id.menu_item4) {
                Intent intent4 = new Intent(SessaoSelecionadaAcitivity.this, ExibirListaContratoActivity.class);
                startActivity(intent4);
                return true;
            } else if (item.getItemId() == R.id.menu_item5) {
                Intent intent5 = new Intent(SessaoSelecionadaAcitivity.this, RelatorioActivity.class);
                startActivity(intent5);
                return true;
            }
            return false;
        });

        ImageView botaoVoltar = findViewById(R.id.botao_voltar_sessaoselecionada);

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SessaoSelecionadaAcitivity.this, ExibirListaSessaoActivity.class);

                startActivity(intent);
            }

        });

    }

    private void initializeButtom(Boolean eUltima) {
        FloatingActionButton mensagemFimContrato = findViewById(R.id.botaoEnviarMensagemFimContrato);
        FloatingActionButton botaoEnviarMensagem = findViewById(R.id.botaoEnviarMensagemConfirmacao);
        if (eUltima){
            mensagemFimContrato.setVisibility(View.VISIBLE);
            botaoEnviarMensagem.setVisibility(View.GONE);
            mensagemFimContrato.setOnClickListener(view -> {
                sessaoViewModel.retornarSessaoSelecionada(idSessao).observe(this, sessao -> {
                    sessaoViewModel.retornarTelefoneClienteUsandoIdContrato(sessao.getIdContrato()).observe(this, telefone -> {
                        if (telefone != null){
                            String dataInicio = sessao.getDataInicio().substring(8,10) + "/" + sessao.getDataInicio().substring(5,7);
                            String mensagem = "Olá! Sua massagem está confirmada para o dia "+dataInicio+ " às "+sessao.getHoraInicio()+
                                    "? Esta também é sua última sessão do contrato. Caso deseje renová-lo estou disponível." ;
                            String numeroTelefone = "+55" + telefone;
                            String uri = "https://wa.me/" + numeroTelefone + "/?text=" + mensagem;
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(uri));
                            startActivity(intent);
                        }
                    });
                });
            });
        }
        else{
            mensagemFimContrato.setVisibility(View.GONE);
            botaoEnviarMensagem.setVisibility(View.VISIBLE);
        }

    }



}