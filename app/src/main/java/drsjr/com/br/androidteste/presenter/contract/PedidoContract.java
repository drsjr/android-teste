package drsjr.com.br.androidteste.presenter.contract;

import java.util.List;

import drsjr.com.br.androidteste.data.entity.Ingrediente;

/**
 * Created by junior on 5/7/18.
 */

public interface PedidoContract {

    interface Action extends BaseContract.BaseAction {

        void setView(PedidoContract.ViewAction view);

        void addIngredients();

        void populatePedido(Long id);

        void sendCustomPedido(Long id, List<Ingrediente> ingredients);

    }

    interface ViewAction extends BaseContract.BaseViewAction {

        void sendCustomPedido();

        void addIngredients(Ingrediente ingrediente);

        void populateRecycler();

        void populateAdapter(List<Ingrediente> lista);

        void populateLancheIngredients(List<Ingrediente> lista);

        void populateAllIngredients(List<Ingrediente> lista);

        void setIngredients();

    }

}
