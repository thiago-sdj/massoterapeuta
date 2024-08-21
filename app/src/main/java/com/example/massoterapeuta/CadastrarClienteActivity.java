package com.example.massoterapeuta;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class CadastrarClienteActivity extends AppCompatActivity {

    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);
        ImageView botaoVoltar = findViewById(R.id.ic_voltar);

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastrarClienteActivity.this, ExibirListaClientesActivity.class);
                startActivity(intent);
            }
        });
        initializeComponents();
    }

    private void initializeComponents() {
        TextInputLayout textInputLayoutNome = findViewById(R.id.layout_cadastro_nome);
        TextInputLayout textInputLayoutLogradouro = findViewById(R.id.layout_cadastro_logradouro);
        TextInputLayout textInputLayoutBairro = findViewById(R.id.layout_cadastro_bairro);
        TextInputLayout textInputLayoutNumero = findViewById(R.id.layout_cadastro_numero);
        TextInputLayout textInputLayoutTelefone = findViewById(R.id.layout_cadastro_telefone);

        TextInputEditText inputEditNome = findViewById(R.id.cadastro_nome);
        TextInputEditText inputEditTelefone = findViewById(R.id.cadastro_telefone);
        TextInputEditText inputEditOcupacao = findViewById(R.id.cadastro_ocupacao);

        String[] sexoOpcoes = {"MASCULINO", "FEMININO"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.layout_item_selecionado, sexoOpcoes);
        arrayAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        Spinner spinner = (Spinner) findViewById(R.id.cadastro_sexo);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bundle.putString("sexo", ((String) parent.getItemAtPosition(position)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Sexo não foi selecionado", Toast.LENGTH_SHORT).show();
            }
        });

        TextInputEditText inputEditLogradouro = findViewById(R.id.cadastro_logradouro);
        TextInputEditText inputEditComplemento = findViewById(R.id.cadastro_complemento);
        TextInputEditText inputEditBairro = findViewById(R.id.cadastro_bairro);
        TextInputEditText inputEditCep = findViewById(R.id.cadastro_cep);
        TextInputEditText inputEditNumero = findViewById(R.id.cadastro_numero);
        TextInputEditText inputEditEmail = findViewById(R.id.cadastro_email);
        TextInputEditText inputEditQueixas = findViewById(R.id.cadastro_queixas);
        TextInputEditText inputEditHistorico = findViewById(R.id.cadastro_historico);
        TextInputEditText inputEditMedicamentos = findViewById(R.id.cadastro_medicamentos);
        TextInputEditText inputEditSono = findViewById(R.id.cadastro_sono);
        TextInputEditText inputEditAlimentacao= findViewById(R.id.cadastro_alimentacao);
        TextInputEditText inputEditDigestao = findViewById(R.id.cadastro_digestao);
        TextInputEditText inputEditExcrecao = findViewById(R.id.cadastro_excrecao);
        TextInputEditText inputEditDoresFisicas = findViewById(R.id.cadastro_doresFisicas);
        TextInputEditText inputEditDoresEmocionais = findViewById(R.id.cadastro_doresEmocionais);
        TextInputEditText inputEditMenstruacao = findViewById(R.id.cadastro_menstruação);
        TextInputEditText inputEditParto = findViewById(R.id.cadastro_parto);
        TextInputEditText inputEditCirurgias = findViewById(R.id.cadastro_cirurgias);
        TextInputEditText inputEditMobilidade = findViewById(R.id.cadastro_mobilidade);
        TextInputEditText inputEditVicios = findViewById(R.id.cadastro_vicios);
        TextInputEditText inputEditHormonal = findViewById(R.id.cadastro_hormonal);

        TextInputEditText inputEditDataNascimento = findViewById(R.id.cadastro_dataNascimento);
        inputEditDataNascimento.setInputType(InputType.TYPE_NULL);
        inputEditDataNascimento.setClickable(true);
        inputEditDataNascimento.setFocusable(true);
        inputEditDataNascimento.setOnClickListener(view -> showDatePickerDialog(inputEditDataNascimento));

        TextInputEditText inputEditObservacao = findViewById(R.id.cadastro_observação);



        MaterialButton botaoSalvar = findViewById(R.id.form_botaoSalvarCliente);

        botaoSalvar.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(inputEditNome.getText())){
                textInputLayoutNome.setError("INSIRA O NOME");
            }
            else if(TextUtils.isEmpty(inputEditTelefone.getText())){
                textInputLayoutTelefone.setError("INSIRA O TELEFONE");
            }
            else if(TextUtils.isEmpty(inputEditLogradouro.getText())){
                textInputLayoutLogradouro.setError("INSIRA O LOGRADOURO");
            }
            else if(TextUtils.isEmpty(inputEditBairro.getText())){
                textInputLayoutBairro.setError("INSIRA O BAIRRO");
            }
            else if(TextUtils.isEmpty(inputEditNumero.getText())){
                textInputLayoutNumero.setError("INSIRA O NÚMERO");
            }
            else {
                String nome = String.valueOf(inputEditNome.getText());
                String telefone = String.valueOf(inputEditTelefone.getText());
                String ocupacao = String.valueOf(inputEditOcupacao.getText());
                String logradouro = String.valueOf(inputEditLogradouro.getText());
                String complemento = String.valueOf(inputEditComplemento.getText());
                String bairro = String.valueOf(inputEditBairro.getText());
                String cep = String.valueOf(inputEditCep.getText());
                String numero = String.valueOf(inputEditNumero.getText());
                String email = String.valueOf(inputEditEmail.getText());
                String queixas = String.valueOf(inputEditQueixas.getText());
                String historico = String.valueOf(inputEditHistorico.getText());
                String medicamentos = String.valueOf(inputEditMedicamentos.getText());
                String sono = String.valueOf(inputEditSono.getText());
                String alimentacao = String.valueOf(inputEditAlimentacao.getText());
                String digestao = String.valueOf(inputEditDigestao.getText());
                String excrecao = String.valueOf(inputEditExcrecao.getText());
                String doresFisicas = String.valueOf(inputEditDoresFisicas.getText());
                String doresEmocionais = String.valueOf(inputEditDoresEmocionais.getText());
                String menstruacao = String.valueOf(inputEditMenstruacao.getText());
                String parto = String.valueOf(inputEditParto.getText());
                String cirurgias = String.valueOf(inputEditCirurgias.getText());
                String mobilidade = String.valueOf(inputEditMobilidade.getText());
                String vicios = String.valueOf(inputEditVicios.getText());
                String hormonal = String.valueOf(inputEditHormonal.getText());
                String dataNascimento = String.valueOf(inputEditDataNascimento.getText());
                String observacao = String.valueOf(inputEditObservacao.getText());


                bundle.putString("nome", nome);
                bundle.putString("telefone", telefone);
                bundle.putString("ocupacao", ocupacao);
                bundle.putString("logradouro", logradouro);
                bundle.putString("complemento", complemento);
                bundle.putString("bairro", bairro);
                bundle.putString("cep", cep);
                bundle.putString("numero", numero);
                bundle.putString("email", email);
                bundle.putString("queixas", queixas);
                bundle.putString("historico", historico);
                bundle.putString("medicamentos", medicamentos);
                bundle.putString("sono", sono);
                bundle.putString("alimentacao", alimentacao);
                bundle.putString("digestao", digestao);
                bundle.putString("excrecao", excrecao);
                bundle.putString("doresFisicas", doresFisicas);
                bundle.putString("doresEmocionais", doresEmocionais);
                bundle.putString("menstruacao", menstruacao);
                bundle.putString("parto", parto);
                bundle.putString("cirurgias", cirurgias);
                bundle.putString("mobilidade", mobilidade);
                bundle.putString("vicios", vicios);
                bundle.putString("hormonal", hormonal);
                bundle.putString("dataNascimento", dataNascimento);
                bundle.putString("observacao", observacao);
                replyIntent.putExtras(bundle);
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });}

    private void showDatePickerDialog (TextInputEditText inputEditDataNascimento) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    LocalDate selectedDate = LocalDate.of(selectedYear, selectedMonth + 1, selectedDay);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault());
                    String selectedDateString = selectedDate.format(formatter);
                    inputEditDataNascimento.setText(selectedDateString);
                },
                year, month, day);

        datePickerDialog.show();
    }

}
