package com.example.veterineryardimcisi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    Realm realm;
    TextView tv;
    Button hastaekleB ;
    ListView yakinZamanIslerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RealmTanimla();
        tanimla();
        SaatveTarih();
        hastaEkleBas();
        listelemeYakin();
        pozisyonBul();
    }

    public void tanimla() {
        tv = findViewById(R.id.girisTarihSaat);
        hastaekleB = findViewById(R.id.hastaEkleButon);
        yakinZamanIslerList = findViewById(R.id.yakinZamanIsler);

    }

    public void RealmTanimla() {
        realm = Realm.getDefaultInstance();
    }

    public void SaatveTarih() {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                long date = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy\nHH:mm:ss");
                                String dateString = sdf.format(date);
                                tv.setText(dateString);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
    }

    public void hastaEkleBas() {
        hastaekleB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hastaEkleGecis();
            }
        });
    }

    public void hastaEkleGecis() {
        Intent hastaEkleEkraniGecis = new Intent(this, HastaEkleActivity.class);
        startActivity(hastaEkleEkraniGecis);

    }



    public void listelemeYakin() {
        RealmResults<HastaBilgileri> hb = realm.where(HastaBilgileri.class).findAll();
        if (hb.size() >= 0) {

            AdapterListele adapter = new AdapterListele(hb, getApplicationContext());
            yakinZamanIslerList.setAdapter(adapter);
        }

    }

    public void pozisyonBul() {
        yakinZamanIslerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("name", "" + position);
                silKarari(position);
            }
        });


    }

    public void sil(final int position) {
        final RealmResults<HastaBilgileri> hb = realm.where(HastaBilgileri.class).findAll();
        Log.i("name", "Eleeman Sayisi" + hb.get(position).getHastaAdi());

        realm.beginTransaction();
        HastaBilgileri silincekBilgi = hb.get(position);
        silincekBilgi.deleteFromRealm();
        listelemeYakin();

        for (HastaBilgileri hk : hb) {
            Log.i("Bak", hk.toString());
        }
        realm.commitTransaction();


    }
    public void silKarari(final int position){
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.alertlayout,null);
        Button evet = view.findViewById(R.id.evetButon);
        Button hayir = view.findViewById(R.id.hayirButon);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(view);
        alert.setCancelable(false);

        final AlertDialog alertDialog = alert.create();
        evet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sil(position);
                alertDialog.cancel();
                Toast.makeText(getApplicationContext(), "Silme Islemi Başarılı", Toast.LENGTH_SHORT).show();
            }
        });
        hayir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
                Toast.makeText(getApplicationContext(), "Silme Isleminden Vaz Gecildi", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.show();

    }



}