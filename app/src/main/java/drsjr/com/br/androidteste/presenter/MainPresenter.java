package drsjr.com.br.androidteste.presenter;

import android.content.Context;

import drsjr.com.br.androidteste.presenter.contract.MainContract;

/**
 * Created by junior on 5/5/18.
 */

public class MainPresenter implements MainContract.Action {

    private Context _context;
    private MainContract.ViewAction _view;

    public MainPresenter(Context context) {
        this._context = context;
    }

    @Override
    public Context getContext() {
        return _context;
    }

    @Override
    public void inProgress(boolean progress) {

    }

    @Override
    public void setView(MainContract.ViewAction view) {
        this._view = view;
    }
}
