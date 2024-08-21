package com.example.massoterapeuta.bancoLocal;

import android.content.Context;
import android.os.Environment;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.massoterapeuta.bancoLocal.dao.ClienteDao;
import com.example.massoterapeuta.bancoLocal.dao.ContratoDao;
import com.example.massoterapeuta.bancoLocal.dao.ServicoDao;
import com.example.massoterapeuta.bancoLocal.dao.SessaoDao;
import com.example.massoterapeuta.bancoLocal.entidade.ClienteEntidade;
import com.example.massoterapeuta.bancoLocal.entidade.ContratoEntidade;
import com.example.massoterapeuta.bancoLocal.entidade.ServicoEntidade;
import com.example.massoterapeuta.bancoLocal.entidade.SessaoEntidade;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(version = 5, entities = {ClienteEntidade.class, ServicoEntidade.class, ContratoEntidade.class, SessaoEntidade.class})
public abstract class MassoterapeutaBanco extends RoomDatabase {

    public abstract ClienteDao getClienteDao();
    public abstract ServicoDao getServicoDao();
    public abstract ContratoDao getContratoDao();
    public abstract SessaoDao getSessaoDao();

    private static final String DATABASE_NAME = "MassoterapeutaDB";

    private static volatile MassoterapeutaBanco INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static MassoterapeutaBanco getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (MassoterapeutaBanco.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            MassoterapeutaBanco.class,
                            DATABASE_NAME
                    ).build();
                }
            }
        }
        return INSTANCE;
    }

    public void backupBanco(){
        try{
            InputStream inputStream = new FileInputStream(
                    new File(Environment.getDataDirectory() +
                            "/data/com.example.massoterapeuta/databases/MassoterapeutaDB"));
            OutputStream outputStream = new FileOutputStream(
                    new File(Environment.getDataDirectory() + "/storage/self/primary/Download/MassoterapeutaDB")
            );

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

}
