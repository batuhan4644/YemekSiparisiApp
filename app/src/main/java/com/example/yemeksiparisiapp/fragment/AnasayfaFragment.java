package com.example.yemeksiparisiapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.yemeksiparisiapp.R;
import com.example.yemeksiparisiapp.adapter.YemeklerAdapter;
import com.example.yemeksiparisiapp.databinding.FragmentAnasayfaBinding;
import com.example.yemeksiparisiapp.entity.Yemekler;
import com.example.yemeksiparisiapp.viewmodel.AnasayfaFragmentViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AnasayfaFragment extends Fragment  {
    private FragmentAnasayfaBinding tasarim;
    private AnasayfaFragmentViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_anasayfa, container, false);
        tasarim.setAnasayfaFragment(this);

        tasarim.setAnasayfaToolbarBaslik("Yemekler");
        ((AppCompatActivity)getActivity()).setSupportActionBar(tasarim.toolbarAnasayfa);

        viewModel.yemeklerListesi.observe(getViewLifecycleOwner(),liste->{
            YemeklerAdapter adapter = new YemeklerAdapter(requireContext(),liste,viewModel);
            tasarim.setYemeklerAdapter(adapter);
        });

         tasarim.rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        return tasarim.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        viewModel = new ViewModelProvider(this).get(AnasayfaFragmentViewModel.class);

    }



}