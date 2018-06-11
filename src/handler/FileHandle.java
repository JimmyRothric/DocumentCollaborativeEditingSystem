package handler;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import dao.*;
import entity.*;

public class FileHandle {
	
	public static String getComparison(Document oldd,Document newd) {
		File old_file = new File(oldd.getPath());
		File new_file = new File(newd.getPath());
		
		try {
			InputStreamReader reader1 = new InputStreamReader(new FileInputStream(old_file));
		    BufferedReader br1 = new BufferedReader(reader1); 
			InputStreamReader reader2 = new InputStreamReader(new FileInputStream(new_file));
		    BufferedReader br2 = new BufferedReader(reader2); 
	        String line = "";  
	          
	        while (br1.read()>0) {  
	             line = br1.readLine(); // 一次读入一行数据  
	        }  
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	public static String makePath(String filename,String savePath){
	    //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
		int hashcode = filename.hashCode();
		int dir1 = hashcode&0xf;  //0--15
		int dir2 = (hashcode&0xf0)>>4;  //0-15
		//构造新的保存目录
		String dir = savePath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
		//File既可以代表文件也可以代表目录
		File file = new File(dir);
		//如果目录不存在
		if(!file.exists()){
			//创建目录
			file.mkdirs();
		}
		return dir;
	 }
	
	public static String findFileSavePathByFileName(String filename,String saveRootPath){
		int hashcode = filename.hashCode();
		int dir1 = hashcode&0xf;  //0--15
		int dir2 = (hashcode&0xf0)>>4;  //0-15
		String dir = saveRootPath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
		File file = new File(dir);
		if(!file.exists()){
		    //创建目录
		    file.mkdirs();
		}
		return dir;
	}
	
	
	public static List readTxtFile(String filePath){
		List list=new ArrayList();
        try {
                String encoding="UTF-8";
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    while((lineTxt = bufferedReader.readLine()) != null){                        
                        list.add(lineTxt);
                    }
                    read.close();
                }else{
                	System.out.println("找不到指定的文件");
                }
        }catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return list;
    }
	
	public static ArrayList<Integer> compare(String filePath1,String filePath2){
		ArrayList<Integer> difList = new ArrayList<Integer>();
		List list = readTxtFile(filePath1);
		List list2 = readTxtFile(filePath2);
		for(int i = 0; i < list.size(); i++) {
			if(!list2.contains(list.get(i))){
				//System.out.println("不相同的是=="+list.get(i));
				difList.add(i);
			}
		}
		return difList;
	}
	

}
