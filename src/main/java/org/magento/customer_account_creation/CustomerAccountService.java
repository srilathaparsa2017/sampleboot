/**
 * 
 */
package org.magento.customer_account_creation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

/**
 * @author rameshparsa
 *
 */
@Component
public class CustomerAccountService {

	public User addUser(User newUser) {

		try {
			CloseableHttpClient httpclient = HttpClientBuilder.create().build();
			HttpPost httppost = new HttpPost("http://ipaddress/index.php/customer/account/create/");

			MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
			reqEntity.addPart("firstname", new StringBody(newUser.getFirstname(), "text/plain", Charset.forName("UTF-8")));

			if (null != newUser.getMiddlename()) {
				reqEntity.addPart("middlename", new StringBody(newUser.getMiddlename(), "text/plain", Charset.forName("UTF-8")));
			}

			reqEntity.addPart("lastname", new StringBody(newUser.getLastname(), "text/plain", Charset.forName("UTF-8")));

			reqEntity.addPart("email", new StringBody(newUser.getEmailaddress(), "text/plain", Charset.forName("UTF-8")));
			reqEntity.addPart("password", new StringBody(newUser.getPassword(), "text/plain", Charset.forName("UTF-8")));
			reqEntity.addPart("confirmation", new StringBody(newUser.getPassword(), "text/plain", Charset.forName("UTF-8")));

			HttpParams params = new BasicHttpParams();
			params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
			httppost.setHeader("Content-Type", "multipart/form-data" );
			httppost.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
			httppost.setEntity(reqEntity);
		
			httppost.setParams(params);

			System.out.println("executing request " + httppost.getRequestLine());
			HttpResponse response = httpclient.execute(httppost);
			/*HttpEntity resEntity = response.getEntity();

			System.out.println("----------------------------------------");
			System.out.println(response.getStatusLine());
			if (resEntity != null) {
				System.out.println("Response content length: " + resEntity.getContentLength());
				System.out.println(resEntity.getContent());
			}
			EntityUtils.consume(resEntity);*/
			 BufferedReader reader = new BufferedReader(new InputStreamReader(
                     response.getEntity().getContent(), "UTF-8"));
             String sResponse;
             StringBuilder s = new StringBuilder();

             while ((sResponse = reader.readLine()) != null) {
                 s = s.append(sResponse);
             }
             System.out.println("response " + s);
} catch (ClientProtocolException e) {
     } catch (IOException e) {
     }
		 /*catch (Exception ignore) {
		}*/

		return new User();
	}

}
