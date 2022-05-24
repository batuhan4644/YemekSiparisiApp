package com.example.yemeksiparisiapp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.yemeksiparisiapp.repo.YemeklerDaoRepository;

public class YemekDetayViewModel extends ViewModel {
    private YemeklerDaoRepository yrepo = new YemeklerDaoRepository();


    public void sepeteEkleme(String yemek_adi, String yemek_resim_adi,
                             int yemek_fiyat, int yemek_siparis_adet ){
        yrepo.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,"batu");
    }



}
