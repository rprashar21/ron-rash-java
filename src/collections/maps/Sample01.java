package collections.maps;

import static java.util.Objects.nonNull;

public class Sample01 {

    public static void main(String[] args) {


        boolean rd= Boolean.TRUE;
        final boolean unscheduled = (nonNull(rd) && (nonNull(rd))) ? rd: Boolean.FALSE;

        System.out.println(unscheduled);
    }
}
