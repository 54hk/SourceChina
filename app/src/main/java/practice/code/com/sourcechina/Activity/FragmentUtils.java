package practice.code.com.sourcechina.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import practice.code.com.sourcechina.R;
import practice.code.com.sourcechina.fragment.BaseFragemnt;

/**
 * Created by 颖 on 2017/5/10.
 */

public class FragmentUtils {
    private static FragmentUtils fragmentUtils;
    private FragmentTransaction transaction;
    private FragmentManager fragmentManager;
    private BaseFragemnt fragment;
    public BaseFragemnt lastFragment;

    private FragmentUtils() {

    }

    public static FragmentUtils getFragment() {
        if (fragmentUtils == null) {
            fragmentUtils = new FragmentUtils();
        }
        return fragmentUtils;
    }

    public FragmentUtils init(BaseActivity activity) {
        fragmentManager = activity.getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        return this;
    }
    int id = 0;
    public FragmentUtils setLayID(int id){
          this.id = id;
        return this;
    }
    public int getLayID(){
        return id;
    }

    public FragmentUtils getStart(Class<? extends BaseFragemnt> fragmentclass){
        //通过类获取simpleName
        String simpleName = fragmentclass.getSimpleName();
        //通过fragment的simpleName来获取每个fragment
        fragment = (BaseFragemnt) fragmentManager.findFragmentByTag(simpleName);
        if (fragment ==null){
            try {
                //判断fragment是否为空,动态代理，创建对象
                fragment =fragmentclass.newInstance();
                //添加fragment
                if(id == 0){
                    transaction.add(R.id.activity_main_fl, fragment,simpleName);
                }else{
                    transaction.add(getLayID(), fragment,simpleName);
                    Log.e("iddddd","wsds");
                }

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //判断上一个fragment是否为空，
        if (lastFragment!=null){
            //如果不为空，就隐藏上一个
            transaction.hide(lastFragment);
        }

        //否则显示当前页面
        transaction.show(fragment);
        transaction.addToBackStack(simpleName);
        //addBace(simpleName);
        return this;
    }


    public FragmentUtils place(Class<? extends BaseFragemnt> fragmentclass){
        //通过类获取simpleName
        String simpleName = fragmentclass.getSimpleName();
        //通过fragment的simpleName来获取每个fragment
        fragment = (BaseFragemnt) fragmentManager.findFragmentByTag(simpleName);



        if (fragment ==null){
            try {
                //判断fragment是否为空,动态代理，创建对象
                fragment =fragmentclass.newInstance();
                //添加fragment

                transaction.replace(getLayID(), fragment,simpleName);


            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        transaction.addToBackStack(simpleName);
        //addBace(simpleName);
        return this;
    }

    public BaseFragemnt getLastFragment() {
        return lastFragment;
    }

    public void setLastFragment(BaseFragemnt lastFragment) {
        this.lastFragment = lastFragment;
    }

    //使用bundle传值
    public FragmentUtils setParme(Bundle bundle){
        fragment.Parmes(bundle);
        return this;
    }
    //提交
    public BaseFragemnt build(){
        //将上一个fragment赋值给空的basefragment，记录
        lastFragment=fragment;
        transaction.commit();
        return fragment;
    }
}
