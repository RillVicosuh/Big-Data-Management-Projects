package edu.ucr.cs.cs167.rvill095_lab2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Incorrect number of arguments! Expected two arguments.");
            System.exit(-1);
        }

        Path input = new Path(args[0]);
        Path output = new Path(args[1]);

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(input.toUri(), conf);
        FileSystem fs2 = FileSystem.get(output.toUri(), conf);

        if (!fs.exists(input)) {
            System.err.printf("Input file '%s' does not exist!\n", input);
            System.exit(-1);
        }

        if (fs2.exists(output)) {
            System.err.printf("Output file '%s' already exists!\n", output);
            System.exit(-1);
        }

        long startTime = System.nanoTime();
        FSDataInputStream in = fs.open(input);
        FSDataOutputStream out = fs2.create(output);
        byte[] buff = new byte[1024];
        int read;
        long length = 0;
        while ((read = in.read(buff)) > 0) {
            out.write(buff);
            length += read;
        }
        in.close();
        out.close();
        long endTime = System.nanoTime();
        System.out.printf("Copied %d bytes from '%s' to '%s' in %.6f seconds\n", length, input, output,
                (double) (endTime - startTime) / 1000000000);
    }
}
