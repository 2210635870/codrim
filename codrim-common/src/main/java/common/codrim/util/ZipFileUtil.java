package common.codrim.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileUtil {
	
	public static byte[] zipFiles (List<File> list) throws IOException {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		
		ZipOutputStream zipOut = new ZipOutputStream(byteOut);
		
		BufferedOutputStream out = new BufferedOutputStream(zipOut);
		
		for(File item : list) {
			if( item.exists() && item.isFile() ) {
				zipOut.putNextEntry(new ZipEntry(item.getName()));
				
				FileInputStream in = new FileInputStream(item);
				BufferedInputStream bi = new BufferedInputStream(in);
				
				int b;
				while((b=bi.read()) != -1) {
					out.write(b);
				}
				
				bi.close();
				in.close();
			}
		}
		
		byteOut.flush();
		zipOut.flush();
		out.flush();
		byteOut.close();
		zipOut.close();
		out.close();
		
		return byteOut.toByteArray();
	}
	
	
	public static void main(String[] args) {
		List<File> list = new ArrayList<File>();
		
		list.add(new File("E:\\资料\\魏\\imgs\\20150421200455nirax.png.jpg"));
		list.add(new File("E:\\资料\\魏\\imgs\\201504171518462mmit.png"));
		
		byte[] data;
		try {
			data = zipFiles(list);
			System.out.println(data.length);
			
			FileOutputStream out = new FileOutputStream("F:\\壁纸.zip");
			
			out.write(data);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
