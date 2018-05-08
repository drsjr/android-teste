package drsjr.com.br.androidteste;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import drsjr.com.br.androidteste.adapter.LancheAdapter;
import drsjr.com.br.androidteste.data.entity.Lanche;
import drsjr.com.br.androidteste.presenter.LanchePresenter;
import drsjr.com.br.androidteste.presenter.contract.LancheContract;


public class LancheFragment extends Fragment implements LancheContract.ViewAction {

    private LancheContract.Action presenter;
    private LancheAdapter mAdapter;

    private RecyclerView mRecyclerView;
    private ProgressBar  mProgress;

    public LancheFragment() {
        // Required empty public constructor
    }

    public static LancheFragment newInstance() {
        LancheFragment fragment = new LancheFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter = new LanchePresenter(getActivity());
        presenter.setView(this);
        presenter.populateFragmet();
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
        View view = inflater.inflate(R.layout.fragment_lanche, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.lanche_recycler);
        mProgress = (ProgressBar) view.findViewById(R.id.lanche_progress);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void inProgress(boolean progress) {
        mProgress.setVisibility(progress ? View.VISIBLE : View.GONE);
        mRecyclerView.setVisibility(progress ? View.GONE : View.VISIBLE);
    }

    @Override
    public void populateRecyclerView() {
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void populateAdapter(List<Lanche> list) {
        mAdapter = new LancheAdapter(list, getActivity().getBaseContext());
    }

}
