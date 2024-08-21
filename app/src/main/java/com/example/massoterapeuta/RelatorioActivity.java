package com.example.massoterapeuta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.massoterapeuta.adapter.RelatorioAdapter;
import com.example.massoterapeuta.bancoLocal.entidade.ContratoEntidade;
import com.example.massoterapeuta.viewModel.ContratoViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class RelatorioActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ContratoViewModel contratoViewModel;

    private ArrayList listaFiltrada = new ArrayList<>();
    private ArrayList ano = new ArrayList<>();

    private ArrayList mes = new ArrayList<>();

    private Double valorTotalFiltradoPorData, valorTotalFiltradoPorMes, valorTotalFiltradoPorAno, valorTotal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);
        TextView textViewTotalNome = findViewById(R.id.relatorio_total_nome);
        textViewTotalNome.setVisibility(View.GONE);
        TextView textViewTotal = findViewById(R.id.relatorio_total);

        ImageView botaoVoltar = findViewById(R.id.ic_voltar);
        botaoVoltar.setOnClickListener(view -> {
            Intent intent = new Intent(RelatorioActivity.this, ExibirListaSessaoActivity.class);
            startActivity(intent);
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_item1) {
                Intent intent1 = new Intent(RelatorioActivity.this, ExibirListaClientesActivity.class);
                startActivity(intent1);
                return true;
            } else if (item.getItemId() == R.id.menu_item2) {
                Intent intent2 = new Intent(RelatorioActivity.this, ExibirListaServicosActivity.class);
                startActivity(intent2);
                return true;
            } else if (item.getItemId() == R.id.menu_item3) {
                Intent intent2 = new Intent(RelatorioActivity.this, ExibirListaSessaoActivity.class);
                startActivity(intent2);
                return true;
            } else if (item.getItemId() == R.id.menu_item4) {
                Intent intent4 = new Intent(RelatorioActivity.this, ExibirListaContratoActivity.class);
                startActivity(intent4);
                return true;
            } else if (item.getItemId() == R.id.menu_item5) {
                Intent intent5 = new Intent(RelatorioActivity.this, RelatorioActivity.class);
                startActivity(intent5);
                return true;
            }
            return false;
        });
        recyclerView = findViewById(R.id.recyclerViewRelatorio);
        final RelatorioAdapter adapter = new RelatorioAdapter(new RelatorioAdapter.relatorioDiff(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contratoViewModel = new ViewModelProvider(this).get(ContratoViewModel.class);

        contratoViewModel.retornarTodosContratos().observe(this, contratos -> {
            if (contratos != null){
                adapter.submitList(contratos);

            }
        });
        ano.add("Todos");
        ano.add("2024");
        ano.add("2023");
        ano.add("2022");
        ArrayAdapter arrayAdapterAno = new ArrayAdapter(this, R.layout.layout_item_selecionado_relatorio, ano);
        arrayAdapterAno.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        Spinner spinnerAno = (Spinner) findViewById(R.id.relatorio_filtro_ano);
        spinnerAno.setAdapter(arrayAdapterAno);

        mes.add("Todos");
        mes.add("01");
        mes.add("02");
        mes.add("03");
        mes.add("04");
        mes.add("05");
        mes.add("06");
        mes.add("07");
        mes.add("08");
        mes.add("09");
        mes.add("10");
        mes.add("11");
        mes.add("12");
        ArrayAdapter arrayAdapterMes = new ArrayAdapter(this,  R.layout.layout_item_selecionado_relatorio, mes);
        arrayAdapterMes.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        Spinner spinnerMes = (Spinner) findViewById(R.id.relatorio_filtro_mes);
        spinnerMes.setAdapter(arrayAdapterMes);

        Button applyFilterButton = findViewById(R.id.botao_filtro);
        applyFilterButton.setOnClickListener(view -> {
            String mesSelecionado = spinnerMes.getSelectedItem().toString();
            String anoSelecionado = spinnerAno.getSelectedItem().toString();
            if (mesSelecionado.equals("Todos") && anoSelecionado.equals("Todos")){
                contratoViewModel.retornarTodosContratos().observe(this, contratos -> {
                    if (contratos != null) {
                        adapter.submitList(contratos);
                        adapter.notifyDataSetChanged();
                        valorTotal = 0.00;
                        for(ContratoEntidade contrato: contratos){
                            valorTotal = valorTotal + contrato.getValorPago();
                        }
                        textViewTotalNome.setVisibility(View.VISIBLE);

                        DecimalFormat formatter = new DecimalFormat("#,###.00");
                        String valorTotalFormatado = formatter.format(valorTotal);
                        textViewTotal.setText(valorTotalFormatado);
                    }

                });
            }
            else if (mesSelecionado.equals("Todos")){
                adapter.submitList(filtrarContratosPorAno(anoSelecionado));
                adapter.notifyDataSetChanged();
                textViewTotalNome.setVisibility(View.VISIBLE);
                DecimalFormat formatter = new DecimalFormat("#,###.00");
                String valorTotalFiltradoPorAnoFormatado = formatter.format(valorTotalFiltradoPorAno);
                textViewTotal.setText(valorTotalFiltradoPorAnoFormatado);
            }
            else if(anoSelecionado.equals("Todos")){
                adapter.submitList(filtrarContratosPorMes(mesSelecionado));
                adapter.notifyDataSetChanged();
                textViewTotalNome.setVisibility(View.VISIBLE);
                DecimalFormat formatter = new DecimalFormat("#,###.00");
                String valorTotalFiltradoPorMesFormatado = formatter.format(valorTotalFiltradoPorMes);
                textViewTotal.setText(valorTotalFiltradoPorMesFormatado);
            }
            else{
                String selectedDateRange = anoSelecionado + "/" + mesSelecionado;
                adapter.submitList(filtrarContratosPorData(selectedDateRange));
                adapter.notifyDataSetChanged();
                textViewTotalNome.setVisibility(View.VISIBLE);
                DecimalFormat formatter = new DecimalFormat("#,###.00");
                String valorTotalFiltradoPorDataFormatado= formatter.format(valorTotalFiltradoPorData);
                textViewTotal.setText(valorTotalFiltradoPorDataFormatado);
            }

        });

    }


    private ArrayList<ContratoEntidade> filtrarContratosPorData(String dataSelecionada) {
        contratoViewModel.retornarTodosContratos().observe(this, contratos -> {
            if (contratos != null) {
                listaFiltrada.clear();
                valorTotalFiltradoPorData = 0.00;
                for (ContratoEntidade contrato : contratos) {
                    String dataFirmado = contrato.getDataFirmado().substring(0,7);
                    if (dataFirmado.equals(dataSelecionada)) {
                        listaFiltrada.add(contrato);
                        valorTotalFiltradoPorData = valorTotalFiltradoPorData + contrato.getValorPago();
                    }
                }

            }
        });

        return listaFiltrada;
    }
    private ArrayList<ContratoEntidade> filtrarContratosPorMes (String dataSelecionada) {
        contratoViewModel.retornarTodosContratos().observe(this, contratos -> {
            if (contratos != null) {
                listaFiltrada.clear();
                valorTotalFiltradoPorMes = 0.00;
                for (ContratoEntidade contrato : contratos) {
                    String dataFirmado = contrato.getDataFirmado().substring(5,7);
                    if (dataFirmado.equals(dataSelecionada)) {
                        listaFiltrada.add(contrato);
                        valorTotalFiltradoPorMes = valorTotalFiltradoPorMes + contrato.getValorPago();
                    }
                }

            }
        });

        return listaFiltrada;
    }

    private ArrayList<ContratoEntidade> filtrarContratosPorAno(String dataSelecionada) {
        contratoViewModel.retornarTodosContratos().observe(this, contratos -> {
            if (contratos != null) {
                listaFiltrada.clear();
                valorTotalFiltradoPorAno = 0.00;
                for (ContratoEntidade contrato : contratos) {
                    String dataFirmado = contrato.getDataFirmado().substring(0,4);
                    if (dataFirmado.equals(dataSelecionada)) {
                        listaFiltrada.add(contrato);
                        valorTotalFiltradoPorAno = valorTotalFiltradoPorAno + contrato.getValorPago();
                    }
                }

            }
        });

        return listaFiltrada;
    }





    }

