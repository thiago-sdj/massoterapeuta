package com.example.massoterapeuta.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.massoterapeuta.bancoLocal.MassoterapeutaBanco;
import com.example.massoterapeuta.bancoLocal.dao.ClienteDao;
import com.example.massoterapeuta.bancoLocal.entidade.ClienteEntidade;

import java.util.List;

public class ClienteRepository {
    private ClienteDao clienteDao;

    private LiveData<List<ClienteEntidade>> clientes;

    private LiveData <ClienteEntidade> cliente;

    public ClienteRepository(Application application){
        MassoterapeutaBanco banco = MassoterapeutaBanco.getInstance(application);
        clienteDao = banco.getClienteDao();
        clientes = clienteDao.lerClientes();
    }

    public LiveData <List<ClienteEntidade>> retornarTodosClientes (){
        return clientes;
    }

    public LiveData <ClienteEntidade> retornarClienteSelecionadoUsandoIdContrato (Long idContrato){
        cliente = clienteDao.lerClienteUsandoIdContrato(idContrato);
        return cliente;
    }

    public LiveData <ClienteEntidade> retornarClienteSelecionado (Long idCliente){
        cliente = clienteDao.lerCliente(idCliente);
        return cliente;
    }

    public void deletarCliente(ClienteEntidade clienteEntidade){
        MassoterapeutaBanco.databaseWriteExecutor.execute(() -> {
            clienteDao.deletarCliente(clienteEntidade);
        });
    }

    public void inserirCliente(ClienteEntidade clienteEntidade){
        MassoterapeutaBanco.databaseWriteExecutor.execute(() -> {
            clienteDao.inserirCliente(clienteEntidade);
        });
    }

    public void atualizarCliente(ClienteEntidade clienteEntidade){
        MassoterapeutaBanco.databaseWriteExecutor.execute(() -> {
            clienteDao.atualizarCliente(clienteEntidade);
        });
    }
}
