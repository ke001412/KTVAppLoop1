package com.hisilicon.android.videoplayer.view;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.PixelFormat;
import android.media.AudioManager;

import com.hisilicon.android.mediaplayer.HiMediaPlayer;
import com.hisilicon.android.mediaplayer.HiMediaPlayer.OnCompletionListener;
import com.hisilicon.android.mediaplayer.HiMediaPlayer.OnErrorListener;
import com.hisilicon.android.mediaplayer.HiMediaPlayer.OnFastBackwordCompleteListener;
import com.hisilicon.android.mediaplayer.HiMediaPlayer.OnInfoListener;
import com.hisilicon.android.mediaplayer.HiMediaPlayer.OnPreparedListener;
import com.hisilicon.android.videoplayer.util.Common;
import com.hisilicon.android.videoplayer.util.Constants;

import android.net.Uri;
import android.os.Parcel;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;

public class HisiVideoView extends SurfaceView implements MediaPlayerControl
{
    private String TAG = "VideoView";

    private Context mContext;

    private Uri mUri;
    private int mDuration;

    private SurfaceHolder mSurfaceHolder = null;
    public HiMediaPlayer mMediaPlayer = null;
    private boolean mIsPrepared;
    private int mVideoWidth;
    private int mVideoHeight;
    private int mSurfaceWidth;
    private int mSurfaceHeight;
    private OnCompletionListener mOnCompletionListener;

    private OnFastBackwordCompleteListener mOnFastBackwordCompleteListener;
    private HiMediaPlayer.OnPreparedListener mOnPreparedListener;
    private int mCurrentBufferPercentage;
    private OnErrorListener mOnErrorListener;
    private OnInfoListener mOn3DModeReceivedListener;
    private boolean mStartWhenPrepared;
    private int mSeekWhenPrepared;
    private String ratioValue = null;
    private String cvrsValue = null;
    private static final int CMD_STOP_FASTPLAY = 199;

    private int mSubtitleNumber;

    private int mExtSubtitleNumber;

    private int mAudioTrackNumber;

    private int mSelectSubtitleId = 0;

    private int mSelectAudioTrackId = 0;

    private List <String> mSubtitleLanguageList;

    private List <String> mExtraSubtitleList;

    private List <String> mAudioTrackLanguageList;

    private List <String> mAudioFormatList;

    private List <String> mAudioSampleRateList;

    private List <String> mAudioChannelList;

    public String[] mSubFormat = {"ASS", "LRC", "SRT", "SMI", "SUB", "TXT", "PGS", "DVB", "DVD"};

    public int getVideoWidth()
    {
        return mVideoWidth;
    }

    public int getVideoHeight()
    {
        return mVideoHeight;
    }

    public void setVideoScale(int width, int height)
    {
        LayoutParams lp = getLayoutParams();
         
        lp.width = width;
        mVideoWidth = width;

        lp.height = height;
        mVideoHeight = height;

        setLayoutParams(lp);
    }

    public HisiVideoView(Context context)
    {
        super(context);
        mContext = context;
        initVideoView();
    }

