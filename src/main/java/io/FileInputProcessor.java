package io;

import commands.Command;
import commands.CommandExecutorFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileInputProcessor extends InputProcessor {

    private final String filename;

    public FileInputProcessor(CommandExecutorFactory executorFactory, String filename) {
        super(executorFactory);
        this.filename = filename;
    }

    @Override
    public void run() throws Exception {
        File file = new File(filename);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String input = reader.readLine();
            while (input != null) {
                final Command command = new Command(input);
                processCommand(command);
                input = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

