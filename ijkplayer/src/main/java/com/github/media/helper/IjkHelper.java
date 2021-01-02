package com.github.media.helper;


import tv.danmaku.ijk.media.player.IjkLibLoader;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class IjkHelper {
    public static void init(IjkLibLoader libLoader) {
        IjkMediaPlayer.loadLibrariesOnce(libLoader);
    }

    public static void native_profileBegin() {
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");
    }

    public static void native_profileEnd() {
        IjkMediaPlayer.native_profileEnd();
    }
}
