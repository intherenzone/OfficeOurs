package edu.umd.cs.officeours;

import android.content.Context;

import edu.umd.cs.officeours.services.ProfService;
import edu.umd.cs.officeours.services.impl.InMemoryProfService;



class DependencyFactory {

    private static ProfService profService;


    static ProfService getProfService(Context context) {
        if (profService == null) {
            profService = new InMemoryProfService(context);
        }
        return profService;
    }
}
