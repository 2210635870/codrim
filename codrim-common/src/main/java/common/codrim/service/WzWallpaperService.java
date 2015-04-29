package common.codrim.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import common.codrim.pojo.TbWzWallpaper;

public interface WzWallpaperService {
	
	TbWzWallpaper selectByPrimaryKey(Long id) throws DataAccessException;
	
	int deleteByPrimaryKey(Long id) throws DataAccessException;
	 
	int insert(TbWzWallpaper wallpaper) throws DataAccessException;
	
	int updateByPrimaryKey(TbWzWallpaper wallpaper) throws DataAccessException;
	
	List<TbWzWallpaper> selectList(Map<String, Object> params) throws DataAccessException;
	
	int getTotalNum(Map<String, Object> params) throws DataAccessException;
}
