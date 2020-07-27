import com.jayway.jsonpath.JsonPath;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class RemoveEntriesInJson {

    private final String json = "{\n"
        + "    \"order\": {\n"
        + "        \"items\": {\n"
        + "            \"substitutes\": [\n"
        + "                {\n"
        + "                    \"b\": \"b\",\n"
        + "                    \"c\": \"c\",\n"
        + "                    \"d\": \"d\"\n"
        + "                },\n"
        + "                {\n"
        + "                    \"b\": \"b\",\n"
        + "                    \"c\": \"c\",\n"
        + "                    \"d\": \"d\"\n"
        + "                }\n"
        + "            ],\n"
        + "            \"nbs\": [\n"
        + "                {\n"
        + "                    \"b\": \"b\",\n"
        + "                    \"c\": \"c\",\n"
        + "                    \"d\": \"d\"\n"
        + "                },\n"
        + "                {\n"
        + "                    \"b\": \"b\",\n"
        + "                    \"c\": \"c\",\n"
        + "                    \"d\": \"d\"\n"
        + "                }\n"
        + "            ],\n"
        + "            \"ncs\": [\n"
        + "                {\n"
        + "                    \"c\": \"c\"\n"
        + "                },\n"
        + "                {\n"
        + "                    \"c\": \"c\"\n"
        + "                }\n"
        + "            ]\n"
        + "        }\n"
        + "    }\n"
        + "}";

    private final String arrayJson = "[\n"
        + "    {\n"
        + "        \"a\": \"a\",\n"
        + "        \"b\": \"b\",\n"
        + "        \"c\": \"c\"\n"
        + "    },\n"
        + "    {\n"
        + "        \"a\": \"a\",\n"
        + "        \"b\": \"b\",\n"
        + "        \"c\": \"c\"\n"
        + "    },\n"
        + "]";

    @Test
    public void shouldRemoveSomeEntriesInJson() {
        Object o = JsonPath.parse(json).read("$.order.items.substitutes[1].h");
        System.out.println(o instanceof Map);
        System.out.println(o instanceof List);

//        DocumentContext ctx = JsonPath.parse(json).delete("$.order.items.substitutes[*].c");
//        System.out.println(ctx.jsonString());
    }

    @Test
    public void shouldIterateArray() {
        Object o = JsonPath.parse(arrayJson).read("$.[0].c");
        System.out.println(o);
    }
}
