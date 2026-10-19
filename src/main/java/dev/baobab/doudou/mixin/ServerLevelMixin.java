package dev.baobab.doudou.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.baobab.doudou.Events.WakeUpAllPlayersCallback;
import net.minecraft.server.level.ServerLevel;

@Mixin (ServerLevel.class)
public class ServerLevelMixin {

    @Inject (method = "wakeUpAllPlayers", at=@At("TAIL"))
    private void TemplateMod$wakeUpAllPlayers(CallbackInfo ci)
    {
        WakeUpAllPlayersCallback.EVENT.invoker().interact((ServerLevel)(Object)this);
    }

}
