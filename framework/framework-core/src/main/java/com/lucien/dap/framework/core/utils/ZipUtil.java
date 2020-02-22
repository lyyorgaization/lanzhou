package com.lucien.dap.framework.core.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Zip压缩/解压缩/文件工具类
 * 实现对目标路径及其子路径下的所有文件及空目录的压缩
 *
 * @author guwm
 * @version v0.1, 19/09/16
 */

@Slf4j
public class ZipUtil {
    /**
     * 缓冲器大小
     */
    private static final int BUFFER = 512;

    /**
     * 取的给定源目录下的所有文件及空的子目录
     * 递归实现
     *
     * @param srcFile
     * @return
     */
    private static List<File> getAllFiles(File srcFile) {
        List<File> fileList = new ArrayList<>();
        File[] tmp = srcFile.listFiles();

        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i].isFile()) {
                fileList.add(tmp[i]);
                log.info("add file: " + tmp[i].getName());
            }

            if (tmp[i].isDirectory()) {
                if (tmp[i].listFiles().length != 0) {
                    //若不是空目录，则递归添加其下的目录和文件
                    fileList.addAll(getAllFiles(tmp[i]));
                } else {
                    //若是空目录，则添加这个目录到fileList
                    fileList.add(tmp[i]);
                    log.info("add empty dir: " + tmp[i].getName());
                }
            }
        }

        return fileList;
    }

    /**
     * 取相对路径
     * 依据文件名和压缩源路径得到文件在压缩源路径下的相对路径
     *
     * @param dirPath 压缩源路径
     * @param file
     * @return 相对路径
     */
    private static String getRelativePath(String dirPath, File file) {
        File dir = new File(dirPath);
        String relativePath = file.getName();
        while (true) {
            file = file.getParentFile();
            if (file == null) {
                break;
            }

            if (file.equals(dir)) {
                break;
            } else {
                relativePath = file.getName() + "/" + relativePath;
            }
        }

        return relativePath;
    }

    /**
     * 创建文件
     * 根据压缩包内文件名和解压缩目的路径，创建解压缩目标文件，
     * 生成中间目录
     *
     * @param dstPath  解压缩目的路径
     * @param fileName 压缩包内文件名
     * @return 解压缩目标文件
     * @throws IOException
     */
    private static File createFile(String dstPath, String fileName) throws IOException {
        String[] dirs = fileName.split("/");//将文件名的各级目录分解
        File file = new File(dstPath);
        if (dirs.length > 1) {//文件有上级目录
            for (int i = 0; i < dirs.length - 1; i++) {
                file = new File(file, dirs[i]);//依次创建文件对象知道文件的上一级目录
            }

            if (!file.exists()) {
                file.mkdirs();//文件对应目录若不存在，则创建
                log.info("createFile mkdirs: " + file.getCanonicalPath());
            }

            file = new File(file, dirs[dirs.length - 1]);//创建文件
            return file;
        } else {
            if (!file.exists()) {
                file.mkdirs();//若目标路径的目录不存在，则创建
                log.info("createFile mkdirs: " + file.getCanonicalPath());
            }

            file = new File(file, dirs[0]);//创建文件
            return file;
        }
    }

    /**
     * 解压缩方法
     *
     * @param zipFileName 压缩文件名
     * @param dstPath     解压目标路径
     * @return
     */
    public static boolean unzip(String zipFileName, String dstPath) {
        log.info("zip uncompressing...");

        try {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFileName));
            ZipEntry zipEntry = null;
            byte[] buffer = new byte[BUFFER];//缓冲器
            int readLength = 0;//每次读出来的长度

            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                if (zipEntry.isDirectory()) {//若是zip条目目录，则需创建这个目录
                    File dir = new File(dstPath + "/" + zipEntry.getName());
                    if (!dir.exists()) {
                        dir.mkdirs();
                        log.info("unzip mkdirs: " + dir.getCanonicalPath());
                        continue;//跳出
                    }
                }

                File file = createFile(dstPath, zipEntry.getName());//若是文件，则需创建该文件
                log.info("file created: " + file.getCanonicalPath());
                OutputStream outputStream = new FileOutputStream(file);
                while ((readLength = zipInputStream.read(buffer, 0, BUFFER)) != -1) {
                    outputStream.write(buffer, 0, readLength);
                }

                outputStream.close();
                log.info("file uncompressed: " + file.getCanonicalPath());
            }
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            log.error("unzip fail!");
            return false;
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            log.error("unzip fail!");
            return false;
        }

        log.info("unzip success!");
        return true;
    }

    /**
     * 压缩方法
     * （可以压缩空的子目录）
     *
     * @param srcPath     压缩源路径
     * @param zipFileName 目标压缩文件
     * @return
     */
    public static boolean zip(String srcPath, String zipFileName) {
        log.info("zip compressing...");

        File srcFile = new File(srcPath);
        List<File> fileList = getAllFiles(srcFile);//所有要压缩的文件
        byte[] buffer = new byte[BUFFER];//缓冲器
        ZipEntry zipEntry = null;
        int readLength = 0;//每次读出来的长度

        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFileName));

            for (File file : fileList) {
                if (file.isFile()) {//若是文件，则压缩这个文件
                    zipEntry = new ZipEntry(getRelativePath(srcPath, file));
                    zipEntry.setSize(file.length());
                    zipEntry.setTime(file.lastModified());
                    zipOutputStream.putNextEntry(zipEntry);

                    InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                    while ((readLength = inputStream.read(buffer, 0, BUFFER)) != -1) {
                        zipOutputStream.write(buffer, 0, readLength);
                    }

                    inputStream.close();
                    log.info("file compressed: " + file.getCanonicalPath());
                } else {//若是目录（即空目录）则将这个目录写入zip条目
                    zipEntry = new ZipEntry(getRelativePath(srcPath, file) + "/");
                    zipOutputStream.putNextEntry(zipEntry);
                    log.info("dir compressed: " + file.getCanonicalPath() + "/");
                }
            }

            zipOutputStream.close();
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            log.error("zip fail!");
            return false;
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            log.error("zip fail!");
            return false;
        }

        log.info("zip success!");
        return true;
    }

    /**
     * 根据byte数组，生成文件
     *
     * @param bytes    文件数组
     * @param filePath 文件存放路径
     * @param fileName 文件名称
     */
    public static void bytesToFile(byte[] bytes, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            //判断文件目录是否存在
            if (!dir.exists() && !dir.isDirectory()) {
                dir.mkdirs();
            }
            file = new File(filePath + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                log.error(e.getMessage());
//                e.printStackTrace();
            }
        }
    }

    public static List<Map<String, Object>> readZipFile(String file) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        try {
            ZipFile zf = new ZipFile(file);
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            ZipInputStream zipInputStream = new ZipInputStream(inputStream);
            //定义ZipEntry,避免由于重复调用zipInputStream.getNextEntry造成的不必要的问题
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                if (zipEntry.isDirectory()) {

                } else {
                    Map<String, Object> result = new HashMap<>();
                    result.put("filePath", zipEntry.getName());
                    result.put("fileSize", zipEntry.getSize());
                    long size = zipEntry.getSize();
                    if (size > 0) {
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader(zf.getInputStream(zipEntry)));
                        String line;
                        while ((line = br.readLine()) != null) {
                            result.put("fileContent", line);
                        }
                        br.close();
                    }
                    resultList.add(result);
                }
            }
            zipInputStream.closeEntry();
            inputStream.close();
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

        return resultList;
    }

    public static Map<String, byte[]> unZip(byte[] file) throws IOException {
        ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(file));
        ZipEntry zipEntry = null;
        Map<String, byte[]> map = new HashMap<>();
        while (null != (zipEntry = zis.getNextEntry())) {
            if (!zipEntry.isDirectory()) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                boolean isRead = false;
                while (-1 != (len = zis.read(buffer))) {
                    baos.write(buffer, 0, len);
                    isRead = true;
                }
                if (isRead) {
                    map.put(zipEntry.getName(), baos.toByteArray());
                }
            }
        }
        return map;
    }

    public static byte[] zip(Map<String, byte[]> fileMap) throws IOException {
        ZipOutputStream zos = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        zos = new ZipOutputStream(baos);
        for (Map.Entry<String, byte[]> entry : fileMap.entrySet()) {
            String path = entry.getKey();
            zos.putNextEntry(new ZipEntry(path));
            zos.write(entry.getValue());
        }
        zos.close();
        return baos.toByteArray();
    }
}
