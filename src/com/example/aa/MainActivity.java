package com.example.aa;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        
        MySlidingDrawer ll=(MySlidingDrawer)findViewById(R.id.ll);
        ll.init();
        
//        
//        Button btn=(Button)findViewById(R.id.btn);
//
//        btn.setOnClickListener(new OnClickListener(){
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				RelativeLayout.LayoutParams rllp=(RelativeLayout.LayoutParams) ll.getLayoutParams();
//		        rllp.setMargins(0, 0, 0, 200);
//		        ll.requestLayout();
//		        Animation open=AnimationUtils.loadAnimation(MainActivity.this, R.anim.open);
//				ll.setAnimation(open);
//			}
//        	
//        });
//        
//        
//        Button btnC=(Button)findViewById(R.id.btnC);
//
//        btnC.setOnClickListener(new OnClickListener(){
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				
//		        Animation open=AnimationUtils.loadAnimation(MainActivity.this, R.anim.close);
//				ll.setAnimation(open);
//				
//
//				
//				open.setAnimationListener(new AnimationListener(){
//
//					@Override
//					public void onAnimationEnd(Animation animation) {
//						// TODO Auto-generated method stub
//						RelativeLayout.LayoutParams rllp=(RelativeLayout.LayoutParams) ll.getLayoutParams();
//				        rllp.setMargins(0, 0, 0, 900);
//				        ll.requestLayout();
//					}
//
//					@Override
//					public void onAnimationRepeat(Animation animation) {
//						// TODO Auto-generated method stub
//						
//					}
//
//					@Override
//					public void onAnimationStart(Animation animation) {
//						// TODO Auto-generated method stub
//						
//					}});
//			}
//        	
//        });
//        
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
    }

    
}
