package common.codrim.service;

import java.util.List;

import org.apache.ibatis.annotations.Update;
import org.springframework.dao.DataAccessException;

import common.codrim.common.SelectResultByCodition;
import common.codrim.pojo.TbWzGameH5;

public interface WzGameH5Service {

	public int saveH5(TbWzGameH5 h5)throws DataAccessException;
	public int update(TbWzGameH5 h5)throws DataAccessException;
	public List<TbWzGameH5> getGameH5List(SelectResultByCodition codition)throws DataAccessException;
	public int getTotalGameH5(SelectResultByCodition codition)throws DataAccessException;
}
