package drsjr.com.br.androidteste.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import drsjr.com.br.androidteste.ItemPedido;
import drsjr.com.br.androidteste.R;
import drsjr.com.br.androidteste.data.entity.Ingrediente;
import drsjr.com.br.androidteste.presenter.contract.ItemPedidoContract;

/**
 * Created by junior on 5/7/18.
 */

public class IngredienteAdapter extends RecyclerView.Adapter<IngredienteAdapter.HolderIngrediente>  {

    private List<Ingrediente> listIngredientes;
    private ItemPedidoContract.ViewAction _itemPedido;

    public IngredienteAdapter(List<Ingrediente> lista) {
        this.listIngredientes = lista;
    }

    public IngredienteAdapter(List<Ingrediente> lista,
                              ItemPedidoContract.ViewAction itemPedido) {
        this.listIngredientes = lista;
        this._itemPedido = itemPedido;
    }

    @Override
    public IngredienteAdapter.HolderIngrediente onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ingrediente, parent, false);
        HolderIngrediente holder = new HolderIngrediente(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(IngredienteAdapter.HolderIngrediente holder, int position) {
        Ingrediente ingr = listIngredientes.get(position);
        holder.mName.setText(ingr.getName());
        holder.mPrice.setText("$" + ingr.getPrice().doubleValue());
        Picasso.get().load(ingr.getImage()).into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return listIngredientes.size();
    }

    public void addIngrediente(Ingrediente ingrediente) {
        listIngredientes.add(ingrediente);
        notifyDataSetChanged();
    }

    class HolderIngrediente extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private ImageView mImage;
        private TextView mName;
        private TextView mPrice;

        public HolderIngrediente(View itemView) {
            super(itemView);
            mImage = (ImageView) itemView.findViewById(R.id.ingred_image);
            mName  = (TextView)  itemView.findViewById(R.id.ingred_name);
            mPrice = (TextView)  itemView.findViewById(R.id.ingred_price);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if(_itemPedido instanceof ItemPedido)
                _itemPedido.sendIngrediente(listIngredientes.get(position));
        }
    }

}
