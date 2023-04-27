package com.ldd.transport.component.preview;

import android.graphics.Rect;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

/**
 * 作者：Zhout
 * 时间：2018/2/1 11:09
 * 描述：计算图片坐标
 */

public class ComputeBoundsUtil {

    /**
     * 方法重载
     * adapter套在popupWindow中 存在一个偏移量offY
     */
    public static void computeSingleBoundsBackward(View imageView, List<ImgListBean> imgList, int position, int offY) {
        Rect bounds = new Rect();
        imageView.getGlobalVisibleRect(bounds);
        bounds.top += offY;
        bounds.bottom += offY;
        imgList.get(position).setBounds(bounds);
    }

    /**
     * 记录单张图片坐标，用在adapter内部的单张图片点击
     */
    public static void computeSingleBoundsBackward(View imageView, List<ImgListBean> imgList, int position) {
        computeSingleBoundsBackward(imageView, imgList, position, 0);
    }

    /**
     * 查找信息
     * 从第一个完整可见item逆序遍历，如果初始位置为0，则不执行方法内循环,用在listview的item点击
     *
     * @param listView ListView 或者 GridView
     * @param imgList  图片数据集合
     * @param imgId    // 这是GridView的Adapter的item里面的图片id
     */
    public static void computeBoundsBackward(AbsListView listView, int imgId, List<ImgListBean> imgList) {
        for (int i = listView.getFirstVisiblePosition(); i < imgList.size(); i++) {
            View itemView = listView.getChildAt(i - listView.getFirstVisiblePosition());
            Rect bounds = new Rect();
            if (itemView != null) {
                ImageView thumbView = (ImageView) itemView.findViewById(imgId);
                thumbView.getGlobalVisibleRect(bounds);
            }
            imgList.get(i).setBounds(bounds);
        }
    }

    /**
     * 查找信息
     * 从第一个完整可见item逆序遍历，如果初始位置为0，则不执行方法内循环
     *
     * @param layoutManager             RecyclerView.LayoutManager
     * @param imgList                   图片数据集合
     * @param imgId                     // 这是RecyclerView的Adapter的item里面的图片id
     * @param firstCompletelyVisiblePos // 第一个位置
     */
    public static void computeBoundsBackward(RecyclerView.LayoutManager layoutManager, int imgId, List<ImgListBean> imgList,
                                             int firstCompletelyVisiblePos) {
        for (int i = firstCompletelyVisiblePos; i < imgList.size(); i++) {
            View itemView = layoutManager.findViewByPosition(i);
            Rect bounds = new Rect();
            if (itemView != null) {
                ImageView thumbView = (ImageView) itemView.findViewById(imgId);
                thumbView.getGlobalVisibleRect(bounds);
            }
            imgList.get(i).setBounds(bounds);
        }
    }
}
