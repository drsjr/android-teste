package drsjr.com.br.androidteste.presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import drsjr.com.br.androidteste.Pedido;
import drsjr.com.br.androidteste.data.entity.Ingrediente;
import drsjr.com.br.androidteste.data.entity.Lanche;
import drsjr.com.br.androidteste.data.network.IngredienteCallBack;
import drsjr.com.br.androidteste.data.network.PedidoCallBack;
import drsjr.com.br.androidteste.presenter.contract.AdapterContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by junior on 5/7/18.
 */

public class AdapterPresenter implements AdapterContract.LancheAction {

    private PedidoCallBack _pedido;
    private IngredienteCallBack _ingrediente;

    private Context _context;

    public AdapterPresenter(Context context) {
        _pedido = new PedidoCallBack();
        _ingrediente = new IngredienteCallBack();
        this._context = context;
    }

    @Override
    public void addPedido(Long id) {
        sendPedido(id);
    }

    @Override
    public void customPedido(Lanche lanche) {
        Intent intent = new Intent(_context, Pedido.class);
        intent.putExtra("id", lanche.getId());
        _context.startActivity(intent);
    }


    private void sendPedido(Long id) {
        _pedido.getService().addItemPedido(id).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Toast.makeText(_context, "Ae, Adicionado!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(_context, "Então, não foi!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setIngredientes() {
        _ingrediente.getService().getAllIngredientes().enqueue(new Callback<List<Ingrediente>>() {
            @Override
            public void onResponse(Call<List<Ingrediente>> call, Response<List<Ingrediente>> response) {
                List<Ingrediente> lista = response.body();
            }

            @Override
            public void onFailure(Call<List<Ingrediente>> call, Throwable t) {

            }
        });
    }
}
