/**
 * 
 */
package common.codrim.util;

import java.text.DecimalFormat;

/**
 * @author Administrator
 *
 */
public class NumberFormatUtil {

	
	public static void main(String[] args) {
		//System.out.println(getDeci(3, 10));
		System.out.println(doubleFormat(56.46482215845,"#.00"));
		
		System.out.println(1%2);
	}
	public static String getDeci(int x,int y){
		if(x==0||y==0){
			return "0.00%";
		}
		String result="";
		Double xxDouble=x*1.0;
		double tempResult=xxDouble/y;
		DecimalFormat deFormat=new DecimalFormat("0.00%");
		result= deFormat.format(tempResult); 
		return result;
	} 
	public static String getDeci(Double x,Double y){
		if(x==0||y==0){
			return "0.00%";
		}
		String result="";
		double tempResult=x/y;
		DecimalFormat deFormat=new DecimalFormat("0.00%");
		result= deFormat.format(tempResult); 
		return result;
	} 
	
    public static double doubleFormat(double value,String type){
    	DecimalFormat df = new DecimalFormat(type);
       return Double.parseDouble(df.format(value));
    }
    
	public static double numberToPercent(int percent) {
		return (Integer.valueOf(percent).doubleValue())/100;
	}
}
