/*
信息:
*/
package yd.itcast.core.utils;

import java.util.ArrayList;
import java.util.List;

public class QueryHelper {
    private String fromClause="";
    private String whereClause="";
    private String orderByClause="";
    private List<Object> paramters;
    public static String ORDER_BY_DESC="DESC";
    public static String ORDER_BY_ASC="ASC";
    private String alias=null;
    public QueryHelper(Class className,String alias) {
        this.alias=alias;
        fromClause="FROM "+className.getSimpleName()+" "+alias;
    }
    public QueryHelper(Class className){
        fromClause="FROM "+className.getSimpleName()+" ";
    }
    public void addCondition(String condition,Object...params){
        if (whereClause.length()>1){
            whereClause+=" AND "+condition;
        }else{
            whereClause=" WHERE "+condition;
        }
        if (paramters==null){
            paramters=new ArrayList<Object>();
        }
        if (params!=null){
            for (Object object:params){
                paramters.add(object);
            }
        }
    }
    public void addOrderBy(String property,String order){
        if (orderByClause.length()>1){
            orderByClause+=" ,"+property+" "+order;
        }else {
            orderByClause=" ORDER By "+property+" "+order;
        }
    }
    public List<Object> getParamters(){
        return paramters;
    }
    public String getClause(){
        return fromClause+whereClause+orderByClause;
    }
}
