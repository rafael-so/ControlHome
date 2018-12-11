package rafael.controlhome.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rafael.controlhome.R;
import rafael.controlhome.modell.ModelRecord;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<ModelRecord> modelRecordList =  new ArrayList();

    private int[] images = {
            R.mipmap.luz,
            R.mipmap.luz,
            R.mipmap.luz,
            R.mipmap.luz,
            R.mipmap.luz,
    };


    public RecyclerViewAdapter() {

    }

//    public RecyclerViewAdapter(List<ModelComponentHome> pojoComponenteCasaList) {
//        this.pojoComponenteCasaList = pojoComponenteCasaList;
//    }

    public RecyclerViewAdapter(List<ModelRecord> modelRecordList) {
        //Collections.reverse(pojoRegistros);
        this.modelRecordList = modelRecordList;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView itemNome;
        public TextView itemData;
        public TextView itemStatus;
        public ImageView itemImagem;

        public ViewHolder(View itemView) {
            super(itemView);
            itemNome = itemView.findViewById(R.id.textViewNome);
            itemData = itemView.findViewById(R.id.textViewData);
            itemStatus = itemView.findViewById(R.id.textViewStatus);
            itemImagem = itemView.findViewById(R.id.imagem);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_records, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.itemNome.setText(modelRecordList.get(position).getNome());
        viewHolder.itemData.setText(modelRecordList.get(position).getData_hora());
        viewHolder.itemStatus.setText(modelRecordList.get(position).getStatus());
        viewHolder.itemImagem.setImageResource(modelRecordList.get(position).getImagem());


    }

    @Override
    public int getItemCount() {
        return modelRecordList.size();
    }
}
