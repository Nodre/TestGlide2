package com.example.testimgloder.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.testimgloder.bean.ImageBean;
import com.example.testimgloder.util.HandlerUtil.StaticHandler;
import com.test.R;

public class ImgAdapter extends BaseAdapter {

	private Activity mActivity;
	private StaticHandler mHandler;
	
	private ArrayList<ImageBean> mImageBeans;
	
	public ImgAdapter(Activity activity, ArrayList<ImageBean> images) {
		this.mActivity = activity;
		this.mImageBeans = images;
		
		this.mHandler = new StaticHandler();
//		this.mRequestBuilder = Glide.with(mActivity)
//				.asDrawable().apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
//				.format(DecodeFormat.PREFER_RGB_565).priority(Priority.HIGH)
//				.dontAnimate().dontTransform()
////				.override(width, height).skipMemoryCache(false)
//	            .placeholder(R.drawable.custom_drawable));
	}

	@Override
	public int getCount() {
		return mImageBeans == null ? 0 : mImageBeans.size();
	}

	@Override
	public ImageBean getItem(int pos) {
		return mImageBeans.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return 0;
	}

	@Override
	public View getView(final int pos, View convertView, ViewGroup arg2) {
		long time = System.currentTimeMillis();
		final ImageBean imageBean = getItem(pos);
		final ImgHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mActivity).inflate(R.layout.adapter_images, null);
			holder = new ImgHolder();
			holder.mImageVi = (ImageView) convertView.findViewById(R.id.adapter_image);
			convertView.setTag(holder);
		} else {
			holder = (ImgHolder) convertView.getTag();
		}
		
		mHandler.post(new Runnable() {
			
			@Override
			public void run() {
				Glide.with(mActivity).load(Uri.parse(imageBean.imageSrc))
				.placeholder(R.drawable.logo)  //影响图片变形
				.centerCrop()
				.crossFade()
				.into(holder.mImageVi);
			}
		});
		
		System.out.println((System.currentTimeMillis() - time)+"====="+imageBean.imageSize);
		return convertView;
	}
	
	private class ImgHolder {
		public ImageView mImageVi;
	}

}
