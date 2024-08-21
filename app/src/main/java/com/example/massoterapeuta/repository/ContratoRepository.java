package com.example.massoterapeuta.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.massoterapeuta.bancoLocal.MassoterapeutaBanco;
import com.example.massoterapeuta.bancoLocal.dao.ContratoDao;
import com.example.massoterapeuta.bancoLocal.entidade.ContratoEntidade;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class ContratoRepository {

    private ContratoDao contratoDao;

    private LiveData<List<ContratoEntidade>> contratos;

    private LiveData<List<ContratoEntidade>> contratosSelecionadosComIdCliente;

    private LiveData <ContratoEntidade> contrato;

    public ContratoRepository(Application application){
        MassoterapeutaBanco banco = MassoterapeutaBanco.getInstance(application);
        contratoDao = banco.getContratoDao();
        contratos = contratoDao.lerContratos();
    }

    public LiveData <List<ContratoEntidade>> retornarTodosContratos (){
        return contratos;
    }

    public LiveData <List<ContratoEntidade>> retornarContratosSelecionados (Long idCliente){
        contratosSelecionadosComIdCliente = contratoDao.lerContratosComIdCliente(idCliente);
        return contratos;
    }

    public LiveData <ContratoEntidade> retornarContratoSelecionado (Long idContrato){
        contrato = contratoDao.lerContrato(idContrato);
        return contrato;
    }

    public void deletarContrato(ContratoEntidade contratoEntidade){
        MassoterapeutaBanco.databaseWriteExecutor.execute(() -> {
            contratoDao.deletarContrato(contratoEntidade);
        });
    }

    public void deletarContratoComIdCliente(Long idCliente){
        MassoterapeutaBanco.databaseWriteExecutor.execute(() -> {
            contratoDao.deletarContratoComIdCliente(idCliente);
        });
    }

    public Future<Long> inserirContrato(ContratoEntidade contratoEntidade){
        return MassoterapeutaBanco.databaseWriteExecutor.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return contratoDao.inserirContrato(contratoEntidade);
            }
        });
    }

    public void atualizarContrato(ContratoEntidade contratoEntidade){
        MassoterapeutaBanco.databaseWriteExecutor.execute(() -> {
            contratoDao.atualizarContrato(contratoEntidade);
        });
    }
}
