/********************************
 *	프로젝트 : commandline
 *	패키지   : commandline
 *	작성일   : 2022. 4. 18.
 *	작성자   : KYJ (callakrsos@naver.com)
 *******************************/
package commandline;

import java.util.Date;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * @author KYJ (callakrsos@naver.com)
 *
 */
public class TestMain {

	/**
	 * @작성자 : KYJ (callakrsos@naver.com)
	 * @작성일 : 2022. 4. 18.
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) {
		var options = new Options();
		options.addOption("t", false, "Display current time.");
		options.addOption("h", "help", false, "help");

		options.addOption(Option.builder()
				.longOpt("webservice")
				.hasArgs().valueSeparator('=').desc("Get method webservice.").build());

		var parser = new DefaultParser();
		CommandLine cmd;
		try {
			cmd = parser.parse(options, args);
			if (cmd.hasOption("help")) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("ant", options);
				return;
			}

			if (cmd.hasOption("webservice")) {
				WebserviceController controller = new WebserviceController(cmd);
				controller.process();
			} else {
				System.out.println("webservice no value");
			}

			if (cmd.hasOption("t")) {
				System.out.println(new Date());
			} else {
//				System.out.println("No time.");
			}

		} catch (ParseException e) {
			System.err.println("Parsing failed.  Reason: " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
