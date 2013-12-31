package org.goblin.provider.impl;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import org.goblin.dto.Command;
import org.goblin.dto.CommandSet;
import org.goblin.exception.CommandSetParseException;
import org.goblin.provider.CommandSetProvider;
import org.jmotor.util.CloseableUtilities;
import org.jmotor.util.StreamUtilities;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Component:
 * Description:
 * Date: 13-12-31
 *
 * @author Andy Ai
 */
public class CommandSetProviderImpl implements CommandSetProvider {
    private List<CommandSet> commandSets = Collections.synchronizedList(new ArrayList<CommandSet>());

    @Override
    public void register(String name) throws CommandSetParseException {
        register(StreamUtilities.getStream4ClassPath(name));
    }

    @Override
    public void register(InputStream inputStream) throws CommandSetParseException {
        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(isr);
        YamlReader yamlReader = new YamlReader(reader);
        yamlReader.getConfig().setPropertyElementType(CommandSet.class, "commands", Command.class);
        try {
            CommandSet commandSet = yamlReader.read(CommandSet.class, Command.class);
            commandSets.add(commandSet);
        } catch (YamlException e) {
            throw new CommandSetParseException(e.getLocalizedMessage(), e);
        } finally {
            CloseableUtilities.closeQuietly(reader);
        }
    }

    @Override
    public List<CommandSet> getCommandSets() {
        return commandSets;
    }


}
