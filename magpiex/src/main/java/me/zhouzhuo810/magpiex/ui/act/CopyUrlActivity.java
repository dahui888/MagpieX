package me.zhouzhuo810.magpiex.ui.act;

import android.os.Bundle;

import androidx.annotation.Nullable;
import me.zhouzhuo810.magpiex.R;
import me.zhouzhuo810.magpiex.cons.Cons;
import me.zhouzhuo810.magpiex.utils.CopyUtil;
import me.zhouzhuo810.magpiex.utils.ShareUtil;
import me.zhouzhuo810.magpiex.utils.ToastUtil;

/**
 * 复制通知栏Url
 */
public class CopyUrlActivity extends BaseActivity {
    
    public static final String NOTICE_ACTION_COPY = "copy";
    public static final String NOTICE_ACTION_SHARE = "share";
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String action = getIntent().getStringExtra(Cons.NOTICE_ACTION);
        String url = getIntent().getStringExtra(Cons.NOTICE_URL);
        if (NOTICE_ACTION_COPY.equals(action)) {
            CopyUtil.copyPlainText("", url);
            ToastUtil.showToast(getString(R.string.magpie_copy_ok));
        } else if (NOTICE_ACTION_SHARE.equals(action)) {
            ShareUtil.shareTextToOther(this, url, null);
        }
        closeAct();
    }
    
    @Override
    public int getLayoutId() {
        return R.layout.activity_copy_url;
    }
    
    @Override
    public boolean shouldSupportMultiLanguage() {
        return false;
    }
    
    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
    }
    
    @Override
    public void initData() {
    
    }
    
    @Override
    public void initEvent() {
    
    }
}
