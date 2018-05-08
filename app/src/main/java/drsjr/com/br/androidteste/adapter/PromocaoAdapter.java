package drsjr.com.br.androidteste.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import drsjr.com.br.androidteste.R;
import drsjr.com.br.androidteste.data.entity.Promocao;

/**
 * Created by junior on 5/7/18.
 */

public class PromocaoAdapter extends RecyclerView.Adapter<PromocaoAdapter.HolderPromocao>  {

    private List<Promocao> listaPromocao;

    public PromocaoAdapter(List<Promocao> lista) {
        this.listaPromocao = lista;
    }

    @Override
    public HolderPromocao onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_promocao, parent, false);
        HolderPromocao holder = new HolderPromocao(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(HolderPromocao holder, int position) {
        Promocao promocao = listaPromocao.get(position);
        holder.mName.setText(promocao.getName());
        holder.mDescription.setText(promocao.getDescription());
    }

    @Override
    public int getItemCount() {
        return listaPromocao.size();
    }

    class HolderPromocao extends RecyclerView.ViewHolder {

        private TextView mName;
        private TextView mDescription;

        public HolderPromocao(View itemView) {
            super(itemView);
            mName  = (TextView)  itemView.findViewById(R.id.promocao_name);
            mDescription = (TextView) itemView.findViewById(R.id.promocao_description);
        }
    }
}
