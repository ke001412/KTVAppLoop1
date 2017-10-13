package com.sz.ktv.util.writeFile;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.sz.ktv.application.MyApplication;
import com.sz.ktv.db.DataBase;
import com.sz.ktv.util.FileUtils;


/**
 * 带日志文件输入的
 */
@SuppressLint("SimpleDateFormat")
public class Write
{

    private static String MYLOG_PATH_SDCARD_DIR = "";
    private static BufferedReader bReader = null;
    private static BufferedWriter bWriter = null;
    private static String mPath = "";

    private static final int MAXSIZE = 10 * 1024 * 1024;
    private static final int MAXSIZE_TWO = 20 * 1024 * 1024;

    static
    {
        String path = DataBase.MNT_SDA_DIR ;//MyApplication.getInstance().getSavePath();
        if (!TextUtils.isEmpty(path))
        {
            MYLOG_PATH_SDCARD_DIR = path
                + "/"
                + "ktv" + "/Applog/";
        }

    }

    private final static int SDCARD_LOG_FILE_SAVE_DAYS = 5;// sd卡中日志文件的最多保存天数
    private final static String MYLOGFILENAME = "_log.txt";// 本类输出的日志文件名称

    public final static String TAG = "MTS LOG";

    public static void debug(String msg)
    {
        writeLogtoFile(TAG, msg);
    }

    public static void i(String msg)
    {
        Log.i(TAG, msg);
    }

    private static long getFileDirSize(File dirFile)
    {
        long allSize = 0;
        FileInputStream fis = null;

        if (dirFile != null && dirFile.exists())
        {
            File[] fileList = dirFile.listFiles();
            if (null != fileList && fileList.length != 0)
            {
                int fsize = fileList.length;
                for (int i = 0; i < fsize; i++)
                {
                    try
                    {

                        File tempFile = fileList[i];
                        String filePath = tempFile.getName();
                        if (filePath.endsWith(".txt"))
                        {
                            fis = new FileInputStream(tempFile);
                            long size = fis.available();
                            allSize = allSize + size;
                        }
                    }
                    catch (IOException e)
                    {
                        writeLogtoFile(TAG, "readFileFailed：" + e.getMessage());
                        return allSize;
                    } finally
                    {
                        try
                        {
                            if (fis != null)
                            {
                                fis.close();
                            }
                        }
                        catch (IOException e2)
                        {
                            writeLogtoFile(TAG, "fileException" + e2.getMessage());
                        }
                    }
                }
            }
        }

        return allSize;

    }

    public static boolean checkFileSize()
    {

        File dataFile = MyApplication.getInstance().getFilesDir();

        long size = getFileDirSize(dataFile);
        if (size >= MAXSIZE)
        {
            if (size >= MAXSIZE_TWO)
            {
                return false;
            }
            return true;
        }

        return false;
    }

    public static void checkFileSizeMax()
    {

        File dataFile = MyApplication.getInstance().getFilesDir();
        long size = getFileDirSize(dataFile);

        if (size >= MAXSIZE_TWO)
        {

            saveFileAll();
            deleteFileAll();
        }

    }

    /**
     * 备份文件到SDcard
     */
    private static void saveFileAll()
    { }

    /**
     * 删除文件
     */
    private static void deleteFileAll()
    {

        Date nowtime = new Date();
        SimpleDateFormat logfile = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String nowDay = logfile.format(nowtime);

        File dataFile = MyApplication.getInstance().getFilesDir();
        File[] listFile = dataFile.listFiles();
        if (null != listFile && listFile.length != 0)
        {
            int size = listFile.length;
            for (int i = 0; i < size; i++)
            {
                File file = listFile[i];
                String fileName = file.getName();
                if (fileName.endsWith(".txt") && !fileName.contains(nowDay))
                {
                    boolean flag = file.delete();
                    Write.i("flag: " + flag);
                }

            }
        }

    }

