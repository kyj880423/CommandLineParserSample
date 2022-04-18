/********************************
 *	프로젝트 : commandline
 *	패키지   : commandline
 *	작성일   : 2022. 4. 18.
 *	작성자   : KYJ (callakrsos@naver.com)
 *******************************/
package commandline;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

import org.apache.commons.cli.CommandLine;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * @author KYJ (callakrsos@naver.com)
 *
 */
public class WebserviceController extends AbstractController {

	/**
	 * @param option
	 */
	public WebserviceController(CommandLine option) {
		super(option);
	}

	/**
	 * @throws NoSuchAlgorithmException
	 * @작성자 : KYJ (callakrsos@naver.com)
	 * @작성일 : 2022. 4. 18.
	 */
	public void process() throws Exception {
		var url = cmd.getOptionValue("webservice");

		HttpClientBuilder b = HttpClientBuilder.create();
		// b = b.setDefaultCookieStore(cookieStore);
		SSLContext ctx = SSLContext.getInstance("TLS");
		ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
		b = b.setSSLContext(ctx);
		b = b.setSSLHostnameVerifier(new HostnameVerifier() {

			@Override
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		});
		try (CloseableHttpClient build = b.build()) {

			HttpGet httpGet = new HttpGet(url);
			try (CloseableHttpResponse execute = build.execute(httpGet)) {
				if (execute.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					String retstring = EntityUtils.toString(execute.getEntity());
					System.out.println(retstring);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
