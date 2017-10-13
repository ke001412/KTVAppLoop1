package com.sz.ktv.view;

import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.View.MeasureSpec.makeMeasureSpec;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.sz.ktv.intf.GridAdatper;



/**
 * @version 1.0
 */
public class MySingerGridLayout extends ViewGroup {
	private final String TAG = "MyGridLayout";

	int margin = 10;// 每个格子的水平和垂直间隔
	int colums = 3;
	private int mMaxChildWidth = 0;
	private int mMaxChildHeight = 0;
	int count = 0;

	GridAdatper adapter;

	public MySingerGridLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		if (attrs != null) {
//			TypedArray a = getContext().obtainStyledAttributes(attrs,
//					R.styleable.MyGridLayout);
			colums = 3;//a.getInteger(R.styleable.MyGridLayout_numColumns, 2);
			margin =10;// (int) a.getInteger(R.styleable.MyGridLayout_itemMargin, 2);
		}
	}

	public MySingerGridLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MySingerGridLayout(Context context) {
		this(context, null);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		mMaxChildWidth = 0;
		mMaxChildHeight = 0;

		int modeW = 0, modeH = 0;
		if (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.UNSPECIFIED)
			modeW = MeasureSpec.UNSPECIFIED;
		if (MeasureSpec.getMode(heightMeasureSpec) != MeasureSpec.UNSPECIFIED)
			modeH = MeasureSpec.UNSPECIFIED;

		final int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
				MeasureSpec.getSize(widthMeasureSpec), modeW);
		final int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
				MeasureSpec.getSize(heightMeasureSpec), modeH);

		count = getChildCount();
		if (count == 0) {
			super.onMeasure(childWidthMeasureSpec, childHeightMeasureSpec);
			return;
		}
		for (int i = 0; i < count; i++) {
			final View child = getChildAt(i);
			if (child.getVisibility() == GONE) {
				continue;
			}

			child.measure(childWidthMeasureSpec, childHeightMeasureSpec);

			mMaxChildWidth = Math.max(mMaxChildWidth, child.getMeasuredWidth());
			mMaxChildHeight = Math.max(mMaxChildHeight,
					child.getMeasuredHeight());
		}
		setMeasuredDimension(resolveSize(mMaxChildWidth, widthMeasureSpec),
				resolveSize(mMaxChildHeight, heightMeasureSpec));
	}

	public void setRows(int row)
	{
		this.mRows = row;
	}
	
	public void setColums(int colum)
	{
		this.colums = colum;
	}
	
	
	private int mRows = 0;
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		int height = b - t;// 布局区域高度
		int width = r - l;// 布局区域宽度
		int rows = mRows ;//count % colums == 0 ? count / colums : count / colums + 1;// 行数
		if (count == 0)
			return;
		int gridW = (width - margin * (colums - 1)) / colums;// 格子宽度
		int gridH = (height - margin * rows) / rows;// 格子高度

		int left = 10;
		int top = margin;

		for (int i = 0; i < rows; i++) {// 遍历�?
			for (int j = 0; j < colums; j++) {// 遍历每一行的元素
				View child = this.getChildAt(i * colums + j);
				if (child == null)
					return;
				left = j * gridW + j * margin;
				// 如果当前布局宽度和测量宽度不�?样，就直接用当前布局的宽度重新测�?
				if (gridW != child.getMeasuredWidth()
						|| gridH != child.getMeasuredHeight()) {
					child.measure(makeMeasureSpec(gridW, EXACTLY),
							makeMeasureSpec(gridH, EXACTLY));
				}
				child.layout(left+margin, top, left + gridW-margin, top + gridH+margin);
				// System.out
				// .println("--top--" + top + ",bottom=" + (top + gridH));

			}
			top += gridH + margin;
		}
	}

//	public interface GridAdatper {
//		View getView(int index);
//
//		int getCount();
//	}

	/** 设置适配�? */
	public void setGridAdapter(GridAdatper adapter) {
		removeAllViews();
		this.adapter = adapter;
		// 动�?�添加视�?
		int size = adapter.getCount();
		for (int i = 0; i < size; i++) {
			addView(adapter.getView(i));
		}
		invalidate();
	}
	
	public GridAdatper getGridAdapter(){
		return this.adapter;
	}
	public void reLoadView(){
		removeAllViews();
		int size = adapter.getCount();
		for (int i = 0; i < size; i++) {
			addView(adapter.getView(i));
		}
		invalidate();
	}

	public interface OnItemClickListener {
		void onItemClick(View v, int index);
	}

	public void setOnItemClickListener(final OnItemClickListener click) {
		if (this.adapter == null)
			return;
		for (int i = 0; i < adapter.getCount(); i++) {
			final int index = i;
			View view = getChildAt(i);
			view.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					 
					click.onItemClick(v, index);
				}
			});
		}
	}

}
