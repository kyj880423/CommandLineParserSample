/********************************
 *	프로젝트 : commandline
 *	패키지   : commandline
 *	작성일   : 2022. 4. 18.
 *	작성자   : KYJ (callakrsos@naver.com)
 *******************************/
package commandline;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.stream.Stream;

import javax.net.ssl.X509TrustManager;

/**
 * @author KYJ (callakrsos@naver.com)
 *
 */
public class DefaultTrustManager implements X509TrustManager {

	@Override
	public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		// LOGGER.debug("######################");
		// LOGGER.debug("checkClientTrusted");
		
		// LOGGER.debug("######################");
	}

	@Override
	public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
		// LOGGER.debug("########################################################################################");
		// LOGGER.debug("checkServerTrusted");
		// LOGGER.debug(arg1);

		boolean present = Stream.of(arg0).filter(v -> {

			switch (v.getSigAlgName()) {
			case "SHA256withRSA":
				return true;
			case "SHA384withECDSA":
				return true;
			case "SHA384withRSA":
				return true;
			case "SHA1withRSA":
				return true;
			}

			return false;
		}).findFirst().isPresent();

		if (!present) {
//			LOGGER.debug("Can't not found Truested Algorisms ");
//			Stream.of(arg0).forEach(v -> LOGGER.warn(v.getSigAlgName()));
			throw new CertificateException();
		}

		// LOGGER.debug("########################################################################################");
	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

}
