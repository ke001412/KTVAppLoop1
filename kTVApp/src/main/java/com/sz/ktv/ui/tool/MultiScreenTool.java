
package com.sz.ktv.ui.tool;

import com.sz.ktv.db.DataBase;
import com.sz.ktv.util.Global;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MultiScreenTool
{
    static Activity activity;
    public DisplayMetrics displayMetrics = null;
    public int defaultX = 0;
    public int defaultY = 0;
    public float defaultDensity = 1.5f;
    public float nowDensity = 0;
    private static MultiScreenTool instanceVertical = null;
    private static MultiScreenTool instanceHorizontal = null;
    private static int defaultWidth = 480;
    private static int defaultHeihgt = 854;
    
    private int tagId;
    private String debugId;
    
    public float getNowDensity()
    {
        return nowDensity;
    }
    
    public void setNowDensity(float nowDensity)
    {
        this.nowDensity = nowDensity;
    }
    
    public String getDebugId()
    {
        return debugId;
    }
    
    public void setDebugId(View view)
    {
        this.debugId = this.getViewCode(view);
    }
    
    /**
     * 此函数在系统启动的第一个Activity的OnCreate中调用
     * 
     * @param act
     */
    public MultiScreenTool ( int width, int height, float nowDensity )
    {
        defaultX = width;
        defaultY = height;
        this.nowDensity = nowDensity;
    }
    
    /**
     * 对此工具进行初始化，使用singleTonVertical或者singleTonHorizontal进行获取实例前，必须调用此函数一次
     * 
     * @param act
     */
    public static void init(Activity act)
    {
        DisplayMetrics dm = new DisplayMetrics();
        DisplayMetrics dm2 = new DisplayMetrics();
        if (act == null)
        {
            // 偶然异常使得系统娶不到activity，则使用480*854的数据
            dm.heightPixels = 854;
            dm.widthPixels = 480;
            dm.xdpi = 240f;
            dm.ydpi = 240f;
            dm.density = 1.5f;
            return;
        }
        else
        {
            activity = act;
            activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        }
        // 横屏、竖屏的x、y坐标颠倒
        dm2.heightPixels = dm.widthPixels;
        dm2.widthPixels = dm.heightPixels;
        dm2.xdpi = dm.ydpi;
        dm2.ydpi = dm.xdpi;
        dm2.density = dm.density;
        if (dm.widthPixels < dm.heightPixels)
        {
            float rate = dm.widthPixels / (dm.heightPixels * 1.0f);
            if (rate >= 0.6f && rate < 0.625)
            {
                defaultWidth = 480;
                defaultHeihgt = 800;
            }
            else if (rate >= 0.625)
            {
                defaultWidth = 480;
                defaultHeihgt = 768;
            }
        }
        else
        {
            float rate = dm.heightPixels / (float) dm.widthPixels;
            if (rate >= 0.6f && rate < 0.625)
            {
                defaultWidth = 480;
                defaultHeihgt = 800;
            }
            else if (rate >= 0.625)
            {
                defaultWidth = 480;
                defaultHeihgt = 768;
            }
        }
        instanceVertical = new MultiScreenTool(defaultWidth, defaultHeihgt, dm.density);
        instanceHorizontal = new MultiScreenTool(defaultHeihgt, defaultWidth, dm.density);
        if (dm.widthPixels < dm.heightPixels)
        {
            // 当前竖屏
            instanceVertical.displayMetrics = dm;
            instanceHorizontal.displayMetrics = dm2;
        }
        else
        {
            // 横屏
            instanceHorizontal.displayMetrics = dm;
            instanceVertical.displayMetrics = dm2;
        }
        instanceVertical.tagId = act.getResources().getIdentifier("view_tag_id", "id", act.getPackageName());
        instanceHorizontal.tagId = act.getResources().getIdentifier("view_tag_id", "id", act.getPackageName());
    }
    
    /**
     * 其他地方不要使用new,而是调用此函数来获取实例
     * 
     * @return
     */
    public static MultiScreenTool singleTonVertical()
    {
        /*
         */
        if (instanceVertical == null)
        {
            MultiScreenTool.init(Global.currentActivity);
        }
        return instanceVertical;
    }
    
    /**
     * 其他地方不要使用new,而是调用此函数来获取实例
     * 
     * @return
     */
    public static MultiScreenTool singleTonHolizontal()
    {
        /*
         */
        if (instanceHorizontal == null)
        {
            MultiScreenTool.init(Global.currentActivity);
        }
        return instanceHorizontal;
    }
    
    /**
     * 取得屏幕X方向的像素值px
     * 
     * @return
     */
    public int getScreenXDp()
    {
        return displayMetrics.widthPixels;
    }
    
    /**
     * 取屏幕Y方向的像素值px
     * 
     * @return
     */
    public int getScreenYDp()
    {
        return displayMetrics.heightPixels;
    }
    
    /**
     * 输入一个X方向的数值，返回一个经过调整、适应多屏幕的px值
     * 
     * @param xInPx X方向的px值
     * @return 适应多屏幕的像素值px
     */
    public int adjustX(int xInPx)
    {
        int ret = (int) (xInPx * displayMetrics.widthPixels / (float) this.defaultX * (float) this.defaultDensity
                / (float) this.nowDensity + 0.5f);
        if (ret > displayMetrics.widthPixels)
        {
            ret = displayMetrics.widthPixels;
        }
        return ret;
    }
    
    /**
     * 输入一个X方向的数值，返回一个经过调整、适应多屏幕的px值
     * 
     * @param xInPx X方向的px值
     * @return 适应多屏幕的像素值px
     */
    public float adjustXInFloat(float xInPx)
    {
        float ret = xInPx * displayMetrics.widthPixels / this.defaultX * this.defaultDensity / this.nowDensity + 0.5f;
        if (ret > displayMetrics.widthPixels)
        {
            ret = displayMetrics.widthPixels;
        }
        return ret;
    }
    
    /**
     * 输入一个X方向的数值，返回一个经过调整、适应多屏幕的px值,用来适配文字大小
     * 
     * @param xInPx X方向的px值
     * @return 适应多屏幕的像素值px
     */
    public float adjustTextInFloat(float xInPx)
    {
        xInPx = xInPx * nowDensity;
        float ret = xInPx * displayMetrics.widthPixels / this.defaultX * this.defaultDensity / this.nowDensity + 0.5f;
        if (ret > displayMetrics.widthPixels)
        {
            ret = displayMetrics.widthPixels;
        }
        return ret;
    }
    
    /**
     * 输入一个Y方向的数值，返回一个经过调整、适应多屏幕的px值
     * 
     * @param yInPx Y方向的px值
     * @return 适应多屏幕的像素值px
     */
    public int adjustY(int yInPx)
    {
        int ret = (int) (yInPx * displayMetrics.heightPixels / (float) this.defaultY * (float) this.defaultDensity
                / (float) this.nowDensity + 0.5f);
        if (ret > displayMetrics.heightPixels)
        {
            ret = displayMetrics.heightPixels;
        }
        return ret;
    }
    
    /**
     * 输入一个X方向的数值，返回一个经过调整、适应多屏幕的px值
     * 
     * @param xInPx X方向的px值
     * @return 适应多屏幕的像素值px
     */
    public int adjustXIgnoreDensity(int xInPx)
    {
        int ret = (int) (xInPx * displayMetrics.widthPixels / this.defaultX);
        if (ret > displayMetrics.widthPixels)
        {
            ret = displayMetrics.widthPixels;
        }
        return ret;
    }
    
    /**
     * 输入一个Y方向的数值，返回一个经过调整、适应多屏幕的px值
     * 
     * @param yInPx Y方向的px值
     * @return 适应多屏幕的像素值px
     */
    public int adjustYIgnoreDensity(int yInPx)
    {
        int ret = (int) (yInPx * displayMetrics.heightPixels / this.defaultY);
        if (ret > displayMetrics.heightPixels)
        {
            ret = displayMetrics.heightPixels;
        }
        return ret;
    }
    
    public void adjustView(View view)
    {
        if (view == null || view.getLayoutParams() == null)
        {
            return;
        }
        if (view instanceof ViewGroup)
        {
            ViewGroup vg = (ViewGroup) view;
            for (int i = 0; i < vg.getChildCount(); ++i)
            {
                adjustView(vg.getChildAt(i));
            }
            // 所有可能有adapter的
            
            vg.setOnHierarchyChangeListener(new HierarchyChangeListener(this));
            
        }
        if (view.getTag(tagId) != null)
        {
            return;
        }
        else
        {
            view.setTag(tagId, true);
        }
        int tmp = 0;
        // 调整layout 参数
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)
        {
            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            if (lp.height > 0)
            {
                lp.height = this.adjustY((int) (lp.height));
            }
            else if (lp.height == RelativeLayout.LayoutParams.WRAP_CONTENT)
            {
                tmp = view.getMeasuredHeight();
                if (tmp != 0)
                {
                    lp.height = this.adjustY(tmp);
                }
            }
            if (lp.width > 0)
            {
                lp.width = this.adjustX((int) (lp.width));
            }
            else if (lp.width == RelativeLayout.LayoutParams.WRAP_CONTENT)
            {
                tmp = view.getMeasuredWidth();
                if (tmp != 0)
                {
                    lp.width = this.adjustX(tmp);
                }
            }
            lp.leftMargin = this.adjustX((int) (lp.leftMargin));
            lp.topMargin = this.adjustY((int) (lp.topMargin));
            lp.bottomMargin = this.adjustY((int) (lp.bottomMargin));
            lp.rightMargin = this.adjustX((int) (lp.rightMargin));
            view.setLayoutParams(lp);
            // 调整padding参数
            view.setPadding(this.adjustX(view.getPaddingLeft()), this.adjustY(view.getPaddingTop()),
                    this.adjustX(view.getPaddingRight()), this.adjustY(view.getPaddingBottom()));
        }
        else if (view.getLayoutParams() instanceof ViewGroup.LayoutParams)
        {
            ViewGroup.LayoutParams lp1 = (ViewGroup.LayoutParams) view.getLayoutParams();
            if (lp1.width > 0)
            {
                lp1.width = this.adjustX((int) (lp1.width));
            }
            else if (lp1.width == RelativeLayout.LayoutParams.WRAP_CONTENT)
            {
                tmp = view.getMeasuredWidth();
                if (tmp != 0)
                {
                    lp1.width = this.adjustX(tmp);
                }
            }
            
            if (lp1.height > 0)
            {
                lp1.height = this.adjustY((int) (lp1.height));
            }
            else if (lp1.height == RelativeLayout.LayoutParams.WRAP_CONTENT)
            {
                tmp = view.getMeasuredHeight();
                if (tmp != 0)
                {
                    lp1.height = this.adjustY(tmp);
                }
            }
            view.setLayoutParams(lp1);
            // 调整padding参数
            view.setPadding(this.adjustX(view.getPaddingLeft()), this.adjustY(view.getPaddingTop()),
                    this.adjustX(view.getPaddingRight()), this.adjustY(view.getPaddingBottom()));
        }
        else
        {
        }
        // // 调整字体大小
        if (!(view.getParent() instanceof android.widget.NumberPicker) && view instanceof android.widget.TextView)
        {
            android.widget.TextView tv = (android.widget.TextView) view;
            float textSizeInPx = tv.getTextSize();
            textSizeInPx = this.adjustXInFloat(textSizeInPx);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeInPx);
        }
    }
    
    
    /**
     * 将某个view及下面的所有子view的登记信息从登记表中清除，后面窗口重新初始化时才能调整
     * 
     * @param view
     */
    public void unRegisterView(View view)
    {
        
    }
    
    private String getViewCode(View view)
    {
        String code = view.hashCode() + "_" + view.getId();
        return code;
    }
    
    /**
     * 调整ImageView，这张ImageView中一般取不到图片的宽和高，所以使用adjustView无法调整
     * 
     * @param view
     */
    public ViewGroup.LayoutParams getAdjustLayoutParamsForImageView(ImageView view)
    {
        if (view.getTag(tagId) != null)
        {
            return view.getLayoutParams();
        }
        else
        {
            view.setTag(tagId, true);
        }
        int bgWidth = view.getBackground().getMinimumWidth();
        bgWidth = this.adjustXIgnoreDensity(bgWidth);
        int bgHight = view.getBackground().getMinimumHeight();
        bgHight = this.adjustYIgnoreDensity(bgHight);
        ViewGroup.LayoutParams rlp = new ViewGroup.LayoutParams(bgWidth, bgHight);
        return rlp;
    }
    
    /**
     * 检查屏幕尺寸是否变化（有些平板，横竖转换转换后其高宽和原来不一样）
     * 
     * @param act 调用的activity
     */
    public void checkWidthAndHeight()
    {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        if (dm.widthPixels > dm.heightPixels)
        {
            if (this.displayMetrics.widthPixels > this.displayMetrics.heightPixels)
            {
                this.displayMetrics.widthPixels = dm.widthPixels;
                this.displayMetrics.heightPixels = dm.heightPixels;
            }
            else
            {
                this.displayMetrics.widthPixels = dm.heightPixels;
                this.displayMetrics.heightPixels = dm.widthPixels;
            }
        }
        else
        {
            if (this.displayMetrics.widthPixels > this.displayMetrics.heightPixels)
            {
                this.displayMetrics.widthPixels = dm.heightPixels;
                this.displayMetrics.heightPixels = dm.widthPixels;
            }
            else
            {
                this.displayMetrics.widthPixels = dm.widthPixels;
                this.displayMetrics.heightPixels = dm.heightPixels;
            }
        }
    }
}
