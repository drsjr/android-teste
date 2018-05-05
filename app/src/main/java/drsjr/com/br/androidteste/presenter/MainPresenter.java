package drsjr.com.br.androidteste.presenter;

import android.content.Context;
import android.view.View;

import drsjr.com.br.androidteste.presenter.contract.MainContract;

/**
 * Created by junior on 5/5/18.
 */

public class MainPresenter implements MainContract.Action {

    private Context _context;
    private View _view;

    public MainPresenter(Context context) {
        this._context = context;
    }


    @Override
    public Context getContext() {
        return _context;
    }

    @Override
    public void setView(View view) {

    }

    @Override
    public void inProgress(boolean progress) {

    }
}
