package com.codrim.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codrim.util.ParametersDebugUtils;

import common.codrim.common.Contans;
import common.codrim.common.SelectResultByCodition;
import common.codrim.pojo.TbWzGameH5;
import common.codrim.service.WzGameH5Service;
import common.codrim.util.PropertiesUtil;

@Controller
public class GameController extends BaseController{
	@Autowired
	WzGameH5Service h5Service;
	private static final Logger log = Logger.getLogger("yj");

	/**
	 * 获取h5游戏列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/h5List.do")
	@ResponseBody
	public Map getGameH5List(HttpServletRequest request){
		List<TbWzGameH5> list = new ArrayList<TbWzGameH5>();
		int startPage = 0;
		try {
			startPage = Integer.parseInt(request.getParameter("page"));
		} catch (NumberFormatException e) {
			startPage = 1;
			log.error("----------------------------------传入分页参数错误startPage"
					+ startPage + ":::" + e);
		}
		int size = 0;
		try {
			size = Integer.parseInt(request.getParameter("size"));
		} catch (NumberFormatException e) {
			size = 10;
			log.error("----------------------------------传入分页参数错误size" + size
					+ ":::" + e);
		}
		ParametersDebugUtils.debugParameters(request, "h5List", "page","size");

		SelectResultByCodition codition=new SelectResultByCodition();
		codition.setSize(size);
		codition.setStartPage((startPage-1)*size);
		codition.setRunningStatus(Contans.RUNNING_STATUS_RUNNING);
		 list = h5Service.getGameH5List(codition);
			String img=PropertiesUtil.getValue("/wz.properties", "img_project");
		 if(list.size()>0){
			 for(TbWzGameH5 h5:list){
				 h5.setGameIcon(img+h5.getGameIcon());
			 }
		 }
		 
     int total=h5Service.getTotalGameH5(codition);
		Map map=new HashMap();
		map.put("total", total);
		map.put("rows", list);
		return map;
		
	}

}
