package practice.code.com.sourcechina.Activity;

import android.os.Process;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.fragment.BaseFragemnt;
import practice.code.com.sourcechina.fragment.LoginFragment;
import practice.code.com.sourcechina.util.FragmentBuilder;

public abstract  class  BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base2);
        APP.activity = this;
        APP.context = this;
        APP.fragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    public static void getBackManager(){
        FragmentManager message = APP.activity.getSupportFragmentManager();
        message.popBackStackImmediate();
        String lastname = message.getBackStackEntryAt(message.getBackStackEntryCount()-1).getName();
        BaseFragemnt fragment = (BaseFragemnt) message.findFragmentByTag(lastname);
        FragmentUtils.getFragment().setLastFragment(fragment);

    }

    public static void getLoginMethod(){
        FragmentUtils.getFragment().init(APP.activity).getStart(LoginFragment.class).build();
        ActivityMain activity = (ActivityMain) APP.activity;
        activity.mineTrue.setChecked(true);
    }


}
