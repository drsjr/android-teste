package drsjr.com.br.androidteste;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import drsjr.com.br.androidteste.adapter.IngredienteAdapter;
import drsjr.com.br.androidteste.data.entity.Ingrediente;
import drsjr.com.br.androidteste.presenter.PedidoPresenter;
import drsjr.com.br.androidteste.presenter.contract.PedidoContract;

public class Pedido extends AppCompatActivity implements PedidoContract.ViewAction {

    @BindView(R.id.pedido_progress)
    ProgressBar mProgress;

    @BindView(R.id.pedido_recycler)
    RecyclerView mRecyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private PedidoContract.Action presenter;
    private IngredienteAdapter mAdapter;

    private Long mIdLanche;
    private List<Ingrediente> listaIngredientes;
    private List<Ingrediente> lancheIngredientes;
    private List<Ingrediente> extras = new ArrayList<>();


    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);
        presenter.inProgress(true);
        presenter.populatePedido(mIdLanche);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        ButterKnife.bind(this);
        presenter = new PedidoPresenter(this);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mProgress = (ProgressBar) findViewById(R.id.pedido_progress);
        mRecyclerView = (RecyclerView) findViewById(R.id.pedido_recycler);*/

        setSupportActionBar(toolbar);

        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIngredients();
            }
        });

        if(getIntent().hasExtra("id"))
            mIdLanche = getIntent().getLongExtra("id", -1l);
        else
            finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pedido_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:

                break;
            case R.id.action_cancel:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            addIngredients((Ingrediente) data.getExtras().getParcelable("result"));
        }
    }

    @Override
    public void inProgress(boolean progress) {
        mProgress.setVisibility( progress ? View.VISIBLE : View.GONE);
        mRecyclerView.setVisibility( progress ? View.GONE: View.VISIBLE);
    }

    @Override
    public void sendCustomPedido() {
        presenter.sendCustomPedido(mIdLanche, extras);
    }

    @Override
    public void populateRecycler() {
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void populateAdapter(List<Ingrediente> lista) {
        mAdapter = new IngredienteAdapter(lista);
    }

    @Override
    public void addIngredients(Ingrediente ingrediente) {
        extras.add(ingrediente);
        mAdapter.addIngrediente(ingrediente);
    }

    @Override
    public void populateLancheIngredients(List<Ingrediente> lista) {
        lancheIngredientes = lista;
    }

    @Override
    public void populateAllIngredients(List<Ingrediente> lista) {
        listaIngredientes = lista;
    }

    @Override
    public void setIngredients() {
        Intent intent = new Intent(this, ItemPedido.class);
        startActivityForResult(intent, 1);
    }
}
