/**
 * 
 */
package common.codrim.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.common.SelectJsonResult;

/**
 * @author Administrator
 *
 */
public interface SeriesService {

	List<SelectJsonResult> selectAll()  throws DataAccessException;
	
	
}
