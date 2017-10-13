package com.sz.ktv.intf;

import android.view.View;

public interface GridAdatper 
{
	View getView(int index);
	 int getCount();
	 public void update();
}