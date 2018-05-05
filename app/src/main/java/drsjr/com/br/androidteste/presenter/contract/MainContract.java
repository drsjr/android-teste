package drsjr.com.br.androidteste.presenter.contract;

/**
 * Created by junior on 5/5/18.
 */

public interface MainContract {

    interface Action extends BaseContract.BaseAction {



    }

    interface ViewAction extends BaseContract.BaseViewAction {

        void accessPedido();

        void acessLanche();

        void accessPromocao();

    }

}
