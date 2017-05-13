package practice.code.com.sourcechina.util;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

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
    private int containerViewId;
    private BaseFragemnt lastFragment;

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

//    public FragmentBuilder containerId(int containerViewId){
//        this.containerViewId = containerViewId;
//        return this;
//    }

    public FragmentBuilder start(Class<? extends BaseFragemnt> fragmentClass){

        FragmentManager fragmentManager = APP.activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        String simpleName = fragmentClass.getSimpleName();
        BaseFragemnt fragment = (BaseFragemnt) fragmentManager.findFragmentByTag(simpleName);
        if(fragment == null){
            try {
                //Java动态代理创建对象
                fragment = fragmentClass.newInstance();

                transaction.add(R.id.activity_main_fl,fragment,simpleName);

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

    public void setLastFragment(BaseFragemnt lastFragment) {
        this.lastFragment = lastFragment;
    }
}
