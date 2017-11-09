package com.rapidvalue.master.dao.plugin;

import java.sql.PreparedStatement;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;

import com.rapidvalue.master.dao.handler.CustomParameterHandler;
import com.rapidvalue.master.dao.util.ReflectionUtils;

@Intercepts({ @Signature(type = ParameterHandler.class, method = "setParameters", args = { PreparedStatement.class }) })
public class ParameterHandlerInterceptor implements Interceptor {

    private static final String TARGET = "target";
    private static final String BOUND_SQL = "boundSql";
    private static final String MAPPED_STATEMENT = "mappedStatement";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        DefaultParameterHandler defaultParameterHandler = (DefaultParameterHandler) invocation.getTarget();
        MappedStatement mappedStatement = (MappedStatement) ReflectionUtils.getField(defaultParameterHandler,
                MAPPED_STATEMENT);
        Object parameterObject = defaultParameterHandler.getParameterObject();
        BoundSql boundSql = (BoundSql) ReflectionUtils.getField(defaultParameterHandler, BOUND_SQL);
        if (mappedStatement != null && boundSql != null) {
            CustomParameterHandler customParameterHandler = new CustomParameterHandler(mappedStatement,
                    parameterObject, boundSql);
            ReflectionUtils.setField(invocation, TARGET, customParameterHandler);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // Do Nothing
    }

}
