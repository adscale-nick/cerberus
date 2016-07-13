package org.adscale.cerberus.cli;

import jline.console.ConsoleReader;
import jline.console.completer.Completer;
import jline.console.completer.StringsCompleter;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Cli {

    private static String tempJsonFile = "/tmp/cerberusCreation.json";

    static Map<String, Runnable> commandMap = new HashMap<String, Runnable>() {

        {
            put("account", createEntity("account"));
            put("website", createEntity("website"));
        }
    };


    private static Runnable createEntity(String entity) {
        return () -> {
            try {
                saveJsonToTmpFile(entity);
                openTextEditor();
                postToCreator(entity);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        };
    }


    private static void postToCreator(String creationEndpoint) throws Exception {
        URL url = new URL("http://localhost:9666/" + creationEndpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        final StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(tempJsonFile));

        String line;
        while ((line = br.readLine()) != null)
            sb.append(line);

        String input = sb.toString();
        OutputStream os = conn.getOutputStream();
        os.write(input.getBytes());
        os.flush();

        if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            System.out.println("Failed to create [" + creationEndpoint + "] error message was:");
            StringWriter writer = new StringWriter();
            IOUtils.copy(conn.getErrorStream(), writer, Charsets.UTF_8);
            String theString = writer.toString();
            System.out.println(theString);
            return;
        }

        br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

        String output;
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }
        conn.disconnect();
    }


    private static void saveJsonToTmpFile(String creationEndpoint) throws Exception {
        String cerberusUrl = "http://localhost:9666/";
        try (PrintWriter out = new PrintWriter(tempJsonFile)) {
            URL url = new URL(cerberusUrl + creationEndpoint);
            URLConnection con = url.openConnection();
            InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            String body = IOUtils.toString(in, encoding);
            out.print(body);
        }
        catch (IOException e) {
            System.out.println("Could not connect to: " + cerberusUrl);
        }
    }


    private static void openTextEditor() throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder("vi", tempJsonFile);
        processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectInput(ProcessBuilder.Redirect.INHERIT);

        Process p = processBuilder.start();
        p.waitFor();
    }


    public static void main(String[] args) throws IOException {
        try {
            ConsoleReader reader = new ConsoleReader();
            usage();
            reader.setPrompt("~> ");
            List<Completer> completors = new LinkedList<>();
            completors.add(new StringsCompleter(commandMap.keySet()));
            completors.forEach(reader::addCompleter);

            String line;
            PrintWriter out = new PrintWriter(reader.getOutput());

            while ((line = reader.readLine()) != null) {
                if (line.isEmpty() || line.equalsIgnoreCase("help")) {
                    usage();
                }
                // out.println("======>\"" + line + "\"");
                // out.flush();
                if (line.equalsIgnoreCase("quit") || line.equalsIgnoreCase("exit")) {
                    break;
                }
                if (line.equalsIgnoreCase("cls")) {
                    reader.clearScreen();
                }
                commandMap.keySet().stream().filter(line::equalsIgnoreCase).forEach(command -> commandMap.get(command).run());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void usage() {
        System.out.println("Commands:");
        for (String key : commandMap.keySet()) {
            System.out.println("\t" + key);
        }

    }
}
