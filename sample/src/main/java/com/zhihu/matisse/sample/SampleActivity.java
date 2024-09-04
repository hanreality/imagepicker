/*
 * Copyright 2017 Zhihu Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhihu.matisse.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.ypx.imagepicker.ImagePicker;
import com.ypx.imagepicker.bean.ImageItem;
import com.ypx.imagepicker.bean.MimeType;
import com.ypx.imagepicker.bean.selectconfig.CropConfig;
import com.ypx.imagepicker.data.OnImagePickCompleteListener;

import java.util.ArrayList;
import java.util.List;

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.zhihu)
                .setOnClickListener(v -> {
                    XXPermissions.with(this)
                            .permission(new String[]{
                                    Permission.READ_MEDIA_IMAGES,
                                    Permission.READ_MEDIA_VIDEO,
                                    Permission.WRITE_EXTERNAL_STORAGE,
                                    Permission.READ_MEDIA_VISUAL_USER_SELECTED
                            }).request(new OnPermissionCallback() {
                                @Override
                                public void onGranted(@NonNull List<String> permissions, boolean allGranted) {
                                    ImagePicker.withMulti(new PickerPresenter())
                                            .showCamera(true)
                                            .setMaxCount(1)
                                            .setColumnCount(3)
                                            .mimeTypes(MimeType.ofImage())
                                            .filterMimeTypes(MimeType.GIF)
                                            .cropSaveInDCIM(false)
                                            .setCropRatio(1, 1)
                                            .cropRectMinMargin(0)
                                            .cropStyle(CropConfig.STYLE_FILL)
                                            .cropGapBackgroundColor(Color.TRANSPARENT)
                                            .crop(SampleActivity.this, new OnImagePickCompleteListener() {
                                                @Override
                                                public void onImagePickComplete(ArrayList<ImageItem> items) {
                                                    Log.d("han.chen", items.get(0).getCropUrl());
                                                }
                                            });
                                }
                            });

                });
    }
}
