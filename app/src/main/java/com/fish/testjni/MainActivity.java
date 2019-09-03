package com.fish.testjni;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.fish.calnum.Compute;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.crypto.Cipher;

public class MainActivity extends AppCompatActivity{

    private Button btnOne;
    private Button btnTwo;
    private RecyclerView rv;

    private SensorManager sensorManager;
    private MySensor mySensor;

    private View rootView;
    private View backView;

    private boolean isDragView = true;

    private Drawable backDrawable = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initSensor();
    }

    private void initView() {
        btnOne = findViewById(R.id.test_jni_one);
        btnTwo = findViewById(R.id.test_jni_two);

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = 6;
                btnOne.setPadding(150, 0, 150, 0);
//                translucentAct();
//                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });


        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opaqueAct();
                String jj = "dd";
                testString(jj);
                int a = 5;
//                rootView.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        findViewById(R.id.drag1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDragView = false;
            }
        });

        findViewById(R.id.drag2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDragView = true;
            }
        });

        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backView.setY(0);
                rootView.setY(0);
            }
        });

        findViewById(R.id.getbg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDragView) {
                    drawable2Bitmap(backView.getBackground());
                } else {
                    drawable2Bitmap(rootView.getBackground());
                }
            }
        });


        rv = findViewById(R.id.rv);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new RVAdapter());

        backView = findViewById(R.id.backview);
        rootView = getWindow().getDecorView();
        touchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
    }

    private void testString(String pic) {
        pic = "jjjd";
    }

    private void drawable2Bitmap(Drawable drawable) {
        if (drawable == null)
            return;

        int height = drawable.getBounds().height();
        int width = drawable.getBounds().width();

        if (height <= 0 || width <= 0) {
            return;
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
        drawable.draw(canvas);
        int a = 5;
    }

    private float lastY = 0;
    private int touchSlop = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float curY = ev.getY();
                if (Math.abs(curY - lastY )> touchSlop) {
                    if (isDragView) {
                        backView.setY((curY - lastY));
                    } else {
                        rootView.setY((curY - lastY));
                    }

                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(ev);
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
        sensorManager.registerListener(mySensor, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_NORMAL);
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
        sensorManager.unregisterListener(mySensor);
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
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Log.i("test", "orientation:" + newConfig.orientation);
        Log.i("test", displayMetrics.widthPixels + "===" + displayMetrics.heightPixels);

//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            btnOne.setPadding(150, 0, 150, 0);
//        }
    }

    protected void translucentAct() {

        boolean found = false;

        backDrawable = rootView.getBackground();

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

        getWindow().setBackgroundDrawable(backDrawable);
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

    private void initSensor() {
        sensorManager  = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mySensor = new MySensor();
    }

    boolean isSet = false;

    class MySensor implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (!isSet) {
//                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                isSet = true;
            }
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            int c = 6;
        }
    }

    public DisplayMetrics getMetrics() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

}
