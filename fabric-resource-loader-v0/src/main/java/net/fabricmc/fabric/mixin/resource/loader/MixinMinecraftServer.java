/*
 * Copyright (c) 2016, 2017, 2018, 2019 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.fabricmc.fabric.mixin.resource.loader;

import net.minecraft.server.Main;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.resource.ResourceType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.resource.ResourcePackProvider;

import net.fabricmc.fabric.impl.resource.loader.ModResourcePackCreator;

@Mixin(Main.class)
public class MixinMinecraftServer {
	@ModifyArg(method = "main", at = @At(value = "INVOKE", target = "Lnet/minecraft/resource/ResourcePackManager;<init>(Lnet/minecraft/resource/ResourcePackProfile$class_5351;[Lnet/minecraft/resource/ResourcePackProvider;)V"))
	private static ResourcePackProvider[] appendFabricDataPacks(ResourcePackProvider[] packProviders) {
		return ArrayUtils.add(packProviders, new ModResourcePackCreator(ResourceType.SERVER_DATA));
	}
}
