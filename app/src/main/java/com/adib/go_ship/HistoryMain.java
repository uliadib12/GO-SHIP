package com.adib.go_ship;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.adib.go_ship.DataLayer.MenuBarViewModel;
import com.adib.go_ship.History.Menunggu;
import com.adib.go_ship.History.Pembatalan;
import com.adib.go_ship.History.Sukses;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryMain#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryMain extends Fragment {

    private FragmentManager fragmentManager;
    private MenuBarViewModel model;
    private ArrayList<Button> allButtons = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistoryMain() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment History.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryMain newInstance(String param1, String param2) {
        HistoryMain fragment = new HistoryMain();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentManager = getChildFragmentManager();
        model = new ViewModelProvider(getActivity()).get(MenuBarViewModel.class);
        setUpViewModel();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpChildFragment();
        setUpMenuBarViews();
    }

    private void setUpMenuBarViews(){
        allButtons.add(getView().findViewById(R.id.sukses_button));
        allButtons.add(getView().findViewById(R.id.menunggu_button));
        allButtons.add(getView().findViewById(R.id.pembatalan_button));

        allButtons.get(model.getSelectedMenu().getValue()).setTextColor(getResources().getColor(R.color.black));
        setAllButtonsOnClickListener();
    }

    private void setAllButtonsOnClickListener(){
        for (int i = 0; i < allButtons.size(); i++) {
            int finalI = i;
            allButtons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    model.setSelectedMenu(finalI);
                }
            });
        }
    }

    private void setUpChildFragment(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.add(R.id.fragmentHistoryMain, new Menunggu());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void setUpViewModel(){
        model.getSelectedMenu().observe(getViewLifecycleOwner(), new androidx.lifecycle.Observer<Integer>() {
            @Override
            public void onChanged(Integer input) {
                switch (input) {
                    case 0:
                        if(model.getPreviousSelectedMenu().getValue() != 0){
                            setAllButtonToGrey();
                            allButtons.get(0).setTextColor(getResources().getColor(R.color.black));

                            FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.replace(R.id.fragmentHistoryMain, new Sukses());
                            transaction.addToBackStack(null);
                            transaction.commit();
                        }
                        break;
                    case 1:
                        if(model.getPreviousSelectedMenu().getValue() != 1){
                            setAllButtonToGrey();
                            allButtons.get(1).setTextColor(getResources().getColor(R.color.black));

                            FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.replace(R.id.fragmentHistoryMain, new Menunggu());
                            transaction.addToBackStack(null);
                            transaction.commit();
                        }
                        break;
                    case 2:
                        if(model.getPreviousSelectedMenu().getValue() != 2){
                            setAllButtonToGrey();
                            allButtons.get(2).setTextColor(getResources().getColor(R.color.black));

                            FragmentTransaction transaction = fragmentManager.beginTransaction();
                            transaction.replace(R.id.fragmentHistoryMain, new Pembatalan());
                            transaction.addToBackStack(null);
                            transaction.commit();
                        }
                        break;
                }
            }
        });
    }

    private void setAllButtonToGrey(){
        for (Button button : allButtons){
            button.setTextColor(getResources().getColor(R.color.graySoft));
        }
    }
}