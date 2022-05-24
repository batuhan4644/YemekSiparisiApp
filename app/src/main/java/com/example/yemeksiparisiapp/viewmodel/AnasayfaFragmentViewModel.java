package com.example.yemeksiparisiapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.yemeksiparisiapp.entity.Yemekler;
import com.example.yemeksiparisiapp.repo.YemeklerDaoRepository;

import java.util.List;

public class AnasayfaFragmentViewModel extends ViewModel {
    private YemeklerDaoRepository yrepo = new YemeklerDaoRepository();
    public MutableLiveData<List<Yemekler>> yemeklerListesi = new MutableLiveData();

    public AnasayfaFragmentViewModel(){
        yemekleriYukle();
        yemeklerListesi = yrepo.yemekleriGetir();
    }

    public void yemekleriYukle() {yrepo.tumYemekleriAl();}
}
