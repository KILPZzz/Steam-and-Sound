package com.frostybadhalo.create_ss.registry;

import com.simibubi.create.Create;

import net.createmod.catnip.gui.TextureSheetSegment;
import net.createmod.catnip.gui.UIRenderHelper;
import net.createmod.catnip.gui.element.ScreenElement;
import net.createmod.catnip.theme.Color;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum SSGuiTextures implements ScreenElement, TextureSheetSegment {


	TRAIN_HUD_SPEED_BG("widgets", 0, 190, 182, 5),
	TRAIN_HUD_SPEED("widgets", 0, 185, 182, 5),
	TRAIN_HUD_THROTTLE("widgets", 0, 195, 182, 5),
	TRAIN_HUD_THROTTLE_POINTER("widgets", 0, 209, 6, 9),
	TRAIN_HUD_FRAME("widgets", 0, 200, 186, 7),
	TRAIN_HUD_DIRECTION("widgets", 77, 165, 28, 20),
	TRAIN_PROMPT_L("widgets", 8, 209, 3, 16),
	TRAIN_PROMPT_R("widgets", 11, 209, 3, 16),
	TRAIN_PROMPT("widgets", 0, 230, 256, 16),

	TRADE_OVERLAY("widgets", 128, 98, 96, 46);

	public static final int FONT_COLOR = 0x575F7A;

	public final ResourceLocation location;
	private final int width;
	private final int height;
	private final int startX;
	private final int startY;

	SSGuiTextures(String location, int width, int height) {
		this(location, 0, 0, width, height);
	}

	SSGuiTextures(String location, int startX, int startY, int width, int height) {
		this(Create.ID, location, startX, startY, width, height);
	}

	SSGuiTextures(String namespace, String location, int startX, int startY, int width, int height) {
		this.location = new ResourceLocation(namespace, "textures/gui/" + location + ".png");
		this.width = width;
		this.height = height;
		this.startX = startX;
		this.startY = startY;
	}

	@Override
	public ResourceLocation getLocation() {
		return location;
	}

	@OnlyIn(Dist.CLIENT)
	public void render(GuiGraphics graphics, int x, int y) {
		graphics.blit(location, x, y, startX, startY, width, height);
	}

	@OnlyIn(Dist.CLIENT)
	public void render(GuiGraphics graphics, int x, int y, Color c) {
		bind();
		UIRenderHelper.drawColoredTexture(graphics, c, x, y, startX, startY, width, height);
	}

	@Override
	public int getStartX() {
		return startX;
	}

	@Override
	public int getStartY() {
		return startY;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}
}
