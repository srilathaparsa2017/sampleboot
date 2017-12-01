/**
 * 
 */
package org.magento.customer_account_creation;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.springframework.stereotype.Component;

/**
 * @author rameshparsa
 *
 */
@Component
public class CustomerAccountService2 {

	public User addUser(User newUser) {

		//HttpClientConnectionManager httpConnectionManager = new HttpClientConnectionManager();
	    //here should set HttpConnectionManagerParams but not important for you
	    HttpClient httpClient = new HttpClient();

	    PostMethod postMethod = new PostMethod("http://ipaddress/index.php/customer/account/create");
	    postMethod.setRequestHeader("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundaryv9rFKu9q4HNrnj1");
	    postMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
	    //postMethod.setFollowRedirects(false);
		/*postMethod.setRequestHeader("success_url", "");
		postMethod.setRequestHeader("error_url", "");
		postMethod.setRequestHeader("form_key", "9qsxO2f3i1HBrhhK");*/
		//postMethod.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		//postMethod.setRequestHeader("Accept-Encoding", "gzip, deflate");
	    StringPart firstNamePart = new StringPart("firstname", newUser.getFirstname());
	    StringPart middleNamePart = new StringPart("middlename", newUser.getMiddlename());
	    StringPart lastNamePart = new StringPart("lastname", newUser.getLastname());
	    StringPart emailAddressPart = new StringPart("email", newUser.getEmailaddress());
	    StringPart passwordPart = new StringPart("password", newUser.getPassword());
	    StringPart confirmationPart = new StringPart("confirmation", newUser.getPassword());
	    StringPart succeesUrl = new StringPart("success_url", "");
	    StringPart errorUrl = new StringPart("error_url", "");
	    StringPart form_key = new StringPart("form_key", "9qsxO2f3i1HBrhhK");
	    
	    Part[] parts = { firstNamePart, middleNamePart, lastNamePart, emailAddressPart, passwordPart, confirmationPart, succeesUrl, errorUrl, form_key};

	    MultipartRequestEntity multipartRequestEntity = new MultipartRequestEntity(parts, postMethod.getParams());
	    postMethod.setRequestEntity(multipartRequestEntity);
	    try {
			int code = httpClient.executeMethod(postMethod);
			System.out.println("code ======== " +code);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String responseStr = null;
		try {

			responseStr = postMethod.getResponseBodyAsString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println(responseStr);

		return new User();
	}

}
