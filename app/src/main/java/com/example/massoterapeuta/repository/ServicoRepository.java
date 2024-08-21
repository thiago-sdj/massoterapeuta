package com.example.massoterapeuta.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.massoterapeuta.bancoLocal.MassoterapeutaBanco;
import com.example.massoterapeuta.bancoLocal.dao.ServicoDao;

import com.example.massoterapeuta.bancoLocal.entidade.ServicoEntidade;

import java.util.List;

public class ServicoRepository {
    private ServicoDao servicoDao;

    private LiveData<List<ServicoEntidade>> servicos;

    private LiveData <ServicoEntidade> servico;

    public ServicoRepository(Application application){
        MassoterapeutaBanco banco = MassoterapeutaBanco.getInstance(application);
        servicoDao = banco.getServicoDao();
        servicos = servicoDao.lerServicos();
    }

    public LiveData <List<ServicoEntidade>> retornarTodosServicos (){
        return servicos;
    }

    public LiveData <ServicoEntidade> retornarServicoSelecionado (Long idServico){
        servico = servicoDao.lerServico(idServico);
        return servico;
    }

    public LiveData <Float> retornarServicoSelecionadoPeso (Long idServico){
        LiveData <Float> peso = servicoDao.lerServicoPeso(idServico);
        return peso;
    }

    public LiveData <Integer> retornarServicoSelecionadoTempo (Long idServico){
        LiveData <Integer> tempo = servicoDao.lerServicoTempo(idServico);
        return tempo;
    }


    public void deletarServico(ServicoEntidade servicoEntidade){
        MassoterapeutaBanco.databaseWriteExecutor.execute(() -> {
            servicoDao.deletarServico(servicoEntidade);
        });
    }

    public void inserirServico(ServicoEntidade servicoEntidade){
        MassoterapeutaBanco.databaseWriteExecutor.execute(() -> {
            servicoDao.inserirServico(servicoEntidade);
        });
    }



    public void atualizarServico(ServicoEntidade servicoEntidade){
        MassoterapeutaBanco.databaseWriteExecutor.execute(() -> {
            servicoDao.atualizarServico(servicoEntidade);
        });
    }



}
