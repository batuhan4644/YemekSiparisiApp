package com.example.yemeksiparisiapp.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yemeksiparisiapp.R;
import com.example.yemeksiparisiapp.databinding.FragmentYemekDetayBinding;
import com.example.yemeksiparisiapp.databinding.FragmentYemekSepetBinding;
import com.example.yemeksiparisiapp.entity.Yemekler;
import com.example.yemeksiparisiapp.viewmodel.YemekDetayViewModel;
import com.example.yemeksiparisiapp.viewmodel.YemekSepetViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.List;

public class YemekSepetFragment extends Fragment {
    private FragmentYemekSepetBinding tasarim;
    private YemekSepetViewModel viewModel;
    private int adet = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_yemek_sepet, container, false);
        tasarim.setYemekSepetFragment(this);
        tasarim.setYemekSepetToolbarBaslik("Sepetim");

        YemekSepetFragmentArgs bundle = YemekSepetFragmentArgs.fromBundle(getArguments());
        Yemekler gelenYemek = bundle.getYemek();


        tasarim.setYemekNesnesi(gelenYemek);

        tasarim.button.setOnClickListener(view -> {
            if ( adet >= 1) {
                Snackbar.make(view, "Siparişiniz Alındı.", Snackbar.LENGTH_SHORT).show();
            }
            else {
                Snackbar.make(view, "Lütfen yemek adedini seçiniz.", Snackbar.LENGTH_SHORT).show();
            }
        });

        resimGoster(tasarim.getYemekNesnesi().getYemek_resim_adi());


        return tasarim.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(YemekSepetViewModel.class);

    }

    public void resimGoster(String resimUrl) {
        String url = "http://kasimadalan.pe.hu/yemekler/resimler/" + resimUrl;
        Picasso.get().load(url).into(tasarim.ivYemekResim);
    }

    public void buttonArti() {
        adet++;
        tasarim.tvYemekSepetAdedi.setText(String.valueOf(adet));
    }

    public void buttonEksi() {
        if (adet > 0) {
            adet--;
        }
        tasarim.tvYemekSepetAdedi.setText(String.valueOf(adet));
    }

}