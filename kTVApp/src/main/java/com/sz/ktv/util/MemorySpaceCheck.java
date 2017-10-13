package com.sz.ktv.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import android.os.StatFs;

public class MemorySpaceCheck {
	public static int lastSongNum = 0;
	private static final int SONG_SIZE = 50;
	/**
	 * 计算剩余空间
	 * 
	 * @param path
	 * @return
	 */
	public static String getAvailableSize(String path) {
		StatFs fileStats = new StatFs(path);
		fileStats.restat(path);
		long size = (long)fileStats.getFreeBlocks()
				* fileStats.getBlockSize();
		double mbSize = formetFileSize(size,3);
		lastSongNum = (int)mbSize/SONG_SIZE;
		String result = formetFileSize(size,4)+"GB";//getFormatSize(size);
		return result;
	}
 

	/**
	 * 计算总空间
	 * 
	 * @param path
	 * @return
	 */
	public static String getTotalSize(String path) {
		StatFs fileStats = new StatFs(path);
		fileStats.restat(path);
		long allSize = (long)fileStats.getBlockCount()
				* (long)fileStats.getBlockSize();
		String result = formetFileSize(allSize,4)+"GB" ;//getFormatSize(allSize);
		return result;
	}

	 
	public static final int SIZETYPE_B = 1;// 获取文件大小单位为B的double值
	public static final int SIZETYPE_KB = 2;// 获取文件大小单位为KB的double值
	public static final int SIZETYPE_MB = 3;// 获取文件大小单位为MB的double值
	public static final int SIZETYPE_GB = 4;// 获取文件大小单位为GB的double值
	/**
	 * 转换文件大小,指定转换的类型
	 * 
	 * @param fileS
	 * @param sizeType
	 * @return
	 */
	private static double formetFileSize(long fileS, int sizeType) {
		DecimalFormat df = new DecimalFormat("#.00");
		double fileSizeLong = 0;
		switch (sizeType) {
		case SIZETYPE_B:
			fileSizeLong = Double.valueOf(df.format((double) fileS));
			break;
		case SIZETYPE_KB:
			fileSizeLong = Double.valueOf(df.format((double) fileS / 1024));
			break;
		case SIZETYPE_MB:
			fileSizeLong = Double.valueOf(df.format((double) fileS / 1048576));
			break;
		case SIZETYPE_GB:
			fileSizeLong = Double.valueOf(df
					.format((double) fileS / 1073741824));
			break;
		default:
			break;
		}
		return fileSizeLong;
	}

	/**
	 * 格式化单位 转换为B,GB等等
	 * 
	 * @param size
	 * @return
	 */
	public static String getFormatSize(double size) {
		double kiloByte = size / 1024;
		if (kiloByte < 1) {
			return size + "Byte";
		}

		double megaByte = kiloByte / 1024;
		if (megaByte < 1) {
			BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
			return result1.setScale(1, BigDecimal.ROUND_HALF_UP)
					.toPlainString() + "KB";
		}

		double gigaByte = megaByte / 1024;
		if (gigaByte < 1) {
			BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
			return result2.setScale(1, BigDecimal.ROUND_HALF_UP)
					.toPlainString() + "MB";
		}

		double teraBytes = gigaByte / 1024;
		if (teraBytes < 1) {
			BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
			return result3.setScale(1, BigDecimal.ROUND_HALF_UP)
					.toPlainString() + "GB";
		}
		BigDecimal result4 = new BigDecimal(teraBytes);
		return result4.setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString()
				+ "TB";
	}

}
