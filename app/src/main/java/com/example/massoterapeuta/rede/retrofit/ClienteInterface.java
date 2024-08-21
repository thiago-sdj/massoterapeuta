package com.example.massoterapeuta.rede.retrofit;

import com.example.massoterapeuta.rede.model.ClienteModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ClienteInterface {

  @GET("clientes")
  Call<List<ClienteModel>> findAll();

  @GET("clientes/{id}")
  Call<ClienteModel> find(@Path("id") Long idCliente);

  @POST("clientes")
  Call<ClienteModel> insert(@Body ClienteModel clienteModel);

  @PUT("clientes/{id}")
  Call<ClienteModel> update(@Body ClienteModel clienteModel, @Path("id") Long idCliente);

  @DELETE("clientes/{id}")
  Call<ClienteModel> delete( @Path("id") Long idCliente);
}
