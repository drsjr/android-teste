package drsjr.com.br.androidteste;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import drsjr.com.br.androidteste.adapter.PromocaoAdapter;
import drsjr.com.br.androidteste.data.entity.Promocao;
import drsjr.com.br.androidteste.presenter.PromocaoPresenter;
import drsjr.com.br.androidteste.presenter.contract.PromocaoContract;

public class PromocaoFragment extends Fragment implements PromocaoContract.ViewAction {

    private PromocaoContract.Action presenter;
    private PromocaoAdapter mAdapter;

    @BindView(R.id.promocao_recycler)
    RecyclerView mRecycler;

    @BindView(R.id.promocao_progress)
    ProgressBar mProgress;


    public PromocaoFragment() {
        // Required empty public constructor
    }

    public static PromocaoFragment newInstance() {
        PromocaoFragment fragment = new PromocaoFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter = new PromocaoPresenter(getActivity());
        presenter.setView(this);
        presenter.inProgress(true);
        presenter.populatePromocao();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promocao, container, false);
        ButterKnife.bind(this, view);

        /*mProgress = view.findViewById(R.id.promocao_progress);
        mRecycler = view.findViewById(R.id.promocao_recycler);*/

        mRecycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecycler.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void inProgress(boolean progress) {
        mProgress.setVisibility( progress ? View.VISIBLE : View.GONE);
        mRecycler.setVisibility( progress ? View.GONE : View.VISIBLE);
    }

    @Override
    public void populateRecycler() {
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void populateAdapter(List<Promocao> lista) {
        mAdapter =  new PromocaoAdapter(lista);
    }
}
