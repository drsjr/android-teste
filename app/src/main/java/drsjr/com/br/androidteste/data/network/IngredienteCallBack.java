package drsjr.com.br.androidteste.data.network;

import drsjr.com.br.androidteste.data.DataManager;

/**
 * Created by junior on 5/7/18.
 */

public class IngredienteCallBack extends DataManager<RestAPI.LancheCall> {

    private RestAPI.IngredienteCall call;

    public IngredienteCallBack() {
        this.call = super.createService(RestAPI.IngredienteCall.class);
    }

    public RestAPI.IngredienteCall getService() {
        return call;
    }
}
