package drsjr.com.br.androidteste.presenter;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import drsjr.com.br.androidteste.data.entity.Promocao;
import drsjr.com.br.androidteste.data.network.PromocaoCallBack;
import drsjr.com.br.androidteste.presenter.contract.PromocaoContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by junior on 5/8/18.
 */

public class PromocaoPresenter implements PromocaoContract.Action {

    private Context _context;
    private PromocaoCallBack _promocao;
    private PromocaoContract.ViewAction _view;

    public PromocaoPresenter(Context context) {
        _context = context;
        _promocao = new PromocaoCallBack();
    }

    @Override
    public void setView(PromocaoContract.ViewAction view) {
        _view = view;
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
    public void populatePromocao() {
        _promocao.getService().getAllPromocao()
            .enqueue(new Callback<List<Promocao>>() {
                @Override
                public void onResponse(Call<List<Promocao>> call, Response<List<Promocao>> response) {
                    List<Promocao>  lista = response.body();
                    _view.populateAdapter(lista);
                    _view.populateRecycler();
                    inProgress(false);
                }

                @Override
                public void onFailure(Call<List<Promocao>> call, Throwable t) {
                    sendMessage("Erro: " + t.getMessage());
                    inProgress(false);
                }
        });
    }

    private void sendMessage(String string) {
        Toast.makeText(getContext(), string.toString(), Toast.LENGTH_SHORT).show();
    }
}
