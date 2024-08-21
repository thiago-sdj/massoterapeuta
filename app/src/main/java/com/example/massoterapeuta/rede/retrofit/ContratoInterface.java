package com.example.massoterapeuta.rede.retrofit;

import com.example.massoterapeuta.rede.model.ContratoModel;
import com.example.massoterapeuta.rede.model.ContratoModelConsulta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ContratoInterface {

    @GET("contratos")
    Call<List<ContratoModelConsulta>> findAll();

    @GET("contratos/{id}")
    Call<ContratoModelConsulta> find(@Path("id") Long idContrato);

    @POST("contratos")
    Call<ContratoModel> insert(@Body ContratoModel contratoModel);

    @DELETE("contratos/{id}")
    Call<ContratoModel> delete( @Path("id") Long idContrato);
}
