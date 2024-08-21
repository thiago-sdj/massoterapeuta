package com.example.massoterapeuta.bancoLocal.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.massoterapeuta.bancoLocal.entidade.SessaoEntidade;

import java.util.List;

@Dao
public interface SessaoDao {

    @Insert
    public void inserirSessao(SessaoEntidade sessaoEntidade);
    @Delete
    public void deletarSessao(SessaoEntidade sessaoEntidade);

    @Query("DELETE FROM tb_sessao where cont_idContrato= :idContrato")
    public void deletarSessaoComIdContrato(Long idContrato);

    @Update
    public void atualizarSessao(SessaoEntidade sessaoEntidade);

    @Query("SELECT * FROM tb_sessao WHERE idSessao = :id")
    public LiveData<SessaoEntidade> lerSessao (Long id);

    @Query ("SELECT tb_cliente.cli_logradouro FROM tb_contrato INNER JOIN tb_cliente ON tb_contrato.con_idCliente = tb_cliente.idCliente WHERE tb_contrato.idContrato = :idContrato")
    public LiveData<String> lerLogradouroCliente (Long idContrato);


    @Query("SELECT tb_servico.ser_tempo from tb_contrato INNER JOIN tb_servico ON tb_contrato.con_idServico = tb_servico.idServico WHERE tb_contrato.idContrato = :idContrato")
    public LiveData<Integer> lerServicoTempoUsandoIdContrato (Long idContrato);

    @Query("SELECT tb_cliente.cli_telefone from tb_contrato INNER JOIN tb_cliente ON tb_contrato.con_idCliente = tb_cliente.idCliente WHERE tb_contrato.idContrato = :idContrato")
    public LiveData<String> lerTelefoneClienteUsandoIdContrato (Long idContrato);

    @Query("select * from tb_sessao order by ses_datainicio, ses_horainicio")
    public LiveData<List<SessaoEntidade>> lerSessoes ();

    @Query("SELECT * FROM tb_sessao WHERE ses_status = 'MARCADA' ORDER BY ses_datainicio, ses_horainicio")
    public LiveData<List<SessaoEntidade>> lerSessoesMarcadas();

    @Query("SELECT * FROM tb_sessao WHERE ses_status != 'MARCADA' ORDER BY ses_datainicio, ses_horainicio")
    public LiveData<List<SessaoEntidade>> lerSessoesNaoMarcadas();




}
