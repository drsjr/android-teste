package drsjr.com.br.androidteste.adapter;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.List;

import drsjr.com.br.androidteste.R;
import drsjr.com.br.androidteste.data.entity.Ingrediente;
import drsjr.com.br.androidteste.data.entity.Lanche;
import drsjr.com.br.androidteste.presenter.AdapterPresenter;
import drsjr.com.br.androidteste.presenter.contract.AdapterContract;

/**
 * Created by junior on 5/6/18.
 */

public class LancheAdapter extends RecyclerView.Adapter<LancheAdapter.HolderLanche> implements AdapterContract.LancheViewAction {

    private List<Lanche> listLanches;
    private List<Ingrediente> listIngredientes;
    private AdapterContract.LancheAction presenter;

    public LancheAdapter(List<Lanche> lista,  Context context) {
        this.listLanches = lista;
        presenter = new AdapterPresenter(context);
    }

    public LancheAdapter(List<Lanche> lanches, List<Ingrediente> ingredientes,  Context context) {
        this.listLanches = lanches;
        this.listIngredientes = ingredientes;
        presenter = new AdapterPresenter(context);
    }

    @Override
    public HolderLanche onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lanche, parent, false);
        HolderLanche holder = new HolderLanche(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(HolderLanche holder, int position) {
        Lanche lanche = listLanches.get(position);
        holder.mName.setText(lanche.getName());
        holder.mPrice.setText("$" + new BigDecimal(lanche.getPrice(listIngredientes).doubleValue())
                .setScale(2, BigDecimal.ROUND_DOWN));
        Picasso.get().load(lanche.getImage()).into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return listLanches.size();
    }

    @Override
    public void addPedido(int position) {
        presenter.addPedido(listLanches.get(position).getId());
    }

    @Override
    public void customPedido(int position) {
        presenter.customPedido(listLanches.get(position));
    }

    @Override
    public void setIngrediente(Ingrediente ingrediente) {

    }

    class HolderLanche extends RecyclerView.ViewHolder
            implements View.OnClickListener,
                PopupMenu.OnMenuItemClickListener{

        private ImageView mImage;
        private TextView mName;
        private TextView mPrice;
        private ImageView mMenu;
        private PopupMenu popupMenu;

        public HolderLanche(View itemView) {
            super(itemView);
            popupMenu = new PopupMenu(itemView.getContext(), itemView);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.inflate(R.menu.menu_item);

            mImage = (ImageView) itemView.findViewById(R.id.lanche_image);
            mName  = (TextView)  itemView.findViewById(R.id.lanche_name);
            mPrice = (TextView)  itemView.findViewById(R.id.lanche_price);
            mMenu = (ImageView) itemView.findViewById(R.id.lanche_menu);
            mMenu.setOnClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.action_add:
                    addPedido(this.getAdapterPosition());
                    break;
                case R.id.action_custom:
                    customPedido(this.getAdapterPosition());
                    break;
            }
            return true;
        }

        @Override
        public void onClick(View v) {
            popupMenu.show();
        }
    }



}
