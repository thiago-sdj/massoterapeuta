package com.example.massoterapeuta;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.massoterapeuta.bancoLocal.entidade.ServicoEntidade;
import com.example.massoterapeuta.viewModel.ServicoViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class EditarServicoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ServicoViewModel servicoViewModel;

    private ServicoEntidade servicoEntidade;

    private Float valorPacote, valorUnitario, peso;
    private Integer tempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_servico);

        Long idServico = getIntent().getLongExtra("idServico", 1);

        servicoViewModel = new ViewModelProvider(this).get(ServicoViewModel.class);
        servicoViewModel.retornarServicoSelecionado(idServico).observe(this, servico -> {
            if (servico != null) {
                servicoEntidade = servico;
                initializeComponents(servicoEntidade);
            } else {
                Toast.makeText(this, "Não foi possível carregar o serviço", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initializeComponents(ServicoEntidade servicoEntidade) {
        TextInputLayout textInputLayoutDescricao = findViewById(R.id.layout_editar_descricao);
        TextInputLayout textInputLayoutValorUnitario = findViewById(R.id.layout_editar_valorUnitario);
        TextInputLayout textInputLayoutValorPacote = findViewById(R.id.layout_editar_valorPacote);
        TextInputLayout textInputLayoutPeso = findViewById(R.id.layout_editar_peso);
        TextInputLayout textInputLayoutTempo = findViewById(R.id.layout_editar_tempo);

        TextInputEditText inputEditDescricao = findViewById(R.id.cadastro_descricao);

        inputEditDescricao.setText(String.valueOf(servicoEntidade.getDescricao()));

        TextInputEditText inputEditValorUnitario = findViewById(R.id.cadastro_valorUnitario);
        inputEditValorUnitario.setText(String.valueOf(servicoEntidade.getValorUnitario()));

        TextInputEditText inputEditValorPacote = findViewById(R.id.cadastro_valorPacote);
        inputEditValorPacote.setText(String.valueOf(servicoEntidade.getValorPacote()));

        TextInputEditText inputEditPeso = findViewById(R.id.cadastro_peso);
        inputEditPeso.setText(String.valueOf(servicoEntidade.getPeso()));

        TextInputEditText inputEditTempo = findViewById(R.id.cadastro_tempo);
        inputEditTempo.setText(String.valueOf(servicoEntidade.getTempo()));


        MaterialButton botaoSalvar = findViewById(R.id.form_botaoSalvarServicoEditar);
        botaoSalvar.setOnClickListener(view -> {

            TextInputEditText inputEditDescricaoAtualizada = findViewById(R.id.cadastro_descricao);

            TextInputEditText inputEditValorUnitarioAtualizado = findViewById(R.id.cadastro_valorUnitario);

            TextInputEditText inputEditValorPacoteAtualizado = findViewById(R.id.cadastro_valorPacote);

            TextInputEditText inputEditPesoAtualizado = findViewById(R.id.cadastro_peso);

            TextInputEditText inputEditTempoAtualizado = findViewById(R.id.cadastro_tempo);


            boolean parsingSuccessful = true;
            if (TextUtils.isEmpty(inputEditDescricaoAtualizada.getText())) {
                textInputLayoutDescricao.setError("INSIRA A DESCRIÇÃO");
            }else if(TextUtils.isEmpty(inputEditValorUnitarioAtualizado.getText())){
                textInputLayoutValorUnitario.setError("INSIRA O VALOR UNITÁRIO");
            }else if(TextUtils.isEmpty(inputEditPesoAtualizado.getText())){
                textInputLayoutPeso.setError("INSIRA O PESO");
            }else if(TextUtils.isEmpty(inputEditTempoAtualizado.getText())){
                textInputLayoutTempo.setError("INSIRA O TEMPO");
            }else {
                String descricao = String.valueOf(inputEditDescricaoAtualizada.getText());

                try {
                    String valorUnitarioString = String.valueOf(inputEditValorUnitarioAtualizado.getText());
                    valorUnitario = Float.valueOf(valorUnitarioString);
                } catch (NumberFormatException e) {
                    textInputLayoutValorUnitario.setError("O VALOR UNITÁRIO DEVE SER UM NÚMERO");
                    parsingSuccessful = false;
                }

                try {
                    if (TextUtils.isEmpty(inputEditValorPacoteAtualizado.getText())){
                        valorPacote = 0.0f;
                    }
                    else{
                        String valorPacoteString = String.valueOf(inputEditValorPacoteAtualizado.getText());
                        valorPacote = Float.valueOf(valorPacoteString);
                    }
                } catch (NumberFormatException e) {
                    textInputLayoutValorPacote.setError("O VALOR DO PACOTE DEVE SER UM NÚMERO");
                    parsingSuccessful = false;
                }

                try {
                    String pesoString = String.valueOf(inputEditPesoAtualizado.getText());
                    peso = Float.valueOf(pesoString);
                } catch (NumberFormatException e) {
                    textInputLayoutPeso.setError("O PESO DEVE SER UM NÚMERO");
                    parsingSuccessful = false; // Parsing failed
                }

                try {
                    String tempoString = String.valueOf(inputEditTempoAtualizado.getText());
                    tempo = Integer.valueOf(tempoString);
                } catch (NumberFormatException e) {
                    textInputLayoutTempo.setError("INSIRA O TEMPO EM MINUTOS (VALOR NUMÉRICO)");
                    parsingSuccessful = false; // Parsing failed
                }


                if (parsingSuccessful){
                    servicoEntidade.setDescricao(descricao);
                    servicoEntidade.setValorUnitario(valorUnitario);
                    servicoEntidade.setValorPacote(valorPacote);
                    servicoEntidade.setPeso(peso);
                    servicoEntidade.setTempo(tempo);
                    servicoViewModel.atualizar(servicoEntidade);
                    Intent intent = new Intent(EditarServicoActivity.this, ServicoSelecionadoActivity.class);
                    intent.putExtra("idServico", servicoEntidade.getIdServico());
                    startActivity(intent);
                }

            }


        });
    }

}

