package com.example.yemeksiparisiapp.repo;

import androidx.lifecycle.MutableLiveData;

import com.example.yemeksiparisiapp.entity.CRUDCevap;
import com.example.yemeksiparisiapp.entity.Yemekler;
import com.example.yemeksiparisiapp.entity.YemeklerCevap;
import com.example.yemeksiparisiapp.retrofit.ApiUtils;
import com.example.yemeksiparisiapp.retrofit.YemeklerdaoInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YemeklerDaoRepository {
    private static MutableLiveData<List<Yemekler>> yemeklerListesi;
    public YemeklerdaoInterface ydao;


    public YemeklerDaoRepository(){
        ydao = ApiUtils.getYemeklerDaoInterface();
        yemeklerListesi = new MutableLiveData();

    }

    public MutableLiveData<List<Yemekler>> yemekleriGetir(){
        return yemeklerListesi;
    }

    public void tumYemekleriAl(){
        ydao.tumYemekler().enqueue(new Callback<YemeklerCevap>() {
            @Override
            public void onResponse(Call<YemeklerCevap> call, Response<YemeklerCevap> response) {
                List<Yemekler> liste = response.body().getYemekler();
                yemeklerListesi.setValue(liste);
            }

            @Override
            public void onFailure(Call<YemeklerCevap> call, Throwable t) {

            }
        });
    }

    public void sepeteEkle(String yemek_adi, String yemek_resim_adi, int yemek_fiyat, int yemekadeti, String kullanici_adi){
        ydao.sepetEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemekadeti,kullanici_adi).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) { }
            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) { }
        });
    }



}
