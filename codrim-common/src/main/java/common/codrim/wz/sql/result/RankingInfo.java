package common.codrim.wz.sql.result;

import java.io.Serializable;

public class RankingInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private long gold;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getGold() {
		return gold;
	}

	public void setGold(long gold) {
		this.gold = gold;
	}
	
	
}