    public HisiVideoView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
        mContext = context;
        initVideoView();
    }

    public HisiVideoView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        mContext = context;
        initVideoView();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int width  = getDefaultSize(mVideoWidth, widthMeasureSpec);
        int height = getDefaultSize(mVideoHeight, heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    public int resolveAdjustedSize(int desiredSize, int measureSpec)
    {
        int result   = desiredSize;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode)
        {
        case MeasureSpec.UNSPECIFIED:

            result = desiredSize;
            break;

        case MeasureSpec.AT_MOST:

            result = Math.min(desiredSize, specSize);
            break;

        case MeasureSpec.EXACTLY:

            result = specSize;
            break;
        }

        return result;
    }

    private void initVideoView()
    {
        mVideoWidth  = 0;
        mVideoHeight = 0;
        getHolder().addCallback(mSHCallback);
        getHolder().setType(SurfaceHolder.SURFACE_TYPE_NORMAL);
        getHolder().setFormat(PixelFormat.RGBA_8888);
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
    }

    public void setVideoPath(String path)
    {
        path = Common.transferredMeaning(path);
        setVideoURI(Uri.parse(path));
    }

    public void setVideoURI(Uri uri)
    {
        mUri = uri;
        mStartWhenPrepared = false;
        mSeekWhenPrepared = 0;
        openVideo();
        requestLayout();
        invalidate();
    }

    public void stopPlayback()
    {
        if (mMediaPlayer != null)
        {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public void intVideoValue()
    {
        String[] cloumns = new String[] {
            "value", "flag"
        };

        String[] args = new String[] {
            "14",
            "15"
        };

        ContentResolver cr = mContext.getContentResolver();
        Uri uri  = Uri.parse(Constants.CONTENT_URI);
        Cursor c = null;
        try
        {
            c = cr.query(uri, cloumns, " id in (?,?) ", args, null);
            if ((c != null) && (c.getCount() > 0))
            {
                while (c.moveToNext())
                {
                    String flag = c.getString(c.getColumnIndex("flag"));
                    if (flag.equals("1"))
                    {
                        ratioValue = c.getString(c.getColumnIndex("value"));
                    }
                    else
                    {
                        cvrsValue = c.getString(c.getColumnIndex("value"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (c != null)
            {
                c.close();
                c = null;
            }
        }
    }

    private void openVideo()
    {
        intVideoValue();

        if ((mUri == null) || (mSurfaceHolder == null))
        {
            return;
        }

        if (mMediaPlayer != null)
        {
            mMediaPlayer.reset();
            Log.i(TAG, "DataSource :" + mUri);
            try
            {
                mIsPrepared = false;
                mDuration = -1;
                mCurrentBufferPercentage = 0;
                mSelectSubtitleId = 0;
                mSelectAudioTrackId = 0;
                mMediaPlayer.setDataSource(mContext, mUri);
                mMediaPlayer.prepareAsync();
            }
            catch (IOException ex) {
                Log.w(TAG, "Unable to open content: " + mUri, ex);
                return;
            }
            catch (IllegalArgumentException ex) {
                Log.w(TAG, "Unable to open content: " + mUri, ex);
                return;
            }
            return;
        }

        try
        {
            mMediaPlayer = new HiMediaPlayer();
            mMediaPlayer.setOnPreparedListener(mPreparedListener);
            mMediaPlayer.setOnVideoSizeChangedListener(mSizeChangedListener);
            mIsPrepared = false;
            mDuration = -1;
            mMediaPlayer.setOnCompletionListener(mCompletionListener);
            mMediaPlayer.setOnFastBackwordCompleteListener(mFastBackwordCompleteListener);
            mMediaPlayer.setOnInfoListener(m3DModeReceivedListener);
            mMediaPlayer.setOnErrorListener(mErrorListener);
            mMediaPlayer.setOnBufferingUpdateListener(mBufferingUpdateListener);
            mCurrentBufferPercentage = 0;
            mSelectSubtitleId = 0;
            mSelectAudioTrackId = 0;
            Log.i(TAG, "DataSource :" + mUri);
            mMediaPlayer.setDataSource(mContext, mUri);
            mSurfaceHolder.setFixedSize(getVideoWidth(), getVideoHeight());
            mMediaPlayer.setDisplay(mSurfaceHolder);
            Surface mSurface;
            mSurface = mSurfaceHolder.getSurface();

            int[] location = new int[2];
            getLocationOnScreen(location);

            int mX, mY, mW, mH;
            if (null != mSurface)
            {
                mX = location[0];
                mY = location[1];
                mW = mVideoWidth;
                mH = mVideoHeight;
                mMediaPlayer.setVideoRange(mX, mY, mW, mH);
            }

            //mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            //mMediaPlayer.setScreenOnWhilePlaying(true);
            mMediaPlayer.prepareAsync();
        } catch (IOException ex) {
            Log.w(TAG, "Unable to open content: " + mUri, ex);
            return;
        } catch (IllegalArgumentException ex) {
            Log.w(TAG, "Unable to open content: " + mUri, ex);
            return;
        }
    }

    HiMediaPlayer.OnVideoSizeChangedListener mSizeChangedListener =
        new HiMediaPlayer.OnVideoSizeChangedListener()
    {
        public void onVideoSizeChanged(HiMediaPlayer mp, int width, int height)
        {
            if ((mVideoWidth != 0) && (mVideoHeight != 0))
            {}
        }
    };

    HiMediaPlayer.OnPreparedListener mPreparedListener = new HiMediaPlayer.OnPreparedListener()
    {
        public void onPrepared(HiMediaPlayer mp)
        {
            mIsPrepared = true;
            if (mOnPreparedListener != null)
            {
                mOnPreparedListener.onPrepared(mMediaPlayer);
                return;
            }

            mVideoWidth  = mp.getVideoWidth();
            mVideoHeight = mp.getVideoHeight();

            if ((mVideoWidth != 0) && (mVideoHeight != 0))
            {
                getHolder().setFixedSize(mVideoWidth, mVideoHeight);

                if (mSeekWhenPrepared != 0)
                {
                    mMediaPlayer.seekTo(mSeekWhenPrepared);
                    mSeekWhenPrepared = 0;
                }

                if (mStartWhenPrepared)
                {
                    mMediaPlayer.start();
                    mStartWhenPrepared = false;
                }
            }
            else
            {
                if (mSeekWhenPrepared != 0)
                {
                    mMediaPlayer.seekTo(mSeekWhenPrepared);
                    mSeekWhenPrepared = 0;
                }

                if (mStartWhenPrepared)
                {
                    mMediaPlayer.start();
                    mStartWhenPrepared = false;
                }
            }
        }
    };

    private HiMediaPlayer.OnCompletionListener mCompletionListener =
        new HiMediaPlayer.OnCompletionListener()
    {
        public void onCompletion(HiMediaPlayer mp)
        {
            if (mOnCompletionListener != null)
            {
                mOnCompletionListener.onCompletion(mMediaPlayer);
            }
        }
    };

    private HiMediaPlayer.OnFastBackwordCompleteListener mFastBackwordCompleteListener =
        new HiMediaPlayer.OnFastBackwordCompleteListener()
    {
        public void onFastBackwordComplete(HiMediaPlayer mp)
        {
            if (mOnFastBackwordCompleteListener != null)
            {
                mOnFastBackwordCompleteListener.onFastBackwordComplete(mMediaPlayer);
            }
        }
    };

    private HiMediaPlayer.OnInfoListener m3DModeReceivedListener =
        new HiMediaPlayer.OnInfoListener()
    {
        public boolean onInfo(HiMediaPlayer mp, int what, int extra)
        {
            if (mOn3DModeReceivedListener != null)
            {
                return mOn3DModeReceivedListener.onInfo(mMediaPlayer, what, extra);
            }

            return false;
        }
    };

    private HiMediaPlayer.OnErrorListener mErrorListener =
        new HiMediaPlayer.OnErrorListener()
    {
        public boolean onError(HiMediaPlayer mp, int framework_err, int impl_err)
        {
            Log.d(TAG, "Error: " + framework_err + "," + impl_err);

            if (mOnErrorListener != null)
            {
                if (mOnErrorListener.onError(mMediaPlayer, framework_err, impl_err))
                {
                    return true;
                }
            }

            if (getWindowToken() != null)
            {}

            return true;
        }
    };

    private HiMediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener =
        new HiMediaPlayer.OnBufferingUpdateListener()
    {
        public void onBufferingUpdate(HiMediaPlayer mp, int percent)
        {
            mCurrentBufferPercentage = percent;
        }
    };

    public void setOnPreparedListener(HiMediaPlayer.OnPreparedListener l)
    {
        mOnPreparedListener = l;
    }

    public void setOnCompletionListener(OnCompletionListener l)
    {
        mOnCompletionListener = l;
    }

    public void setOnFastBackwordCompleteListener(OnFastBackwordCompleteListener l)
    {
        mOnFastBackwordCompleteListener = l;
    }

    public void setOn3DModeReceivedListener(OnInfoListener l)
    {
        mOn3DModeReceivedListener = l;
    }

    public void setOnErrorListener(OnErrorListener l)
    {
        mOnErrorListener = l;
    }

    SurfaceHolder.Callback mSHCallback = new SurfaceHolder.Callback()
    {
        public void surfaceChanged(SurfaceHolder holder, int format,
                                   int w, int h)
        {
            mSurfaceWidth  = w;
            mSurfaceHeight = h;
            
            LayoutParams lp = getLayoutParams();
            
            lp.width = mSurfaceWidth;
            mVideoWidth = mSurfaceWidth;

            lp.height = mSurfaceHeight;
            mVideoHeight = mSurfaceHeight;

            setLayoutParams(lp);
            
            if ((mMediaPlayer != null) && mIsPrepared && (mVideoWidth == w) && (mVideoHeight == h))
            {
                if (mSeekWhenPrepared != 0)
                {
                    mMediaPlayer.seekTo(mSeekWhenPrepared);
                    mSeekWhenPrepared = 0;
                }
            }
        }

        public void surfaceCreated(SurfaceHolder holder)
        {
            mSurfaceHolder = holder;
            
            openVideo();
        }

        public void surfaceDestroyed(SurfaceHolder holder)
        {
            destroyPlayer();
        }
    };

    public int invoke(Parcel request, Parcel reply)
    {
        if ((mMediaPlayer != null) && mIsPrepared)
        {
            return mMediaPlayer.invoke(request, reply);
        }

        return 0;
    }

    public void resume()
    {
        if ((mMediaPlayer != null) && mIsPrepared)
        {
            play();
            Common.isResume = true;
        }
    }

    public void play()
    {
        Parcel requestParcel = Parcel.obtain();

        requestParcel.writeInt(CMD_STOP_FASTPLAY);
        Parcel replayParcel = Parcel.obtain();
        invoke(requestParcel, replayParcel);
    }

    public void start()
    {
        if ((mMediaPlayer != null) && mIsPrepared)
        {
            mMediaPlayer.start();
            mStartWhenPrepared = false;
        }
        else
        {
            mStartWhenPrepared = true;
        }
    }
    public void reset()
    {
        if ((mMediaPlayer != null) && mIsPrepared)
        {
            mMediaPlayer.reset();
        }
    }
    public void pause()
    {
        if ((mMediaPlayer != null) && mIsPrepared)
        {
            if (mMediaPlayer.isPlaying())
            {
                mMediaPlayer.pause();
            }
        }

        mStartWhenPrepared = false;
    }

    public int getDuration()
    {
        if ((mMediaPlayer != null) && mIsPrepared)
        {
            if (mDuration > 0)
            {
                return mDuration;
            }

            mDuration = mMediaPlayer.getDuration();
            return mDuration;
        }

        mDuration = -1;
        return mDuration;
    }

    public int getCurrentPosition()
    {
        if ((mMediaPlayer != null) && mIsPrepared)
        {
            return mMediaPlayer.getCurrentPosition();
        }

        return 0;
    }

    public void seekTo(int msec)
    {
        if ((mMediaPlayer != null) && mIsPrepared)
        {
            mMediaPlayer.seekTo(msec);
        }
        else
        {
            mSeekWhenPrepared = msec;
        }
    }

    public void setSpeed(int speed)
    {
        if ((mMediaPlayer != null) && mIsPrepared)
        {
            mMediaPlayer.setSpeed(speed);
        }
    }

    public boolean isPlaying()
    {
        if ((mMediaPlayer != null) && mIsPrepared)
        {
            return mMediaPlayer.isPlaying();
        }

        return false;
    }

    public int getBufferPercentage()
    {
        if (mMediaPlayer != null)
        {
            return mCurrentBufferPercentage;
        }

        return 0;
    }

    public int setStereoVideoFmt(int inVideoFmt)
    {
        if (mMediaPlayer != null)
        {
            return mMediaPlayer.setStereoVideoFmt(inVideoFmt);
        }

        return -1;
    }

    public void setStereoStrategy(int strategy)
    {
        if (mMediaPlayer != null)
        {
            //mMediaPlayer.setStereoStrategy(strategy);
        }
    }

    public Parcel getMediaInfo()
    {
        if (mMediaPlayer != null)
        {
            return mMediaPlayer.getMediaInfo();
        }

        return null;
    }
    public int setVideoCvrs(int flag)
    {
        if (mMediaPlayer != null)
        {
            Parcel Request = Parcel.obtain();
            Parcel Reply = Parcel.obtain();

            Request.writeInt(2);
            Request.writeInt(flag);

            if (invoke(Request, Reply) != 0)
            {
                Request.recycle();
                Reply.recycle();
                return -1;
            }

            Reply.readInt();

            int Result = Reply.readInt();

            Request.recycle();
            Reply.recycle();

            return Result;
        }

        return -1;
    }
    public synchronized List <String> getAudioInfoList()
    {
        if ((mMediaPlayer != null) && mIsPrepared)
        {
            Parcel _Request = Parcel.obtain();
            Parcel _Reply = Parcel.obtain();

            _Request.writeInt(32);

            if (mMediaPlayer.invoke(_Request, _Reply) != 0)
            {
                _Request.recycle();
                _Reply.recycle();
                return null;
            }

            List <String> _AudioInfoList = new ArrayList <String>();

            // for get
            _Reply.readInt();
            int _Num = _Reply.readInt();
            String _Language = "";
            String _Format = "";
            String _SampleRate = "";
            String _Channel = "";

            for (int i = 0; i < _Num; i++)
            {
                _Language = _Reply.readString();
                if (_Language == null)
                {
                    _Language = "";
                }

                _AudioInfoList.add(_Language);

                _Format = Integer.toString(_Reply.readInt());
                _AudioInfoList.add(_Format);

                _SampleRate = Integer.toString(_Reply.readInt());
                _AudioInfoList.add(_SampleRate);

                int _ChannelNum = _Reply.readInt();
                switch (_ChannelNum)
                {
                case 0:
                case 1:
                case 2:
                    _Channel = _ChannelNum + ".0";
                    break;
                default:
                    _Channel = (_ChannelNum - 1) + ".1";
                    break;
                }

                _AudioInfoList.add(_Channel);
            }

            _Request.recycle();
            _Reply.recycle();

            return _AudioInfoList;
        }

        return null;
    }

    public int getAudioTrackNumber()
    {
        return mAudioTrackNumber;
    }

    public void setAudioTrackNumber(int pAudioTrackNumber)
    {
        mAudioTrackNumber = pAudioTrackNumber;
    }

    public int getSelectAudioTrackId()
    {
        return mSelectAudioTrackId;
    }

    public void setSelectAudioTrackId(int pSelectAudioTrackId)
    {
        mSelectAudioTrackId = pSelectAudioTrackId;
    }

    public List <String> getAudioTrackLanguageList()
    {
        return mAudioTrackLanguageList;
    }

    public void setAudioTrackLanguageList(List < String > pAudioTrackLanguageList)
    {
        mAudioTrackLanguageList = pAudioTrackLanguageList;
    }

    public List <String> getAudioFormatList()
    {
        return mAudioFormatList;
    }

    public void setAudioFormatList(List < String > pAudioFormatList)
    {
        mAudioFormatList = pAudioFormatList;
    }

    public List <String> getAudioSampleRateList()
    {
        return mAudioSampleRateList;
    }

    public void setAudioSampleRateList(List < String > pAudioSampleRateList)
    {
        mAudioSampleRateList = pAudioSampleRateList;
    }

    public List <String> getAudioChannelList()
    {
        return mAudioChannelList;
    }

    public int setAudioTrackPid(int pAudioId)
    {
        if ((mMediaPlayer != null) && mIsPrepared)
        {
            return mMediaPlayer.setAudioTrack(pAudioId);
        }

        return -1;
    }

    public void setAudioChannelList(List < String > pAudioChannelList)
    {
        mAudioChannelList = pAudioChannelList;
    }

    public List <String> getInternalSubtitleLanguageInfoList()
    {
        if ((mMediaPlayer != null) && mIsPrepared)
        {
            Parcel _Request = Parcel.obtain();
            Parcel _Reply = Parcel.obtain();

            _Request.writeInt(103);

            if (mMediaPlayer.invoke(_Request, _Reply) != 0)
            {
                _Request.recycle();
                _Reply.recycle();
                return null;
            }

            List <String> _LanguageList = new ArrayList <String>();

            // for get
            _Reply.readInt();
            int _Num = _Reply.readInt();
            String _Language = "";
            String _SubFormat = "";
            int _IsExt = 0;

            for (int i = 0; i < _Num; i++)
            {
                _Reply.readInt();
                _IsExt = _Reply.readInt();
                _Language = _Reply.readString();
                _SubFormat = mSubFormat[_Reply.readInt()];
                if (_Language == null  || _Language.equals("-"))
                {
                    _Language = "";
                }
                if (_IsExt == 0)
                {
                    _LanguageList.add(_SubFormat);
                    _LanguageList.add(_Language);
                }
            }

            _Request.recycle();
            _Reply.recycle();

            return _LanguageList;
        }

        return new ArrayList <String>();
    }

    public List <String> getExtSubtitleLanguageInfoList()
    {
        if ((mMediaPlayer != null) && mIsPrepared)
        {
            Parcel _Request = Parcel.obtain();
            Parcel _Reply = Parcel.obtain();

            _Request.writeInt(103);

            if (mMediaPlayer.invoke(_Request, _Reply) != 0)
            {
                _Request.recycle();
                _Reply.recycle();
                return null;
            }

            List <String> _LanguageList = new ArrayList <String>();

            // for get
            _Reply.readInt();
            int _Num = _Reply.readInt();
            String _Language = "";
            String _SubFormat = "";
            int _IsExt = 0;

            for (int i = 0; i < _Num; i++)
            {
                _Reply.readInt();
                _IsExt = _Reply.readInt();
                _Language = _Reply.readString();
                _SubFormat = mSubFormat[_Reply.readInt()];
                if (_Language == null || _Language.equals("-"))
                {
                    _Language = "";
                }
                if (_IsExt == 1)
                {
                    _LanguageList.add(_SubFormat);
                    _LanguageList.add(_Language);
                }
            }

            _Request.recycle();
            _Reply.recycle();

            return _LanguageList;
        }

        return new ArrayList <String>();
    }

    public int getSubtitleNumber()
    {
        return mSubtitleNumber;
    }

    public void setSubtitleNumber(int pSubtitleNumber)
    {
        mSubtitleNumber = pSubtitleNumber;
    }

    public int getExtSubtitleNumber()
    {
        return mExtSubtitleNumber;
    }

    public void setExtSubtitleNumber(int pExtSubtitleNumber)
    {
        mExtSubtitleNumber = pExtSubtitleNumber;
    }

    public int getSelectSubtitleId()
    {
        return mSelectSubtitleId;
    }

    public void setSelectSubtitleId(int pSelectSubtitleId)
    {
        mSelectSubtitleId = pSelectSubtitleId;
    }

    public List <String> getSubtitleLanguageList()
    {
        return mSubtitleLanguageList;
    }

    public void setSubtitleLanguageList(List < String > pSubtitleLanguageList)
    {
        mSubtitleLanguageList = pSubtitleLanguageList;
    }

    public List <String> getExtraSubtitleList()
    {
        return mExtraSubtitleList;
    }

    public void setExtraSubtitleList(List < String > pExtraSubtitleList)
    {
        mExtraSubtitleList = pExtraSubtitleList;
    }

    public int enableSubtitle(int enable)
    {
        if ((mMediaPlayer != null) && mIsPrepared)
        {
            return mMediaPlayer.enableSubtitle(enable);
        }

        return -1;
    }

    public int setSubVertical(int position)
    {
        if ((mMediaPlayer != null) && mIsPrepared)
        {
            return mMediaPlayer.setSubVertical(position);
        }

        return -1;
    }

    public int getSubtitleId()
    {
        if ((mMediaPlayer != null) && mIsPrepared)
        {
            Parcel Request = Parcel.obtain();
            Parcel Reply = Parcel.obtain();

            Request.writeInt(102);

            if (invoke(Request, Reply) != 0)
            {
                Request.recycle();
                Reply.recycle();
                return -1;
            }
            Reply.readInt();
            int Result = Reply.readInt();

            Request.recycle();
            Reply.recycle();

            return Result;
        }

        return -1;
    }

    public int setSubtitleId(int pSubtitleId)
    {
        if ((mMediaPlayer != null) && mIsPrepared)
        {
            return mMediaPlayer.setSubTrack(pSubtitleId);
        }

        return -1;
    }

    public int setSubtitlePath(String pPath)
    {
        if ((mMediaPlayer != null) && mIsPrepared)
        {
            return mMediaPlayer.setSubPath(pPath);
        }

        return -1;
    }

    public boolean canPause()
    {
        return false;
    }

    public boolean canSeekBackward()
    {
        return false;
    }

    public boolean canSeekForward()
    {
        return false;
    }

    public void destroyPlayer()
    {
        if (mSurfaceHolder != null)
        {
            mSurfaceHolder = null;
        }

        if (mMediaPlayer != null)
        {
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public int getAudioSessionId(){
        return 0;
    }
}
