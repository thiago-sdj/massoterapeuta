package com.example.massoterapeuta.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.massoterapeuta.SessaoSelecionadaAcitivity;
import com.example.massoterapeuta.bancoLocal.entidade.SessaoEntidade;
import com.example.massoterapeuta.viewModel.SessaoViewModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import io.reactivex.annotations.NonNull;

public class SessaoAdapter extends ListAdapter<SessaoEntidade, SessaoHolder> {

    LifecycleOwner lifecycleOwner;

    public SessaoAdapter(@io.reactivex.annotations.NonNull DiffUtil.ItemCallback<SessaoEntidade> diffCalback, LifecycleOwner lifecycleOwner){
        super(diffCalback);
        this.lifecycleOwner = lifecycleOwner;
    }

    @Override
    public SessaoHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return SessaoHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(SessaoHolder holder, int position) {
        SessaoEntidade sessao = getItem(position);
        SessaoViewModel sessaoViewModel = new ViewModelProvider((ViewModelStoreOwner) lifecycleOwner).get(SessaoViewModel.class);
        sessaoViewModel.retornarLogradouroCliente(sessao.getIdContrato()).observe(lifecycleOwner, logradouro -> {
            if (logradouro != null) {
                String dataNaoFormatada = sessao.getDataInicio();
                String horaInicio = sessao.getHoraInicio();
                String horaFim = sessao.getHoraFim();

                DateTimeFormatter formatterOriginal = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
                LocalDateTime inicioDateTime = LocalDateTime.parse(dataNaoFormatada + " " + horaInicio, formatterOriginal);
                LocalDateTime fimDateTime = LocalDateTime.parse(dataNaoFormatada + " " + horaFim, formatterOriginal);

                // Verifica se há outra sessão com conflito de horário
                boolean conflitoDeHorario = false;
                for (SessaoEntidade outraSessao : getCurrentList()) {
                    if (!sessao.equals(outraSessao)) {
                        String outraDataNaoFormatada = outraSessao.getDataInicio();
                        String outraHoraInicio = outraSessao.getHoraInicio();
                        String outraHoraFim = outraSessao.getHoraFim();
                        LocalDateTime outraInicioDateTime = LocalDateTime.parse(outraDataNaoFormatada + " " + outraHoraInicio, formatterOriginal);
                        LocalDateTime outraFimDateTime = LocalDateTime.parse(outraDataNaoFormatada + " " + outraHoraFim, formatterOriginal);
                        if (inicioDateTime.isBefore(outraFimDateTime) && fimDateTime.isAfter(outraInicioDateTime)) {
                            conflitoDeHorario = true;
                            break;
                        }
                    }
                }

                DateTimeFormatter formatterNovo = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String dataFormatada = inicioDateTime.format(formatterNovo);

                // Altera a cor da sessão se houver conflito de horário
                if (conflitoDeHorario) {
                    String mensagemConflito = "Duas ou mais sessões tem horários coincidentes";
                    Toast.makeText((Context) lifecycleOwner, mensagemConflito, Toast.LENGTH_LONG).show();
                    holder.bind(horaInicio, dataFormatada, sessao.getStatus(), logradouro);
                    holder.horaInicio.setBackgroundColor(Color.parseColor("#F54636"));
                } else {
                    holder.bind(horaInicio, dataFormatada, sessao.getStatus(), logradouro);
                    holder.horaInicio.setBackgroundColor(Color.parseColor("#94B9AF"));
                }
            } else {
                Log.w("ERRO", "Não foi possível carregar");
            }

        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Handle the click event, for example, navigate to another activity
                Intent intent = new Intent(v.getContext(), SessaoSelecionadaAcitivity.class);
                intent.putExtra("idSessao", sessao.getIdSessao()); // Pass data to the new activity if needed
                v.getContext().startActivity(intent);
                Log.w("teste", sessao.getIdSessao().toString());
            }
        });
    }



    /*@Override
    public void onBindViewHolder (SessaoHolder holder, int position){
        SessaoEntidade sessao =  getItem(position);
        SessaoViewModel sessaoViewModel = new ViewModelProvider((ViewModelStoreOwner) lifecycleOwner).get(SessaoViewModel.class);
        sessaoViewModel.retornarLogradouroCliente(sessao.getIdContrato()).observe(lifecycleOwner, logradouro -> {
            if (logradouro != null){
                String dataNaoFormatada = sessao.getDataInicio();

                DateTimeFormatter formatterOriginal = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate localDate = LocalDate.parse(dataNaoFormatada, formatterOriginal);

                DateTimeFormatter formatterNovo = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String dataFormatada = localDate.format(formatterNovo);

                holder.bind(sessao.getHoraInicio(), dataFormatada, sessao.getStatus(), logradouro);
            }
            else{
                Log.w("ERRO", "Não foi possível carregar");
            }

        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Handle the click event, for example, navigate to another activity
                Intent intent = new Intent(v.getContext(), SessaoSelecionadaAcitivity.class);
                intent.putExtra("idSessao", sessao.getIdSessao()); // Pass data to the new activity if needed
                v.getContext().startActivity(intent);
                Log.w("teste", sessao.getIdSessao().toString());
            }
        });
    }*/

    public static class sessaoDiff extends DiffUtil.ItemCallback<SessaoEntidade>{
        @Override
        public boolean areItemsTheSame(@io.reactivex.annotations.NonNull SessaoEntidade itemAntigo, @io.reactivex.annotations.NonNull SessaoEntidade itemNovo){
            return itemAntigo == itemNovo;
        }

        @Override
        public boolean areContentsTheSame (@io.reactivex.annotations.NonNull SessaoEntidade itemAntigo, @NonNull SessaoEntidade itemNovo){
            return itemAntigo.getIdSessao().equals(itemNovo.getIdSessao());
        }
    }

}


