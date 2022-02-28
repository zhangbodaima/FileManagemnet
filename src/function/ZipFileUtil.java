package function;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipFileUtil implements FileOperation{
	public String doFileOperation(String[] cmds) {
		return "";
	}
    private void compressFile(File file, ZipOutputStream zipOut) throws Exception {
        byte[] buffer = new byte[1024];
        InputStream input = new FileInputStream(file);
        zipOut.putNextEntry(new ZipEntry(file.getName()));
        int i;
        while ((i = input.read(buffer)) != -1) {
            zipOut.write(buffer, 0, i);
        }
        input.close();
    }

    private void compressFile(File file, ZipOutputStream zipOut, String baseDir) throws Exception {
        byte[] buffer = new byte[1024];
        InputStream input = new FileInputStream(file);
        zipOut.putNextEntry(new ZipEntry(baseDir + file.getName()));
        int i;
        while ((i = input.read(buffer)) != -1) {
            zipOut.write(buffer, 0, i);
        }
        input.close();
    }


    
    public boolean zipMultiFile(File file, String zipName, boolean dirFlag) throws Exception {
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(new File(file.getParent() + File.separator + zipName + ".zip")));
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                if (files.length == 0) {
                    zipOut.putNextEntry(new ZipEntry(file.getName() + File.separator));
                }
                for (File fileSec : files) {
                    if (dirFlag) {
                        recursionZip(zipOut, fileSec, file.getName() + File.separator);
                    } else {
                        recursionZip(zipOut, fileSec, "");
                    }
                }
                zipOut.close();
                return true;
            }
        } else if (file.isFile()) {
            compressFile(file, zipOut);
            zipOut.close();
            return true;
        } else {
            zipOut.close();
            return false;
        }

        return dirFlag;
    }

    private void recursionZip(ZipOutputStream zipOut, File file, String baseDir) throws Exception {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                if (files.length == 0) {
                    zipOut.putNextEntry(new ZipEntry(baseDir + file.getName() + File.separator));
                } else {
                    for (File fileSec : files) {
                        recursionZip(zipOut, fileSec, baseDir + file.getName() + File.separator);
                    }
                }
            }
        } else {
            compressFile(file, zipOut, baseDir);
        }
    }

    public boolean unZip(File srcFile, File destDir) throws IOException {
        FileInputStream fis = new FileInputStream(srcFile);
        CheckedInputStream csumi = new CheckedInputStream(fis, new Adler32());
        ZipInputStream zis = new ZipInputStream(csumi);
        BufferedInputStream bis = new BufferedInputStream(zis);
        ZipEntry ze;
        File file_out;
        while ((ze = zis.getNextEntry()) != null) {
            if (!ze.isDirectory()) {
                System.out.println("解压缩" + ze.getName() + "  文件");
                file_out = new File(destDir, ze.getName());
                if (!file_out.exists()) {
                    new File(file_out.getParent()).mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(file_out);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                int x;
                while ((x = bis.read()) != -1) {
                    bos.write(x);
                }
                bos.close();
                fos.close();
            } else {
                System.out.println("解压缩" + ze.getName() + " 文件夹");
                new File(destDir, ze.getName()).mkdirs();
            }

        }
        bis.close();
        zis.close();
        return true;
    }
}
