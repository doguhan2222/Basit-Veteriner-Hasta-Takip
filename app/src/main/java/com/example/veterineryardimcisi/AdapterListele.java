package com.example.veterineryardimcisi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterListele extends BaseAdapter {
    public AdapterListele(List<HastaBilgileri> liste, Context context) {
        this.liste = liste;
        this.context = context;
    }

    List<HastaBilgileri> liste ;
    Context context;
    @Override
    public int getCount() {
        return liste.size();
    }

    @Override
    public Object getItem(int position) {
        return liste.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.kayitlistelemeyakin,parent,false);
        TextView isimSahipGoster = convertView.findViewById(R.id.hastaSahipAdiYakin);
        TextView noSahipGoster = convertView.findViewById(R.id.hastaSahipNoYakin);
        TextView hastaisimGoster = convertView.findViewById(R.id.hastaAdiYakin);
        TextView hastaCinsiGoster = convertView.findViewById(R.id.hastaCinsiYakin);
        TextView hastaYasiGoster = convertView.findViewById(R.id.hastaYasiYakin);
        TextView aciklamaGoster = convertView.findViewById(R.id.aciklamaYakin);
        TextView tarihGoster = convertView.findViewById(R.id.tarihYakin);

        isimSahipGoster.setText(liste.get(position).getHastaSahibiIsmiSoyismi());
        noSahipGoster.setText(liste.get(position).getHastaSahibiNumarasi());
        hastaisimGoster.setText(liste.get(position).getHastaAdi());
        hastaCinsiGoster.setText(liste.get(position).getHastaCinsi());
        hastaYasiGoster.setText(liste.get(position).getHastaYasi());
        aciklamaGoster.setText(liste.get(position).getAciklama());
        tarihGoster.setText(liste.get(position).getTarih());
        return convertView;
    }
}
