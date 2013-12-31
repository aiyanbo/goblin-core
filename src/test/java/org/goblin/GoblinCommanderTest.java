package org.goblin;

import junit.framework.TestCase;
import org.goblin.builder.GoblinCommanderBuilder;
import org.goblin.commander.GoblinCommander;
import org.goblin.dto.ProcessContext;
import org.jmotor.util.CloseableUtilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Component:
 * Description:
 * Date: 13-12-26
 *
 * @author Andy Ai
 */
public class GoblinCommanderTest extends TestCase {
    public void testCommander() throws Exception {
        GoblinCommander commander = GoblinCommanderBuilder.newBuilder().build();
        ProcessContext processContext = new ProcessContext();
        processContext = commander.execute(processContext, "go to /tmp");
        Process process = processContext.getProcess();
        InputStreamReader isr = new InputStreamReader(process.getInputStream());
        BufferedReader reader = new BufferedReader(isr);
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println(process.waitFor());

        processContext = commander.execute(processContext, "list details");
        process = processContext.getProcess();
        isr = new InputStreamReader(process.getInputStream());
        reader = new BufferedReader(isr);
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println(process.waitFor());
        CloseableUtilities.closeQuietly(reader);
    }

}
