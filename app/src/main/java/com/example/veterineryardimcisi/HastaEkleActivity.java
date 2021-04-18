package com.example.veterineryardimcisi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class HastaEkleActivity extends AppCompatActivity {
    EditText hastaSahipAdiEd, hastaSahipNoEd, hastaAdiEd, hastaCinsiEd, hastaYasEd, hastaAciklamaEd;
    TextView tarihSecGoster;
    Button kaydetBu,tarihSecBu ;
    Realm realm;
    Calendar calendar;
    DatePickerDialog dpd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasta_ekle);
        tanimla();
        RealmTanimla();
        BilgileriAl();
        tarihiAl();



    }

    public void RealmTanimla() {
        realm = Realm.getDefaultInstance();

    }

    public void tanimla() {
        hastaSahipAdiEd = findViewById(R.id.hastaSahipAdE);
        hastaSahipNoEd = findViewById(R.id.hastaSahipNoE);
        hastaAdiEd = findViewById(R.id.hastaAdE);
        hastaCinsiEd = findViewById(R.id.hastaCinsiE);
        hastaYasEd = findViewById(R.id.hastaYasiE);
        hastaAciklamaEd = findViewById(R.id.hastaAciklamaE);
        kaydetBu = findViewById(R.id.kaydetButon);
        tarihSecGoster = findViewById(R.id.tarihiGosterT);
        tarihSecBu= findViewById(R.id.tarihiSecB);

    }
    public void tarihiAl(){
        tarihSecBu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int gun = calendar.get(Calendar.DAY_OF_MONTH);
                int ay = calendar.get(Calendar.MONTH);
                int yil = calendar.get(Calendar.YEAR);

                dpd = new DatePickerDialog(HastaEkleActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int yil, int ay, int gun) {
                        tarihSecGoster.setText(gun + "/"+ (ay+1) +"/"+ yil);

                    }

                },yil,ay,gun);
                dpd.show();
            }

        });
    }


    public void BilgileriAl() {



        kaydetBu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String hastaSahibiAdiveSoyadiAl = hastaSahipAdiEd.getText().toString();
                final String hastaSahibiNumarasiAl = hastaSahipNoEd.getText().toString();
                final String hastaAdiAl = hastaAdiEd.getText().toString();
                final String hastaYasiAl = hastaYasEd.getText().toString();
                final String hastaCinsiAl = hastaCinsiEd.getText().toString();
                final String aciklamaAl = hastaAciklamaEd.getText().toString();
                final String tarihAl = tarihSecGoster.getText().toString();
                veriTabaninaKaydet(hastaSahibiAdiveSoyadiAl, hastaSahibiNumarasiAl, hastaAdiAl, hastaYasiAl, hastaCinsiAl, aciklamaAl,tarihAl);
                anaEkranaGecisYap();
            }
        });
    }


    public void veriTabaninaKaydet(final String hastaSahibiAdiveSoyadiAl, final String hastaSahibiNumarasiAl, final String hastaAdiAl, final String hastaYasiAl, final String hastaCinsiAl, final String aciklamaAl,final String tarihAl) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                HastaBilgileri hastabilgileri = realm.createObject(HastaBilgileri.class);
                hastabilgileri.setHastaSahibiIsmiSoyismi(hastaSahibiAdiveSoyadiAl);
                hastabilgileri.setHastaSahibiNumarasi(hastaSahibiNumarasiAl);
                hastabilgileri.setHastaAdi(hastaAdiAl);
                hastabilgileri.setHastaYasi(hastaYasiAl);
                hastabilgileri.setHastaCinsi(hastaCinsiAl);
                hastabilgileri.setAciklama(aciklamaAl);
                hastabilgileri.setTarih(tarihAl);
            }
        }, new Realm.Transaction.OnSuccess() {

            @Override
            public void onSuccess() {
                Toast.makeText(getApplicationContext(), " Kayit Başarılı", Toast.LENGTH_LONG).show();
            }
        }, new Realm.Transaction.OnError() {

            @Override
            public void onError(Throwable error) {
                Toast.makeText(getApplicationContext(), "Kayit Başarısız", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void anaEkranaGecisYap() {
        Intent anaEkranaGecis = new Intent(this, MainActivity.class);
        startActivity(anaEkranaGecis);

    }


}