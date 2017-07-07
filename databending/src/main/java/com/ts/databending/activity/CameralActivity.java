package com.ts.databending.activity;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ts.databending.R;
//新的摄像机程序调用
public class CameralActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cameral);

    }
    private void camera() {
        CameraManager manager =
                (CameraManager) getSystemService(CAMERA_SERVICE);
        try {
            for (String cameraId : manager.getCameraIdList()) {
                CameraCharacteristics chars
                        = manager.getCameraCharacteristics(cameraId);
                // Do something with the characteristics
                Integer facing = chars.get(CameraCharacteristics.LENS_FACING);

            }
            } catch(CameraAccessException e){
                e.printStackTrace();
            }

    }
}
