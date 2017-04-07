package com.songsf.learn.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class RestClient {

	private static Logger logger = LoggerFactory.getLogger(RestClient.class);

	private static Gson gson = new Gson();

	public static <T> T get(String uri, Class<T> T) {
		try {
			CloseableHttpClient httpClient = HttpClients.custom().build();
			HttpGet getRequest = new HttpGet(uri);
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);
			String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
			T t = gson.fromJson(responseString, T);
			httpClient.close();
			return t;
		} catch (ClientProtocolException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * get方法
	 * @param uri 资源路径
	 * @param T 泛化类型
	 * @return
	 */
	public static <T> T get(String accessToken, String uri, Class<T> T) {
		try {
			//PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
			// 将最大连接数增加到200
			//cm.setMaxTotal(200);
			// 将每个路由基础的连接增加到20
			//cm.setDefaultMaxPerRoute(20);

			CloseableHttpClient httpClient = HttpClients.custom().build();

			HttpGet getRequest = new HttpGet(uri);
			getRequest.addHeader("accept", "application/json");
			getRequest.addHeader("authorization", accessToken);

			HttpResponse response = httpClient.execute(getRequest);
			String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
			System.out.println("=====session ===== " + responseString);
			if (response.getStatusLine().getStatusCode() < 200 || response.getStatusLine().getStatusCode() > 206) {
				logger.error(uri + " Failed : " + responseString);
				//throw new RuntimeException(uri + " Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
				return null;
			}

			T t = gson.fromJson(responseString, T);

			httpClient.close();

			return t;
		} catch (ClientProtocolException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * post方法
	 * @param uri 资源路径
	 * @param param 参数
	 * @return
	 */
	public static boolean post(String accessToken, String uri, Object param) {
		return post(accessToken, uri, null, null, param);
	}

	/**
	 * post方法
	 * @param uri 资源路径
	 * @param userName 用户名
	 * @param passWord 口令
	 * @param param 参数
	 * @return
	 */
	public static boolean post(String accessToken, String uri, String userName, String passWord, Object param) {
		CloseableHttpClient httpClient = null;
		try {
			//PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
			// 将最大连接数增加到200
			//cm.setMaxTotal(200);
			// 将每个路由基础的连接增加到20
			//cm.setDefaultMaxPerRoute(20);
			// 将目标主机的最大连接数增加到50
			// HttpHost localhost = new HttpHost("www.yeetrack.com", 80);
			// cm.setMaxPerRoute(new HttpRoute(localhost), 50);
			HttpClientBuilder hcBuilder = HttpClients.custom();
			if (null != userName) {
				CredentialsProvider credsProvider = new BasicCredentialsProvider();
				credsProvider.setCredentials(new AuthScope("localhost", AuthScope.ANY_PORT),
						new UsernamePasswordCredentials(userName, passWord));
				hcBuilder.setDefaultCredentialsProvider(credsProvider);
			}
			httpClient = hcBuilder.build();

			HttpPost postRequest = new HttpPost(uri);
			postRequest.addHeader("authorization", accessToken);

			StringEntity input = new StringEntity(gson.toJson(param), "UTF-8");

			input.setContentType("application/json");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);
			String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");

			if (response.getStatusLine().getStatusCode() < 200 || response.getStatusLine().getStatusCode() > 206) {
				logger.error(uri + " Failed : " + responseString);
				//throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
				return false;
			}

			return true;
		} catch (MalformedURLException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} finally {
			try {
				if(httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * get方法
	 *
	 * @param accessToken
	 * @param uri
	 * @param params
	 * @return
	 */
	public static JSONObject getJSONObject(String accessToken, String uri, String params) {
		CloseableHttpClient httpClient = null;
		try {
			//PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
			//cm.setMaxTotal(200);// 将最大连接数增加到200
			//cm.setDefaultMaxPerRoute(20);// 将每个路由基础的连接增加到20
			httpClient = HttpClients.custom().build();
			String url = uri+(params==null ? "" : params);
			HttpGet getRequest = new HttpGet(url);
			getRequest.addHeader("accept", "application/json");
			//getRequest.addHeader("cheatStaffId", "cn=pang.mengchang,ou=staffs,dc=nkf");
			//getRequest.addHeader(Constant.header_token_name, accessToken);

			HttpResponse response = httpClient.execute(getRequest);
			String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");

			logger.debug(accessToken+"==getJSONObject=="+url+"==" + response.getStatusLine().getStatusCode() + "===" + responseString);

			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode < 200 || statusCode > 206) {

				return null;
			}

			return JSONObject.parseObject(responseString);
		} catch (ClientProtocolException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} finally {
			try {
				if(httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * get方法
	 *
	 * @param ub
	 * @param uri
	 * @param params
	 * @return
	 *//*
	public static JSONObject getJSONObject(UserBean ub, String uri, String params) {
		CloseableHttpClient httpClient = null;
		try {
			httpClient = HttpClients.custom().build();
			String url = uri+(params==null ? "" : params);
			HttpGet getRequest = new HttpGet(url);
			getRequest.addHeader("accept", "application/json");
			if(ub.getCheatStaffId() !=null ){
				getRequest.addHeader("cheatStaffId", ub.getCheatStaffId());
			}
			getRequest.addHeader("jwts", JWTUtil.pack(JSONObject.toJSONString(ub)));
			HttpResponse response = httpClient.execute(getRequest);
			String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
			logger.debug("==getJSONObject=="+url+"==" + response.getStatusLine().getStatusCode() + "===" + responseString);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode < 200 || statusCode > 206) {
				return null;
			}
			return JSONObject.parseObject(responseString);
		} catch (ClientProtocolException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} finally {
			try {
				if(httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
*/
	/**
	 * post方法
	 *
	 * @param accessToken
	 * @param uri
	 * @param param
	 * @return
	 */
	public static JSONObject postObject(String accessToken, String uri, Object param) {
		CloseableHttpClient httpClient = null;
		try {
			//PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
			// 将最大连接数增加到200
			//cm.setMaxTotal(200);
			// 将每个路由基础的连接增加到20
			//cm.setDefaultMaxPerRoute(20);

			HttpClientBuilder hcBuilder = HttpClients.custom();
			httpClient = hcBuilder.build();

			HttpPost postRequest = new HttpPost(uri);
			//postRequest.addHeader(Constant.header_token_name, accessToken);

			StringEntity input = new StringEntity(gson.toJson(param), "UTF-8");
			input.setContentType("application/json");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);
			String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");

			logger.debug(accessToken+"==postObject=="+uri+"==" + response.getStatusLine().getStatusCode() + "===" + responseString);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode < 200 || statusCode > 206) {

				return null;
			}

			return JSONObject.parseObject(responseString);
		} catch (MalformedURLException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} finally {
			try {
				if(httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * post方法
	 *
	 * @param accessToken
	 * @param uri
	 * @param param
	 * @return
	 */
	public static JSONObject postCustomCare(String accessToken, String uri, Object param) {
		CloseableHttpClient httpClient = null;
		try {
			//PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
			// 将最大连接数增加到200
			//cm.setMaxTotal(200);
			// 将每个路由基础的连接增加到20
			//cm.setDefaultMaxPerRoute(20);

			HttpClientBuilder hcBuilder = HttpClients.custom();
			httpClient = hcBuilder.build();

			HttpPost postRequest = new HttpPost(uri);
			//postRequest.addHeader(Constant.header_token_name, accessToken);

			StringEntity input = new StringEntity(gson.toJson(param), "UTF-8");
			input.setContentType("application/json");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);
			String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");

			System.out.println(accessToken+"==postObject=="+uri+"==" + response.getStatusLine().getStatusCode() + "===" + responseString);

			return JSONObject.parseObject(responseString);
		} catch (MalformedURLException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} finally {
			try {
				if(httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * post方法
	 *
	 * @param accessToken
	 * @param uri
	 * @param param
	 * @return
	 */
	public static JSONObject postObjectWithMessage(String accessToken, String uri, Object param) {
		CloseableHttpClient httpClient = null;
		try {
			HttpClientBuilder hcBuilder = HttpClients.custom();
			httpClient = hcBuilder.build();

			HttpPost postRequest = new HttpPost(uri);
			//postRequest.addHeader(Constant.header_token_name, accessToken);

			StringEntity input = new StringEntity(gson.toJson(param), "UTF-8");
			input.setContentType("application/json");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);
			String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");

			logger.debug(accessToken+"==postObject=="+uri+"==" + response.getStatusLine().getStatusCode() + "===" + responseString);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 500 && (statusCode < 200 || statusCode > 206)) {
				return null;
			}

			return JSONObject.parseObject(responseString);
		} catch (MalformedURLException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} finally {
			try {
				if(httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}




	/**
	 * put方法
	 *
	 * @param accessToken
	 * @param uri
	 * @param param
	 * @return
	 */
	public static JSONObject putObject(String accessToken, String uri, Object param) {
		CloseableHttpClient httpClient = null;
		try {
			//PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
			// 将最大连接数增加到200
			//cm.setMaxTotal(200);
			// 将每个路由基础的连接增加到20
			//cm.setDefaultMaxPerRoute(20);

			HttpClientBuilder hcBuilder = HttpClients.custom();
			httpClient = hcBuilder.build();

			HttpPut postRequest = new HttpPut(uri);
			//postRequest.addHeader(Constant.header_token_name, accessToken);

			StringEntity input = new StringEntity(gson.toJson(param), "UTF-8");
			input.setContentType("application/json");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);
			String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");

			logger.debug(accessToken+"==putObject=="+uri+"==" + response.getStatusLine().getStatusCode() + "===" + responseString);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode < 200 || statusCode > 206) {

				return null;
			}

			return JSONObject.parseObject(responseString);
		} catch (MalformedURLException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} finally {
			try {
				if(httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * delete方法
	 *
	 * @param accessToken
	 * @param uri
	 * @return
	 */
	public static JSONObject deleteObject(String accessToken, String uri) {
		CloseableHttpClient httpClient = null;
		try {
			//PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
			// 将最大连接数增加到200
			//cm.setMaxTotal(200);
			// 将每个路由基础的连接增加到20
			//cm.setDefaultMaxPerRoute(20);

			HttpClientBuilder hcBuilder = HttpClients.custom();
			httpClient = hcBuilder.build();

			HttpDelete postRequest = new HttpDelete(uri);
			//postRequest.addHeader(Constant.header_token_name, accessToken);

			HttpResponse response = httpClient.execute(postRequest);
			String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");

			logger.debug(accessToken+"==deleteObject=="+uri+"==" + response.getStatusLine().getStatusCode() + "===" + responseString);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode < 200 || statusCode > 206) {

				return null;
			}

			return JSONObject.parseObject(responseString);
		} catch (MalformedURLException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} finally {
			try {
				if(httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}


	/**
	 * 下载远程图片至本地
	 *
	 * @param uri
	 * @param path
	 * @param postfix
	 * @param suffix
	 * @return
	 */
	public static boolean getImg(String uri, String path, String postfix, String suffix) {
		InputStream is = null;
		OutputStream os = null;
		try {
			URL url = new URL(uri);
			URLConnection con = url.openConnection();
			//设置请求超时为10s
			con.setConnectTimeout(10*1000);

			is = con.getInputStream();

			os = new FileOutputStream(path+"/"+postfix+suffix);

			byte[] bs = new byte[1024];
			int len=0;
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
		} catch(Exception e) {
			logger.error(e.toString());
			e.printStackTrace();

			return false;
		} finally {
			try {
				if(os != null) {
					os.close();
				}
				if(is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	/**
	 * get方法
	 * @param url
	 * @return
	 */
	public static String getToken(String url) {
		CloseableHttpClient httpClient = null;
		try {
			//PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
			//cm.setMaxTotal(200);// 将最大连接数增加到200
			//cm.setDefaultMaxPerRoute(20);// 将每个路由基础的连接增加到20

			httpClient = HttpClients.custom().build();
			HttpGet getRequest = new HttpGet(url);
			getRequest.addHeader("accept", "application/json");

			HttpResponse response = httpClient.execute(getRequest);
			String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");

			logger.debug("==getJSONObject=="+url+"==" + response.getStatusLine().getStatusCode() + "===" + responseString);

			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode < 200 || statusCode > 206) {
				return null;
			}
			JSONObject o = JSONObject.parseObject(responseString);
			if(o != null && o.containsKey("token_type") && o.containsKey("access_token")) {
				return o.getString("token_type")+" "+o.getString("access_token");
			}
			return null;
		} catch (ClientProtocolException e) {
			logger.error(e.toString()+url);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString()+url);
			e.printStackTrace();
		} finally {
			try {
				if(httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
