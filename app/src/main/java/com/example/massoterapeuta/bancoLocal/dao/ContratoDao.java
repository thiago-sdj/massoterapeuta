package com.example.massoterapeuta.bancoLocal.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.massoterapeuta.bancoLocal.entidade.ContratoEntidade;

import java.util.List;

@Dao
public interface ContratoDao {
    @Insert
    public Long inserirContrato(ContratoEntidade contratoEntidade);
    @Delete
    public void deletarContrato(ContratoEntidade contratoEntidade);

    @Query("DELETE FROM tb_contrato where con_idCliente = :idCliente")
    public void deletarContratoComIdCliente(Long idCliente);

    @Update
    public void atualizarContrato(ContratoEntidade contratoEntidade);

    @Query("SELECT * FROM tb_contrato WHERE idContrato = :id")
    public LiveData<ContratoEntidade> lerContrato(Long id);

    @Query("select * from tb_contrato")
    public LiveData <List<ContratoEntidade>> lerContratos ();

    @Query("select * from tb_contrato where con_idCliente= :idCliente")
    public LiveData <List<ContratoEntidade>> lerContratosComIdCliente (Long idCliente);
}
