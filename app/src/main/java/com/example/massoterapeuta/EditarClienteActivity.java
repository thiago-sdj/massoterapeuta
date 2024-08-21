package com.example.massoterapeuta;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.massoterapeuta.bancoLocal.entidade.ClienteEntidade;
import com.example.massoterapeuta.bancoLocal.entidade.ContratoEntidade;
import com.example.massoterapeuta.viewModel.ClienteViewModel;
import com.example.massoterapeuta.viewModel.ContratoViewModel;
import com.example.massoterapeuta.viewModel.SessaoViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class EditarClienteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ClienteViewModel clienteViewModel;

    private ContratoViewModel contratoViewModel;

    private SessaoViewModel sessaoViewModel;

    private ClienteEntidade clienteEntidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cliente);
        
        ImageView botaoVoltar = findViewById(R.id.ic_voltar);
        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditarClienteActivity.this, ExibirListaClientesActivity.class);
                startActivity(intent);
            }
        });
        contratoViewModel = new ViewModelProvider(this).get(ContratoViewModel.class);

       Long idCliente = getIntent().getLongExtra("idCliente", 1);
        clienteViewModel = new ViewModelProvider(this).get(ClienteViewModel.class);
        clienteViewModel.retornarClienteSelecionado(idCliente).observe(this, cliente -> {
            if (cliente != null) {
                clienteEntidade = cliente;
                initializeComponents(clienteEntidade);
            } else {
                Toast.makeText(this, "Não foi possível carregar o cliente", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initializeComponents(ClienteEntidade clienteEntidade) {
        TextInputLayout textInputLayoutNome = findViewById(R.id.layout_editar_nome);
        TextInputLayout textInputLayoutTelefone = findViewById(R.id.layout_editar_telefone);
        TextInputLayout textInputLayoutBairro = findViewById(R.id.layout_editar_bairro);
        TextInputLayout textInputLayoutLogradouro = findViewById(R.id.layout_editar_logradouro);
        TextInputLayout textInputLayoutNumero = findViewById(R.id.layout_editar_numero);


        TextInputEditText inputEditNome = findViewById(R.id.editar_nome);
            inputEditNome.setText(clienteEntidade.getNome());


        TextInputEditText inputEditTelefone = findViewById(R.id.editar_telefone);
            inputEditTelefone.setText(clienteEntidade.getTelefone());


        TextInputEditText inputEditOcupacao = findViewById(R.id.editar_ocupacao);
            inputEditOcupacao.setText(clienteEntidade.getOcupacao());


        String[] sexoOpcoes = {"MASCULINO", "FEMININO"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.layout_item_selecionado, sexoOpcoes);
        arrayAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        Spinner spinner = (Spinner) findViewById(R.id.editar_sexo);
        spinner.setAdapter(arrayAdapter);
        if (clienteEntidade.getSexo().equals("MASCULINO")){
            spinner.setSelection(0, false);
        }
        else {
            spinner.setSelection(1, false);
        }



        TextInputEditText inputEditLogradouro = findViewById(R.id.editar_logradouro);
            inputEditLogradouro.setText(clienteEntidade.getLogradouro());


        TextInputEditText inputEditComplemento = findViewById(R.id.editar_complemento);
            inputEditComplemento.setText(clienteEntidade.getComplemento());


        TextInputEditText inputEditBairro = findViewById(R.id.editar_bairro);
            inputEditBairro.setText(clienteEntidade.getBairro());


        TextInputEditText inputEditCep = findViewById(R.id.editar_cep);
            inputEditCep.setText(clienteEntidade.getCep());


        TextInputEditText inputEditNumero = findViewById(R.id.editar_numero);
            inputEditNumero.setText(String.valueOf(clienteEntidade.getNumero()));


        TextInputEditText inputEditEmail = findViewById(R.id.editar_email);
            inputEditEmail.setText(clienteEntidade.getEmail());


        TextInputEditText inputEditQueixas = findViewById(R.id.editar_queixas);
            inputEditQueixas.setText(clienteEntidade.getQueixas());


        TextInputEditText inputEditHistorico = findViewById(R.id.editar_historico);
            inputEditHistorico.setText(clienteEntidade.getHistorico());


        TextInputEditText inputEditMedicamentos = findViewById(R.id.editar_medicamentos);
            inputEditMedicamentos.setText(clienteEntidade.getMedicamentos());


        TextInputEditText inputEditSono = findViewById(R.id.editar_sono);
            inputEditSono.setText(clienteEntidade.getSono());


        TextInputEditText inputEditAlimentacao = findViewById(R.id.editar_alimentacao);
            inputEditAlimentacao.setText(clienteEntidade.getAlimentacao());


        TextInputEditText inputEditDigestao = findViewById(R.id.editar_digestao);
            inputEditDigestao.setText(clienteEntidade.getDigestao());


        TextInputEditText inputEditExcrecao = findViewById(R.id.editar_excrecao);
            inputEditExcrecao.setText(clienteEntidade.getExcrecao());


        TextInputEditText inputEditDoresFisicas = findViewById(R.id.editar_doresFisicas);
            inputEditDoresFisicas.setText(clienteEntidade.getDoresFisicas());


        TextInputEditText inputEditDoresEmocionais = findViewById(R.id.editar_doresEmocionais);
            inputEditDoresEmocionais.setText(clienteEntidade.getDoresEmocionais());


        TextInputEditText inputEditMenstruacao = findViewById(R.id.editar_menstruação);
            inputEditMenstruacao.setText(clienteEntidade.getMenstruacao());


        TextInputEditText inputEditParto = findViewById(R.id.editar_parto);
            inputEditParto.setText(clienteEntidade.getParto());


        TextInputEditText inputEditCirurgias = findViewById(R.id.editar_cirurgias);
            inputEditCirurgias.setText(clienteEntidade.getCirurgias());

        TextInputEditText inputEditMobilidade = findViewById(R.id.editar_mobilidade);
            inputEditMobilidade.setText(clienteEntidade.getMobilidade());


        TextInputEditText inputEditVicios = findViewById(R.id.editar_vicios);
            inputEditVicios.setText(clienteEntidade.getVicios());


        TextInputEditText inputEditHormonal = findViewById(R.id.editar_hormonal);
            inputEditHormonal.setText(clienteEntidade.getHormonal());


        TextInputEditText inputEditDataNascimento = findViewById(R.id.editar_dataNascimento);
        inputEditDataNascimento.setText(clienteEntidade.getDataNascimento());
        inputEditDataNascimento.setInputType(InputType.TYPE_NULL);
        inputEditDataNascimento.setClickable(true);
        inputEditDataNascimento.setFocusable(true);
        inputEditDataNascimento.setOnClickListener(view -> showDatePickerDialog(inputEditDataNascimento));

        TextInputEditText inputEditObservacao = findViewById(R.id.editar_observação);
        inputEditObservacao.setText(clienteEntidade.getObservacao());


        MaterialButton botaoApagar = findViewById(R.id.form_botaoApagarCliente);
        botaoApagar.setOnClickListener(view -> {
            clienteViewModel.apagar(clienteEntidade);
            apagarContratos(clienteEntidade);
            Toast.makeText(this, "Cliente apagado", Toast.LENGTH_LONG);
            Intent intent = new Intent(EditarClienteActivity.this, ExibirListaClientesActivity.class);
            startActivity(intent);
        });


        MaterialButton botaoSalvar = findViewById(R.id.form_botaoSalvarCliente);
        botaoSalvar.setOnClickListener(view -> {

            TextInputEditText inputEditNomeAtualizado = findViewById(R.id.editar_nome);


            TextInputEditText inputEditTelefoneAtualizado = findViewById(R.id.editar_telefone);


            TextInputEditText inputEditOcupacaoAtualizado = findViewById(R.id.editar_ocupacao);

            String sexoSelecionado = spinner.getSelectedItem().toString();

            TextInputEditText inputEditLogradouroAtualizado = findViewById(R.id.editar_logradouro);


            TextInputEditText inputEditComplementoAtualizado = findViewById(R.id.editar_complemento);


            TextInputEditText inputEditBairroAtualizado = findViewById(R.id.editar_bairro);


            TextInputEditText inputEditCepAtualizado = findViewById(R.id.editar_cep);


            TextInputEditText inputEditNumeroAtualizado = findViewById(R.id.editar_numero);


            TextInputEditText inputEditEmailAtualizado = findViewById(R.id.editar_email);


            TextInputEditText inputEditQueixasAtualizado = findViewById(R.id.editar_queixas);


            TextInputEditText inputEditHistoricoAtualizado = findViewById(R.id.editar_historico);


            TextInputEditText inputEditMedicamentosAtualizado = findViewById(R.id.editar_medicamentos);


            TextInputEditText inputEditSonoAtualizado = findViewById(R.id.editar_sono);


            TextInputEditText inputEditAlimentacaoAtualizado = findViewById(R.id.editar_alimentacao);


            TextInputEditText inputEditDigestaoAtualizado = findViewById(R.id.editar_digestao);


            TextInputEditText inputEditExcrecaoAtualizado = findViewById(R.id.editar_excrecao);


            TextInputEditText inputEditDoresFisicasAtualizado = findViewById(R.id.editar_doresFisicas);


            TextInputEditText inputEditDoresEmocionaisAtualizado = findViewById(R.id.editar_doresEmocionais);


            TextInputEditText inputEditMenstruacaoAtualizado = findViewById(R.id.editar_menstruação);


            TextInputEditText inputEditPartoAtualizado = findViewById(R.id.editar_parto);


            TextInputEditText inputEditCirurgiasAtualizado = findViewById(R.id.editar_cirurgias);

            TextInputEditText inputEditMobilidadeAtualizado = findViewById(R.id.editar_mobilidade);


            TextInputEditText inputEditViciosAtualizado = findViewById(R.id.editar_vicios);


            TextInputEditText inputEditHormonalAtualizado = findViewById(R.id.editar_hormonal);


            TextInputEditText inputEditDataNascimentoAtualizado = findViewById(R.id.editar_dataNascimento);


            TextInputEditText inputEditObservacaoAtualizado = findViewById(R.id.editar_observação);

            if (TextUtils.isEmpty(inputEditNomeAtualizado.getText())){
                textInputLayoutNome.setError("INSIRA O NOME");
            }
            else if(TextUtils.isEmpty(inputEditTelefoneAtualizado.getText())){
                textInputLayoutTelefone.setError("INSIRA O TELEFONE");
            }
            else if(TextUtils.isEmpty(inputEditLogradouroAtualizado.getText())){
                textInputLayoutLogradouro.setError("INSIRA O LOGRADOURO");
            }
            else if(TextUtils.isEmpty(inputEditBairroAtualizado.getText())){
                textInputLayoutBairro.setError("INSIRA O BAIRRO");
            }
            else if(TextUtils.isEmpty(inputEditNumeroAtualizado.getText())){
                textInputLayoutNumero.setError("INSIRA O NÚMERO");
            }
            else{
                String nome = String.valueOf(inputEditNomeAtualizado.getText());
                String telefone = String.valueOf(inputEditTelefoneAtualizado.getText());
                String ocupação = String.valueOf(inputEditOcupacaoAtualizado.getText());
                String sexo = sexoSelecionado;
                String logradouro = String.valueOf(inputEditLogradouroAtualizado.getText());
                String complemento = String.valueOf(inputEditComplementoAtualizado.getText());
                String bairro = String.valueOf(inputEditBairroAtualizado.getText());
                String cep = String.valueOf(inputEditCepAtualizado.getText());
                String numero = String.valueOf(inputEditNumeroAtualizado.getText());
                String email = String.valueOf(inputEditEmailAtualizado.getText());
                String queixas = String.valueOf(inputEditQueixasAtualizado.getText());
                String historico = String.valueOf(inputEditHistoricoAtualizado.getText());
                String medicamentos = String.valueOf(inputEditMedicamentosAtualizado.getText());
                String sono = String.valueOf(inputEditSonoAtualizado.getText());
                String alimentacao = String.valueOf(inputEditAlimentacaoAtualizado.getText());
                String digestao = String.valueOf(inputEditDigestaoAtualizado.getText());
                String excrecao = String.valueOf(inputEditExcrecaoAtualizado.getText());
                String doresFisicas = String.valueOf(inputEditDoresFisicasAtualizado.getText());
                String doresEmocionais = String.valueOf(inputEditDoresEmocionaisAtualizado.getText());
                String menstruacao = String.valueOf(inputEditMenstruacaoAtualizado.getText());
                String parto = String.valueOf(inputEditPartoAtualizado.getText());
                String cirurgias = String.valueOf(inputEditCirurgiasAtualizado.getText());
                String mobilidade = String.valueOf(inputEditMobilidadeAtualizado.getText());
                String vicios = String.valueOf(inputEditViciosAtualizado.getText());
                String hormonal = String.valueOf(inputEditHormonalAtualizado.getText());
                String dataNascimento = String.valueOf(inputEditDataNascimentoAtualizado.getText());
                String observacao = String.valueOf(inputEditObservacaoAtualizado.getText());


                clienteEntidade.setNome(nome);
                clienteEntidade.setTelefone(telefone);
                clienteEntidade.setOcupacao(ocupação);
                clienteEntidade.setSexo(sexo);
                clienteEntidade.setLogradouro(logradouro);
                clienteEntidade.setComplemento(complemento);
                clienteEntidade.setBairro(bairro);
                clienteEntidade.setCep(cep);
                clienteEntidade.setNumero(numero);
                clienteEntidade.setEmail(email);
                clienteEntidade.setQueixas(queixas);
                clienteEntidade.setHistorico(historico);
                clienteEntidade.setMedicamentos(medicamentos);
                clienteEntidade.setSono(sono);
                clienteEntidade.setAlimentacao(alimentacao);
                clienteEntidade.setDigestao(digestao);
                clienteEntidade.setExcrecao(excrecao);
                clienteEntidade.setDoresFisicas(doresFisicas);
                clienteEntidade.setDoresEmocionais(doresEmocionais);
                clienteEntidade.setMenstruacao(menstruacao);
                clienteEntidade.setParto(parto);
                clienteEntidade.setCirurgias(cirurgias);
                clienteEntidade.setMobilidade(mobilidade);
                clienteEntidade.setVicios(vicios);
                clienteEntidade.setHormonal(hormonal);
                clienteEntidade.setDataNascimento(dataNascimento);
                clienteEntidade.setObservacao(observacao);

                clienteViewModel.atualizar(clienteEntidade);
                Intent intent = new Intent(EditarClienteActivity.this, ClienteSelecionadoActivity.class);
                intent.putExtra("idCliente", clienteEntidade.getIdCliente());
                startActivity(intent);
            }



        });
    }

    private void apagarContratos(ClienteEntidade clienteEntidade){
        contratoViewModel.retornarTodosContratos().observe(this, contratos -> {
            if (contratos != null){
                apagarSessoes(contratos);
            }
            else{
                Toast.makeText(this, "Não foram encontrados contratos desse cliente para serem apagados", Toast.LENGTH_LONG);
            }
        });


    }

    private void apagarSessoes(List<ContratoEntidade> contratos){
        sessaoViewModel = new ViewModelProvider(this).get(SessaoViewModel.class);
        for (ContratoEntidade contrato: contratos){
            if (contrato.getIdCliente().equals(clienteEntidade.getIdCliente())){
                Log.w("contratoId", contrato.getIdContrato().toString());
                sessaoViewModel.apagarComIdContrato(contrato.getIdContrato());
            }
        }
        contratoViewModel.apagarComIdCliente(clienteEntidade.getIdCliente());
        Toast.makeText(this, "Contratos e sessões associadas ao cliente apagadas", Toast.LENGTH_LONG);
    }

    private void showDatePickerDialog(TextInputEditText inputEditDataNascimento) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    LocalDate selectedDate = LocalDate.of(selectedYear, selectedMonth + 1, selectedDay);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault());
                    String formattedDate = selectedDate.format(formatter).toString();
                    inputEditDataNascimento.setText(formattedDate);
                },
                year, month, day);

        datePickerDialog.show();
    }
}
