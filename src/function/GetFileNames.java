package function;

import java.io.File;//乱码
import java.util.ArrayList;
import java.util.Arrays;

public class GetFileNames implements FileOperation
{
	public String doFileOperation(String[] cmds) {
		return "";
	}
    public static String [] getFileName(String path)
    {
        File file = new File(path);
        String [] fileName = file.list();
        return fileName;
    }
    public static void getAllFileName(String path,ArrayList<String> fileName)//�õ�Ŀ¼�����е��ļ�/�ļ�����
    {
        File file = new File(path);
        File [] files = file.listFiles();
        String [] names = file.list();
        if(names != null)
        fileName.addAll(Arrays.asList(names));
        for(File a:files)
        {
            if(a.isDirectory())
            {
                getAllFileName(a.getAbsolutePath(),fileName);
            }
        }
    }
}