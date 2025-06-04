package POlling;

import static java.util.Collections.emptyList;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Polling1 {
    public static final List<String> mediaTypes = new ArrayList<>();
    public static void main(String[] args) {

    }

//    public void pollForResponse(final String path,
//                                final String contentType, final String userId,
//                                final Map<String, Object> queryParams,
//                                final Status status,
//                                final Matcher[] matchers, final int retries) {
//
//        final RequestParams requestParams = getRequestParams(path, contentType, userId, queryParams);
//
//        ResponseAssertionHolder responseAssertionHolder = null;
//
//        for (int i = 0; i <= retries; i++) {
//
//            final ResponseData tempResponseData = poll(requestParams).with().timeout(5L, SECONDS).until();
//
//            if (status != tempResponseData.getStatus()) {
//                if (i == retries) {
//                    responseAssertionHolder = new ResponseAssertionHolder(tempResponseData, emptyList());
//                    break;
//                }
//                pollDelay();
//                continue;
//            }
//
//            final List<Description> errorDescriptions = newArrayList();
//            for (Matcher matcher : matchers) {
//                final StringDescription description = new StringDescription();
//                matcher.describeMismatch(tempResponseData, description);
//                if (StringUtils.isNotBlank(description.toString())) {
//                    errorDescriptions.add(description);
//                }
//            }
//            responseAssertionHolder = new ResponseAssertionHolder(tempResponseData, errorDescriptions);
//
//            if (i == retries || errorDescriptions.isEmpty()) {
//                break;
//            }
//
//            pollDelay();
//        }
//
//
//        if (null == responseAssertionHolder) {
//            fail("Response should not be null");
//        }
//        Status responseStatus = responseAssertionHolder.getResponseData().getStatus();
//        if (!status.equals(responseStatus)) {
//            fail("Expected status code " + status + " but obtained " + responseStatus);
//        }
//
//        final List<Description> descriptions = responseAssertionHolder.getDescriptions();
//        if (CollectionUtils.isNotEmpty(descriptions)) {
//            final String errorMessage = descriptions.stream().map(Object::toString)
//                                                .collect(Collectors.joining("\n")) + "\n Actual Payload: \n" + responseAssertionHolder.getResponseData().getPayload();
//            fail("Error matching payload\n" + errorMessage);
//        }
//
//
//        final String payload = responseAssertionHolder.getResponseData().getPayload();
//        LOGGER.info("Query End Point Response: {}", payload);
//        return new JsonPath(payload);
//    }
}
