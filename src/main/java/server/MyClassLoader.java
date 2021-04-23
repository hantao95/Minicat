package server;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyClassLoader extends ClassLoader{
	
	
	//指定路径
    private String path ;
 
 
    public MyClassLoader(String classPath){
        path=classPath;
    }
	
	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		
		 
		Class clazz = null;
        // 获取该class文件字节码数组
        byte[] classData = getData();
 
        if (classData != null) {
            // 将class的字节码数组转换成Class类的实例
        	clazz = defineClass(name, classData, 0, classData.length);
        }
        return clazz;
	}
	
	 /**
     * 将class文件转化为字节码数组
     * @return
     */
    private byte[] getData() {
 
        File file = new File(path);
        if (file.exists()){
            FileInputStream in = null;
            ByteArrayOutputStream out = null;
            try {
                in = new FileInputStream(file);
                out = new ByteArrayOutputStream();
 
                byte[] buffer = new byte[1024];
                int size = 0;
                while ((size = in.read(buffer)) != -1) {
                    out.write(buffer, 0, size);
                }
 
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
 
                    e.printStackTrace();
                }
            }
            return out.toByteArray();
        }else{
            return null;
        }
 
 
    }
}
