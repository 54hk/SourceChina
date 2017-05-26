package practice.code.com.sourcechina.util;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import practice.code.com.sourcechina.Activity.APP;
import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.fragment.BaseFragemnt;

/**
 * Created by xingge on 2017/5/12.
 *
 * 用于Fragment切换跳转 Activity跳转Fragment、Fragment跳转Fragment、普通类跳转Fragment
 */
public class FragmentBuilder {


    private static FragmentBuilder builder;
    private Bundle bundle;
    private int containerViewId;
    private BaseFragemnt lastFragment;
    private Class<? extends BaseFragemnt> fragmentClass;
    private BaseFragemnt fragment;
    private FragmentTransaction transaction;
    private FragmentManager fragmentManager;
    private String simpleName;
    private boolean isBack = false;
    private boolean isDefault = true;
    private FragmentBuilder(){

    }

    public static FragmentBuilder getInstance(){
        if(builder == null) {
            synchronized (FragmentBuilder.class) {

                if (builder == null)
                    builder = new FragmentBuilder();
            }
        }

        return builder;
    }

    public  FragmentBuilder addStack(String name){
        transaction.addToBackStack(name);
        return this;
    }

//    public FragmentBuilder containerId(int containerViewId){
//        this.containerViewId = containerViewId;
//        return this;
//    }

    public FragmentBuilder start(Class<? extends BaseFragemnt> fragmentClass){

        fragmentManager = APP.activity.getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        simpleName = fragmentClass.getSimpleName();
        fragment = (BaseFragemnt) fragmentManager.findFragmentByTag(simpleName);
        if(fragment == null){
            try {
                //Java动态代理创建对象
                fragment = fragmentClass.newInstance();

                transaction.add(R.id.activity_main_fl, fragment, simpleName);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }




        return this;
    }

//  show
    public FragmentBuilder show(){
        //隐藏上一个fragment
        if (lastFragment != null)
            transaction.hide(lastFragment);
//显示当前的Fragment

        return this;
    }

    public FragmentBuilder addBack(boolean isBack){
         this.isBack = isBack;

     return this;
    }

    public FragmentBuilder place(){
    transaction.replace(R.id.activity_main_fl , fragment,simpleName);
        isDefault = false;
        return this;
    }

    public BaseFragemnt builde(){
        if(isDefault){
            show();
        }

        if(isBack == true)
            transaction.addToBackStack(simpleName);
        transaction.show(fragment);
        transaction.commit();
        isBack = false;
        lastFragment = fragment;
        return fragment;
    }

    public FragmentBuilder setParme(Bundle bundle){
        fragment.Parmes(bundle);
        return this;
    }

    public BaseFragemnt getLastFragment() {
        return lastFragment;
    }

    public void setLastFragment(BaseFragemnt lastFragment) {
        Log.d("FragmentBuilder", "lastFragment:" + lastFragment);
        this.lastFragment = lastFragment;
    }
}
