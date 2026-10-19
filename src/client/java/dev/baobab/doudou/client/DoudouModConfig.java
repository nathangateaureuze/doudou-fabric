package dev.baobab.doudou.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.phys.Vec3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DoudouModConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve(DoudouClient.MOD_ID+".json");

    public Vec3 doudouOffset = new Vec3(0.26, 0.5625, 0.26);
    public Vec3 doudouOffsetStrawBed = new Vec3(0.26, 0.3125, 0.26);
    public float doudouYRotation = 22;

    public static DoudouModConfig load() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());

            if (Files.exists(CONFIG_PATH)) {
                String json = Files.readString(CONFIG_PATH);
                DoudouModConfig config =
                        GSON.fromJson(json, DoudouModConfig.class);

                if (config != null) {
                    return config;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        DoudouModConfig config = new DoudouModConfig();
        config.save();
        return config;
    }

    public void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            Files.writeString(
                    CONFIG_PATH,
                    GSON.toJson(this)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
