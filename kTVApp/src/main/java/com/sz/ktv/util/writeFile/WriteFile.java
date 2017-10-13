
package com.sz.ktv.util.writeFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.text.TextUtils;

import com.sz.ktv.application.MyApplication;
import com.sz.ktv.util.FileUtils;

public class WriteFile
{
    
    private static volatile ExecutorService executor = null;
    
    protected static void setExecutor(ExecutorService executor)
    {
        WriteFile.executor = executor;
    }
    
    protected static synchronized void log2file(final String path, final String str)
    {
        if (executor == null)
        {
            executor = Executors.newSingleThreadExecutor();
        }
        
        if (executor != null)
        {
            executor.execute(new Runnable()
            {
                @Override
                public void run()
                {
                    
                    File file = getFileFromPath(path);
                    if (null != file && file.exists())
                    {
                        FileOutputStream fOut = null;
                        OutputStreamWriter osw = null;
                        BufferedWriter bWrite = null;
                        PrintWriter out = null;
                        try
                        {
                            fOut = new FileOutputStream(file, true);
                            osw = new OutputStreamWriter(fOut, "utf-8");
                            // out = new PrintWriter(new BufferedWriter(
                            // new FileWriter(file, true)));
                            bWrite = new BufferedWriter(osw);
                            out = new PrintWriter(bWrite);
                            out.println(str);
                            out.flush();
                            
                        }
                        catch (IOException e)
                        {
                            Write.debug(e.getMessage()+"");
                        }
                        finally
                        {
                            if (null != out)
                            {
                                out.close();
                                out = null;
                            }
                            if (null != bWrite)
                            {
                                try
                                {
                                    bWrite.close();
                                    bWrite = null;
                                }
                                catch (IOException e)
                                {
                                	Write.debug(e.getMessage()+"");
                                }
                            }
                            if (null != osw)
                            {
                                try
                                {
                                    osw.close();
                                    osw = null;
                                }
                                catch (IOException e)
                                {
                                	Write.debug(e.getMessage()+"");
                                }
                            }
                            if (null != fOut)
                            {
                                try
                                {
                                    fOut.close();
                                    fOut = null;
                                }
                                catch (IOException e)
                                {
                                	Write.debug(e.getMessage()+"");
                                }
                            }
                        }
                    }
                }
            });
        }
    }
    
    /**
     * 
     * @param path 文件路径或者文件名称
     * @param str 文件内容
     * @param savePath 保存位置 true 保存在SDcard false 保存在内存里面
     */
    protected static void log2file(final String path, final String str, final boolean savePath)
    {
        if (executor == null)
        {
            executor = Executors.newSingleThreadExecutor();
        }
        if (null != executor)
        {
            executor.execute(new Runnable()
            {
                @Override
                public void run()
                {
                    if (savePath)
                    {
                        FileOutputStream fOut = null;
                        OutputStreamWriter osw = null;
                        BufferedWriter bWrite = null;
                        PrintWriter out = null;
                        File file = getFileFromPath(path);
                        if (file != null)
                        {
                            try
                            {
                                fOut = new FileOutputStream(file, true);
                                osw = new OutputStreamWriter(fOut, "UTF-8");
                                bWrite = new BufferedWriter(osw);
                                out = new PrintWriter(bWrite);
                                out.println(str);
                                out.flush();
                            }
                            catch (IOException e)
                            {
                                Write.debug("" + e.getMessage());
                            }
                            finally
                            {
                                    if (null != fOut)
                                    {
                                        try {
											fOut.close();
											fOut=null;
										} catch (IOException e) {
											Write.debug(e.getMessage()+"");
										}
                                    }
                                    if (null != osw)
                                    {
                                        try {
											osw.close();
											osw=null;
										} catch (IOException e) {
											Write.debug(e.getMessage()+"");
										}
                                    }
                                    if (null != bWrite)
                                    {
                                        try {
											bWrite.close();
											bWrite=null;
										} catch (IOException e) {
											Write.debug(e.getMessage()+"");
										}
                                    }
                                    if (out != null){
                                    	out.close();
                                    	out=null;
                                    }
                            }
                        }
                    }
                    else
                    {
                        saveDataFile(path, str);
                    }
                }
            });
            
        }
    }
    
    protected static void saveDataFile(String path, String str)
    {
        PrintWriter out = null;
        OutputStreamWriter osw = null;
        FileOutputStream fOut = null;
        BufferedWriter bWrite = null;
        File file = new File(MyApplication.getInstance().getFilesDir() + "/" + path);
        
        try
        {
            if (!file.exists())
            {
                boolean ble = file.createNewFile();
//                FileUtils.scanSdCard(file);
                Write.i("flag:" + ble);
            }
            fOut = new FileOutputStream(file, true);
            osw = new OutputStreamWriter(fOut, "utf-8");
            bWrite = new BufferedWriter(osw);
            out = new PrintWriter(bWrite);
            out.println(str);
            out.flush();
        }
        catch (IOException e)
        {
            Write.debug("" + e.getMessage());
        }
        finally
        {
            try
            {
                if (null != out)
                {
                    out.close();
                }
                if (null != bWrite)
                {
                    bWrite.close();
                }
                if (null != osw)
                {
                    osw.close();
                }
                if (null != fOut)
                {
                    fOut.close();
                }
                
            }
            catch (IOException e)
            {
                Write.debug("" + e.getMessage());
            }
            FileUtils.scanFile(file.getPath());
            
        }
        
    }
    
    public static File getFileFromPath(String path)
    {
        boolean isExist;
        File file = null;
        if (TextUtils.isEmpty(path))
        {
            return file;
        }
        file = new File(path);
        
        isExist = file.exists();
        
        if (!isExist)
        {
            try
            {
                boolean result = file.createNewFile();
                if(result)
                {
                FileUtils.scanSdCard(file);
                }
//                Write.i("result:" + result + "");
            }
            catch (IOException e)
            {
                Write.debug(e.getMessage()+"");
            }
        }
        
        return file;
    }
}
