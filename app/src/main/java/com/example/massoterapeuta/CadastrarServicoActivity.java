package com.example.massoterapeuta;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class CadastrarServicoActivity extends AppCompatActivity {



    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private Float valorPacote, valorUnitario, peso;
    private Integer tempo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_servico);
        ImageView botaoVoltar = findViewById(R.id.ic_voltar);

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastrarServicoActivity.this, ExibirListaServicosActivity.class);
                startActivity(intent);
            }
        });

        initializeComponents();
    }

    private void initializeComponents() {
        TextInputLayout textInputLayoutDescricao = findViewById(R.id.layout_cadastro_descricao);
        TextInputLayout textInputLayoutValorUnitario = findViewById(R.id.layout_cadastro_valorUnitario);
        TextInputLayout textInputLayoutValorPacote = findViewById(R.id.layout_cadastro_valorPacote);
        TextInputLayout textInputLayoutPeso = findViewById(R.id.layout_cadastro_peso);
        TextInputLayout textInputLayoutTempo = findViewById(R.id.layout_cadastro_tempo);
        TextInputEditText inputEditDescricao = findViewById(R.id.cadastro_descricao);
        TextInputEditText inputEditValorUnitario = findViewById(R.id.cadastro_valorUnitario);
        TextInputEditText inputEditValorPacote = findViewById(R.id.cadastro_valorPacote);

        TextInputEditText inputEditTempo = findViewById(R.id.cadastro_tempo);
        inputEditTempo.setText("50");

        TextInputEditText inputEditPeso = findViewById(R.id.cadastro_peso);
        inputEditPeso.setText("1");

        MaterialButton botaoSalvar = findViewById(R.id.form_botaoSalvarServico);
        botaoSalvar.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            boolean parsingSuccessful = true;
            if (TextUtils.isEmpty(inputEditDescricao.getText())) {
                textInputLayoutDescricao.setError("INSIRA A DESCRIÇÃO");
            }else if(TextUtils.isEmpty(inputEditValorUnitario.getText())){
                textInputLayoutValorUnitario.setError("INSIRA O VALOR UNITÁRIO");
            }else if(TextUtils.isEmpty(inputEditPeso.getText())){
                textInputLayoutPeso.setError("INSIRA O PESO");
            }else if(TextUtils.isEmpty(inputEditTempo.getText())){
                textInputLayoutTempo.setError("INSIRA O TEMPO");
            }else {
                String descricao = String.valueOf(inputEditDescricao.getText());

                try {
                    String valorUnitarioString = String.valueOf(inputEditValorUnitario.getText());
                    valorUnitario = Float.valueOf(valorUnitarioString);
                } catch (NumberFormatException e) {
                    textInputLayoutValorUnitario.setError("O VALOR UNITÁRIO DEVE SER UM NÚMERO");
                    parsingSuccessful = false;
                }

                try {
                    if (TextUtils.isEmpty(inputEditValorPacote.getText())){
                        valorPacote = 0.0f;
                    }
                    else{
                        String valorPacoteString = String.valueOf(inputEditValorPacote.getText());
                        valorPacote = Float.valueOf(valorPacoteString);
                    }
                } catch (NumberFormatException e) {
                    textInputLayoutValorPacote.setError("O VALOR DO PACOTE DEVE SER UM NÚMERO");
                    parsingSuccessful = false;
                }

                try {
                    String pesoString = String.valueOf(inputEditPeso.getText());
                    peso = Float.valueOf(pesoString);
                } catch (NumberFormatException e) {
                    textInputLayoutPeso.setError("O PESO DEVE SER UM NÚMERO");
                    parsingSuccessful = false;
                }

                try {
                    String tempoString = String.valueOf(inputEditTempo.getText());
                    tempo = Integer.valueOf(tempoString);
                } catch (NumberFormatException e) {
                    textInputLayoutTempo.setError("INSIRA O TEMPO EM MINUTOS (VALOR NUMÉRICO)");
                    parsingSuccessful = false;
                }

                if (parsingSuccessful){
                    Bundle bundle = new Bundle();
                    bundle.putString("descricao", descricao);
                    bundle.putFloat("valorUnitario", valorUnitario);
                    bundle.putFloat("valorPacote", valorPacote);
                    bundle.putFloat("peso", peso);
                    bundle.putInt("tempo", tempo);
                    replyIntent.putExtras(bundle);
                    setResult(RESULT_OK, replyIntent);
                    finish();
                }

            }
        });

    }

}