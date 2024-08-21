package com.example.massoterapeuta.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.massoterapeuta.bancoLocal.entidade.ClienteEntidade;
import com.example.massoterapeuta.repository.ClienteRepository;

import java.util.List;

public class ClienteViewModel extends AndroidViewModel {

    private ClienteRepository clienteRepository;

    private final LiveData<List<ClienteEntidade>> todosClientes;

    private LiveData <ClienteEntidade> clienteSelecionado;



    public ClienteViewModel (Application application){
        super(application);
        clienteRepository = new ClienteRepository(application);
        todosClientes = clienteRepository.retornarTodosClientes();
    }

    public LiveData<List<ClienteEntidade>> retornarTodosClientes (){
        return todosClientes;
    }

    public LiveData <ClienteEntidade> retornarClienteSelecionadoUsandoIdContrato (Long idContrato){
        clienteSelecionado = clienteRepository.retornarClienteSelecionadoUsandoIdContrato(idContrato);
        return clienteSelecionado;
    }

    public LiveData <ClienteEntidade> retornarClienteSelecionado (Long idCliente){
        clienteSelecionado = clienteRepository.retornarClienteSelecionado(idCliente);
        return clienteSelecionado;
    }


    public void inserir (ClienteEntidade clienteEntidade){
        clienteRepository.inserirCliente(clienteEntidade);
    }

    public void atualizar (ClienteEntidade clienteEntidade){
        clienteRepository.atualizarCliente(clienteEntidade);
    }

    public void apagar (ClienteEntidade clienteEntidade){
        clienteRepository.deletarCliente(clienteEntidade);
    }
}
