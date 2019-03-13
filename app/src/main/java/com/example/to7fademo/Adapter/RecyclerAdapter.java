package com.example.to7fademo.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.to7fademo.Models.GetProducts.Datum;
import com.example.to7fademo.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<Datum> getproduct;
    Context c;
    ListAllClickListeners1 listAllClickListeners1;

    public RecyclerAdapter(List<Datum> getproduct,  ListAllClickListeners1 listAllClickListeners1) {
        this.getproduct = getproduct;
        this.listAllClickListeners1 = listAllClickListeners1;
    }

    public interface ListAllClickListeners1 {
        void onItemClick(Datum getproducts, int pos);
    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from (parent.getContext ()).inflate (R.layout.productdesign, parent, false);
        c = parent.getContext ();

        return new MyViewHolder (view1);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Datum movies = getproduct.get (position);
        holder.onBind (movies);
    }

    @Override
    public int getItemCount() {
        return getproduct.size ();
    }

    public void replaceData(List<Datum> movies1) {
        this.getproduct.clear ();
        this.getproduct.addAll (movies1);
        notifyDataSetChanged ();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtname,txtpriceafter,txtpricebefore,txtcode,txtdesc;
        ImageView image2;


        public MyViewHolder(View itemView) {
            super (itemView);
            image2 = itemView.findViewById (R.id.imageView);
            txtname = itemView.findViewById (R.id.textname);
            txtpriceafter = itemView.findViewById (R.id.txt_priceafter);
            txtpricebefore = itemView.findViewById (R.id.txtpricebefore);
            txtcode = itemView.findViewById (R.id.txtcode);
            txtdesc = itemView.findViewById (R.id.txtdesc);



        }

        void onBind(final Datum data1) {
            Picasso.get ().load (data1.getImg ()).error (R.drawable.caticon).placeholder (R.drawable.ic_home).into (image2);

            txtname.setText (data1.getDescription ());
            txtpriceafter.setText (data1.getPriceAfter ());
            txtpricebefore.setText (data1.getPriceBefore ());
            txtcode.setText (data1.getCode ());
            txtdesc.setText (data1.getDescription ());
            itemView.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    listAllClickListeners1.onItemClick (data1,getAdapterPosition ());
                }
            });

        }
    }



}
