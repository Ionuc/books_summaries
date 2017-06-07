package com.imesaros.patterns.design.structural.proxy;

public class CommandExecutorProxy implements CommandExecutor
{
    private final CommandExecutor commandExecutor;
    private final boolean isAdmin;

    public CommandExecutorProxy(String user, String password)
    {
        commandExecutor = new CommandExecutorImpl();
        isAdmin = "imesaros".equals(user) && "imesaros".equals(password);
    }

    @Override
    public void runCommand(String command) throws Exception
    {

        if (isAdmin)
        {
            commandExecutor.runCommand(command);
        }
        else
        {
            if (command.startsWith("rm"))
            {
                throw new Exception("rm command is not allowed for non-admin users.");
            }
            commandExecutor.runCommand(command);
        }
    }
}
