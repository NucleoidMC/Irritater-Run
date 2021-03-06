package io.github.haykam821.irritaterrun.game;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import io.github.haykam821.irritaterrun.game.map.IrritaterRunMapConfig;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import xyz.nucleoid.plasmid.game.config.PlayerConfig;

public class IrritaterRunConfig {
	public static final Codec<IrritaterRunConfig> CODEC = RecordCodecBuilder.create(instance -> {
		return instance.group(
			IrritaterRunMapConfig.CODEC.fieldOf("map").forGetter(IrritaterRunConfig::getMapConfig),
			PlayerConfig.CODEC.fieldOf("players").forGetter(IrritaterRunConfig::getPlayerConfig),
			SoundEvent.CODEC.optionalFieldOf("destroy_sound", SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER).forGetter(IrritaterRunConfig::getDestroySound),
			Codec.INT.optionalFieldOf("armor_color", 0xFF0000).forGetter(IrritaterRunConfig::getArmorColor)
		).apply(instance, IrritaterRunConfig::new);
	});

	private final IrritaterRunMapConfig mapConfig;
	private final PlayerConfig playerConfig;
	private final SoundEvent destroySound;
	private final int armorColor;

	public IrritaterRunConfig(IrritaterRunMapConfig mapConfig, PlayerConfig playerConfig, SoundEvent destroySound, int armorColor) {
		this.mapConfig = mapConfig;
		this.playerConfig = playerConfig;
		this.destroySound = destroySound;
		this.armorColor = armorColor;
	}

	public IrritaterRunMapConfig getMapConfig() {
		return this.mapConfig;
	}

	public PlayerConfig getPlayerConfig() {
		return this.playerConfig;
	}

	public SoundEvent getDestroySound() {
		return this.destroySound;
	}

	public int getArmorColor() {
		return this.armorColor;
	}
}