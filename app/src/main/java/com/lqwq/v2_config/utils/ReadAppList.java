package com.lqwq.v2_config.utils;



import java.util.ArrayList;
import java.util.List;

import eu.chainfire.libsuperuser.Shell;

public class ReadAppList {

    public static List<String> GetAppList(){
        List<String> c = new ArrayList<>();
        c.add("cat /data/system/packages.list | cut -d ' ' -f1");
        c.add("cat /data/system/packages.list | cut -d ' ' -f2");
        List<String> t = Shell.SU.run(c);
        return t;
    }
}
