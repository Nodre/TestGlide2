package com.example.testimgloder;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
import android.provider.MediaStore;
import android.widget.GridView;

import com.example.testimgloder.adapter.ImgAdapter;
import com.example.testimgloder.base.BaseActivity;
import com.example.testimgloder.bean.ImageBean;
import com.example.testimgloder.util.HandlerUtil.StaticHandler;
import com.test.R;

public class ImageListActivity extends BaseActivity{

	private static final int MSG_SELECT_COMPLETE = 1001;
	
	private GridView mGridView;
	private ImgAdapter mImgAdapter;
	private ArrayList<ImageBean> mImageBeans = new ArrayList<ImageBean>();
	
	private StaticHandler mHandler = new StaticHandler() {
        @Override
        public void handleMessage(Message msg) {
        	switch (msg.what) {
			case MSG_SELECT_COMPLETE:
				mImgAdapter.notifyDataSetChanged();
				break;
			default:
				break;
			}
        }
    };
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_choose);
		
		mGridView = (GridView) findViewById(R.id.photo_choose_gridview);
		mImgAdapter = new ImgAdapter(this, mImageBeans);
		
		mGridView.setAdapter(mImgAdapter);
		
		mSearchPhotoThread.start();
	}

	private Thread mSearchPhotoThread = new Thread(new Runnable() {
        @Override
        public void run() {
            getAllPic(null);
            mHandler.sendEmptyMessage(MSG_SELECT_COMPLETE);
        }
    });
	
	private void getAllPic(String seletction) {
		mImageBeans.clear();
		String[] projection = new String[] { MediaStore.Images.Media._ID,
				MediaStore.Images.Media.DATE_TAKEN,// 图片日期
				MediaStore.Images.Media.DATA, // 图片绝对路径
				MediaStore.Images.Media.BUCKET_DISPLAY_NAME, // 直接包含该图片文件的文件夹名
				MediaStore.Images.Media.SIZE };
		String order = "_id desc";
		ContentResolver cr = getContentResolver();
		Cursor cursor = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				projection, seletction, null, order);

		if (null != cursor) {
			while (cursor.moveToNext()) {
				long size = cursor.getLong(cursor
						.getColumnIndex(MediaStore.Images.Media.SIZE));
				if (size == 0) {
					continue;
				}
				String path = cursor.getString(cursor
						.getColumnIndex(MediaStore.Images.Media.DATA));
				ImageBean imageBean = new ImageBean("file://" + path, formatFileSize(size));
				mImageBeans.add(imageBean);
			}
			if (!cursor.isClosed()) {
				cursor.close();
			}
		}
	}
	
	public String formatFileSize(long fileSize) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileSize < 1024) {
			fileSizeString = df.format((double) fileSize) + "B";
		} else if (fileSize < 1048576) {
			fileSizeString = df.format((double) fileSize / 1024) + "K";
		} else if (fileSize < 1073741824) {
			fileSizeString = df.format((double) fileSize / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileSize / 1073741824) + "G";
		}
		return fileSizeString;
	}
}
