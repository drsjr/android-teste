package drsjr.com.br.androidteste.data.network;

import java.util.List;

import drsjr.com.br.androidteste.data.entity.Ingrediente;
import drsjr.com.br.androidteste.data.entity.Lanche;
import drsjr.com.br.androidteste.data.entity.Promocao;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by junior on 5/7/18.
 */

public interface RestAPI {

    interface LancheCall {

        @GET("lanche")
        Call<List<Lanche>> getAllLanches();

        @GET("lanche/{id} ")
        Call<Lanche> getLancheById(@Path("id") Long id);

    }

    interface IngredienteCall {

        @GET("ingrediente")
        Call<List<Ingrediente>> getAllIngredientes();

        @GET("ingrediente/de/{id}")
        Call<List<Ingrediente>> getIngredienteLancheById(@Path("id") Long id);
    }

    interface PedidoCall {

        @PUT("pedido/{id}")
        Call<Lanche> addItemPedido(@Path("id") Long id);

        @PUT("pedido/{id}")
        Call<Lanche> addCustomPedido(@Path("id") Long id, @Body List<Long> extras);

    }

    interface PromocaoCall {

        @GET("promocao")
        Call<List<Promocao>> getAllPromocao();

    }
}
