package dani2pix.ro.foursquareapp.listeners;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Domnica on 05.09.2016.
 */
public class VenuesClickListener implements RecyclerView.OnItemTouchListener  {

    private GestureDetector mGestureDetector;
    private OnItemClickListener mOnItemClickListener;
    private RecyclerView mRecyclerView;

    public VenuesClickListener(Context context, RecyclerView view, OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
        this.mRecyclerView = view;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }


    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View children = rv.findChildViewUnder(e.getX(), e.getY());
        if (children != null && children.isEnabled() && mGestureDetector.onTouchEvent(e) && mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(children, mRecyclerView.getChildAdapterPosition(children));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        // not used
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        // not used
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
