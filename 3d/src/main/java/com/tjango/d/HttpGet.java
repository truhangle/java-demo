package com.tjango.d;

import java.net.Socket;

import org.apache.commons.httpclient.util.URIUtil;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.DefaultHttpClientConnection;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestExpectContinue;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;
import org.apache.http.util.EntityUtils;

public class HttpGet {

	public static void main(String[] args) throws Exception {

		HttpParams params = new BasicHttpParams();
		// HTTP 协议的版本,1.1/1.0/0.9
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params, "GBK");
		HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1");
		HttpProtocolParams.setUseExpectContinue(params, true);

		BasicHttpProcessor httpproc = new BasicHttpProcessor();
		httpproc.addInterceptor(new RequestContent());
		httpproc.addInterceptor(new RequestTargetHost());
		httpproc.addInterceptor(new RequestConnControl());
		httpproc.addInterceptor(new RequestUserAgent());
		httpproc.addInterceptor(new RequestExpectContinue());
		
		HttpRequestExecutor httpexecutor = new HttpRequestExecutor();
		HttpContext context = new BasicHttpContext(null);
		HttpHost host = new HttpHost("www.cha.la", 80);
		
		DefaultHttpClientConnection conn = new DefaultHttpClientConnection();
		ConnectionReuseStrategy connStrategy = new DefaultConnectionReuseStrategy();
		
		context.setAttribute(ExecutionContext.HTTP_CONNECTION, conn);
		context.setAttribute(ExecutionContext.HTTP_TARGET_HOST, host);
		try {
			String url = "/tools/turn_bihua.asp?hanzi=田矗晗";
			url = URIUtil.encodeQuery(url,"GBK");
			System.out.println(url);
			Socket socket = new Socket(host.getHostName(), host.getPort());
			conn.bind(socket, params);
			BasicHttpRequest request = new BasicHttpRequest("GET", url);
			context.setAttribute(ExecutionContext.HTTP_REQUEST, request);
			
			request.setParams(params);
			httpexecutor.preProcess(request, httpproc, context);
			HttpResponse response = httpexecutor.execute(request, conn, context);
			response.setParams(params);
			
			httpexecutor.postProcess(response, httpproc, context);
			System.out.println(EntityUtils.toString(response.getEntity()));
			if (!connStrategy.keepAlive(response, context))
				conn.close();
		} finally {
			conn.close();
		}
	}
}
