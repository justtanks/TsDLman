package com.daili.tsapp.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Build;
import android.util.Log;
import android.view.SurfaceHolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraManager {

    private Camera mCamera;
    private SurfaceHolder mHolder;

    public CameraManager(Camera camera, SurfaceHolder holder) {
        mCamera = camera;
        mHolder = holder;

    }

    public Camera getCamera() {
        return mCamera;
    }

    /**
     * 打开相机
     *
     * @param tagInfo 摄像头信息，分为前置/后置摄像头 Camera.CameraInfo.CAMERA_FACING_FRONT：前置
     *                Camera.CameraInfo.CAMERA_FACING_BACK：后置
     * @return 是否成功打开某个摄像头
     */
    @SuppressLint("NewApi")
    public boolean openCamera(int tagInfo) {
        // 尝试开启摄像头
        try {
            mCamera = Camera.open(getCameraId(tagInfo));
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
        // 开启前置失败
        if (mCamera == null) {
            return false;
        }
        // 将摄像头中的图像展示到holder中
        try {
            // 这里的myCamera为已经初始化的Camera对象
            mCamera.setPreviewDisplay(mHolder);
        } catch (IOException e) {
            e.printStackTrace();
            // 如果出错立刻进行处理，停止预览照片
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
        // 如果成功开始实时预览
        mCamera.startPreview();
        return true;
    }

    /**
     * @return 前置摄像头的ID
     */
    public int getFrontCameraId() {
        return getCameraId(Camera.CameraInfo.CAMERA_FACING_FRONT);
    }

    /**
     * @return 后置摄像头的ID
     */
    public int getBackCameraId() {
        return getCameraId(Camera.CameraInfo.CAMERA_FACING_BACK);
    }

    /**
     * @param tagInfo
     * @return 得到特定camera info的id
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    private int getCameraId(int tagInfo) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        // 开始遍历摄像头，得到camera info
        int cameraId, cameraCount;
        for (cameraId = 0, cameraCount = Camera.getNumberOfCameras(); cameraId < cameraCount; cameraId++) {
            Camera.getCameraInfo(cameraId, cameraInfo);

            if (cameraInfo.facing == tagInfo) {
                break;
            }
        }
        return cameraId;
    }

    /**
     * 定义图片保存的路径和图片的名字
     */
    public final static String PHOTO_PATH = "mnt/sdcard/CAMERA_DEMO/Camera/";

    public static String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("'LOCK'_yyyyMMdd_HHmmss");
        return dateFormat.format(date) + ".jpg";
    }

    /**
     * 拍照成功回调
     */
    public class PicCallback implements PictureCallback {
        private String TAG = getClass().getSimpleName();
        private Camera mCamera;

        public PicCallback(Camera camera) {
            // TODO 自动生成的构造函数存根
            mCamera = camera;
        }

        /*
         * 将拍照得到的字节转为bitmap，然后旋转，接着写入SD卡
         * @param data
         * @param camera
         */
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            // 将得到的照片进行270°旋转，使其竖直
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            Matrix matrix = new Matrix();
            matrix.preRotate(270);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            // 创建并保存图片文件
            File mFile = new File(PHOTO_PATH);
            if (!mFile.exists()) {
                mFile.mkdirs();
            }
            File pictureFile = new File(PHOTO_PATH, getPhotoFileName());
            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                bitmap.recycle();
                fos.close();
                Log.i(TAG, "拍摄成功！");
            } catch (Exception error) {
                Log.e(TAG, "拍摄失败");
                error.printStackTrace();
            }
        }

    }

}
