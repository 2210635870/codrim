package common.codrim.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.codrim.dao.TbDailyReportMapper;
import common.codrim.dao.TbDailyReportReplyMapper;
import common.codrim.pojo.TbDailyReport;
import common.codrim.pojo.TbDailyReportReply;
import common.codrim.query.resultmap.DailyReport;
import common.codrim.query.resultmap.DailyReportReply;
import common.codrim.service.DailyReportService;

@Service
@Transactional
public class DailyReportServiceImpl  implements DailyReportService {
	
	@Autowired
	private TbDailyReportMapper dailyReportDao;
	
	@Autowired TbDailyReportReplyMapper reportReplyDao;

	@Override
	public void addDailyReport(TbDailyReport report) throws DataAccessException {
		dailyReportDao.insert(report);
	}

	@Override
	public void modifyDailyReport(TbDailyReport report) throws DataAccessException {
		dailyReportDao.updateByPrimaryKeySelective(report);
	}

	@Override
	public void deleteDailyReport(long id) throws DataAccessException {
		dailyReportDao.deleteByPrimaryKey(id);
		reportReplyDao.deleteByReport(id);
	}

	@Override
	public int count(DailyReport param) throws DataAccessException {
		return dailyReportDao.count(param);
	}

	@Override
	public List<DailyReport> page(int startPage, int size, DailyReport param) throws DataAccessException {
		int start = (startPage-1) * size;
		return dailyReportDao.page(start, size, param);
	}

	@Override
	public DailyReport getDailyReportById(long id) throws DataAccessException {
		return dailyReportDao.getById(id);
	}

	@Override
	public List<DailyReport> getDailyReportSummary(DailyReport param) throws DataAccessException {
		return dailyReportDao.getDailyReportSummary(param);
	}

	@Override
	public void addReply(TbDailyReportReply reply) throws DataAccessException {
		reportReplyDao.insert(reply);
	}

	@Override
	public void modifyReply(TbDailyReportReply reply) throws DataAccessException {
		reportReplyDao.updateByPrimaryKeySelective(reply);
	}

	@Override
	public void deleteReply(long id) throws DataAccessException {
		reportReplyDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<DailyReportReply> getReplysByReport(long reportId) throws DataAccessException {
		return reportReplyDao.getReplysByReport(reportId);
	}

}
