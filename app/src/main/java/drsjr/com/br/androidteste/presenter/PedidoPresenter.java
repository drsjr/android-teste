package drsjr.com.br.androidteste.presenter;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import drsjr.com.br.androidteste.data.entity.Ingrediente;
import drsjr.com.br.androidteste.data.entity.Lanche;
import drsjr.com.br.androidteste.data.network.IngredienteCallBack;
import drsjr.com.br.androidteste.data.network.PedidoCallBack;
import drsjr.com.br.androidteste.presenter.contract.PedidoContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by junior on 5/7/18.
 */

public class PedidoPresenter implements PedidoContract.Action {

    private Context _context;
    private PedidoContract.ViewAction _view;
    private IngredienteCallBack _ingrediente;
    private PedidoCallBack _pedido;
    private boolean progressA, progressB;

    public PedidoPresenter(Context context) {
        _context = context;
        _ingrediente = new IngredienteCallBack();
        _pedido = new PedidoCallBack();
    }

    @Override
    public Context getContext() {
        return _context;
    }

    @Override
    public void setView(PedidoContract.ViewAction view) {
        _view = view;
    }

    @Override
    public void inProgress(boolean progress) {
        _view.inProgress(progress);
    }

    @Override
    public void sendCustomPedido(Long id, List<Ingrediente> ingredients) {
        inProgress(true);
        _pedido.getService().addCustomPedido(id, getExtra(ingredients)).enqueue(new Callback<Lanche>() {
            @Override
            public void onResponse(Call<Lanche> call, Response<Lanche> response) {
                sendMessage("Adicionado!");
                inProgress(false);
            }

            @Override
            public void onFailure(Call<Lanche> call, Throwable t) {
                sendMessage("Ahh, que coisa não! \n" + t.getMessage());
                inProgress(false);
            }

        });
    }

    @Override
    public void addIngredients() {

    }

    @Override
    public void populatePedido(Long id) {
        carregarDados(id);
    }

    private List<Long> getExtra(List<Ingrediente> list) {
        List<Long> extras = new ArrayList<>();
        for(Ingrediente item : list)
            extras.add(item.getId());
        return extras;
    }

    private void sendMessage(String string) {
        Toast.makeText(getContext(), string.toString(), Toast.LENGTH_SHORT).show();
    }

    private void carregarDados(Long id) {
        inProgress(true);
        _ingrediente.getService().getIngredienteLancheById(id).enqueue(new Callback<List<Ingrediente>>() {
            @Override
            public void onResponse(Call<List<Ingrediente>> call, Response<List<Ingrediente>> response) {
                List<Ingrediente> lista = response.body();
                _view.populateLancheIngredients(lista);
                _view.populateAdapter(lista);
                _view.populateRecycler();
                progressA = true;
                inProgress(!(progressA && progressB));
            }

            @Override
            public void onFailure(Call<List<Ingrediente>> call, Throwable t) {
                sendMessage("Ahh, que coisa não! \n" + t.getMessage());
                progressA = true;
                inProgress(!(progressA && progressB));            }
        });

        _ingrediente.getService().getAllIngredientes().enqueue(new Callback<List<Ingrediente>>() {
            @Override
            public void onResponse(Call<List<Ingrediente>> call, Response<List<Ingrediente>> response) {
                List<Ingrediente> lista = response.body();
                _view.populateAllIngredients(lista);
                progressB = true;
                inProgress(!(progressA && progressB));            }

            @Override
            public void onFailure(Call<List<Ingrediente>> call, Throwable t) {
                sendMessage("Ahh, que coisa não! \n" + t.getMessage());
                progressB = true;
                inProgress(!(progressA && progressB));            }
        });
    }
}
