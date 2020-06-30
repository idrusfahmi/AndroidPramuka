package com.akagami.absensipramuka;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API {

//    String BASEURL = "http://192.168.1.6:8000/api/";
//String BASEURL = "http://10.212.55.225:8000/api/";
String BASEURL = "http://192.168.43.133:8000/api/";
    @FormUrlEncoded
    @POST("login")
    Call<String> postLogin(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("profil_siswa")
    Call<String> postProfilSiswa(
            @Field("username") String username
    );

    @FormUrlEncoded
    @POST("absensi_siswa")
    Call<String> postAbsensiSiswa(
            @Field("username") String username
    );

    @FormUrlEncoded
    @POST("iuran_siswa")
    Call<String> postIuranSiswa(
            @Field("username") String username
    );

    @FormUrlEncoded
    @POST("sku_siswa")
    Call<String> postSkuSiswa(
            @Field("username") String username
    );

}
