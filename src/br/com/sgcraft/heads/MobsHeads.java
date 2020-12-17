package br.com.sgcraft.heads;

public enum MobsHeads {

	// @formatter:off
	
    BLAZE("MHF_Blaze", "Blaze Head"),
    CAVE_SPIDER("MHF_CaveSpider", "Cave Spider Head", "cavespider"),
    CHICKEN("MHF_Chicken", "Chicken Head"),
    COW("MHF_Cow", "Cow Head"),
    ENDERMAN("MHF_Enderman", "Enderman Head"),
    GHAST("MHF_Ghast", "Ghast Head"),
    IRON_GOLEM("MHF_Golem", "Iron Golem Head", "irongolem", "golem"),
    MAGMA_CUBE("MHF_LavaSlime", "Magma Cube Head", "magmacube", "lava_slime", "lavaslime"),
    MUSHROOM_COW("MHF_MushroomCow", "Mushroom Cow Head", "mushroomcow", "mooshrom"),
    OCELOT("MHF_Ocelot", "Ocelot Head", "cat"),
    PIG("MHF_Pig", "Pig Head"),
    PIG_ZOMBIE("MHF_PigZombie", "Pig Zombie Head", "pigzombie", "pigman", "zombie_pigman", "zombiepigman"),
    SHEEP("MHF_Sheep", "Sheep Head"),
    SLIME("MHF_Slime", "Slime Head"),
    SPIDER("MHF_Spider", "Spider Head"),
    SQUID("MHF_Squid", "Squid Head"),
    VILLAGER("MHF_Villager", "Villager Head"),
    WITHER("MHF_Wither", "Wither Head");

    // @formatter:on

	private String skin;
	private String displayName;
	private String[] otherNames;

	private MobsHeads(String skin, String displayName, String... otherNames) {
		this.skin = skin;
		this.displayName = displayName;
		this.otherNames = otherNames;
	}

	public String getSkinName() {
		return this.skin;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public static MobsHeads forSkullOwnerString(String skullOwner) {
		for (MobsHeads t : values())
			if (t.getSkinName().equalsIgnoreCase(skullOwner))
				return t;

		return null;
	}

	public static MobsHeads forGeneralEntityName(String entityName) {
		for (MobsHeads t : values()) {
			if (entityName.equalsIgnoreCase(t.toString()))
				return t;
			else
				for (String otherName : t.otherNames)
					if (otherName.equalsIgnoreCase(entityName))
						return t;
		}

		return null;
	}

}
