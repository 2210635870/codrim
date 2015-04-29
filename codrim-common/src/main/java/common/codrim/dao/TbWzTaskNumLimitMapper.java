package common.codrim.dao;


import java.util.List;

import common.codrim.pojo.TbWzTaskNumLimit;

public interface TbWzTaskNumLimitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbWzTaskNumLimit record);

    int insertSelective(TbWzTaskNumLimit record);

    TbWzTaskNumLimit selectByPrimaryKey(Integer id);

    TbWzTaskNumLimit selectByLimitType(Short limitType);
 List<TbWzTaskNumLimit> selectAll();
    int updateByPrimaryKeySelective(TbWzTaskNumLimit record);

    int updateByPrimaryKey(TbWzTaskNumLimit record);
}