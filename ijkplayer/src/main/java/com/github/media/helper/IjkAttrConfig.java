package com.github.media.helper;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.github.ijkplayer.R;
import com.github.media.IjkVideoView;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class IjkAttrConfig {
    private int playerType=IjkVideoView.PV_PLAYER_IjkMediaPlayer;
    private int renderView = IjkVideoView.RENDER_SURFACE_VIEW;
     /*硬解码*/
    private boolean useMediaCodec;
     /*让媒体编解码器处理分辨率改变*/
    private boolean useMediaCodecHandleResolutionChange;
    private boolean useMediaCodecAutoRotate;
    private boolean useOpenSLES;
    private int pixelFormat=IjkMediaPlayer.SDL_FCC_RV32;
    private boolean useDetachedSurfaceTextureView;
    private boolean enableBackgroundPlay;


    public int getPlayerType() {
        return playerType;
    }

    public void setPlayerTypeIjk( ) {
        this.playerType = IjkVideoView.PV_PLAYER_IjkMediaPlayer;
    }
    public void setPlayerTypeMediaPlay( ) {
        this.playerType = IjkVideoView.PV_PLAYER_AndroidMediaPlayer;
    }

    public int getRenderView() {
        return renderView;
    }

    public void setRenderViewNone() {
        this.renderView = IjkVideoView.RENDER_NONE;
    }

    public void setRenderViewSurfaceView() {
        this.renderView = IjkVideoView.RENDER_SURFACE_VIEW;
    }

    public void setRenderViewTextureView() {
        this.renderView = IjkVideoView.RENDER_TEXTURE_VIEW;
    }

    public boolean isUseMediaCodec() {
        return useMediaCodec;
    }

    public void setUseMediaCodec(boolean useMediaCodec) {
        this.useMediaCodec = useMediaCodec;
    }

    public boolean isUseMediaCodecHandleResolutionChange() {
        return useMediaCodecHandleResolutionChange;
    }

    public void setUseMediaCodecHandleResolutionChange(boolean useMediaCodecHandleResolutionChange) {
        this.useMediaCodecHandleResolutionChange = useMediaCodecHandleResolutionChange;
    }

    public boolean isUseMediaCodecAutoRotate() {
        return useMediaCodecAutoRotate;
    }

    public void setUseMediaCodecAutoRotate(boolean useMediaCodecAutoRotate) {
        this.useMediaCodecAutoRotate = useMediaCodecAutoRotate;
    }

    public boolean isUseOpenSLES() {
        return useOpenSLES;
    }

    public void setUseOpenSLES(boolean useOpenSLES) {
        this.useOpenSLES = useOpenSLES;
    }

    public int getPixelFormat() {
        return pixelFormat;
    }

    public void setPixelFormatRV32() {
        this.pixelFormat = IjkMediaPlayer.SDL_FCC_RV32;
    }

    public void setPixelFormatRV16() {
        this.pixelFormat = IjkMediaPlayer.SDL_FCC_RV16;
    }

    public void setPixelFormatYV12() {
        this.pixelFormat = IjkMediaPlayer.SDL_FCC_YV12;
    }

    public boolean isUseDetachedSurfaceTextureView() {
        return useDetachedSurfaceTextureView;
    }

    public void setUseDetachedSurfaceTextureView(boolean useDetachedSurfaceTextureView) {
        this.useDetachedSurfaceTextureView = useDetachedSurfaceTextureView;
    }

    public boolean isEnableBackgroundPlay() {
        return enableBackgroundPlay;
    }

    public void setEnableBackgroundPlay(boolean enableBackgroundPlay) {
        this.enableBackgroundPlay = enableBackgroundPlay;
    }
    public void initAttr(Context context,AttributeSet attrs) {
        if(context==null){
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IjkVideoView);
        playerType = typedArray.getInt(R.styleable.IjkVideoView_playerType,  IjkVideoView.PV_PLAYER_IjkMediaPlayer);
        renderView = typedArray.getInt(R.styleable.IjkVideoView_renderView,  IjkVideoView.RENDER_SURFACE_VIEW);
        useMediaCodec = typedArray.getBoolean(R.styleable.IjkVideoView_useMediaCodec, false);
        useMediaCodecHandleResolutionChange = typedArray.getBoolean(R.styleable.IjkVideoView_useMediaCodecHandleResolutionChange, false);
        useMediaCodecAutoRotate = typedArray.getBoolean(R.styleable.IjkVideoView_useMediaCodecAutoRotate, false);
        useOpenSLES = typedArray.getBoolean(R.styleable.IjkVideoView_useOpenSLES, false);
        int overlay_format = typedArray.getInt(R.styleable.IjkVideoView_pixelFormat, 0);

        if (overlay_format <= 0) {
            pixelFormat = IjkMediaPlayer.SDL_FCC_RV32;
        } else if (overlay_format == 1) {
            pixelFormat = IjkMediaPlayer.SDL_FCC_RV16;
        } else if (overlay_format == 2) {
            pixelFormat = IjkMediaPlayer.SDL_FCC_YV12;
        }else{
            pixelFormat = IjkMediaPlayer.SDL_FCC_RV32;
        }

        useDetachedSurfaceTextureView = typedArray.getBoolean(R.styleable.IjkVideoView_useDetachedSurfaceTextureView, false);
        enableBackgroundPlay = typedArray.getBoolean(R.styleable.IjkVideoView_enableBackgroundPlay, false);

        typedArray.recycle();
    }
}
