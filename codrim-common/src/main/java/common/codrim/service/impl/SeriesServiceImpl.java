/**
 * 
 */
package common.codrim.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.common.SelectJsonResult;
import common.codrim.dao.TbSeriesMapper;
import common.codrim.pojo.TbSeries;
import common.codrim.service.SeriesService;

/**
 * @author Administrator
 *
 */
@Service
@Transactional
public class SeriesServiceImpl implements SeriesService {
@Autowired
TbSeriesMapper seriesDao;
	/* (non-Javadoc)
	 * @see common.codrim.service.SeriesService#selectAll()
	 */
	@Override
	public List<SelectJsonResult> selectAll() throws DataAccessException {
		// TODO Auto-generated method stub
		List<TbSeries> list= seriesDao.selectAll();
		List<SelectJsonResult> nameList=null;
		if(list!=null&&list.size()>0){
			nameList=new ArrayList<SelectJsonResult>();
			for(TbSeries series:list){
				SelectJsonResult json=new SelectJsonResult();
				json.setId(series.getId());
				json.setText(series.getSeriesName());
				nameList.add(json);
			}
	}
		return nameList;
	}

}
