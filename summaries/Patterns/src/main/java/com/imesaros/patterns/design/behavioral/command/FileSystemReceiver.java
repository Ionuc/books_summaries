package com.imesaros.patterns.design.behavioral.command;

public interface FileSystemReceiver
{
    void openFile();

    void writeFile();

    void closeFile();
}