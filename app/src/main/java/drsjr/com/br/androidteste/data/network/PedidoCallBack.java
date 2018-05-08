package drsjr.com.br.androidteste.data.network;

import drsjr.com.br.androidteste.data.DataManager;

/**
 * Created by junior on 5/7/18.
 */

public class PedidoCallBack extends DataManager<RestAPI.PedidoCall> {

    private RestAPI.PedidoCall call;

    public PedidoCallBack() {
        this.call = super.createService(RestAPI.PedidoCall.class);
    }

    public RestAPI.PedidoCall getService() {
        return call;
    }
}

