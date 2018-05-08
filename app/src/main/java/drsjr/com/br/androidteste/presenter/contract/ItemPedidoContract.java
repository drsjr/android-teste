package drsjr.com.br.androidteste.presenter.contract;

import java.util.List;

import drsjr.com.br.androidteste.ItemPedido;
import drsjr.com.br.androidteste.data.entity.Ingrediente;

/**
 * Created by junior on 5/8/18.
 */

public interface ItemPedidoContract {


    interface Action extends BaseContract.BaseAction {

        void setView(ItemPedidoContract.ViewAction view);

        void addIngredients();

        void populateActivity();
    }

    interface ViewAction extends BaseContract.BaseViewAction {

        void sendIngrediente(Ingrediente ingrediente);

        void populateRecycler();

        void populateAdapter(List<Ingrediente> lista);

    }
}
