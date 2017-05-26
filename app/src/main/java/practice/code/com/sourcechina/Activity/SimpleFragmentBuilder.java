package practice.code.com.sourcechina.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.fragment.BaseFragemnt;
import practice.code.com.sourcechina.util.FragmentBuilder;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public class SimpleFragmentBuilder {


    private static SimpleFragmentBuilder builder;
    private int containerViewId;
    private BaseFragemnt lastFragment;
    private BaseFragemnt fragment;
    private FragmentManager fragmentManager;

    private SimpleFragmentBuilder(){
        fragmentManager = APP.activity.getSupportFragmentManager();
    }

    public static SimpleFragmentBuilder getInstance(){
        if(builder == null) {
            synchronized (SimpleFragmentBuilder.class) {

                if (builder == null)
                    builder = new SimpleFragmentBuilder();
            }
        }

        return builder;
    }

//    public FragmentBuilder containerId(int containerViewId){
//        this.containerViewId = containerViewId;
//        return this;
//    }

    public SimpleFragmentBuilder start(Class<? extends BaseFragemnt> fragmentClass){
        String simpleName = fragmentClass.getSimpleName();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        fragment = (BaseFragemnt) fragmentManager.findFragmentByTag(simpleName);
        if(fragment == null){
            try {
                //Java动态代理创建对象
                fragment = fragmentClass.newInstance();

                transaction.add(R.id.activity_main_fl, fragment,simpleName);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        //隐藏上一个fragment
        if (lastFragment != null)
            transaction.hide(lastFragment);

        //显示当前的Fragment
        transaction.show(fragment);
        transaction.addToBackStack(simpleName);
        lastFragment = fragment;
        transaction.commit();
        return this;
    }

    public BaseFragemnt getLastFragment() {
        return lastFragment;
    }
    public SimpleFragmentBuilder setParme(Bundle bundle){
        fragment.Parmes(bundle);
        return this;
    }
    public void setLastFragment(BaseFragemnt lastFragment) {
        this.lastFragment = lastFragment;
    }
}
