package com.zhang.mobilesafe.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.zhang.mobilesafe.R;

/**
 * Created by ZhangRuxing on 2017-04-16.
 */

public class SwitchImageView extends ImageView {
    private boolean switchStatus = true;

    public boolean getSwitchStatus() {
        return switchStatus;
    }

    public void setSwitchStatus(boolean switchStatus) {
        this.switchStatus = switchStatus;
        if (switchStatus) {
            setImageResource(R.drawable.on);
        } else {
            setImageResource(R.drawable.off);
        }
    }

    public void changeSwitchStatus() {
        setSwitchStatus(!switchStatus);
    }

    public SwitchImageView(Context context) {
        super(context);
    }

    public SwitchImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwitchImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
