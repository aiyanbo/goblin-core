package org.goblin.builder;

import org.goblin.commander.GoblinCommander;
import org.goblin.commander.impl.GoblinCommanderImpl;
import org.goblin.exception.CommandSetParseException;
import org.goblin.executor.CommandExecutor;
import org.goblin.executor.impl.BashCommandExecutorImpl;
import org.goblin.executor.impl.WindowsCommandExecutorImpl;
import org.goblin.parser.impl.CommandParserImpl;
import org.goblin.provider.CommandSetProvider;
import org.goblin.provider.impl.CommandSetProviderImpl;
import org.jmotor.util.SystemUtilities;

import java.io.InputStream;

/**
 * Component:
 * Description:
 * Date: 13-12-31
 *
 * @author Andy Ai
 */
public class GoblinCommanderBuilder {
    private CommandSetProvider commandSetProvider;

    private GoblinCommanderBuilder() {
        commandSetProvider = new CommandSetProviderImpl();
    }

    public static GoblinCommanderBuilder newBuilder() {
        return new GoblinCommanderBuilder();
    }

    public GoblinCommander build() {
        register("commands/operation_system.yml");
        CommandParserImpl commandParser = new CommandParserImpl();
        commandParser.setCommandSetProvider(commandSetProvider);
        GoblinCommanderImpl goblinCommander = new GoblinCommanderImpl();
        goblinCommander.setCommandParser(commandParser);
        goblinCommander.setCommandExecutor(createCommandExecutor());
        return goblinCommander;
    }

    public GoblinCommanderBuilder register(String name) {
        try {
            commandSetProvider.register(name);
        } catch (CommandSetParseException e) {
            throw new IllegalStateException(e.getLocalizedMessage(), e);
        }
        return this;
    }

    public GoblinCommanderBuilder register(InputStream inputStream) {
        try {
            commandSetProvider.register(inputStream);
        } catch (CommandSetParseException e) {
            throw new IllegalStateException(e.getLocalizedMessage(), e);
        }
        return this;
    }

    private CommandExecutor createCommandExecutor() {
        switch (SystemUtilities.getOSName().toLowerCase()) {
            case "windows":
                return new WindowsCommandExecutorImpl();
            default:
                return new BashCommandExecutorImpl();
        }
    }
}
