/**
 * 
 */
package com.codrim.controller;

import com.codrim.common.ResultJson;
import common.codrim.common.Contans;

/**
 * @author Administrator
 *
 */
public abstract class CommonController {
	public ResultJson getResultJson(boolean flag) {
		ResultJson json = new ResultJson();
		if (flag) {
			json.setSuccess(true);
			json.setCode(Contans.SUCCESS);
			json.setMessage(Contans.SUCCESS_MESSAGE);
		} else {
			json.setSuccess(false);
			json.setCode(Contans.ERROR);
			json.setMessage(Contans.ERROR_MESSAGE);
		}
		return json;
	}
}
