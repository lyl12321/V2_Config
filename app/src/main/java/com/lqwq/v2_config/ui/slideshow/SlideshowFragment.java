package com.lqwq.v2_config.ui.slideshow;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lqwq.v2_config.MainActivity;
import com.lqwq.v2_config.R;
import com.lqwq.v2_config.context.MyApplication;
import com.lqwq.v2_config.utils.ReadAppList;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import eu.chainfire.libsuperuser.Shell;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private RecyclerView recyclerView;
    private static List<AppList> appLists = new ArrayList<>();
    private static final String TAG = "SlideshowFragment";
    private EditText textView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        textView = root.findViewById(R.id.textView3);
        textView.setKeyListener(null);
        new MyTask().execute();

        return root;
    }


    class MyTask extends AsyncTask<String,String,String>{
        @Override
        protected void onPostExecute(String s) {
            textView.setText(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();
            List<String> t = Shell.SU.run("cat /data/v2ray/run/error.log");
            for (int i =0;i< t.size();i++){
                stringBuilder.append(t.get(i).toString()+"\n");
            }
            return stringBuilder.toString();
        }
    }
}