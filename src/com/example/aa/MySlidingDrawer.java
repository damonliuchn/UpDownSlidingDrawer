package com.example.aa;




import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MySlidingDrawer extends RelativeLayout{
	private Context context;
	private Button  button;
	private RelativeLayout  content;
	private TranslateAnimation open;
	private Animation close;
	private int duration=500;
	private float btnHeight=50f;//dip  按钮高度
	private int btnHeightPx;//转换成px的按钮高度
	private int heightPx;//屏幕高度
	private boolean show=false;
	private boolean firstShow=true;
//	ll.setAnimation(open);
	public MySlidingDrawer(Context context, AttributeSet attrs){
		super(context,attrs);
		// TODO Auto-generated constructor stub
		this.context=context;
	}
	@Override
	protected void onFinishInflate()
	{
		super.onFinishInflate();
		
	}
	public void init(){
		//得到屏幕高度
		heightPx = ((Activity) context).getWindowManager().getDefaultDisplay().getHeight();
		//设置按钮高度
		button=(Button)findViewById(R.id.btn);
		content=(RelativeLayout)findViewById(R.id.content);
		btnHeightPx=dip2px(context,btnHeight);
		button.setHeight(btnHeightPx);
		RelativeLayout.LayoutParams rllp=(RelativeLayout.LayoutParams) this.getLayoutParams();
        rllp.setMargins(0, 0, 0, heightPx-btnHeightPx);//最初位置
        ///////////////////////////////////////////////////////////////////////////////////////////
        close=new TranslateAnimation(0, 0, 0, -(heightPx-btnHeightPx));//关闭动画
	    close.setDuration(duration); 
	    close.setAnimationListener(new AnimationListener(){
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				MySlidingDrawer.this.clearAnimation();
				RelativeLayout.LayoutParams rllp=(RelativeLayout.LayoutParams)MySlidingDrawer.this.getLayoutParams();
			    rllp.setMargins(0, 0, 0, heightPx-btnHeightPx);//结束位置
			    MySlidingDrawer.this.requestLayout();
				show=false;
				button.setBackgroundResource(R.drawable.top_switcher_expanded_background);
				if(listener!=null)listener.onSlidingDrawerClosed(MySlidingDrawer.this);
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}});
	    open=new TranslateAnimation(0, 0, -(heightPx-btnHeightPx), 0);//开启动画
	    open.setDuration(duration);
	    open.setAnimationListener(new AnimationListener(){
           	@Override
   			public void onAnimationEnd(Animation animation) {
   				// TODO Auto-generated method stub
           		button.setBackgroundResource(R.drawable.top_switcher_collapsed_background);
           		if(listener!=null){
           			if(firstShow){
               			listener.onSlidingDrawerFirstOpened(MySlidingDrawer.this);
               			firstShow=false;
               		}else{
               			listener.onSlidingDrawerOpened(MySlidingDrawer.this);
               		}
           		}
           		
   		        show=true;
   			}

   			@Override
   			public void onAnimationRepeat(Animation animation) {
   				// TODO Auto-generated method stub
   				
   			}

   			@Override
   			public void onAnimationStart(Animation animation) {
   				// TODO Auto-generated method stub
   				
   			}});
	    /////////////////////////////////////////////////////////////////
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		       if(show){//关闭
			       MySlidingDrawer.this.startAnimation(close);
		       }
		       else{//开启
		    	   RelativeLayout.LayoutParams rllp=(RelativeLayout.LayoutParams) MySlidingDrawer.this.getLayoutParams();
				   rllp.setMargins(0, 0, 0, 0);//结束位置
				   MySlidingDrawer.this.requestLayout();
		    	   MySlidingDrawer.this.startAnimation(open);
		       }
			}
		});
		//启动拖动效果
		//initGesture();
	}
	public int dip2px(Context context, float dipValue){   
        final float scale = context.getResources().getDisplayMetrics().density;   
        return (int)(dipValue * scale + 0.5f);   
    }
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return show;
	}
	public void closeOrOpen() {
		// TODO Auto-generated method stub
		button.performClick();
	}
	public void setContentViewBackground(int resid){
		if(content!=null)
			content.setBackgroundResource(resid);
	}
	public void setContentViewBackgroundColor(int color){
		if(content!=null)
			content.setBackgroundColor(color);
	}
	/**
	 * 回调接口
	 * @author liumeng
	 *
	 */
	private OnMySlidingDrawerListener listener;
	public static interface OnMySlidingDrawerListener
	{
		public void onSlidingDrawerClosed(MySlidingDrawer m);
		public void onSlidingDrawerOpened(MySlidingDrawer m);
		public void onSlidingDrawerFirstOpened(MySlidingDrawer m);
	}
	public void setOnMySlidingDrawerListener(OnMySlidingDrawerListener onPanelListener)
	{
		listener = onPanelListener;
	}
	/**
	 * 拖动效果的添加
	 */
	public void initGesture(){
		button.setOnTouchListener(touchListener);
		mGestureListener = new PanelOnGestureListener();
		mGestureDetector = new GestureDetector(mGestureListener);
		mGestureDetector.setIsLongpressEnabled(false);
	}
	private GestureDetector mGestureDetector;
	private PanelOnGestureListener mGestureListener;
	OnTouchListener touchListener = new OnTouchListener()
	{
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			mGestureDetector.onTouchEvent(event);
			return false;
		}
	};
	class PanelOnGestureListener implements OnGestureListener
	{
		float scrollY;
		float scrollX;
		RelativeLayout.LayoutParams rllp=(RelativeLayout.LayoutParams) MySlidingDrawer.this.getLayoutParams();
		@Override
		public boolean onDown(MotionEvent e)
		{
//			scrollX = scrollY = 0;
//			lastRawX = curRawX = lastRawY = curRawY = -1;
//			lastEventTime = curEventTime = -1;
//			initChange();

			return true;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY)
		{
//			mState = State.FLYING;
//
//			float velocityX2, velocityY2;
//			if (lastRawX == -1 && lastRawY == -1)	//见onScroll方法
//			{
//				velocityX2 = (curRawX - e1.getRawX())
//						/ (curEventTime - e1.getEventTime()) * 1000; //  px/s
//				velocityY2 = (curRawY - e1.getRawY())
//						/ (curEventTime - e1.getEventTime()) * 1000;
//			}
//			else
//			{
//				velocityX2 = (curRawX - lastRawX)
//						/ (curEventTime - lastEventTime) * 1000;
//				velocityY2 = (curRawY - lastRawY)
//						/ (curEventTime - lastEventTime) * 1000;
//			}
//
//			mVelocity = mOrientation == VERTICAL ? velocityY2 : velocityX2;
//
//			if (Math.abs(mVelocity) > 50)
//			{
//				if (mVelocity > 0)
//				{
//					mAnimatedAcceleration = mMaximumAcceleration;
//				}
//				else
//				{
//					mAnimatedAcceleration = -mMaximumAcceleration;
//				}
//
//				long now = SystemClock.uptimeMillis();
//				mAnimationLastTime = now;
//				mCurrentAnimationTime = now + ANIMATION_FRAME_DURATION;
//				mAnimating = true;
//				mHandler.removeMessages(MSG_ANIMATE);
//				mHandler.removeMessages(MSG_PREPARE_ANIMATE);
//				mHandler.sendMessageAtTime(mHandler.obtainMessage(MSG_ANIMATE),
//						mCurrentAnimationTime);
			
			return false;
		}

		@Override
		public void onLongPress(MotionEvent e)
		{
			// not used
		}
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY)
		{
		
			rllp.setMargins(0, 0, 0, heightPx-(int)e2.getRawY());//结束位置
			MySlidingDrawer.this.requestLayout();
//			mState = State.TRACKING;
//			float tmpY = 0, tmpX = 0;
//			if (mOrientation == VERTICAL)
//			{
//				scrollY -= distanceY;
//				if (mPosition == TOP)
//				{
//					tmpY = ensureRange(scrollY, -mContentHeight, 0);
//				}
//				else
//				{
//					tmpY = ensureRange(scrollY, 0, mContentHeight);
//				}
//			}
//			else
//			{
//				scrollX -= distanceX;
//				if (mPosition == LEFT)
//				{
//					tmpX = ensureRange(scrollX, -mContentWidth, 0);
//				}
//				else
//				{
//					tmpX = ensureRange(scrollX, 0, mContentWidth);
//				}
//			}
//
//			if (tmpX != mTrackX || tmpY != mTrackY)
//			{
//				mTrackX = tmpX;
//				mTrackY = tmpY;
//				// invalidate(); //放在此导致极快速滑动至touch区域外界面不刷新（mTrackX、mTrackY均为0）
//			}
//			invalidate();
//
//			lastRawX = curRawX;
//			lastRawY = curRawY;
//			lastEventTime = curEventTime;
//			curRawX = e2.getRawX();
//			curRawY = e2.getRawY();
//			curEventTime = e2.getEventTime();
			return true;
		}

		@Override
		public void onShowPress(MotionEvent e)
		{
			// not used
		}

		@Override
		public boolean onSingleTapUp(MotionEvent e)
		{
			// not used
			return false;
		}
	}
}
