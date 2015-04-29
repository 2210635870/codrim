package com.codrim.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codrim.bean.JsonBasicResult;
import com.codrim.bean.JsonErrorResult;
import com.codrim.constant.ErrorCode;
import common.codrim.service.WzGroupService;
import common.codrim.service.WzUserService;
import common.codrim.wz.constant.DataConstant;
import common.codrim.wz.sql.result.RankingInfo;

@Controller
public class RankingController extends BaseController {
	
	private static final Logger logger = Logger.getLogger("yj");

	@Autowired
	private WzUserService userService;
	
	@Autowired
	private WzGroupService groupService;

	
	/**
	 * 排行榜  type  user group
	 * @param request
	 * @return
	 * @throws DataAccessException
	 */
	@RequestMapping("/ranking.do")
	@ResponseBody
	public JsonBasicResult groupRanking(HttpServletRequest request) throws DataAccessException {
		String type=request.getParameter("type");
		JsonBasicResult<List<RankingInfo>> result = new JsonBasicResult<List<RankingInfo>>();
		try {
			if("user".equals(type)){
				result.setResult(userService.userRanking(DataConstant.PAGESIZE_50));
				result.setRtCode(DataConstant.SUCCESS);
			}else if("group".equals(type)){
			result.setResult(groupService.groupRanking(DataConstant.PAGESIZE_50));
			result.setRtCode(DataConstant.SUCCESS);
			}else{
				return new JsonErrorResult(ErrorCode.UN100, "Required fields is empty.");
			}
		} catch(Exception e) {
			logger.error(">>>>> Get Group Ranking ERROR!", e);
			return new JsonErrorResult(ErrorCode.UN000, "groupRanking ERROR");
		}
		
		return result;
	}


	
}
