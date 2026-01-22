package com.adex.payload;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class ModServerReceivers {

    public static void initialize(){
        ServerPlayNetworking.registerGlobalReceiver(LocateRefugeC2S.ID, LocateRefugeReceiver::received);
    }


}
