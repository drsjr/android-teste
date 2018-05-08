package drsjr.com.br.androidteste.presenter.contract;

/**
 * Created by junior on 5/5/18.
 */

public interface MainContract {

    interface Action extends BaseContract.BaseAction {

        void setView(ViewAction view);

    }

    interface ViewAction extends BaseContract.BaseViewAction {

        void accessPedido();

        void accessLanche();

        void accessPromocao();

    }

}
