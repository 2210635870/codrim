package common.codrim.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import common.codrim.dao.TbWzWallpaperMapper;
import common.codrim.pojo.TbWzWallpaper;
import common.codrim.service.WzWallpaperService;
import common.codrim.wz.sql.in.PageParamsUtil;

@Service
@Transactional
public class WzWallpaperSettingServiceImpl implements WzWallpaperService{

	@Value("#{configProperties['upload.root']}")
	private String uploadRoot;
	
	@Autowired
	private TbWzWallpaperMapper wallpaperDao;
	
	@Override
	public TbWzWallpaper selectByPrimaryKey(Long id) throws DataAccessException {
		return wallpaperDao.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(Long id) throws DataAccessException {
		TbWzWallpaper wallpaper = wallpaperDao.selectByPrimaryKey(id);
		File originalFile = new File(uploadRoot+wallpaper.getWallpaper());
		
		int num = wallpaperDao.deleteByPrimaryKey(id);
		if(originalFile.exists()) {
			FileUtils.deleteQuietly(originalFile);
		}
		return num;
	}

	@Override
	public int insert(TbWzWallpaper wallpaper) throws DataAccessException {
		return wallpaperDao.insert(wallpaper);
	}

	@Override
	public int updateByPrimaryKey(TbWzWallpaper wallpaper) throws DataAccessException {
		TbWzWallpaper po = wallpaperDao.selectByPrimaryKey(wallpaper.getId());
		File originalFile = new File(uploadRoot+po.getWallpaper());
		
		int num = wallpaperDao.updateByPrimaryKey(wallpaper);
		
		if(originalFile.exists()) {
			FileUtils.deleteQuietly(originalFile);
		}
		
		return num;
	}

	@Override
	public List<TbWzWallpaper> selectList(Map<String, Object> params) throws DataAccessException {
		PageParamsUtil.calculatePageParams(params);
		return wallpaperDao.selectList(params);
	}

	@Override
	public int getTotalNum(Map<String, Object> params) throws DataAccessException {
		return wallpaperDao.getTotalNum(params);
	}
}
