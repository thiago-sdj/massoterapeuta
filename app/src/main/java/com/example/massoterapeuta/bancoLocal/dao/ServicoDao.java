package com.example.massoterapeuta.bancoLocal.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.massoterapeuta.bancoLocal.entidade.ServicoEntidade;

import java.util.List;

@Dao
public interface ServicoDao {
    @Insert
    public void inserirServico(ServicoEntidade servicoEntidade);
    @Delete
    public void deletarServico(ServicoEntidade servicoEntidade);

    @Update
    public void atualizarServico(ServicoEntidade servicoEntidade);

    @Query("SELECT * FROM tb_servico WHERE idServico = :id")
    public LiveData<ServicoEntidade> lerServico(Long id);

    @Query("SELECT ser_peso FROM tb_servico WHERE idServico = :id")
    public LiveData<Float> lerServicoPeso(Long id);

    @Query("SELECT ser_tempo FROM tb_servico WHERE idServico = :id")
    public LiveData<Integer> lerServicoTempo(Long id);

    @Query("select * from tb_servico")
    public LiveData<List<ServicoEntidade>> lerServicos ();
}
