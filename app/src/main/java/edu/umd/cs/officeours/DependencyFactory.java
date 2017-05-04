package edu.umd.cs.officeours;

import android.content.Context;

import edu.umd.cs.officeours.services.ProfService;
import edu.umd.cs.officeours.services.impl.InMemoryProfService;



public class DependencyFactory {

    private static ProfService profService;


    public static ProfService getStoryService(Context context) {
        if (profService == null) {
            profService = new InMemoryProfService(context);
        }
        return profService;
    }
}
