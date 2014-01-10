package org.goblin;

import junit.framework.TestCase;
import org.goblin.builder.GoblinCommanderBuilder;
import org.goblin.commander.GoblinCommander;
import org.goblin.dto.ProcessContext;
import org.goblin.dto.Result;
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
    public void testCommander() throws Exception {
        GoblinCommander commander = GoblinCommanderBuilder.newBuilder().build();
        ProcessContext processContext = new ProcessContext();
        Result result = commander.execute(processContext, "go to /tmp");
        Process process = result.getProcess();
        printProcess(process);

        result = commander.execute(processContext, "list details");
        process = result.getProcess();
        printProcess(process);


        result = commander.execute(processContext, "maven version");
        process = result.getProcess();
        printProcess(process);

    }

    private void printProcess(Process process) throws IOException, InterruptedException {
        InputStreamReader isr = new InputStreamReader(process.getInputStream());
        BufferedReader reader = new BufferedReader(isr);
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        CloseableUtilities.closeQuietly(reader);
        System.out.println(process.waitFor());
    }

}
