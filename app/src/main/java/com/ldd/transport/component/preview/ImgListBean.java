package com.ldd.transport.component.preview;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

import com.previewlibrary.enitity.IThumbViewInfo;

import java.io.Serializable;

/**
 * 图片
 * Created by Ace on 2016/7/11.
 */
public class ImgListBean implements Parcelable, IThumbViewInfo, Serializable {
    private String url;
    private String id;
    private int width;
    private int height;
    private Rect bounds; // 记录坐标 (app自己添加的字段)

    public ImgListBean(){}

    public ImgListBean(String url, String id) {
        this.url = url;
        this.id = id;
    }

    @Override
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public Rect getBounds() {
        return bounds;
    }

    @Nullable
    @Override
    public String getVideoUrl() {
        return "";
    }

    public void setBounds(Rect bounds) {
        this.bounds = bounds;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeParcelable(this.bounds, 0);
    }

    protected ImgListBean(Parcel in) {
        this.url = in.readString();
        this.bounds = in.readParcelable(Rect.class.getClassLoader());
    }

    public static final Creator<ImgListBean> CREATOR = new Creator<ImgListBean>() {
        @Override
        public ImgListBean createFromParcel(Parcel source) {
            return new ImgListBean(source);
        }

        @Override
        public ImgListBean[] newArray(int size) {
            return new ImgListBean[size];
        }
    };
}
