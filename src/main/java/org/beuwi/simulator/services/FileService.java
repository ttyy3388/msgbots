package org.beuwi.simulator.services;

import java.io.File;

public class FileService
{
    final public static File PROGRAM_FOLDER  = new File(System.getProperty("user.dir"));
    final public static File DATA_FOLDER     = new File(PROGRAM_FOLDER + File.separator + "data");
    final public static File PROJECT_FOLDER  = new File(PROGRAM_FOLDER + File.separator + "bots");
}