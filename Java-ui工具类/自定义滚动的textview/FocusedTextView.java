package test.test;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ZhangRuxing
 */

public class FocusedTextView extends TextView {
    public FocusedTextView(Context context) {
        super(context);
    }

    public FocusedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FocusedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //重写父类的方法，欺骗系统TextView直接获取到焦点
    @Override
    public boolean isFocused() {
        return true;
    }
}
