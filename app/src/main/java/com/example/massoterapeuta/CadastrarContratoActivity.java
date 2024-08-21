package com.example.massoterapeuta;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.massoterapeuta.bancoLocal.entidade.ClienteEntidade;
import com.example.massoterapeuta.bancoLocal.entidade.ServicoEntidade;
import com.example.massoterapeuta.viewModel.ClienteViewModel;
import com.example.massoterapeuta.viewModel.ServicoViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class CadastrarContratoActivity extends AppCompatActivity {


    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    Boolean finished = false;

    ArrayList listaNomeClientes = new ArrayList<>();

    ArrayList listaIdClientes = new ArrayList<>();

    ArrayList listaNomeServicos = new ArrayList<>();

    ArrayList listaIdServicos = new ArrayList<>();

    String idClienteSelecionado;

    String idServicoSelecionado;

    Integer numeroSessoes, vezesSemana;
    Double valorPago;

    Boolean diaSelecionado = false;

    String formaPagamento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_contrato);
        ImageView botaoVoltar = findViewById(R.id.botao_voltar_cadastrarcontrato);

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastrarContratoActivity.this, ExibirListaContratoActivity.class);
                startActivity(intent);
            }
        });
        carregarClientes();
    }

    private void initializeComponents() {

        TextInputLayout textInputLayoutCodigoCliente = findViewById(R.id.layout_cadastro_codigo_cliente);
        TextInputLayout textInputLayoutCodigoServico = findViewById(R.id.layout_cadastro_codigo_servico);
        TextInputLayout textInputLayoutNumeroSessoes = findViewById(R.id.layout_cadastro_numero_sessoes);
        TextInputLayout textInputLayoutVezesSemana = findViewById(R.id.layout_cadastro_vezes_semana);
        TextInputLayout textInputLayoutValorPago = findViewById(R.id.layout_cadastro_valor_pago);
        TextInputLayout textInputLayoutDiaSemana = findViewById(R.id.layout_cadastro_dia_semana);

        TextInputEditText inputEditCliente = findViewById(R.id.cadastro_codigo_cliente);
        inputEditCliente.setText(idClienteSelecionado);
        inputEditCliente.setClickable(false);
        inputEditCliente.setFocusable(false);


        TextInputEditText inputEditServico = findViewById(R.id.cadastro_codigo_servico);
        inputEditServico.setText(idServicoSelecionado);
        inputEditServico.setClickable(false);
        inputEditServico.setFocusable(false);

        TextInputEditText inputEditVezesSemana = findViewById(R.id.cadastro_vezes_semana);
        TextInputEditText inputEditNumeroSessoes = findViewById(R.id.cadastro_numero_sessoes);
        TextInputEditText inputEditFormaPagamento = findViewById(R.id.cadastro_forma_pagamento);
        TextInputEditText inputEditValorPago = findViewById(R.id.cadastro_valor_pago);
        TimePicker timePicker = findViewById(R.id.cadastro_horario);

        CheckBox checkBoxSegunda = findViewById(R.id.checkbox_segunda);
        checkBoxSegunda.setOnCheckedChangeListener((buttonView, segundaIsChecked) -> {
            Log.d("CHECKBOXES", "Segunda is checked: " + segundaIsChecked);
            diaSelecionado = true;
        });

        CheckBox checkBoxTerca = findViewById(R.id.checkbox_terca);
        checkBoxTerca.setOnCheckedChangeListener((buttonView, tercaisChecked) -> {
            Log.d("CHECKBOXES", "Terça is checked: " + tercaisChecked);
            diaSelecionado = true;
        });

        CheckBox checkBoxQuarta = findViewById(R.id.checkbox_quarta);
        checkBoxQuarta.setOnCheckedChangeListener((buttonView, quartaisChecked) -> {
            Log.d("CHECKBOXES", "Quarta is checked: " + quartaisChecked);
            diaSelecionado = true;
        });

        CheckBox checkBoxQuinta = findViewById(R.id.checkbox_quinta);
        checkBoxQuinta.setOnCheckedChangeListener((buttonView, quintaisChecked) -> {
            Log.d("CHECKBOXES", "Quinta is checked: " + quintaisChecked);
            diaSelecionado = true;
        });

        CheckBox checkBoxSexta = findViewById(R.id.checkbox_sexta);
        checkBoxSexta.setOnCheckedChangeListener((buttonView, sextaisChecked) -> {
            Log.d("CHECKBOXES", "Sexta is checked: " + sextaisChecked);
            diaSelecionado = true;
        });

        CheckBox checkBoxSabado = findViewById(R.id.checkbox_sabado);
        checkBoxSabado.setOnCheckedChangeListener((buttonView, sabadoisChecked) -> {
            Log.d("CHECKBOXES", "Sábado is checked: " + sabadoisChecked);
            diaSelecionado = true;
        });


        MaterialButton botaoSalvar = findViewById(R.id.form_buttonSaveContrato);

        botaoSalvar.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            Boolean parsingSuccessful = true;
            if (TextUtils.isEmpty(inputEditCliente.getText())) {
                textInputLayoutCodigoCliente.setError("Selecione um cliente na caixa acima");
            }
            else if (TextUtils.isEmpty(inputEditServico.getText())){
                textInputLayoutCodigoServico.setError("Selecione um serviço na caixa acima");
            }
            else if (TextUtils.isEmpty(inputEditNumeroSessoes.getText())){
                textInputLayoutNumeroSessoes.setError("Digite o número de sessões");
            }
            else if (diaSelecionado == false) {
                textInputLayoutDiaSemana.setError("Selecione pelo menos um dia na semana");
            }
            else {

                try{
                    String numeroSessoesString = String.valueOf(inputEditNumeroSessoes.getText());
                    numeroSessoes = new Integer(numeroSessoesString);
                }catch (NumberFormatException e){
                    textInputLayoutNumeroSessoes.setError("Digite um valor numérico válido");
                    parsingSuccessful = false;
                }

                try{
                    String valorPagoString = String.valueOf(inputEditValorPago.getText());
                    valorPago = new Double(valorPagoString);
                }catch (NumberFormatException e){
                    textInputLayoutValorPago.setError("Digite um valor numérico válido");
                    parsingSuccessful = false;
                }

                try{
                    String vezesSemanaString = String.valueOf(inputEditVezesSemana.getText());
                    vezesSemana = new Integer(vezesSemanaString);
                }catch (NumberFormatException e){
                    textInputLayoutVezesSemana.setError("Digite um valor numérico válido");
                    parsingSuccessful = false;
                }


                String idClienteString = inputEditCliente.getText().toString().trim();
                Long idCliente = new Long(idClienteString);

                String idServicoString = inputEditServico.getText().toString().trim();
                Long idServico = new Long(idServicoString);

                if (TextUtils.isEmpty(inputEditFormaPagamento.getText())){
                    formaPagamento = " ";
                }
                else{
                    formaPagamento = String.valueOf(inputEditFormaPagamento.getText());
                }


                String hora, minuto;
                if (timePicker.getHour() < 10) {
                    hora = String.valueOf(timePicker.getHour());
                    hora = "0" + hora;
                } else {
                    hora = String.valueOf(timePicker.getHour());
                }
                if (timePicker.getMinute() < 10) {
                    minuto = String.valueOf(timePicker.getMinute());
                    minuto = "0" + minuto;
                } else {
                    minuto = String.valueOf(timePicker.getMinute());
                }
                String horaMinuto = hora + ":" + minuto;


                if (parsingSuccessful){
                    Bundle bundle = new Bundle();
                    bundle.putLong("idCliente", idCliente);
                    bundle.putLong("idServico", idServico);
                    bundle.putString("horaMinuto", horaMinuto);
                    bundle.putInt("vezesSemana", vezesSemana);
                    bundle.putInt("numeroSessoes", numeroSessoes);
                    bundle.putString("formaPagamento", formaPagamento);
                    bundle.putDouble("valorPago", valorPago);
                    bundle.putBoolean("segundaIsChecked", checkBoxSegunda.isChecked());
                    bundle.putBoolean("tercaIsChecked", checkBoxTerca.isChecked());
                    bundle.putBoolean("quartaIsChecked", checkBoxQuarta.isChecked());
                    bundle.putBoolean("quintaIsChecked", checkBoxQuinta.isChecked());
                    bundle.putBoolean("sextaIsChecked", checkBoxSexta.isChecked());
                    bundle.putBoolean("sabadoIsChecked", checkBoxSabado.isChecked());
                    replyIntent.putExtras(bundle);
                    setResult(RESULT_OK, replyIntent);
                    finish();
                }
            }
        });


    }

    private void carregarClientes() {
        ClienteViewModel clienteViewModel = new ViewModelProvider(this).get(ClienteViewModel.class);
        clienteViewModel.retornarTodosClientes().observe(this, clientes -> {
            if (clientes != null) {
                for (ClienteEntidade cliente : clientes) {
                    String clienteNome = cliente.getNome();
                    listaNomeClientes.add(clienteNome);
                    listaIdClientes.add(cliente.getIdCliente());
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.layout_item_selecionado, listaNomeClientes);
                arrayAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                Spinner spinner = (Spinner) findViewById(R.id.cadastro_nome_cliente);
                spinner.setAdapter(arrayAdapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String nomeClienteSelecionado = (String) parent.getItemAtPosition(position);
                        idClienteSelecionado = listaIdClientes.get(position).toString();
                        carregarServicos();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        Toast.makeText(getApplicationContext(), "Não foi selecionado", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(
                        getApplicationContext(),
                        "Não foi possível carregar clientes",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

        private void carregarServicos () {
            ServicoViewModel servicoViewModel = new ViewModelProvider(this).get(ServicoViewModel.class);
            servicoViewModel.retornarTodosServicos().observe(this, servicos -> {
                if (servicos != null) {
                    if (listaNomeServicos.isEmpty()){
                        for (ServicoEntidade servico : servicos) {
                            String clienteNome = servico.getDescricao();
                            listaNomeServicos.add(clienteNome);
                            listaIdServicos.add(servico.getIdServico());
                        }
                    }
                    ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.layout_item_selecionado, listaNomeServicos);
                    arrayAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                    Spinner spinner = (Spinner) findViewById(R.id.cadastro_nome_servico);
                    spinner.setAdapter(arrayAdapter);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String nomeServicoSelecionado = (String) parent.getItemAtPosition(position);
                            idServicoSelecionado = listaIdServicos.get(position).toString();
                            initializeComponents();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            Toast.makeText(getApplicationContext(), "Não foi selecionado", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            "Não foi possível carregar clientes",
                            Toast.LENGTH_LONG).show();
                }
            });
        }



}
