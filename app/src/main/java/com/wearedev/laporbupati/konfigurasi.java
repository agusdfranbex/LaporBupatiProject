package com.wearedev.laporbupati;

/**
 * Created by agusdfranbex on 11/20/2017.
 */

public class konfigurasi {
//pengalamatan file php yg adadi komputer atau server
    public final static String URL_SIMPAN = "http://192.168.44.141/laporbupati/posting_laporan.php";
    public final static String URL_TAMPIL = "http://192.168.44.141/laporbupati/posting_laporan.php"

//merupakan kunci untuk memnggil script php
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAMA = "name";
    public static final String KEY_EMP_EMAIL = "email";
    public static final String KEY_EMP_TELP = "telp";
    public static final String KEY_EMP_LAPOR = "lapor";
    public static final String KEY_EMP_ALMT = "alamat";

//TAG json array
    public static final String TAG_JSON_ARRAY= "result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "name";
    public static final String TAG_POSISI = "desg";
    public static final String TAG_GAJI = "salary";
    public static final String TAG_ALMT = "alamat";

//ID karyawan
//emp itu singkatan dari Employee
    public static final String EMP_ID = "emp_id";

}
