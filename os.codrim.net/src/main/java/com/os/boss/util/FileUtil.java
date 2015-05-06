/**
 * 
 */
package com.os.boss.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;




import common.codrim.common.ResultJsonBean;
import common.codrim.util.DateUtil;
import common.codrim.util.PropertiesUtil;

/**
 * @author Administrator
 *
 */
public class FileUtil {
	private static final Logger logger = Logger.getLogger("BOSS");
	private static final String imagePath=PropertiesUtil.getValue("/os.properties", "imagePath");
	private static final String defaultFormatName=PropertiesUtil.getValue("/os.properties", "default_format_name");
	
	  public static ResultJsonBean  saveFile(HttpServletRequest request,ResultJsonBean bean) throws Exception {
	        String orgiSavePath = null;
	        File file = null;
	        String type=request.getParameter("type");
	        String timePath=DateUtil.getNowDateToString(System.currentTimeMillis(), "yyyy-MM-dd");
	        boolean isSucc=false;
	        try {
	            logger.info("--------本地上传:   解析request");
	        	List<FileItem> listsFileItems=getFileItems(request);
	            Iterator<FileItem> fileItemIterator =listsFileItems .iterator();
	            while (fileItemIterator.hasNext()) {
	                FileItem fileItem = fileItemIterator.next();
	                //判断是文件上传
	                if (!fileItem.isFormField()) {
	                    String fileName = fileItem.getName();
	                    logger.info("--------本地上传文件名：" + fileName + " 开始上传");
	                    if (StringUtils.isBlank(fileName)) {
	                        continue;
	                    }
	                    orgiSavePath=getOrgiSaveFilePath(fileName,type,timePath);
	                    file = new File(orgiSavePath);
	                    //保存文件
	                    fileItem.write(file);
	                    isSucc=true;
	                    logger.info("----------本地上传文件名：" + fileName + " 上传结束");
	                }
	            }
	        
	        } catch (Exception e) {
                logger.info("----------本地上传文件名： 上传失败");
	            throw e;
	        }
	        bean.setSuccess(isSucc);
	        orgiSavePath = orgiSavePath.replaceAll("/codrim_resource/sources", "");
	        bean.setObject(orgiSavePath);
	        return bean;
	    }
	  
	   /**
	     * 
	     * @param imagePath （如：/usr/local/d/img/aca/icon）
	     * @param timePath （如：2013/02/28）
	     * @param imageName（如：0a64d50a-de5f-4c83-8421-e913ad5c3299）
	     * @param formatName
	     * @return
	     */
	    public static String getOrgiSaveFilePath(String fileName,String type,String timePath) {
	        String filePath = "";
	        String imageName=getNameByUUID();
	        String orgiDir = imagePath + "/" + type + "/" +timePath ;
	           mkDirs(orgiDir);
	           if(type.equals("apk")){
	   	        filePath = orgiDir + "/" + fileName ;
	           }else{
	   	        filePath = orgiDir + "/" + imageName + "." + defaultFormatName;
	           }
	        return filePath;
	    }
	    public static String getNameByUUID() {
	        return UUID.randomUUID().toString();
	    } 
	    public static boolean mkDirs(String filePath) {
	        boolean isSucc = false;
	        if(StringUtils.isBlank(filePath)) return isSucc;
	        File file = new File(filePath);
	        if (!file.exists()) {
	            isSucc = file.mkdirs();
	        }
	        return isSucc;
	    }
	    
    public static List<FileItem> getFileItems(HttpServletRequest request) {
        ServletFileUpload servletFileUpload = null;
        List<FileItem> fileItems = null;
        try {
            servletFileUpload = ServletFileUploadFactory.getServletFileUpload();
            fileItems = servletFileUpload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
            logger.info(e);
        }
        return fileItems;
    }
	
	
	
	
	
}
