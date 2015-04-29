package common.codrim.wz.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Converter<PO,DTO> {

	public abstract PO toPO(DTO dto);

	public abstract DTO toDTO(PO po);
	
	public List<DTO> toDTOs(Collection<PO> poL){
		if(poL==null || poL.size()==0){
			return new ArrayList<DTO>();
		}		
		List<DTO> dtoL = new ArrayList<DTO>();
		for(PO po:poL){
			dtoL.add(toDTO(po));
		}
		return dtoL;
	}
	
	public List<PO> toPOs(Collection<DTO> dtoL){
		if(dtoL==null || dtoL.size()==0){
			return new ArrayList<PO>();
		}
		List<PO> poL = new ArrayList<PO>();
		for(DTO dto:dtoL){
			poL.add(toPO(dto));
		}
		return poL;
	}

}
