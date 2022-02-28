package function;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import function.CreateFile;

public class EncryptionFile implements FileOperation{
//	private Key key;
//    private void setKey(String strKey)
//	{
//		try {
//			KeyGenerator generator=KeyGenerator.getInstance("DES");
//			generator.init(new SecureRandom(strKey.getBytes()));
//			this.key=generator.generateKey();
//		} catch (Exception e) {
//			// TODO: handle exception
//			throw new RuntimeException("Error"+e);
//		}
//	}
//	
	public String doFileOperation(String[] cmds) {
		return "";
	}
	private static final int numOfEncAndDec = 0x99; //加密解密秘钥
	private static int dataOfFile = 0; //文件字节内容
	public void encrypt(File file) throws Exception
	{
		if(!file.exists())
		{
			System.out.println("source file not exixt");
			return;
		}
		File out = new File(file.getParent() + File.separator + "temp");
        CreateFile createFile = new CreateFile();
        createFile.createFile(out);
		InputStream fis  = new FileInputStream(file);
		OutputStream fos = new FileOutputStream(out);
		while ((dataOfFile = fis.read()) > -1) 
		{
			fos.write(dataOfFile^numOfEncAndDec);
		}
			fis.close();
			fos.flush();
			fos.close();
            //关闭流后删除源文件，改目标文件的名字
            DeleteFile delete = new DeleteFile();
            System.out.println("删除源文件 " + (delete.deleteFile(file) ? "成功":"失败"));
            File result = new File(out.getPath());
            System.out.println("改名 "+ (result.renameTo(file) ? "成功":"失败") + ": " + result.getName() );
	}

	/*
	 * 解密算法
	 * 
	 */
	public static void decrypt(File file) throws Exception{
			if(!file.exists()){
			System.out.println("encrypt file not exixt");
			return;
			}
			InputStream fis  = new FileInputStream(file);
			File out = new File(file.getParent() + File.separator + "temp");
            CreateFile createFile = new CreateFile();
            createFile.createFile(out);
	 		OutputStream fos = new FileOutputStream(out);
	 		while ((dataOfFile = fis.read()) > -1) {
	 			fos.write(dataOfFile^numOfEncAndDec);
	 		}
			fis.close();
			fos.flush();
		  	fos.close();
            DeleteFile delete = new DeleteFile();
            System.out.println("删除源文件 " + (delete.deleteFile(file) ? "成功" : "失败"));
            File result = new File(out.getPath());
            System.out.println("改名 " + (result.renameTo(file) ? "成功":"失败"));
		}
}
