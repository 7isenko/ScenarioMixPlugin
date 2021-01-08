package io.github._7isenko.scenariomix.utils;

import org.bukkit.Material;

public class MaterialUtils {
    public static Material getMaterial(String materialName){
        Material material = Material.getMaterial(materialName); //в 1.12- нету параметра legacyName
        if (material == null) material = Material.getMaterial(materialName, true); //1.13+
        return material;
    }
}
