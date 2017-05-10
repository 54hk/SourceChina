package practice.code.com.sourcechina.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import practice.code.com.sourcechina.R;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class Head_message_Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View inflate = inflater.inflate(R.layout.head_fragment_adapter, null);

        return inflate;
    }
}
