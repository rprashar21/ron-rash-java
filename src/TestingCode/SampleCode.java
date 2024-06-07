package TestingCode;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class SampleCode
{
    static boolean isEven(int n)
    {
        return n%2==0;
    }

    public static void main(String[] args) {

        final List<UUID> uuids = splitStringIntoListOfUUId("071e108d-8a70-4532-b7da-2168d0260d08,1618293d-9d62-416b-9072-d08be54d3d62");
        System.out.println(uuids.get(1));
    }

    private static List<UUID> splitStringIntoListOfUUId(final String caseId) {
        return Stream.of(caseId.split(","))
                .map(String::trim)
                .map(s -> UUID.fromString(s))
                .collect(toList());
    }
}
