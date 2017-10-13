package com.hisilicon.android.videoplayer.util;

import java.io.File;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnDismissListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.WindowManager;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class Common
{
    private static String TAG = "Common";
    private Context context;
    private Activity activity;
    private int screenWidth;

    private boolean isPositive = false;

    private int hour = 0;

    private int minute = 0;

    public int limitHour = 0;

    public int limitMinute = 0;

    public int step = 10;

    public int init_step = 10;

    public int acc_step = 10;

    private int mode = 0;

    private int subtitleEncode = 0;


    private static int duration = 0;

    public static boolean isScanDialogShow = false;

    public static boolean isCloseSubForever = false;

    public static boolean isResume = false;

    public static boolean isLastMediaFile = false;

    public static boolean isLoadSuccess = false;

    public static boolean haveShowNoFileToast = false;

    public static boolean isShowLoadingToast = true;

    public static int sortCount = -1;

    public boolean isPositive()
    {
        return isPositive;
    }

    public void setPositive(boolean isPositive)
    {
        this.isPositive = isPositive;
    }

    public static int getDuration()
    {
        return duration;
    }

    public static void setDuration(int duration)
    {
        Common.duration = duration;
    }

    public int getMode()
    {
        return mode;
    }

    public void setMode(int mode)
    {
        this.mode = mode;
    }

    public int getSubtitleEncode()
    {
        return subtitleEncode;
    }

    public void setSubtitleEncode(int encode)
    {
        this.subtitleEncode = encode;
    }
 
    public int getStep()
    {
        return step * 1000;
    }

    public void setStep(int step)
    {
        this.step = step;
    }

    public int getInitStep()
    {
        return init_step * 1000;
    }

    public void setInitStep(int init_step)
    {
        this.init_step = init_step;
    }

    public int getAccStep()
    {
        return acc_step * 1000;
    }

    public void setAccStep(int acc_step)
    {
        this.acc_step = acc_step;
    }

    public static boolean isLoadSuccess()
    {
        return isLoadSuccess;
    }

    public static void setLoadSuccess(boolean isLoadSuccess)
    {
        Common.isLoadSuccess = isLoadSuccess;
    }

    public Common(Context context, Activity activity, int screenWidth)
    {
        this.context  = context;
        this.activity = activity;
        this.screenWidth = screenWidth;
    }
 
    public void switchPlayModel(int model)
    {
        switch (model)
        {
        case Constants.ALLNOCYCLE:
            mode = Constants.ALLNOCYCLE;
            break;
        case Constants.ALLCYCLE:
            mode = Constants.ALLCYCLE;
            break;
        case Constants.ONECYCLE:
            mode = Constants.ONECYCLE;
            break;
        case Constants.ONENOCYCLE:
            mode = Constants.ONENOCYCLE;
            break;
        case Constants.RANDOM:
            mode = Constants.RANDOM;
            break;
        default:
            break;
        }
    }

    public int ifContinue()
    {
        return 1;
    }

    public class MarkComparator implements Comparator < HashMap < String, Object >>
    {
        public int compare(HashMap < String, Object > object1, HashMap <String, Object> object2)
        {
            HashMap <String, Object> m1 = object1;
            HashMap <String, Object> m2 = object2;
            int i1 = Integer.parseInt(m1.get("mark").toString());
            int i2 = Integer.parseInt(m2.get("mark").toString());
            if (i1 >= i2)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
    }

     

    public static View setDialogTitleAndListView(Context context, View contentView, int title)
    {
        LinearLayout parentLinear = new LinearLayout(context);

        parentLinear.setOrientation(LinearLayout.VERTICAL);
        if (title != -1)
        {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(android.R.drawable.ic_menu_more);
            imageView.setPadding(10, 10, 4, 10);

            TextView textView = new TextView(context);
            textView.setTextSize(22);
            textView.setTextColor(Color.WHITE);
            textView.setText(title);
            textView.setPadding(4, 17, 10, 10);

            LinearLayout childLinear = new LinearLayout(context);
            childLinear.addView(imageView);
            childLinear.addView(textView);

            ImageView view = new ImageView(context);
            view.setBackgroundColor(Color.WHITE);
            view.setMaxHeight(1);
            view.setMinimumHeight(1);

            parentLinear.addView(childLinear);
            parentLinear.addView(view);
        }

        parentLinear.addView(contentView);

        return parentLinear;
    }

    public static void setDialogWidth(Dialog dialog, int screenWidth, int x, int y)
    {
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = screenWidth / 2;
        params.x = x;
        params.y = y;
        dialog.getWindow().setAttributes(params);
    }

    public void setDialog(Dialog dialog, View content, int x, int y, int rate)
    {
        dialog.setContentView(content);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        if (rate != -1)
        {
            params.width = screenWidth / rate;
        }

        params.x = x;
        params.y = y;
        dialog.getWindow().setAttributes(params);
        dialog.show();
    }

    public int sharedPreferencesOpration(String name, String key, int value, int defaultValue, boolean isEdit)
    {
        if (isEdit)
        {
            SharedPreferences.Editor editor = context.getSharedPreferences(name, context.MODE_PRIVATE).edit();
            editor.putInt(key, value);
            editor.commit();
            return 0;
        }
        else
        {
            return context.getSharedPreferences(name, Context.MODE_PRIVATE).getInt(key, defaultValue);
        }
    }

    public static String transferredMeaning(String path)
    {
        if (path.contains("%"))
        {
            path = path.replace("%", "%25");
        }

        if (path.contains("#"))
        {
            path = path.replace("#", "%23");
        }

        return path;
    }

    public static String getTimeFormatValue(long time)
    {
        long t = time / 1000;

        return MessageFormat.format(
                   "{0,number,00}:{1,number,00}:{2,number,00}",
                   t / 60 / 60, t / 60 % 60, t % 60);
    }

    public static String getFormatDate(long date)
    {
        Date d = new Date(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd/kk:mm:ss");

        return sdf.format(d);
    }
 
 
 
    private static int compareBySize(long object1, long object2)
    {
        long diff = object1 - object2;

        if (diff > 0)
        {
            return 1;
        }
        else if (diff == 0)
        {
            return 0;
        }
        else
        {
            return -1;
        }
    }

    public static int compareByDate(long object1, long object2)
    {
        long diff = object1 - object2;

        if (diff > 0)
        {
            return 1;
        }
        else if (diff == 0)
        {
            return 0;
        }
        else
        {
            return -1;
        }
    }
}
