package com.zhihu.matisse.sample;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.ypx.imagepicker.adapter.PickerItemAdapter;
import com.ypx.imagepicker.bean.ImageItem;
import com.ypx.imagepicker.bean.selectconfig.BaseSelectConfig;
import com.ypx.imagepicker.data.ICameraExecutor;
import com.ypx.imagepicker.data.IReloadExecutor;
import com.ypx.imagepicker.data.ProgressSceneEnum;
import com.ypx.imagepicker.presenter.IPickerPresenter;
import com.ypx.imagepicker.utils.PViewSizeUtils;
import com.ypx.imagepicker.views.PickerUiConfig;
import com.ypx.imagepicker.views.PickerUiProvider;
import com.ypx.imagepicker.views.base.PickerControllerView;
import com.ypx.imagepicker.views.base.PickerFolderItemView;
import com.ypx.imagepicker.views.base.PickerItemView;
import com.ypx.imagepicker.views.base.PreviewControllerView;
import com.ypx.imagepicker.views.base.SingleCropControllerView;
import com.ypx.imagepicker.views.wx.WXItemView;

import java.util.ArrayList;

/**
 * Author: han.chen
 * Date: 2024/9/4
 */
public class PickerPresenter implements IPickerPresenter {


    @Override
    public void displayImage(View view, ImageItem item, int size, boolean isThumbnail) {
        if (view == null || item == null) return;
        if (view instanceof ImageView) {
            Object object = item.getUri() != null ? item.getUri() : item.path;
            Glide.with(view.getContext()).load(object).apply(new RequestOptions()
                            .format(isThumbnail ? DecodeFormat.PREFER_RGB_565 : DecodeFormat.PREFER_ARGB_8888))
                    .override(isThumbnail ? size : Target.SIZE_ORIGINAL)
                    .into((ImageView) view);
        }
    }

    @NonNull
    @Override
    public PickerUiConfig getUiConfig(@Nullable Context context) {
        PickerUiConfig uiConfig = new PickerUiConfig();
        //设置主题色
        uiConfig.setThemeColor(Color.parseColor("#303030"));
        //设置是否显示状态栏
        uiConfig.setShowStatusBar(true);
        //设置状态栏颜色
        uiConfig.setStatusBarColor(Color.parseColor("#f5f5f5"));
        //设置选择器背景
        uiConfig.setPickerBackgroundColor(Color.BLACK);
        //设置单图剪裁背景色
        uiConfig.setSingleCropBackgroundColor(Color.BLACK);
        //设置预览页面背景色
        uiConfig.setPreviewBackgroundColor(Color.BLACK);
        //设置选择器文件夹打开方向
        uiConfig.setFolderListOpenDirection(PickerUiConfig.DIRECTION_BOTTOM);
        //设置文件夹列表距离顶部/底部边距
        uiConfig.setFolderListOpenMaxMargin(0);
        //设置小红书剪裁区域的背景色
        uiConfig.setCropViewBackgroundColor(Color.BLACK);
        //设置文件夹列表距离底部/顶部的最大间距。通俗点就是设置文件夹列表的高
        if (context != null) {
            uiConfig.setFolderListOpenMaxMargin(PViewSizeUtils.dp(context, 100));
        }
        //自定义选择器标题栏，底部栏，item，文件夹列表item，预览页面，剪裁页面
        uiConfig.setPickerUiProvider(new PickerUiProvider() {
            //定制选择器标题栏，默认实现为 WXTitleBar
            @Override
            public PickerControllerView getTitleBar(Context context) {
                return super.getTitleBar(context);
            }

            //定制选择器底部栏，返回null即代表没有底部栏，默认实现为 WXBottomBar
            @Override
            public PickerControllerView getBottomBar(Context context) {
                return super.getBottomBar(context);
            }

            //定制选择器item,默认实现为 WXItemView
            @Override
            public PickerItemView getItemView(Context context) {
                WXItemView itemView = (WXItemView) super.getItemView(context);
                itemView.setBackgroundColor(Color.parseColor("#303030"));
                return itemView;
            }

            //定制选择器文件夹列表item,默认实现为 WXFolderItemView
            @Override
            public PickerFolderItemView getFolderItemView(Context context) {
                return super.getFolderItemView(context);
            }

            //定制选择器预览页面,默认实现为 WXPreviewControllerView
            @Override
            public PreviewControllerView getPreviewControllerView(Context context) {
                return super.getPreviewControllerView(context);
            }

            //定制选择器单图剪裁页面,默认实现为 WXSingleCropControllerView
            @Override
            public SingleCropControllerView getSingleCropControllerView(Context context) {
                return super.getSingleCropControllerView(context);
            }
        });
        return uiConfig;
    }

    @Override
    public void tip(@Nullable Context context, String msg) {

    }

    @Override
    public void overMaxCountTip(@Nullable Context context, int maxCount) {

    }

    @Override
    public DialogInterface showProgressDialog(@Nullable Activity activity, ProgressSceneEnum progressSceneEnum) {
        return new DialogInterface() {
            @Override
            public void cancel() {

            }

            @Override
            public void dismiss() {

            }
        };
    }

    @Override
    public boolean interceptPickerCompleteClick(@Nullable Activity activity, ArrayList<ImageItem> selectedList, BaseSelectConfig selectConfig) {
        return false;
    }

    @Override
    public boolean interceptPickerCancel(@Nullable Activity activity, ArrayList<ImageItem> selectedList) {
        return false;
    }

    @Override
    public boolean interceptItemClick(@Nullable Activity activity, ImageItem imageItem, ArrayList<ImageItem> selectImageList, ArrayList<ImageItem> allSetImageList, BaseSelectConfig selectConfig, PickerItemAdapter adapter, boolean isClickCheckBox, @Nullable IReloadExecutor reloadExecutor) {
        return false;
    }

    @Override
    public boolean interceptCameraClick(@Nullable Activity activity, ICameraExecutor takePhoto) {
        return false;
    }
}
