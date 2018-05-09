package drsjr.com.br.androidteste.presenter.contract;

import java.util.List;

import drsjr.com.br.androidteste.data.entity.Ingrediente;
import drsjr.com.br.androidteste.data.entity.Lanche;

/**
 * Created by junior on 5/6/18.
 */

public interface LancheContract {

    interface Action extends BaseContract.BaseAction {

        void setView(LancheContract.ViewAction view);

        void populateFragmet();
    }

    interface ViewAction extends BaseContract.BaseViewAction {

        void populateRecyclerView();

        void populateAdapter(List<Lanche> lanches, List<Ingrediente> ingredientes);

    }
}
