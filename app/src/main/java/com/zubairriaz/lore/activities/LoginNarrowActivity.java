package com.zubairriaz.lore.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zubairriaz.lore.R;
import com.zubairriaz.lore.fragmets.LoginFragment;

/**
 * Created by zubair on 12/2/2016.
 */

public class LoginNarrowActivity  extends BaseActivity implements LoginFragment.Callbacks{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_narrow_layout);
    }

    @Override
    public void onLoggedIn() {
        setResult(RESULT_OK);
        finish();
    }
}
