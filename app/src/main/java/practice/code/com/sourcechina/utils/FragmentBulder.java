package practice.code.com.sourcechina.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.fragment.BaseFragemnt;

/**
 * Created by Administrator on 2017/5/12 0012.
 */

public class FragmentBulder  {


    private static FragmentManager manager;
    private static FragmentTransaction transaction;
    private static String simpleName;
    private BaseFragemnt listFragment;

    private FragmentBulder(){
        manager = APP.activity.getSupportFragmentManager();
    }
    private static FragmentBulder fragmentBulder;
    public static FragmentBulder getInstance(){

        if(fragmentBulder == null){
            synchronized (FragmentBulder.class){
                fragmentBulder = new FragmentBulder();
            }
        }
        return fragmentBulder;
    }

    // 实务
     public FragmentBulder create(Class<? extends BaseFragemnt> fragmentClass){
         transaction = manager.beginTransaction();
         simpleName = fragmentClass.getSimpleName();
         BaseFragemnt fragment = (BaseFragemnt) manager.findFragmentByTag(simpleName);
         if(fragment == null){
             try {
                 fragment = fragmentClass.newInstance();
                 transaction.add(R.id.activity_main_fl,fragment,simpleName);
             } catch (InstantiationException e) {
                 e.printStackTrace();
             } catch (IllegalAccessException e) {
                 e.printStackTrace();
             }
         }
         if(listFragment != null){
             transaction.hide(listFragment);
         }

         transaction.show(fragment);
         listFragment = fragment;
         transaction.commit();
         return this;


     }

}
