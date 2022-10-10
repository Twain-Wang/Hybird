package com.wcy.hybirdinteg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.wcy.hybirdcommon.utils.LocalStorage;

import io.dcloud.PandoraEntry;

public class MainActivity extends PandoraEntry {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        intent.putExtra("__start_from_to_class__","com.wcy.hybirdinteg.MainPandoraEntry");
        super.onCreate(savedInstanceState);
        try{
            MainApplication.currActivity = this;
//            LocalStorage.saveSPInfo(MainApplication.getCurrActivity(),"userinfo","");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}