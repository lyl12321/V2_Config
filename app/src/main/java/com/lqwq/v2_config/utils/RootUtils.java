package com.lqwq.v2_config.utils;

import java.util.List;

import eu.chainfire.libsuperuser.Shell;

public class RootUtils {

    public static boolean requestRootPermission() {
        return Shell.SU.available();
    }


}
