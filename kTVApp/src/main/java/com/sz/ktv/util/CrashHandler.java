
package com.sz.ktv.util;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Looper;

import com.sz.ktv.R;
import com.sz.ktv.application.MyApplication;
import com.sz.ktv.ui.activity.WelcomeActivity;
import com.sz.ktv.util.writeFile.Write;


/**
 * 全局异常处理: 当程序发生Uncaught异常时,由该类来接管处理
 * 
 * 
 */
public class CrashHandler implements UncaughtExceptionHandler
{
    
    public static final String TAG = "CrashHandler";
    
    // 系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    // CrashHandler实例
    private static CrashHandler instance = new CrashHandler();
    private ExecutorService executors=Executors.newFixedThreadPool(2);
    // 程序的Context对象
    private Context mContext;
    // 用来存储设备信息和异常信息
    
    /*
     * // 用于格式化日期,作为日志文件名的一部分 private DateFormat formatter = new
     */
    /** 获取CrashHandler实例 ,单例模式 */
    public static CrashHandler getInstance()
    {
        return instance;
    }
    
    /**
     * 初始化
     * 
     * @param context
     */
    public void init(Context context)
    {
        mContext = context;
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
    
    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex)
    {
        if (!handleException(ex) && mDefaultHandler != null)
        {
            // 如果用户没有处理则让系统默认的异常处理器来处理
        	StackTraceElement stackTraceElement1= ex.getStackTrace()[0];// 得到异常棧的首个元素
        	Write.debug("File="+stackTraceElement1.getFileName());// 打印文件名
        	Write.debug("Line="+stackTraceElement1.getLineNumber());// 打印出错行号
        	Write.debug("Method="+stackTraceElement1.getMethodName());// 打印出错方法
        	Write.debug("Message = "+ex.getMessage());
        	Write.debug(ex.getMessage());
            mDefaultHandler.uncaughtException(thread, ex);
        }
        else
        {
        	Runnable restartLoginFun =new LoginOutRunnable();
            executors.execute(restartLoginFun);
		      
        }
        
    }
    
    private static class LoginOutRunnable implements Runnable{
		@Override
		public void run() {
		 
//                Intent intent = new Intent(MyApplication.getInstance(), WelcomeActivity.class);  
//                PendingIntent restartIntent = PendingIntent.getActivity(    
//                        MyApplication.getInstance(), 0, intent,Intent.FLAG_ACTIVITY_NEW_TASK);   
//               
//                //退出程序                                          
//                AlarmManager mgr = (AlarmManager)MyApplication.getInstance().getSystemService(Context.ALARM_SERVICE);    
//                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 10,    
//                        restartIntent); // 1秒钟后重启应用   
                android.os.Process.killProcess(android.os.Process.myPid());  
                System.exit(0); 
                
		 
		}
    	
    }
    
    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     * 
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex)
    {
        if (ex == null)
        {
            return false;
        }
        
        StackTraceElement stackTraceElement4= ex.getStackTrace()[0];// 得到异常棧的首个元素
    	Write.debug("File="+stackTraceElement4.getFileName());// 打印文件名
    	Write.debug("Line="+stackTraceElement4.getLineNumber());// 打印出错行号
    	Write.debug("Method="+stackTraceElement4.getMethodName());// 打印出错方法
    	Write.debug("Message = "+ex.getMessage());
    	Write.debug("StackTraceElement = " + Arrays.toString(ex.getStackTrace()));
    	Write.debug(ex.getMessage());
        // 使用Toast来显示异常信息
    	executors.execute(new Runnable()
        {
            
            @Override
            public void run()
            {
                // TODO Auto-generated method stub
                Looper.prepare();
//                ToastUtils.toastTip(mContext.getResources().getString(R.string.program_unusual_msg));
                Looper.loop();
            }
        });
        return true;
    }
    
    /**
     * 网络是否可用
     * 
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = mgr.getAllNetworkInfo();
        if (info != null)
        {
            for (int i = 0; i < info.length; i++)
            {
                if (info[i].getState() == NetworkInfo.State.CONNECTED)
                {
                    return true;
                }
            }
        }
        return false;
    }
    
}
