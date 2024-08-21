package com.example.massoterapeuta.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.massoterapeuta.bancoLocal.entidade.ContratoEntidade;
import com.example.massoterapeuta.repository.ContratoRepository;

import java.util.List;
import java.util.concurrent.Future;

public class ContratoViewModel extends AndroidViewModel {

    private ContratoRepository contratoRepository;

    private final LiveData<List<ContratoEntidade>> todosContratos;

    private LiveData<List<ContratoEntidade>> ContratosSelecionadosComIdCliente;

    private LiveData <ContratoEntidade> contratoSelecionado;

    public ContratoViewModel(Application application){
        super(application);
        contratoRepository = new ContratoRepository(application);
        todosContratos = contratoRepository.retornarTodosContratos();
    }

    public LiveData<List<ContratoEntidade>> retornarTodosContratos (){
        return todosContratos;
    }

    public LiveData<List<ContratoEntidade>> retornarTodosContratosComIdCliente (Long idCliente){
        ContratosSelecionadosComIdCliente = contratoRepository.retornarContratosSelecionados(idCliente);
        return todosContratos;
    }

    public LiveData <ContratoEntidade> retornarContratoSelecionado (Long idContrato){
        contratoSelecionado = contratoRepository.retornarContratoSelecionado(idContrato);
        return contratoSelecionado;
    }


    public Future<Long> inserir (ContratoEntidade contratoEntidade){
        return contratoRepository.inserirContrato(contratoEntidade);
    }

    public void atualizar (ContratoEntidade contratoEntidade){
        contratoRepository.atualizarContrato(contratoEntidade);
    }

    public void apagar (ContratoEntidade contratoEntidade){
        contratoRepository.deletarContrato(contratoEntidade);
    }
    public void apagarComIdCliente (Long idCliente){
        contratoRepository.deletarContratoComIdCliente(idCliente);

    }

}

