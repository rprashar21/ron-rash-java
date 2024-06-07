package collections;



import java.io.IOException;

public class Sample {
    public static void main(String[] args) throws IOException{
        throwexception(new IOException());
    }

    private static <T extends IOException> void throwexception(final Throwable e) throws T {
        throw (T) e;
    }
}
