package com.catcompanion;

import java.util.ArrayList;

public class Init {

    public static int GameInit(ArrayList<Cat> cats) {
        int catId = 0;
        cats.add(new Cat());

        ServiceCommands.println(Data.output,
                "--------------------------------------------------------------");
        ServiceCommands.println(Data.output,
                "Please create your cat!");
        //CatCreation.CreateCat(cats, new Cat());
        ServiceCommands.println(Data.output,
                "\nYour new cat id is " + catId);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ServiceCommands.println(Data.output,
                "--------------------------------------------------------------");

        CatCommands.catStatus(cats.get(catId));
        CatCommands.meow(cats.get(catId));

        return catId;
    }
}
