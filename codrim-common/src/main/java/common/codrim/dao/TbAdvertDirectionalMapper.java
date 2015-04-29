package common.codrim.dao;

import common.codrim.pojo.TbAdvertDirectional;

public interface TbAdvertDirectionalMapper {
    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-02-09 15:10:08
     */
    int insert(TbAdvertDirectional record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-02-09 15:10:08
     */
    int insertSelective(TbAdvertDirectional record);
}