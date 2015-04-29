package common.codrim.dao;

import java.util.List;

import common.codrim.common.SelectResultByCodition;
import common.codrim.pojo.TbWzGameH5;

public interface TbWzGameH5Mapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbWzGameH5 record);

    int insertSelective(TbWzGameH5 record);

    TbWzGameH5 selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbWzGameH5 record);

    int updateByPrimaryKey(TbWzGameH5 record);

	List<TbWzGameH5> getGameH5List(SelectResultByCodition codition);

	int getTotalGameH5(SelectResultByCodition codition);
}