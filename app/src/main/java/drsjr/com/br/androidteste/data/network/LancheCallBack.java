package drsjr.com.br.androidteste.data.network;

import drsjr.com.br.androidteste.data.DataManager;

/**
 * Created by junior on 5/7/18.
 */

public class LancheCallBack extends DataManager<RestAPI.LancheCall> {

    private RestAPI.LancheCall call;

    public LancheCallBack() {
        this.call = super.createService(RestAPI.LancheCall.class);
    }

    public RestAPI.LancheCall getService() {
        return call;
    }

}
