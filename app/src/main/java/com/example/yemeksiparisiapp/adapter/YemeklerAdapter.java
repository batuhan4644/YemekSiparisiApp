package com.example.yemeksiparisiapp.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.adapters.AdapterViewBindingAdapter;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yemeksiparisiapp.R;
import com.example.yemeksiparisiapp.databinding.ActivityMainBinding;
import com.example.yemeksiparisiapp.databinding.CardTasarimBinding;
import com.example.yemeksiparisiapp.databinding.FragmentYemekDetayBinding;
import com.example.yemeksiparisiapp.entity.Yemekler;
import com.example.yemeksiparisiapp.fragment.AnasayfaFragmentDirections;
import com.example.yemeksiparisiapp.fragment.YemekDetayFragmentArgs;
import com.example.yemeksiparisiapp.viewmodel.AnasayfaFragmentViewModel;
import com.example.yemeksiparisiapp.viewmodel.YemekSepetViewModel;
import com.google.android.material.circularreveal.CircularRevealHelper;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.List;

public class YemeklerAdapter extends RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu> {
    private Context mContext;
    private List<Yemekler> yemeklerListesi;
    private AnasayfaFragmentViewModel viewModel;


    public YemeklerAdapter(Context mContext, List<Yemekler> yemeklerListesi, AnasayfaFragmentViewModel viewModel) {
        this.mContext = mContext;
        this.yemeklerListesi = yemeklerListesi;
        this.viewModel = viewModel;
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder {
        private CardTasarimBinding tasarim;

        public CardTasarimTutucu(CardTasarimBinding tasarim) {
            super(tasarim.getRoot());
            this.tasarim = tasarim;
        }
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        CardTasarimBinding tasarim = DataBindingUtil.inflate(layoutInflater, R.layout.card_tasarim,parent,false);
        return new CardTasarimTutucu(tasarim);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {
        Yemekler yemek = yemeklerListesi.get(position);
        CardTasarimBinding t = holder.tasarim;
        t.setYemekNesnesi(yemek);

        Picasso.get().load("http://kasimadalan.pe.hu/yemekler/resimler/" + t.getYemekNesnesi()
                .getYemek_resim_adi()).into(t.imageViewYemekResim);
        t.cardViewYemek.setOnClickListener(view -> {
            AnasayfaFragmentDirections.DetayGecis gecis = AnasayfaFragmentDirections.detayGecis(yemek);
            Navigation.findNavController(view).navigate(gecis);
        });
    }

    @Override
    public int getItemCount() {
        return yemeklerListesi.size();
    }



}
