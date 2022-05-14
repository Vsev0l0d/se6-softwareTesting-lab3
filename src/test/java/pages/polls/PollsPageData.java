package pages.polls;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PollsPageData {
    private static final Random random = new Random();
    private static final List<String> data = new ArrayList<String>() {{
        add("Do you think that Bitcoin will survive?");
        add("Do you think if crypto will be available for all people in the world?");
        add("Do you think will Eth will survive?");
        add("Do you think will StableCoin will survive?");
        add("Do you think will DogeCoin will survive?");
    }};

    public static String getTestData() {
        int randomIndex = random.nextInt(data.size());
        return data.get(randomIndex);
    }
}

