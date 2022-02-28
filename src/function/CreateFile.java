package function;

import java.io.File;
import java.io.IOException;

public class CreateFile implements FileOperation{
	public String doFileOperation(String[] cmds) {
		return "";
	}
	public boolean createFile(File path) throws IOException{
        if (path.createNewFile()) {
            System.out.println("文件创建成功！");
            return true;
        }
        return false;
	}
	
	
	public boolean mkdir(File path)
	{
		if(path.mkdir())
		{
			System.out.println("创建文件夹成功");
			return true;
		}
		return false;
	}
}
