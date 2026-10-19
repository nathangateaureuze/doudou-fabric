package dev.baobab.doudou.Events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.level.ServerLevel;

public interface WakeUpAllPlayersCallback {

    Event<WakeUpAllPlayersCallback> EVENT = EventFactory.createArrayBacked(WakeUpAllPlayersCallback.class, 
        (listeners)->(serverLevel)->
        {
            for(WakeUpAllPlayersCallback listener : listeners)
            {
                listener.interact(serverLevel);
            }
        }
    );
    
    void interact(ServerLevel serverLevel);
}