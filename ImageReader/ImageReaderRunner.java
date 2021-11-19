// ImagaReaderRunner.java
import imagereader.IImageIO;
import imagereader.IImageProcessor;
import imagereader.Runner;

public class ImageReaderRunner {
    public static void main(String[] args) {
        IImageIO imageioer = new ImplementImageIO();
        IImageProcessor processor = new ImplementImageProcessor();
        Runner.run(imageioer, processor);
    }
}