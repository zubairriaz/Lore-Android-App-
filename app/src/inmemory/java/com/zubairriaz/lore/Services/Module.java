package com.zubairriaz.lore.Services;

import com.zubairriaz.lore.infrastructure.LoreApplication;

public class Module{
    public static void register(LoreApplication application){
        new inMemoryAccountServices(application);
        new inMemoryContactService(application);
    }
}