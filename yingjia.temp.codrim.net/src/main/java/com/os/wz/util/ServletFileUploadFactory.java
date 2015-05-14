package com.os.wz.util;


import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import common.codrim.util.PropertiesUtil;

public class ServletFileUploadFactory {
    
    private static final String UPLOAD_SIZE_MAX = PropertiesUtil.getValue("/os.properties","uploadSizeMax");
    
    public static ServletFileUpload servletFileUpload;
    
    public static synchronized ServletFileUpload getServletFileUpload() {
        if (servletFileUpload==null) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            servletFileUpload = new ServletFileUpload(factory);
            servletFileUpload.setHeaderEncoding("UTF-8");
            servletFileUpload.setSizeMax(Long.parseLong(UPLOAD_SIZE_MAX));
        }
        return servletFileUpload;
    }
}
