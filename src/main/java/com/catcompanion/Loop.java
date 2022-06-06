package com.catcompanion;

import java.text.MessageFormat;
import java.util.ArrayList;

public class Loop {
    public static void GameLoop(ArrayList<Cat> cats, int catId) {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ServiceCommands.println(Data.output,
                "--------------------------------------------------------------");
        CatCommands.catMiniStatus(cats.get(catId));
        CatCommands.checkHunger(cats.get(catId));

        ServiceCommands.println(Data.output,
                (MessageFormat.format(
                "Choose action:\n" +
                        "\tstatus - Check status {0}\n" +
                        "\tplay - Play with {0}\n" +
                        "\tfeed - Feed {0}\n" +
                        "\t4 - \n" +
                        "\t5 - \n" +
                        "\tchoose - Choose another cat" +
                        "\tcreate - Create new cat\n" +
                        "\texit - Close CatCompanion",
                cats.get(0).getName())));
        ServiceCommands.println(Data.output,
                "--------------------------------------------------------------");

    }
}
