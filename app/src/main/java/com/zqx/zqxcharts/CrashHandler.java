package com.zqx.zqxcharts;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrashHandler implements UncaughtExceptionHandler {
	private static final String TAG = "CrashHandler";
	private static final boolean DEBUG = true;

	private static String PATH = SDUtils.gainSDCardPath() + "/zchart/crash/";
	private static final String FILE_NAME = "crash";

	// log文件的后缀名
	private static final String FILE_NAME_SUFFIX = ".trace";

	private static CrashHandler sInstance = new CrashHandler();

	// 系统默认的异常处理（默认情况下，系统会终止当前的异常程序）
	private UncaughtExceptionHandler mDefaultCrashHandler;

	private Context mContext;
	private Throwable currEx = new Throwable();

	// 构造方法私有，防止外部构造多个实例，即采用单例模式
	private CrashHandler() {
	}

	public static CrashHandler getInstance() {
		return sInstance;
	}

	// 这里主要完成初始化工作
	public void init(Context context) {
		// 获取系统默认的异常处理器
		mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
		// 将当前实例设为系统默认的异常处理器
		Thread.setDefaultUncaughtExceptionHandler(this);
		// 获取Context，方便内部使用
		mContext = context.getApplicationContext();
	}

	/**
	 * 这个是最关键的函数，当程序中有未被捕获的异常，系统将会自动调用#uncaughtException方法
	 * thread为出现未捕获异常的线程，ex为未捕获的异常，有了这个ex，我们就可以得到异常信息。
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		// 如果系统提供了默认的异常处理器，则交给系统去结束我们的程序，否则就由我们自己结束自己
		//try {
			// 导出异常信息到SD卡中
			//dumpExceptionToSDCard(ex);
            //这里可以通过网络上传异常信息到服务器，便于开发人员分析日志从而解决bug
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}
		
		//Intent intent = new Intent(mContext,SplashActivity.class);
		// 参数1：包名，参数2：程序入口的activity
//		PendingIntent restartIntent = PendingIntent.getActivity(mContext, 0,
//				intent, Intent.FLAG_ACTIVITY_NEW_TASK);
//		AlarmManager mgr = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
//        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,
//                 restartIntent); // 1秒钟后重启应用
        android.os.Process.killProcess(android.os.Process.myPid());
 		System.exit(1);
        //AppManager.getAppManager().finishAllActivity();
	}

	/**
	 * 将异常信息写入sd卡
	 * 
	 * @author zqx
	 * @date 2015年11月10日 上午10:38:31
	 * @Description: TODO
	 * @param ex
	 * @throws IOException
	 */
	private void dumpExceptionToSDCard(Throwable ex) throws IOException {

		// 如果SD卡不存在或无法使用，则无法把异常信息写入SD卡
		if (SDUtils.gainSDCardPath().equals("")) {
			if (DEBUG) {
				Log.w(TAG, "sdcard unmounted,skip dump exception");
			}
			PATH = "/data/data/com.tlkj.quanminhuoyun/crash/";
		}
		File dir = new File(PATH);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		long current = System.currentTimeMillis();
		String time = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")
				.format(new Date(current));
		// 以当前时间创建log文件
		File file = new File(PATH + FILE_NAME + time + FILE_NAME_SUFFIX);

		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
					file)));
			// 导出发生异常的时间
			pw.println(time);
			// 导出手机信息
			dumpPhoneInfo(pw);
			pw.println();
			// 导出异常的调用栈信息
			ex.printStackTrace(pw);

			pw.close();
		} catch (Exception e) {
			Log.e(TAG, "dump crash info failed");
		}
	}

	/**
	 * 获取应用的版本名称和版本号
	 * 
	 * @author zqx
	 * @date 2015年11月10日 下午12:24:18
	 * @Description: TODO
	 * @param pw
	 * @throws NameNotFoundException
	 */
	private void dumpPhoneInfo(PrintWriter pw) throws NameNotFoundException {
		PackageManager pm = mContext.getPackageManager();
		PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(),
				PackageManager.GET_ACTIVITIES);
		pw.print("App Version: ");
		pw.print(pi.versionName);
		pw.print('_');
		pw.println(pi.versionCode);

		// android版本号
		pw.print("OS Version: ");
		pw.print(Build.VERSION.RELEASE);
		pw.print("_");
		pw.println(Build.VERSION.SDK_INT);

		// 手机制造商
		pw.print("Vendor: ");
		pw.println(Build.MANUFACTURER);

		// 手机型号
		pw.print("Model: ");
		pw.println(Build.MODEL);

		// cpu架构
		pw.print("CPU ABI: ");
		pw.println(Build.CPU_ABI);
	}
}
