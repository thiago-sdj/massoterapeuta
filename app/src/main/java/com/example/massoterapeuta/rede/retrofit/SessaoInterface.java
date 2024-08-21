package com.example.massoterapeuta.rede.retrofit;

import com.example.massoterapeuta.rede.model.SessaoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SessaoInterface {
    @GET("sessoes")
    Call<List<SessaoModel>> findAll();

    @GET("sessoes/{id}")
    Call<SessaoModel> find(@Path("id") Long idSessao);

    @GET("sessoes/contrato/{id}")
    Call<List<SessaoModel>> findByIdContrato(@Path("id") Long idContrato);

    @POST("sessoes")
    Call<SessaoModel> insert(@Body SessaoModel sessaoModel);

    @PUT("sessoes/{id}")
    Call<SessaoModel> update(@Body SessaoModel sessaoModel, @Path("id") Long idSessao);

    @DELETE("sessoes/{id}")
    Call<SessaoModel> delete( @Path("id") Long idSessao);
}