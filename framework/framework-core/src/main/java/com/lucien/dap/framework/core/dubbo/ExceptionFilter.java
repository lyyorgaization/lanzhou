package com.lucien.dap.framework.core.dubbo;

import com.alibaba.fastjson.JSONObject;
import com.lucien.dap.framework.common.constants.RetEnum;
import com.lucien.dap.framework.core.domain.Response;
import com.lucien.dap.framework.core.exception.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

@Slf4j
@Activate(group = {"provider"})
public class ExceptionFilter implements Filter {
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		log.info("收到dubbo请求,请求参数:{}", JSONObject.toJSONString(invocation));
		Result result = invoker.invoke(invocation);
		Throwable exception = result.getException();
		if (null != exception) {
			if (exception instanceof ApplicationException) {
				result.setValue(Response.failed((ApplicationException) exception));
			} else {
				log.error("dubbo请求出错,请求参数{}", JSONObject.toJSONString(invocation), exception);
				result.setValue(Response.failed(RetEnum.SystemError));
			}
			result.setException(null);
		}
		return result;
	}
}
