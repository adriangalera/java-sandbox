import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.util.List;
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

    @Test
    public void shouldRemoveSomeEntriesInJson(){


        DocumentContext ctx = JsonPath.parse(json).delete("$.order.items.substitutes[*].c");
        System.out.println(ctx.jsonString());





    }
}
