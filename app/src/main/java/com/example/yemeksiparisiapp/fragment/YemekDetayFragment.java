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
import com.example.yemeksiparisiapp.entity.Yemekler;
import com.example.yemeksiparisiapp.viewmodel.YemekDetayViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

public class YemekDetayFragment extends Fragment {
    private FragmentYemekDetayBinding tasarim;
    private YemekDetayViewModel viewModel;
    private int adet = 0;
    private Yemekler gelenYemek;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_yemek_detay, container, false);
        tasarim.setYemekDetayFragment(this);
        YemekDetayFragmentArgs bundle = YemekDetayFragmentArgs.fromBundle(getArguments());
        gelenYemek = bundle.getYemek();
        tasarim.setYemekNesnesi(gelenYemek);

        tasarim.setYemekDetayToolbarBaslik(gelenYemek.getYemek_adi());
        tasarim.toolbarYemekDetay.setTitle(gelenYemek.getYemek_adi());

        tasarim.btnSepeteEkle.setOnClickListener(view -> {

            if ( adet >= 1) {
                YemekDetayFragmentDirections.Sepetegecis sepetegecis = YemekDetayFragmentDirections.sepetegecis(gelenYemek);
                Navigation.findNavController(view).navigate(sepetegecis);
                butonSepeteEkle();

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
    viewModel = new ViewModelProvider(this).get(YemekDetayViewModel.class);

   }
    public void resimGoster(String resimUrl) {
        String url = "http://kasimadalan.pe.hu/yemekler/resimler/" + resimUrl;
        Picasso.get().load(url).into(tasarim.ivYemekResim);
    }

    public void buttonArti(){
        adet++;
        tasarim.tvYemekAdedi.setText(String.valueOf(adet));
    }
    public void buttonEksi(){
        if ( adet > 0){
            adet--;
        }

        tasarim.tvYemekAdedi.setText(String.valueOf(adet));
    }

    public void butonSepeteEkle()
    {
            viewModel.sepeteEkleme(gelenYemek.getYemek_adi(),gelenYemek.getYemek_resim_adi(),gelenYemek.getYemek_fiyat(),adet);
   }

}