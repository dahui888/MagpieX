package me.zhouzhuo810.magpiex.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.RequiresApi;

/**
 * Android 复制粘贴工具
 */
public class CopyUtil {

    private CopyUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean copyPlainText(CharSequence label, String text) {
        ClipboardManager cm = (ClipboardManager) BaseUtil.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        if (cm == null) {
            return false;
        }
        try {
            cm.setPrimaryClip(ClipData.newPlainText(label, text));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static CharSequence getCopyPlainText() {
        ClipboardManager cm = (ClipboardManager) BaseUtil.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        if (cm == null) {
            return null;
        }
        try {
            return cm.getPrimaryClip().getItemAt(0).getText();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean copyUrl(CharSequence label, String url) {
        ClipboardManager cm = (ClipboardManager) BaseUtil.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        if (cm == null) {
            return false;
        }
        try {
            cm.setPrimaryClip(ClipData.newRawUri(label, Uri.parse(url)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean copyUri(CharSequence label, Uri uri) {
        ClipboardManager cm = (ClipboardManager) BaseUtil.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        if (cm == null) {
            return false;
        }
        try {
            cm.setPrimaryClip(ClipData.newRawUri(label, uri));
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static Uri getCopyUri() {
        ClipboardManager cm = (ClipboardManager) BaseUtil.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        if (cm == null) {
            return null;
        }
        try {
            return cm.getPrimaryClip().getItemAt(0).getUri();
        } catch (Exception e) {
            return null;
        }
    }

    @RequiresApi(value = 16)
    public static boolean copyHtml(CharSequence label, String text, String html) {
        ClipboardManager cm = (ClipboardManager) BaseUtil.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        if (cm == null) {
            return false;
        }
        try {
            cm.setPrimaryClip(ClipData.newHtmlText(label, text, html));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @RequiresApi(value = 16)
    public static CharSequence getCopyHtmlText() {
        ClipboardManager cm = (ClipboardManager) BaseUtil.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        if (cm == null) {
            return null;
        }
        try {
            return cm.getPrimaryClip().getItemAt(0).getHtmlText();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean copyIntent(CharSequence label, Intent intent) {
        ClipboardManager cm = (ClipboardManager) BaseUtil.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        if (cm == null) {
            return false;
        }
        try {
            cm.setPrimaryClip(ClipData.newIntent(label, intent));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public static Intent getCopyIntent() {
        ClipboardManager cm = (ClipboardManager) BaseUtil.getApp().getSystemService(Context.CLIPBOARD_SERVICE);
        if (cm == null) {
            return null;
        }
        try {
            return cm.getPrimaryClip().getItemAt(0).getIntent();
        } catch (Exception e) {
            return null;
        }
    }


}
