
package com.sz.ktv.ui.tool;

import android.view.View;
import android.view.ViewGroup.OnHierarchyChangeListener;

public class HierarchyChangeListener implements OnHierarchyChangeListener
{
    
    MultiScreenTool mst = null;
    
    public HierarchyChangeListener ( MultiScreenTool mst )
    {
        this.mst = mst;
    }
    
    @Override
    public void onChildViewAdded(View parent, View child)
    {
        // TODO Auto-generated method stub
        if (parent != null)
        {
            mst.adjustView(parent);
        }
    }
    
    @Override
    public void onChildViewRemoved(View parent, View child)
    {
        // TODO Auto-generated method stub
        
    }
    
}
