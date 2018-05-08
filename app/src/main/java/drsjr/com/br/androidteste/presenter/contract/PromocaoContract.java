package drsjr.com.br.androidteste.presenter.contract;

import java.util.List;

import drsjr.com.br.androidteste.data.entity.Promocao;

/**
 * Created by junior on 5/8/18.
 */

public interface PromocaoContract {

    interface Action extends BaseContract.BaseAction {

        void setView(PromocaoContract.ViewAction view);

        void populatePromocao();

    }

    interface ViewAction extends BaseContract.BaseViewAction {

        void populateRecycler();

        void populateAdapter(List<Promocao> lista);

    }

}
