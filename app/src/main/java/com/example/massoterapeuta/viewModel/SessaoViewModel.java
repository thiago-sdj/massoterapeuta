package com.example.massoterapeuta.viewModel;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.massoterapeuta.bancoLocal.MassoterapeutaBanco;
import com.example.massoterapeuta.bancoLocal.entidade.SessaoEntidade;
import com.example.massoterapeuta.repository.SessaoRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;

public class SessaoViewModel extends AndroidViewModel {

    private SessaoRepository sessaoRepository;

    private final LiveData<List<SessaoEntidade>> todasSessoes;
    private LiveData<List<SessaoEntidade>> todasSessoesMarcadas;

    private LiveData<List<SessaoEntidade>> todasSessoesNaoMarcadas;

    private LiveData <SessaoEntidade> sessaoSelecionada;



    public SessaoViewModel (Application application){
        super(application);
        sessaoRepository = new SessaoRepository(application);
        todasSessoes = sessaoRepository.retornarTodasSessoes();
    }

    public LiveData<List<SessaoEntidade>> retornarTodasSessoes (){
        return todasSessoes;
    }

    public LiveData<List<SessaoEntidade>> retornarTodasSessoesMarcadas (){
        todasSessoesMarcadas = sessaoRepository.retornarTodasSessoesMarcadas();
        return todasSessoesMarcadas;
    }

    public LiveData<List<SessaoEntidade>> retornarTodasSessoesNaoMarcadas (){
        todasSessoesNaoMarcadas = sessaoRepository.retornarTodasSessoesNaoMarcadas();
        return todasSessoesNaoMarcadas;
    }

    public LiveData <SessaoEntidade> retornarSessaoSelecionada (Long idSessao){
        sessaoSelecionada = sessaoRepository.retornarSessaoSelecionada(idSessao);
        return sessaoSelecionada;
    }

    public LiveData <String> retornarLogradouroCliente (Long idContrato){
        LiveData <String> logradouroCliente = sessaoRepository.retornarLogradouroCliente(idContrato);
        return logradouroCliente;
    }

    public LiveData <Integer> retornarServicoTempoUsandoIdContrato (Long idContrato){
        LiveData <Integer>  tempo = sessaoRepository.retornarServicoTempoUsandoIdContrato(idContrato);
        return tempo;
    }

    public LiveData <String> retornarTelefoneClienteUsandoIdContrato (Long idContrato){
        LiveData <String>  telefone = sessaoRepository.retornarTelefoneClienteUsandoIdContrato(idContrato);
        return telefone;
    }
    public void inserir (SessaoEntidade sessaoEntidade){
        sessaoRepository.inserirSessao(sessaoEntidade);
    }

    public void atualizar (SessaoEntidade sessaoEntidade){
        sessaoRepository.atualizarSessao(sessaoEntidade);
    }

    public void apagar (SessaoEntidade sessaoEntidade){
        sessaoRepository.deletarSessao(sessaoEntidade);
    }

    public void apagarComIdContrato (Long idContrato){
        sessaoRepository.deletarSessaoComIdContrato(idContrato);
    }

    public void backupBanco(Context context){
        LocalDateTime dataHora = LocalDateTime.now();
        String dateTimeString = dataHora.toString();
        String numericOnly = dateTimeString.replaceAll("[^\\d]", "");
        try{
            InputStream inputStream = new FileInputStream(
                    new File(Environment.getDataDirectory() +
                            "/data/com.example.massoterapeuta/databases/MassoterapeutaDB"));
            OutputStream outputStream = new FileOutputStream(
                    new File(Environment.getExternalStorageDirectory()+ "/Download/Massoterapeuta" + numericOnly)
            );
            MassoterapeutaBanco.getInstance(context).close();
            byte [] buffer = new byte[1024];
            int comprimento;

            while ((comprimento = inputStream.read(buffer)) > 0){
                outputStream.write(buffer, 0, comprimento);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void restaurarBackupBanco(Context context){
        try{
            InputStream inputStream = new FileInputStream(
                    new File(Environment.getExternalStorageDirectory()+ "/Download/Massoterapeuta20240515192023443075")
            );
            OutputStream outputStream = new FileOutputStream(
                    new File(Environment.getDataDirectory() +
                            "/data/com.example.massoterapeuta/databases/MassoterapeutaDB"));
            byte [] buffer = new byte[1024];
            int comprimento;
            MassoterapeutaBanco.getInstance(context).close();
            while ((comprimento = inputStream.read(buffer)) > 0){
                outputStream.write(buffer, 0, comprimento);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }





}
