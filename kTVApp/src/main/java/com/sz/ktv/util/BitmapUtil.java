package com.sz.ktv.util;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import com.sz.ktv.R;

public class BitmapUtil {

	 public static  Bitmap getViewBitmap( View view ){

		    view.setDrawingCacheEnabled( true );

		    Bitmap bitmap = null;

		    try{

		        if( null != view.getDrawingCache( ) ){

		           bitmap = Bitmap.createScaledBitmap( view.getDrawingCache( ), 256, 192, false );

		        }else{

		            Bitmap bitmapTmp =( ( BitmapDrawable )( Global.currentActivity.getResources( ).getDrawable( R.drawable.mtv ) ) ).getBitmap( );

		        }

		        }catch( OutOfMemoryError e ){

		            e.printStackTrace( );

		        }finally{

		           view.setDrawingCacheEnabled( false );

		           view.destroyDrawingCache( );

		        }

		        return bitmap;

		    }
}
