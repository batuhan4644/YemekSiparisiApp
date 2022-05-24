package com.example.yemeksiparisiapp.retrofit;

import com.example.yemeksiparisiapp.entity.CRUDCevap;

import com.example.yemeksiparisiapp.entity.YemeklerCevap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface YemeklerdaoInterface {
    @GET("yemekler/tumYemekleriGetir.php")
   Call<YemeklerCevap> tumYemekler();

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    Call<CRUDCevap> sepetEkle(@Field("yemek_adi") String yemek_adi,@Field("yemek_resim_adi") String yemek_resim_adi,
                               @Field("yemek_fiyat") int yemek_fiyat,@Field("yemek_siparis_adet") int yemek_siparis_adet,
                               @Field("kullanici_adi") String kullanici_adi );



}
