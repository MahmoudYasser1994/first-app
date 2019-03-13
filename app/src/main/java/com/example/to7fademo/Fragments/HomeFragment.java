package com.example.to7fademo.Fragments;


import android.app.AlertDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.to7fademo.Adapter.JobsAdapter;
import com.example.to7fademo.Helper.MyAlertDialog;
import com.example.to7fademo.Models.GetSection.Datum;
import com.example.to7fademo.Models.GetSection.GetSectionModel;
import com.example.to7fademo.R;
import com.example.to7fademo.api.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements JobsAdapter.ListAllClickListeners {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private JobsAdapter jobsAdapter;
    List<Datum> getdata;
    String type;
    private MyAlertDialog myAlertDialog;
    AlertDialog alertDialog;
    Toolbar toolbar;
    private String title;
    TextView txt1;


    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate (R.layout.fragment_home, container, false);
        ButterKnife.bind (this,view);

        recyclerView = view.findViewById (R.id.rec11);
        toolbar = getActivity ( ).findViewById (R.id.toolbar);
        TextView txt1 = toolbar.findViewById (R.id.toolbar_title);
        txt1.setText ("القائمة الرئيسية");


        getdata = new ArrayList<> ( );
        jobsAdapter = new JobsAdapter (new ArrayList<Datum> ( ), this);
        recyclerView = view.findViewById (R.id.rec11);
        GridLayoutManager gridLayoutManager = new GridLayoutManager (getActivity ( ), 2);
        recyclerView.setLayoutManager (gridLayoutManager);
        recyclerView.setItemAnimator (new DefaultItemAnimator ( ));
        recyclerView.setAdapter (jobsAdapter);

        myAlertDialog = new MyAlertDialog (alertDialog);


        onCall ( );


        return view;
    }


    private void onCall() {

        myAlertDialog.showDialogue (getActivity ( ));
        final Call<GetSectionModel> call = RetrofitInstance.getInstance ( ).getApi ( ).getdata ( );

        call.enqueue (new Callback<GetSectionModel> ( ) {
            @Override
            public void onResponse(Call<GetSectionModel> call, Response<GetSectionModel> response) {

                jobsAdapter.replaceData (response.body ( ).getData ( ));

                myAlertDialog.cancell ( );
            }

            @Override
            public void onFailure(Call<GetSectionModel> call, Throwable t) {
                myAlertDialog.cancell ( );

            }
        });
    }

    @Override
    public void onItemClick(Datum datumList, int pos) {

        ProductFragment productFragment = new ProductFragment ( );
        type = String.valueOf (datumList.getId ( ));
        title = String.valueOf (datumList.getName ( ));
        Bundle b = new Bundle ( );
        b.putString ("type", type);
        b.putString ("title", title);
        productFragment.setArguments (b);

        FragmentManager fragmentManager = getFragmentManager ( );
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction ( );
        fragmentTransaction.replace (R.id.container_a, productFragment);
        fragmentTransaction.addToBackStack (null);
        fragmentTransaction.commit ( );

    }
}


