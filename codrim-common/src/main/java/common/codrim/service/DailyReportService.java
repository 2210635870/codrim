package common.codrim.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.pojo.TbDailyReport;
import common.codrim.pojo.TbDailyReportReply;
import common.codrim.query.resultmap.DailyReport;
import common.codrim.query.resultmap.DailyReportReply;


public interface DailyReportService {
	
	public void addDailyReport(TbDailyReport report) throws DataAccessException;
	public void modifyDailyReport(TbDailyReport report) throws DataAccessException;
	public void deleteDailyReport(long id) throws DataAccessException;
	public DailyReport getDailyReportById(long id) throws DataAccessException;
	
	public int count(DailyReport param) throws DataAccessException;
	public List<DailyReport> page(int startPage, int size, DailyReport param) throws DataAccessException;
	
	public List<DailyReport> getDailyReportSummary(DailyReport param) throws DataAccessException;
	
	public void addReply(TbDailyReportReply reply) throws DataAccessException;
	public void modifyReply(TbDailyReportReply reply) throws DataAccessException;
	public void deleteReply(long id) throws DataAccessException;
	public List<DailyReportReply> getReplysByReport(long reportId) throws DataAccessException;
}
