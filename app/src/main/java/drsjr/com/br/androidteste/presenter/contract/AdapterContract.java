package drsjr.com.br.androidteste.presenter.contract;

import drsjr.com.br.androidteste.data.entity.Ingrediente;
import drsjr.com.br.androidteste.data.entity.Lanche;
import drsjr.com.br.androidteste.data.network.IngredienteCallBack;

/**
 * Created by junior on 5/7/18.
 */

public interface AdapterContract {

    interface LancheAction {

        void addPedido(Long id);

        void customPedido(Lanche lanche);

        void setIngredientes();
    }

    interface LancheViewAction {

        void addPedido(int position);

        void customPedido(int position);

        void setIngrediente(Ingrediente ingrediente);
    }



    interface IngredienteViewAction {

        void getIngrediente(Ingrediente ingrediente);
    }
}
