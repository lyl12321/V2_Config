package com.lqwq.v2_config.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.ui.AppBarConfiguration;

import com.lqwq.v2_config.R;
import com.lqwq.v2_config.utils.RootUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.internal.Utils;
import eu.chainfire.libsuperuser.Shell;

public class HomeFragment extends Fragment {


    private HomeViewModel homeViewModel;

    private static final String TAG = "HomeFragment";


    private TextView textView ;
    private Button button ;
    private Button button1 ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        textView = root.findViewById(R.id.textView4);
        button = root.findViewById(R.id.button3);
        button1 = root.findViewById(R.id.button4);
        new MyTask().execute("/sbin/.magisk/img/v2ray/scripts/v2ray.service status");


        button.setOnClickListener(V ->{
            new MyTask().execute("sh /data/adb/modules/v2ray/service.sh");
        });
        button1.setOnClickListener(V ->{
            new MyTask().execute("/data/adb/modules/v2ray/scripts/v2ray.service stop");
        });

        return root;
    }


    class MyTask extends AsyncTask<String,String,String>{
        @Override
        protected void onPostExecute(String s) {
            textView.setText(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            Shell.SU.run(strings);
            List<String> tList = Shell.SU.run("/sbin/.magisk/img/v2ray/scripts/v2ray.service status");
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < tList.size();i++){
                stringBuilder.append(tList.get(i)+"\n");
            }
            return stringBuilder.toString();
        }
    }

}