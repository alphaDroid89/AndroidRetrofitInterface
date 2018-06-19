package com.retrofit.sample.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.retrofit.sample.R;
import com.retrofit.sample.model.Entry;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ApplicationAdapter extends
        RecyclerView.Adapter<ApplicationAdapter.ViewHolder> {

    private static final String TAG = ApplicationAdapter.class.getSimpleName();

    private Context context;
    private List<Entry> list;
    private OnItemClickListener onItemClickListener;

    public ApplicationAdapter(Context context, List<Entry> list,
                              OnItemClickListener onItemClickListener) {
        this.context = context;
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.row_list_item_model, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Entry item = list.get(position);
//        holder.txtAppTitle.setText(item.getImName().getLabel());
        holder.txtAppTitle.setText(item.getTitle().getLabel());
        holder.txtAppDescription.setText(item.getCategory().getAttributes().getLabel());
        Picasso.get().load(item.getImImage().get(0).getLabel()).into(holder.imgAppIcon);
        holder.bind(item, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAppIcon;
        TextView txtAppTitle;
        TextView txtAppDescription;

        public ViewHolder(View view) {
            super(view);
            imgAppIcon = view.findViewById(R.id.img_app_icon);
            txtAppTitle = view.findViewById(R.id.txt_app_title);
            txtAppDescription = view.findViewById(R.id.txt_app_description);
        }

        public void bind(final Entry model,
                         final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getLayoutPosition());

                }
            });
        }
    }

}
