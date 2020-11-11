package com.lqwq.v2_config.ui.tools;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.lqwq.v2_config.R;


import java.util.List;

import eu.chainfire.libsuperuser.Shell;

public class ToolsFragment extends Fragment {



    private ToolsViewModel toolsViewModel;

    private static final String TAG = "ToolsFragment";


    private EditText textView ;
    private Button button ;
    private Button button1 ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);

        textView = root.findViewById(R.id.tmstatus);
        button = root.findViewById(R.id.tmstart);
        button1 = root.findViewById(R.id.tmstop);
        textView.setKeyListener(null);
        new ToolsFragment.MyTask().execute();


        button.setOnClickListener(V ->{
            new ToolsFragment.MyTask().execute("/data/adb/modules/v2ray/scripts/v2ray.tproxy enable");
        });
        button1.setOnClickListener(V ->{
            new ToolsFragment.MyTask().execute("/data/adb/modules/v2ray/scripts/v2ray.tproxy disable");
        });

        return root;
    }


    class MyTask extends AsyncTask<String,String,String> {
        @Override
        protected void onPostExecute(String s) {
            textView.setText(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            List<String> tList = Shell.SU.run(strings);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < tList.size();i++){
                stringBuilder.append(tList.get(i)+"\n");
            }
            return stringBuilder.toString();
        }
    }
}