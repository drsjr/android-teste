package drsjr.com.br.androidteste;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import drsjr.com.br.androidteste.adapter.IngredienteAdapter;
import drsjr.com.br.androidteste.data.entity.Ingrediente;
import drsjr.com.br.androidteste.presenter.ItemPedidoPresenter;
import drsjr.com.br.androidteste.presenter.contract.ItemPedidoContract;
import drsjr.com.br.androidteste.presenter.contract.PedidoContract;

public class ItemPedido extends AppCompatActivity
        implements ItemPedidoContract.ViewAction {

    private ItemPedidoContract.Action presenter;
    private IngredienteAdapter mAdapter;

    @BindView(R.id.item_pedido_recycler)
    RecyclerView mRecycler;

    @BindView(R.id.item_pedido_progress)
    ProgressBar mProgress;


    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);
        presenter.inProgress(true);
        presenter.populateActivity();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_pedido);
        ButterKnife.bind(this);

        /*mRecycler = (RecyclerView) findViewById(R.id.item_pedido_recycler);
        mProgress = (ProgressBar) findViewById(R.id.item_pedido_progress);*/

        presenter = new ItemPedidoPresenter(this);
        mRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(layoutManager);
    }

    @Override
    public void inProgress(boolean progress) {
        mProgress.setVisibility(progress ? View.VISIBLE : View.GONE);
        mRecycler.setVisibility(progress ? View.GONE : View.VISIBLE);
    }

    @Override
    public void sendIngrediente(Ingrediente ingrediente) {
        Intent intent = new Intent();
        intent.putExtra("result", ingrediente);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public void populateRecycler() {
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void populateAdapter(List<Ingrediente> lista) {
        mAdapter = new IngredienteAdapter(lista, this);
    }
}
