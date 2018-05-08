package drsjr.com.br.androidteste.data.network;

import drsjr.com.br.androidteste.data.DataManager;

/**
 * Created by junior on 5/8/18.
 */

public class PromocaoCallBack extends DataManager<RestAPI.PromocaoCall> {

    private RestAPI.PromocaoCall call;

    public PromocaoCallBack() {
        this.call = super.createService(RestAPI.PromocaoCall.class);
    }

    public RestAPI.PromocaoCall getService() {
        return call;
    }
}
