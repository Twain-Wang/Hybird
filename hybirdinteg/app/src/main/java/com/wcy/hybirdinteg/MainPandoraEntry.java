package com.wcy.hybirdinteg;

import android.content.Intent;
import android.os.Bundle;

import io.dcloud.PandoraEntryActivity;

public class MainPandoraEntry extends PandoraEntryActivity {
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        MainApplication.currActivity = this;
    }

    @Override
    public void onActivityResult(int i, int i1, Intent intent) {
        super.onActivityResult(i, i1, intent);
    }
}
