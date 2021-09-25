package com.example.befitgroupproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class HeartRateActivity extends AppCompatActivity {

    private static final String HEARTBEATS = "heartbeats";
    Intent resIntent;
    private static int res;
    private String resStr;

    private Button mButtonBack;
    private static TextView mTvBeats;
    private static TextView mTvPixelAvg;
    private static TextView mTvResultHR;
    private Context context;

    private Timer timer;
    private TimerTask task;
    private static int gx;
    private static int j;


    private static double flag = 1;
    private Handler handler;
    private String title = "pulse";
    private XYSeries series;
    private XYMultipleSeriesDataset mDataset;
    private GraphicalView chart;
    private XYMultipleSeriesRenderer renderer;
    private int addX = -1;
    double addY;
    int[] xv = new int[300];
    int[] yv = new int[300];
    int[] curve = new int[] {9,10,11,12,13,14,13,12,11,10,9,8,7,6,7,8,9,10,11,10,10};

    private static final AtomicBoolean processing= new AtomicBoolean(false);
    private SurfaceView preview = null;
    private static SurfaceHolder previewHolder = null;
    private static android.hardware.Camera camera = null;
    private PowerManager pm;
    private static PowerManager.WakeLock wakeLock = null;
    private static int averageIndex = 0;
    private static final int averageArraySize = 4;
    private static final int[] averageArray = new int[averageArraySize];

    private static int beatsIndex = 0;
    private static final int beatsArraySize = 3;
    private static final int[] beatsArray = new int[beatsArraySize];
    private static double beats = 0;
    private static long startTime = 0;

    private final int REQUEST_PERMISSION_CAMERA = 1;
    private boolean isCameraEnabled;
    private final int REQUEST_PERMISSION_WAKE_LOCK = 2;
    private boolean isWakeLockEnabled;

    public enum TYPE { GREEN, RED };
    private static TYPE currentType = TYPE.GREEN;

    private FirebaseDatabase rootNode;
    private DatabaseReference reference;
    private String userId;
    private ArrayList<String> heartRateList;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Users");

        user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        TextView heartRate = findViewById(R.id.textView32);

        reference.child(userId).child("HeartRate").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null) {
                   heartRate.setText("Take a heart rate test!");
                } else {
                    GenericTypeIndicator<ArrayList<String>> t =
                            new GenericTypeIndicator<ArrayList<String>>() {
                            };
                    heartRateList = new ArrayList<>();
                    heartRateList = dataSnapshot.getValue(t);
                    heartRate.setText("last test: "+ heartRateList.get(heartRateList.size()-1) + "bpm");
                }

            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(HeartRateActivity.this, error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });



        Button back = findViewById(R.id.button17);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HeartRateActivity.this, LaunchActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });


        initConfig();
    }

    @SuppressLint("HandlerLeak")
    @SuppressWarnings("deprecation")
    protected void initConfig() {

        resIntent = getIntent();

        mButtonBack = findViewById(R.id.button_back);
        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnHeartRate(v);
            }
        });

        mTvPixelAvg = findViewById(R.id.tv_pixel_avg);
        mTvResultHR = findViewById(R.id.tv_heart_rate);
        mTvBeats = findViewById(R.id.tv_beats);

        timer = new Timer();
        context = getApplicationContext();

        LinearLayout layout = (LinearLayout) findViewById(R.id.linear_layout);
        series = new XYSeries(title);
        mDataset = new XYMultipleSeriesDataset();
        mDataset.addSeries(series);

        int color = Color.GREEN;
        PointStyle style = PointStyle.CIRCLE;
        renderer = buildRenderer(color, style, true);
        setChartSettings(renderer, "X", "Y", 0, 300, 4, 16, Color.WHITE, Color.WHITE);
        chart = ChartFactory.getLineChartView(context, mDataset, renderer);

        layout.addView(chart, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                updateChart();
                super.handleMessage(msg);
            }
        };

        task = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        };

        timer.schedule(task, 1, 20);
        pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, ":DoNotDimScreen");

        preview = (SurfaceView) findViewById(R.id.camera_preview);
        preview.setZOrderOnTop(true);
        previewHolder = preview.getHolder();
        previewHolder.addCallback(surfaceCallback);
        previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResume() {
        super.onResume();

        if (
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WAKE_LOCK)
                        != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(new String[] {Manifest.permission.WAKE_LOCK}, REQUEST_PERMISSION_WAKE_LOCK);
        } else {
            isWakeLockEnabled = true;
        }

        if (
                ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(new String[] {Manifest.permission.CAMERA}, REQUEST_PERMISSION_CAMERA);
        } else {
            isCameraEnabled = true;
        }

        if(isWakeLockEnabled)
            wakeLock.acquire();
        else
            mTvBeats.setText("Wake lock denied");

        //Reopencamera
        if(isCameraEnabled)
           camera = Camera.open();
        else
            mTvBeats.setText("Camera denied.");

        startTime = System.currentTimeMillis();
    }

    @Override
    public void onPause() {
        super.onPause();
        wakeLock.release();
        //Close and re-open every time to make sure that each frame functions sequentially.
        camera.setPreviewCallback(null);
        camera.stopPreview();
        camera.release();
        camera = null;
    }

    @Override
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }



    protected XYMultipleSeriesRenderer buildRenderer(int color, PointStyle style, boolean fill) {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();

        XYSeriesRenderer r = new XYSeriesRenderer();
        r.setColor(Color.RED);
        r.setLineWidth(1);
        renderer.addSeriesRenderer(r);
        return renderer;
    }

    protected void setChartSettings(XYMultipleSeriesRenderer renderer, String xTitle, String yTitle,
                                    double xMin, double xMax, double yMin, double yMax, int axesColor, int labelsColor) {
        renderer.setChartTitle(title);
        renderer.setXTitle(xTitle);
        renderer.setYTitle(yTitle);
        renderer.setXAxisMin(xMin);
        renderer.setXAxisMax(xMax);
        renderer.setYAxisMin(yMin);
        renderer.setYAxisMax(yMax);
        renderer.setAxesColor(axesColor);
        renderer.setLabelsColor(labelsColor);
        renderer.setShowGrid(true);
        renderer.setGridColor(Color.parseColor("#FFF8EDE0"));
        renderer.setXLabels(20);
        renderer.setYLabels(10);
        renderer.setXTitle("Time");
        renderer.setYTitle("mmHg");
        renderer.setYLabelsAlign(Paint.Align.RIGHT);
        renderer.setPointSize((float) 3 );
        renderer.setShowLegend(false);
    }

    private void updateChart() {

        if(flag == 1) {
            addY = 10;
        } else {
            flag = 1;
            if(gx < 200){
                if(curve[20] > 1){
                    Toast.makeText(context, "Press your finger against the camera！", Toast.LENGTH_SHORT).show();
                    curve[20] = 0;
                }
                curve[20]++;
                return;
            } else {
                curve[20] = 10;
            }
            j = 0;
        }
        if(j < 20){
            addY = curve[j];
            j++;
        }

        mDataset.removeSeries(series);

        int length = series.getItemCount();
        int bz = 0;
        //addX = length;
        if (length > 300) {
            length = 300;
            bz = 1;
        }
        addX = length;

        for (int i = 0; i < length; i++) {
            xv[i] = (int) series.getX(i) - bz;
            yv[i] = (int) series.getY(i);
        }

        series.clear();
        mDataset.addSeries(series);

        series.add(addX, addY);
        for (int k = 0; k < length; k++) {
            series.add(xv[k], yv[k]);
        }

        chart.invalidate();
    }

    private static Camera.PreviewCallback previewCallback = new Camera.PreviewCallback() {
        @Override
        public void onPreviewFrame(byte[] data, Camera camera) {
            if(data == null)
                throw new NullPointerException();

            Camera.Size size = camera.getParameters().getPreviewSize();
            if(size == null)
                throw new NullPointerException();

            if(!processing.compareAndSet(false, true))
                return;

            int width = size.width;
            int height = size.height;

            int imgAvgRed = ImageProcessing.decodeYUV420SPtoRedAvg(data.clone(), height, width);
            gx = imgAvgRed;
            mTvPixelAvg.setText("average red signal intensity (0-255): " + String.valueOf(imgAvgRed));
            if(imgAvgRed == 0 || imgAvgRed == 255) {
                processing.set(false);
                return;
            }

            int averageArrayAvg = 0;
            int averageArrayCnt = 0;
            for(int i = 0; i < averageArray.length; i++) {
                if(averageArray[i] > 0) {
                    averageArrayAvg += averageArray[i];
                    averageArrayCnt++;
                }
            }

            int rollingAverage = (averageArrayCnt > 0) ? (averageArrayAvg/averageArrayCnt) : 0;
            TYPE newType = currentType;
            if(imgAvgRed < rollingAverage) {
                newType = TYPE.RED;
                if(newType != currentType) {
                    beats++;
                    flag = 0;
                    mTvBeats.setText("Beats per second: " + String.valueOf(beats));
                }
            } else if (imgAvgRed > rollingAverage) {
                newType = TYPE.GREEN;
            }

            if(averageIndex == averageArraySize)
                averageIndex = 0;

            averageArray[averageIndex] = imgAvgRed;
            averageIndex++;

            if(newType != currentType)
                currentType = newType;

            long endTime = System.currentTimeMillis();
            double totalTimeInSecs = (endTime - startTime) / 1000d;
            if(totalTimeInSecs >= 2) {
                double bps = beats / totalTimeInSecs;
                int bpm = (int)(bps * 60d);
                if(bpm < 30 || bpm > 180 || imgAvgRed < 200) {
                    startTime = System.currentTimeMillis();
                    beats = 0;
                    processing.set(false);
                    return;
                }

                if(beatsIndex == beatsArraySize)
                    beatsIndex = 0;
                beatsArray[beatsIndex] = bpm;
                beatsIndex++;

                int beatsArrayAvg = 0;
                int beatsArrayCnt = 0;
                for(int i = 0; i < beatsArray.length; i++) {
                    if(beatsArray[i] > 0) {
                        beatsArrayAvg += beatsArray[i];
                        beatsArrayCnt++;
                    }
                }
                int beatsAvg = beatsArrayAvg / beatsArrayCnt;
                mTvResultHR.setText("Heart Rate (beats per minute): " + beatsAvg);
                res = beatsAvg;

                startTime = System.currentTimeMillis();
                beats = 0;
            }
            processing.set(false);
        }
    };

    private static SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback() {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                camera.setPreviewDisplay(previewHolder);
                camera.setPreviewCallback(previewCallback);
            } catch (Throwable t) {
                Log.e("PreviewDemo-surfaceCallback","Exception in setPreviewDisplay()", t);
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            Camera.Size size = getSmallestPreviewSize(width, height, parameters);
            if (size != null) {
                parameters.setPreviewSize(size.width, size.height);
            }
            camera.setParameters(parameters);
            camera.startPreview();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
        }
    };

    private static Camera.Size getSmallestPreviewSize(int width, int height, Camera.Parameters parameters) {
        Camera.Size result = null;
        for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
            if (size.width <= width && size.height <= height) {
                if (result == null) {
                    result = size;
                } else {
                    int resultArea = result.width * result.height;
                    int newArea = size.width * size.height;
                    if (newArea < resultArea) {
                        result = size;
                    }
                }
            }
        }
        return result;
    }

    public void returnHeartRate(View view) {
        //resStr = "70";
        resStr = String.valueOf(res);
        resIntent.putExtra(HEARTBEATS, resStr);
        setResult(RESULT_OK, resIntent);
        finish();
        heartRateList = new ArrayList<>();
        reference.child(userId).child("HeartRate").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null) {
                    heartRateList.add(resStr);
                    reference.child(userId).child("HeartRate").setValue(heartRateList);
                } else {
                    GenericTypeIndicator<ArrayList<String>> t =
                            new GenericTypeIndicator<ArrayList<String>>() {
                            };
                    heartRateList = dataSnapshot.getValue(t);
                    heartRateList.add(resStr);
                    reference.child(userId).child("HeartRate").setValue(heartRateList);
                }

            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(HeartRateActivity.this, error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });


    }
}