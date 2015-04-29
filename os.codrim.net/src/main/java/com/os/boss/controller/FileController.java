/**
 * 
 */
package com.os.boss.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.os.boss.util.FileUtil;

import common.codrim.common.ResultJsonBean;

/**
 * @author Administrator
 *
 */
@Controller
public class FileController {

	
	
	@RequestMapping("/uploadImage.do")
	@ResponseBody
	public ResultJsonBean uploadImage(HttpServletRequest request) throws Exception {
		ResultJsonBean bean=new ResultJsonBean();
		bean=FileUtil.saveFile(request,bean);
		return bean;
	}
	@RequestMapping("/uploadApk.do")
	@ResponseBody
	public ResultJsonBean uploadApk(HttpServletRequest request) throws Exception {
		ResultJsonBean bean=new ResultJsonBean();
		bean=FileUtil.saveFile(request,bean);
		return bean;
	}
}
