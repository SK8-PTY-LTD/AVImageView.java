//================================================================================
//  AVImageView.java
//  Author: Xujie Song
//  Copyright (c) 2015 SK8 PTY LTD. All rights reserved.
//================================================================================

package is.shelf.views;

import is.shelf.tools.SHLog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.GetDataCallback;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.ImageView;

public class AVImageView extends ImageView {

	private static final int MINIMUM_WIDTH = 160;
	private static final int MINIMUM_Height = 160;
	private final Context context;
	private AVFile imageFile;

	public AVImageView(Context mContext) {
		super(mContext);
		context = mContext;
	}

	public AVImageView(Context mContext, AttributeSet attrs) {
		super(mContext, attrs);
		context = mContext;
	}

	public AVImageView(Context mContext, AttributeSet attrs, int defStyle) {
		super(mContext, attrs, defStyle);
		context = mContext;
	}

	public void setAVFile(AVFile image) {
		imageFile = image;
	}

	public void loadInBackground() {
		if (imageFile != null) {
			this.post(new Runnable() {
				@Override
				public void run() {
					new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								int width = AVImageView.this.getWidth();
								if (width < MINIMUM_WIDTH) {
									width = MINIMUM_WIDTH;
								}
								int height = AVImageView.this.getHeight();
								if (height < MINIMUM_Height) {
									height = MINIMUM_Height;
								}
								String urlString = imageFile.getThumbnailUrl(
										false, width, height);
								URL url = new URL(urlString);
								InputStream is;
								is = url.openStream();
								final Bitmap bitmap = BitmapFactory
										.decodeStream(is);
								Activity activity = (Activity) context;
								activity.runOnUiThread(new Runnable() {
									public void run() {
										AVImageView.this.setImageBitmap(bitmap);
									}
								});
								is.close();
//								if (!imageFile.isDirty()) {
//									try {
//										byte[] data = imageFile.getData();
//										final Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//										Activity activity = (Activity) context;
//										activity.runOnUiThread(new Runnable() {
//											public void run() {
//												AVImageView.this.setImageBitmap(bitmap);
//											}
//										});
//									} catch (AVException e) {
//										SHLog.e(e.getLocalizedMessage());
//									}
//								} else {
//									String urlString = imageFile.getThumbnailUrl(
//											false, width, height);
//									URL url = new URL(urlString);
//									InputStream is;
//									is = url.openStream();
//									final Bitmap bitmap = BitmapFactory
//											.decodeStream(is);
//									Activity activity = (Activity) context;
//									activity.runOnUiThread(new Runnable() {
//										public void run() {
//											AVImageView.this.setImageBitmap(bitmap);
//										}
//									});
//									is.close();
//								}
							} catch (IOException e) {
								SHLog.e(e.getMessage());
							}
						}
					}).start();
				}
			});
		} else {
			SHLog.e("AVImageView failed to load because image is not set.");
		}
	}

	public void loadInBackground(final Boolean scaleToFit) {
		if (imageFile != null) {
			this.post(new Runnable() {
				@Override
				public void run() {
					new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								int width = AVImageView.this.getWidth();
								if (width < MINIMUM_WIDTH) {
									width = MINIMUM_WIDTH;
								}
								int height = AVImageView.this.getHeight();
								if (height < MINIMUM_Height) {
									height = MINIMUM_Height;
								}
								String urlString = imageFile.getThumbnailUrl(
										scaleToFit, width, height);
								URL url = new URL(urlString);
								InputStream is;
								is = url.openStream();
								final Bitmap bitmap = BitmapFactory
										.decodeStream(is);
								Activity activity = (Activity) context;
								activity.runOnUiThread(new Runnable() {
									public void run() {
										AVImageView.this.setImageBitmap(bitmap);
									}
								});
								is.close();
//								if (!imageFile.isDirty()) {
//									try {
//										byte[] data = imageFile.getData();
//										final Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//										Activity activity = (Activity) context;
//										activity.runOnUiThread(new Runnable() {
//											public void run() {
//												AVImageView.this.setImageBitmap(bitmap);
//											}
//										});
//									} catch (AVException e) {
//										SHLog.e(e.getLocalizedMessage());
//									}
//								} else {
//									String urlString = imageFile.getThumbnailUrl(
//											scaleToFit, width, height);
//									URL url = new URL(urlString);
//									InputStream is;
//									is = url.openStream();
//									final Bitmap bitmap = BitmapFactory
//											.decodeStream(is);
//									Activity activity = (Activity) context;
//									activity.runOnUiThread(new Runnable() {
//										public void run() {
//											AVImageView.this.setImageBitmap(bitmap);
//										}
//									});
//									is.close();
//								}
							} catch (IOException e) {
								SHLog.e(e.getMessage());
							}
						}
					}).start();
				}
			});
		} else {
			SHLog.e("AVImageView failed to load because image is not set.");
		}
	}

	public void loadInBackground(final GetDataCallback callback) {
		if (imageFile != null) {
			this.post(new Runnable() {
				@Override
				public void run() {
					new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								int width = AVImageView.this.getWidth();
								if (width < MINIMUM_WIDTH) {
									width = MINIMUM_WIDTH;
								}
								int height = AVImageView.this.getHeight();
								if (height < MINIMUM_Height) {
									height = MINIMUM_Height;
								}
								String urlString = imageFile.getThumbnailUrl(
										false, width, height);
								URL url = new URL(urlString);
								InputStream is;
								is = url.openStream();
								final Bitmap bitmap = BitmapFactory
										.decodeStream(is);
								Activity activity = (Activity) context;
								activity.runOnUiThread(new Runnable() {
									public void run() {
										AVImageView.this.setImageBitmap(bitmap);
									}
								});
								is.close();
								if (callback != null) {
									ByteArrayOutputStream baos = new ByteArrayOutputStream();
									bitmap.compress(Bitmap.CompressFormat.PNG, 100,
											baos);
									byte[] data = baos.toByteArray();
									callback.done(data, null);
								}
//								if (!imageFile.isDirty()) {
//									try {
//										byte[] data = imageFile.getData();
//										final Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//										Activity activity = (Activity) context;
//										activity.runOnUiThread(new Runnable() {
//											public void run() {
//												AVImageView.this.setImageBitmap(bitmap);
//											}
//										});
//									} catch (AVException e) {
//										SHLog.e(e.getLocalizedMessage());
//									}
//								} else {
//									String urlString = imageFile.getThumbnailUrl(
//											false, width, height);
//									URL url = new URL(urlString);
//									InputStream is;
//									is = url.openStream();
//									final Bitmap bitmap = BitmapFactory
//											.decodeStream(is);
//									Activity activity = (Activity) context;
//									activity.runOnUiThread(new Runnable() {
//										public void run() {
//											AVImageView.this.setImageBitmap(bitmap);
//										}
//									});
//									is.close();
//									if (callback != null) {
//										ByteArrayOutputStream baos = new ByteArrayOutputStream();
//										bitmap.compress(Bitmap.CompressFormat.PNG, 100,
//												baos);
//										byte[] data = baos.toByteArray();
//										callback.done(data, null);
//									}
//								}
							} catch (final IOException e) {
								Activity activity = (Activity) context;
								activity.runOnUiThread(new Runnable() {
									public void run() {
										SHLog.e(e.getMessage());
										SHLog.e("AVImageView failed to load because image is not set.");
										AVException e1 = new AVException(
												"AVImageView", e.getCause());
										callback.done(null, e1);
									}
								});
							}
						}
					}).start();
				}
			});
		} else {
			SHLog.e("AVImageView failed to load because image is not set.");
		}
	}

}
