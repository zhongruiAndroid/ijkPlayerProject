package com.github.ijkplayer.widget.helper;

import com.github.ijkplayer.IjkLibLoader;
import com.github.ijkplayer.IjkMediaPlayer;

public class IjkHelper {
    public static void init() {
        IjkMediaPlayer.loadLibrariesOnce(new IjkLibLoader() {
            @Override
            public void loadLibrary(String libName) throws UnsatisfiedLinkError, SecurityException {

            }
        });
    }

    public static void native_profileBegin() {
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
    }

    public static void native_profileEnd() {
        IjkMediaPlayer.native_profileEnd();
    }
}
