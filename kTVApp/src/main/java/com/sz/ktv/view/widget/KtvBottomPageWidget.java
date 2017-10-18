package com.sz.ktv.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sz.ktv.R;

public class KtvBottomPageWidget extends RelativeLayout {


    private Context mContext;
    private LayoutInflater mInflater;
    private ImageView fanhui;
    private TextView showPage;
    private ImageView left;
    private ImageView right;
    public static final int TYPE_DOTS = 1;
    public static final int TYPE_NO_DOTS = 2;
    public static final int TYPE_NO_DOTS_NO_PAGENUM = 3;

    BackClickListener listener;

    public void setBackClickListener(BackClickListener mListener) {
        this.listener = mListener;

    }

    public interface BackClickListener {
        public void back();
    }

    public KtvBottomPageWidget(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mContext = context;

        initView();
    }

    public KtvBottomPageWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public KtvBottomPageWidget(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    private void initView() {
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater.inflate(R.layout.ktv_page_dots, this);

        fanhui = (ImageView) findViewById(R.id.ktv_fanhui);
        showPage = (TextView) findViewById(R.id.ktv_show_page);

        left = (ImageView) findViewById(R.id.ktv_left);
        right = (ImageView) findViewById(R.id.ktv_right);

        fanhui.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.back();
                }
            }
        });

    }

    private int allPage = 0;
    private int type = -1;
    private int currentPage;

    public void setAllPage(int all) {
        allPage = all;
    }

    public void setCurrentPage(int current) {
        currentPage = current;
        showPage.setText(currentPage + "/" + allPage);
    }

    public void setType(int mtype) {
        type = mtype;
        if (type == TYPE_DOTS) {
            left.setVisibility(View.VISIBLE);
            right.setVisibility(View.VISIBLE);
            showPage.setVisibility(View.VISIBLE);

        } else if (type == TYPE_NO_DOTS) {
            left.setVisibility(View.GONE);
            right.setVisibility(View.GONE);
            showPage.setVisibility(View.VISIBLE);

        } else if (type == TYPE_NO_DOTS_NO_PAGENUM) {
            left.setVisibility(View.GONE);
            right.setVisibility(View.GONE);
            showPage.setVisibility(View.GONE);

        }

    }

}
