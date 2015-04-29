package common.codrim.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import common.codrim.pojo.TbAdmin;
@Repository
public interface TbAdminMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2014-11-28 12:43:38
     */
    int deleteByPrimaryKey(Long id) throws DataAccessException;

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-11-28 12:43:38
     */
    int insert(TbAdmin record) throws DataAccessException;

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-11-28 12:43:38
     */
    int insertSelective(TbAdmin record) throws DataAccessException;

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-11-28 12:43:38
     */
    TbAdmin selectByPrimaryKey(Long id) throws DataAccessException;
    /**
     * 根据name查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-11-25 14:21:17
     */
    TbAdmin selectIdByName(String name)throws DataAccessException;
    /**
     * 查询所有
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-11-25 14:21:17
     */
       TbAdmin login(@Param("email")String account,@Param("password")String password)throws DataAccessException;
    /**
     * 查询所有
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-11-25 14:21:17
     */
    List<TbAdmin> selectAll()throws DataAccessException;
    /**
     * 查询所有
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-11-25 14:21:17
     */
    List<TbAdmin> selectUsersByPage(@Param("startPage")int startPage,@Param("size")int size)throws DataAccessException;
    /**
     * 查询总数  分页使用
     * 参数:查询条件,主键值
     * 返回:总数
     * @ibatorgenerated 2014-11-25 14:21:17
     */
    int getTotalNum() throws DataAccessException;
    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-11-28 12:43:38
     */
    int updateByPrimaryKeySelective(TbAdmin record) throws DataAccessException;

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-11-28 12:43:38
     */
    int updateByPrimaryKey(TbAdmin record) throws DataAccessException;
}