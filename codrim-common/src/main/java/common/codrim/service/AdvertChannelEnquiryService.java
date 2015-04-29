/**
 * 
 */
package common.codrim.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.common.ChannelEnquiryBean;
import common.codrim.pojo.TbAdvertChannelEnquiry;

/**
 * @author Administrator
 *
 */
public interface AdvertChannelEnquiryService {

	public int saveChannelEnquiry(TbAdvertChannelEnquiry advertChannelEnquiry) throws DataAccessException;
	public  int insertByBatch(List<TbAdvertChannelEnquiry> list)throws DataAccessException;
	
	public List<ChannelEnquiryBean> getChannelEnquirysByAdvertId(long advertId)throws DataAccessException;
}
