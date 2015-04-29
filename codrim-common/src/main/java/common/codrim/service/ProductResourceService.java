/**
 * 
 */
package common.codrim.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import common.codrim.pojo.TbProductResource;

/**
 * @author Administrator
 *
 */
public interface ProductResourceService {
 
	public int insertByBatch(List<TbProductResource> productResources)  throws DataAccessException;
	
	List<TbProductResource> geTbProductResources(long productId)throws DataAccessException;
}
