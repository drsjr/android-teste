package drsjr.com.br.androidteste.presenter;

import android.content.Context;
import android.content.Intent;

import java.util.List;

import drsjr.com.br.androidteste.data.entity.Ingrediente;
import drsjr.com.br.androidteste.data.network.IngredienteCallBack;
import drsjr.com.br.androidteste.data.network.PedidoCallBack;
import drsjr.com.br.androidteste.presenter.contract.ItemPedidoContract;
import drsjr.com.br.androidteste.presenter.contract.PedidoContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by junior on 5/8/18.
 */

public class ItemPedidoPresenter implements ItemPedidoContract.Action {

    private Context _context;
    private ItemPedidoContract.ViewAction _view;
    private IngredienteCallBack _ingrediente;

    public ItemPedidoPresenter(Context context) {
        _context = context;
        _ingrediente = new IngredienteCallBack();
    }


    @Override
    public Context getContext() {
        return _context;
    }

    @Override
    public void inProgress(boolean progress) {
        _view.inProgress(progress);
    }

    @Override
    public void setView(ItemPedidoContract.ViewAction view) {
        _view = view;
    }

    @Override
    public void addIngredients() {
    }

    @Override
    public void populateActivity() {
        _ingrediente.getService().getAllIngredientes().enqueue(new Callback<List<Ingrediente>>() {
            @Override
            public void onResponse(Call<List<Ingrediente>> call, Response<List<Ingrediente>> response) {
                List<Ingrediente> lista = response.body();
                _view.populateAdapter(lista);
                _view.populateRecycler();
                inProgress(false);
            }

            @Override
            public void onFailure(Call<List<Ingrediente>> call, Throwable t) {
                inProgress(false);
            }
        });
    }
}
