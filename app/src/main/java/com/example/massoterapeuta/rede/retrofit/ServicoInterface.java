package com.example.massoterapeuta.rede.retrofit;


import com.example.massoterapeuta.rede.model.ServicoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServicoInterface {

    @GET("servicos")
    Call<List<ServicoModel>> findAll();

    @GET("servicos/{id}")
    Call<ServicoModel> find(@Path("id") Long idServico);

    @POST("servicos")
    Call<ServicoModel> insert(@Body ServicoModel servicoModel);

    @PUT("servicos/{id}")
    Call<ServicoModel> update(@Path("id") Long idServico, @Body ServicoModel servicoModel);

    @DELETE("servicos/{id}")
    Call<ServicoModel> delete(@Path("id") Long idServico);
}
