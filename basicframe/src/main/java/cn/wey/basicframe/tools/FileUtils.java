
package cn.wey.basicframe.tools;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;

/**
 * 文件处理工具类
 * Created by wey on 2016/2/19.
 */
public class FileUtils {

    public static final double KB = 1024.0;
    public static final double MB = KB * KB;
    public static final double GB = KB * KB * KB;

    /**
     * 删除SD卡上某个文件
     *
     * @param target
     * @return
     */
    public static boolean deleteSDCardFile(String target) {
        File file = new File(target);
        if (file.exists()) {
            if (!file.isDirectory()) {
                return file.delete();
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * 创建文件夹
     *
     * @param name
     * @return
     */
    public static boolean createFolder(String name) {
        File folder = new File(name);
        if (folder.exists()) {
            return false;
        } else {
            return folder.mkdirs();
        }
    }

    /**
     * 创建文件
     *
     * @param name
     * @return
     */
    public static boolean createFile(String name) {
        if (TextUtils.isEmpty(name)) {
            return false;
        }
        File file = new File(name);
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取sd卡上文件路径
     *
     * @param context
     * @return
     */
    public static String getPath(Context context) {
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        File mf = null;
        if (sdCardExist) {
            mf = Environment.getExternalStorageDirectory();
        } else {
            mf = context.getFilesDir();
        }
        return mf.toString();
    }

    /**
     * 根据文件名创建文件夹
     *
     * @param context
     * @param folderName
     * @return
     */
    public static boolean createFolder(Context context, String folderName) {
        return createFolder(folderPath(context, folderName));
    }

    /**
     * @param context
     * @param fileName
     * @param folderName
     */
    public static void createFile(Context context, String fileName, String folderName) {
        createFile(folderPath(context, folderName) + fileName);
    }

    /**
     * @param context
     * @param folderName
     * @return
     */
    private static String folderPath(Context context, String folderName) {
        String folderPath = getPath(context) + File.separator + folderName + File.separator;
        return folderPath;
    }

    /**
     * 删除方法 这里只会删除某个文件夹下的文件，如果传入的path是个文件，将不做处理
     *
     * @param path
     */
    public static void deleteFilesByDirectory(String path) {
        File file = new File(path);
        if (file != null && file.exists() && file.isDirectory()) {
            for (File item : file.listFiles()) {
                item.delete();
            }
        }
    }

    /**
     * 文件是否存在
     *
     * @param path
     * @return
     */
    public static boolean isFileExist(String path) {
        File file = new File(path);
        return file.exists();
    }

    /**
     * 计算文件或者文件夹的大小 ，单位 MB
     *
     * @param file 要计算的文件或者文件夹 ， 类型：java.io.File
     * @return 大小，单位：MB
     */
    public static double getFileSize(File file) {
        //判断文件是否存在  
        if (file.exists()) {
            //如果是目录则递归计算其内容的总大小，如果是文件则直接返回其大小  
            if (!file.isFile()) {
                //获取文件大小  
                File[] fl = file.listFiles();
                double ss = 0;
                for (File f : fl)
                    ss += getFileSize(f);
                return ss;
            } else {
                double ss = (double) file.length() / MB;
                return ss;
            }
        } else {
            return 0.0;
        }
    }
}