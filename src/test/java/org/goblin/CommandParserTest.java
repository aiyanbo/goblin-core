package org.goblin;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import junit.framework.TestCase;
import org.goblin.dto.Command;
import org.goblin.dto.CommandSet;
import org.jmotor.util.CloseableUtilities;
import org.jmotor.util.StreamUtilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Component:
 * Description:
 * Date: 13-12-27
 *
 * @author Andy Ai
 */
public class CommandParserTest extends TestCase {
    @SuppressWarnings("unchecked")
    public void testParser() throws YamlException {
        InputStreamReader isr = new InputStreamReader(StreamUtilities.getStream4ClassPath("config/commands.yml"));
        BufferedReader reader = new BufferedReader(isr);
        YamlReader yamlReader = new YamlReader(reader);
        yamlReader.getConfig().setPropertyElementType(CommandSet.class, "commands", Command.class);
        CommandSet commandSet = yamlReader.read(CommandSet.class, Command.class);
        CloseableUtilities.closeQuietly(reader);
        for (Command command : commandSet.getCommands()) {
            System.out.println(command.getName());
        }
    }
}
