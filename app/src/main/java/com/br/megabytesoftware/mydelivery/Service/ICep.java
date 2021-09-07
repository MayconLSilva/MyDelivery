package com.br.megabytesoftware.mydelivery.Service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ICep {

    //consultar CEP no webservice do ViaCEP
    @GET("{cep}/json/")
    Call<ModelEnderecoService> consultarCEP(@Path("cep") String cep);

}
