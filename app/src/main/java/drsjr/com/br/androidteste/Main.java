package drsjr.com.br.androidteste;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import drsjr.com.br.androidteste.data.entity.Promocao;
import drsjr.com.br.androidteste.presenter.MainPresenter;
import drsjr.com.br.androidteste.presenter.contract.MainContract;

public class Main extends AppCompatActivity implements MainContract.ViewAction {


    View mContainer;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private MainContract.Action presenter;
    private FragmentTransaction mTransaction;

    private LancheFragment mLanche;
    private PromocaoFragment mPromocao;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_promocao:
                    accessPromocao();
                    return true;
                case R.id.navigation_lanche:
                    accessLanche();
                    return true;
                case R.id.navigation_pedido:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        presenter = new MainPresenter(this);
        presenter.setView(this);
        accessPromocao();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //mContainer = (FrameLayout) findViewById(R.id.container_fragment);
        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mLanche = LancheFragment.newInstance();
        mPromocao = PromocaoFragment.newInstance();
    }

    @Override
    public void accessPedido() {

    }

    @Override
    public void accessLanche() {
        replace(mLanche, "Lanche");
    }

    @Override
    public void accessPromocao() {
        replace(mPromocao, "Promocao");
    }

    @Override
    public void inProgress(boolean progress) {

    }

    private void replace(Fragment fragment, String tag) {
        mTransaction = getTransaction();
        mTransaction.replace(R.id.container_fragment, fragment, tag);
        mTransaction.commit();
    }

    private FragmentTransaction getTransaction() {
        return getSupportFragmentManager().beginTransaction();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
