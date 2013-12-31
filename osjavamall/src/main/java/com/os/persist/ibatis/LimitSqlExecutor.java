 package com.os.persist.ibatis;
 
 import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.util.ClassUtils;
import org.springframework.web.context.request.RequestScope;

import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.mapping.parameter.ParameterMap;
import com.ibatis.sqlmap.engine.mapping.statement.RowHandlerCallback;
import com.os.persist.dialect.Dialect;
 
 public class LimitSqlExecutor extends SqlExecutor
 {
  
   private Dialect dialect;
   private boolean enableLimit;
 
   public LimitSqlExecutor()
   {
     this.enableLimit = true;
   }
   public Dialect getDialect() {
     return this.dialect;
   }
 
   public void setDialect(Dialect dialect) {
     this.dialect = dialect;
   }
 
   public boolean isEnableLimit() {
     return this.enableLimit;
   }
 
   public void setEnableLimit(boolean enableLimit) {
     this.enableLimit = enableLimit;
   }
 
   public void executeQuery(RequestScope request, Connection conn, String sql, Object[] parameters, int skipResults, int maxResults, RowHandlerCallback callback)
     throws SQLException
   {
     
     if (((skipResults != 0) || (maxResults != -999999)) && (supportsLimit()) && (!IBatisExecuteContextUtil.isForced()))
     {
       if (this.dialect.supportsLimitOffset()) {
         sql = this.dialect.getLimitString(sql, skipResults, maxResults);
         parameters = this.dialect.getLimitedParameterArray(parameters, skipResults, maxResults);
       }
       else
       {
         sql = this.dialect.getLimitString(sql, 0, maxResults + skipResults);
         parameters = this.dialect.getLimitedParameterArray(parameters, 0, maxResults + skipResults);
       }
 
       
 
 
       if (this.dialect.supportsLimitOffset()) {
         skipResults = 0;
       }
       if (this.dialect.supportsLimit()) {
         maxResults = -999999;
       }
     }
 
   }
 
   public boolean supportsLimit()
   {
     if ((this.enableLimit) && (this.dialect != null)) {
       return true;
     }
     return false;
   }
 
 
 
   static class PaginationSupportedParameterMapProxy
     implements InvocationHandler
   {
     private ParameterMap target;
 
     public PaginationSupportedParameterMapProxy(ParameterMap target)
     {
       this.target = target;
     }
 
     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
     {
       Object returnObject = null;
       try
       {
         returnObject = method.invoke(this.target, args);
       } catch (InvocationTargetException ex) {
         throw ex.getTargetException();
       }
 
       if ("setParameters".equals(method.getName()))
       {
         PreparedStatement ps = null;
         Object[] parameterValues = null;
         if (args.length > 2)
         {
           ps = (PreparedStatement)args[1];
           parameterValues = (Object[])args[2];
         }
 
         if (parameterValues == null) {
           return null;
         }
 
         int mappedParamCnt = this.target.getParameterMappings().length;
         int pageParamCnt = parameterValues.length - mappedParamCnt;
 
         for (int i = 0; i < pageParamCnt; i++) {
           if ((parameterValues[(mappedParamCnt + i)] != null) && (ClassUtils.isAssignableValue(Integer.class, parameterValues[(mappedParamCnt + i)])))
           {
             ps.setInt(mappedParamCnt + i + 1, ((Integer)parameterValues[(mappedParamCnt + i)]).intValue());
           }
           else
           {
             throw new SQLException("参数不合法, pos=" + (mappedParamCnt + i + 1) + " value=[" + parameterValues[(mappedParamCnt + i)] + ']');
           }
         }
 
       }
 
       return returnObject;
     }
   }
 }
