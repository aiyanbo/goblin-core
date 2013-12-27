package org.goblin;

import junit.framework.TestCase;
import org.goblin.commander.GoblinCommander;
import org.goblin.commander.impl.GoblinCommanderImpl;
import org.goblin.dto.ProcessContext;
import org.goblin.exception.CommandExecuteException;
import org.jmotor.util.CloseableUtilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Component:
 * Description:
 * Date: 13-12-26
 *
 * @author Andy Ai
 */
public class GoblinCommanderTest extends TestCase {
    public void testCommander() throws CommandExecuteException, IOException, InterruptedException {
        GoblinCommander commander = new GoblinCommanderImpl();
        ProcessContext processContext = new ProcessContext();
        processContext = commander.execute(processContext, "mvn -version");
        Process process = processContext.getProcess();
        InputStreamReader isr = new InputStreamReader(process.getInputStream());
        BufferedReader reader = new BufferedReader(isr);
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println(process.waitFor());
        CloseableUtilities.closeQuietly(reader);
    }
}
