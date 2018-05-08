package drsjr.com.br.androidteste.presenter.contract;

import android.content.Context;

/**
 * Created by junior on 5/5/18.
 */

public interface BaseContract {

    interface BaseAction {

        Context getContext();

        void inProgress(final boolean progress);

    }

    interface BaseViewAction {

        void inProgress(final boolean progress);

    }


}
