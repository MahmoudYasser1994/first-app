package com.example.to7fademo.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.to7fademo.Models.GetSection.Datum;
import com.example.to7fademo.R;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by MahmoudAyman on 05/02/2019.
 */
public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.MyViewHolder> {
    private List<Datum> datumList;
    private Context c;
    private ListAllClickListeners listAllClickListeners;

    public JobsAdapter(@NonNull List<Datum> datumList, ListAllClickListeners listAllClickListeners) {
        this.datumList = datumList;
        this.listAllClickListeners = listAllClickListeners;
    }

    public interface ListAllClickListeners {
        void onItemClick(Datum datumList, int pos);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (parent.getContext ())
                .inflate (R.layout.homedesign, parent, false);
        c = parent.getContext ();

        return new MyViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Datum movie = datumList.get (position);
        holder.onBind (movie);
    }

    @Override
    public int getItemCount() {
        return datumList.size ();
    }

    public void replaceData(List<Datum> movies) {
        this.datumList.clear ();
        this.datumList.addAll (movies);
        notifyDataSetChanged ();
    }

//    public void updateData(List<Datum> movies) {
//        this.datumList.addAll(movies);
//        notifyDataSetChanged();
//    }

//    public Datum getItem(int position) {
//        if (position < 0 || position >= datumList.size()) {
//            throw new InvalidParameterException("INVALID IDX");
//        }
//        return datumList.get(position);
//    }

//    public void clearData() {
//        datumList.clear();
//        notifyDataSetChanged();
//    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtv;
        ImageView image;

        public MyViewHolder(View itemView) {
            super (itemView);
            image = itemView.findViewById (R.id.imageView);
            txtv = itemView.findViewById (R.id.textView);
        }


        void onBind(final Datum data) {
            Picasso.get ().load (data.getImg ()).error (R.drawable.caticon).placeholder (R.drawable.ic_home).into (image);

           txtv.setText(data.getName ());

            itemView.setOnClickListener (v ->
                    listAllClickListeners.onItemClick (data, getAdapterPosition ())
            );
        }

    }
}