package com.example.massoterapeuta.adapter;

import android.util.Log;
import android.view.ViewGroup;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.massoterapeuta.bancoLocal.entidade.SessaoEntidade;
import com.example.massoterapeuta.viewModel.ClienteViewModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SessaoSelecionadaAdapter extends ListAdapter<SessaoEntidade, SessaoSelecionadaHolder> {
    private ClienteViewModel clienteViewModel;

    private LifecycleOwner lifecycleOwner;

    public SessaoSelecionadaAdapter(@io.reactivex.annotations.NonNull DiffUtil.ItemCallback<SessaoEntidade> diffCalback, LifecycleOwner lifecycleOwner){
        super(diffCalback);
        this.lifecycleOwner = lifecycleOwner;
    }

    @Override
    public SessaoSelecionadaHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return SessaoSelecionadaHolder.create(parent);
    }

    @Override
    public void onBindViewHolder (SessaoSelecionadaHolder holder, int position){
        SessaoEntidade sessao = getItem(position);
        clienteViewModel = new ViewModelProvider((ViewModelStoreOwner) lifecycleOwner).get(ClienteViewModel.class);
        clienteViewModel.retornarClienteSelecionadoUsandoIdContrato(sessao.getIdContrato()).observe(lifecycleOwner, cliente -> {
            if (cliente != null){

                String dataNaoFormatada = sessao.getDataInicio();

                DateTimeFormatter formatterOriginal = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate localDate = LocalDate.parse(dataNaoFormatada, formatterOriginal);

                DateTimeFormatter formatterNovo = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String dataFormatada = localDate.format(formatterNovo);

                holder.nomeCliente.setText(cliente.getNome());
                if (cliente.getComplemento().isEmpty()){
                    holder.endereco.setText(cliente.getLogradouro() +", Bairro "+ cliente.getBairro() +", nº"+ cliente.getNumero().toString());
                }
                else{
                    holder.endereco.setText(cliente.getLogradouro() +", "+ cliente.getComplemento() +", Bairro "+ cliente.getBairro() +", nº"+ cliente.getNumero().toString());

                }
                holder.bind(sessao.getHoraInicio().toString(), dataFormatada, sessao.getHoraFim().toString(),
                        sessao.getStatus().toString(), sessao.getPesoSessao().toString());
            }
            else{
                Log.w("ERRO", "Não foi possível carregar");
            }

        });

    }

    public static class sessaoDiff extends DiffUtil.ItemCallback<SessaoEntidade>{
        @Override
        public boolean areItemsTheSame(@io.reactivex.annotations.NonNull SessaoEntidade itemAntigo, @io.reactivex.annotations.NonNull SessaoEntidade itemNovo){
            return itemAntigo == itemNovo;
        }

        @Override
        public boolean areContentsTheSame (@io.reactivex.annotations.NonNull SessaoEntidade itemAntigo, @io.reactivex.annotations.NonNull SessaoEntidade itemNovo){
            return itemAntigo.getIdSessao().equals(itemNovo.getIdSessao());
        }
    }

}
