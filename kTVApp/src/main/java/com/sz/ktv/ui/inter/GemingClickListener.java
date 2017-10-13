package com.sz.ktv.ui.inter;

import android.view.View;
import android.widget.TextView;

import com.sz.ktv.db.Song;

public interface GemingClickListener {
	public void gemingSingerClick(Song song,View v);
	public void gemingSongClick(Song song,View v,TextView tips);
	public void gemingSongYuLanClick(Song song);
}
