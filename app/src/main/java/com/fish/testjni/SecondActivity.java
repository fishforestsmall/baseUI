package com.fish.testjni;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SecondActivity extends AppCompatActivity {


    private TextureView textureView;
    private Button play;
    private Button transparent;
    private Button opaque;

    private MediaPlayer mediaPlayer;

    private Surface mySurface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        initView();
        initData();
    }


    private void initView() {
        textureView = findViewById(R.id.textureView);
        play = findViewById(R.id.play);
        transparent = findViewById(R.id.translucent);
        opaque = findViewById(R.id.opaque);
        textureView.setSurfaceTextureListener(listener);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mediaPlayer == null) {
                        mediaPlayer = new MediaPlayer();
                    } else {
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                        }
                    }

                    mediaPlayer.reset();

                    setMediaListener();
                    AssetFileDescriptor descriptor = getResources().openRawResourceFd(R.raw.tt);
                    mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
                    mediaPlayer.setSurface(mySurface);

                    mediaPlayer.prepareAsync();
                } catch (Exception e) {
                    int a = 5;
                }
            }
        });

        transparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translucentAct();
            }
        });

        opaque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opaqueAct();
            }
        });
    }

    private void setMediaListener() {
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                int a = 5;
                mediaPlayer.start();
            }
        });

        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                int a = 5;
            }
        });
    }

    private void initData() {
    }


    private TextureView.SurfaceTextureListener listener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            mySurface = new Surface(surface);
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {

        }
    };

    private void obtainAssets() {
        AssetManager assetManager = getAssets();
    }

    private void obtainRaw() {
        getResources().openRawResource(R.raw.ddd);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    protected void translucentAct() {

        boolean found = false;

        Method[] methods = getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().equals("convertToTranslucent")) {
                found = true;
                invokeConverToTranslucentMethod(method);
                break;
            }
        }

        if (!found) {
            methods = getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals("convertToTranslucent")) {
                    invokeConverToTranslucentMethod(method);
                    break;
                }
            }
        }

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    protected void opaqueAct() {

        boolean found = false;

        Method[] methods = getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().equals("convertFromTranslucent")) {
                found = true;
                invokeConverToTranslucentMethod(method);
                break;
            }
        }

        if (!found) {
            methods = getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.getName().equals("convertFromTranslucent")) {
                    invokeConverToTranslucentMethod(method);
                    break;
                }
            }
        }

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void invokeConverToTranslucentMethod(Method method) {

        if (method == null) {
            return;
        }

        try {

            Object[] params = null;

            if (method.getParameterTypes().length > 0) {
                params = obtainNullObjectArray(method.getParameterTypes().length);
                method.invoke(this, params);
            } else {
                method.invoke(this);
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private Object[] obtainNullObjectArray(int size) {

        if (size <= 0) {
            return null;
        }

        Object[] obj = new Object[size];
        for (int i = 0; i < size; i++) {
            obj[i] = null;
        }

        return obj;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
