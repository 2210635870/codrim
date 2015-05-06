package common.codrim.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.dao.TbWzNewsMapper;
import common.codrim.pojo.TbWzNews;
import common.codrim.pojo.TbWzWallpaper;
import common.codrim.service.WzNewsService;
import common.codrim.util.StringUtil;
import common.codrim.wz.sql.in.PageParamsUtil;

@Service
@Transactional
public class WzNewsServiceImpl implements WzNewsService {

	@Value("#{configProperties['upload.root']}")
	private String uploadRoot;
	
	@Autowired
	private TbWzNewsMapper wzNewsDao;
	
	@Override
	public TbWzNews selectByPrimaryKey(Long id) throws DataAccessException {
		return wzNewsDao.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(Long id) throws DataAccessException {
		TbWzNews news = wzNewsDao.selectByPrimaryKey(id);
		File originalFile = new File(uploadRoot+news.getNewsScreenLock());
		
		int num = wzNewsDao.deleteByPrimaryKey(id);
		if(originalFile.exists()) {
			FileUtils.deleteQuietly(originalFile);
		}
		return num;
	}

	@Override
	public int insert(TbWzNews news) throws DataAccessException {
		return wzNewsDao.insert(news);
	}

	@Override
	public int updateByPrimaryKey(TbWzNews news) throws DataAccessException {
		TbWzNews po = wzNewsDao.selectByPrimaryKey(news.getId());
		
		int num = wzNewsDao.updateByPrimaryKey(news);
		
		File originalFile = new File(uploadRoot+po.getNewsScreenLock());
		if( !StringUtil.isEmpty(news.getNewsScreenLock()) && originalFile.exists() ) {
			FileUtils.deleteQuietly(originalFile);
		}
		
		return num;
	}

	@Override
	public List<TbWzNews> selectList(Map<String, Object> params) throws DataAccessException {
		PageParamsUtil.calculatePageParams(params);
		return wzNewsDao.selectList(params);
	}

	@Override
	public int getTotalNum(Map<String, Object> params) throws DataAccessException {
		return wzNewsDao.getTotalNum(params);
	}

	@Override
	public List<Map<String, Object>> selectForScreenlock(Map<String, Object> params) {
		return wzNewsDao.selectForScreenlock(params);
	}
	
	
}
