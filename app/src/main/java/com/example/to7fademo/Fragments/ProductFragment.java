package com.example.to7fademo.Fragments;


import android.app.AlertDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.to7fademo.Adapter.RecyclerAdapter;
import com.example.to7fademo.Helper.MyAlertDialog;
import com.example.to7fademo.Models.GetProducts.Datum;
import com.example.to7fademo.Models.GetProducts.GetProductsModel;
import com.example.to7fademo.R;
import com.example.to7fademo.api.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment implements RecyclerAdapter.ListAllClickListeners1 {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter recyclerAdapter;
    List<Datum> getproduct;
    String type;
    String title;
    MyAlertDialog myAlertDialog;
    AlertDialog alertDialog;
    Toolbar toolbar;
    TextView txt1;


    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate (R.layout.fragment_product, container, false);

        recyclerView = view.findViewById (R.id.rec12);

        getproduct = new ArrayList<> ( );
        recyclerAdapter = new RecyclerAdapter (new ArrayList<com.example.to7fademo.Models.GetProducts.Datum> ( ), this);
        recyclerView = view.findViewById (R.id.rec12);
        GridLayoutManager gridLayoutManager = new GridLayoutManager (getActivity ( ), 1);
        recyclerView.setLayoutManager (gridLayoutManager);
        recyclerView.setItemAnimator (new DefaultItemAnimator ( ));
        recyclerView.setAdapter (recyclerAdapter);

        Bundle b = this.getArguments ( );
        type = b.getString ("type");
        title = b.getString ("title");


        toolbar = getActivity ( ).findViewById (R.id.toolbar);
        TextView txt1 = toolbar.findViewById (R.id.toolbar_title);
        txt1.setText (title);


        myAlertDialog = new MyAlertDialog (alertDialog);

        onCall ( );

        return view;
    }

    private void onCall() {
        myAlertDialog.showDialogue (getActivity ( ));

        final Call<GetProductsModel> call = RetrofitInstance.getInstance ( ).getApi ( ).getProducts (type, 1);

        call.enqueue (new Callback<GetProductsModel> ( ) {
            @Override
            public void onResponse(Call<GetProductsModel> call, Response<GetProductsModel> response) {

                recyclerAdapter.replaceData (response.body ( ).getData ( ).getData ( ));
                myAlertDialog.cancell ( );

            }

            @Override
            public void onFailure(Call<GetProductsModel> call, Throwable t) {
                myAlertDialog.cancell ( );
            }
        });
    }


    @Override
    public void onItemClick(Datum getproducts, int pos) {

    }
}
