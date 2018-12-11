/**
 * @author Rafael Oliveira
 *
 * Está Activity tem por finalidade apresentar uma lista com as informações de ações que foram
 * realizadas(Ligar/Desligar) nos componentes externos (lâmpadas).
 *
 * @see android.widget.ExpandableListView
 * @link https://developer.android.com/reference/android/widget/ExpandableListView
 *
 * @see android.support.v4.app.Fragment
 * @link https://developer.android.com/reference/android/support/v4/app/Fragment
 *
 * @see android.view.LayoutInflater
 * @link https://developer.android.com/reference/android/view/LayoutInflater
 *
 * @see android.support.v7.widget.RecyclerView
 * @link https://developer.android.com/guide/topics/ui/layout/recyclerview
 *
 * @see android.support.v4.widget.SwipeRefreshLayout
 * @link https://developer.android.com/reference/androidx/swiperefreshlayout/widget/SwipeRefreshLayout
 *
 * @see android.widget.Switch
 * @link https://developer.android.com/reference/android/widget/Switch
 *
 * @see android.view.View
 * @link https://developer.android.com/reference/android/view/View
 *
 * @see android.view.ViewGroup
 * @link https://developer.android.com/reference/android/view/ViewGroup
 *
 */

package rafael.controlhome.controller.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import rafael.controlhome.R;
import rafael.controlhome.adapters.RecyclerViewAdapter;
import rafael.controlhome.modell.ModelRecord;
import rafael.controlhome.modell.dao.DaoRecord;

public class FragmentRecord extends Fragment implements  SwipeRefreshLayout.OnRefreshListener  {

    private List<ModelRecord> pojoRegistroList;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;


    public static FragmentRecord newInstance() {
        FragmentRecord fragment = new FragmentRecord();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_records, container, false);

        recyclerView = view.findViewById(R.id.recycler_registros);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(FragmentControlHome.modelRecordList);
//        adapter = new RecyclerViewAdapter(pojoRegistroList);
        recyclerView.setAdapter(adapter);


        swipeRefreshLayout = view.findViewById(R.id.swipe_container);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        swipeRefreshLayout.setOnRefreshListener(this);

        return view;
    }

    protected  void carregarDadosRegistros(){
        DaoRecord daoRecord =  new DaoRecord(this.getContext());

         pojoRegistroList = daoRecord.consultar();
    }


    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
                //carregarDadosRegistros();
            }
        }, 500);
    }
}
