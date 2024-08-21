package com.example.massoterapeuta.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.massoterapeuta.bancoLocal.entidade.ServicoEntidade;
import com.example.massoterapeuta.repository.ServicoRepository;

import java.util.List;

public class ServicoViewModel extends AndroidViewModel {

    private ServicoRepository servicoRepository;

    private final LiveData<List<ServicoEntidade>> todosServicos;

    private LiveData <ServicoEntidade> servicoSelecionado;



    public ServicoViewModel (Application application){
        super(application);
        servicoRepository = new ServicoRepository(application);
        todosServicos = servicoRepository.retornarTodosServicos();
    }

    public LiveData<List<ServicoEntidade>> retornarTodosServicos (){
        return todosServicos;
    }

    public LiveData <ServicoEntidade> retornarServicoSelecionado (Long idServico){
        servicoSelecionado = servicoRepository.retornarServicoSelecionado(idServico);
        return servicoSelecionado;
    }

    public LiveData <Float> retornarServicoSelecionadoPeso (Long idServico){
        LiveData <Float> peso = servicoRepository.retornarServicoSelecionadoPeso(idServico);
        return peso;
    }

    public LiveData <Integer> retornarServicoSelecionadoTempo(Long idServico){
        LiveData <Integer> tempo = servicoRepository.retornarServicoSelecionadoTempo(idServico);
        return tempo;
    }


    public void inserir (ServicoEntidade servicoEntidade){
        servicoRepository.inserirServico(servicoEntidade);
    }

    public void atualizar (ServicoEntidade servicoEntidade){
        servicoRepository.atualizarServico(servicoEntidade);
    }

    public void apagar (ServicoEntidade servicoEntidade){
        servicoRepository.deletarServico(servicoEntidade);
    }


}
