package com.example.massoterapeuta.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.massoterapeuta.bancoLocal.MassoterapeutaBanco;
import com.example.massoterapeuta.bancoLocal.dao.SessaoDao;
import com.example.massoterapeuta.bancoLocal.entidade.SessaoEntidade;

import java.util.List;

public class SessaoRepository {

    private SessaoDao sessaoDao;

    private LiveData<List<SessaoEntidade>> sessoes;

    private LiveData<List<SessaoEntidade>> sessoesMarcadas;
    private LiveData<List<SessaoEntidade>> sessoesNaoMarcadas;


    private LiveData <SessaoEntidade> sessao;

    public SessaoRepository(Application application){
        MassoterapeutaBanco banco = MassoterapeutaBanco.getInstance(application);
        sessaoDao = banco.getSessaoDao();
        sessoes = sessaoDao.lerSessoes();
    }

    public LiveData <List<SessaoEntidade>> retornarTodasSessoes (){
        return sessoes;
    }

    public LiveData <List<SessaoEntidade>> retornarTodasSessoesMarcadas (){
        sessoesMarcadas = sessaoDao.lerSessoesMarcadas();
        return  sessoesMarcadas;
    }
    public LiveData <List<SessaoEntidade>> retornarTodasSessoesNaoMarcadas (){
        sessoesNaoMarcadas = sessaoDao.lerSessoesNaoMarcadas();
        return sessoesNaoMarcadas;
    }

    public LiveData <SessaoEntidade> retornarSessaoSelecionada (Long idSessao){
        sessao = sessaoDao.lerSessao(idSessao);
        return sessao;
    }

    public LiveData <Integer> retornarServicoTempoUsandoIdContrato (Long idContrato){
        LiveData <Integer> tempo = sessaoDao.lerServicoTempoUsandoIdContrato(idContrato);
        return tempo;
    }

    public LiveData <String> retornarTelefoneClienteUsandoIdContrato (Long idContrato){
        LiveData <String> telefone = sessaoDao.lerTelefoneClienteUsandoIdContrato(idContrato);
        return telefone;
    }

    public LiveData <String> retornarLogradouroCliente (Long idContrato){
        LiveData <String> logradouro = sessaoDao.lerLogradouroCliente(idContrato);
        return logradouro;
    }

    public void deletarSessao(SessaoEntidade sessaoEntidade){
        MassoterapeutaBanco.databaseWriteExecutor.execute(() -> {
            sessaoDao.deletarSessao(sessaoEntidade);
        });
    }

    public void deletarSessaoComIdContrato(Long idContrato){
        MassoterapeutaBanco.databaseWriteExecutor.execute(() -> {
            sessaoDao.deletarSessaoComIdContrato(idContrato);
        });
    }

    public void inserirSessao(SessaoEntidade sessaoEntidade){
        MassoterapeutaBanco.databaseWriteExecutor.execute(() -> {
            sessaoDao.inserirSessao(sessaoEntidade);
        });
    }

    public void atualizarSessao(SessaoEntidade sessaoEntidade){
        MassoterapeutaBanco.databaseWriteExecutor.execute(() -> {
            sessaoDao.atualizarSessao(sessaoEntidade);
        });
    }


}
