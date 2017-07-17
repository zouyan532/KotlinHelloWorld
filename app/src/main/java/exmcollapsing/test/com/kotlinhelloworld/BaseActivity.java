package exmcollapsing.test.com.kotlinhelloworld;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity {
    protected LinearLayout layout_content;
    protected ViewGroup content;
    protected View top_bar;
    ImageView btnLeft;
    TextView tvTitle;
    ImageView btnRight;
    RelativeLayout rl_left;
    RelativeLayout rl_right;
    RelativeLayout rl_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        layout_content = (LinearLayout) findViewById(R.id.content_container);
        top_bar = (View) findViewById(R.id.included_top_bar);
    }
    public ImageView getBtnRight(){
        return  btnRight;
    }
    protected abstract void initViews();

    protected abstract void initEvents();

    protected abstract void init();

    protected void setView(int layoutId) {
        content = (ViewGroup) View.inflate(this, layoutId, null);
        layout_content.removeAllViews();
        layout_content.addView(content, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initViews();
        init();
        initEvents();
    }

    public RelativeLayout getTopLayout(){
        return rl_header;
    }

    protected void hideTopBar(boolean isHide) {
        if (!isHide) {
            top_bar.setVisibility(View.VISIBLE);
            btnRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        } else {
            top_bar.setVisibility(View.GONE);
        }
    }

    protected void showShortToast(int resId) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
    }

    protected void showShortToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(int resId) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
    }

    public void showLongToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void startActivity(String action) {
        startActivity(action, null);
    }

    protected void startActivity(String action, Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void defaultFinish() {
        super.finish();
    }


    public void setTopTitle(int resId) {
        tvTitle.setText(resId);
    }

    private boolean hasSDCard() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    public int getStatusHeight(Activity activity) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = activity.getResources().getDimensionPixelSize(i5);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

    public void setStatusbar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            View statusBar = (View) findViewById(R.id.SysStatusBar);
            ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
            layoutParams.width = 0;
            layoutParams.height = getStatusHeight(activity);
            statusBar.setLayoutParams(layoutParams);
        }
    }

    public void onTopMenuClick(View view) {
        switch (view.getId()) {
            case R.id.rl_left:
                onLeftButtonClick(view);
                break;
            case R.id.rl_right:
                onRightButtonClick(view);
                break;
        }
    }

    public void onLeftButtonClick(View view) {
        finish();
    }

    public void onRightButtonClick(View view) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
