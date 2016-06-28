package com.zqx.zqxcharts;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zqx on 2015/9/12.
 * sd卡工具
 */
public class SDUtils {

    /**
     * 获取可用的SD卡路径（若SD卡不没有挂载则返回""）
     *
     * @return
     */
    public static String gainSDCardPath() {
        if (isMountedSDCard()) {
            File sdcardDir = Environment.getExternalStorageDirectory();
            if (!sdcardDir.canWrite()) {
                Log.w("sys", "SDCARD can not write !");
            }
            return sdcardDir.getPath();
        }
        return "";
    }
    /**
     * 检查是否已挂载SD卡镜像（是否存在SD卡）
     *
     * @return
     */
    private static boolean isMountedSDCard() {
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            return true;
        } else {
            Log.w("sys", "SDCARD is not MOUNTED !");
            return false;
        }
    }

    /**
     * 将bitmap保存成jpg 指定目录写入文件内容
     * @param filePath 文件路径+文件名
     * @throws IOException
     */
    public static void saveAsJPEG(Bitmap bitmap,String filePath)
            throws IOException {
        FileOutputStream fos = null;

        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100,fos);
            fos.flush();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }
}
