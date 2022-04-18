/********************************
 *	프로젝트 : commandline
 *	패키지   : commandline
 *	작성일   : 2022. 4. 18.
 *	작성자   : KYJ (callakrsos@naver.com)
 *******************************/
package commandline;

import org.apache.commons.cli.CommandLine;

/**
 * @author KYJ (callakrsos@naver.com)
 *
 */
public abstract class AbstractController {

	/**
	 * @최초생성일 2022. 4. 18.
	 */
	protected CommandLine cmd;

	public AbstractController(CommandLine cmd) {
		super();
		this.cmd = cmd;
	}

	/**
	 * @작성자 : KYJ (callakrsos@naver.com)
	 * @작성일 : 2022. 4. 18.
	 */
	public abstract void process() throws Exception;
}
