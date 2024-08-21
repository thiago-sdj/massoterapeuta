package com.example.massoterapeuta.bancoLocal.dao;

import android.os.Environment;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.massoterapeuta.bancoLocal.entidade.ClienteEntidade;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Dao
public interface ClienteDao {
    @Insert
    public void inserirCliente(ClienteEntidade clienteEntidade);
    @Delete
    public void deletarCliente(ClienteEntidade clienteEntidade);

    @Update
    public void atualizarCliente(ClienteEntidade clienteEntidade);
    @Query("SELECT TB_CLIENTE.* FROM TB_CONTRATO INNER JOIN TB_CLIENTE ON TB_CONTRATO.con_idCliente = TB_CLIENTE.idCliente WHERE TB_CONTRATO.idContrato = :id")
    LiveData<ClienteEntidade> lerClienteUsandoIdContrato(Long id);

    @Query("SELECT * FROM TB_CLIENTE WHERE idCliente = :id")
    LiveData<ClienteEntidade> lerCliente(Long id);

    @Query("select * from tb_cliente")
    public LiveData<List<ClienteEntidade>> lerClientes ();

}
