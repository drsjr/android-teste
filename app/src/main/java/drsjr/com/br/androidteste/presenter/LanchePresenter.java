package drsjr.com.br.androidteste.presenter;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import drsjr.com.br.androidteste.data.entity.Ingrediente;
import drsjr.com.br.androidteste.data.entity.Lanche;
import drsjr.com.br.androidteste.data.network.IngredienteCallBack;
import drsjr.com.br.androidteste.data.network.LancheCallBack;
import drsjr.com.br.androidteste.data.network.RestAPI;
import drsjr.com.br.androidteste.presenter.contract.LancheContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by junior on 5/6/18.
 */

public class LanchePresenter implements LancheContract.Action {

    private Context _context;
    private LancheContract.ViewAction _view;

    private LancheCallBack _lanche;
    private IngredienteCallBack _ingreditente;
    private List<Ingrediente> listIngredientes;
    private List<Lanche> listLanches;

    public LanchePresenter(Context context) {
        this._context = context;
        this._lanche = new LancheCallBack();
        this._ingreditente = new IngredienteCallBack();
    }

    @Override
    public void setView(LancheContract.ViewAction view) {
        this._view = view;
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
    public void populateFragmet() {
        inProgress(true);
        getLanches();
    }

    private void getLanches() {
        _lanche.getService().getAllLanches().enqueue(new Callback<List<Lanche>>() {
            @Override
            public void onResponse(Call<List<Lanche>> call, Response<List<Lanche>> response) {
                List<Lanche> lista = response.body();
                populateListLanche(lista);
                getIngrediente();
            }

            @Override
            public void onFailure(Call<List<Lanche>> call, Throwable t) {
                Toast.makeText(getContext(), "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getIngrediente() {
        _ingreditente.getService().getAllIngredientes().enqueue(new Callback<List<Ingrediente>>() {
            @Override
            public void onResponse(Call<List<Ingrediente>> call, Response<List<Ingrediente>> response) {
                List<Ingrediente> lista = response.body();
                populateListIngrediente(lista);
                populateAdapter();
                inProgress(false);
            }

            @Override
            public void onFailure(Call<List<Ingrediente>> call, Throwable t) {
                Toast.makeText(getContext(), "Erro: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateListIngrediente(List<Ingrediente> lista) {
        listIngredientes = lista;
    }

    private void populateListLanche(List<Lanche> lista) {
        listLanches = lista;
    }

    private void populateAdapter() {
        _view.populateAdapter(listLanches, listIngredientes);
        _view.populateRecyclerView();
    }
}
