package com.lqwq.v2_config.ui.gallery;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.lqwq.v2_config.R;
import com.lqwq.v2_config.context.MyApplication;
import com.lqwq.v2_config.ui.home.HomeFragment;
import com.lqwq.v2_config.utils.AppUtils;
import com.lqwq.v2_config.utils.KeyboardUtils;
import com.lqwq.v2_config.utils.RootUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import eu.chainfire.libsuperuser.Shell;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private static final String TAG = "GalleryFragment";
    private EditText editText1;
    private StringBuilder appUID;
    private List<String> tAppUID;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        EditText editText = root.findViewById(R.id.editText);
        editText1 = root.findViewById(R.id.editText2);
        Button button = root.findViewById(R.id.button);
        Button button1 = root.findViewById(R.id.button2);

        appUID = new StringBuilder();
//        new GalleryFragment.MyTask().execute("cat /data/v2ray/appid.list");

        button.setOnClickListener(V -> {

            KeyboardUtils.hideKeyboard(root);

            BufferedReader reader = new BufferedReader(new StringReader(editText.getText().toString()));
            appUID.setLength(0); //清空UID列表
            String line ="";

            try {
                while ((line = reader.readLine()) != null){
                    String t = AppUtils.getAppUID(MyApplication.getContext(),line);
                    if (!(t == "PackageNameNotFound")){
                        appUID.append(t+"\n");
                    }
                }
            }catch (IOException e){

            }
            editText1.setText(appUID.toString());
        });


        button1.setOnClickListener(V ->{
            if (Shell.SU.run("echo \""+ editText1.getText().toString() + "\" > /data/v2ray/appid.list") == null){
                Toast.makeText(MyApplication.getContext(),"写入失败",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MyApplication.getContext(),"写入成功",Toast.LENGTH_LONG).show();
            }
//            if (Shell.SU.run("echo \""+ appUID.toString() + "\" > /data/v2ray/appid.list") == null){
//                Toast.makeText(MyApplication.getContext(),"写入失败",Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(MyApplication.getContext(),"写入成功",Toast.LENGTH_LONG).show();
//            }
        });


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        new GalleryFragment.MyTask().execute("cat /data/v2ray/appid.list");
    }

    @Override
    public void onPause() {
        super.onPause();
        editText1.setText("");
    }

    class MyTask extends AsyncTask<String,String,String> {
        @Override
        protected void onPostExecute(String s) {
            editText1.setText(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            tAppUID=Shell.SU.run(strings);
            for (int i = 0; i < tAppUID.size();i++){
                appUID.append(tAppUID.get(i)+"\n");
            }
            return appUID.toString();
        }
    }
}