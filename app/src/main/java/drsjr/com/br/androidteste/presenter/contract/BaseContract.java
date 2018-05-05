package drsjr.com.br.androidteste.presenter.contract;

import android.content.Context;
import android.view.View;

/**
 * Created by junior on 5/5/18.
 */

public interface BaseContract {

    interface BaseAction {

        Context getContext();

        void setView(View view);

        void inProgress(final boolean progress);

    }

    interface BaseViewAction {

        void inProgress(final boolean progress);

    }


}