    private final static int MAXLENGTH = 2 * 1024 * 1024;
    private static int after = 1;
    private static String path = "";

   
    /**
     * 打开日志文件并写入日志
     * 
     * @return
     * **/
    private static void writeLogtoFile(String tag, String text)
    {// 新建或打开日志文件
        Date nowtime = new Date();
        SimpleDateFormat logfile = new SimpleDateFormat("yyyy-MM-dd");// 日志文件格式
        String needWriteFiel = logfile.format(nowtime);
        SimpleDateFormat myLogSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");// 日志的输出格式

        String needWriteMessage = myLogSdf.format(nowtime) + "---" + "---" + "[ " + tag
            + " ] " + " [ " + text + " ]";

        String par = MYLOG_PATH_SDCARD_DIR + needWriteFiel + "/";
        File parEnt = new File(par);
        boolean mkFlag = true;

        if (FileUtils.sdCardIsExsit())
        {

            if (!parEnt.exists())
            {
                mkFlag = parEnt.mkdirs();
            }

            if (mkFlag)
            {
                File[] f = parEnt.listFiles();

                int len = 0;
                if (null != f)
                {
                    len = f.length;
                }
                if (len == 0)
                {
                    after = 1;
                    mPath = needWriteFiel + "-" + after + MYLOGFILENAME;

                }
                else
                {
                    mPath = needWriteFiel + "-" + after + MYLOGFILENAME;
                    File file = new File(path);
                    long fileLen = file.length();
                    if (fileLen >= MAXLENGTH)
                    {
                        after = after + 1;
                        mPath = needWriteFiel + "-" + after + MYLOGFILENAME;
                    }
                }

                path = par + mPath;
                WriteFile.log2file(path, needWriteMessage);
                FileUtils.scanFile(path);
            }

        }

    }

    /**
     * 删除指定时间内的日志文件
     * */
    public static void delFile()
    {
        // 日志文件格式
        SimpleDateFormat logfile = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String needDelFiel = logfile.format(getDateBefore());
        String path = MYLOG_PATH_SDCARD_DIR + needDelFiel;
        // 删除日志文件夹
        DelFile.delFolder(path);
    }

    /**
     * 得到现在时间前的几天日期，用来得到需要删除的日志文件名
     * */
    private static Date getDateBefore()
    {
        Date nowtime = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(nowtime);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - SDCARD_LOG_FILE_SAVE_DAYS);

        return now.getTime();
    }

    static final int MAX_STR_LEN_SIG = 20000;
    private static InputStreamReader is = null;
    private static OutputStreamWriter ow = null;
    private static FileInputStream fileIn = null;
    private static FileOutputStream fileOut = null;
    private static File filer = null;
    private static File filew = null;

    /**
     * 文件拷贝 private
     * 
     * @param readfile
     * @param writeFile
     */
    public static void fileCopy(String readfile, String writeFile)
    {

        try
        {
            filer = new File(readfile);
            fileIn = new FileInputStream(filer);
            is = new InputStreamReader(fileIn, "utf-8");
            bReader = new BufferedReader(is);
            filew = new File(writeFile);
            fileOut = new FileOutputStream(filew);
            ow = new OutputStreamWriter(fileOut, "utf-8");
            bWriter = new BufferedWriter(ow);

            StringBuffer line = new StringBuffer("");
            int intC;
            while ((intC = bReader.read()) != -1)
            {
                char c = (char) intC;
                if (c == '\n')
                {
                    String resultString = line.toString();
                    if (!TextUtils.isEmpty(resultString))
                    {
                        bWriter.write(line.toString());
                        bWriter.newLine();
                    }
                    line = new StringBuffer("");
                    continue;
                }
                if (line.length() >= MAX_STR_LEN_SIG)
                {
                    continue;
                }
                line.append(c);
            }
        }
        catch (IOException e)
        {
            Write.debug("" + e.getMessage());
        } finally
        {
            try
            {
                if (null != bReader)
                {
                    bReader.close();
                }

            }
            catch (IOException e2)
            {
                Write.debug("" + e2.getMessage());
            }

            if (null != is)
            {
                try
                {
                    is.close();
                }
                catch (IOException e)
                {
                    Write.debug("" + e.getMessage());
                }
            }
            if (null != fileIn)
            {
                try
                {
                    fileIn.close();
                }
                catch (IOException e)
                {
                    Write.debug("" + e.getMessage());
                }
            }
            if (null != bWriter)
            {
                try
                {
                    bWriter.close();
                }
                catch (IOException e)
                {
                    Write.debug("" + e.getMessage());
                }
            }
            if (null != ow)
            {
                try
                {
                    ow.close();
                }
                catch (IOException e)
                {
                    Write.debug("" + e.getMessage());
                }
            }
            if (null != fileOut)
            {
                try
                {
                    fileOut.close();
                }
                catch (IOException e)
                {
                    Write.debug("" + e.getMessage());
                }
            }

        }
    }
}
