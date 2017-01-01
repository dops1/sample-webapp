package com.company.foo;

import java.io.*;
import java.util.*;

@SuppressWarnings("serial")
public class PropertyReader implements Serializable {

    private class ConfigurationException extends Exception {
        public ConfigurationException(String message) {
            super(message);
        }
    }

    private final String path;
    private final List<ConfigFile> files;

    public PropertyReader() throws ConfigurationException {
        super();
        path = System.getenv("APP_CONFIG_PATH");
        if (path == null) {
            throw new ConfigurationException("Environment variable APP_CONFIG_PATH not set!");
        }
        files = readFiles();
    }

    public List<ConfigFile> getFiles() {
        return files;
    }

    public ConfigFile getFile(String name) {
        for (ConfigFile f : files) {
            if (f.getName().equals(name)) {
                return f;
            }
        }
        return null;
    }

    public List<ConfigFile> readFiles() {
        String[] files = null;
        files = new File(path).list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".properties");
            }
        });
        if (files == null) {
            return new LinkedList<ConfigFile>();
        }

        List<ConfigFile> result = new LinkedList<ConfigFile>();
        for (String fileName : files) {
            try {
                ConfigFile c = new ConfigFile(fileName, getFileContents(fileName));
                result.add(c);
            } catch (IOException e) {
                //
            }
        }
        return result;
    }

    private String getFileContents(String fileName) throws IOException {
        File f = new File(path, fileName);
        InputStream input = new FileInputStream(f);
        Properties properties = new Properties();
        properties.load(input);
        return getPropertyAsString(properties);
    }

    private String getPropertyAsString(Properties prop) {
        StringWriter writer = new StringWriter();
        prop.list(new PrintWriter(writer));
        return writer.getBuffer().toString();
    }

}