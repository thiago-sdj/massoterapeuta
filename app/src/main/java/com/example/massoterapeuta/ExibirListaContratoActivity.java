package com.example.massoterapeuta;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.massoterapeuta.adapter.ContratoAdapter;
import com.example.massoterapeuta.bancoLocal.entidade.ContratoEntidade;
import com.example.massoterapeuta.bancoLocal.entidade.SessaoEntidade;
import com.example.massoterapeuta.viewModel.ContratoViewModel;
import com.example.massoterapeuta.viewModel.ServicoViewModel;
import com.example.massoterapeuta.viewModel.SessaoViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.materialswitch.MaterialSwitch;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ExibirListaContratoActivity extends AppCompatActivity {

    public static final int CADASTRAR_CONTRATOS_ACTIVITY_REQUEST_CODE = 1;
    private RecyclerView recyclerView;

    private ContratoViewModel contratoViewModel;

    private SessaoViewModel sessaoViewModel;

    private List <ContratoEntidade> contratosAtivos, contratosInativos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_lista_contrato);
        contratosAtivos = new ArrayList<>();
        contratosInativos = new ArrayList<>();


        contratoViewModel = new ViewModelProvider(this).get(ContratoViewModel.class);
        sessaoViewModel = new ViewModelProvider(this).get(SessaoViewModel.class);

        MaterialSwitch ativos = findViewById(R.id.switch_ativos);
        ativos.setChecked(true);
        contratoViewModel.retornarTodosContratos().observe(this, contratos -> {
            if (contratos != null){
                listarAtivos(contratos);
            }
        });

        ativos.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                contratoViewModel.retornarTodosContratos().observe(this, contratos -> {
                    if (contratos != null){
                        listarAtivos(contratos);
                    }
                });
            }
            else{
                contratoViewModel.retornarTodosContratos().observe(this, contratos -> {
                    if (contratos != null){
                        listarInativos(contratos);
                    }
                });
            }
        });



        ImageView botaoVoltar = findViewById(R.id.botao_voltar_listacontrato);

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExibirListaContratoActivity.this, ExibirListaSessaoActivity.class);
                startActivity(intent);
            }
        });

        ImageView botaoAdicionar = findViewById(R.id.adicionar_contrato);

        botaoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExibirListaContratoActivity.this, CadastrarContratoActivity.class);
                startActivityForResult(intent, CADASTRAR_CONTRATOS_ACTIVITY_REQUEST_CODE);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_item1) {
                Intent intent1 = new Intent(ExibirListaContratoActivity.this, ExibirListaClientesActivity.class);
                startActivity(intent1);
                return true;
            } else if (item.getItemId() == R.id.menu_item2) {
                Intent intent2 = new Intent(ExibirListaContratoActivity.this, ExibirListaServicosActivity.class);
                startActivity(intent2);
                return true;
            } else if (item.getItemId() == R.id.menu_item3) {
                Intent intent2 = new Intent(ExibirListaContratoActivity.this, ExibirListaSessaoActivity.class);
                startActivity(intent2);
                return true;
            } else if (item.getItemId() == R.id.menu_item4) {
                Intent intent4 = new Intent(ExibirListaContratoActivity.this, ExibirListaContratoActivity.class);
                startActivity(intent4);
                return true;
            } else if (item.getItemId() == R.id.menu_item5) {
                Intent intent5 = new Intent(ExibirListaContratoActivity.this, RelatorioActivity.class);
                startActivity(intent5);
                return true;
            }
            return false;
        });

    }

    public void listarAtivos(List <ContratoEntidade> contratos){
        LocalDate dataAtual = LocalDate.now();
        contratosAtivos.clear();
        for (ContratoEntidade contrato: contratos){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate dataFirmado = LocalDate.parse(contrato.getDataFirmado(), formatter);
            if (dataFirmado.plusMonths(3).isAfter(dataAtual)){
                contratosAtivos.add(contrato);
            }
        }
        recyclerView = findViewById(R.id.recyclerViewListaContrato);
        final ContratoAdapter adapter = new ContratoAdapter(new ContratoAdapter.contratoDiff(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.submitList(contratosAtivos);
    }

    public void listarInativos(List <ContratoEntidade> contratos){
        LocalDate dataAtual = LocalDate.now();
        contratosInativos.clear();
        for (ContratoEntidade contrato: contratos){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate dataFirmado = LocalDate.parse(contrato.getDataFirmado(), formatter);
            if (dataFirmado.plusMonths(3).isBefore(dataAtual)){
                contratosInativos.add(contrato);
            }
        }
        recyclerView = findViewById(R.id.recyclerViewListaContrato);
        final ContratoAdapter adapter = new ContratoAdapter(new ContratoAdapter.contratoDiff(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.submitList(contratosInativos);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CADASTRAR_CONTRATOS_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            ContratoEntidade contratoEntidade = new ContratoEntidade();

            contratoEntidade.setIdCliente(data.getLongExtra("idCliente", 1));

            Long idServico = data.getLongExtra("idServico", 1);
            contratoEntidade.setIdServico(idServico);

            Integer vezesSemana = data.getIntExtra("vezesSemana", 1);
            contratoEntidade.setVezesSemana(vezesSemana);

            Integer numeroSessoes = data.getIntExtra("numeroSessoes", 1);
            contratoEntidade.setNumeroSessoes(numeroSessoes);

            contratoEntidade.setFormaPagamento(data.getStringExtra("formaPagamento"));
            contratoEntidade.setValorPago(data.getDoubleExtra("valorPago", 0.00));

            String horaMinuto = data.getStringExtra("horaMinuto");

            LocalDate dataAtual = LocalDate.now();

            DateTimeFormatter formatterNovo = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String dataFormatada = dataAtual.format(formatterNovo);
            contratoEntidade.setDataFirmado(dataFormatada);

            Future<Long> idContratoFuture = contratoViewModel.inserir(contratoEntidade);
            try {
                Long idContrato = idContratoFuture.get();
                ServicoViewModel servicoViewModel = new ViewModelProvider(this).get(ServicoViewModel.class);
                servicoViewModel.retornarServicoSelecionadoPeso(idServico).observe(this, peso -> {
                    if (peso != null) {
                        servicoViewModel.retornarServicoSelecionadoTempo(idServico).observe(this, tempo -> {
                            if (tempo != null) {
                                Log.w("Deu certo", "Carregou");
                                Boolean checkBoxSegunda = data.getBooleanExtra("segundaIsChecked", false);
                                Boolean checkBoxTerca = data.getBooleanExtra("tercaIsChecked", false);
                                Boolean checkBoxQuarta = data.getBooleanExtra("quartaIsChecked", false);
                                Boolean checkBoxQuinta = data.getBooleanExtra("quintaIsChecked", false);
                                Boolean checkBoxSexta = data.getBooleanExtra("sextaIsChecked", false);
                                Boolean checkBoxSabado = data.getBooleanExtra("sabadoIsChecked", false);
                                cadastrarSessoes(idContrato, numeroSessoes, peso, horaMinuto, tempo, checkBoxSegunda, checkBoxTerca, checkBoxQuarta,
                                        checkBoxQuinta, checkBoxSexta, checkBoxSabado);

                            } else {
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Não foi possível encontrar tempo de serviço",
                                        Toast.LENGTH_LONG).show();
                            }
                        });

                    } else {
                        Toast.makeText(
                                getApplicationContext(),
                                "Não foi possível encontrar peso de serviço",
                                Toast.LENGTH_LONG).show();
                    }
                });

            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                Toast.makeText(
                        getApplicationContext(),
                        "Não foi possível retornar id de contrato",
                        Toast.LENGTH_LONG).show();

            }

        } else {
            Log.w("ERRO", "Não foi possível carregar");
        }
    }

    public void cadastrarSessoes(Long idContrato, Integer numeroSessoes, Float pesoSesssao, String horaMinuto, Integer tempo,
                                 Boolean checkBoxSegunda, Boolean checkBoxTerca, Boolean checkBoxQuarta,Boolean checkBoxQuinta,
                                 Boolean checkBoxSexta, Boolean checkBoxSabado){
        LocalDate dataAtual = LocalDate.now();
        int i = 0;
        String pattern = "HH:mm";
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern(pattern);
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        while (i<numeroSessoes){
            if (checkBoxSegunda == true) {
                SessaoEntidade sessaoEntidade = new SessaoEntidade();
                sessaoEntidade.setIdContrato(idContrato);
                sessaoEntidade.setPesoSessao(pesoSesssao);
                sessaoEntidade.setStatus("MARCADA");
                sessaoEntidade.setHoraInicio(horaMinuto);
                LocalTime horaFim = LocalTime.parse(horaMinuto, formatterTime);
                horaFim = horaFim.plusMinutes(tempo);
                sessaoEntidade.setHoraFim(horaFim.toString());
                dataAtual = dataAtual.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
                String dataFormatada = dataAtual.format(formatterDate);
                sessaoEntidade.setDataInicio(dataFormatada);
                sessaoViewModel.inserir(sessaoEntidade);
                Log.w("Sessão cadastrada: ", sessaoEntidade.getDataInicio());
                i++;
            }
            if (checkBoxTerca == true && i<numeroSessoes) {
                SessaoEntidade sessaoEntidade = new SessaoEntidade();
                sessaoEntidade.setIdContrato(idContrato);
                sessaoEntidade.setPesoSessao(pesoSesssao);
                sessaoEntidade.setStatus("MARCADA");
                sessaoEntidade.setHoraInicio(horaMinuto);
                LocalTime horaFim = LocalTime.parse(horaMinuto, formatterTime);
                horaFim = horaFim.plusMinutes(tempo);
                sessaoEntidade.setHoraFim(horaFim.toString());
                dataAtual = dataAtual.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
                String dataFormatada = dataAtual.format(formatterDate);
                sessaoEntidade.setDataInicio(dataFormatada);
                sessaoViewModel.inserir(sessaoEntidade);
                Log.w("Sessão cadastrada: ", sessaoEntidade.getDataInicio());
                i++;
            }
            if (checkBoxQuarta == true && i<numeroSessoes) {
                SessaoEntidade sessaoEntidade = new SessaoEntidade();
                sessaoEntidade.setIdContrato(idContrato);
                sessaoEntidade.setPesoSessao(pesoSesssao);
                sessaoEntidade.setStatus("MARCADA");
                sessaoEntidade.setHoraInicio(horaMinuto);
                LocalTime horaFim = LocalTime.parse(horaMinuto, formatterTime);
                horaFim = horaFim.plusMinutes(tempo);
                sessaoEntidade.setHoraFim(horaFim.toString());
                dataAtual = dataAtual.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
                String dataFormatada = dataAtual.format(formatterDate);
                sessaoEntidade.setDataInicio(dataFormatada);
                sessaoViewModel.inserir(sessaoEntidade);
                Log.w("Sessão cadastrada: ", sessaoEntidade.getDataInicio());
                i++;
            }
            if (checkBoxQuinta == true && i<numeroSessoes) {
                SessaoEntidade sessaoEntidade = new SessaoEntidade();
                sessaoEntidade.setIdContrato(idContrato);
                sessaoEntidade.setPesoSessao(pesoSesssao);
                sessaoEntidade.setStatus("MARCADA");
                sessaoEntidade.setHoraInicio(horaMinuto);
                LocalTime horaFim = LocalTime.parse(horaMinuto, formatterTime);
                horaFim = horaFim.plusMinutes(tempo);
                sessaoEntidade.setHoraFim(horaFim.toString());
                dataAtual = dataAtual.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
                String dataFormatada = dataAtual.format(formatterDate);
                sessaoEntidade.setDataInicio(dataFormatada);
                sessaoViewModel.inserir(sessaoEntidade);
                Log.w("Sessão cadastrada: ", sessaoEntidade.getDataInicio());
                i++;
            }
            if (checkBoxSexta == true && i<numeroSessoes) {
                SessaoEntidade sessaoEntidade = new SessaoEntidade();
                sessaoEntidade.setIdContrato(idContrato);
                sessaoEntidade.setPesoSessao(pesoSesssao);
                sessaoEntidade.setStatus("MARCADA");
                sessaoEntidade.setHoraInicio(horaMinuto);
                LocalTime horaFim = LocalTime.parse(horaMinuto, formatterTime);
                horaFim = horaFim.plusMinutes(tempo);
                sessaoEntidade.setHoraFim(horaFim.toString());
                dataAtual = dataAtual.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
                String dataFormatada = dataAtual.format(formatterDate);
                sessaoEntidade.setDataInicio(dataFormatada);
                sessaoViewModel.inserir(sessaoEntidade);
                Log.w("Sessão cadastrada: ", sessaoEntidade.getDataInicio());
                i++;
            }
            if (checkBoxSabado == true && i<numeroSessoes) {
                SessaoEntidade sessaoEntidade = new SessaoEntidade();
                sessaoEntidade.setIdContrato(idContrato);
                sessaoEntidade.setPesoSessao(pesoSesssao);
                sessaoEntidade.setStatus("MARCADA");
                sessaoEntidade.setHoraInicio(horaMinuto);
                LocalTime horaFim = LocalTime.parse(horaMinuto, formatterTime);
                horaFim = horaFim.plusMinutes(tempo);
                sessaoEntidade.setHoraFim(horaFim.toString());
                dataAtual = dataAtual.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
                String dataFormatada = dataAtual.format(formatterDate);
                sessaoEntidade.setDataInicio(dataFormatada);
                sessaoViewModel.inserir(sessaoEntidade);
                Log.w("Sessão cadastrada: ", sessaoEntidade.getDataInicio());
                i++;
            }
            dataAtual = dataAtual.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
            Log.w("Data atual", "é" + dataAtual);

        }

    }
}